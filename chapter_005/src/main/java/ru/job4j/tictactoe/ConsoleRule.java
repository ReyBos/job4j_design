package ru.job4j.tictactoe;

import java.util.LinkedList;
import java.util.Queue;

public class ConsoleRule implements Rule {
    private Player playerFirst;
    private Player playerSecond;
    Queue<Player> queue;

    public ConsoleRule(Player playerFirst, Player playerSecond) {
        if (playerFirst.equals(playerSecond)) {
            throw new IllegalArgumentException("Играть могут только разные игроки");
        }
        this.playerFirst = playerFirst;
        this.playerSecond = playerSecond;
        this.queue = new LinkedList<>();
        this.queue.add(playerFirst);
        this.queue.add(playerSecond);
    }

    @Override
    public Player getNextPlayer() {
        Player nextPlayer = queue.poll();
        queue.add(nextPlayer);
        return nextPlayer;
    }

    @Override
    public Player showNextPlayer() {
        return queue.peek();
    }

    @Override
    public boolean canMove(Point point, Store store) {
        return store.getFieldSize() > point.getColumn()
                && store.getFieldSize() > point.getRow()
                && store.get(point.getRow(), point.getColumn()).isEmpty()
                && point.getPlayer().equals(this.showNextPlayer());
    }
}
