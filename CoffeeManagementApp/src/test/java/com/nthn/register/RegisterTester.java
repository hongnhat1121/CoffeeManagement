/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.register;

import com.nthn.check.RegisterChecker;
import com.nthn.configs.Utils;
import com.nthn.pojo.Account;
import com.nthn.pojo.Active;
import com.nthn.pojo.Employee;
import com.nthn.pojo.Gender;
import com.nthn.pojo.Role;
import com.nthn.services.EmployeeService;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author HONGNHAT
 */
public class RegisterTester {

    @ParameterizedTest
    @ValueSource(strings = {"", "\t", "\n"})
    public void testFullnameEmpty(String input) {
        Assertions.assertTrue(input.trim().isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Nguyễn Thị Hồng Nhật"})
    public void testFullnameValid(String input) {
        Assertions.assertTrue(new RegisterChecker().isValidFullname(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0836479646"})
    public void testPhoneNotEmpty(String input) {
        Assertions.assertFalse(input.trim().isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"342010930"})
    public void testIdentityCardNotEmpty(String input) {
        Assertions.assertFalse(input.trim().isEmpty());
    }

    //Ngày sinh <= ngày hiện tại - 18 năm
    @ParameterizedTest
    @ValueSource(strings = {"10/10/2001", "01/07/2004"})
    public void testBirthDateValid(String input) {
        LocalDate date = Utils.converter.fromString(input);
        Assertions.assertTrue(new RegisterChecker().isValidBirthDate(date));
    }

    //Số điện thoại phải đủ 10 ký tự chữ số
    @ParameterizedTest
    @ValueSource(strings = {"0836479646", "0855176256"})
    public void testPhoneNumber(String input) {
        Assertions.assertTrue(input.matches("\\d{10}"));
    }

    //CMND/CCCD nhập 9  hoặc 12 chữ số
    @ParameterizedTest
    @ValueSource(strings = {"342010930", "760119117647"})
    public void testIdentityCard(String input) {
        Assertions.assertTrue(new RegisterChecker().isValidIdentityCard(input));
    }

    @ParameterizedTest(name = "{index} => input={0}")
    @ValueSource(strings = {"account", "username1"})
    public void testUsernameValid(String input) {
        try {
            Assertions.assertTrue(new RegisterChecker().isValidUsername(input));
        } catch (SQLException ex) {
            Logger.getLogger(RegisterTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @ParameterizedTest(name = "{index} => input={0}")
    @ValueSource(strings = {"user2022", "user1"})
    public void testUsernameInvalid(String input) {
        try {
            Assertions.assertFalse(new RegisterChecker().isValidUsername(input));
        } catch (SQLException ex) {
            Logger.getLogger(RegisterTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//
    private Account account = new Account(Utils.randomID(), "username", "password", Active.AVAILABLE, Role.USER);
    private Employee employee = new Employee(Utils.randomID(), LocalDate.MIN, "", LocalDate.MIN, Gender.OTHER, "identityCard", "identityCard", "identityCard", account.getAccountID());

    @ParameterizedTest
    public void testRegisterSuccess(Employee employee, Account account) throws SQLException {
        Assertions.assertTrue(new RegisterChecker().isSuccessRegister(employee, account));
    }
}
