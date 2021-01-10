package ru.job4j.tdd.cinema;

import ru.job4j.tdd.session.Session;
import ru.job4j.tdd.ticket.Ticket;
import ru.job4j.tdd.account.Account;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public interface Cinema {
    List<Session> find(Predicate<Session> filter);

    Ticket buy(Account account, int row, int column, Calendar date);

    void add(Session session);
}