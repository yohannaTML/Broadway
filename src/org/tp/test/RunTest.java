        package org.tp.test;

        import java.lang.reflect.Method;
        import java.lang.reflect.Modifier;
        import java.lang.reflect.InvocationTargetException;

        import org.tp.logger.Logger;
        import org.tp.logger.LoggerFactory;

public class RunTest {
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
        logger.debug("PROGRAM", "running " + clazz.getName() + "." + method.getName());

        nbTests++;

        boolean success = true;
        long testDurationMillis = 0;

        try {
            long startTimeMillis = System.currentTimeMillis();

            try {
                method.invoke(instance);
            } catch (IllegalAccessException e) {
                logger.error("OUTPUT", "could not invoke " + clazz.getName() + "." + method.getName());
                success = false;
            }

            testDurationMillis = System.currentTimeMillis() - startTimeMillis;
        } catch (InvocationTargetException e) {
            success = false;
        }

        String reportMsg;
        if (success) {
            reportMsg = String.format("%s.%s: OK (%d ms)", clazz.getName(), method.getName(), testDurationMillis);
        } else {
            reportMsg = String.format("%s.%s: KO (%d ms)", clazz.getName(), method.getName(), testDurationMillis);
            nbFailedTests++;
        }
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
