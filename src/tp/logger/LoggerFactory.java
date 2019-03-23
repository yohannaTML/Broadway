package org.logger;

public class LoggerFactory {
    private static Logger getCompositeLogger(Logger... loggers) {
        return new CompositeLogger(loggers);
    }

    private static Logger getConsoleLogger() {
        return new ConsoleLogger();
    }

    private static Logger getFileLogger(String filename) {
        return new FileLogger(filename);
    }

    public static Logger getLogger() {
        return getCompositeLogger(getConsoleLogger(), getFileLogger("restaurant.log"));
    }
}
