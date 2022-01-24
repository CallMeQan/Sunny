package com.atelier.sunny;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Launcher {
    private static boolean initialized = false;
    private static final Logger logger = LoggerFactory.getLogger(Launcher.class);

    public static void main(String[] args) {
        if (initialized)
            logger.error("Launcher has ran twice!!");
        Bot.INSTANCE.init();
        initialized = true;
    }
}
