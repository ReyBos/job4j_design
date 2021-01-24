package ru.job4j.tictactoe;

import org.junit.Test;
import ru.job4j.ood.isp.io.StubOutput;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConsoleMarkTest {
    @Test
    public void print() {
        StubOutput out = new StubOutput();
        new ConsoleMark("X", out).print();
        assertThat(out.toString(), is("X"));
    }
}