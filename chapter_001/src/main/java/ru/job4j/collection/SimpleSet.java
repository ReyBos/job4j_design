package ru.job4j.collection;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable {
    private SimpleArray<E> data = new SimpleArray<>();

    public void add(E e) {
        if (!data.contains(e)) {
            data.add(e);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return data.iterator();
    }
}
