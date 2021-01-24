package ru.job4j.tictactoe;

import java.util.Objects;

public class ConsolePoint implements Point {
    private int row;
    private int column;
    private Player player;

    public ConsolePoint(int row, int column, Player player) {
        this.row = row;
        this.column = column;
        this.player = player;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConsolePoint that = (ConsolePoint) o;
        return row == that.row
                && column == that.column
                && Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, player);
    }
}
