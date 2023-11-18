package edu.hw6.Task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class DiskMap implements Map<String, String> {

    private final String filePath;

    private final Map<String, String> memoryMap;

    public String getFilePath() {
        return filePath;
    }

    public Map<String, String> getMemoryMap() {
        return memoryMap;
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public DiskMap(String filePath) {
        this.filePath = filePath;
        this.memoryMap = new HashMap<>();
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Entry<String, String> entry : memoryMap.entrySet()) {
                String line = entry.getKey() + ":" + entry.getValue();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            LOGGER.info("Ошибка записи в файл.");
        }
    }

    public void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            while (line != null) {
                int colonIndex = line.indexOf(":");
                String key = line.substring(0, colonIndex);
                String value = line.substring(colonIndex + 1);
                memoryMap.put(key, value);
                line = reader.readLine();
            }
        } catch (IOException e) {
            LOGGER.info("Ошибка чтения файла.");
        }
    }

    @Override
    public int size() {
        return memoryMap.size();
    }

    @Override
    public boolean isEmpty() {
        return memoryMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return memoryMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return memoryMap.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return memoryMap.get(key);
    }

    @Override
    public String put(String key, String value) {
        return memoryMap.put(key, value);
    }

    @Override
    public String remove(Object key) {
        return memoryMap.remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        memoryMap.putAll(m);
    }

    @Override
    public void clear() {
        memoryMap.clear();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return memoryMap.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return memoryMap.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return memoryMap.entrySet();
    }
}
