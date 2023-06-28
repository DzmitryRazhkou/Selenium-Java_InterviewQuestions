package org.example.constants;

public class Paths {
    private static final String USER_DIRECTORY = System.getProperty("user.dir");
    private static final String CONFIG_MAIN = "src/main/java/org/example/";
    private static final String CONFIG_PATH = "config/config.properties";
    private static final String SCREENSHOT_PATH = "screenshots/";

    public static final long PAGE_LOAD_TIMEOUT = 20;
    public static final long WEB_DRIVER_WAIT_TIMEOUT = 10;
    public static final String CONFIG = CONFIG_MAIN + CONFIG_PATH;
    public static final String REPORTS = "reports/";
    public static final String SCREENSHOTS = USER_DIRECTORY + '/' + SCREENSHOT_PATH;
}
