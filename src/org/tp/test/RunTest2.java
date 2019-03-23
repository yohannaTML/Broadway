package org.tp.test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.InvocationTargetException;

import org.tp.logger.Logger;
import org.tp.logger.LoggerFactory;

public class RunTest2 {
    private static Logger logger;
    private static int nbTests;
    private static int nbFailedTests;

    public static void testClass(String className) {
        logger.debug("PROGRAM", "testing class " + className);

        try {
            Class<?> clazz = Class.forName(className);

            try {
                Object instance = clazz.newInstance();

                for (Method method : clazz.getDeclaredMethods()) {
                    System.out.println(method.getName());
                    int modifiers = method.getModifiers();
                    if (Modifier.isPublic(modifiers)) {
                        testMethod(clazz, instance, method);
                    }
                }
            } catch (InstantiationException | IllegalAccessException e) {
                logger.error("OUTPUT", "could not instantiate " + clazz.getName());
            }
        } catch (ClassNotFoundException e) {
            logger.error("OUTPUT", "class " + className + " not found");
        }
    }


    public static void testMethod(Class<?> clazz, Object instance, Method method) {
        logger.info("PROGRAM", "running " + clazz.getName() + "." + method.getName());

        nbTests++;
        try {
            instance = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error("OUTPUT", "could not instantiate " + clazz.getName());
        }

        try {
            method.invoke(instance);
        } catch (InvocationTargetException | IllegalAccessException e) {
            logger.error("OUTPUT", "could not invoke " + clazz.getName() + "." + method.getName());

        }

        boolean success = true;
        long testDurationMillis;

        long startTimeMillis = System.currentTimeMillis();


        testDurationMillis = System.currentTimeMillis() - startTimeMillis;

        String reportMsg;
        reportMsg = String.format("%s.%s: OK (%d ms)", clazz.getName(), method.getName(), testDurationMillis);
        logger.info("OUTPUT", reportMsg);
    }

    public static void main(String[] args) {
        logger = LoggerFactory.getLogger();
        logger.debug("PROGRAM", "Starting tests");

        long startTimeMillis = System.currentTimeMillis();
        for (String className: args) {
            testClass(className);
        }
        long totalTimeMillis = System.currentTimeMillis() - startTimeMillis;

        int nbPassedTests = nbTests - nbFailedTests;
        float passedRate = (float)nbPassedTests / nbTests * 100;
        float failedRate = (float)nbFailedTests / nbTests * 100;

        logger.info("OUTPUT", String.format("total  : %d", nbTests));
        logger.info("OUTPUT", String.format("passed : %d (%3.0f%%)", nbPassedTests, passedRate));
        logger.info("OUTPUT", String.format("failed : %d (%3.0f%%)", nbFailedTests, failedRate));
        logger.info("OUTPUT", String.format("time   : %d ms", totalTimeMillis));
    }
}
