package edu.hw2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("HideUtilityClassConstructor")
public class Main {

    public final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        LOGGER.info(Task4.callInfo());
    }
}
