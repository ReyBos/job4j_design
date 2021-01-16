package ru.job4j.ood.isp.io;

public class StubInput implements Input {
    private String[] answers;
    private int point;

    public StubInput(String[] answers) {
        this.answers = answers;
        this.point = 0;
    }

    @Override
    public int askInt(String question) {
        return Integer.parseInt(answers[point++]);
    }

    @Override
    public String askStr(String question) {
        return answers[point++];
    }
}
