package edu.hw10.Task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"MultipleStringLiterals", "RegexpSinglelineJava"})
public class CacheProxy implements InvocationHandler {
    private final Object target;
    private final Map<String, Object> cache;
    private final boolean persistCache;

    private CacheProxy(Object target, boolean persistCache) {
        this.target = target;
        this.cache = new HashMap<>();
        this.persistCache = persistCache;
    }

    public static @NotNull <T> T create(T target, @NotNull Class<T> targetInterface) {
        boolean persistCache = false;
        for (Method method : targetInterface.getMethods()) {
            if (method.getAnnotation(Cache.class) != null) {
                persistCache = method.getAnnotation(Cache.class).persist();
            }
        }
        return (T) Proxy.newProxyInstance(
            targetInterface.getClassLoader(),
            new Class<?>[] {targetInterface},
            new CacheProxy(target, persistCache)
        );
    }

    @Override
    public Object invoke(Object proxy, @NotNull Method method, Object[] args) throws Throwable {

        Cache cacheAnnotation = method.getAnnotation(Cache.class);
        String cacheKey = generateCacheKey(method, args);
        Object result = method.invoke(target, args);

        if (cacheAnnotation == null) {
            return result;
        }

        if (persistCache) {
            persistCacheResult(cacheKey, result);
            String cacheFileStrPath = System.getProperty("user.dir") + "\\" + cacheKey + "\\cache.txt";
            if (Files.exists(Path.of(cacheFileStrPath))) {
                try (BufferedReader reader = new BufferedReader(new FileReader(cacheFileStrPath))) {
                    System.out.println("Результат взят из файла");
                    return castToObject(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (cache.containsKey(cacheKey)) {
                System.out.println("Результат взят из внутреннего кэша");
                return cache.get(cacheKey);
            }
            cache.put(cacheKey, result);
            System.out.println("Результат записан во внутренний кэш");
        }

        return result;
    }

    private Object castToObject(String fileContent) {
        Object result;
        try {
            result = Long.parseLong(fileContent);
            return result;
        } catch (NumberFormatException ignored) { }
        try {
            result = Double.parseDouble(fileContent);
            return result;
        } catch (NumberFormatException ignored) { }
        result = fileContent;
        return result;
    }

    private @NotNull String generateCacheKey(@NotNull Method method, Object @NotNull [] args) {
        StringBuilder sb = new StringBuilder();
        sb.append(method.getName());
        for (Object arg : args) {
            sb.append('_');
            sb.append(arg);
        }
        return sb.toString();
    }

    private void persistCacheResult(String cacheKey, @NotNull Object result) {
        String tempDirStrPath = System.getProperty("user.dir") + "\\" + cacheKey;
        String cacheFileStrPath = tempDirStrPath + "\\cache.txt";
        Path tempDirPath = Path.of(tempDirStrPath);
        Path cacheFilePath = Path.of(cacheFileStrPath);
        try {
            if (!Files.exists(tempDirPath)) {
                Files.createDirectory(tempDirPath);
            }
            if (!Files.exists(cacheFilePath)) {
                Files.createFile(cacheFilePath);
                try (FileWriter writer = new FileWriter(cacheFileStrPath)) {
                    writer.write(result.toString());
                    System.out.println("Результат сохранен в файл по адресу: " + cacheFileStrPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
