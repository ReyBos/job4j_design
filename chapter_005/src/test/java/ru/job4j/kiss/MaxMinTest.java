package ru.job4j.kiss;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class MaxMinTest {
    @Test
    public void max() {
        MaxMin maxMin = new MaxMin();
        List<String> input = List.of("Andrew", "Ivan", "Zina");
        String rsl = maxMin.max(input, Comparator.naturalOrder());
        String expected = "Zina";
        assertEquals(expected, rsl);
    }

    @Test
    public void min() {
        MaxMin maxMin = new MaxMin();
        List<String> input = List.of("Andrew", "Ivan", "Zina");
        String rsl = maxMin.max(input, Comparator.reverseOrder());
        String expected = "Andrew";
        assertEquals(expected, rsl);
    }
}