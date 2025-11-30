package com.backend.logs_analyser;

import java.util.*;
import com.backend.logs_analyser.logs_parser.Parser;
import com.backend.logs_analyser.logs_reader.Reader;
import com.backend.logs_analyser.userProfile.UserProfile;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LogsAnalyser {

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

            // Récupère ou crée le profil utilisateur
            UserProfile profile = profilesMap.computeIfAbsent(userId, UserProfile::new);

            // Met à jour le profil avec ce log
            profile.updateProfile(log);
        }

        // Transforme la map en collection
        Collection<UserProfile> usersProfile = profilesMap.values();

        // Sérialisation en JSON
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonOutput = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(usersProfile);
            System.out.println(jsonOutput);
        } catch (Exception e) {
            System.err.println("Error converting to JSON: " + e.getMessage());
        }

        System.out.println("Logs Analyser Finished");
    }
}

