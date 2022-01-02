/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.nthn.coffeemanagementapp;

import com.nthn.configs.Utils;
import com.nthn.pojo.Account;
import com.nthn.pojo.Active;
import com.nthn.pojo.Employee;
import com.nthn.pojo.Gender;
import com.nthn.pojo.Role;
import com.nthn.services.AccountService;
import com.nthn.services.EmployeeService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HONGNHAT
 */
public class FXML_CreateAccountController implements Initializable {
    
    @FXML
    private TextField txtFullName;
    @FXML
    private DatePicker dpBirthDate;
    @FXML
    private RadioButton rbtnMale;
    @FXML
    private RadioButton rbtnFemale;
    @FXML
    private RadioButton rbtnOther;
    @FXML
    private TextField txtIdentityCard;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtPhone;
    @FXML
    private DatePicker dpHireDate;
    @FXML
    private TextField txtUsername;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnCreate;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        this.btnReset.getStyleClass().setAll("btn", "btn-danger");
        this.btnCreate.getStyleClass().setAll("btn", "btn-success");
        this.dpHireDate.setValue(LocalDate.now());
        
    }
    
    public void createHandler(ActionEvent event) {
        Account account = new Account(Utils.randomID(), txtUsername.getText(),
                Account.createPassword(), Active.AVAILABLE, Role.USER);
        
//        Employee employee = new Employee(Utils.randomID(), Date.valueOf(dpHireDate.getValue().format(Utils.DATEFORMAT.)), account.getAccountID(),
//                txtFullName.getText(), dpBirthDate.setConverter(sc),
//                Gender.OTHER, txtIdentityCard.getText(), txtAddress.getText(),
//                txtPhone.getText());
        try {
            account.display();
//        AccountService.addAccount(account);
//        EmployeeService.addEmployee(employee);
        } catch (SQLException ex) {
            Logger.getLogger(FXML_CreateAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void resetHandler(ActionEvent event) {
        this.txtFullName.clear();
        this.txtAddress.clear();
        this.txtIdentityCard.clear();
        this.txtPhone.clear();
        this.txtUsername.clear();
    }
    
}
