package com.backend.logs_analyser.fetchDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;

public class MostExpensiveProductInDatabase {
    private static final String URL = "jdbc:postgresql://localhost:5432/DATA_BASE_API";
    private static  final String USER = "API_ADMIN";
    private static final  String PASSWORD = "gfityf_voul_4586";

    public static Collection<Long> findMostExpensiveProductId() {

        String sql = "SELECT id FROM products ORDER BY price DESC";
        Collection<Long> productIds = new java.util.ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("id");
                productIds.add(id);
            }
            return productIds;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productIds; // si aucun produit trouvé
    }

}
