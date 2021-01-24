package ru.job4j.tictactoe;

import java.util.Optional;

public class MemStore implements Store {
    private Point[][] points;
    private int size;
    private int capacity;
    private int fieldSize;

    public MemStore(int fieldSize) {
        this.size = 0;
        points = new Point[fieldSize][fieldSize];
        this.capacity = fieldSize * fieldSize;
        this.fieldSize = fieldSize;
    }

    @Override
    public boolean add(Point point) {
        boolean rsl = false;
        if (points[point.getRow()][point.getColumn()] == null) {
            points[point.getRow()][point.getColumn()] = point;
            size++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Optional<Point> get(int row, int column) {
        Optional<Point> rsl = Optional.empty();
        if (points[row][column] != null) {
            rsl = Optional.of(points[row][column]);
        }
        return rsl;
    }

    @Override
    public Point[][] findAll() {
        return points;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getFieldSize() {
        return fieldSize;
    }
}
