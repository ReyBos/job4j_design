package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void unavailableWithTempFiles() throws IOException {
        Analizy analizy = new Analizy();
        File source = folder.newFile("server.log");
        File target = folder.newFile("unavailable.csv");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("200 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("300 11:02:02");
        }
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::add);
        }
        List<String> expected = List.of(
                "10:58:01;10:59:01;",
                "11:01:02;11:02:02;"
        );
        assertThat(rsl, is(expected));
    }

    @Test
    public void unavailable() {
        Analizy analizy = new Analizy();
        analizy.unavailable(
                "./data/server.log",
                "./data/unavailable.csv"
        );
        List<String> expected = List.of(
                "10:58:01;10:59:01;",
                "11:01:02;11:02:02;"
        );
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(
                new FileReader("./data/unavailable.csv")
        )) {
            in.lines().forEach(rsl::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(rsl, is(expected));
    }
}