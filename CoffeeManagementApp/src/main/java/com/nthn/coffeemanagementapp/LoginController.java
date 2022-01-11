/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.nthn.coffeemanagementapp;

import com.nthn.check.LoginChecker;
import com.nthn.configs.Utils;
import com.nthn.pojo.Account;
import com.nthn.pojo.Employee;
import com.nthn.pojo.Role;
import com.nthn.services.AccountService;
import com.nthn.services.EmployeeService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    private Button btnLogin;
    @FXML
    private Button btnSwitchRegister;

    private Account account;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.btnLogin.getStyleClass().setAll("btn", "btn-success");
    }

    public void loginHandler(ActionEvent event) throws SQLException, IOException {
        String username = this.txtUsername.getText();
        String password = this.txtPassword.getText();
        if (this.txtUsername.getText().isEmpty() || username.contains(" ") || username.matches(".*\\W.*") || username.length() > 20) {
            Utils.showAlert(Alert.AlertType.ERROR, "Login Error!", "Please enter username!");
            return;
        }
        if (this.txtPassword.getText().isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Login Error!", "Please enter password!");
            return;
        }

        LoginChecker checker = new LoginChecker();
        if (checker.isSuccessLogin(username, password)) {
            this.account = new AccountService().getAccountByUsername(username);
            if (account.getRole() == Role.ADMIN) {
                loadOrderController();
            } else {
                loadPrimaryController();
            }
        } else {
            Utils.showAlert(Alert.AlertType.ERROR, "Login Error!", "Login failed!");
        }
    }

    public void registerHandler(ActionEvent event) throws IOException {
        App app = new App();
        app.loaderController("Register.fxml", "Register");
    }

    public void loadPrimaryController() {
        App app = new App();
        try {
            app.loaderController("Main.fxml", "Coffee Management App");
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        MainController controller = new MainController();
        controller.setAccount(account);
    }

    public void loadOrderController() {
        App app = new App();
        try {
            app.loaderController("Order.fxml", "Coffee Management App - Order");
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        EmployeeService employeeService = new EmployeeService();
        Employee e;
        e = employeeService.getEmployeeByAccountID(account.getAccountID());

        OrderController controller = new OrderController();
        controller.setEmployee(e);
    }
}
