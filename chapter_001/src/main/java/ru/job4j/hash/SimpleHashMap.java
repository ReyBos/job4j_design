package ru.job4j.hash;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Node<K, V>> {
    private Node<K, V>[] storage;
    private int size = 0;
    private int modCount = 0;
    private static final int DEFAULT_CAPACITY = 1 << 4;
    private static final int MAXIMUM_CAPACITY = 1 << 30;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    @SuppressWarnings({"unchecked"})
    public SimpleHashMap() {
        this.storage = (Node<K, V>[]) new Node[DEFAULT_CAPACITY];
    }

    @SuppressWarnings({"unchecked"})
    public SimpleHashMap(int capacity) {
        int tableSize = tableSizeFor(capacity);
        this.storage = (Node<K, V>[]) new Node[tableSize];
    }

    static int tableSizeFor(int capacity) {
        int n = -1 >>> Integer.numberOfLeadingZeros(capacity - 1);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    private int getIndex(K key) {
        int hashCode = (key == null) ? 0 : key.hashCode();
        int hash = (hashCode == 0) ? hashCode : hashCode ^ (hashCode >>> 16);
        return hash & (storage.length - 1);
    }

    private void resizeTable() {
        SimpleHashMap<K, V> newMap = new SimpleHashMap<>(storage.length << 1);
        Iterator<Node<K, V>> it = iterator();
        while (it.hasNext()) {
            Node<K, V> next = it.next();
            newMap.insert(next.getKey(), next.getVal());
        }
        storage = newMap.getStorage();
        size = newMap.getSize();
        modCount = newMap.getModCount();
    }

    public boolean insert(K key, V val) {
        if ((float) size / storage.length >= DEFAULT_LOAD_FACTOR) {
            resizeTable();
        }
        int index = getIndex(key);
        if (storage[index] == null) {
            storage[index] = new Node<>(key, val);
            size++;
            modCount++;
            return true;
        }
        Node<K, V> currElem = storage[index];
        if (!currElem.getKey().equals(key)) {
            return false;
        }
        currElem.setVal(val);
        modCount++;
        return true;
    }

    public V get(K key) {
        int index = getIndex(key);
        if (storage[index] == null || !storage[index].getKey().equals(key)) {
            return null;
        }
        return storage[index].getVal();
    }

    public boolean delete(K key) {
        int index = getIndex(key);
        if (storage[index] == null || !storage[index].getKey().equals(key)) {
            return false;
        }
        storage[index] = null;
        size--;
        modCount++;
        return true;
    }

    public Node<K, V>[] getStorage() {
        return storage;
    }

    public int getSize() {
        return size;
    }

    public int getModCount() {
        return modCount;
    }

    @Override
    public Iterator<Node<K, V>> iterator() {
        return new Iterator<>() {
            private final Node<K, V>[] data = storage;
            private int point = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
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
