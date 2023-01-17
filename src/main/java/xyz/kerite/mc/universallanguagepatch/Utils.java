package xyz.kerite.mc.universallanguagepatch;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utils {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void tryRun(SimpleRunnable runnable) {
        try {
            runnable.run();
        } catch (Exception exception) {
            LOGGER.error(exception);
        }
    }

    interface SimpleRunnable {
        void run() throws Exception;
    }
}
