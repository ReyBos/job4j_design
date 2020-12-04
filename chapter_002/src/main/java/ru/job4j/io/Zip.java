package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //-d=./chapter_002/data -e=*.js -o=./chapter_002/data/data.zip
    public static void main(String[] args) throws IOException {
        ArgZip argZip = new ArgZip(args);
        if (argZip.valid()) {
            Path root = Paths.get(argZip.directory());
            List<Path> sourcesPath = searchExclude(root, argZip.exclude());
            List<File> sources = sourcesPath.stream()
                    .map(Path::toFile)
                    .collect(Collectors.toList());
            new Zip().packFiles(sources, new File(argZip.output()));
        }
    }

    public static List<Path> searchExclude(Path root, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles(
                p -> !p.toFile().getName().endsWith(ext.substring(1))
        );
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}