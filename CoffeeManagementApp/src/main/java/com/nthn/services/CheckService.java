/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.services;

/**
 *
 * @author HONGNHAT
 */
public class CheckService {

    /**
     * Kiểm tra chuỗi text có chứa chữ hoa hay không?
     *
     * @param text
     * @return
     */
    public static boolean containUpper(String text) {
        return text.matches(".*[A-Z].*");
    }

    /**
     * Kiểm tra chuỗi text có chứa chữ thường hay không?
     *
     * @param text
     * @return
     */
    public static boolean containLower(String text) {
        return text.matches(".*[a-z].*");
    }

    /**
     * Kiểm tra chuỗi text có chứa chữ số hay không?
     *
     * @param text
     * @return
     */
    public static boolean containAlnum(String text) {
        return text.matches(".*[0-9].*");
    }

    /**
     * Kiểm tra chuỗi text có chứa ký tự đặc biệt hay không?
     *
     * @param text
     * @return
     */
    public static boolean containSpecialLetter(String text) {
        return text.matches(".*\\W.*");
    }

  

}
