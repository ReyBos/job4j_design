package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator<Integer> {
    private final int[] data;
    private int point;

    public EvenIterator(final int[] numbers) {
        data = numbers;
        point = findNext(0);
    }

    private int findNext(int start) {
        int rsl = -1;
        for (int i = start; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }

    @Override
    public boolean hasNext() {
        return point != -1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer elem = data[point++];
        point = findNext(point);
        return elem;
    }
}
