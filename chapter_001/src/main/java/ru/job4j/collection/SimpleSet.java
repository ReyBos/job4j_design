package ru.job4j.collection;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable {
    private SimpleArray<E> data = new SimpleArray<>();

    public void add(E e) {
        if (!contains(e)) {
            data.add(e);
        }
    }

    public boolean contains(E elem) {
        return data.contains(elem);
    }

    @Override
    public Iterator<E> iterator() {
        return data.iterator();
    }
}
