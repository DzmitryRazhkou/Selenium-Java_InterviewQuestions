package org.example.pages;

import org.example.basepage.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class OpenPage extends BasePage {

    public OpenPage(WebDriver driver) {
        super(driver);
    }

    private static final By SELECT = By.cssSelector("p select");
    private static final By EMAIL = By.id("input-email");
    private static final By PSW = By.id("input-password");
    private static final By SUBMIT = By.cssSelector("input[type='submit']");

    private WebElement getSelectMenu() {
        wait.until(ExpectedConditions.presenceOfElementLocated(SELECT));
        return driver.findElement(SELECT);
    }

    private WebElement getEmail() {
        wait.until(ExpectedConditions.presenceOfElementLocated(EMAIL));
        return driver.findElement(EMAIL);
    }

    private WebElement getPsw() {
        wait.until(ExpectedConditions.presenceOfElementLocated(PSW));
        return driver.findElement(PSW);
    }

    private WebElement submitBtn() {
        wait.until(ExpectedConditions.presenceOfElementLocated(SUBMIT));
        return driver.findElement(SUBMIT);
    }

    public void selectDropDown() {
        Select sl = new Select(getSelectMenu());
        List<WebElement> options = sl.getOptions();
        List<String> listOptsList = new ArrayList<>();

        for (WebElement s : options) {
            listOptsList.add(s.getText());
        }
        System.out.println(options.size());
        listOptsList.forEach(System.out::println);
    }

    public void selectDropDownCertain(String option1, String option2) {
        Select sl = new Select(getSelectMenu());
        List<WebElement> options = sl.getOptions();
        sl.selectByValue(option1);

        for (WebElement s : options) {
            if (s.getText().contains(option2)) {
                s.click();
                break;
            }
        }
    }

    public void loginJS_WithoutSendMethod(String email, String psw) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value=arguments[1]", getEmail(), email);
//        js.executeScript("arguments[0].value='dimagadjilla@gmail.com'", getEmail());
//        js.executeScript("document.getElementById('input-email').value ='email'");

        js.executeScript("arguments[0].value=arguments[1]", getPsw(), psw);
//        js.executeScript("arguments[0].value='3036057Dr'", getPsw());
//        js.executeScript("document.getElementById('input-password').value ='psw'");
        js.executeScript("arguments[0].click()", submitBtn());
        Thread.sleep(2000);
    }

    public void loginHighLight(String email, String psw) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        highLightWebElement(getEmail());
        js.executeScript("arguments[0].value=arguments[1]", getEmail(), email);
        highLightWebElement(getPsw());
        js.executeScript("arguments[0].value=arguments[1]", getPsw(), psw);
        highLightWebElement(submitBtn());
        js.executeScript("arguments[0].click()", submitBtn());
        Thread.sleep(2000);
    }

    public void loginActionClass(String email, String psw) throws InterruptedException {
        Actions actions = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        highLightWebElement(getEmail());
        js.executeScript("arguments[0].value=arguments[1]", getEmail(), email);
        highLightWebElement(getPsw());
        js.executeScript("arguments[0].value=arguments[1]", getPsw(), psw);
        actions.moveToElement(submitBtn()).click().build().perform();
        Thread.sleep(2000);
    }

    public void setCookie() {
        driver.manage().addCookie(new Cookie("Gans Automation", "Cypress Selenium"));
        Cookie cookie = driver.manage().getCookieNamed("Gans Automation");
        System.out.println("Sent Cookie is: " + cookie);
    }

    public void retrieveCookies() {
        Set<Cookie> cookieSet = driver.manage().getCookies();
        cookieSet.forEach(System.out::println);
    }

    public void sendYahooStringBuffer(StringBuffer buffer) {
        WebElement searchField = driver.findElement(By.cssSelector("input[id='ybar-sbq']"));
        searchField.sendKeys(buffer);
    }

    public static void disableImageChrome(ChromeOptions options) {
        HashMap<String, Object> images = new HashMap<>();
        images.put("images", 2);
        HashMap<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values", images);
        options.setExperimentalOption("prefs", prefs);
    }

    public static void disableImagesFirefox(FirefoxOptions options) {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("permissions.default.image", 2);
        options.setProfile(profile);
//        options.setCapability(PROFILE, profile);
    }

    public void dragAndDropClickHoldRelease() {
        By DRAGGABLE = By.id("draggable");
        By DROPPABLE = By.id("droppable");

        WebElement source = driver.findElement(DRAGGABLE);
        WebElement target = driver.findElement(DROPPABLE);

        Actions act = new Actions(driver);
        act.clickAndHold(source).moveToElement(target)
                .release()
                .build()
                .perform();
    }

    public void dragAndDrop() {
        By DRAGGABLE = By.id("draggable");
        By DROPPABLE = By.id("droppable");

        WebElement source = driver.findElement(DRAGGABLE);
        WebElement target = driver.findElement(DROPPABLE);

        Actions act = new Actions(driver);
        act.dragAndDrop(source, target)
                .build()
                .perform();
    }

    public void dropDownLoop(String value) throws InterruptedException {
        By dropDownLocator = By.cssSelector("select>option");

//        Define Web Elements:
        List<WebElement> listOfDropDownElements = driver.findElements(dropDownLocator);
        System.out.println(" The Size Of List Of Web Elements: " + listOfDropDownElements.size());

        for (int i = 0; i < listOfDropDownElements.size(); i++) {
            if (listOfDropDownElements.get(i).getText().equalsIgnoreCase(value)) {
                listOfDropDownElements.get(i).click();
                break;
            }
        }
//        for (WebElement s : listOfDropDownElements) {
//            System.out.println(s.getText());
//            if (s.getText().equalsIgnoreCase(value)) {
//                s.click();
//                break;
//            }
//        }
        Thread.sleep(2000);
    }

    public void dropDownSelect(String value) throws InterruptedException {
        By dropDown = By.cssSelector("div[rel-title='Select Country']>p>select");
        WebElement list = driver.findElement(dropDown);

        Select select = new Select(list);
        List<WebElement> listOfWebElements = select.getOptions();

//        The Amount:
        System.out.println("The Size Of The Web Elements is: " + listOfWebElements.size());

        for (WebElement s : listOfWebElements) {
            System.out.println(s.getText());
            if (s.getText().equalsIgnoreCase(value)) {
                s.click();
                break;
            }
        }
        Thread.sleep(2000);
    }

    public void doMouseHover() throws InterruptedException {
        By ACCOUNT_LIST = By.cssSelector("#nav-link-accountList");
        WebElement account_list = driver.findElement(ACCOUNT_LIST);

        Actions act = new Actions(driver);
        act.moveToElement(account_list).build().perform();
        Thread.sleep(2000);
    }

    public void avoidAlert() {

        By CLICK_ALERT = By.id("confirm");
        WebElement clickAlert = driver.findElement(CLICK_ALERT);
        clickAlert.click();

        Alert alert = driver.switchTo().alert();
        String alertTxt = alert.getText();
        System.out.println(alertTxt);

        if (alertTxt.equals("Are you happy with LetCode?")) {
            alert.accept();
        } else {
            alert.dismiss();
        }
    }

    public void doScrollDown() throws InterruptedException {
        JavascriptExecutor javascriptExecutor = ((JavascriptExecutor) driver);
        javascriptExecutor.executeScript("window.scrollTo(0, 950)", "");
//        javascriptExecutor.executeScript("window.scrollBy(0, 950)", "");
        Thread.sleep(2000);
    }

    public void doScrollUp() throws InterruptedException {
        JavascriptExecutor javascriptExecutor = ((JavascriptExecutor) driver);
        javascriptExecutor.executeScript("window.scrollTo(0, -950)", "");
//        javascriptExecutor.executeScript("window.scrollBy(0, 950)", "");
        Thread.sleep(2000);
    }

    public void doScrollToElement() throws InterruptedException {
        By WEB_ELEMENT = By.cssSelector("a[href='https://www.youtube.com/user/Charter97video']");
        WebElement el = driver.findElement(WEB_ELEMENT);

        JavascriptExecutor javascriptExecutor = ((JavascriptExecutor) driver);
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();", el);
        Thread.sleep(2000);
    }

    public void keyBoardActions() throws InterruptedException {
        By SOURCE = By.cssSelector("#dropZone > .row-container");
        By TARGET = By.cssSelector("#dropZone2 > .row-container");

        WebElement source = driver.findElement(SOURCE);
        WebElement target = driver.findElement(TARGET);

        Actions actions = new Actions(driver);
        Thread.sleep(2000);
        actions.keyDown(source, Keys.COMMAND).sendKeys("a").sendKeys("c").build().perform();
        Thread.sleep(2000);
        actions.keyDown(target, Keys.COMMAND).sendKeys("a").sendKeys("v").build().perform();
        Thread.sleep(2000);
    }

    public void loginSauce(String user, String psw) {
        By USER = By.id("user-name");
        By PSW = By.id("password");
        By LOGIN_BTN = By.id("login-button");

        WebElement userField = driver.findElement(USER);
        WebElement pswField = driver.findElement(PSW);
        WebElement loginBtn = driver.findElement(LOGIN_BTN);

        userField.sendKeys(user);
        pswField.sendKeys(psw);
        loginBtn.click();
    }

    public List<Double> getPricesBeforeFiltering() {
        By PRICES_LOCATOR = By.cssSelector("div[class='inventory_item_price']");
        List<WebElement> listOfPrices = driver.findElements(PRICES_LOCATOR);

//        Before Filtering
        List<Double> listOfPricesBeforeFiltering = new ArrayList<>();
        for (WebElement s : listOfPrices) {
            listOfPricesBeforeFiltering.add(Double.valueOf(s.getText().replace("$", "")));
        }

        int sizeBeforeFiltering = listOfPricesBeforeFiltering.size();
        System.out.println("The Amount Of The Prices Before Filtering is: " + sizeBeforeFiltering);
        return listOfPricesBeforeFiltering;
    }

    public void selectOption(String value) throws InterruptedException {
        By SELECT_DROPDOWN = By.cssSelector("select[class='product_sort_container']");
        WebElement dropDown = driver.findElement(SELECT_DROPDOWN);

        Select select = new Select(dropDown);
        select.selectByValue(value);
        Thread.sleep(2000);
    }

    public List<Double> getPricesAfterFiltering() {
        By PRICES_LOCATOR = By.cssSelector("div[class='inventory_item_price']");
        List<WebElement> listOfPrices = driver.findElements(PRICES_LOCATOR);

        List<Double> listOfPricesAfterFiltering = new ArrayList<>();
        for (WebElement s : listOfPrices) {
            listOfPricesAfterFiltering.add(Double.valueOf(s.getText().replace("$", "")));
        }
        int sizeBeforeFiltering = listOfPricesAfterFiltering.size();
        System.out.println("The Amount Of The Prices After Filtering is: " + sizeBeforeFiltering);
        return listOfPricesAfterFiltering;
    }

    public void generateNoSuchElementException() {
        By WEB_ELEMENT = By.cssSelector("a[href='https://www.youtube.com/user/Charter97video___']");
        try {
            WebElement el = driver.findElement(WEB_ELEMENT);
        } catch (NoSuchElementException y) {
            System.out.println(" 'The Web Element Not Found' " + y.getMessage());
        }
    }

    public void generateTimeoutException() {
        By WEB_ELEMENT = By.cssSelector("a[href='https://www.youtube.com/user/Charter97video___']");
        try {
            WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(WEB_ELEMENT));
        } catch (TimeoutException y) {
            System.out.println(" 'Timed out waiting for element: ' " + y.getMessage());
        }
    }

    public void generateElementNotInteractableException() {
        try {
            WebElement element = driver.findElement(By.id("hidden-element"));
            element.click();
        } catch (ElementNotInteractableException e) {
            System.out.println("Element not visible: " + e.getMessage());
        }
    }

    public void generateStaleElementReferenceException() {
        try {
            WebElement element = driver.findElement(By.id("dynamic-element"));
            element.click();
        } catch (StaleElementReferenceException e) {
            System.out.println("Element is stale: " + e.getMessage());
        }
    }

    public void generateNoSuchWindowException() {
        try {
            String currentWindowHandle = driver.getWindowHandle();
            driver.switchTo().window("nonexistent-window-handle");
        } catch (NoSuchWindowException e) {
            System.out.println("No such window: " + e.getMessage());
        }
    }

    public void generateInvalidSelectorException() {
        try {
            driver.findElement(By.cssSelector("invalid-selector"));
        } catch (InvalidSelectorException e) {
            System.out.println("Invalid selector: " + e.getMessage());
        }
    }

    public void generateNoSuchFrame() {
        try {
            // Switch to the iframe
            WebElement iframe = driver.findElement(By.tagName("iframe"));
            driver.switchTo().frame(iframe);

            // Perform some action on the elements within the iframe
            WebElement element = driver.findElement(By.id("someElement"));
            element.click();

            // Switch back to the default content
            driver.switchTo().defaultContent();
        } catch (NoSuchFrameException e) {
            // Handle the exception if the iframe is not found
            System.out.println("Iframe not found: " + e.getMessage());
        }
    }

    public void generateNoSuchSessionException() {
        // Assume the WebDriver session has been closed or timed out
        try {
            driver.getTitle();
        } catch (NoSuchSessionException e) {
            System.out.println("Caught NoSuchSessionException: " + e.getMessage());
        }
    }

    public void findBrokenLinks() {
        List<String> listOfBrokenURLs = new ArrayList<>();  // List Of URLs
        List<String> listOfWorkingURLs = new ArrayList<>();  // List Of URLs
        List<WebElement> listOfLinks = driver.findElements(By.tagName("a")); // List Of Links:

        System.out.println("The Amount Of Links are: " + listOfLinks.size());
        for (WebElement s : listOfLinks) {
            String url = s.getAttribute("href");

            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
                httpURLConnection.setRequestMethod("HEAD");
                httpURLConnection.connect();
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode >= 400) {
//                    System.out.println(url + " is broken");
                    listOfBrokenURLs.add(url);
                } else {
//                    System.out.println(url + " is working fine");
                    listOfWorkingURLs.add(url);
                }

            } catch (Exception e) {
                System.out.println(url + " is a broken link due to exception: " + e.getMessage());
            }
        }

        Iterator<String> it = listOfBrokenURLs.iterator();
        System.out.println("The Broken Links: ===> ");
        System.out.println("There Are '" + listOfBrokenURLs.size() + "' Broken Links!!! ");

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
    public void findBrokenImages() {
        List<String> listOfBrokenURLs = new ArrayList<>();  // List Of URLs
        List<String> listOfWorkingURLs = new ArrayList<>();  // List Of URLs
        List<WebElement> listOfLinks = driver.findElements(By.tagName("img")); // List Of Links:

        System.out.println("The Amount Of Links are: " + listOfLinks.size());
        for (WebElement s : listOfLinks) {
            String url = s.getAttribute("src");

            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
                httpURLConnection.setRequestMethod("HEAD");
                httpURLConnection.connect();
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode >= 400) {
//                    System.out.println(url + " is broken");
                    listOfBrokenURLs.add(url);
                } else {
//                    System.out.println(url + " is working fine");
                    listOfWorkingURLs.add(url);
                }

            } catch (Exception e) {
                System.out.println(url + " is a broken link due to exception: " + e.getMessage());
            }
        }

        Iterator<String> it = listOfBrokenURLs.iterator();
        System.out.println("The Broken Images: ===> ");
        System.out.println("There Are '" + listOfBrokenURLs.size() + "' Broken Images!!! ");

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
