package ru.job4j.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    //curl -i http://localhost:9000/?msg=Hello
    //Параметр -i указывает curl вывести всю информацию принятую от сервера.
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
                    String str;
                    String[] line;
                    boolean needStop = false;
                    for (int i = 0; !(str = in.readLine()).isEmpty(); i++) {
                        if (i == 0 && (line = str.split("=")).length == 2) {
                            needStop = line[1].startsWith("Bye");
                        }
                        System.out.println(str);
                    }
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                    if (needStop) {
                        break;
                    }
                }
            }
        }
    }
}