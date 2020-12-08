package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EchoServer {
    private static final Pattern GET_PARAM_PATTERN = Pattern.compile("[a-zA-Z]+=[a-zA-Z0-9]+");
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    //curl -i http://localhost:9000/?msg=Hello
    //Параметр -i указывает curl вывести всю информацию принятую от сервера.
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (true) {
                // ожидает, когда к нему обратиться клиент
                Socket socket = server.accept();
                // Когда клиент обратился к серверу программа получает входной поток
                // и может отправить данные в выходной поток.
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    ServerAction serverAction = new ServerAction();
                    for (int i = 0; !str.isEmpty(); i++) { 
                        if (i == 0) {
                            Matcher matcher = GET_PARAM_PATTERN.matcher(str);
                            if (matcher.find()) {
                                String[] arg = matcher.group().split("=");
                                serverAction.checkAction(arg[1]);
                                System.out.println(arg[1] + " " + serverAction.getServerAnswer());
                            }
                        }
                        System.out.println(str);
                        str = in.readLine();
                    }
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    if (!serverAction.getServerAnswer().isEmpty()) {
                        out.write(serverAction.getServerAnswer().getBytes());
                    }
                    if (serverAction.isStopServer()) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            LOG.error("Error", e);
        }
    }
}