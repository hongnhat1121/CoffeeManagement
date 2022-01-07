/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.testcontroller;

import com.nthn.check.LoginChecker;
import com.nthn.coffeemanagementapp.LoginController;
import com.nthn.login.PasswordTester;
import com.nthn.login.UsernameTester;
import com.nthn.pojo.Account;
import com.nthn.services.AccountService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author HONGNHAT
 */
public class LoginTester {

    private static final PasswordTester PASSWORD_TESTER = new PasswordTester();
    private static final UsernameTester USERNAME_TESTER = new UsernameTester();

    @Test
    public void testGetAccountByInvalidID() {
        try {
            AccountService accountService = new AccountService();
            Account a = accountService.getAccountByID("1");

            Assertions.assertNull(a);
        } catch (SQLException ex) {
            Logger.getLogger(LoginTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testGetAccountByValidID() {
        try {
            AccountService accountService = new AccountService();
            Account a = accountService.getAccountByID("c6672ebe-6b57-42d4-8c8d-a4a2f3a19c11");

            Assertions.assertEquals("admin", a.getUsername());
            Assertions.assertEquals("c6672ebe-6b57-42d4-8c8d-a4a2f3a19c11", a.getAccountID());
        } catch (SQLException ex) {
            Logger.getLogger(LoginTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Test đăng nhập thành công
    @ParameterizedTest(name = "{index} => username={0}, password={1}")
    @CsvSource({"admin, 1121", "user, 1121"})
    public void testLoginValid(String username, String password) throws SQLException {
        Assertions.assertEquals(LoginChecker.isSuccessLogin(username, password), true);
    }

    //Test đăng nhập không thành công
    @ParameterizedTest(name = "{index} => username={0}, password={1}")
    @CsvSource({"usernam=e, ?username2022", "username, username2022", "UserName, USERNAME2022", "username, username"})
    public void testLoginInvalid(String username, String password) throws SQLException {
        Assertions.assertEquals(LoginChecker.isSuccessLogin(username, password), false);
    }

}
