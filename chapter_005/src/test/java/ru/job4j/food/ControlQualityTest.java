package ru.job4j.food;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.food.storage.Shop;
import ru.job4j.food.storage.Trash;
import ru.job4j.food.storage.Warehouse;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ControlQualityTest {
    ControlQuality controlQuality;

    @Before
    public void init() {
        this.controlQuality = new ControlQuality(List.of(
                new Warehouse(),
                new Shop(),
                new Trash()
        ));
    }

    @Test
    public void whenSortThenWarehouse() {
        Calendar expiryDate = new GregorianCalendar();
        expiryDate.add(Calendar.DAY_OF_MONTH, 1);
        Food item = new Food(
                "Молоко",
                expiryDate,
                new GregorianCalendar(),
                100,
                0
        );
        controlQuality.sort(item);
        List<Food> warehouseFoods = controlQuality.getStorages().get(0).findAll();
        assertThat(warehouseFoods, is(List.of(item)));
    }

    @Test
    public void whenSortThenShop() {
        Calendar expiryDate = new GregorianCalendar();
        expiryDate.add(Calendar.DAY_OF_MONTH, 1);
        Calendar createDate = new GregorianCalendar();
        createDate.add(Calendar.DAY_OF_MONTH, -1);
        Food item = new Food(
                "Молоко",
                expiryDate,
                createDate,
                100,
                0
        );
        controlQuality.sort(item);
        List<Food> shopFoods = controlQuality.getStorages().get(1).findAll();
        assertThat(shopFoods, is(List.of(item)));
    }

    @Test
    public void whenSortThenShopAndDiscount() {
        Calendar expiryDate = new GregorianCalendar();
        expiryDate.add(Calendar.HOUR, 1);
        Calendar createDate = new GregorianCalendar();
        createDate.add(Calendar.DAY_OF_MONTH, -1);
        Food item = new Food(
                "Молоко",
                expiryDate,
                createDate,
                100,
                0
        );
        controlQuality.sort(item);
        List<Food> shopFoods = controlQuality.getStorages().get(1).findAll();
        assertTrue(shopFoods.get(0).getDiscount() > 0);
    }

    @Test
    public void whenSortThenTrash() {
        Calendar expiryDate = new GregorianCalendar();
        expiryDate.add(Calendar.DAY_OF_MONTH, -1);
        Calendar createDate = new GregorianCalendar();
        createDate.add(Calendar.DAY_OF_MONTH, -2);
        Food item = new Food(
                "Молоко",
                expiryDate,
                createDate,
                100,
                0
        );
        controlQuality.sort(item);
        List<Food> trashFoods = controlQuality.getStorages().get(2).findAll();
        assertThat(trashFoods, is(List.of(item)));
    }
}