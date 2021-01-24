package ru.job4j.tictactoe;

import java.util.Optional;

public interface Store {
    boolean add(Point point);

    Optional<Point> get(int row, int column);

    Point[][] findAll();

    int getSize();

    int getCapacity();

    int getFieldSize();
}