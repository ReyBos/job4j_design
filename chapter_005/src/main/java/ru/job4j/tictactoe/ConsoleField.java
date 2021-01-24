package ru.job4j.tictactoe;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;

public class ConsoleField implements Field {
    Store store;

    public ConsoleField(Store store) {
        this.store = store;
    }

    @Override
    public boolean isGameOver() {
        return store.getSize() >= store.getCapacity() || getWinner().isPresent();
    }

    @Override
    public Optional<Player> getWinner() {
        Optional<Point> suggestion;
        for (int i = 0; i < store.getFieldSize(); i++) {
            suggestion = store.get(i, i);
            if (suggestion.isPresent() && (checkColumn(i) || checkRow(i))) {
                return Optional.of(suggestion.get().getPlayer());
            }
        }
        suggestion = store.get(0, 0);
        if (suggestion.isPresent() && checkMainDiagonal(0, 0)) {
            return Optional.of(suggestion.get().getPlayer());
        }
        suggestion = store.get(0, store.getFieldSize() - 1);
        if (suggestion.isPresent() && checkSideDiagonal(0, store.getFieldSize() - 1)) {
            return Optional.of(suggestion.get().getPlayer());
        }
        return Optional.empty();
    }

    private boolean checkRow(int checkedRow) {
        BiFunction<Integer, Integer, Integer[]> getCoordinate = (column, row) -> new Integer[] {row, column};
        return checkLine(getCoordinate, checkedRow);
    }

    private boolean checkColumn(int checkedColumn) {
        BiFunction<Integer, Integer, Integer[]> getCoordinate = (row, column) -> new Integer[] {row, column};
        return checkLine(getCoordinate, checkedColumn);
    }

    private boolean checkLine(BiFunction<Integer, Integer, Integer[]> getCoordinate, int line) {
        Optional<Point> firstPoint = store.get(getCoordinate.apply(0, line)[0], getCoordinate.apply(0, line)[1]);
        Optional<Point> nextPoint;
        if (firstPoint.isEmpty()) {
            return false;
        }
        for (int i = 1; i < store.getFieldSize(); i++) {
            nextPoint = store.get(getCoordinate.apply(i, line)[0], getCoordinate.apply(i, line)[1]);
            if (nextPoint.isEmpty() || !nextPoint.get().getPlayer().equals(firstPoint.get().getPlayer())) {
                return false;
            }
        }
        return true;
    }

    private boolean checkMainDiagonal(int startRow, int startColumn) {
        IntPredicate checkCoordinate = num -> num < store.getFieldSize();
        IntFunction<Integer> changeCoordinate = num -> num + 1;
        return checkDiagonal(
                startRow, startColumn,
                checkCoordinate, checkCoordinate,
                changeCoordinate, changeCoordinate
        );
    }

    private boolean checkSideDiagonal(int startRow, int startColumn) {
        IntPredicate checkRowCoordinate = num -> num < store.getFieldSize();
        IntPredicate checkColumnCoordinate = num -> num >= 0;
        IntFunction<Integer> changeRowCoordinate = num -> num + 1;
        IntFunction<Integer> changeColumnCoordinate = num -> num - 1;
        return checkDiagonal(
                startRow, startColumn,
                checkRowCoordinate, checkColumnCoordinate,
                changeRowCoordinate, changeColumnCoordinate
        );
    }

    private boolean checkDiagonal(
            int startRow, int startColumn,
            IntPredicate checkRowCoordinate, IntPredicate checkColumnCoordinate,
            IntFunction<Integer> changeRowCoordinate, IntFunction<Integer> changeColumnCoordinate
    ) {
        Optional<Point> firstPoint = store.get(startRow, startColumn);
        Optional<Point> nextPoint;
        if (firstPoint.isEmpty()) {
            return false;
        }
        while (checkColumnCoordinate.test(startColumn) && checkRowCoordinate.test(startRow)) {
            nextPoint = store.get(startRow, startColumn);
            if (nextPoint.isEmpty() || !nextPoint.get().getPlayer().equals(firstPoint.get().getPlayer())) {
                return false;
            }
            startRow = changeRowCoordinate.apply(startRow);
            startColumn = changeColumnCoordinate.apply(startColumn);
        }
        return true;
    }
}
