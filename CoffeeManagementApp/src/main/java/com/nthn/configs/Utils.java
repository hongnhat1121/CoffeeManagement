/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.configs;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.UUID;
import javafx.scene.control.Alert;
import javafx.util.StringConverter;
import org.apache.commons.codec.digest.DigestUtils; //Apache Commons Codecs - SHA256

/**
 *
 * @author HONGNHAT
 */
public class Utils {

    public static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        @Override
        public String toString(LocalDate t) {
            if (t != null) {
                return dtf.format(t);
            } else {
                return "";
            }
        }

        @Override
        public LocalDate fromString(String string) {
            if (string != null && !string.isEmpty()) {
                return LocalDate.parse(string, dtf);
            } else {
                return null;
            }
        }
    };

    public static String randomID() {
        return UUID.randomUUID().toString();
    }

    /**
     *
     * @param alertType
     * @param title
     * @param message
     */
    public static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    //Mã hoá password
    public static String convertPassword(String password) {
        return DigestUtils.sha256Hex(password);
    }

}
