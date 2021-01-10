package ru.job4j.tdd.cinema;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import ru.job4j.tdd.account.Account;
import ru.job4j.tdd.account.AccountCinema;
import ru.job4j.tdd.session.Session;
import ru.job4j.tdd.session.Session3D;
import ru.job4j.tdd.ticket.Ticket;
import ru.job4j.tdd.ticket.Ticket3D;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CinemaTest {
//    @Test
    public void buy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

//    @Test(expected = IllegalArgumentException.class)
    public void whenBuyThenIllegalDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
    }

//    @Test(expected = IllegalArgumentException.class)
    public void whenBuyThenIllegalSeat() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
    }

//    @Test(expected = IllegalArgumentException.class)
    public void whenBuyThenHasNotMoney() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
    }

//    @Test
    public void find() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

//    @Test
    public void findEmpty() {
        Cinema cinema = new Cinema3D();
        List<Session> sessions = cinema.find(session -> true);
        assertTrue(sessions.size() == 0);
    }
}