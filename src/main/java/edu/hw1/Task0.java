package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("HideUtilityClassConstructor")
public final class Task0 {

    private final static Logger LOGGER = LogManager.getLogger();

    public static void sayHello() {
        LOGGER.info("Hello and welcome!");
    }
}
