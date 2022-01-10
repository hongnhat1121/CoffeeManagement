/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.database;

import com.nthn.configs.Utils;
import com.nthn.pojo.Product;
import com.nthn.services.ProductService;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author HONGNHAT
 */
public class ProductTester {

    @ParameterizedTest(name = "{index} => id={0}, productName={1}")
    @CsvSource({"94c8de04-3447-4882-9be5-423ddf72dfaf, Americano",
        "d3bec52c-a55e-4612-94b7-4b22df887bb6, Bánh caramel phô mai",
        "06e8e958-8fc9-4edd-8aa4-9bdfb767024f, Bánh chuối"})
    public void testGetProductByValidID(String id, String productName) {
        try {
            ProductService service = new ProductService();
            Product product = service.getProduct(id);

            Assertions.assertEquals(id, product.getProductID());
            Assertions.assertEquals(productName, product.getProductName());
        } catch (SQLException ex) {
            Logger.getLogger(ProductTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "23438ihfskjfh74", ""})
    public void testGetProductByInvalidID(String input) throws SQLException {
        ProductService service = new ProductService();
        Product product = service.getProduct(input);

        Assertions.assertNull(product);
    }

    //Test product unique
    @Test
    public void testProductUnique() {
        try {
            ProductService service = new ProductService();
            List<Product> products = service.getProducts();

            Set<Product> products1 = new HashSet<>(products);

            Assertions.assertEquals(products.size(), products1.size());
        } catch (SQLException ex) {
            Logger.getLogger(ProductTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
