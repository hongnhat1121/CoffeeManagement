/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.check;

import com.nthn.coffeemanagementapp.LoginController;
import com.nthn.pojo.Account;
import com.nthn.services.AccountService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author HONGNHAT
 */
public class LoginChecker {

    public boolean isExistAccount(String username) {
        Account account = null;
        AccountService accountService = new AccountService();
        try {
            account = accountService.getAccountByUsername(username);
        } catch (SQLException ex) {
            Logger.getLogger(LoginChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account != null;
    }

    public boolean isValidUsername(String username) throws SQLException {
        return !username.matches(".*\\W.*") && username.length() <= 20
                && !username.isEmpty() && !username.contains(" ")
                && isExistAccount(username);
    }

    public boolean isValidPassword(String password, String username) {
        return !password.trim().isEmpty() && password.length() >= 6
                && password.matches(".*[a-z].*") && password.matches(".*[A-Z].*")
                && password.matches(".*[0-9].*") && !password.matches(".*\\W.*")
                && !password.equalsIgnoreCase(username);
    }

    public boolean isSuccessLogin(String username, String password) {
        AccountService accountService = new AccountService();
        Account account = null;
        try {
            account = accountService.getAccountByUsername(username);
        } catch (SQLException ex) {
            Logger.getLogger(LoginChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (account != null) {
            return account.getPassword().equals(DigestUtils.sha256Hex(password));
        } else {
            return false;
        }
    }
}
