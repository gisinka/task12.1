package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Tests {

    @Test
    public void testWithEmptyString() {
        Throwable exception = Assertions.assertThrows(NumberFormatException.class, () -> Integer.decode(""));
        assertEquals("Zero length string", exception.getMessage());
    }

    @Test
    public void testWithIncorrectSignsPositions() {
        ArrayList<String> strings = new ArrayList<>(Arrays.asList("+-", "-+", "+-100", "-+100", "+-0x64", "-+0x64", "+-#64", "-+#64"));
        for (String s : strings) {
            Throwable exception = Assertions.assertThrows(NumberFormatException.class, () -> Integer.decode(s));
            assertEquals("Sign character in wrong position", exception.getMessage());
        }
    }

    @Test
    public void testWithBoundaryValues() {
        assertEquals(Integer.MIN_VALUE, Integer.decode(String.valueOf(Integer.MIN_VALUE)));
        assertEquals(Integer.MAX_VALUE, Integer.decode(String.valueOf(Integer.MAX_VALUE)));
    }

    @Test
    public void testWithPositiveDecimalNumbers() {
        assertEquals(0, Integer.decode("0"));
        assertEquals(0, Integer.decode("+0"));
        assertEquals(1, Integer.decode("1"));
        assertEquals(1, Integer.decode("+1"));
        assertEquals(10, Integer.decode("10"));
        assertEquals(10, Integer.decode("+10"));
        assertEquals(512, Integer.decode("512"));
        assertEquals(512, Integer.decode("+512"));
    }

    @Test
    public void testWithNegativeDecimalNumbers() {
        assertEquals(0, Integer.decode("-0"));
        assertEquals(-1, Integer.decode("-1"));
        assertEquals(-10, Integer.decode("-10"));
        assertEquals(-512, Integer.decode("-512"));
    }

    @Test
    public void testWithPositiveOctalRadix() {
        assertEquals(0, Integer.decode("00"));
        assertEquals(0, Integer.decode("+00"));
        assertEquals(1, Integer.decode("01"));
        assertEquals(1, Integer.decode("+01"));
        assertEquals(8, Integer.decode("010"));
        assertEquals(8, Integer.decode("+010"));
        assertEquals(64, Integer.decode("0100"));
        assertEquals(64, Integer.decode("+0100"));
    }

    @Test
    public void testWithNegativeOctalRadix() {
        assertEquals(0, Integer.decode("-00"));
        assertEquals(-1, Integer.decode("-01"));
        assertEquals(-8, Integer.decode("-010"));
        assertEquals(-64, Integer.decode("-0100"));
    }

    @Test
    public void testWithHexPositiveRadix() {
        assertEquals(0, Integer.decode("0x0"));
        assertEquals(0, Integer.decode("+0x0"));
        assertEquals(0, Integer.decode("#0"));
        assertEquals(0, Integer.decode("+#0"));
        assertEquals(1, Integer.decode("0x1"));
        assertEquals(1, Integer.decode("+0x1"));
        assertEquals(1, Integer.decode("#1"));
        assertEquals(1, Integer.decode("+#1"));
        assertEquals(16, Integer.decode("0x10"));
        assertEquals(16, Integer.decode("+0x10"));
        assertEquals(16, Integer.decode("#10"));
        assertEquals(16, Integer.decode("+#10"));
        assertEquals(1000, Integer.decode("0x3E8"));
        assertEquals(1000, Integer.decode("+0x3E8"));
        assertEquals(1000, Integer.decode("#3E8"));
        assertEquals(1000, Integer.decode("+#3E8"));
    }

    @Test
    public void testWithNegativeHexRadixNumbers() {
        assertEquals(0, Integer.decode("-0x0"));
        assertEquals(0, Integer.decode("-#0"));
        assertEquals(-1, Integer.decode("-0x1"));
        assertEquals(-1, Integer.decode("-#1"));
        assertEquals(-16, Integer.decode("-0x10"));
        assertEquals(-16, Integer.decode("-#10"));
        assertEquals(-1000, Integer.decode("-0x3E8"));
        assertEquals(-1000, Integer.decode("-#3E8"));
    }
}
