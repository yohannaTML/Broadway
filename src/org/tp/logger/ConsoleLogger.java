package org.tp.logger;

public class ConsoleLogger implements Logger {
    public void info(String category, String message) {
        if (category.equals("OUTPUT")){
            System.out.println(message);
        }
    }

    public void error(String category, String message) {
        if (category.equals("OUTPUT")) {
            System.out.println("erreur : " + message);
        }
    }

    @Override
    public void debug(String category, String message) {

    }
}

