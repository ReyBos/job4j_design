package ru.job4j.ood.srp;

import java.io.FileOutputStream;

public class SimpleMatrix implements Matrix {
    // первая проблема что мы возвращаем тут строку, как-то работать с этим дальше будет неудобно
    // лучше возвращать массив
    @Override
    public String doMatrix(int size) {
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

    // ну вторая проблема что функционал раздваивается: создать таблицу умножения и сохранить ее
    // и третья проблема: источник для сохранения сохраняется прямо в методе
    @Override
    public void saveMatrix(String matrix) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write(matrix.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
