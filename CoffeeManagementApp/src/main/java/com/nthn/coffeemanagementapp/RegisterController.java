/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.nthn.coffeemanagementapp;

import com.nthn.configs.Utils;
import com.nthn.pojo.Account;
import com.nthn.pojo.Active;
import com.nthn.pojo.Role;
import com.nthn.services.AccountService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HONGNHAT
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtConfirm;
    @FXML
    private Button btnRegister;
    @FXML
    private Label lbHaveAccount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.btnRegister.getStyleClass().setAll("btn", "btn-success");

    }

    public void registerHandler(ActionEvent event) {
        String username = this.txtUsername.getText();
        String password = this.txtPassword.getText();
        String confirm = this.txtConfirm.getText();
        if (username.isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Register Error!", "Please enter username");
            return;
        }
        if (password.isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Register Error!", "Please enter password");
            return;
        }
        if (confirm.isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Register Error!", "Please enter confirm password");
            return;
        }
        if (!confirm.equals(password)) {
            Utils.showAlert(Alert.AlertType.ERROR, "Register Error!", "Please re-enter confirm password");
            return;
        }
        Utils.showAlert(Alert.AlertType.INFORMATION, "Registration successfull!", "Welcome to Coffee Management App");
        Account account = new Account(Utils.randomID(), username, password, Active.AVAILABLE, Role.USER);
        new AccountService().addAccount(account);
    }

    public void haveAccountHandler(ActionEvent event) {
        LoginController loginController = new LoginController();

    }
}
