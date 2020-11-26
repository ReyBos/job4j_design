package ru.job4j.collection;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizeTest {
    @Test
    public void whenHasNotChanged() {
        List<Analize.User> previous = List.of(
                new Analize.User(1, "Первый"),
                new Analize.User(2, "Второй")
        );
        List<Analize.User> current = List.of(
                new Analize.User(1, "Первый"),
                new Analize.User(2, "Второй")
        );
        Analize analize = new Analize();
        Analize.Info out = analize.diff(previous, current);
        assertThat(out, is(new Analize.Info()));
    }

    @Test
    public void whenHasChanged() {
        List<Analize.User> previous = List.of(
                new Analize.User(1, "Первый"),
                new Analize.User(2, "Второй"),
                new Analize.User(3, "Третий")
        );
        List<Analize.User> current = List.of(
                new Analize.User(1, "Изменен 1"),
                new Analize.User(2, "Второй"),
                new Analize.User(4, "Четвертый")
        );
        Analize analize = new Analize();
        Analize.Info out = analize.diff(previous, current);
        Analize.Info expected = new Analize.Info(1, 1, 1);
        assertThat(out, is(expected));
    }
}