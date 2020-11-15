package ru.job4j.generics;

public class User extends Base {
    private String name;

    protected User(String id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
