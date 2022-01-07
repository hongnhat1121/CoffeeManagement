/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.login;

import com.nthn.check.LoginChecker;
import com.nthn.pojo.Account;
import com.nthn.services.AccountService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author HONGNHAT
 */
public class UsernameTester {

    //Test username rỗng
    @ParameterizedTest
    @ValueSource(strings = {"", "\t", "\n"})
    public void testEmpty(String input) {
        Assertions.assertEquals(input.trim().isEmpty(), true);
    }

    //Test username khác rỗng
    @ParameterizedTest
    @ValueSource(strings = {"coffee", "management", "app "})
    public void testNotEmpty(String input) {
        Assertions.assertEquals(input.trim().isEmpty(), false);
    }

    //Test username không chứa khoảng trắng
    @ParameterizedTest
    @ValueSource(strings = {"coffee", "management"})
    public void testNotSpace(String input) {
        Assertions.assertEquals(input.contains(" "), false);
    }

    //Test username chứa khoảng trắng
    @ParameterizedTest
    @ValueSource(strings = {"coff   ee ", "manag ement "})
    public void testSpace(String input) {
        Assertions.assertEquals(input.contains(" "), true);
    }

    //Test username có chứa ký tự đặc biệt không, không có => false
    //Ký tự đặc biệt !@#$%&*()+=|<>?{}\\[\\]~-
    //regex \W : bất kỳ ký tự nào không phải chữ cái và chữ số
    @ParameterizedTest(name = "{index} => input={0}, expected={1}")
    @CsvSource({"coffee!, true", "coffee@, true", "coffee2022, false", "coffee_2020, false"})
    public void testSpecialLetter(String input, String expected) {
        Assertions.assertEquals(input.matches(".*\\W.*"), Boolean.valueOf(expected));
    }

    //Test độ dài username không vượt quá 20 ký tự
    @ParameterizedTest(name = "{index} => input={0}, expected={1}")
    @CsvSource({"username, true", "password, true", "coffeemanagementapp_2022, false"})
    public void testLength(String input, String expected) {
        Assertions.assertEquals(input.length() <= 20, Boolean.valueOf(expected));
    }

    //Test username đã tồn tại
    @ParameterizedTest
    @ValueSource(strings = {"user", "admin", "User1"})
    public void testExist(String input) throws SQLException {
        Assertions.assertTrue(LoginChecker.isExistAccount(input));
    }

    //Test username không tồn tại
    @ParameterizedTest
    @ValueSource(strings = {"username"})
    public void testNotExist(String input) {
        Account account = null;
        AccountService accountService = new AccountService();
        try {
            account = accountService.getAccountByUsername(input);
        } catch (SQLException ex) {
            Logger.getLogger(UsernameTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assertions.assertTrue(account == null);
    }

    //Test username hợp lệ
    @ParameterizedTest
    @ValueSource(strings = {"coffee", "coffee_2022"})
    public void testValid(String input) {
        Assertions.assertEquals(LoginChecker.isValidUsername(input), true);
    }

    //Test username không hợp lệ
    @ParameterizedTest
    @ValueSource(strings = {"coff ee", "coffeemanagementapp_2022", "coffee*2022", "coffee  2022"})
    public void testInvalid(String input) {
        Assertions.assertEquals(LoginChecker.isValidUsername(input), false);
    }
}
