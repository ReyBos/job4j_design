package ru.job4j.io;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class DuplicateTest {
    @Test
    public void searchDuplicate() throws IOException {
        Path root = Paths.get("./data");
        List<Path> rsl = Duplicate.searchDuplicate(root);
        assertThat(rsl.get(0).toFile().getName(), is("equal.js"));
    }
}