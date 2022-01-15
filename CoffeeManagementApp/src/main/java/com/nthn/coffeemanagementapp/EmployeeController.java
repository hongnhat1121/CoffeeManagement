/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.coffeemanagementapp;

import com.nthn.configs.Utils;
import com.nthn.pojo.Employee;
import com.nthn.pojo.Gender;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.nthn.services.EmployeeService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @author HONGNHAT
 */
public class EmployeeController {

    private EmployeeService service=new EmployeeService();


    public void initTableViewEmployee() {

    }

    public void loadTableViewEmployee(TableView tbvEmployee) {

        TableColumn<Employee, String> colFullName = new TableColumn<>("Họ tên");
        TableColumn<Employee, Gender> colGender = new TableColumn<>("Giới tính");
        TableColumn<Employee, String> colBirthDate = new TableColumn<>("Ngày sinh");
        TableColumn<Employee, String> colIdentityCard = new TableColumn<>("CMND/CCCD");
        TableColumn<Employee, String> colPhoneNumber = new TableColumn<>("Số điện thoại");
        TableColumn<Employee, String> colAddress = new TableColumn<>("Địa chỉ");
        TableColumn<Employee, String> colHireDate = new TableColumn<>("Ngày vào làm");
        TableColumn<Employee, String> colAccount = new TableColumn<>("Tài khoản");

        colFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colBirthDate.setCellValueFactory(param -> {
            return new SimpleObjectProperty<>(Utils.converter.toString(param.getValue().getBirthDate()));
        });
        colIdentityCard.setCellValueFactory(new PropertyValueFactory<>("identityCard"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colHireDate.setCellValueFactory(param -> {
            return new SimpleObjectProperty<>(Utils.converter.toString(param.getValue().getHireDate()));
        });
        colAccount.setCellValueFactory(new PropertyValueFactory<>("account"));

        tbvEmployee.getColumns().addAll(colFullName, colGender, colBirthDate, colIdentityCard, colPhoneNumber, colAddress,
                colHireDate, colAccount);

        tbvEmployee.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void loadTableDataEmployee(TableView tbvEmployee, String username) throws SQLException {
        tbvEmployee.setItems(FXCollections.observableList(service.getEmployees(username)));
    }

    public void loadTableDataEmployee(TableView tbvEmployee) throws SQLException {
        tbvEmployee.setItems(FXCollections.observableList(service.getEmployees()));
    }
}
