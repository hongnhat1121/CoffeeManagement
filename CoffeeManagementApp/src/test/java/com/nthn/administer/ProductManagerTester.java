/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.administer;

import com.nthn.pojo.Category;
import com.nthn.pojo.Product;
import com.nthn.services.ProductService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author HONGNHAT
 */
public class ProductManagerTester {

    private ProductService service = new ProductService();

    @ParameterizedTest
    @ValueSource(strings = {"c9491382-63e5-4920-ab42-c04fed63bc45"})
    public void testDeleteProductSuccess(String input) throws SQLException {
        service.deleteProduct(input);
        Product product = service.getProduct(input);
        Assertions.assertNull(product);
    }

    @ParameterizedTest(name = "{index} => id={0}, name={1}, price={2}, category={3}")
    @CsvSource({"c9491382-ree5-4920-ab42-c04fed63bc45, Hồng trà, 26000, Đồ uống"})
    public void testAddProductSuccess(String id, String name, String price, String category) {
        try {
            Product product = new Product(id, name, Integer.parseInt(price), Category.getByContent(category));
            service.addProduct(product);

            product = service.getProduct(id);

            Assertions.assertNotNull(product);
        } catch (SQLException ex) {
            Logger.getLogger(TableManagerTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @ParameterizedTest(name = "{index} => id={0}, name={1}, price={2}, category={3}")
    @CsvSource({"c9491382-ree5-4920-ab42-c04fed63bc45, Hồng trà, 26000, Đồ uống"})
    public void testAddProductFailed(String id, String name, String price, String category) {
        try {
            Product product = new Product(id, name, Integer.parseInt(price), Category.getByContent(category));
            service.addProduct(product);

            product = service.getProduct(id);

            Assertions.assertNull(product);
        } catch (SQLException ex) {
            Logger.getLogger(TableManagerTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @ParameterizedTest(name = "{index} => id={0}, name={1}, price={2}, category={3}")
    @CsvSource({"c9491382-ree5-4920-ab42-c04fed63bc45, Hồng trà, 27000, Đồ uống"})
    public void testUpdateProductSuccess(String id, String name, String price, String category) {
        try {
            Product p = service.getProduct(id);

            Product product = new Product(id, name, Integer.parseInt(price), Category.getByContent(category));
            service.updateProduct(product);

            product = service.getProduct(id);

            Assertions.assertNotEquals(p, product);
        } catch (SQLException ex) {
            Logger.getLogger(TableManagerTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
