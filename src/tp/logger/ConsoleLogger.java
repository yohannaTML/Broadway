package org.logger;

public class ConsoleLogger implements Logger {
    public void info(String category, String message) {
        if (category.equals("OUTPUT")){
            System.out.println(message);
        }
    }

    public void error(String category, String message) {
        if (category.equals("OUTPUT")) {
            System.err.println("erreur : " + message);
        }
    }
}

