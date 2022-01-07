/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.nthn.coffeemanagementapp;

import com.nthn.configs.JdbcUtils;
import com.nthn.configs.Utils;
import com.nthn.pojo.Account;
import com.nthn.pojo.Active;
import com.nthn.pojo.Employee;
import com.nthn.pojo.Gender;
import com.nthn.pojo.Role;
import com.nthn.services.AccountService;
import com.nthn.services.EmployeeService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

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
        this.btnRegister.getStyleClass().setAll("btn", "btn-success");

        this.dpBirthDate.setConverter(Utils.converter);
        this.dpBirthDate.setValue(LocalDate.now());

        this.dpHireDate.setConverter(Utils.converter);
        this.dpHireDate.setValue(LocalDate.now());
    }

    public void registerHandler(ActionEvent event) {
        AccountService accountService = new AccountService();
        EmployeeService employeeService = new EmployeeService();

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
        if (fullname.isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Register Error!", "Please enter fullname!");
            return;
        }

        //Kiểm tra ngày sinh >= ngày hiện tại - 18 năm
        LocalDate localDate = LocalDate.now();
        localDate.minusYears(18);

        if (birthDate.compareTo(localDate) > 0) {
            Utils.showAlert(Alert.AlertType.ERROR, "Register Error!", "Please re-choose birthday!");
            return;
        }

        //Kiểm tra CMND/CCCD
        if (identityCard.isEmpty() || !identityCard.matches("\\d{9,12}")) {
            Utils.showAlert(Alert.AlertType.ERROR, "Register Error!", "Please enter identity card!");
            return;
        }

        //Kiểm tra số điện thoại
        if (phone.isEmpty() || !phone.matches("\\d{10}")) {
            Utils.showAlert(Alert.AlertType.ERROR, "Register Error!", "Please enter phone number!");
            return;
        }

        //Kiểm tra tên đăng nhập
        if (username.isEmpty() || username.length() > 20 || username.matches(".*\\W.*")) {
            Utils.showAlert(Alert.AlertType.ERROR, "Login Error!", "Please enter username!");
            return;
        }

        //Kiểm tra mật khẩu
//        if (password.isEmpty() || password.length() > 6 || password.equals(username) 
//                || !password.matches(".*[0-9].*") || !password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*") || )
//            ,) {
//            Utils.showAlert(Alert.AlertType.ERROR, "Register Error!", "Please enter password");
//            return;
//        }
//
//        //Kiểm tra xác nhận mật khẩu
//        if (confirm.isEmpty() || !confirm.equals(password)) {
//            Utils.showAlert(Alert.AlertType.ERROR, "Register Error!", "Please enter confirm password");
//            return;
//        }
//        if (!this.txtConfirm.getText().equals(this.txtPassword.getText())) {
//            Utils.showAlert(Alert.AlertType.ERROR, "Register Error!", "Please re-enter confirm password");
//            return;
//        }
        //Kiểm tra username đã tồn tại hay chưa?
        Account account = null;
        try {
            account = accountService.getAccountByUsername(username);
        } catch (SQLException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (account != null) {
            Utils.showAlert(Alert.AlertType.ERROR, "Register Error!", "Username already exists.");
            return;
        }

        account = new Account(Utils.randomID(), username, password, Active.AVAILABLE, Role.USER);
        Employee employee = new Employee(Utils.randomID(), hireDate, fullname, birthDate, gender, identityCard, address, phone, account.getAccountID());

        accountService.addAccount(account);
        employeeService.addEmployee(employee);
        Utils.showAlert(Alert.AlertType.INFORMATION, "Registration successfull!", "Welcome to Coffee Management App");
    }

    public void loginHandler(ActionEvent event) throws IOException {
        App app = new App();
        app.loaderController("Login.fxml", "Login");
    }

    private LocalDate getLocalDate(DatePicker datePicker) {
        TextField textField = datePicker.getEditor();
        String date = textField.getText();
        LocalDate result = Utils.converter.fromString(date);
        return result;
    }
}
