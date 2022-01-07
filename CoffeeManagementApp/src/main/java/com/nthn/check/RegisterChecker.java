/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.check;

import com.nthn.services.AccountService;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HONGNHAT
 */
public class RegisterChecker {

    //Họ tên, CMND/CCCD, số điện thoại không được bỏ trống
    //Tuổi lớn hơn 18;
    public static boolean isValidBirthDate(LocalDate localDate) {
        LocalDate now = LocalDate.now();
        now.minusYears(18); //now=now-18years
        return localDate.compareTo(now) < 0;
    }

    //CMND/CCCD có đủ 9 số hoặc 12 chữ số
    public static boolean isValidIdentityCard(String string) {
        return StringChecker.isAlnum(string, 9) || StringChecker.isAlnum(string, 12);
    }

    //Số điện thoại có đủ 10 chữ số
    public static boolean isValidPhoneNumber(String string) {
        return StringChecker.isAlnum(string, 10);
    }

    //Kiểm tra thông tin tài khoản muốn đăng ký
    public static boolean isValidInfo(String fullname, LocalDate birthDate,
            String identityCard, String phoneNumber) {
        return !fullname.isEmpty() && !identityCard.isEmpty() && !phoneNumber.isEmpty() && isValidBirthDate(birthDate) && isValidIdentityCard(identityCard) && isValidPhoneNumber(phoneNumber);
    }

    //Kiểm tra username có hợp lệ không? Quy định: không quá 20 ký tự, không có ký tự đặc biệt, skhông có khoảng trắng. Tên đăng nhập không trùng.
    public static boolean isValidUsername(String string) {
        try {
            if (!string.isEmpty() && string.length() <= 20 && string.contains(" ") && AccountService.getAccountByUsername(string) == null) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //Kiểm tra password có hợp lệ không? Quy định: có ít nhất 6 ký tự, có chữ hoa, có chữ thường, có số, không trùng tên đăng nhập
    public static boolean isValidPassword(String username, String password) {
        return !password.isEmpty() && password.length() >= 6 && password.contains(" ")
                && StringChecker.containUpper(password)
                && StringChecker.containLower(password)
                && StringChecker.containNumeric(password)
                && password.equalsIgnoreCase(username);
    }
}
