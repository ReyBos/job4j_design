package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Cache {
    private SoftReference<Map<String, String>> storageLink;
    private String root;

    public Cache(String root) {
        this.storageLink = new SoftReference<>(new HashMap<>());
        this.root = root;
    }

    public String get(String fileName) throws IOException {
        Map<String, String> storage = storageLink.get();
        if (storage == null) {
            storageLink = new SoftReference<>(new HashMap<>());
            storage = storageLink.get();
        }
        return storage.get(fileName) != null ? storage.get(fileName) : load(fileName);
    }

    private String load(String fileName) throws IOException {
        String content;
        try (BufferedReader reader = new BufferedReader(new FileReader(root + "/" + fileName))) {
            content = reader.lines().
                    collect(Collectors.joining(System.lineSeparator()));
            storageLink.get().put(fileName, content);
        }
        return content;
    }
}
