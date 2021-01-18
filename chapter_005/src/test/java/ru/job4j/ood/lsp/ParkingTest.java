package ru.job4j.ood.lsp;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParkingTest {
//    @Test
    public void whenTruckCarAndTruckParkingThenSuccess() {
        TruckParking parking = new TruckParking();
        TruckCar car = new TruckCar();
        assertTrue(parking.canPark(car));
        parking.park(car);
        TruckCar car2 = new TruckCar();
        assertTrue(parking.canPark(car2));
        parking.park(car2);
        assertThat(parking.getCars(), is(List.of(car, car2)));
    }

//    @Test(expected = IllegalStateException.class)
    public void whenTruckCarAndTruckParkingThenFullParkingError() {
        TruckParking parking = new TruckParking();
        TruckCar car = new TruckCar();
        assertTrue(parking.canPark(car));
        parking.park(car);
        TruckCar car2 = new TruckCar();
        assertFalse(parking.canPark(car2));
        parking.park(car2);
    }

//    @Test(expected = IllegalStateException.class)
    public void whenTruckParkingTwiceThenError() {
        TruckParking parking = new TruckParking();
        TruckCar car = new TruckCar();
        assertTrue(parking.canPark(car));
        parking.park(car);
        assertFalse(parking.canPark(car));
        parking.park(car);
    }

//    @Test(expected = IllegalArgumentException.class)
    public void whenPassengerCarAndTruckParkingThenError() {
        TruckParking parking = new TruckParking();
        PassengerCar car = new PassengerCar();
        assertFalse(parking.canPark(car));
        parking.park(car);
    }

//    @Test
    public void whenTruckAndPassengerCarAndCarParkingThenSuccess() {
        PassengerParking parking = new PassengerParking();
        TruckCar truckCar = new TruckCar();
        assertTrue(parking.canPark(truckCar));
        parking.park(truckCar);
        PassengerCar car = new PassengerCar();
        assertTrue(parking.canPark(car));
        parking.park(car);
        assertThat(parking.getCars(), is(List.of(truckCar, car)));
    }

//    @Test(expected = IllegalStateException.class)
    public void whenPassengerCarAndPassengerParkingThenFullParkingError() {
        PassengerParking parking = new PassengerParking();
        PassengerCar car = new PassengerCar();
        assertTrue(parking.canPark(car));
        parking.park(car);
        PassengerCar car2 = new PassengerCar();
        assertFalse(parking.canPark(car2));
        parking.park(car2);
    }

//    @Test(expected = IllegalStateException.class)
    public void whenPassengerParkingTwiceThenError() {
        PassengerParking parking = new PassengerParking();
        PassengerCar car = new PassengerCar();
        assertTrue(parking.canPark(car));
        parking.park(car);
        assertFalse(parking.canPark(car));
        parking.park(car);
    }
}