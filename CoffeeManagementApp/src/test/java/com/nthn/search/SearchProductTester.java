/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author HONGNHAT
 */
public class SearchProductTester {

    //Tìm kiếm theo tên, chỉ được nhập chữ
    @ParameterizedTest(name = "{index} => input={0}}")
    @ValueSource(strings = {"Cafe sữa", "Capuchino"})
    public void testerSearchProductByNameValid(String input) {
        Assertions.assertTrue(input.matches(".*^[a-zA-Z].*"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"345", "(*_="})
    public void testSearchProductByNameInvalid(String input) {
        Assertions.assertFalse(input.matches(".*^[a-zA-Z].*"));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n", "     ", ""})
    public void testSearchProductByNameEmpty(String input) {
        Assertions.assertTrue(input.isBlank());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Cafe sữa", "Capuchino"})
    public void testSearchProductByNameNotEmpty(String input) {
        Assertions.assertFalse(input.isBlank());
    }

    //Nhập giá hợp lệ: chỉ nhập ký tự số
    @ParameterizedTest
    @ValueSource(strings = {"10", "15", "1"})
    public void testSearchProductByPriceIsAlnum(String input) {
        String regex = "\\d{" + input.length() + "}";
        Assertions.assertTrue(input.matches(regex));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "không", "một"})
    public void testSearchProductByPriceNotAlnum(String input) {
        String regex = "\\d{" + input.length() + "}";
        Assertions.assertFalse(input.matches(regex));
    }

    //Khoảng giá hợp lệ: giá > 0, giá tối thiểu < giá tối đa
    @ParameterizedTest
    @CsvSource({"1, 10", "13, 14"})
    public void testSearchProductByPriceRangeValid(int fromPrice, int toPrice) {
        boolean flag = fromPrice > 0 && fromPrice < toPrice;
        Assertions.assertTrue(flag);
    }

    //Khoảng giá không hợp lệ
    @ParameterizedTest
    @CsvSource({"-1, 10", "0, 14", "-1, 0"})
    public void testSearchProductByPriceRangeInvalid(int fromPrice, int toPrice) {
        boolean flag = fromPrice > 0 && fromPrice < toPrice;
        Assertions.assertFalse(flag);
    }

}
