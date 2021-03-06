package ru.job4j.food.container;

import ru.job4j.food.Food;
import ru.job4j.food.store.Store;

import java.sql.SQLException;
import java.util.List;

public class Warehouse implements Container {
    private final Store store;

    public Warehouse(Store store) {
        this.store = store;
    }

    @Override
    public boolean accept(Food item) {
        return Double.compare(item.getLivedTimePercent(), 0.25) < 0;
    }

    @Override
    public void add(Food item) throws SQLException {
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