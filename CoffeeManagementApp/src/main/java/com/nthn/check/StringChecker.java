/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.check;

/**
 *
 * @author HONGNHAT
 */
public class StringChecker {

    public static boolean containUpper(String string) {
        return string.matches(".*[A-Z].*");
    }

    public static boolean containLower(String string) {
        return string.matches(".*[a-z].*");
    }

    public static boolean containNumeric(String string) {
        return string.matches(".*[0-9].*");
    }

    public static boolean containSpecialLetter(String string) {
        return string.matches(".*\\W.*");
    }

    //Kiểm tra chuỗi string có phải là chuỗi số hay không
    public static boolean isAlnum(String string, int length) {
        String regex = "\\d{" + length + "}";
        return string.matches(regex);
    }

    //Kiểm tra chuỗi string chỉ chứa ký tự chữ cái
    public static boolean isAlpla(String string) {
        return !containNumeric(string) && !containSpecialLetter(string);
    }
}
