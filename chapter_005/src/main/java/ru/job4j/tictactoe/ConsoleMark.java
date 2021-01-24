package ru.job4j.tictactoe;

import ru.job4j.ood.isp.io.Output;

import java.util.Objects;

public class ConsoleMark implements Mark {
    private String mark;
    private Output out;

    public ConsoleMark(String mark, Output out) {
        this.mark = mark;
        this.out = out;
    }

    @Override
    public void print() {
        out.print(mark);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConsoleMark that = (ConsoleMark) o;
        return Objects.equals(mark, that.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mark);
    }
}
