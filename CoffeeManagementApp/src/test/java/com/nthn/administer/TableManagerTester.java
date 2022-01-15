/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.administer;

import com.nthn.pojo.Status;
import com.nthn.pojo.Table;
import com.nthn.services.TableService;
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
public class TableManagerTester {

    private TableService service = new TableService();

    @ParameterizedTest
    @ValueSource(strings = {"c9491382-63e5-4920-ab42-c04fed63bc45"})
    public void testDeleteTableSuccess(String input) {
        try {
            service.deleteTable(input);
            Table table = service.getTable(input);
            Assertions.assertNull(table);
        } catch (SQLException ex) {
            Logger.getLogger(TableManagerTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   

    @ParameterizedTest(name = "{index} => id={0}, name={1}, capacity={2}, status={3}")
    @CsvSource({"c9491382-ree5-4920-ab42-c04fed63bc45, Bàn 023, 5, Còn trống"})
    public void testAddTableSuccess(String id, String name, String capacity, String status) {
        Table table = new Table(id, name, Integer.parseInt(capacity), Status.getByContent(status));
        service.addTable(table);

        table = service.getTable(id);
        Assertions.assertNotNull(table);
    }
    
    @ParameterizedTest(name = "{index} => id={0}, name={1}, capacity={2}, status={3}")
    @CsvSource({"c9891382-ree5-4920-ab42-c04fed63bc45, Bàn 073, 5, Còn trống"})
    public void testAddTableFailed(String id, String name, String capacity, String status) {
        Table table = new Table(id, name, Integer.parseInt(capacity), Status.getByContent(status));
        table = service.getTable(id);
        Assertions.assertNull(table);
    }

    @ParameterizedTest(name = "{index} => id={0}, name={1}, capacity={2}, status={3}")
    @CsvSource({"c9491382-63e5-4920-ab42-c04fed63bc45, , 5, Đã đặt"})
    public void testUpdateTableSuccess(String id, String name, String capacity, String status) {
        Table table1=service.getTable(id); //table trước khi update

        Table table = new Table(id, name, Integer.parseInt(capacity),  Status.getByContent(status));
        service.updateTable(table);

        table = service.getTable(id); //table đã update

        Assertions.assertNotEquals(table, table1);
    }
    
    @ParameterizedTest(name = "{index} => id={0}, name={1}, capacity={2}, status={3}")
    @CsvSource({"c9491382-63e5-4920-ab42-c04fed63bc45, Bàn 023, 5, đã đặt"})
    public void testUpdateTableFailed(String id, String name, String capacity, String status) {
        Table table1=service.getTable(id); //table trước khi update

        Table table = new Table(id, name, Integer.parseInt(capacity), Status.getByContent(status));
        service.updateTable(table);

        table = service.getTable(id); //table đã update
        Assertions.assertEquals(table, table1);
    }
}
