package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStoreTest {
    private UserStore store;

    @Before
    public void setUp() {
        store = new UserStore();
        store.add(new User("1", "test"));
    }

    @Test
    public void replace() {
        store.replace("1", new User("2", "new"));
        assertThat(store.findById("2").getName(), is("new"));
    }

    @Test
    public void delete() {
        store.delete("1");
        assertNull(store.findById("1"));
    }

    @Test
    public void findById() {
        assertThat(store.findById("1").getName(), is("test"));
    }
}