/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.testcontroller;

import com.nthn.check.StringChecker;
import com.nthn.configs.Utils;
import com.nthn.pojo.Employee;
import com.nthn.pojo.Gender;
import com.nthn.services.EmployeeService;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
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
        } catch (SQLException ex) {
            Logger.getLogger(RegisterTester.class.getName()).log(Level.SEVERE, null, ex);
        }

        Assertions.assertEquals(employee.getFullName(), employee1.getFullName());
        Assertions.assertEquals(employee.getAccountID(), employee1.getAccountID());
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void testFullnameEmpty(String input) {
        Assertions.assertTrue(input.isEmpty());
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void testPhoneNotEmpty(String input) {
        Assertions.assertFalse(input.isEmpty());
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void testIdentityCardNotEmpty(String input) {
        Assertions.assertTrue(!input.trim().isEmpty());
    }

    //Ngày sinh >= ngày hiện tại - 18 năm
    @ParameterizedTest
    @ValueSource(strings = {"10/10/2001", "01/07/2004"})
    public void testBirthDateValid(String input) {
        LocalDate localDate = LocalDate.now();
        localDate.minusYears(18);
        LocalDate date = Utils.converter.fromString(input);
        Assertions.assertTrue(date.compareTo(localDate) < 0);
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
        Assertions.assertTrue(StringChecker.isAlnum(input, 9)
                && StringChecker.isAlnum(input, 12));
    }
}
