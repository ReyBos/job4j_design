package ru.job4j.ood.isp.builder;

import org.junit.Test;
import ru.job4j.ood.isp.io.Output;
import ru.job4j.ood.isp.io.StubOutput;
import ru.job4j.ood.isp.item.*;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleMenuBuilderTest {
    @Test
    public void whenMenuWithoutSubmenu() {
        Output out = new StubOutput();
        SimpleMenuBuilder menuBuilder = new SimpleMenuBuilder();
        FirstItem firstItem = new FirstItem(out);
        ExitItem exitItem = new ExitItem();
        List<MenuItem> input = List.of(firstItem, exitItem);
        List<MenuItem> rsl = menuBuilder.makeMenu(input);
        assertThat(rsl, is(input));
    }

    @Test
    public void whenMenuWithSubmenu() {
        Output out = new StubOutput();
        SimpleMenuBuilder menuBuilder = new SimpleMenuBuilder();
        FirstSubSubItem firstSubSubItem = new FirstSubSubItem(out);
        FirstSubItem firstSubItem = new FirstSubItem(out, firstSubSubItem);
        SecondSubItem secondSubItem = new SecondSubItem(out);
        FirstItem firstItem = new FirstItem(out, firstSubItem, secondSubItem);
        ExitItem exitItem = new ExitItem();
        List<MenuItem> input = List.of(firstItem, exitItem);
        List<MenuItem> rsl = menuBuilder.makeMenu(input);
        List<MenuItem> expected = List.of(
                firstItem,
                firstSubItem,
                firstSubSubItem,
                secondSubItem,
                exitItem
        );
        assertThat(rsl, is(expected));
    }
}