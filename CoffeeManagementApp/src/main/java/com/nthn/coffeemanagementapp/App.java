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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root, 900, 600);

        stage.setOnHiding((t) -> {
            try {
                Connection c = JdbcUtils.getConnection();
                if (c != null) {
                    c.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        //launch();
//        AccountService accountService = new AccountService();
//        Account account = new Account(Utils.randomID(), "admin", "1121", Active.AVAILABLE, Role.ADMIN);
//
//        EmployeeService employeeService = new EmployeeService();
//        Employee employee = new Employee(Utils.randomID(), LocalDate.parse("4/1/2022"),
//                "Nguyễn Thị Hồng Nhật", LocalDate.parse("10/10/2001"),
//                Gender.getByContent("Nữ"), "342010930", "Đồng Tháp", "0836479646", account.getAccountID());
//
////        accountService.addAccount(account);
//        //employeeService.addEmployee(employee);
//        account.viewDetail();
//        employee.viewDetail();
       // System.out.println(LocalDate.parse("2001-04-11"));
//       Employee employee=new Employee(Utils.randomID(), LocalDate.now(),
//                "Nguyễn Thị Hồng Nhật", LocalDate.of(2001, Month.OCTOBER, 10),
//                Gender.getByContent("Nữ"), "342010930", "Đồng Tháp", "0836479646", "563d4212-1042-4c6b-9979-9b171b15d437");
//       new EmployeeService().addEmployee(employee);
    }
}
