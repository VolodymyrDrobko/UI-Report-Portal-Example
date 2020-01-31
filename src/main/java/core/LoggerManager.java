package core;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

public class LoggerManager {
    private static ThreadLocal<Logger> loggerPool = new ThreadLocal<>();

    public static void setLogger(Logger logger) {
        loggerPool.set(logger);
    }

    public synchronized static Logger getLogger() {
        return loggerPool.get();
    }

    public static void log(String message) {
        getLogger().log(Level.INFO, message);
    }
}
