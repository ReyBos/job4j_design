package ru.job4j.tictactoe;

import org.junit.Test;
import ru.job4j.ood.isp.io.ConsoleInput;
import ru.job4j.ood.isp.io.ConsoleOutput;
import ru.job4j.ood.isp.io.Input;
import ru.job4j.ood.isp.io.Output;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConsoleRuleTest {
    @Test(expected = IllegalArgumentException.class)
    public void whenCreateRuleError() {
        Input in = new ConsoleInput();
        Output out = new ConsoleOutput();
        Mark mark = new ConsoleMark("X", out);
        Player player = new ConsolePlayer(mark, in, out);
        new ConsoleRule(player, player);
    }

    @Test
    public void getNextPlayer() {
        Input in = new ConsoleInput();
        Output out = new ConsoleOutput();
        Mark mark = new ConsoleMark("X", out);
        Player player = new ConsolePlayer(mark, in, out);
        Mark mark2 = new ConsoleMark("O", out);
        Player player2 = new ConsolePlayer(mark2, in, out);
        Rule rule = new ConsoleRule(player, player2);
        assertThat(rule.getNextPlayer(), is(player));
        assertThat(rule.getNextPlayer(), is(player2));
        assertThat(rule.getNextPlayer(), is(player));
    }

    @Test
    public void whenPointRowAndColumnBigThenCanNotMove() {
        Store store = new MemStore(3);
        Input in = new ConsoleInput();
        Output out = new ConsoleOutput();
        Mark mark = new ConsoleMark("X", out);
        Player player = new ConsolePlayer(mark, in, out);
        Mark mark2 = new ConsoleMark("O", out);
        Player player2 = new ConsolePlayer(mark2, in, out);
        Rule rule = new ConsoleRule(player, player2);
        Point pointBigRow = new ConsolePoint(3, 1, player);
        assertFalse(rule.canMove(pointBigRow, store));
        Point pointBigColumn = new ConsolePoint(1, 3, player);
        assertFalse(rule.canMove(pointBigColumn, store));
    }

    @Test
    public void whenCellBusyThenCanNotMove() {
        Store store = new MemStore(3);
        Input in = new ConsoleInput();
        Output out = new ConsoleOutput();
        Mark mark = new ConsoleMark("X", out);
        Player player = new ConsolePlayer(mark, in, out);
        Mark mark2 = new ConsoleMark("O", out);
        Player player2 = new ConsolePlayer(mark2, in, out);
        Rule rule = new ConsoleRule(player, player2);
        Point point = new ConsolePoint(1, 1, player);
        store.add(point);
        assertFalse(rule.canMove(point, store));
    }

    @Test
    public void whenOutOfTurnThenCanNotMove() {
        Store store = new MemStore(3);
        Input in = new ConsoleInput();
        Output out = new ConsoleOutput();
        Mark mark = new ConsoleMark("X", out);
        Player player = new ConsolePlayer(mark, in, out);
        Mark mark2 = new ConsoleMark("O", out);
        Player player2 = new ConsolePlayer(mark2, in, out);
        Rule rule = new ConsoleRule(player, player2);
        Point point = new ConsolePoint(1, 1, player);
        Point point2 = new ConsolePoint(1, 1, player2);
        assertFalse(rule.canMove(point2, store));
        assertTrue(rule.canMove(point, store));
    }

    @Test
    public void whenCanMove() {
        Store store = new MemStore(3);
        Input in = new ConsoleInput();
        Output out = new ConsoleOutput();
        Mark mark = new ConsoleMark("X", out);
        Player player = new ConsolePlayer(mark, in, out);
        Mark mark2 = new ConsoleMark("O", out);
        Player player2 = new ConsolePlayer(mark2, in, out);
        Rule rule = new ConsoleRule(player, player2);
        Point point = new ConsolePoint(1, 1, player);
        assertTrue(rule.canMove(point, store));
    }
}