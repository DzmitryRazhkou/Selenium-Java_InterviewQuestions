package org.example.basetest;

import com.github.javafaker.Faker;
import org.example.constants.Paths;
import org.example.utils.BrowserFactory;
import org.example.utils.ConfigReader;
import org.example.utils.Screenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    protected Faker faker;
    protected static Properties prop;

    @BeforeSuite
    public void readProperties() {
        System.out.println(" =====> Read Properties Configuration File <===== ");
        prop = ConfigReader.initProperties();
        faker = new Faker();
    }

    @Parameters({"browser"})
    @BeforeMethod
    public void startUp(BrowserFactory.Browser browser) {
        BrowserFactory.Environment env = BrowserFactory.Environment.valueOf(prop.getProperty("ENVIRONMENT"));
//        BrowserFactory.Browser browser = BrowserFactory.Browser.valueOf(prop.getProperty("BROWSER").trim().toUpperCase());
        boolean headless = Boolean.parseBoolean(prop.getProperty("HEADLESS"));
        String baseURL = prop.getProperty("baseUrl");

        System.out.println(" =====> The '" + browser + "' has been opened <===== ");
        BrowserFactory.setDriver(env, browser, headless);
        driver = BrowserFactory.getDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Paths.PAGE_LOAD_TIMEOUT));
        driver.get(baseURL);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        System.out.println(" =====> Bye Bye ... <===== ");
        if (result.FAILURE == result.getStatus()) {
            Screenshot.takeScreenshot(driver, result.getMethod().getMethodName());
        }
        if (driver != null) {
            driver.quit();
            BrowserFactory.removeDriver();
        }
    }
}
