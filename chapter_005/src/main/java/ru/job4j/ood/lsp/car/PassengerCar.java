package ru.job4j.ood.lsp.car;

public class PassengerCar implements Car {
    private int size;

    public PassengerCar() {
        this.size = 1;
    }

    @Override
    public int getParkingSize() {
        return size;
    }
}
