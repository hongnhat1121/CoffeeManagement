/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.database;

import com.nthn.login.LoginTester;
import com.nthn.pojo.Account;
import com.nthn.services.AccountService;
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
public class AccounTester {

    @ParameterizedTest
    @ValueSource(strings = {"1", "23438ihfskjfh74", ""})
    public void testGetAccountByInvalidID(String input) {
        try {
            AccountService accountService = new AccountService();
            Account a = accountService.getAccountByID(input);

            Assertions.assertNull(a);
        } catch (SQLException ex) {
            Logger.getLogger(LoginTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @ParameterizedTest(name = "{index} => id={0}, username={1}")
    @CsvSource({"0d95bc5c-4b83-4b16-a53e-e21e2bcb0bea, admin27",
        "d19b5955-7df8-4e23-a669-ab5a3e6ae241, admin35",
        "cc77cadd-96ce-4a61-beee-831474b4cd84, admin53"})
    public void testGetAccountByValidID(String id, String username) {
        try {
            AccountService accountService = new AccountService();
            Account account = accountService.getAccountByID(id);

            Assertions.assertEquals(id, account.getAccountID());
            Assertions.assertEquals(username, account.getUsername());
        } catch (SQLException ex) {
            Logger.getLogger(LoginTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testAccountUnique() {
        try {
            AccountService accountService = new AccountService();
            List<Account> accounts = accountService.getAccounts();

            Set<Account> accounts1 = new HashSet<>(accounts);

            Assertions.assertEquals(accounts.size(), accounts1.size());
        } catch (SQLException ex) {
            Logger.getLogger(AccounTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
