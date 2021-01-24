package ru.job4j.tictactoe;

import org.junit.Test;
import ru.job4j.ood.isp.io.ConsoleInput;
import ru.job4j.ood.isp.io.ConsoleOutput;
import ru.job4j.ood.isp.io.Input;
import ru.job4j.ood.isp.io.Output;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MemStoreTest {
    @Test
    public void whenSizeAndCapacity() {
        MemStore store = new MemStore(3);
        assertEquals(0, store.getSize());
        assertEquals(9, store.getCapacity());
        Input in = new ConsoleInput();
        Output out = new ConsoleOutput();
        Mark mark = new ConsoleMark("X", out);
        Player player = new ConsolePlayer(mark, in, out);
        Point point = new ConsolePoint(1, 1, player);
        store.add(point);
        assertEquals(1, store.getSize());
    }

    @Test
    public void get() {
        MemStore store = new MemStore(3);
        Input in = new ConsoleInput();
        Output out = new ConsoleOutput();
        Mark mark = new ConsoleMark("X", out);
        Player player = new ConsolePlayer(mark, in, out);
        Point point = new ConsolePoint(1, 1, player);
        assertTrue(store.add(point));
        assertThat(store.get(1, 1).get(), is(point));
    }

    @Test
    public void whenAddTwiceThenFalse() {
        MemStore store = new MemStore(3);
        Input in = new ConsoleInput();
        Output out = new ConsoleOutput();
        Mark mark = new ConsoleMark("X", out);
        Player player = new ConsolePlayer(mark, in, out);
        Point point = new ConsolePoint(1, 1, player);
        assertTrue(store.add(point));
        assertFalse(store.add(point));
    }

    @Test
    public void findAll() {
        MemStore store = new MemStore(3);
        Input in = new ConsoleInput();
        Output out = new ConsoleOutput();
        Mark mark = new ConsoleMark("X", out);
        Player player = new ConsolePlayer(mark, in, out);
        Point point = new ConsolePoint(1, 1, player);
        store.add(point);
        Point[][] expected = new Point[3][3];
        expected[1][1] = point;
        assertThat(store.findAll(), is(expected));
    }
}