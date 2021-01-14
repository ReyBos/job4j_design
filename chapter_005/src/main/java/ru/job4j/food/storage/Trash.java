package ru.job4j.food.storage;

import ru.job4j.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Storage {
    private List<Food> products;

    public Trash() {
        this.products = new ArrayList<>();
    }

    @Override
    public boolean accept(Food item) {
        return Double.compare(item.getLivedTimePercent(), 1) > 0;
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