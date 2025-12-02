package com.backend.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.backend.backend.entities.User;
import com.backend.backend.service.ProductService;
import com.backend.backend.service.UserService;

@SpringBootTest
public class ProductScenarioRandomTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    private final Random random = new Random();

    @Test
void simulateRandomScenarios() {
    for (int u = 1; u <= 10; u++) {
        User user = new User();
        user.setName("User" + u);
        user.setPassword("pass" + u);
        user.setEmail("user" + u + "@test.com");
        user.setAge(18 + random.nextInt(30));
        User userId = userService.addUser(user);

        // Liste des produits créés par cet utilisateur
        List<Long> productIds = new ArrayList<>();

        for (int s = 1; s <= 40; s++) {
            String name = "Product_" + u + "_" + s;
            Double price = 10.0 + random.nextDouble() * 1000;
            String dateStr = "202" + random.nextInt(5) + "-" + (1 + random.nextInt(12)) + "-" + (1 + random.nextInt(28));
            java.sql.Date date = java.sql.Date.valueOf(dateStr);

            int action = random.nextInt(5);

            switch (action) {
                case 0 -> {
                    Long productId = productService.addProduct(name, price, date, userId.getId());
                    if (productId != null) {
                        productIds.add(productId);
                    }
                }
                case 1 -> productService.getAllProducts(userId.getId());
                case 2 -> {
                    if (!productIds.isEmpty()) {
                        Long productId = productIds.get(random.nextInt(productIds.size()));
                        productService.updateProduct(productId, name + "_Updated", price + 5, date, userId.getId());
                    }
                }
                case 3 -> {
                    if (!productIds.isEmpty()) {
                        int index = random.nextInt(productIds.size());
                        Long productId = productIds.remove(index);
                        productService.deleteProductById(productId, userId.getId());
                    }
                }
                case 4 -> {
                    if (!productIds.isEmpty()) {
                        Long productId = productIds.get(random.nextInt(productIds.size()));
                        productService.getProductById(productId, userId.getId());
                    }
                }
            }
        }
    }
}

}
