/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.search;

import com.nthn.check.StringChecker;
import com.nthn.pojo.Status;
import com.nthn.pojo.Table;
import com.nthn.services.TableService;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author HONGNHAT
 */
public class SearchTableTester {

    private TableService service = new TableService();
    private StringChecker checker;

    @ParameterizedTest
    @ValueSource(strings = {"10", "1", "2", "3"})
    public void testSearchTableByCapacityValid(String input) throws SQLException {
        Assertions.assertTrue(StringChecker.isAlnum(input, input.length()) && !input.equals("0"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-10", "0", "-1", "-2"})
    public void testSearchTableByCapacityInvalid(String input) throws SQLException {
        Assertions.assertTrue(!StringChecker.isAlnum(input, input.length())|| input.equals("0"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Còn trống", "Đã đặt"})
    public void testSearchTableByStatusValid(String input) throws SQLException {
        Assertions.assertNotNull(Status.getByContent(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Không", "Đặt rồi"})
    public void testSearchTableByStatusInvalid(String input) throws SQLException {
        Assertions.assertTrue(Status.getByContent(input) == null);
    }
}
