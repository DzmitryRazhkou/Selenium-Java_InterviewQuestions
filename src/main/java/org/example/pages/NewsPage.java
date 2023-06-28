package org.example.pages;

import org.example.basepage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;

public class NewsPage extends BasePage {

    public NewsPage(WebDriver driver) {
        super(driver);
    }

    public static final By SEARCH_ICON = By.cssSelector("a[class='icon-search js-search-show']");
    public static final By SEARCH_FIELD = By.cssSelector("td[id='gs_tti50']");
    public static final By SEARCH_BTN = By.cssSelector("button[class='gsc-search-button gsc-search-button-v2']");
    public static final By TOP_NEWS_LIST = By.cssSelector("div[class='news'] ul li a strong");
    public static final By SUBSCRIPTION = By.cssSelector("div[class='b subscription'] ");

    public static final By YOUTUBE_LINK = By.cssSelector("a[href='https://www.youtube.com/user/Charter97video']");

    private WebElement getSearchIcon() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(SEARCH_ICON));
        return driver.findElement(SEARCH_ICON);
    }

    private WebElement getSearchField() {
        wait.until(ExpectedConditions.presenceOfElementLocated(SEARCH_FIELD));
        return driver.findElement(SEARCH_FIELD);
    }

    private WebElement getSearchBtn() {
        wait.until(ExpectedConditions.presenceOfElementLocated(SEARCH_BTN));
        return driver.findElement(SEARCH_BTN);
    }

    private WebElement getSubscription() {
        wait.until(ExpectedConditions.presenceOfElementLocated(SUBSCRIPTION));
        return driver.findElement(SUBSCRIPTION);
    }

    private WebElement getYouTubeLink() {
        wait.until(ExpectedConditions.presenceOfElementLocated(YOUTUBE_LINK));
        return driver.findElement(YOUTUBE_LINK);
    }

    public void clickOnTheSearchIcon() {
        log.info("User clicks on the search icon. ");
        getSearchIcon().click();
    }


    public boolean validateNewsTitlePage(String pageTitle) {
        log.info("Register page title test");
        System.out.println(" =====> News page title is: " + driver.getTitle() + " <===== ");
        return driver.getTitle().equalsIgnoreCase(pageTitle);
    }

    public void clickOnTheTopNews(String includeTxt) {
        List<WebElement> list = driver.findElements(TOP_NEWS_LIST);
        for (WebElement s : list) {
            if (s.getText().contains(includeTxt)) {
                s.click();
                break;
            }
        }
    }

    public boolean validateNewsId(String proposalId) {
        String currentUrl = driver.getCurrentUrl();
        System.out.println(" Current URL is: " + currentUrl);
        String[] wrd = currentUrl.split("/");
        String id = wrd[5];
        System.out.println(id);
        return id.equalsIgnoreCase(proposalId);
    }

    public void scrollToSubscription() throws InterruptedException {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].scrollIntoView();", getSubscription());
        Thread.sleep(3000);
    }

    public void doClickOnYoutubeLink() {
        getYouTubeLink().click();
    }

    public void handleNewTab() throws InterruptedException {
        String parent = driver.getWindowHandle();
        Set<String> handle = driver.getWindowHandles();
        System.out.println("The News Tabs sre opened now are: " + handle.size());

        for (String child : handle) {
            if (!parent.equals(child)) {
                driver.switchTo().window(child);
            }
            System.out.println("Child Page Title is: " + driver.getTitle());
        }
    }

}
