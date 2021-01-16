package ru.job4j.ood.isp.io;

public class ConsoleOutput implements Output {
    @Override
    public void print(Object ob) {
        System.out.print(ob);
    }

    @Override
    public void println(Object ob) {
        System.out.println(ob);
    }
}
