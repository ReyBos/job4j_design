package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

public class Analizy {
    public void unavailable(String source, String target) {
        try (
                BufferedReader in = new BufferedReader(new FileReader(source));
                PrintWriter out = new PrintWriter(new FileOutputStream(target))
        ) {
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
                            String rsl = stack.pop() + ";" + line[1] + ";";
                            out.println(rsl);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
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