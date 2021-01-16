package ru.job4j.ood.isp;

import org.junit.Test;
import ru.job4j.ood.isp.io.Input;
import ru.job4j.ood.isp.io.Output;
import ru.job4j.ood.isp.io.StubInput;
import ru.job4j.ood.isp.io.StubOutput;
import ru.job4j.ood.isp.item.ExitItem;
import ru.job4j.ood.isp.item.FirstItem;
import ru.job4j.ood.isp.item.MenuItem;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {
    @Test
    public void whenInvalidThenExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"1", "0"}
        );
        List<MenuItem> items = List.of(new ExitItem());
        new StartUI(out, in).init(items);
        String expected = "0. Выход" + System.lineSeparator()
                + "Неверный ввод, вы можете выбрать: 0 .. 0" + System.lineSeparator()
                + "0. Выход" + System.lineSeparator();
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenValidThenExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"0", "1"}
        );
        List<MenuItem> items = List.of(new FirstItem(out), new ExitItem());
        new StartUI(out, in).init(items);
        String expected = "0. Первый пункт" + System.lineSeparator()
                + "1. Выход" + System.lineSeparator()
                + "Первый пункт" + System.lineSeparator()
                + "0. Первый пункт" + System.lineSeparator()
                + "1. Выход" + System.lineSeparator();
        assertThat(out.toString(), is(expected));
    }
}