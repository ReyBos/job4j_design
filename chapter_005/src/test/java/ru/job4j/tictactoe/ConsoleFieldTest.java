package ru.job4j.tictactoe;

import org.junit.Test;
import ru.job4j.ood.isp.io.ConsoleInput;
import ru.job4j.ood.isp.io.ConsoleOutput;
import ru.job4j.ood.isp.io.Input;
import ru.job4j.ood.isp.io.Output;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConsoleFieldTest {
    @Test
    public void whenGameOverThenDraw() {
        MemStore store = new MemStore(3);
        ConsoleField consoleField = new ConsoleField(store);
        Input in = new ConsoleInput();
        Output out = new ConsoleOutput();
        Mark mark = new ConsoleMark("X", out);
        Player player = new ConsolePlayer(mark, in, out);
        Mark mark2 = new ConsoleMark("O", out);
        Player player2 = new ConsolePlayer(mark2, in, out);
        Point point1 = new ConsolePoint(0, 0, player2);
        Point point2 = new ConsolePoint(0, 1, player);
        Point point3 = new ConsolePoint(0, 2, player2);
        Point point4 = new ConsolePoint(1, 0, player2);
        Point point5 = new ConsolePoint(1, 1, player);
        Point point6 = new ConsolePoint(1, 2, player);
        Point point7 = new ConsolePoint(2, 0, player);
        Point point8 = new ConsolePoint(2, 1, player2);
        Point point9 = new ConsolePoint(2, 2, player);
        store.add(point1);
        store.add(point2);
        store.add(point3);
        store.add(point4);
        store.add(point5);
        store.add(point6);
        store.add(point7);
        store.add(point8);
        store.add(point9);
        assertTrue(consoleField.isGameOver());
        assertTrue(consoleField.getWinner().isEmpty());
    }

    @Test
    public void whenColumnThenWin() {
        MemStore store = new MemStore(3);
        ConsoleField consoleField = new ConsoleField(store);
        Input in = new ConsoleInput();
        Output out = new ConsoleOutput();
        Mark mark = new ConsoleMark("X", out);
        Player player = new ConsolePlayer(mark, in, out);
        Point point = new ConsolePoint(0, 1, player);
        Point point2 = new ConsolePoint(1, 1, player);
        Point point3 = new ConsolePoint(2, 1, player);
        store.add(point);
        store.add(point2);
        store.add(point3);
        assertTrue(consoleField.isGameOver());
        assertThat(consoleField.getWinner().get(), is(player));
    }

    @Test
    public void whenRowThenWin() {
        MemStore store = new MemStore(3);
        ConsoleField consoleField = new ConsoleField(store);
        Input in = new ConsoleInput();
        Output out = new ConsoleOutput();
        Mark mark = new ConsoleMark("X", out);
        Player player = new ConsolePlayer(mark, in, out);
        Point point = new ConsolePoint(1, 0, player);
        Point point2 = new ConsolePoint(1, 1, player);
        Point point3 = new ConsolePoint(1, 2, player);
        store.add(point);
        store.add(point2);
        store.add(point3);
        assertTrue(consoleField.isGameOver());
        assertThat(consoleField.getWinner().get(), is(player));
    }

    @Test
    public void whenMainDiagonalThenWin() {
        MemStore store = new MemStore(3);
        ConsoleField consoleField = new ConsoleField(store);
        Input in = new ConsoleInput();
        Output out = new ConsoleOutput();
        Mark mark = new ConsoleMark("X", out);
        Player player = new ConsolePlayer(mark, in, out);
        Point point = new ConsolePoint(0, 0, player);
        Point point2 = new ConsolePoint(1, 1, player);
        Point point3 = new ConsolePoint(2, 2, player);
        store.add(point);
        store.add(point2);
        store.add(point3);
        assertTrue(consoleField.isGameOver());
        assertThat(consoleField.getWinner().get(), is(player));
    }

    @Test
    public void whenSideDiagonalThenWin() {
        MemStore store = new MemStore(3);
        ConsoleField consoleField = new ConsoleField(store);
        Input in = new ConsoleInput();
        Output out = new ConsoleOutput();
        Mark mark = new ConsoleMark("X", out);
        Player player = new ConsolePlayer(mark, in, out);
        Point point = new ConsolePoint(0, 2, player);
        Point point2 = new ConsolePoint(1, 1, player);
        Point point3 = new ConsolePoint(2, 0, player);
        store.add(point);
        store.add(point2);
        store.add(point3);
        assertTrue(consoleField.isGameOver());
        assertThat(consoleField.getWinner().get(), is(player));
    }
}