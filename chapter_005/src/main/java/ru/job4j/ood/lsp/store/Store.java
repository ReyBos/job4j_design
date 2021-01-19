package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.car.Car;

import java.util.List;

public interface Store {
    void add(Car item);

    List<Car> findAll();

    boolean contains(Car item);
}
