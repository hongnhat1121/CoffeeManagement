/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.search;

import com.nthn.pojo.Status;
import com.nthn.pojo.Table;
import com.nthn.services.TableService;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author HONGNHAT
 */
public class SearchTableTester {

    private TableService service = new TableService();

    @ParameterizedTest
    @ValueSource(ints = {10, 1, 2, 3})
    public void testSearchTableByCapacityValid(int input) {
        Assertions.assertTrue(input > 0);
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, 0, -1, -2})
    public void testSearchTableByCapacityInvalid(int input) {
        Assertions.assertTrue(input <= 0);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Còn trống", "Đã đặt"})
    public void testSearchTableByStatusValid(String input) {
        List<Table> tables = service.getTables(input);
        Assertions.assertNotNull(Status.getByContent(input));
        Assertions.assertNotNull(tables);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Không", "Đặt rồi"})
    public void testSearchTableByStatusInvalid(String input) {
        Assertions.assertNull(Status.getByContent(input));
    }
}
