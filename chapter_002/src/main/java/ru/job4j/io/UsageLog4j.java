package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
        String name = "Petr Arsentev";
        int age = 33;
        LOG.debug("User info name : {}, age : {}", name, age);
        byte bt = 0;
        char ch = '1';
        short sh = 2;
        long lg = 3;
        float fl = 4f;
        double db = 5;
        boolean bool = true;
        LOG.debug("0 - {}, 1 - {}, 2 - {}, 3 - {}, 4 - {}, 5 - {}, 6 - {}",
                bt,
                ch,
                sh,
                lg,
                fl,
                db,
                bool
        );
        try {
            throw new Exception("Not supported code");
        } catch (Exception e) {
            LOG.error("Exception in log example", e);
        }
    }
}