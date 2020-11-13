package ru.job4j.generics;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private int size;
    private Object[] elementData;

    public SimpleArray(int size) {
        this.elementData = new Object[size];
        this.size = 0;
    }

    private void checkIndex(int index) {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        Objects.checkIndex(index, size);
    }

    public void add(T model) {
        if (size == elementData.length) {
            throw new IndexOutOfBoundsException();
        }
        elementData[size++] = model;
    }

    public void set(int index, T model) {
        checkIndex(index);
        elementData[index] = model;
    }

    public void remove(int index) {
        checkIndex(index);
        if (index + 1 == size) {
            elementData[index] = null;
        } else {
            System.arraycopy(elementData, index + 1, elementData, index, (size - index - 1));
            elementData[size - 1] = null;
        }
        size--;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) elementData[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final Object[] data = elementData;
            private final int expectedModCount = size;
            private int point = 0;

            @Override
            public boolean hasNext() {
                return point < expectedModCount;
            }

            @SuppressWarnings("unchecked")
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) data[point++];
            }
        };
    }
}