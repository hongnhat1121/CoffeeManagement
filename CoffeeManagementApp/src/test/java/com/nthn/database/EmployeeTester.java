/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.database;

import com.nthn.pojo.Account;
import com.nthn.pojo.Employee;
import com.nthn.register.RegisterTester;
import com.nthn.services.AccountService;
import com.nthn.services.EmployeeService;
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
public class EmployeeTester {

    @ParameterizedTest(name = "{index} => id={0}, fullName={1}")
    @CsvSource({"0fbbabeb-331a-4a03-8536-c3a9fbcd3381, Hoàng Vũ Thanh",
        "23c74463-75ea-4835-b0b7-9e6545bac000, Nguyễn Thị Hồng Nhật",
        "54cf6d95-fdff-4477-8237-805d07e90217, La Trung Hiếu",
        "98885571-a5a3-4390-84c7-6be660f84f5f, Nguyễn Thanh Định"})
    public void testGetEmployeeByValidID(String id, String fullName) {
        try {
            EmployeeService service = new EmployeeService();
            Employee employee = service.getEmployeeByID(id);

            Assertions.assertEquals(id, employee.getEmployeeID());
            Assertions.assertEquals(fullName, employee.getFullName());
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"2022", "22929943hfjh3ru3", " "})
    public void testGetEmployeeByInvalidID(String input) {
        try {
            EmployeeService service = new EmployeeService();
            Employee employee = service.getEmployeeByID(input);

            Assertions.assertNull(employee);
        } catch (SQLException ex) {
            Logger.getLogger(RegisterTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
