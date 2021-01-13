package ru.job4j.food.storage;

import ru.job4j.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Storage {
    private List<Food> products;

    public Warehouse() {
        this.products = new ArrayList<>();
    }

    @Override
    public boolean add(Food item) {
        if (products.contains(item)) {
            return true;
        }
        return products.add(item);
    }

    @Override
    public boolean remove(Food item) {
        return products.remove(item);
    }

    @Override
    public List<Food> findAll() {
        return List.copyOf(products);
    }
}