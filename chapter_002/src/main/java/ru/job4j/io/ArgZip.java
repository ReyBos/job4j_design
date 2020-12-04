package ru.job4j.io;

public class ArgZip {
    private final ArgsName parser;

    public ArgZip(String[] args) {
        this.parser = ArgsName.of(args);
    }

    public boolean valid() {
        directory();
        exclude();
        output();
        return true;
    }

    public String directory() {
        return parser.get("d");
    }

    public String exclude() {
        return parser.get("e");
    }

    public String output() {
        return parser.get("o");
    }
}
