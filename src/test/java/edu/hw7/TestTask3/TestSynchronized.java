package edu.hw7.TestTask3;

import edu.hw7.Task3.Person;
import edu.hw7.Task3.PersonDatabaseSynchronized;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSynchronized {

    private final PersonDatabaseSynchronized dbSync = new PersonDatabaseSynchronized();
    {
        dbSync.add(new Person(1, "John", "123 Main St", "123-456-7890"));
        dbSync.add(new Person(2, "Jane", "456 Elm St", "987-654-3210"));
        dbSync.add(new Person(3, "John", "789 Oak St", "456-789-1230"));
    }

    @Test
    @DisplayName("Тест add")
    public void testAdd() {
        Person person = new Person(4, "Alice", "321 Pine St", "111-222-3333");
        dbSync.add(person);

        List<Person> results = dbSync.findByName("Alice");
        assertEquals(1, results.size());
        assertEquals(person, results.get(0));
    }

    @Test
    @DisplayName("Тест delete")
    public void testDelete() {
        dbSync.delete(2);

        List<Person> results = dbSync.findByName("Jane");
        assertEquals(0, results.size());
    }

    @Test
    @DisplayName("Тест findByName")
    public void testFindByName() {
        List<Person> results = dbSync.findByName("John");
        assertEquals(2, results.size());
    }

    @Test
    @DisplayName("Тест findByAddress")
    public void testFindByAddress() {
        List<Person> results = dbSync.findByAddress("123 Main St");
        assertEquals(1, results.size());
        assertEquals("John", results.get(0).name());
    }

    @Test
    @DisplayName("Тест findByPhone")
    public void testFindByPhone() {
        List<Person> results = dbSync.findByPhone("456-789-1230");
        assertEquals(1, results.size());
        assertEquals("John", results.get(0).name());
    }

    @Test
    @DisplayName("Тест многопотокового поведения")
    public void multiThread() {

        Thread addThread = new Thread(() -> {
            Person person = new Person(5, "Ruslan", "Address", "111-111-1111");
            dbSync.add(person);
        });

        Thread findThread = new Thread(() -> {
            String name = "Ruslan";
            String address = "Address";
            String phone = "111-111-1111";

            List<Person> nameGet = dbSync.findByName(name);
            List<Person> phoneGet = dbSync.findByPhone(phone);
            List<Person> addressGet = dbSync.findByAddress(address);

            if (nameGet.isEmpty()) {
                assertTrue(phoneGet.isEmpty());
                assertTrue(addressGet.isEmpty());
            } else {
                assertEquals("Ruslan", nameGet.get(0).name());
                assertEquals("Address", nameGet.get(0).address());
                assertEquals("111-111-1111", nameGet.get(0).phoneNumber());
            }
        });

        addThread.start();
        findThread.start();

        try {
            addThread.join();
            findThread.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception!");
        }
    }
}
