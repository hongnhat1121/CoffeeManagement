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

    private final LoginChecker checker = new LoginChecker();

    //Test username rỗng
    @ParameterizedTest
    @ValueSource(strings = {"", "\t", "\n"})
    public void testUsernameEmpty(String input) {
        Assertions.assertEquals(input.trim().isEmpty(), true);
    }

    //Test username khác rỗng
    @ParameterizedTest
    @ValueSource(strings = {"coffee", "management", "app "})
    public void testUsernameNotEmpty(String input) {
        Assertions.assertEquals(input.trim().isEmpty(), false);
    }

    //Test username không chứa khoảng trắng
    @ParameterizedTest
    @ValueSource(strings = {"coffee", "management"})
    public void testUsernameNotSpace(String input) {
        Assertions.assertEquals(input.contains(" "), false);
    }

    //Test username chứa khoảng trắng
    @ParameterizedTest
    @ValueSource(strings = {"coff   ee ", "manag ement "})
    public void testUsernameSpace(String input) {
        Assertions.assertEquals(input.contains(" "), true);
    }

    //Test username có chứa ký tự đặc biệt không, không có => false
    //Ký tự đặc biệt !@#$%&*()+=|<>?{}\\[\\]~-
    //regex \W : bất kỳ ký tự nào không phải chữ cái và chữ số
    @ParameterizedTest(name = "{index} => input={0}, expected={1}")
    @CsvSource({"coffee!, true", "coffee@, true", "coffee2022, false", "coffee_2020, false"})
    public void testUsernameSpecialLetter(String input, String expected) {
        Assertions.assertEquals(input.matches(".*\\W.*"), Boolean.valueOf(expected));
    }

    //Test độ dài username không vượt quá 20 ký tự
    @ParameterizedTest(name = "{index} => input={0}, expected={1}")
    @CsvSource({"username, true", "password, true", "coffeemanagementapp_2022, false"})
    public void testUsernameLength(String input, String expected) {
        Assertions.assertEquals(input.length() <= 20, Boolean.valueOf(expected));
    }

    //Test username đã tồn tại
    @ParameterizedTest
    @ValueSource(strings = {"user1"})
    public void testUsernameExist(String input) throws SQLException {
        Assertions.assertTrue(checker.isExistAccount(input));
    }

    //Test username không tồn tại
    @ParameterizedTest
    @ValueSource(strings = {"username"})
    public void testUsernameNotExist(String input) {
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
    @ParameterizedTest(name = "{index} => input={0}")
    @ValueSource(strings = {"user1"})
    public void testUsernameValid(String input) throws SQLException {
        Assertions.assertEquals(checker.isValidUsername(input), true);
    }

    //Test username không hợp lệ
    @ParameterizedTest
    @ValueSource(strings = {"coff ee", "coffeemanagementapp_2022", "coffee*2022", "coffee  2022"})
    public void testUsernameInvalid(String input) throws SQLException {
       Assertions.assertEquals(checker.isValidUsername(input), false);
    }

}
