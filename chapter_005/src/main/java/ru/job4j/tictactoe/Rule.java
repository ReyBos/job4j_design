package ru.job4j.tictactoe;

public interface Rule {
    Player getNextPlayer();

    Player showNextPlayer();

    boolean canMove(Point point, Store store);
}