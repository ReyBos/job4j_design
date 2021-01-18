package ru.job4j.ood.lsp;

import java.util.List;

public class PassengerParking implements Parking {
    @Override
    public void park(Car car) {

    }

    @Override
    public boolean canPark(Car car) {
        return false;
    }

    @Override
    public List<Car> getCars() {
        return null;
    }
}
