package com.backend.logs_analyser.userProfile.generator;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;

import com.backend.logs_analyser.userProfile.UserProfile;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReaderProductProfileGenerator {


    private final Collection<UserProfile> usersProfile;
    private final int nomberOfUsers;

    public ReaderProductProfileGenerator(Collection<UserProfile> usersProfile, int nb) {
        this.usersProfile = usersProfile;
        this.nomberOfUsers = nb;
    }

    public void generate(){
        int count = 0;
        HashMap<Integer,FormalReaderProfile> readerProfiles = new HashMap<>();
        ArrayList<FormalReaderProfile> readerArray = new ArrayList<>();
        for (UserProfile profile : this.usersProfile) {
            int addproduct = profile.getActionCounts().get("ADD_PRODUCT");
            int getAllProduct = profile.getActionCounts().get("GET_ALL_PRODUCTS");
            int updateProduct = profile.getActionCounts().get("UPDATE_PRODUCT");
            int deleteProduct = profile.getActionCounts().get("DELETE_PRODUCT");
            int getProductById = profile.getActionCounts().get("GET_PRODUCT_BY_ID");
            long userId = profile.getUserId();
            if (userId != 0) {
                FormalReaderProfile formalProfile = new FormalReaderProfile(userId, addproduct, getAllProduct, updateProduct, deleteProduct, getProductById);
                readerArray.add(formalProfile);             
            }
        }
        readerArray.sort(Comparator.comparingInt(FormalReaderProfile::getReadAction));
        int reverseCount = readerArray.size() - 1;
        while (count < this.nomberOfUsers) { 
            readerProfiles.put(count + 1 ,readerArray.get(reverseCount));
            count = count + 1;
            reverseCount = reverseCount - 1;
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonOutput = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(readerProfiles);
            System.out.println(jsonOutput);
            //On ecrit le JSON dans un fichier
            Files.writeString(Paths.get("logs/readerUserProfile.json"), jsonOutput);
        } catch (Exception e) {
            System.err.println("Error converting to JSON: " + e.getMessage());
        }
    
    }

}
