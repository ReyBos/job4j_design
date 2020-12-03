package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchDuplicate implements FileVisitor<Path> {
    private HashMap<String, Path> allFiles;
    private List<Path> duplicates;

    public SearchDuplicate() {
        this.allFiles = new HashMap<>();
        this.duplicates = new ArrayList<>();
    }

    public List<Path> getDuplicates() {
        return duplicates;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path prevFile = allFiles.put(file.toFile().getName(), file);
        if (prevFile != null && prevFile.toFile().length() == file.toFile().length()) {
            duplicates.add(prevFile);
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return CONTINUE;
    }
}
