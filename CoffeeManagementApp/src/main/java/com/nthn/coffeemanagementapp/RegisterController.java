/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.nthn.coffeemanagementapp;

import com.nthn.check.RegisterChecker;
import com.nthn.configs.Utils;
import com.nthn.pojo.Account;
import com.nthn.pojo.Active;
import com.nthn.pojo.Employee;
import com.nthn.pojo.Gender;
import com.nthn.pojo.Role;
import com.nthn.services.EmployeeService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

/**
 * FXML Controller class
 *
 * @author HONGNHAT
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField txtFullname;
    @FXML
    private RadioButton rbMale;
    @FXML
    private RadioButton rbFemale;
    @FXML
    private RadioButton rbOther;
    @FXML
    private DatePicker dpBirthDate;
    @FXML
    private TextField txtIdentityCard;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtAddress;
    @FXML
    private DatePicker dpHireDate;

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtConfirm;

    @FXML
    private Button btnRegister;
    @FXML
    private Button btnSwitchLogin;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RegisterChecker checker = new RegisterChecker();

        this.btnRegister.getStyleClass().setAll("btn", "btn-success");

        this.dpBirthDate.setConverter(Utils.converter);
        this.dpBirthDate.setValue(LocalDate.now());

        this.dpHireDate.setConverter(Utils.converter);
        this.dpHireDate.setValue(LocalDate.now());

        this.txtFullname.textProperty().addListener((ov, t, t1) -> {
            Tooltip tooltip = new Tooltip();
            if (!checker.isValidFullname(t1)) {
                tooltip.setText("Error! Full name contains only [a-z] or [A-Z].");
                Tooltip.install(this.txtFullname, tooltip);
            } else {
                Tooltip.uninstall(this.txtFullname, tooltip);
            }
        });

        this.txtIdentityCard.textProperty().addListener((ov, t, t1) -> {
            Tooltip tooltip = new Tooltip();
            if (!checker.isValidIdentityCard(t1)) {
                tooltip.setText("Error! Identity card contains only [0-9].");
                Tooltip.install(this.txtIdentityCard, tooltip);
            } else {
                Tooltip.uninstall(this.txtIdentityCard, tooltip);
            }
        });

        this.txtPhone.textProperty().addListener((ov, t, t1) -> {
            Tooltip tooltip = new Tooltip();
            if (!checker.isValidPhoneNumber(t1)) {
                tooltip.setText("Error! Phone number contains only [0-9].");
                Tooltip.install(this.txtPhone, tooltip);
            } else {
                Tooltip.uninstall(this.txtPhone, tooltip);
            }
        });

        this.txtUsername.textProperty().addListener((ov, t, t1) -> {
            Tooltip tooltip = new Tooltip();
            try {
                if (!checker.isValidUsername(t1)) {
                    tooltip.setText("Username already exist or wrong username.");
                    Tooltip.install(this.txtUsername, tooltip);
                } else {
                    Tooltip.uninstall(this.txtUsername, tooltip);
                }
            } catch (SQLException ex) {
                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        this.txtPassword.textProperty().addListener((ov, t, t1) -> {
            Tooltip tooltip = new Tooltip();
            if (!checker.isValidPassword(this.txtUsername.getText(), t1)) {
                tooltip.setText("Password must contain at least 1 uppercase, 1 lowercase, digits, no special characters.");
                Tooltip.install(this.txtPassword, tooltip);
            } else {
                Tooltip.uninstall(this.txtPassword, tooltip);
            }
        });

        this.txtConfirm.textProperty().addListener((ov, t, t1) -> {
            Tooltip tooltip = new Tooltip();
            if (!checker.isValidConfirm(t1, this.txtPassword.getText())) {
                tooltip.setText("Error! Confirm password doesn't match password.");
                Tooltip.install(this.txtConfirm, tooltip);
            } else {
                Tooltip.uninstall(this.txtConfirm, tooltip);
            }
        });

    }

    public void registerHandler(ActionEvent event) {
        EmployeeService employeeService = new EmployeeService();
        RegisterChecker checker = new RegisterChecker();

        String fullname = this.txtFullname.getText();
        Gender gender = Gender.MALE;
        if (rbFemale.isSelected()) {
            gender = Gender.FEMALE;
        } else if (rbOther.isSelected()) {
            gender = Gender.OTHER;
        }
        LocalDate birthDate = this.getLocalDate(this.dpBirthDate);
        String identityCard = this.txtIdentityCard.getText();
        String phone = this.txtPhone.getText();
        String address = this.txtAddress.getText();
        LocalDate hireDate = this.getLocalDate(this.dpHireDate);

        String username = this.txtUsername.getText();
        String password = this.txtPassword.getText();
        String confirm = this.txtConfirm.getText();

        //Kiểm tra họ và tên
        if (!checker.isValidFullname(fullname)) {
            Utils.showAlert(Alert.AlertType.ERROR, "Register Error!", "Please enter fullname!");
            return;
        }

        //Kiểm tra ngày sinh >= ngày hiện tại - 18 năm
        if (!checker.isValidBirthDate(birthDate)) {
            Utils.showAlert(Alert.AlertType.ERROR, "Register Error!", "Please re-choose birthday!");
            return;
        }

        //Kiểm tra CMND/CCCD
        if (!checker.isValidIdentityCard(identityCard)) {
            Utils.showAlert(Alert.AlertType.ERROR, "Register Error!", "Please enter identity card!");
            return;
        }

        //Kiểm tra số điện thoại
        if (!checker.isValidPhoneNumber(phone)) {
            Utils.showAlert(Alert.AlertType.ERROR, "Register Error!", "Please enter phone number!");
            return;
        }

        //Kiểm tra tên đăng nhập
        if (username.isEmpty() || username.length() > 20 || username.matches(".*\\W.*")) {
            Utils.showAlert(Alert.AlertType.ERROR, "Login Error!", "Please enter username!");
            return;
        }

        //Kiểm tra mật khẩu
        if (!checker.isValidPassword(username, password)) {
            Utils.showAlert(Alert.AlertType.ERROR, "Login Error!", "Please enter password!");
            return;
        }

        //Kiểm tra mật khẩu nhập lại
        if (!checker.isValidConfirm(confirm, password)) {
            Utils.showAlert(Alert.AlertType.ERROR, "Login Error!", "Please enter confirm password!");
            return;
        }

        //Lưu thông tin
        Account account = new Account(Utils.randomID(), username, password, Active.AVAILABLE, Role.USER);
        Employee employee = new Employee(Utils.randomID(), hireDate, fullname, birthDate, gender, identityCard, address, phone, account.getAccountID());

        employeeService.addAccount(employee, account);

        //Kiểm tra kết quả lưu
        try {
            if (!checker.isSuccessRegister(employee, account)) {
                Utils.showAlert(Alert.AlertType.INFORMATION, "Registration successfull!", "Welcome to Coffee Management App");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loginHandler(ActionEvent event) throws IOException {
        App app = new App();
        app.loaderController("Login.fxml", "Login");
    }

    public LocalDate getLocalDate(DatePicker datePicker) {
        TextField textField = datePicker.getEditor();
        String date = textField.getText();
        LocalDate result = Utils.converter.fromString(date);
        return result;
    }

}
