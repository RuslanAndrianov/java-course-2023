package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw3.Task4.convertToRoman;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask4 {

    @Test
    @DisplayName("Корректные числа")
    void validInput() {
        assertEquals(convertToRoman(1), "I");
        assertEquals(convertToRoman(2), "II");
        assertEquals(convertToRoman(3), "III");
        assertEquals(convertToRoman(4), "IV");
        assertEquals(convertToRoman(5), "V");
        assertEquals(convertToRoman(6), "VI");
        assertEquals(convertToRoman(7), "VII");
        assertEquals(convertToRoman(8), "VIII");
        assertEquals(convertToRoman(9), "IX");
        assertEquals(convertToRoman(10), "X");
        assertEquals(convertToRoman(11),"XI");
        assertEquals(convertToRoman(12), "XII");
        assertEquals(convertToRoman(13), "XIII");
        assertEquals(convertToRoman(14), "XIV");
        assertEquals(convertToRoman(15), "XV");
        assertEquals(convertToRoman(16), "XVI");
        assertEquals(convertToRoman(17), "XVII");
        assertEquals(convertToRoman(18), "XVIII");
        assertEquals(convertToRoman(19), "XIX");
        assertEquals(convertToRoman(20), "XX");
        assertEquals(convertToRoman(30), "XXX");
        assertEquals(convertToRoman(40), "XL");
        assertEquals(convertToRoman(50), "L");
        assertEquals(convertToRoman(60), "LX");
        assertEquals(convertToRoman(70), "LXX");
        assertEquals(convertToRoman(80), "LXXX");
        assertEquals(convertToRoman(89), "LXXXIX");
        assertEquals(convertToRoman(90), "XC");
        assertEquals(convertToRoman(100), "C");
        assertEquals(convertToRoman(101), "CI");
        assertEquals(convertToRoman(110), "CX");
        assertEquals(convertToRoman(111), "CXI");
        assertEquals(convertToRoman(200), "CC");
        assertEquals(convertToRoman(300), "CCC");
        assertEquals(convertToRoman(400), "CD");
        assertEquals(convertToRoman(500), "D");
        assertEquals(convertToRoman(600), "DC");
        assertEquals(convertToRoman(700), "DCC");
        assertEquals(convertToRoman(800), "DCCC");
        assertEquals(convertToRoman(900), "CM");
        assertEquals(convertToRoman(1000), "M");
        assertEquals(convertToRoman(2000),	"MM");
        assertEquals(convertToRoman(3000),	"MMM");
        assertEquals(convertToRoman(3999),	"MMMCMXCIX");
    }

    @Test
    @DisplayName("Некорректные числа")
    void invalidInput() {
        assertEquals(convertToRoman(0), "");
        assertEquals(convertToRoman(4000), "");
        assertEquals(convertToRoman(-1), "");
        assertEquals(convertToRoman(Integer.MAX_VALUE), "");
    }
}
