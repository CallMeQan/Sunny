package com.atelier.sunny;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Launcher {
    private static boolean initialized = false;
    private static final Logger logger = LogManager.getLogger(Launcher.class);
    public static void main(String[] args) {
        if (initialized) logger.fatal("Launcher has ran twice!!");
        Bot.INSTANCE.init();
        initialized = true;
    }
}
