package org.example.basepage;

import org.example.constants.Paths;
import org.example.pages.OpenPage;
import org.example.utils.ConfigReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;
import java.util.logging.Logger;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Logger log;
    protected static Properties prop;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(Paths.WEB_DRIVER_WAIT_TIMEOUT));
        log = Logger.getLogger(BasePage.class.getName());
        prop = ConfigReader.initProperties();
    }

    public void navigatePageUrl(String URL) {
        driver.navigate().to(URL);
    }

    public void highLightWebElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red')", element);

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        js.executeScript("arguments[0].setAttribute('style', 'border: solid 2px white')", element);
    }
}
