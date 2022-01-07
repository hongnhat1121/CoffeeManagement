/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.check;

import java.time.LocalDate;

/**
 *
 * @author HONGNHAT
 */
public class RegisterChecker {

    //Tuổi lớn hơn 18;
    public static boolean isValidBirthDate(LocalDate localDate) {
        LocalDate now = LocalDate.now();
        now.minusYears(18); //now=now-18years
        return localDate.compareTo(now) < 0;
    }

    //CMND/CCCD có đủ 9 số hoặc 12 chữ số
    public static boolean isValidIndentityCard(String string) {
        return StringChecker.isAlnum(string, 9) || StringChecker.isAlnum(string, 12);
    }
    
    //Số điện thoại có đủ 10 chữ số
    public static boolean isValidPhoneNumber(String string){
        return StringChecker.isAlnum(string, 10);
    }
            
            
}
