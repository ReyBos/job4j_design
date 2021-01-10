package ru.job4j.template;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class GeneratorTest {
    private Generator generator = (template, args) -> {
        if (args.size() != 2) {
            throw new IllegalArgumentException();
        }
        return "Value1 = val1, value2 = val2";
    };

    @Test(expected = IllegalArgumentException.class)
    public void whenNeed2Transferred1() {
        Map<String, String> input = Map.of("key1", "val1");
        String template = "I am a ${name}, Who are ${subject}? ";
        generator.produce(template, input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNeed2Transferred3() {
        Map<String, String> input = Map.of("key1", "val1", "key2", "val2", "key3", "val3");
        String template = "I am a ${name}, Who are ${subject}? ";
        generator.produce(template, input);
    }

    @Test
    public void whenSuccessProduce() {
        Map<String, String> input = Map.of("key1", "val1", "key2", "val2");
        String template = "Value1 = ${key1}, value2 = ${key2}";
        String rsl = generator.produce(template, input);
        String expected = "Value1 = val1, value2 = val2";
        assertEquals(expected, rsl);
    }
}