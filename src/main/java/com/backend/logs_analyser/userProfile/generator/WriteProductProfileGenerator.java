package com.backend.logs_analyser.userProfile.generator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;

import com.backend.logs_analyser.userProfile.UserProfile;
import com.backend.logs_analyser.userProfile.generator.formalProfile.FormalWriterProfile;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WriteProductProfileGenerator implements IGenerator {

    private final Collection<UserProfile> usersProfile;
    private final int nomberOfUsers;

    public WriteProductProfileGenerator(Collection<UserProfile> usersProfile, int nb) {
        this.usersProfile = usersProfile;
        this.nomberOfUsers = nb;
    }

    public void generate(){
        int count = 0;
        HashMap<Integer,FormalWriterProfile> writerProfiles = new HashMap<>();
        ArrayList<FormalWriterProfile> writerArray = new ArrayList<>();
        for (UserProfile profile : this.usersProfile) {
            int addproduct = profile.getActionCounts().get("ADD_PRODUCT");
            int getAllProduct = profile.getActionCounts().get("GET_ALL_PRODUCTS");
            int updateProduct = profile.getActionCounts().get("UPDATE_PRODUCT");
            int deleteProduct = profile.getActionCounts().get("DELETE_PRODUCT");
            int getProductById = profile.getActionCounts().get("GET_PRODUCT_BY_ID");
            long userId = profile.getUserId();
            if (userId != 0) {
                FormalWriterProfile formalProfile = new FormalWriterProfile(userId, addproduct, getAllProduct, updateProduct, deleteProduct, getProductById);
                writerArray.add(formalProfile);             
            }
        }
        writerArray.sort(Comparator.comparingInt(FormalWriterProfile::getWriteAction));
        int reverseCount = writerArray.size() - 1;
        while (count < this.nomberOfUsers) { 
            writerProfiles.put(count + 1 ,writerArray.get(reverseCount));
            count = count + 1;
            reverseCount = reverseCount - 1;
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonOutput = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(writerProfiles);
            System.out.println(jsonOutput);
            //On ecrit le JSON dans un fichier
            Files.writeString(Paths.get("logs/writerUserProfile.json"), jsonOutput);
        } catch (IOException e) {
            System.err.println("Error converting to JSON: " + e.getMessage());
        }
    
    }

    

}
