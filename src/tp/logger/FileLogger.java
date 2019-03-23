package org.logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class FileLogger implements Logger {
    private final Path path;

    public FileLogger(String pathAsString) {
        path = Paths.get(pathAsString).toAbsolutePath();
    }

    private static String getDateTime() {
        Date date = new Date( );
        SimpleDateFormat newformat;
        newformat = new SimpleDateFormat ("yyyy.MM.dd hh:mm:ss");
        return newformat.format(date);
    }

    private static String rightPadding(String string, int number) {
        return String.format("%1$-" + number + "s", string);
    }

    private void logToFile(String category, String level, String message) {
        try {
            String file = String.format("DATE(%s) CATEGORY(%s) LEVEL(%s) %s\n", getDateTime(), rightPadding(category, 7), rightPadding(level, 5), message);
            Files.write(path, file.getBytes(), APPEND, CREATE);
        } catch (IOException e) {
            throw new RuntimeException("Impossible d'écrire dans le fichier : " + path);
        }
    }

    public void info(String category, String message) {
        logToFile( category,"INFO", message);
    }
    public void error(String category, String message) {
        logToFile( category,"ERROR", message);
    }
}

