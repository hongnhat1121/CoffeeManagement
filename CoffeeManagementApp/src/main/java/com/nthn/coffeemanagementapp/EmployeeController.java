// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
package com.nthn.coffeemanagementapp;

import com.nthn.configs.Utils;
import com.nthn.pojo.Employee;
import com.nthn.pojo.Gender;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import com.nthn.services.EmployeeService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @author HONGNHAT
 */
public class EmployeeController {

    private EmployeeService service = new EmployeeService();

    private List<Employee> getEmployeeList() throws SQLException {
        return service.getEmployees();
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

    public void loadTableDataEmployee(TableView tbvEmployee, ComboBox comboBox, String keyword) throws SQLException {
        ObservableList<Employee> list = null;
        if (comboBox.valueProperty().getValue().equals("Tìm kiếm theo họ tên")) {
            list = FXCollections.observableList(getByFullName(keyword));
        } else if (comboBox.valueProperty().getValue().equals("Tìm kiếm theo địa chỉ")) {
            list = FXCollections.observableList(getByAddress(keyword));
        } else if (comboBox.valueProperty().getValue().equals("Tìm kiếm theo số điện thoại")) {
            list = FXCollections.observableList(getByPhoneNumber(keyword));
        }
        tbvEmployee.setItems(list);
    }

    public void loadTableDataEmployee(TableView tbvEmployee) {
        try {
            tbvEmployee.setItems(FXCollections.observableList(getEmployeeList()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Employee> getByFullName(String name) throws SQLException {
        List<Employee> employees = new ArrayList<>();

        getEmployeeList().forEach(employee -> {
            if (employee.getFullName().toLowerCase().contains(name))
                employees.add(employee);
        });

        return FXCollections.observableList(employees);
    }

    public ObservableList<Employee> getByPhoneNumber(String phone) throws SQLException {
        List<Employee> employees = new ArrayList<>();

        getEmployeeList().forEach(employee -> {
            if (employee.getPhone().indexOf(phone)!=-1)
                employees.add(employee);
        });

        return FXCollections.observableList(employees);
    }

    public ObservableList<Employee> getByAddress(String address) throws SQLException {
        List<Employee> employees = new ArrayList<>();

        getEmployeeList().forEach(employee -> {
            if (employee.getAddress().toLowerCase().contains(address))
                employees.add(employee);
        });

        return FXCollections.observableList(employees);
    }

    public void loadComboBoxOptional(ComboBox<String> comboBox) {
        List<String> list = new ArrayList<>();
        list.add("Tìm kiếm theo họ tên");
        list.add("Tìm kiếm theo địa chỉ");
        list.add("Tìm kiếm theo số điện thoại");
        comboBox.setItems(FXCollections.observableList(list));
        comboBox.setPromptText("Lựa chọn tìm kiếm");
        comboBox.getSelectionModel().selectFirst();
    }


}
