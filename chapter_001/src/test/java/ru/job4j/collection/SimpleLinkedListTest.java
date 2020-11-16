package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleLinkedListTest {
    @Test
    public void whenAddAndGetSuccess() {
        SimpleLinkedList<String> lst = new SimpleLinkedList<>();
        lst.add("first");
        lst.add("second");
        lst.add("third");
        assertThat(lst.get(0), is("first"));
        assertThat(lst.get(1), is("second"));
        assertThat(lst.get(2), is("third"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetError() {
        SimpleLinkedList<String> lst = new SimpleLinkedList<>();
        lst.get(0);
    }

    @Test
    public void whenAddThenIt() {
        SimpleLinkedList<String> lst = new SimpleLinkedList<>();
        lst.add("first");
        String rsl = lst.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        SimpleLinkedList<String> lst = new SimpleLinkedList<>();
        lst.add("first");
        lst.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleLinkedList<String> lst = new SimpleLinkedList<>();
        lst.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleLinkedList<String> lst = new SimpleLinkedList<>();
        lst.add("first");
        Iterator<String> it = lst.iterator();
        lst.add("second");
        it.next();
    }
}