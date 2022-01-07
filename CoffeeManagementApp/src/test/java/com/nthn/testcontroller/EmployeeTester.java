/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.testcontroller;

import com.nthn.check.StringChecker;
import com.nthn.pojo.Employee;
import com.nthn.pojo.Gender;
import com.nthn.services.EmployeeService;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author HONGNHAT
 */
public class EmployeeTester {

    private static EmployeeService service;
    private static Employee employee;

    @BeforeAll
    public static void init() {
        service = new EmployeeService();
        employee = new Employee("23c74463-75ea-4835-b0b7-9e6545bac000", LocalDate.now(),
                "Nguyễn Thị Hồng Nhật", LocalDate.of(2001, Month.OCTOBER, 10),
                Gender.FEMALE, "342010930", "Đồng Tháp", "0836479646",
                "563d4212-1042-4c6b-9979-9b171b15d437");
    }

    @Test
    public void testGetEmployeeByValidID() {
        try {
            Employee e = service.getEmployeeByID("23c74463-75ea-4835-b0b7-9e6545bac000");

            Assertions.assertEquals(e.getFullName(), employee.getFullName());
            Assertions.assertEquals(e.getAccountID(), employee.getAccountID());
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testFullnameNotEmpty() {
        Assertions.assertFalse(employee.getFullName().isEmpty());
    }

    @Test
    public void testPhoneNotEmpty() {
        Assertions.assertFalse(employee.getPhone().isEmpty());
    }

    @Test
    public void testIdentityCardNotEmpty() {
        Assertions.assertFalse(employee.getIdentityCard().isEmpty());
    }

    //Ngày sinh >= ngày hiện tại - 18 năm
    @Test
    public void testBirthDateValid() {
        LocalDate localDate = LocalDate.now();
        localDate.minusYears(18);

        Assertions.assertTrue(employee.getBirthDate().compareTo(localDate) < 0);
    }

    //Số điện thoại phải đủ 10 ký tự
    @Test
    public void testPhoneLength() {
        int length = employee.getPhone().length();
        Assertions.assertTrue(length == 10);
    }

    //Số điện thoại phải đủ 10 chữ số
    @Test
    public void testPhoneNumber() {
        Assertions.assertTrue(employee.getPhone().matches("\\d{10}"));
    }

    //CMND nhập từ 9-12 chữ số
    @Test
    public void testIdentityCard() {
        Assertions.assertTrue(employee.getIdentityCard().matches("\\d{9}")
                || employee.getIdentityCard().matches("\\d{12}"));
    }
}
