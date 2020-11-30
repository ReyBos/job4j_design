package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    private List<String> rsl;

    public Analizy() {
        this.rsl = new ArrayList<>();
    }

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            Stack stack = new Stack();
            in.lines()
                    .forEach(s -> {
                        String[] line = s.split(" ");
                        if (
                                (line[0].equals("400") || line[0].equals("500"))
                                && stack.size() == 0
                        ) {
                            stack.push(line[1]);
                        }
                        if (
                                (line[0].equals("200") || line[0].equals("300"))
                                && stack.size() == 1
                        ) {
                            String start = stack.pop();
                            String end = line[1];
                            rsl.add(start + ";" + end + ";");
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            for (String str : rsl) {
                out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getRsl() {
        return rsl;
    }

    private class Stack {
        private String[] storage;
        private int point;

        public Stack() {
            storage = new String[1];
            point = 0;
        }

        public void push(String str) {
            storage[point++] = str;
        }

        public String pop() {
            return storage[--point];
        }

        public int size() {
            return point;
        }
    }
}