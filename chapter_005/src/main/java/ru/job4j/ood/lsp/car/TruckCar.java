package ru.job4j.ood.lsp.car;

public class TruckCar implements Car {
    private int size;

    public TruckCar() {
        this.size = 2;
    }

    public TruckCar(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Размер грузовой машины должен быть больше 1");
        }
        this.size = size;
    }

    @Override
    public int getParkingSize() {
        return size;
    }
}
