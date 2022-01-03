/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.account;

import com.nthn.configs.Utils;
import com.nthn.pojo.Account;
import com.nthn.pojo.Active;
import com.nthn.pojo.Role;
import com.nthn.services.AccountService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author HONGNHAT
 */
public class AccountTester {

    private static AccountService service;
    private static Account account;

    @BeforeAll
    public static void init() {
        service = new AccountService();
        account = new Account(Utils.randomID(), "user", "DHito3", Active.AVAILABLE, Role.USER);
    }

    @Test
    public void testGetAccountByInvalidID() {
        try {
            Account a = service.getAccountByID("1");

            Assertions.assertNull(a);
        } catch (SQLException ex) {
            Logger.getLogger(AccountTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testGetAccountByValidID() {
        try {
            Account a = service.getAccountByID("c6672ebe-6b57-42d4-8c8d-a4a2f3a19c11");

            Assertions.assertEquals("admin", a.getUsername());
            Assertions.assertEquals("c6672ebe-6b57-42d4-8c8d-a4a2f3a19c11", a.getAccountID());
        } catch (SQLException ex) {
            Logger.getLogger(AccountTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Tên đăng nhập là duy nhất, không trùng
    @Test
    public void testUsernameUnique() {
        try {
            List<Account> accounts = new ArrayList<>(service.getAccounts());
            Set<Account> accounts1 = new HashSet<>(accounts);

            Assertions.assertEquals(accounts.size(), accounts1.size());
        } catch (SQLException ex) {
            Logger.getLogger(AccountTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Tên đăng nhập không vượt quá 20 ký tự
    @Test
    public void testUsernameLength() {
        int length = account.getUsername().length();

        Assertions.assertTrue(length <= 20);
    }

    //Tên đăng nhập không có ký tự đặc biệt
    @Test
    public void testUsernameNonSpecialLetter() {
        Assertions.assertFalse(account.getUsername().matches(".*\\W.*"));
    }

    //Mật khẩu không trùng tên đăng nhập
    @Test
    public void testPasswordDiffUsername() {
        Assertions.assertNotEquals(account.getPassword(), account.getUsername());
    }

    //Mật khẩu ít nhất 6 ký tự
    @Test
    public void testPasswordLength() {
        int length = account.getPassword().length();

        Assertions.assertTrue(length >= 6);
    }

    //Mật khẩu có ký tự số
    @Test
    public void testPasswordNumeric() {
        Assertions.assertTrue(account.getPassword().matches(".*\\d.*"));
    }

    //Mật khẩu có chữ thường
    @Test
    public void testPasswordLowercase() {
        Assertions.assertTrue(account.getPassword().matches(".*[a-z].*"));
    }

    //Mật khẩu có chữ in hoa
    @Test
    public void testPasswordUppercase() {
        Assertions.assertTrue(account.getPassword().matches(".*[A-Z].*"));
    }

    //Mật khẩu không có ký tự đặc biệt
    //Ký tự đặc biệt [!@#$%&*()+=|<>?{}\\[\\]~-]
    @Test
    public void testPasswordNonSpecialLetter() {
        Assertions.assertFalse(account.getPassword().matches(".*\\W.*"));
    }

}
