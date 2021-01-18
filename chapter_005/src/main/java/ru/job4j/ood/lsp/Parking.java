package ru.job4j.ood.lsp;

import java.util.List;

public interface Parking {
    void park(Car car);

    boolean canPark(Car car);

    List<Car> getCars();
}