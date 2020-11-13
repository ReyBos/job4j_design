package ru.job4j.generics;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {
    @Test
    public void whenAddAndGetSuccess() {
        SimpleArray<Integer> test = new SimpleArray<>(5);
        test.add(1);
        test.add(2);
        assertThat(test.get(0), is(1));
        assertThat(test.get(1), is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddException() {
        SimpleArray<Integer> test = new SimpleArray<>(1);
        test.add(1);
        test.add(2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetException() {
        SimpleArray<Integer> test = new SimpleArray<>(5);
        test.get(0);
    }

    @Test
    public void whenSetSuccess() {
        SimpleArray<Integer> test = new SimpleArray<>(5);
        test.add(1);
        test.set(0, 2);
        assertThat(test.get(0), is(2));
    }

    @Test
    public void whenRemoveMiddleElem() {
        SimpleArray<Integer> test = new SimpleArray<>(5);
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
        test.remove(1);
        assertThat(test.get(0), is(1));
        assertThat(test.get(1), is(3));
        assertThat(test.get(2), is(4));
    }

    @Test
    public void whenRemoveSingleEelement() {
        SimpleArray<Integer> test = new SimpleArray<>(5);
        test.add(1);
        test.remove(0);
        Iterator<Integer> it = test.iterator();
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenMultiCallHasNextThenTrue() {
        SimpleArray<Integer> test = new SimpleArray<>(5);
        test.add(1);
        test.add(1);
        Iterator<Integer> it = test.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenReadSequence() {
        SimpleArray<Integer> test = new SimpleArray<>(5);
        test.add(1);
        test.add(2);
        Iterator<Integer> it = test.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
        SimpleArray<Integer> test = new SimpleArray<>(5);
        test.add(1);
        Iterator<Integer> it = test.iterator();
        it.next();
        it.next();
    }
}