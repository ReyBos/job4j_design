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
        return getValue("d");
    }

    public String exclude() {
        return getValue("e");
    }

    public String output() {
        return getValue("o");
    }

    private String getValue(String arg) {
        try {
            return parser.get(arg);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
