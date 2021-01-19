package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.car.Car;
import ru.job4j.ood.lsp.store.Store;

import java.util.List;

public class TruckParking implements Parking {
    private Store store;
    private int capacity;
    private int fullness;

    public TruckParking(Store store, int capacity) {
        this.store = store;
        this.capacity = capacity;
        this.fullness = 0;
    }

    @Override
    public void park(Car car) {
        if (!canPark(car)) {
            if (car.getParkingSize() == 1) {
                throw new IllegalArgumentException("Нельзя парковать легковушку на грузовой стоянке");
            }
            throw new IllegalStateException("Невозможно припарковать машину");
        }
        store.add(car);
        fullness += car.getParkingSize();
    }

    @Override
    public boolean canPark(Car car) {
        return car.getParkingSize() != 1
                && (capacity - fullness - car.getParkingSize()) >= 0
                && !store.contains(car);
    }

    @Override
    public List<Car> getCars() {
        return store.findAll();
    }
}