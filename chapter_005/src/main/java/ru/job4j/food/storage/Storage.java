package ru.job4j.food.storage;

import ru.job4j.food.Food;

import java.util.List;

public interface Storage {
    boolean add(Food item);

    boolean remove(Food item);

    List<Food> findAll();
}
