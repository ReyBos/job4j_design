package ru.job4j.hash;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + '}';
    }

    public static void main(String[] args) {
        Map<User, Object> storage = new HashMap<>();
        User user1 = new User(
                "Andrew",
                0,
                new GregorianCalendar(1991, Calendar.APRIL, 14)
        );
        User user2 = new User(
                "Andrew",
                0,
                new GregorianCalendar(1991, Calendar.APRIL, 14)
        );
        storage.put(user1, user1);
        storage.put(user2, user2);
        for (User key : storage.keySet()) {
            System.out.println(key + " - " + storage.get(key));
        }
    }
}
