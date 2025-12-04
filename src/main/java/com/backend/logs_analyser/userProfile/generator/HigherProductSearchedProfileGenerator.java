package com.backend.logs_analyser.userProfile.generator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.backend.logs_analyser.fetchDatabase.MostExpensiveProductInDatabase;
import com.backend.logs_analyser.userProfile.LogEvent;
import com.backend.logs_analyser.userProfile.UserProfile;
import com.backend.logs_analyser.userProfile.generator.formalProfile.FormalHigherPriceProfile;
import com.backend.logs_analyser.userProfile.generator.formalProfile.searchedProduct;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HigherProductSearchedProfileGenerator implements IGenerator {

    private final Collection<UserProfile> userProfiles;
    private final int nomberOfUsers;

    public HigherProductSearchedProfileGenerator(Collection<UserProfile> userProfile, int nomberOfUsers) {
        this.userProfiles = userProfile;
        this.nomberOfUsers = nomberOfUsers;
    }

    public void generate(){
        int count = 0;
        HashMap<Integer,FormalHigherPriceProfile> formalHigherPriceProfiles = new HashMap<>();
        ArrayList<FormalHigherPriceProfile> formalHigherPriceList = new ArrayList<>();

        for (UserProfile userProfile : userProfiles) {
            long userId = userProfile.getUserId();
            List<LogEvent> eventProductId = userProfile.getEvents().stream().filter(event-> event.event.equals("GET_PRODUCT_BY_ID")).toList();
            ArrayList<searchedProduct> searchedProducts = new ArrayList<>();
            eventProductId.stream().forEach(event -> {
                int id = event.id;
                double price = MostExpensiveProductInDatabase.findProductPriceById(id);
                searchedProduct product = new searchedProduct(id, price);
                searchedProducts.add(product);
                
            });
            //searchedProducts.sort(Comparator.comparingDouble(searchedProduct::getPrice));
            FormalHigherPriceProfile profile = new FormalHigherPriceProfile(userId, searchedProducts);
            formalHigherPriceList.add(profile);
        }
        formalHigherPriceList.sort(Comparator.comparingDouble(FormalHigherPriceProfile::getHighestPrice));
        int reverseCount = formalHigherPriceList.size() - 1;
        while (count < this.nomberOfUsers) { 
            formalHigherPriceProfiles.put(count + 1 ,formalHigherPriceList.get(reverseCount));
            count = count + 1;
            reverseCount = reverseCount - 1;
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonOutput = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(formalHigherPriceProfiles);
            System.out.println(jsonOutput);
            //On ecrit le JSON dans un fichier
            Files.writeString(Paths.get("logs/higherPriceSearchedByUserProfile.json"), jsonOutput);
        } catch (IOException e) {
            System.err.println("Error converting to JSON: " + e.getMessage());
        }



    };

    


}
