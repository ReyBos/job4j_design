package ru.job4j.food.store;

import ru.job4j.food.Food;

import java.sql.SQLException;
import java.util.List;

public interface Store extends AutoCloseable {
    void add(Food item) throws SQLException;

    List<Food> findAll() throws SQLException;

    List<Food> deleteAll() throws SQLException;
}
