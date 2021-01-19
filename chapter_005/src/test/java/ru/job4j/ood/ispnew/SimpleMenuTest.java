package ru.job4j.ood.ispnew;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleMenuTest {
    @Test
    public void whenOnlyRootsMenuOrdered() {
        SimpleMenu menu = new SimpleMenu("Пункт", new DemoAction());
        menu.addRoot("Пункт 2", new DemoAction());
        menu.addRoot("Выход", new ExitAction());
        String expected = "1. Пункт" + System.lineSeparator()
                + "2. Пункт 2" + System.lineSeparator()
                + "3. Выход" + System.lineSeparator();
        assertThat(menu.ordered(), is(expected));
    }

    @Test
    public void whenDifficultyMenuOrdered() {
        SimpleMenu menu = new SimpleMenu("Пункт", new DemoAction());
        menu.add("Пункт", "Подпункт 1", new DemoAction());
        menu.add("Пункт", "Подпункт 2", new DemoAction());
        menu.add("Подпункт 1", "Подпункт 1.1", new DemoAction());
        menu.addRoot("Пункт 2", new DemoAction());
        menu.addRoot("Выход", new ExitAction());
        String expected = "1. Пункт" + System.lineSeparator()
                + "1.1. Подпункт 1" + System.lineSeparator()
                + "1.1.1. Подпункт 1.1" + System.lineSeparator()
                + "1.2. Подпункт 2" + System.lineSeparator()
                + "2. Пункт 2" + System.lineSeparator()
                + "3. Выход" + System.lineSeparator();
        assertThat(menu.ordered(), is(expected));
    }

    @Test
    public void whenOnlyRootsMenuUnOrdered() {
        SimpleMenu menu = new SimpleMenu("Пункт", new DemoAction());
        menu.addRoot("Пункт 2", new DemoAction());
        menu.addRoot("Выход", new ExitAction());
        String expected = " Пункт" + System.lineSeparator()
                + " Пункт 2" + System.lineSeparator()
                + " Выход" + System.lineSeparator();
        assertThat(menu.unOrdered(), is(expected));
    }

    @Test
    public void whenDifficultyMenuUnOrdered() {
        SimpleMenu menu = new SimpleMenu("Пункт", new DemoAction());
        menu.add("Пункт", "Подпункт 1", new DemoAction());
        menu.add("Пункт", "Подпункт 2", new DemoAction());
        menu.add("Подпункт 1", "Подпункт 1.1", new DemoAction());
        menu.addRoot("Пункт 2", new DemoAction());
        menu.addRoot("Выход", new ExitAction());
        String expected = " Пункт" + System.lineSeparator()
                + "- Подпункт 1" + System.lineSeparator()
                + "-- Подпункт 1.1" + System.lineSeparator()
                + "- Подпункт 2" + System.lineSeparator()
                + " Пункт 2" + System.lineSeparator()
                + " Выход" + System.lineSeparator();
        assertThat(menu.unOrdered(), is(expected));
    }

    @Test
    public void whenAddAndRemove() {
        SimpleMenu menu = new SimpleMenu("Пункт", new DemoAction());
        menu.add("Пункт", "Подпункт", new DemoAction());
        assertTrue(menu.remove("Подпункт"));
        assertTrue(menu.get("Подпункт").isEmpty());
        assertTrue(menu.remove("Пункт"));
        assertTrue(menu.get("Пункт").isEmpty());
    }

    @Test
    public void whenAddAndUpdate() {
        SimpleMenu menu = new SimpleMenu("Пункт", new DemoAction());
        menu.add("Пункт", "Подпункт", new DemoAction());
        assertTrue(menu.update("Подпункт", new ExitAction()));
        assertFalse(menu.get("Подпункт").get().doAction());
    }
}