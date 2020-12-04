package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String rsl = values.get(key);
        if (rsl == null) {
            throw new IllegalArgumentException("Не передан аргумент: -" + key);
        }
        return rsl;
    }

    private void parse(String[] args) {
        for (String arg : args) {
            String[] line = arg.split("=");
            if (line.length != 2) {
                throw new IllegalArgumentException(
                        "Передайте аргумент в виде ключ=значение (" + arg + ")"
                );
            }
            values.put(line[0].replaceFirst("-", ""), line[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}