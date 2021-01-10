package ru.job4j.tdd.cinema;

import ru.job4j.tdd.account.Account;
import ru.job4j.tdd.session.Session;
import ru.job4j.tdd.session.Session3D;
import ru.job4j.tdd.ticket.Ticket;
import ru.job4j.tdd.ticket.Ticket3D;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class Cinema3D implements Cinema {
    @Override
    public List<Session> find(Predicate<Session> filter) {
        return Arrays.asList(new Session3D());
    }

    @Override
    public Ticket buy(Account account, int row, int column, Calendar date) {
        return new Ticket3D();
    }

    @Override
    public void add(Session session) {

    }
}