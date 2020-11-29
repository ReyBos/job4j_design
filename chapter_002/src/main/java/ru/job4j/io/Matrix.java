package ru.job4j.io;

import java.io.FileOutputStream;

public class Matrix {
    public static String doMatrix(int size) {
        StringBuilder rsl = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                rsl.append((i + 1) * (j + 1));
                if (j + 1 != size) {
                    rsl.append(" ");
                }
            }
            rsl.append(System.lineSeparator());
        }
        return rsl.toString();
    }

    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write(doMatrix(10).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
