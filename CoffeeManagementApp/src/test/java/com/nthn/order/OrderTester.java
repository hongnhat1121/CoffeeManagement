/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.order;

import com.nthn.coffeemanagementapp.OrderController;
import com.nthn.configs.Utils;
import com.nthn.pojo.Category;
import com.nthn.pojo.Order;
import com.nthn.pojo.OrderDetail;
import com.nthn.pojo.Product;
import com.nthn.services.OrderDetailService;
import com.nthn.services.OrderService;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author HONGNHAT
 */
public class OrderTester {

    private final OrderService service = new OrderService();
    private final OrderDetailService ods = new OrderDetailService();

    @Test
    public void testOrderSuccess() throws SQLException {
        String employeeID = "0fbbabeb-331a-4a03-8536-c3a9fbcd3381";
        String tableID = "5713adde-002b-46d7-bcaf-03ff01c5ac39";
        Product product = new Product("17b34b86-0742-4df0-b008-3b4060381bd8",
                "Latte Táo Lê Quế Đá", 59000, Category.DRINK);
        String note = "";

        Order order = new Order();
        order.setOrderID(Utils.randomID());

        OrderDetail detail = new OrderDetail(order.getOrderID(), product.getProductID(),
                tableID, 1, product.getUnitPrice(), note);
        order = new Order(order.getOrderID(), LocalDate.now(),
                BigDecimal.valueOf(detail.getQuantity() * detail.getUnitPrice()),
                employeeID, tableID, 0);

        service.addOrder(order);
        ods.addOrderDetail(detail);

        Order order1 = service.getOrderByID(order.getOrderID());

        Assertions.assertNotNull(order1);
    }

    @Test
    public void testOrderFailed() throws SQLException {
        String employeeID = "0fbbabeb-331a-4a03-8536-c3a9fbcd3381";
        String tableID = "";
        Product product = new Product("17b34b86-0742-4df0-b008-3b4060381bd8",
                "Latte Táo Lê Quế Đá", 59000, Category.DRINK);
        String note = "";

        Order order = new Order();
        order.setOrderID(Utils.randomID());

        OrderDetail detail = new OrderDetail(order.getOrderID(), product.getProductID(),
                tableID, 1, product.getUnitPrice(), note);
        order = new Order(order.getOrderID(), LocalDate.now(),
                BigDecimal.valueOf(detail.getQuantity() * detail.getUnitPrice()),
                employeeID, tableID, 0);

        service.addOrder(order);
        ods.addOrderDetail(detail);

        Order order1 = service.getOrderByID(order.getOrderID());

        Assertions.assertNull(order1);
    }


    @ParameterizedTest(name = "{index} => orderID ={0}")
    @ValueSource(strings = {"13a46c15-c294-456a-940c-334d18fd5b93"})
    public void testPayment(String orderID) throws SQLException {
        Order order=service.getOrderByID(orderID);
        if (order.getPayment()==0)
            order.setPayment(1);

        order=service.getOrderByID(orderID);
        Assertions.assertEquals(order.getPayment(), 1);
    }
}
