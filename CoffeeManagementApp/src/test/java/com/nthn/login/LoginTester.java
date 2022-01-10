/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.login;

import com.nthn.check.LoginChecker;
import java.sql.SQLException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author HONGNHAT
 */
public class LoginTester {

    private final LoginChecker checker = new LoginChecker();

    

    //Test đăng nhập thành công
    @ParameterizedTest(name = "{index} => username={0}, password={1}")
    @CsvSource({"user1, User2022"})
    public void testLoginSuccess(String username, String password) throws SQLException {
        Assertions.assertTrue(checker.isSuccessLogin(username, password));
    }

    //Test đăng nhập không thành công
    @ParameterizedTest(name = "{index} => username={0}, password={1}")
    @CsvSource({"usernam=e, ?username2022", "username, username2022", "UserName, USERNAME2022", "username, username"})
    public void testLoginFailed(String username, String password) throws SQLException {
        Assertions.assertFalse(checker.isSuccessLogin(username, password));
    }

}
