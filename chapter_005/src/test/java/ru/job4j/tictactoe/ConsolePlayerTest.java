package ru.job4j.tictactoe;

import org.junit.Test;
import ru.job4j.ood.isp.io.Input;
import ru.job4j.ood.isp.io.Output;
import ru.job4j.ood.isp.io.StubInput;
import ru.job4j.ood.isp.io.StubOutput;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConsolePlayerTest {
    @Test
    public void move() {
        Input in = new StubInput(new String[] {"1", "2"});
        Output out = new StubOutput();
        Player player = new ConsolePlayer(new ConsoleMark("X", out), in, out);
        Point point = player.move();
        Point expected = new ConsolePoint(1, 2, player);
        assertThat(point, is(expected));
    }
}