package com.backend.logs_analyser;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.*;

import com.backend.logs_analyser.fetchDatabase.MostExpensiveProductInDatabase;
import com.backend.logs_analyser.logs_parser.Parser;
import com.backend.logs_analyser.logs_reader.Reader;
import com.backend.logs_analyser.userProfile.UserProfile;
import com.backend.logs_analyser.userProfile.generator.ReaderProductProfileGenerator;
import com.backend.logs_analyser.userProfile.generator.WriteProductProfileGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LogsAnalyser {

    private static final int limit = 3;

    public static void main(String[] args) {
        System.out.println("Logs Analyser Started");

        // Lire et parser les logs
        Reader reader = new Reader();
        Collection<Parser> parsedLogs = reader.getParsedLogs();

        // Map pour regrouper les profils par userId
        Map<Long, UserProfile> profilesMap = new HashMap<>();

        // Construire les profils utilisateurs
        for (Parser log : parsedLogs) {
            Long userId =(long) log.getUserId();

            if (log.getUserId() != 0) {
                // Récupère ou crée le profil utilisateur
                UserProfile profile = profilesMap.computeIfAbsent(userId, UserProfile::new);
                // Met à jour le profil avec ce log
                profile.updateProfile(log);
            }

            
        }

        // Transforme la map en collection
        Collection<UserProfile> usersProfile = profilesMap.values();

        // Sérialisation en JSON
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonOutput = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(usersProfile);
            System.out.println(jsonOutput);
            //On ecrit le JSON dans un fichier
            Files.writeString(Paths.get("logs/users_profiles.json"), jsonOutput);
        } catch (Exception e) {
            System.err.println("Error converting to JSON: " + e.getMessage());
        }

        // Analyse des profils utilisateurs

        Collection<Long> findMostExpensiveProductId = MostExpensiveProductInDatabase.findMostExpensiveProductId();
        /* 

        for (UserProfile profile : usersProfile) {
            System.out.println("=== Profil utilisateur " + profile.getUserId() + " ===");

            // Afficher les compteurs d'actions
            System.out.println("Actions : " + profile.getActionCounts());

            // Déterminer le type de profil
            Map<String, Integer> counts = profile.getActionCounts();
            int reads = counts.get("GET_ALL_PRODUCTS") + counts.get("GET_PRODUCT_BY_ID");
            int writes = counts.get("ADD_PRODUCT") + counts.get("UPDATE_PRODUCT") + counts.get("DELETE_PRODUCT");


            if (reads > writes) {
                System.out.println("Profil : orienté lecture ");
            } else if (writes > reads) {
                System.out.println("Profil : orienté écriture ");
            }

            // Montré le produit le plus cher consulté par l'utilisateur
            List<LogEvent> events = profile.getEvents();
            for (LogEvent event : events) {
                if (event.event.equals("GET_PRODUCT_BY_ID")) {
                    Long productId =(long) event.id;
                    if (findMostExpensiveProductId.contains(productId)) {
                        System.out.println("Produit le plus cher consulté : ID " + productId);
                    }
                }
            }  
        }
        */

        WriteProductProfileGenerator writerProfile = new WriteProductProfileGenerator(usersProfile, limit);
        writerProfile.generate();

        ReaderProductProfileGenerator readerProfil = new ReaderProductProfileGenerator(usersProfile, limit);
        readerProfil.generate();
        
        System.out.println("Logs Analyser Finished");
    }
}

