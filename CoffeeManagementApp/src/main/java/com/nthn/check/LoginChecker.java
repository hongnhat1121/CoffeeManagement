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

    public static boolean isExistAccount(String username) {
        Account account = new Account();
        AccountService accountService = new AccountService();
        try {
            account = accountService.getAccountByUsername(username);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account != null;
    }

    public static boolean isValidUsername(String username) {
        return !username.isEmpty() && !username.contains(" ") && username.length() <= 20
                && !username.matches(".*\\W.*") && isExistAccount(username);
    }

    public static boolean isValidPassword(String password, String username) {
        return !password.trim().isEmpty() && password.length() >= 6
                && password.matches(".*[a-z].*") && password.matches(".*[A-z].*")
                && password.matches(".*[0-9].*") && !password.matches(".*\\W.*")
                && !password.equalsIgnoreCase(username);
    }

    public static boolean isSuccessLogin(String username, String password) {
        AccountService accountService = new AccountService();
        Account account = null;
        try {
            account = accountService.getAccountByUsername(username);
        } catch (SQLException ex) {
            Logger.getLogger(LoginChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (account != null) {
            return account.getPassword().equals(DigestUtils.sha256Hex(password));
        }
        return false;
    }

    public static boolean isValidLogin(String username, String password) {
        return isValidUsername(username) && isValidPassword(password, username);
    }
}
