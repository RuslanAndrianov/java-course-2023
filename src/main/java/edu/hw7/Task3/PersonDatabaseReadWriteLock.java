package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PersonDatabaseReadWriteLock implements PersonDatabase {

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final List<Person> persons;
    private final Map<String, List<Person>> nameIndex;
    private final Map<String, List<Person>> addressIndex;
    private final Map<String, List<Person>> phoneIndex;

    public PersonDatabaseReadWriteLock() {
        this.persons = new ArrayList<>();
        this.nameIndex = new HashMap<>();
        this.addressIndex = new HashMap<>();
        this.phoneIndex = new HashMap<>();
    }

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        try {
            persons.add(person);
            addToIndex(nameIndex, person.name(), person);
            addToIndex(addressIndex, person.address(), person);
            addToIndex(phoneIndex, person.phoneNumber(), person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        try {
            persons.removeIf(person -> person.id() == id);
            removeFromIndex(nameIndex, id);
            removeFromIndex(addressIndex, id);
            removeFromIndex(phoneIndex, id);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        lock.readLock().lock();
        try {
            return nameIndex.getOrDefault(name, new ArrayList<>());
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        lock.readLock().lock();
        try {
            return addressIndex.getOrDefault(address, new ArrayList<>());
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        lock.readLock().lock();
        try {
            return phoneIndex.getOrDefault(phone, new ArrayList<>());
        } finally {
            lock.readLock().unlock();
        }
    }

    // Вспомогательные методы для работы с индексами
    private void addToIndex(Map<String, List<Person>> index, String key, Person person) {
        List<Person> list = index.computeIfAbsent(key, k -> new ArrayList<>());
        synchronized (list) {
            list.add(person);
        }
    }

    private void removeFromIndex(Map<String, List<Person>> index, int id) {
        index.values().forEach(list -> {
            synchronized (list) {
                list.removeIf(person -> person.id() == id);
            }
        });
    }
}
