package ru.job4j.cache;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CacheTest {
    @Test
    public void get() throws IOException {
        Cache cache = new Cache("./src/main/resources");
        String out = cache.get("Address.txt");
        String expected = "Москва" + System.lineSeparator() + "Краснодар";
        assertEquals(out, expected);
    }

    @Test(expected = IOException.class)
    public void whenGetError() throws IOException {
        Cache cache = new Cache("./src/main/resources");
        String out = cache.get("Names");
    }
}