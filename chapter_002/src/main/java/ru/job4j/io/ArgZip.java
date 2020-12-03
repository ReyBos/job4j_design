package ru.job4j.io;

public class ArgZip {
    private final ArgsName parser;
    private StringBuilder error;

    public ArgZip(String[] args) {
        this.parser = ArgsName.of(args);
        error = new StringBuilder();
    }

    public boolean valid() {
        if (directory() == null) {
            error.append("-d ");
        }
        if (exclude() == null) {
            error.append("-e ");
        }
        if (output() == null) {
            error.append("-o ");
        }
        return error.length() == 0;
    }

    public String getError() {
        return error.toString();
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
