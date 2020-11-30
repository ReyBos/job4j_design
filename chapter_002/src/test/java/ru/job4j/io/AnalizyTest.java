package ru.job4j.io;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {
    @Test
    public void unavailable() {
        Analizy analizy = new Analizy();
        analizy.unavailable(
                "./data/server.log",
                "./data/unavailable.csv"
        );
        List<String> expected = List.of(
                "10:58:01;10:59:01;",
                "11:01:02;11:02:02;"
        );
        assertThat(analizy.getRsl(), is(expected));
    }
}