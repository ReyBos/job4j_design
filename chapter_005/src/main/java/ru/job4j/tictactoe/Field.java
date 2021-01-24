package ru.job4j.tictactoe;

import java.util.Optional;

public interface Field {
    boolean isGameOver();

    Optional<Player> getWinner();
}