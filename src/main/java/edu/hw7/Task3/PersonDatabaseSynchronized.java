package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public class PersonDatabaseSynchronized implements PersonDatabase {

    private final Object lock = new Object();
    private final List<Person> persons;
    private final Map<String, List<Person>> nameIndex;
    private final Map<String, List<Person>> addressIndex;
    private final Map<String, List<Person>> phoneIndex;

    public PersonDatabaseSynchronized() {
        this.persons = new ArrayList<>();
        this.nameIndex = new HashMap<>();
        this.addressIndex = new HashMap<>();
        this.phoneIndex = new HashMap<>();
    }

    @Override
    public void add(Person person) {
        synchronized (lock) {
            persons.add(person);
            addToIndex(nameIndex, person.name(), person);
            addToIndex(addressIndex, person.address(), person);
            addToIndex(phoneIndex, person.phoneNumber(), person);
        }
    }

    @Override
    public void delete(int id) {
        synchronized (lock) {
            persons.removeIf(person -> person.id() == id);
            removeFromIndex(nameIndex, id);
            removeFromIndex(addressIndex, id);
            removeFromIndex(phoneIndex, id);
        }
    }

    @Override
    public List<Person> findByName(String name) {
        synchronized (lock) {
            return nameIndex.getOrDefault(name, new ArrayList<>());
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        synchronized (lock) {
            return addressIndex.getOrDefault(address, new ArrayList<>());
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        synchronized (lock) {
            return phoneIndex.getOrDefault(phone, new ArrayList<>());
        }
    }

    private void addToIndex(@NotNull Map<String, List<Person>> index, String key, Person person) {
        index.computeIfAbsent(key, k -> new ArrayList<>()).add(person);
    }

    private void removeFromIndex(@NotNull Map<String, List<Person>> index, int id) {
        index.values().forEach(list -> list.removeIf(person -> person.id() == id));
    }
}
