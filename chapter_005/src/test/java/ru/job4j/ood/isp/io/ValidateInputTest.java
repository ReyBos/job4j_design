package ru.job4j.ood.isp.io;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidateInputTest {
    @Test
    public void whenValidInput() {
        StubOutput out = new StubOutput();
        StubInput in = new StubInput(new String[]{"1"});
        ValidateInput input = new ValidateInput(in, out);
        int rsl = input.askInt("введите число: ");
        assertEquals(1, rsl);
    }

    @Test
    public void whenInvalidInput() {
        StubOutput out = new StubOutput();
        StubInput in = new StubInput(new String[]{"test", "5"});
        ValidateInput input = new ValidateInput(in, out);
        int rsl = input.askInt("введите число: ");
        assertEquals(5, rsl);
        String expectedOutput = "введите число: "
                + "Введено не число" + System.lineSeparator()
                + "введите число: ";
        assertEquals(expectedOutput, out.toString());
    }
}