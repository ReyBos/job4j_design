package ru.job4j.hash;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Node<K, V>> {
    private Node<K, V>[] storage;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 1 << 4;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    @SuppressWarnings({"unchecked"})
    public SimpleHashMap() {
        this.storage = (Node<K, V>[]) new Node[DEFAULT_CAPACITY];
    }

    private int getIndex(K key) {
        int hashCode = (key == null) ? 0 : key.hashCode();
        int hash = (hashCode == 0) ? hashCode : hashCode ^ (hashCode >>> 16);
        return hash & (storage.length - 1);
    }

    @SuppressWarnings({"unchecked"})
    private void resizeTable() {
        Node<K, V>[] oldStorage = storage;
        Iterator<Node<K, V>> it = iterator();
        storage = (Node<K, V>[]) new Node[oldStorage.length << 1];
        size = 0;
        while (it.hasNext()) {
            Node<K, V> next = it.next();
            insert(next.getKey(), next.getVal());
        }
    }

    public boolean insert(K key, V val) {
        if ((float) size / storage.length >= DEFAULT_LOAD_FACTOR) {
            resizeTable();
        }
        int index = getIndex(key);
        if (storage[index] == null) {
            storage[index] = new Node<>(key, val);
            size++;
            return true;
        }
        Node<K, V> currElem = storage[index];
        if (!currElem.getKey().equals(key)) {
            return false;
        }
        currElem.setVal(val);
        return true;
    }

    public V get(K key) {
        int index = getIndex(key);
        return storage[index] != null ? storage[index].getVal() : null;
    }

    public boolean delete(K key) {
        int index = getIndex(key);
        if (storage[index] == null) {
            return false;
        }
        storage[index] = null;
        size--;
        return true;
    }

    @Override
    public Iterator<Node<K, V>> iterator() {
        return new Iterator<>() {
            private final Node<K, V>[] data = storage;
            private int point = 0;

            @Override
            public boolean hasNext() {
                for (int i = point; i < data.length; i++) {
                    if (data[i] != null) {
                        point = i;
                        return true;
                    }
                }
                return false;
            }

            @Override
            public Node<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return data[point++];
            }
        };
    }

    public static class Node<K, V> {
        private final K key;
        private V val;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public K getKey() {
            return key;
        }

        public V getVal() {
            return val;
        }

        public void setVal(V val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return key + " -> " + val;
        }
    }
}
