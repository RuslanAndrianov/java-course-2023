package edu.hw3;

import edu.hw3.Task5.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw3.Task5.Task5.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask5 {

    String[] s_list1 = new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
    String[] s_list2 = new String[] {"Paul", "Leonhard", "Carl Gauss"};
    String[] s_list3 = new String[] {};
    String[] s_list4 = new String[] {"  Name   Surname   Ignored   "};

    String[] s_list5 = null;

    Person[] p_list1 = new Person[s_list1.length];
    Person[] p_list2 = new Person[s_list2.length];
    Person[] p_list3 = new Person[s_list3.length];
    Person[] p_list4 = new Person[s_list4.length];

    Person[] p_list5 = new Person[] {};

    void fill() {
        fillPersonList(s_list1, p_list1);
        fillPersonList(s_list2, p_list2);
        fillPersonList(s_list3, p_list3);
        fillPersonList(s_list4, p_list4);
    }

    void sort() {
        sortPersonListASC(p_list1);
        sortPersonListASC(p_list2);
        sortPersonListASC(p_list3);
        sortPersonListASC(p_list4);
    }

    void reverse() {
        reversePersonsList(p_list1);
        reversePersonsList(p_list2);
        reversePersonsList(p_list3);
        reversePersonsList(p_list4);
    }

    @Test
    @DisplayName("Тест вспомогательного метода fillPersonList")
    void testFillPersonList() {

        fill();

        assertEquals(p_list1[0].getName(), "John");
        assertEquals(p_list1[0].getSurname(), "Locke");
        assertEquals(p_list1[1].getName(), "Thomas");
        assertEquals(p_list1[1].getSurname(), "Aquinas");
        assertEquals(p_list1[2].getName(), "David");
        assertEquals(p_list1[2].getSurname(), "Hume");
        assertEquals(p_list1[3].getName(), "Rene");
        assertEquals(p_list1[3].getSurname(), "Descartes");

        assertEquals(p_list2[0].getName(), "Paul");
        assertEquals(p_list2[0].getSurname(), "");
        assertEquals(p_list2[1].getName(), "Leonhard");
        assertEquals(p_list2[1].getSurname(), "");
        assertEquals(p_list2[2].getName(), "Carl");
        assertEquals(p_list2[2].getSurname(), "Gauss");

        assertArrayEquals(p_list3, new Person[]{});

        assertEquals(p_list4[0].getName(), "Name");
        assertEquals(p_list4[0].getSurname(), "Surname");
    }

    @Test
    @DisplayName("Тест вспомогательного метода sortPersonListASC")
    void testSortPersonListASC() {

        fill();
        sort();

        assertEquals(p_list1[3].getName(), "John");
        assertEquals(p_list1[3].getSurname(), "Locke");
        assertEquals(p_list1[0].getName(), "Thomas");
        assertEquals(p_list1[0].getSurname(), "Aquinas");
        assertEquals(p_list1[2].getName(), "David");
        assertEquals(p_list1[2].getSurname(), "Hume");
        assertEquals(p_list1[1].getName(), "Rene");
        assertEquals(p_list1[1].getSurname(), "Descartes");

        assertEquals(p_list2[2].getName(), "Paul");
        assertEquals(p_list2[2].getSurname(), "");
        assertEquals(p_list2[1].getName(), "Leonhard");
        assertEquals(p_list2[1].getSurname(), "");
        assertEquals(p_list2[0].getName(), "Carl");
        assertEquals(p_list2[0].getSurname(), "Gauss");

        assertArrayEquals(p_list3, new Person[]{});

        assertEquals(p_list4[0].getName(), "Name");
        assertEquals(p_list4[0].getSurname(), "Surname");
    }

    @Test
    @DisplayName("Тест вспомогательного метода reversePersonsList")
    void testReversePersonsList() {

        fill();
        sort();
        reverse();

        assertEquals(p_list1[0].getName(), "John");
        assertEquals(p_list1[0].getSurname(), "Locke");
        assertEquals(p_list1[3].getName(), "Thomas");
        assertEquals(p_list1[3].getSurname(), "Aquinas");
        assertEquals(p_list1[1].getName(), "David");
        assertEquals(p_list1[1].getSurname(), "Hume");
        assertEquals(p_list1[2].getName(), "Rene");
        assertEquals(p_list1[2].getSurname(), "Descartes");

        assertEquals(p_list2[0].getName(), "Paul");
        assertEquals(p_list2[0].getSurname(), "");
        assertEquals(p_list2[1].getName(), "Leonhard");
        assertEquals(p_list2[1].getSurname(), "");
        assertEquals(p_list2[2].getName(), "Carl");
        assertEquals(p_list2[2].getSurname(), "Gauss");

        assertArrayEquals(p_list3, new Person[]{});

        assertEquals(p_list4[0].getName(), "Name");
        assertEquals(p_list4[0].getSurname(), "Surname");
    }

    @Test
    @DisplayName("Тест основного метода parseContacts")
    void testParseContacts() {

        p_list1 = parseContacts(s_list1, "DESC");
        p_list2 = parseContacts(s_list2, "ASC");
        p_list3 = parseContacts(s_list3, "DESC");
        p_list4 = parseContacts(s_list4, "asc");
        p_list5 = parseContacts(s_list5, "ASC");

        assertEquals(p_list1[0].getName(), "John");
        assertEquals(p_list1[0].getSurname(), "Locke");
        assertEquals(p_list1[3].getName(), "Thomas");
        assertEquals(p_list1[3].getSurname(), "Aquinas");
        assertEquals(p_list1[1].getName(), "David");
        assertEquals(p_list1[1].getSurname(), "Hume");
        assertEquals(p_list1[2].getName(), "Rene");
        assertEquals(p_list1[2].getSurname(), "Descartes");

        assertEquals(p_list2[2].getName(), "Paul");
        assertEquals(p_list2[2].getSurname(), "");
        assertEquals(p_list2[1].getName(), "Leonhard");
        assertEquals(p_list2[1].getSurname(), "");
        assertEquals(p_list2[0].getName(), "Carl");
        assertEquals(p_list2[0].getSurname(), "Gauss");

        assertArrayEquals(p_list3, new Person[]{});

        assertArrayEquals(p_list4, new Person[]{});

        assertArrayEquals(p_list5, new Person[]{});
    }
}
