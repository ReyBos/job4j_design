package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Cache {
    private final Map<String, SoftReference<String>> storage;
    private final String root;

    public Cache(String root) {
        this.storage = new HashMap<>();
        this.root = root;
    }

    public String get(String fileName) throws IOException {
        SoftReference<String> file = storage.get(fileName);
        if (file == null || file.get() == null) {
            return load(fileName);
        }
        String rsl = file.get();
        if (rsl == null) {
            rsl = load(fileName);
        }
        return rsl;
    }

    private String load(String fileName) throws IOException {
        String content;
        try (BufferedReader reader = new BufferedReader(new FileReader(root + "/" + fileName))) {
            content = reader.lines().
                    collect(Collectors.joining(System.lineSeparator()));
            storage.put(fileName, new SoftReference<>(content));
        }
        return content;
    }
}