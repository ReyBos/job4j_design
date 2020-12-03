package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Duplicate {
    public static List<Path> searchDuplicate(Path root) throws IOException {
        SearchDuplicate searcher = new SearchDuplicate();
        Files.walkFileTree(root, searcher);
        return searcher.getDuplicates();
    }
}
