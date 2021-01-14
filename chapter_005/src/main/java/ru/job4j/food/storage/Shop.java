package ru.job4j.food.storage;

import ru.job4j.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage {
    private List<Food> products;

    public Shop() {
        this.products = new ArrayList<>();
    }

    @Override
    public boolean accept(Food item) {
        return Double.compare(item.getLivedTimePercent(), 0.25) >= 0 && Double.compare(item.getLivedTimePercent(), 1) < 0;
    }

    private void checkAddingDiscount(Food item) {
        if (Double.compare(item.getLivedTimePercent(), 0.75) > 0) {
            item.setDiscount(0.75);
        }
    }

    @Override
    public boolean add(Food item) {
        checkAddingDiscount(item);
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