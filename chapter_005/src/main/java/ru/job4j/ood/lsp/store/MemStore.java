package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.car.Car;

import java.util.ArrayList;
import java.util.List;

public class MemStore implements Store {
    List<Car> cars;

    public MemStore() {
        this.cars = new ArrayList<>();
    }

    @Override
    public void add(Car item) {
        cars.add(item);
    }

    @Override
    public List<Car> findAll() {
        return List.copyOf(cars);
    }

    @Override
    public boolean contains(Car item) {
        return cars.contains(item);
    }
}
