/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.coffeemanagementapp;

import com.nthn.pojo.Employee;
import com.nthn.pojo.Gender;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

/**
 *
 * @author HONGNHAT
 */
public class EmployeeController {

    private List<Employee> employees;
    @FXML
    private TableColumn<Employee, String> employeeNameCol;
    @FXML
    private TableColumn<Employee, Gender> genderCol;
    @FXML
    private TableColumn<Employee, LocalDate> birthDate;
    @FXML

    public void btnAddEmployeeHandler(ActionEvent event) throws IOException {
        App app = new App();

        
        app.loaderController("Register.fxml", "Coffee Management App - Register");
    }

    public void initTableViewEmployee() {

    }

    public void loadTableView(ObservableList<Employee> list) {

    }

}
