/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.services;

import com.nthn.configs.JdbcUtils;
import com.nthn.pojo.Account;
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

    public List<Account> getAccounts() throws SQLException, ClassNotFoundException {
        List<Account> accounts = new ArrayList<>();
        try (Connection c = JdbcUtils.getConnection()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM accounts");
            while (rs.next()) {
                Account a = new Account(rs.getString("Username"), rs.getString("Password"), rs.getInt("ActiveID"), rs.getInt("RoleID"));
                accounts.add(a);
            }
        }
        return accounts;
    }

    public void addAccount(Account account) {
        try (Connection connection = JdbcUtils.getConnection()) {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(""
                    + "INSERT INTO accounts(AccountID, Username, Password, ActiveID, RoleID) "
                    + "VALUES(?,?,?,?,?)");
            preparedStatement.setInt(1, Account.getAccountID());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.setString(3, account.getPassword());
            preparedStatement.setInt(4, account.getActiveID());
            preparedStatement.setInt(5, account.getRoleID());

            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Account getAccount(int id) throws SQLException {
        try (Connection c = JdbcUtils.getConnection()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM accounts WHERE AccountID=" + id);
            while (rs.next()) {
                return new Account(rs.getString("Username"), rs.getString("Password"), rs.getInt("ActiveID"), rs.getInt("RoleID"));
            }
        }
        return null;
    }
}
