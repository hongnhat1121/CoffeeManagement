/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.services;

import com.nthn.configs.JdbcUtils;
import com.nthn.pojo.Account;
import com.nthn.pojo.Active;
import com.nthn.pojo.Role;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HONGNHAT
 */
public class AccountService {

    public List<Account> getAccounts() throws SQLException, ClassNotFoundException {
        List<Account> accounts = new ArrayList<>();
        try (Connection c = JdbcUtils.getConnection()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM accounts");
            while (rs.next()) {
               // Account a = new Account(rs.getString("username"), rs.getString("password"), rs.getObject("activeID", Class.forName("Active")), new Role(rs.getInt("roleID"));
            }
        }
        return accounts;
    }
}
