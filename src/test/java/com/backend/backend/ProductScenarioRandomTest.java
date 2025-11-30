package com.backend.backend;

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
            user.setAge(18 + random.nextInt(30)); // âge entre 18 et 48
            User userId = userService.addUser(user);

            for (int s = 1; s <= 40; s++) {
                String name = "Product_" + u + "_" + s;
                Double price = 10.0 + random.nextDouble() * 1000; // prix entre 10 et 110
                String dateStr = "202" + random.nextInt(5) + "-" + (1 + random.nextInt(12)) + "-" + (1 + random.nextInt(28));
                java.sql.Date date = java.sql.Date.valueOf(dateStr);

                int action = random.nextInt(5); // 0=add, 1=get, 2=update, 3=delete, 4=getById

                switch (action) {
                    case 0 -> productService.addProduct(name, price, date, userId.getId());
                    case 1 -> productService.getAllProducts(userId.getId());
                    case 2 -> productService.updateProduct(
                                (long) random.nextInt(20) + 1,
                                name + "_Updated",
                                price + 5,
                                date,
                                userId.getId()
                            );
                    case 3 -> productService.deleteProductById((long) random.nextInt(20) + 1, userId.getId());
                    case 4 -> productService.getProductById((long) random.nextInt(20) + 1, userId.getId());
                }
            }
        }
    }
}
