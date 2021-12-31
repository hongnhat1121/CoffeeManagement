/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.nthn.coffeemanagementapp;

import com.nthn.pojo.Account;
import com.nthn.pojo.Active;
import com.nthn.pojo.Role;
import com.nthn.services.AccountService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HONGNHAT
 */
public class FXML_LoginController implements Initializable {

    @FXML
    private ComboBox<Role> cbRoles;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnReset;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        this.cbRoles.getItems().addAll(Role.values());
//        this.cbRoles.setValue(Role.USER);
    }

    public void loginHandler(ActionEvent actionEvent) throws SQLException {
        Account a = new Account(UUID.randomUUID().toString(),
                txtUsername.getText(), txtPassword.getText(), Active.AVAILABLE,
                cbRoles.getSelectionModel().getSelectedItem());
        a.display();
    }

    public void resetHandler(ActionEvent actionEvent) {
        this.txtUsername.clear();
        this.txtPassword.clear();
    }

}
