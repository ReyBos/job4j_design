package ru.job4j.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Config {
    private static final Logger LOG = LoggerFactory.getLogger(Config.class.getName());
    private final String path;
    private final Map<String, String> values;

    public static final String URL = "url";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";

    public Config(String path) {
        this.path = path;
        this.values = new HashMap<>();
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .forEach(s -> {
                        String[] line = s.split("=");
                        if (line.length == 2) {
                            values.put(line[0], line[1]);
                        }
                    });
        } catch (IOException e) {
            LOG.error("Ошибка чтения файла конфигурации", e);
        }
    }

    public String get(String key) {
        return values.get(key);
    }
}
