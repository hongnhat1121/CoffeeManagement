/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.database;

import com.nthn.services.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.SQLException;

/**
 *
 * @author HONGNHAT
 */
public class OrderTester {

    private OrderService service=new OrderService();

    @ParameterizedTest(name = "{index} => orderID ={0}")
    @ValueSource(strings = {"13a46c15-c294-456a-940c-334d18fd5b93"})
    public void testPayment(String orderID) throws SQLException {
//        if (service.getOrderByID(orderID).getPayment()==0)

    }
}
