

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import static objects.Waiter.*;

public class WaiterTest {
    private WebDriver driver;
    private static final String BASE_URL = "http://onliner.by";
    private static final String SECOND_URL = "http://the-internet.herokuapp.com";
    private static final String PEOPLE_TITLE = "Люди onliner.by";
    private static final String ALERT_TEXT = "I am a JS Alert";

    @BeforeMethod
    public void setup() throws InterruptedException {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(4, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void waitForTitleTest(){
        driver.get(BASE_URL);
        driver.findElement(By.linkText("Люди")).click();
        waitForTitle(driver, PEOPLE_TITLE);
    }

    @Test
    public void waitForAlertTest(){
        driver.get(SECOND_URL);
        driver.findElement(By.linkText("JavaScript Alerts")).click();
        WebElement promptButton = driver.findElement(By.cssSelector("button[onclick*=jsAlert]"));
        promptButton.click();
        waitForAlert(driver, ALERT_TEXT);
    }

    @Test
    public void waitForSimpleAlertTest(){
        driver.get(SECOND_URL);
        driver.findElement(By.linkText("JavaScript Alerts")).click();
        WebElement promptButton = driver.findElement(By.cssSelector("button[onclick*=jsAlert]"));
        promptButton.click();
        waitForSimpleAlert(driver);
    }

    @Test
    public void waitForHandlesMoreThanTest(){
        driver.get(BASE_URL);
        driver.get(SECOND_URL);
        waitForHandlesMoreThan(driver, 1);
    }

    @Test
    public void waitForElementClickableTest(){
        driver.get(BASE_URL);
        waitForElementClickable(driver, By.cssSelector(".top-search-button"));
    }


    @Test
    public void waitForElementsPresentMoreThanTest(){
        driver.get(BASE_URL);
        waitForElementsPresentMoreThan(driver, By.tagName("script"), 10);
    }

    @Test
    public void waitForTextInElementPresentTest(){
        driver.get(SECOND_URL);
        waitForTextInElementPresent(driver, By.id("content"), "Available Examples");
    }
}
