/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.login;

import com.nthn.check.LoginChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author HONGNHAT
 */
public class PasswordTester {

    //Test password rỗng
    @ParameterizedTest
    @ValueSource(strings = {"", "\t", "\n"})
    public void testEmpty(String input) {
        Assertions.assertEquals(input.trim().isEmpty(), true);
    }

    //Test password khác rỗng
    @ParameterizedTest
    @ValueSource(strings = {"coffee", "management", "app "})
    public void testNotEmpty(String input) {
        Assertions.assertEquals(input.trim().isEmpty(), false);
    }

    //Test password không ký tự đặc biệt
    //Ký tự đặc biệt !@#$%&*()+=|<>?{}\\[\\]~-
    //regex \W : bất kỳ ký tự nào không phải chữ cái và chữ số
    @ParameterizedTest(name = "{index} => input={0}, expected={1}")
    @CsvSource({"coffee2022, true", "coffee_2020, true", "coffee!, false", "coffee@, false"})
    public void testNonSpecialLetter(String input, String expected) {
        Assertions.assertEquals(!input.matches(".*\\W.*"), Boolean.valueOf(expected));
    }

    //Test password có chứa chữ thường
    @ParameterizedTest(name = "{index} => input={0}, expected={1}")
    @CsvSource({"coffee, true", "coffee_2022, true", "coffEE, true", "cofFee2022, true", "Coffee_2020, true", "COFFEe, true", "COFFEE, false", "COFFEE_2022, false"})
    public void testLowercase(String input, String expected) {
        Assertions.assertEquals(input.matches(".*[a-z].*"), Boolean.valueOf(expected));
    }

    //Test password có chứa chữ hoa
    @ParameterizedTest(name = "{index} => input={0}, expected={1}")
    @CsvSource({"coffee, false", "coffee_2022, false", "coffEE, true", "cofFee2022, true", "Coffee_2020, true", "COFFEe, true", "COFFEE, true", "COFFEE_2022, true"})
    public void testUppercase(String input, String expected) {
        Assertions.assertEquals(input.matches(".*[A-Z].*"), Boolean.valueOf(expected));
    }

    //Test password có chứa chữ số
    @ParameterizedTest(name = "{index} => input={0}, expected={1}")
    @CsvSource({"coffee, false", "coffee_2022, true", "coffEE, false", "cofFee2022, true", "Coffee_2020, true", "COFFEe, false", "COFFEE, false", "COFFEE_2022, true"})
    public void testNumeric(String input, String expected) {
        Assertions.assertEquals(input.matches(".*[0-9].*"), Boolean.valueOf(expected));
    }

    //Test độ dài password ít nhất 6 ký tự
    @ParameterizedTest(name = "{index} => input={0}, expected={1}")
    @CsvSource({"username, true", "password, true", "2022, false"})
    public void testLength(String input, String expected) {
        Assertions.assertEquals(input.length() >= 6, Boolean.valueOf(expected));
    }

    //Test password không trùng username => true
    @ParameterizedTest(name = "{index} => password={0}, username={1}, expected={2}")
    @CsvSource({"user, username, true", "user, user, false", "User, USER, false"})
    public void testDifferent(String password, String username, String expected) {
        Assertions.assertEquals(!password.equalsIgnoreCase(username), Boolean.valueOf(expected));
    }

    //Test password hợp lệ
    @ParameterizedTest(name = "{index} => password={0}, username={1}")
    @CsvSource({"Coffee2022, coffee", "cOffee2022, coffee", "coffEE2022, coffee",
        "cofFee2022, coffee", "Coffee_2022, coffee", "COFFEe2022, coffee",
        "CoFFEE2022, coffee", "cOFEE2022, coffee"})
    public void testValid(String password, String username) {
        testNotEmpty(password);
        testDifferent(password, username, "true");
        testLength(password, "true");
        testNumeric(password, "true");
        testLowercase(password, "true");
        testNonSpecialLetter(password, "true");
    }

    //Test password không hợp lệ
    @ParameterizedTest(name = "{index} => password={0}, username={1}")
    @CsvSource({"Coffee2022, Coffee2022", "cOffee, coffee", "coffee, coffee2022",
        "COFFEe, coffee2022", "coffee2022, coffee2022", "COFEE2022, coffee",
        "2022, coffee", "06012022, coffee"})
    public void testInvalid(String password, String username) {
        Assertions.assertEquals(LoginChecker.isValidPassword(password, username), false);
    }
}
