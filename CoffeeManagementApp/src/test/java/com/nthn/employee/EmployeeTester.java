/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.employee;

import com.nthn.configs.Utils;
import com.nthn.pojo.Employee;
import com.nthn.pojo.Gender;
import com.nthn.services.EmployeeService;
import java.sql.Date;
import java.sql.SQLException;
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
    public void init() {
        service = new EmployeeService();
        employee = new Employee(Utils.randomID(), Date.valueOf("3/1/2022"),
                "Nguyễn Thị Hồng Nhật", Date.valueOf("10/10/2001"),
                Gender.valueOf("Nữ"), "342010930", "Đồng Tháp", "0836479646", null);
    }

    @Test
    public void testGetEmployeeByValidID() {
        try {
            employee = service.getEmployeeByID("1");

            Assertions.assertEquals(employee.getFullName(), "");
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
