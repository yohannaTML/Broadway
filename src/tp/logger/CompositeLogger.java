package org.logger;

public class CompositeLogger implements Logger {
    protected final Logger[] loggers;

    public CompositeLogger(Logger... loggers) {
        this.loggers = loggers;
    }

    public void info(String category, String message) {
        for (Logger logger: loggers) {
            logger.info(category, message);
        }
    }

    public void error(String category, String message) {
        for (Logger logger: loggers) {
            logger.error(category, message);
        }
    }
}