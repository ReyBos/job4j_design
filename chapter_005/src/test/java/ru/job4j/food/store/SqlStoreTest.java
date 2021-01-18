package ru.job4j.food.store;

import org.junit.Test;
import ru.job4j.food.ConnectionRollback;
import ru.job4j.food.Food;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SqlStoreTest {
    public Connection init() {
        try (InputStream in = SqlStore.class
                .getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void whenAddThenFindAll() throws Exception {
        try (SqlStore store = new SqlStore(ConnectionRollback.create(this.init()))) {
            Calendar expiryDate = new GregorianCalendar();
            expiryDate.add(Calendar.HOUR, 1);
            Calendar createDate = new GregorianCalendar();
            Food item = new Food(
                    "milk", expiryDate, createDate, 1.0, 0
            );
            store.add(item);
            assertThat(store.findAll().get(0), is(item));
        }
    }

    @Test
    public void whenAddThenDelete() throws Exception {
        try (SqlStore store = new SqlStore(ConnectionRollback.create(this.init()))) {
            Calendar expiryDate = new GregorianCalendar();
            expiryDate.add(Calendar.HOUR, 1);
            Calendar createDate = new GregorianCalendar();
            Food item = new Food(
                    "milk", expiryDate, createDate, 1.0, 0
            );
            store.add(item);
            assertThat(store.deleteAll().get(0), is(item));
            assertThat(store.findAll(), is(List.of()));
        }
    }
}