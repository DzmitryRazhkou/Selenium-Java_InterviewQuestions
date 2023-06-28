package org.example.tests;

import org.example.basetest.BaseTest;
import org.example.pages.NewsPage;
import org.example.pages.OpenPage;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class NewsPageTest extends BaseTest {
    @Test(priority = 1, description = "Belarusian News - Charter'97 - News page test. ")
    public void validateTitlePage() {
        NewsPage newsPage = new NewsPage(driver);
        Assert.assertTrue(newsPage.validateNewsTitlePage(prop.getProperty("titlePage")));
    }

    @Test(priority = 2, description = "Belarusian News - Charter'97 - Perform search. ")
    public void topNewsList() {
        NewsPage newsPage = new NewsPage(driver);
        newsPage.clickOnTheSearchIcon();
        newsPage.clickOnTheTopNews(prop.getProperty("newsTitle"));
//        newsPage.validateNewsId();
//        Assert.assertTrue("");

    }

    @Test(priority = 3, description = "Belarusian News - Charter'97 - Perform search. ")
    public void handleNewTab() throws InterruptedException {
        NewsPage newsPage = new NewsPage(driver);
        newsPage.clickOnTheSearchIcon();
        newsPage.scrollToSubscription();
        newsPage.doClickOnYoutubeLink();
        newsPage.handleNewTab();
    }

    @Test(priority = 4, description = "Select Drop Down Menu")
    public void dropDownMenu() {
        OpenPage spiceJetPage = new OpenPage(driver);
        spiceJetPage.navigatePageUrl("https://www.globalsqa.com/demo-site/select-dropdown-menu/");
        spiceJetPage.selectDropDown();
    }

    @Test(priority = 5, description = "Select Drop Down Menu Option")
    public void dropDownMenuCertainOption() {
        OpenPage spiceJetPage = new OpenPage(driver);
        spiceJetPage.navigatePageUrl("https://www.globalsqa.com/demo-site/select-dropdown-menu/");
        spiceJetPage.selectDropDownCertain("BLR", "Ukraine");
    }

    @Test(priority = 6, description = "Select Drop Down Menu Option")
    public void sendTextWithOutSendMethod() throws InterruptedException {
        String email = prop.getProperty("email");
        String psw = prop.getProperty("psw");

        OpenPage openPage = new OpenPage(driver);
        openPage.navigatePageUrl("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
        openPage.loginJS_WithoutSendMethod(email, psw);
    }

    @Test(priority = 7, description = "Highlight Web Element")
    public void highLightWebElement() throws InterruptedException {
        String email = prop.getProperty("email");
        String psw = prop.getProperty("psw");

        OpenPage openPage = new OpenPage(driver);
        openPage.navigatePageUrl("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
        openPage.loginHighLight(email, psw);
    }

    @Test(priority = 8, description = "Highlight Web Element")
    public void loginActionsClick() throws InterruptedException {
        String email = prop.getProperty("email");
        String psw = prop.getProperty("psw");

        OpenPage openPage = new OpenPage(driver);
        openPage.navigatePageUrl("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
        openPage.loginActionClass(email, psw);
    }

    @Test(priority = 9, description = "Set and Get Cookies")
    public void loginSetGetCookies() throws InterruptedException {
        String email = prop.getProperty("email");
        String psw = prop.getProperty("psw");

        OpenPage openPage = new OpenPage(driver);
        openPage.navigatePageUrl("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
        openPage.setCookie();
        openPage.loginActionClass(email, psw);
        openPage.retrieveCookies();
    }

    @Test(priority = 10, description = "String Buffer")
    public void stringBuffer() throws InterruptedException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Dzmitry")
                .append(" ")
                .append("Razhkou")
                .append(" ")
                .append("Selenium")
                .append(" ")
                .append("Automatiom");

        OpenPage openPage = new OpenPage(driver);
        openPage.navigatePageUrl("https://www.yahoo.com/");
        openPage.sendYahooStringBuffer(stringBuffer);
        Thread.sleep(2000);
    }

    @Test(priority = 11, description = "Disabled Images")
    public void disabledImages() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        OpenPage.disableImageChrome(chromeOptions);
        OpenPage openPage = new OpenPage(driver);
        openPage.navigatePageUrl("https://www.yahoo.com/");
        Thread.sleep(2000);
    }

    @Test(priority = 12, description = "Drop'n'Drap")
    public void dragAndDropClickHoldRelease() {
        OpenPage openPage = new OpenPage(driver);
        openPage.navigatePageUrl("https://letcode.in/dropable");
        openPage.dragAndDropClickHoldRelease();
    }

    @Test(priority = 13, description = "Drop'n'Drap")
    public void dragAndDrop() {
        OpenPage openPage = new OpenPage(driver);
        openPage.navigatePageUrl("https://letcode.in/dropable");
        openPage.dragAndDrop();
    }

    @Test(priority = 14, description = "Drop Down Loop")
    public void dropDownLoop() throws InterruptedException {
        String country = "Ukraine";
        OpenPage openPage = new OpenPage(driver);
        openPage.navigatePageUrl("https://www.globalsqa.com/demo-site/select-dropdown-menu/");
        openPage.dropDownLoop(country);
    }

    @Test(priority = 15, description = "Drop Down Select Option")
    public void dropDownSelectOption() throws InterruptedException {
        String country = "Ukraine";
        OpenPage openPage = new OpenPage(driver);
        openPage.navigatePageUrl("https://www.globalsqa.com/demo-site/select-dropdown-menu/");
        openPage.dropDownSelect(country);
    }

    @Test(priority = 16, description = "Mouse Hover")
    public void mouseHoverTest() throws InterruptedException {
        OpenPage openPage = new OpenPage(driver);
        openPage.navigatePageUrl("https://www.amazon.com/");
        openPage.doMouseHover();
    }

    @Test(priority = 17, description = "Handle Alert")
    public void handleJSAlertTest() {
        OpenPage openPage = new OpenPage(driver);
        openPage.navigatePageUrl("https://letcode.in/alert");
        openPage.avoidAlert();
    }

    @Test(priority = 18, description = "Handle Scroll Up/ Down")
    public void handleScrollUpDown() throws InterruptedException {
        OpenPage openPage = new OpenPage(driver);
        openPage.doScrollDown();
        openPage.doScrollUp();
        openPage.doScrollToElement();
    }

    @Test(priority = 19, description = "Handle Keyboard Actions")
    public void doKeyboardAction() throws InterruptedException {
        OpenPage openPage = new OpenPage(driver);
        openPage.navigatePageUrl("https://extendsclass.com/text-compare");
        openPage.keyBoardActions();
    }

    @Test(priority = 20, description = "Sauce Demo")
    public void doSauceDemo() throws InterruptedException {
        String userName = "problem_user";
        String psw = "secret_sauce";
        String value = "lohi";

        OpenPage openPage = new OpenPage(driver);
        openPage.navigatePageUrl("https://www.saucedemo.com/");
        openPage.loginSauce(userName, psw);
        List<Double> listBeforeFiltering = openPage.getPricesBeforeFiltering();
        openPage.selectOption(value);
        List<Double> listAfterFiltering = openPage.getPricesAfterFiltering();
        Collections.sort(listBeforeFiltering);
//        Assert.assertEquals(listBeforeFiltering, listAfterFiltering);
    }

    @Test(priority = 21, description = "Selenium Exceptions")
    public void generateExceptions() throws InterruptedException {
        OpenPage openPage = new OpenPage(driver);
        openPage.generateNoSuchElementException();
        openPage.generateTimeoutException();
//        openPage.generateElementNotInteractableException();
//        openPage.generateStaleElementReferenceException();
//        openPage.generateNoSuchWindowException();
//        openPage.generateInvalidSelectorException();
//        openPage.generateNoSuchFrame();
//        openPage.generateNoSuchSessionException();
    }

    @Test(priority = 22, description = "Selenium Find Broken Links")
    public void findBrokenLinks() {
        OpenPage openPage = new OpenPage(driver);
        openPage.findBrokenLinks();
    }

    @Test(priority = 23, description = "Selenium Find Broken Images")
    public void findBrokenImages() {
        OpenPage openPage = new OpenPage(driver);
        openPage.findBrokenImages();
    }
}
