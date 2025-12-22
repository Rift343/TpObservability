package com.backend.logs_injector;

import com.backend.logs_injector.visitor.ServiceVisitor;

import spoon.Launcher;
import spoon.reflect.CtModel;

public class LogsInjector {

    public static ServiceVisitor vis1;


    public static void main(String[] args) {
        System.out.println("Begin Logs Injection...");
        //on parse la backend avec spoon
        Launcher launcher = new Launcher();
        launcher.addInputResource("src/main/java/com/backend/backend");
        launcher.getEnvironment().setNoClasspath(true);
        launcher.buildModel();

        CtModel model = launcher.getModel();

        //récupérer toutes les classes 
        ServiceVisitor serviceVisitor = new ServiceVisitor();
        model.getAllTypes().forEach(ctType -> {
            ctType.accept(serviceVisitor);
        });
        // Sauvegarder le code instrumenté
        launcher.prettyprint();



        System.out.println("Logs Injection finished.");
    }

}
