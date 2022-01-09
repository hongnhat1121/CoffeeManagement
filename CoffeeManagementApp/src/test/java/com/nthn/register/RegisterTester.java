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
    @ValueSource(strings = {"23c74463-75ea-4835-b0b7-9e6545bac000"})
    public void testGetEmployeeByValidID(String input) {
        EmployeeService service = new EmployeeService();
        Employee employee = new Employee("23c74463-75ea-4835-b0b7-9e6545bac000",
                LocalDate.now(), "Nguyễn Thị Hồng Nhật", LocalDate.of(2001, Month.OCTOBER, 10),
                Gender.FEMALE, "342010930", "Đồng Tháp", "0836479646",
                "563d4212-1042-4c6b-9979-9b171b15d437");
        Employee employee1 = null;

        try {
            employee1 = service.getEmployeeByID(input);
            Assertions.assertEquals(employee.getFullName(), employee1.getFullName());
            Assertions.assertEquals(employee.getAccountID(), employee1.getAccountID());
        } catch (SQLException ex) {
            Logger.getLogger(RegisterTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"2022", "22929943hfjh3ru3"})
    public void testGetEmployeeByInvalidID(String input) {
        try {
            EmployeeService service = new EmployeeService();
            Employee employee = null;
            employee = service.getEmployeeByID(input);

            Assertions.assertNull(employee);
        } catch (SQLException ex) {
            Logger.getLogger(RegisterTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    
//    @ParameterizedTest
//    public void testGetRegister

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
        Assertions.assertTrue(new RegisterChecker().isValidUsername(input));
    }

    @ParameterizedTest(name = "{index} => input={0}")
    @ValueSource(strings = {"user2022", "user1"})
    public void testUsernameInvalid(String input) {
        Assertions.assertFalse(new RegisterChecker().isValidUsername(input));
    }
//
    private Account account = new Account(Utils.randomID(), "username", "password", Active.AVAILABLE, Role.USER);
    private Employee employee = new Employee(Utils.randomID(), LocalDate.MIN, "", LocalDate.MIN, Gender.OTHER, "identityCard", "identityCard", "identityCard", account.getAccountID());

    @ParameterizedTest
    public void testRegisterSuccess(Employee employee, Account account) throws SQLException {
        Assertions.assertTrue(new RegisterChecker().isSuccessRegister(employee, account));
    }
}
