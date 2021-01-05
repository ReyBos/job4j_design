package ru.job4j.cache;

import java.io.IOException;

public class CacheDemo {
    public static void main(String[] args) throws IOException {
        Cache cache = new Cache("./chapter_004/src/main/resources");
        System.out.println(cache.get("Names.txt"));
        System.out.println(cache.get("Address.txt"));
    }
}
