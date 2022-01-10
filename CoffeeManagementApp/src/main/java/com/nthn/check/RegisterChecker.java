/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.check;

import com.nthn.configs.Utils;
import com.nthn.pojo.Account;
import com.nthn.pojo.Employee;
import com.nthn.services.AccountService;
import com.nthn.services.EmployeeService;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HONGNHAT
 */
public class RegisterChecker {

    //Họ tên không bỏ trống, chỉ chứa ký tự chữ cái
    public boolean isValidFullname(String string) {
        return !string.isEmpty() && !StringChecker.containNumeric(string);
    }

    //Tuổi lớn hơn 18;
    public boolean isValidBirthDate(LocalDate localDate) {
        LocalDate now = LocalDate.now();
        now.minusYears(18);
        return localDate.compareTo(now) < 0;

    }

    //CMND/CCCD có đủ 9 số hoặc 12 chữ số
    public boolean isValidIdentityCard(String string) {
        return StringChecker.isAlnum(string, 9) || StringChecker.isAlnum(string, 12);
    }

    //Số điện thoại có đủ 10 chữ số
    public boolean isValidPhoneNumber(String string) {
        return StringChecker.isAlnum(string, 10);
    }

    //Kiểm tra thông tin tài khoản muốn đăng ký
    public boolean isValidInfo(String fullname, LocalDate birthDate,
            String identityCard, String phoneNumber) {
        return !fullname.isEmpty() && !identityCard.isEmpty() && !phoneNumber.isEmpty()
                && isValidBirthDate(birthDate) && isValidIdentityCard(identityCard)
                && isValidPhoneNumber(phoneNumber);
    }

    //Kiểm tra username có hợp lệ không? Quy định: không quá 20 ký tự, không có ký tự đặc biệt, skhông có khoảng trắng. Tên đăng nhập không trùng.
    public boolean isValidUsername(String string) throws SQLException {
        AccountService accountService = new AccountService();
        return !string.isEmpty() && string.length() <= 20 && !string.contains(" ")
                && accountService.getAccountByUsername(string) == null;

    }

    //Kiểm tra password có hợp lệ không? Quy định: có ít nhất 6 ký tự, có chữ hoa, có chữ thường, có số, không trùng tên đăng nhập
    public boolean isValidPassword(String username, String password) {
        return !password.isEmpty() && (password.length() >= 6) && !password.contains(" ")
                && StringChecker.containUpper(password)
                && StringChecker.containLower(password)
                && StringChecker.containNumeric(password)
                && !password.equals(username);
    }

    //Kiểm tra confirm password
    public boolean isValidConfirm(String confirm, String password) {
        return confirm.equals(password);
    }

    //Kiểm tra đăng ký tài khoản thành công không?
    public boolean isSuccessRegister(Employee employee, Account account) throws SQLException {
        EmployeeService employeeService = new EmployeeService();
        return employeeService.checkEmployee(employee.getEmployeeID(), account.getAccountID());
    }
}
