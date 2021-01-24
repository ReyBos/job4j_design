package ru.job4j.tictactoe;

import ru.job4j.ood.isp.io.Input;
import ru.job4j.ood.isp.io.Output;

import java.util.Objects;

public class ConsolePlayer implements Player {
    private Mark mark;
    private Input in;
    private Output out;

    public ConsolePlayer(Mark mark, Input in, Output out) {
        this.mark = mark;
        this.in = in;
        this.out = out;
    }

    @Override
    public Point move() {
        out.println("Ваш ход");
        int row = in.askInt("Введите строку: ");
        int column = in.askInt("Введите столбец: ");
        return new ConsolePoint(row, column, this);
    }

    @Override
    public Mark getMark() {
        return mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConsolePlayer that = (ConsolePlayer) o;
        return Objects.equals(mark, that.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mark);
    }
}
