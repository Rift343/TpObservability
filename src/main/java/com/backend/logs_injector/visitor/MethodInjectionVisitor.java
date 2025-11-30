package com.backend.logs_injector.visitor;

import java.util.ArrayList;
import java.util.Collection;

import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.CtScanner;

public class MethodInjectionVisitor extends CtScanner{

    @Override
    public <T> void visitCtMethod(CtMethod<T> m) {
        CtCodeSnippetStatement snippet = m.getFactory().Core().createCodeSnippetStatement();
        
        //On récupère le nom de la méthode
        String methodName = m.getSimpleName();
        //On récupère l'ensemble des paramètres de la méthode
        Collection<String> params = new ArrayList<>();
        m.getParameters().forEach(p -> params.add(p.getSimpleName()));
        
        //On crée le code à injecter
        StringBuilder codeToInject = new StringBuilder();
        codeToInject.append("logger.info(\"method ").append(methodName).append("-");
        if (params.isEmpty()) {
            codeToInject.append("none");
        } else
        {
            params.forEach(param -> codeToInject.append(param).append("=\" + ").append(param).append(" + \","));
            //supprimer la dernière virgule et l'espace
            codeToInject.setLength(codeToInject.length() - 1);
        }
        codeToInject.append("\")");
        snippet.setValue(codeToInject.toString());
        m.getBody().insertBegin(snippet);
        super.visitCtMethod(m);
    }

}
