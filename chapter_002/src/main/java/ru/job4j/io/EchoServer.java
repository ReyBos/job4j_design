package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EchoServer {
    //curl -i http://localhost:9000/?msg=Hello
    //Параметр -i указывает curl вывести всю информацию принятую от сервера.
    @SuppressWarnings("checkstyle:InnerAssignment")
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (true) {
                // ожидает, когда к нему обратиться клиент
                Socket socket = server.accept();
                // Когда клиент обратился к серверу программа получает входной поток
                // и может отправить данные в выходной поток.
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String answer = "";
                    boolean needStop = false;
                    String str = in.readLine();
                    for (int i = 0; !str.isEmpty(); i++) {
                        if (i == 0) {
                            Pattern pattern = Pattern.compile("[a-zA-Z]+=[a-zA-Z]+");
                            Matcher matcher = pattern.matcher(str);
                            if (matcher.find()) {
                                String[] arg = matcher.group().split("=");
                                switch (arg[1]) {
                                    case "Exit":
                                        needStop = true;
                                        break;
                                    case "Hello":
                                        answer = "Hello";
                                        break;
                                    default:
                                        answer = "What";
                                }
                            }
                        }
                        System.out.println(str);
                        str = in.readLine();
                    }
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    if (!answer.isEmpty()) {
                        out.write(answer.getBytes());
                    }
                    if (needStop) {
                        break;
                    }
                }
            }
        }
    }
}