package ru.job4j.food;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.food.container.Shop;
import ru.job4j.food.container.Trash;
import ru.job4j.food.container.Warehouse;
import ru.job4j.food.store.MemStore;

import java.sql.SQLException;
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
                new Warehouse(new MemStore()),
                new Shop(new MemStore()),
                new Trash(new MemStore())
        ));
    }

    @Test
    public void whenSortThenWarehouse() throws SQLException {
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
        List<Food> warehouseFoods = controlQuality.getContainers().get(0).findAll();
        assertThat(warehouseFoods, is(List.of(item)));
    }

    @Test
    public void whenSortThenShop() throws SQLException {
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
        List<Food> shopFoods = controlQuality.getContainers().get(1).findAll();
        assertThat(shopFoods, is(List.of(item)));
    }

    @Test
    public void whenSortThenShopAndDiscount() throws SQLException {
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
        List<Food> shopFoods = controlQuality.getContainers().get(1).findAll();
        assertTrue(shopFoods.get(0).getDiscount() > 0);
    }

    @Test
    public void whenSortThenTrash() throws SQLException {
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
        List<Food> trashFoods = controlQuality.getContainers().get(2).findAll();
        assertThat(trashFoods, is(List.of(item)));
    }

    @Test
    public void whenSortAndResort() throws SQLException {
        Calendar expiryDate = new GregorianCalendar();
        expiryDate.add(Calendar.DAY_OF_MONTH, 1);
        Calendar createDate = new GregorianCalendar();
        Food item = new Food(
                "Склад",
                expiryDate,
                createDate,
                100,
                0
        );
        controlQuality.sort(item);
        Calendar expiryDate2 = new GregorianCalendar();
        expiryDate2.add(Calendar.DAY_OF_MONTH, 1);
        Calendar createDate2 = new GregorianCalendar();
        createDate2.add(Calendar.DAY_OF_MONTH, -1);
        Food item2 = new Food(
                "Магазин",
                expiryDate2,
                createDate2,
                100,
                0
        );
        controlQuality.sort(item2);
        Calendar expiryDate3 = new GregorianCalendar();
        expiryDate3.add(Calendar.DAY_OF_MONTH, -1);
        Calendar createDate3 = new GregorianCalendar();
        createDate3.add(Calendar.DAY_OF_MONTH, -2);
        Food item3 = new Food(
                "Мусорка",
                expiryDate3,
                createDate3,
                100,
                0
        );
        controlQuality.sort(item3);
        List<Food> warehouseFoods = controlQuality.getContainers().get(0).findAll();
        assertThat(warehouseFoods, is(List.of(item)));
        List<Food> shopFoods = controlQuality.getContainers().get(1).findAll();
        assertThat(shopFoods, is(List.of(item2)));
        List<Food> trashFoods = controlQuality.getContainers().get(2).findAll();
        assertThat(trashFoods, is(List.of(item3)));
        item.setExpiryDate(expiryDate2);
        item.setCreateDate(createDate2);
        item2.setExpiryDate(expiryDate3);
        item2.setCreateDate(createDate3);
        item3.setExpiryDate(expiryDate);
        item3.setCreateDate(createDate);
        controlQuality.resort();
        warehouseFoods = controlQuality.getContainers().get(0).findAll();
        assertThat(warehouseFoods, is(List.of(item3)));
        shopFoods = controlQuality.getContainers().get(1).findAll();
        assertThat(shopFoods, is(List.of(item)));
        trashFoods = controlQuality.getContainers().get(2).findAll();
        assertThat(trashFoods, is(List.of(item2)));
    }
}