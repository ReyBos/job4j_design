package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException(
                    "Root folder is null. Usage java -jar dir.jar ROOT_FOLDER."
            );
        }
        File file = new File(args[0]);
//        File file = new File("/Users/reybos/Documents/projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format(
                    "Not exist %s", file.getAbsoluteFile()
            ));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format(
                    "Not directory %s", file.getAbsoluteFile()
            ));
        }
        System.out.println(String.format("size : %s", file.getTotalSpace()));
        for (File subfile : file.listFiles()) {
            System.out.println(String.format(subfile.getName() + " : %s", subfile.getTotalSpace()));
        }
    }
}