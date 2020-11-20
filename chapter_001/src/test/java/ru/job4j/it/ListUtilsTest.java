package ru.job4j.it;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {
    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 0, 2);
        assertThat(Arrays.asList(2, 1, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 1, 2);
        assertThat(Arrays.asList(1, 3, 2), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 2, 2);
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(-1, 1, 3, -1));
        List<Integer> out = ListUtils.removeIf(input, num -> num < 0);
        assertThat(Arrays.asList(1, 3), Is.is(out));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(-1, 1, 3, -1));
        List<Integer> out = ListUtils.replaceIf(input, num -> num < 0, 0);
        assertThat(Arrays.asList(0, 1, 3, 0), Is.is(out));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(-1, 1, 3, -1, 2));
        List<Integer> elements = new ArrayList<>(Arrays.asList(-1, 2));
        List<Integer> out = ListUtils.removeAll(input, elements);
        assertThat(Arrays.asList(1, 3), Is.is(out));
    }
}