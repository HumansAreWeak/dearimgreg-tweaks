package de.humansareweak.digt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class DIG {
    private static Logger LOG = LogManager.getLogger(Tags.MODID);
    public static final Random rng = new Random();

    public static void debug(String message) {
        LOG.debug(message);
    }

    public static void info(String message) {
        LOG.info(message);
    }

    public static void warn(String message) {
        LOG.warn(message);
    }

    public static void error(String message) {
        LOG.error(message);
    }
}
