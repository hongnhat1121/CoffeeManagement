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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HONGNHAT
 */
public class AccountService {

    /**
     *
     * @return @throws SQLException
     */
    public static List<Account> getAccounts() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        try (Connection c = JdbcUtils.getConnection()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM accounts");
            while (rs.next()) {
                Account a = new Account(rs.getString("AccountID"),
                        rs.getString("Username"), rs.getString("Password"),
                        Active.valueOf(rs.getString("Active")),
                        Role.valueOf(rs.getString("Role")));
                accounts.add(a);
            }
        }
        return accounts;
    }

    /**
     *
     * @param account
     */
    public static void addAccount(Account account) {
        try (Connection connection = JdbcUtils.getConnection()) {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(""
                    + "INSERT INTO accounts(AccountID, Username, Password, Active, Role) "
                    + "VALUES(?,?,?,?,?)");

            preparedStatement.setString(1, account.getAccountID());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.setString(3, account.getPassword());
            preparedStatement.setString(4, account.getActive().name());
            preparedStatement.setString(5, account.getRole().name());

            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public static Account getAccount(int id) throws SQLException {
        try (Connection c = JdbcUtils.getConnection()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM accounts WHERE AccountID=" + id);
            while (rs.next()) {
                return new Account(rs.getString("AccountID"),
                        rs.getString("Username"), rs.getString("Password"),
                        Active.valueOf(rs.getString("Active")),
                        Role.valueOf(rs.getString("Role")));
            }
        }
        return null;
    }
}
