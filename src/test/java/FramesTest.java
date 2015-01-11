import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class FramesTest {

    private WebDriver driver;
    private static final String BASE_URL = "http://the-internet.herokuapp.com";
    private static final String IFRAME_TEXT = "123456";

    @BeforeMethod
    public void setup() throws InterruptedException {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(4, TimeUnit.SECONDS);
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void nestedFramesTest(){
        WebElement ref = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                presenceOfElementLocated(By.linkText("Frames")));
        ref.click();
        WebElement ref2 = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                presenceOfElementLocated(By.linkText("Nested Frames")));
        ref2.click();
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");
        Assert.assertTrue(driver.getPageSource().contains("LEFT"));
        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-middle");
        Assert.assertTrue(driver.getPageSource().contains("MIDDLE"));
        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-right");
        Assert.assertTrue(driver.getPageSource().contains("RIGHT"));
        driver.switchTo().parentFrame();
        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-bottom");
        Assert.assertTrue(driver.getPageSource().contains("BOTTOM"));
    }

    @Test
    public void iFramesTest() {
        WebElement ref = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                presenceOfElementLocated(By.linkText("Frames")));
        ref.click();
        WebElement ref2 = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                presenceOfElementLocated(By.linkText("iFrame")));
        ref2.click();
        driver.switchTo().frame("mce_0_ifr");
        WebElement content = driver.findElement(By.id("tinymce"));
        content.clear();
        content.sendKeys(IFRAME_TEXT);
        Assert.assertTrue(content.getText().contains(IFRAME_TEXT));
    }
}
