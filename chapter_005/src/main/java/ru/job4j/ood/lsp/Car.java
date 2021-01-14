package ru.job4j.ood.lsp;

public interface Car {
    enum CarType {
        PASSENGER,
        TRUCK
    }

    int getParkingSize();

    Car.CarType getCarType();
}
