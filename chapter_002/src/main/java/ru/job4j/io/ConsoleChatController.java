package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class ConsoleChatController {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final Map<Function<String, Boolean>, Consumer<ConsoleChatController>> dispatch;
    private boolean out;
    private boolean pause;

    public ConsoleChatController() {
        out = false;
        pause = false;
        dispatch = new HashMap<>();
        dispatch.put(
                s -> s.equals(OUT),
                control -> control.setOut(true)
        );
        dispatch.put(
                s -> s.equals(STOP),
                control -> control.setPause(true)
        );
        dispatch.put(
                s -> s.equals(CONTINUE),
                control -> control.setPause(false)
        );
    }

    public void checkAction(String action) {
        for (Function<String, Boolean> predict : this.dispatch.keySet()) {
            if (predict.apply(action)) {
                this.dispatch.get(predict).accept(this);
            }
        }
    }

    public boolean isOut() {
        return out;
    }

    public boolean isPause() {
        return pause;
    }

    public String getOutConst() {
        return OUT;
    }

    public String getStopConst() {
        return STOP;
    }

    public String getContinueConst() {
        return CONTINUE;
    }

    public void setOut(boolean out) {
        this.out = out;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }
}
