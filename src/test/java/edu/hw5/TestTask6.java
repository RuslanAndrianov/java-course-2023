package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task6.isSubstr;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask6 {

    @Test
    @DisplayName("Подстрока есть в строке")
    void substrIsPresent() {

        assertTrue(isSubstr("abc", "achfdbaabgabcaabg"));
        assertTrue(isSubstr("abc^", "abc^asda"));
        assertTrue(isSubstr("abc^wef$", "132qwd1fqwabc^wef$qrqqrwr"));
        assertTrue(isSubstr("01010", "abc^asda01010"));
        assertTrue(isSubstr("\\d{3}", "This is example of regexp: \\d{3}"));
    }

    @Test
    @DisplayName("Подстроки нет в строке")
    void substrIsNotPresent() {

        assertFalse(isSubstr("abd", "achfdbaabgabcaabg"));
        assertFalse(isSubstr("abc^", "%abc%asda"));
        assertFalse(isSubstr("abc^wef$", "132qwd1fqwabc\\wef$qrqqrwr"));
        assertFalse(isSubstr("01010", "abc^asda01011"));
        assertFalse(isSubstr("\\d{4}", "This is example of regexp: \\d{3}"));

    }
}
