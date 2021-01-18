package ru.job4j.food.container;

import ru.job4j.food.Food;
import ru.job4j.food.store.Store;

import java.sql.SQLException;
import java.util.List;

public class Shop implements Container {
    private final Store store;

    public Shop(Store store) {
        this.store = store;
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
    public void add(Food item) throws SQLException {
        checkAddingDiscount(item);
        store.add(item);
    }

    @Override
    public List<Food> findAll() throws SQLException {
        return store.findAll();
    }

    @Override
    public List<Food> deleteAll() throws SQLException {
        return store.deleteAll();
    }
}