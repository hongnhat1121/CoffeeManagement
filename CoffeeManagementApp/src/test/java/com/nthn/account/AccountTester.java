/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.account;

import com.nthn.configs.JdbcUtils;
import com.nthn.pojo.Account;
import com.nthn.services.AccountService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author HONGNHAT
 */
public class AccountTester {

    private static Connection c;
    private static AccountService as = new AccountService();

    @BeforeAll
    public static void beforeAll() throws SQLException {
        c = JdbcUtils.getConnection();
    }

    @AfterAll
    public static void afterAll() throws SQLException {
        if (c != null) {
            c.close();
        }
    }

    @Test
    public void testUsername() throws SQLException {
        Statement s = c.createStatement();

        ResultSet rs = s.executeQuery("SELECT * FROM accounts");
        List<String> result = new ArrayList<>();
        while (rs.next()) {
            String username = rs.getString("username");
            result.add(username);
        }

        Set<String> result2 = new HashSet<>(result);
        Assertions.assertEquals(result.size(), result2.size());
    }

    @Test
    public void testGetAccountByInvalidID() {
        try {
            Account a = as.getAccount(2);
            Assertions.assertNull(a);
        } catch (SQLException ex) {
            Logger.getLogger(AccountTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @Test
    public void testGetAccountByValidID() {
        try {
            Account a = as.getAccount(1);
            Assertions.assertEquals("hongnhat1121", a.getUsername());
            Assertions.assertEquals("efc0e953-52c7-453a-bf99-a5f120d973cf", a.getAccountID());
        } catch (SQLException ex) {
            Logger.getLogger(AccountTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
