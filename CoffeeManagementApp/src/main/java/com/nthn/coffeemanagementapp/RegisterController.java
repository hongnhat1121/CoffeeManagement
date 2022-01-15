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
import com.nthn.services.AccountService;
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
import javafx.scene.control.Label;
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

    @FXML
    private Label lbError;

    private final AccountService as = new AccountService();
    private final EmployeeService es = new EmployeeService();

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

        this.txtFullname.textProperty().addListener((ov, t, t1) -> {
            if (t1.isBlank()) {
                this.lbError.setText("Họ tên không được trống.");
            } else if (t1.matches(".*\\d.*")) {
                this.lbError.setText("Họ tên chứa số.");
            } else if (t1.matches(".*\\W.*")) {
                this.lbError.setText("Họ tên chứa ký tự đặc biệt.");
            } else {
                this.lbError.setText("");
            }
        });

        this.txtIdentityCard.textProperty().addListener((ov, t, t1) -> {
            if (t1.isBlank()) {
                this.lbError.setText("CMND/CCCD không được bỏ trống.");
            } else if (t1.matches(".*[a-zA-Z].*")) {
                this.lbError.setText("CMND/CCCD chứa ký tự chữ cái.");
            } else if (t1.matches(".*\\W.*")) {
                this.lbError.setText("CMND/CCCD chứa ký tự đặc biệt.");
            } else if (!t1.matches("\\d{9}") && !t1.matches("\\d{12}")) {
                this.lbError.setText("CMND/CCCD phải chứa 9 hoặc 12 chữ số.");
            } else {
                this.lbError.setText("");
            }
        });

        this.txtPhone.textProperty().addListener((ov, t, t1) -> {
                    if (t1.isBlank()) {
                        this.lbError.setText("Số điện thoại không được bỏ trống.");
                    } else if (!t1.matches("\\d{9}")) {
                        this.lbError.setText("Số điện thoại phải chứa 9 chữ số.");
                    } else if (t1.matches(".*[a-zA-Z].*")) {
                        this.lbError.setText("Số điện thoại chứa ký tự chữ cái.");
                    } else if (t1.matches(".*\\W.*")) {
                        this.lbError.setText("Số điện thoại chứa ký tự đặc biệt.");
                    } else {
                        this.lbError.setText("");
                    }
                }
        );

        this.txtUsername.textProperty().addListener((ov, t, t1) -> {
            if (t1.isBlank()) {
                this.lbError.setText("Tên đăng nhập không được bỏ trống.");
            } else if (t1.length() > 20) {
                this.lbError.setText("Tên đăng nhập vượt quá 20 ký tự.");
            } else if (t1.contains(" ")) {
                this.lbError.setText("Tên đăng nhập chứa khoảng trắng.");
            } else if (t1.matches(".*\\W.*")) {
                this.lbError.setText("Tên đăng nhập chứa ký tự đặc biệt.");
            } else {
                this.lbError.setText("");
            }
        });

        this.txtPassword.textProperty().addListener((ov, t, t1) -> {
            if (t1.isBlank()) {
                this.lbError.setText("Mật khẩu không được bỏ trống.");
            } else if (t1.length() < 6) {
                this.lbError.setText("Mật khẩu ít hơn 6 ký tự.");
            } else if (t1.contains(" ")) {
                this.lbError.setText("Mật khẩu chứa khoảng trắng.");
            } else if (t1.matches(".*\\W.*")) {
                this.lbError.setText("Mật khẩu chứa ký tự đặc biệt.");
            } else if (!t1.matches(".*[a-z].*")) {
                this.lbError.setText("Mật khẩu phải có ít nhất 1 ký tự chữ thường.");
            } else if (!t1.matches(".*[A-Z].*")) {
                this.lbError.setText("Mật khẩu phải có ít nhất 1 ký tự chữ hoa.");
            } else if (!t1.matches(".*[0-9].*")) {
                this.lbError.setText("Mật khẩu phải có ít nhất 1 chữ số.");
            } else {
                this.lbError.setText("");
            }
        });

        this.txtConfirm.textProperty().addListener((ov, t, t1) -> {
            if (t1.equals(this.txtPassword.getText())) {
                this.lbError.setText("Mật khẩu không khớp.");
            }
        });

    }

    public void registerHandler(ActionEvent event) throws SQLException {
        if (txtFullname.getText().isBlank()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Register Error!", "Vui lòng nhập họ tên.");
            return;
        }
        if (txtIdentityCard.getText().isBlank()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Register Error!", "Vui lòng nhập CMND/CCCD.");
            return;
        }
        if (txtPhone.getText().isBlank()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Register Error!", "Vui lòng nhập số điện thoại.");
            return;
        }
        if (txtUsername.getText().isBlank()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Register Error!", "Vui lòng nhập tên đăng nhập.");
            return;
        }
        if (txtPassword.getText().isBlank()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Register Error!", "Vui lòng nhập mật khẩu.");
            return;
        }
        if (txtConfirm.getText().isBlank()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Register Error!", "Vui lòng nhập xác nhận mật khẩu.");
            return;
        }

        if (as.getAccountByUsername(this.txtUsername.getText()) != null) {
            this.lbError.setText("Tên đăng nhập đã tồn tại.");
            return;
        }

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

        //Lưu thông tin
        Account account = new Account(Utils.randomID(), username, password,
                Active.AVAILABLE, Role.USER);
        Employee employee = new Employee(Utils.randomID(), fullname, gender,
                birthDate, identityCard, phone, address, hireDate, account);

        es.addEmployee(employee, account);

        //Kiểm tra kết quả lưu
        try {
            if (new RegisterChecker().isSuccessRegister(employee, account)) {
                App app = new App();
                app.loaderController("Main.fxml", "Coffee Management App - Main");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void loginHandler(ActionEvent event) throws IOException {
        App app = new App();
        app.loaderController("Login.fxml", "Coffee Management App - Login");
    }

    private LocalDate getLocalDate(DatePicker datePicker) {
        TextField textField = datePicker.getEditor();
        String date = textField.getText();
        LocalDate result = Utils.converter.fromString(date);
        return result;
    }

}
