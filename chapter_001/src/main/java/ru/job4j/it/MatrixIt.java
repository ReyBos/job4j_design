package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
        this.row = findNextRow(0);
    }

    private int findNextRow(int startRow) {
        int rsl = -1;
        for (int i = startRow; i < data.length; i++) {
            if (data[i].length > 0) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }

    @Override
    public boolean hasNext() {
        return row != -1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer elem = data[row][column++];
        if (column == data[row].length) {
            row = findNextRow(++row);
            column = 0;
        }
        return elem;
    }
}