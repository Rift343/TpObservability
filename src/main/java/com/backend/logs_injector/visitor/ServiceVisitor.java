package com.backend.logs_injector.visitor;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;

import spoon.reflect.declaration.CtAnnotation;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.visitor.CtScanner;

public class ServiceVisitor extends CtScanner {
    MethodInjectionVisitor v1;

    @Override
    public <T> void visitCtClass(CtClass<T> ctClass) {

        List<CtAnnotation<? extends Annotation>> annotation = ctClass.getAnnotations();

        boolean isService = annotation.stream().anyMatch(a -> a.getAnnotationType().getSimpleName().equals("Service"));

        if (isService) {
            System.out.println("Visiting Service class: " + ctClass.getSimpleName());
            Collection<CtField<?>> fields = ctClass.getFields();
            boolean loggerIsPresent = fields.stream().anyMatch(f-> f.getSimpleName().equals("logger") && f.getType().getSimpleName().equals("Logger"));
            if (!loggerIsPresent) {
                System.out.println("Injecting logger in class: " + ctClass.getSimpleName());
                CtField<?> loggerField = ctClass.getFactory().createField();
                loggerField.setSimpleName("logger");
                loggerField.setType(ctClass.getFactory().Type().createReference("org.apache.logging.log4j.Logger"));
                loggerField.addModifier(ModifierKind.PRIVATE);
                loggerField.addModifier(ModifierKind.STATIC);
                loggerField.addModifier(ModifierKind.FINAL);
                
                String loggerInitialization = "org.apache.logging.log4j.LogManager.getLogger(" + ctClass.getSimpleName() + ".class)";
                loggerField.setDefaultExpression(ctClass.getFactory().Code().createCodeSnippetExpression(loggerInitialization));
                ctClass.addFieldAtTop(loggerField);
                //On injecte le code dans chaque méthode
                for (CtMethod<?> method : ctClass.getMethods()) {
                    method.accept(new MethodInjectionVisitor());
                }
            }
        }
        super.visitCtClass(ctClass);
    }
}
