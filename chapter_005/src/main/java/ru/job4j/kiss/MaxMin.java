package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> func = integer -> integer < 0;
        return find(value, comparator, func);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> func = integer -> integer > 0;
        return find(value, comparator, func);
    }

    private <T> T find(List<T> value, Comparator<T> comparator, Predicate<Integer> func) {
        if (value.size() == 0) {
            return null;
        }
        T rsl = value.get(0);
        for (T elem : value) {
            if (func.test(comparator.compare(rsl, elem))) {
                rsl = elem;
            }
        }
        return rsl;
    }
}