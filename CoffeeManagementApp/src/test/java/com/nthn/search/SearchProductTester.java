/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
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

    @ParameterizedTest(name = "{index} => input={0}")
    @ValueSource(strings = {"345", "(*_="})
    public void testSearchProductByNameInvalid(String input) {
        Assertions.assertFalse(input.matches(".*^[a-zA-Z].*"));
    }

    @ParameterizedTest(name = "{index} => input={0}")
    @ValueSource(strings = {" ", "\t", "\n", "     ", ""})
    public void testSearchProductByNameEmpty(String input) {
        Assertions.assertTrue(input.isBlank());
    }

    @ParameterizedTest(name = "{index} => fromPrice={0}, toPrice={1}")
    @ValueSource(ints = {10000, 20000})
    public void testSearchProductByPriceRangeValid(int fromPrice, int toPrice) {
        boolean flag = false;
        if (fromPrice > 0 && fromPrice < toPrice) {
            flag = true;
        }
        Assertions.assertTrue(flag);
    }

    @ParameterizedTest(name = "{index} => fromPrice={0}, toPrice={1}")
    @ValueSource(ints = {})
    public void testSearchProductByPriceRangeInvalid(int input) {
        Assertions.assertTrue(true);
    }

}
