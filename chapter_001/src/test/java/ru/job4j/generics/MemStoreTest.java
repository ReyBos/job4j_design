package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

public class MemStoreTest {
    private MemStore<User> store;

    @Before
    public void setUp() {
        store = new MemStore<>();
        store.add(new User("1", "test"));
    }

    @Test
    public void replace() {
        store.replace("1", new User("2", "new"));
        assertThat(store.findById("2").getName(), is("new"));
    }

    @Test
    public void delete() {
        MemStore<User> store = new MemStore<>();
        store.add(new User("1", "test"));
        store.delete("1");
        assertNull(store.findById("1"));
    }
}