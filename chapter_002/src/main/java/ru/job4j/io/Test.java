package ru.job4j.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Test {
    public static void main(String[] args) {
        char ch = 65407;
        byte test = (byte) ch;
        try (FileOutputStream out = new FileOutputStream("./chapter_002/data/test.txt")) {
            out.write(ch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (FileInputStream in = new FileInputStream("./chapter_002/data/test.txt")) {
            System.out.println(in.available());
            int rsl;
            while ((rsl = in.read()) != -1) {
                System.out.println(rsl);
                System.out.println(test);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
