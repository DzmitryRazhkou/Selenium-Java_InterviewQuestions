package org.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class BrowserFactory {
    private static final ThreadLocal<WebDriver> e_driver = new ThreadLocal<>();

    public enum Environment {
        LOCAL,
        REMOTE
    }

    public enum Browser {
        CHROME,
        FIREFOX,
        SAFARI
    }

    //    CONSTRUCTOR:
    private BrowserFactory() throws Exception {
        throw new Exception();
    }

    //    METHODS:
    public static void setDriver(Environment environment, Browser browser, boolean headless) {
        switch (environment) {
            case LOCAL -> setLocalDriver(browser, headless);
            case REMOTE -> {
                URL hub = null;
                try {
                    hub = new URL(System.getProperty("HUB.URL"));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                setRemoteDriver(hub, browser, headless);
            }
            default -> System.out.println(" =====> PROVIDE ANOTHER ENVIRONMENT <===== ");
        }
    }

    private static void setLocalDriver(Browser browser, boolean headless) {
        switch (browser) {
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions opts = new ChromeOptions();
                opts.addArguments("--disable-extensions");
                opts.addArguments("--remote-allow-origins=*");
                opts.setExperimentalOption("excludeSwitches", List.of("enable-automation"));
                e_driver.set(new ChromeDriver(opts.setHeadless(headless)));
                System.out.println(" =====> Selenium Google Chrome <===== ");
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                e_driver.set(new FirefoxDriver(options.setHeadless(headless)));
                System.out.println(" =====> Selenium Mozilla Firefox <===== ");
            }
            case SAFARI -> {
                WebDriverManager.safaridriver().setup();
                e_driver.set(new SafariDriver());
                System.out.println(" =====> Selenium Apple Safari <===== ");
            }
            default -> throw new IllegalArgumentException(browser + " is not supported to run locally!");
        }
    }

    private static synchronized void setRemoteDriver(URL hub, Browser browser, boolean headless) {
        switch (browser) {
            case CHROME -> e_driver.set(new RemoteWebDriver(hub, new ChromeOptions().setHeadless(headless)));
            case FIREFOX -> e_driver.set(new RemoteWebDriver(hub, new FirefoxOptions().setHeadless(headless)));
        }
    }

    public synchronized static WebDriver getDriver() {
        return e_driver.get();
    }

    public synchronized static void removeDriver() {
        e_driver.remove();
    }

}
