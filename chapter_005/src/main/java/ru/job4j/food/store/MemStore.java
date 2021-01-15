package ru.job4j.food.store;

import ru.job4j.food.Food;

import java.util.ArrayList;
import java.util.List;

public class MemStore implements Store {
    private List<Food> foods;

    public MemStore() {
        this.foods = new ArrayList<>();
    }

    @Override
    public void add(Food item) {
        if (!foods.contains(item)) {
            foods.add(item);
        }
    }

    @Override
    public List<Food> findAll() {
        return List.copyOf(foods);
    }

    @Override
    public void close() throws Exception { }
}
