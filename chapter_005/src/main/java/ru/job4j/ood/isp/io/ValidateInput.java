package ru.job4j.ood.isp.io;

public class ValidateInput implements Input {
    private final Input in;
    private final Output out;

    public ValidateInput(Input in, Output out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public String askStr(String question) {
        out.print(question);
        return in.askStr(question);
    }

    @Override
    public int askInt(String question) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                out.print(question);
                value = in.askInt(question);
                invalid = false;
            } catch (NumberFormatException ex) {
                out.println("Введено не число");
            }
        } while (invalid);
        return value;
    }
}
