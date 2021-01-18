package ru.job4j.food.container;

import ru.job4j.food.Food;

import java.sql.SQLException;
import java.util.List;

public interface Container {
    boolean accept(Food item);

    void add(Food item) throws SQLException;

    List<Food> findAll() throws SQLException;

    List<Food> deleteAll() throws SQLException;
}
