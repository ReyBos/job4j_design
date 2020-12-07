package ru.job4j.io;

import java.util.LinkedHashMap;
import java.util.function.Consumer;
import java.util.function.Function;

public class ServerAction {
    private final LinkedHashMap<Function<String, Boolean>, Consumer<ServerAction>> dispatch;
    private boolean stopServer;
    private String serverAnswer;

    public ServerAction() {
        stopServer = false;
        serverAnswer = "";
        dispatch = new LinkedHashMap<>();
        dispatch.put(
                s -> s.equals("Exit"),
                serverAction -> serverAction.setStopServer(true)
        );
        dispatch.put(
                s -> s.equals("Hello"),
                serverAction -> serverAction.setServerAnswer("Hello")
        );
        dispatch.put(
                s -> !s.equals("Hello"),
                serverAction -> serverAction.setServerAnswer("What")
        );
    }

    public void checkAction(String action) {
        for (Function<String, Boolean> predict : this.dispatch.keySet()) {
            if (predict.apply(action)) {
                this.dispatch.get(predict).accept(this);
                break;
            }
        }
    }

    public void setStopServer(boolean stopServer) {
        this.stopServer = stopServer;
    }

    public void setServerAnswer(String serverAnswer) {
        this.serverAnswer = serverAnswer;
    }

    public boolean isStopServer() {
        return stopServer;
    }

    public String getServerAnswer() {
        return serverAnswer;
    }
}
