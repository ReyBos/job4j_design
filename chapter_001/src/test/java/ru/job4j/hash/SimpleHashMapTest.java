package ru.job4j.hash;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {
    @Test
    public void whenInsertThenGet() {
        SimpleHashMap<Integer, Integer> map = new SimpleHashMap<>();
        for (int i = 0; i < 5; i++) {
            map.insert(i, i);
        }
        for (int i = 0; i < 5; i++) {
            assertThat(map.get(i), is(i));
        }
    }

    @Test
    public void whenInsertThenDelete() {
        SimpleHashMap<Integer, Integer> map = new SimpleHashMap<>();
        for (int i = 0; i < 5; i++) {
            map.insert(i, i);
        }
        assertTrue(map.delete(0));
        assertFalse(map.delete(0));
        assertNull(map.get(0));
        for (int i = 1; i < 5; i++) {
            assertThat(map.get(i), is(i));
        }
    }

    @Test
    public void whenResizeTable() {
        SimpleHashMap<Integer, Integer> map = new SimpleHashMap<>();
        for (int i = 0; i < 50; i++) {
            map.insert(i, i);
        }
        for (int i = 0; i < 50; i++) {
            assertThat(map.get(i), is(i));
        }
    }

    @Test
    public void whenIterator() {
        SimpleHashMap<Integer, Integer> map = new SimpleHashMap<>();
        map.insert(5, 5);
        map.insert(10, 10);
        Iterator<SimpleHashMap.Node<Integer, Integer>> it = map.iterator();
        assertThat(it.next().getVal(), is(5));
        assertThat(it.next().getVal(), is(10));
    }

    @Test
    public void whenInsertThenReplace() {
        SimpleHashMap<Integer, Integer> map = new SimpleHashMap<>();
        map.insert(5, 5);
        map.insert(5, 10);
        Iterator<SimpleHashMap.Node<Integer, Integer>> it = map.iterator();
        assertThat(it.next().getVal(), is(10));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorHasNextException() {
        SimpleHashMap<Integer, Integer> map = new SimpleHashMap<>();
        map.insert(5, 5);
        Iterator<SimpleHashMap.Node<Integer, Integer>> it = map.iterator();
        map.insert(5, 10);
        it.hasNext();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorNextException() {
        SimpleHashMap<Integer, Integer> map = new SimpleHashMap<>();
        map.insert(5, 5);
        Iterator<SimpleHashMap.Node<Integer, Integer>> it = map.iterator();
        it.next();
        it.next();
    }
}