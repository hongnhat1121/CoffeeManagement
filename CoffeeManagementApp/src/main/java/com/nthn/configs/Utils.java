/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.configs;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Scanner;
import java.util.UUID;

/**
 *
 * @author HONGNHAT
 */
public class Utils {

    public static final Scanner SCANNER = new Scanner(System.in);
    public static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("yyyy/MM/dd");

    public static String randomID() {
        return UUID.randomUUID().toString();
    }

    public static Date asDate(LocalDate localDate) {
        return (Date) Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
}
