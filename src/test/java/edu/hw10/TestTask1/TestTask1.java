package edu.hw10.TestTask1;

import edu.hw10.Task1.RandomObjectGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask1 {

    @Test
    @DisplayName("Тест MyClass")
    void testMyClass() {
        RandomObjectGenerator rog = new RandomObjectGenerator();

        for (int i = 0; i < 20; i++) {
            MyClass myClass1 = rog.nextObject(MyClass.class, "create");
            assertTrue(myClass1.getIntField() >= -10 && myClass1.getIntField() <= 10);
            assertTrue(myClass1.getStrField().length() >= 1 && myClass1.getStrField().length() <= 11);
        }

        MyClass myClass2 = rog.nextObject(MyClass.class, "noSuchMethod");
        assertNull(myClass2);
    }

    @Test
    @DisplayName("Тест MyRecord")
    void testMyRecord() {
        RandomObjectGenerator rog = new RandomObjectGenerator();

        for (int i = 0; i < 20; i++) {
            MyRecord myRecord = rog.nextObject(MyRecord.class);
            assertTrue(myRecord.byteField() >= 20);
            assertTrue(myRecord.doubleField() <= 100);
            assertTrue(myRecord.stringField().length() >= 5 && myRecord.stringField().length() <= 7);
        }
    }
}
