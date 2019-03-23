package org.tp.logger;

public interface Logger {
   void info(String category, String message);
   void error(String category, String message);

   void debug(String category, String message);
}

