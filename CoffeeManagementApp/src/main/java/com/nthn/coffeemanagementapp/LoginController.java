/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.nthn.coffeemanagementapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HONGNHAT
 */
public class LoginController implements Initializable {
    
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnSignin;
    @FXML
    private Label lbNoAccount;
    @FXML
    private Label lbError;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.btnSignin.getStyleClass().setAll("btn", "btn-success");
    }
    
    public void loginHandler(ActionEvent event) {
        String username = this.txtUsername.getText();
        String password = this.txtPassword.getText();
        if (username.isEmpty() && password.isEmpty()) {
            this.lbError.setText("Please enter account!");
        } else if (username.isEmpty()) {
            this.lbError.setText("Please enter username!");
        } else if (password.isEmpty()) {
            this.lbError.setText("Please enter password!");
            
        } else {
            this.lbError.setText("");
        }
    }
    
}
