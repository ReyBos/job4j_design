package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private final String path;
    private final String botAnswersPath;
    private final List<String> botAnswers;
    private static final Charset CHARSET = StandardCharsets.UTF_8;

    public ConsoleChat(String path, String botAnswersPath) {
        this.path = path;
        this.botAnswersPath = botAnswersPath;
        this.botAnswers = getBotAnswers();
    }

    private List<String> getBotAnswers() {
        List<String> answers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(botAnswersPath, CHARSET)
        )
        ) {
            reader.lines().forEach(answers::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answers;
    }

    private String getRandomAnswer() {
        Random random = new Random();
        return botAnswers.get(random.nextInt(botAnswers.size()));
    }

    public void run() {
        boolean isPaused = false;
        try (Scanner scanner = new Scanner(System.in);
             BufferedWriter writer = new BufferedWriter(
                     new FileWriter(path, CHARSET, true)
             )
        ) {
            ConsoleChatController controller = new ConsoleChatController();
            System.out.println("Задайте вопрос боту, управлять ботом можно фразами:");
            System.out.println(
                    controller.getOutConst()
                    + " / " + controller.getStopConst()
                    + " / " + controller.getContinueConst()
            );
            String question;
            do {
                question = scanner.nextLine();
                controller.checkAction(question);
                writer.write(question + System.lineSeparator());
                if (!controller.isPause() && !controller.isOut()) {
                    String answer = getRandomAnswer();
                    System.out.println(answer);
                    writer.write(answer + System.lineSeparator());
                }
            } while (!controller.isOut());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(
                "./chapter_002/data/bot_log.txt",
                "./chapter_002/data/bot_answers.txt"
        );
        cc.run();
    }
}
