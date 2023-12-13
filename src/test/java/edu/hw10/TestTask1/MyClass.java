package edu.hw10.TestTask1;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;
import edu.hw10.Task1.annotations.NotNull;

public class MyClass {

    private final String strField;
    private final int intField;

    private MyClass(String strField, int intField) {
        this.strField = strField;
        this.intField = intField;
    }

    public static MyClass create(@NotNull String strField, @Min(-10) @Max(10) int intField) {
        return new MyClass(strField, intField);
    }

    public int getIntField() {
        return intField;
    }

    public String getStrField() {
        return strField;
    }
}
