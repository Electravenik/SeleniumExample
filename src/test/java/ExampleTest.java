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

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ExampleTest {

    WebDriver driver;
    public static final String SITE_ONE = "http://www.soccer.ru/";
    public static final String SITE_TWO = "http://www.yaplakal.com/";

    @BeforeMethod
    public void setup() throws InterruptedException {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(SITE_ONE);
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

    @Test
    public void backTest() throws InterruptedException {
        driver.get(SITE_TWO);
        driver.navigate().back();
        Assert.assertTrue(driver.getCurrentUrl().contains(SITE_ONE));
    }

    @Test
    public void forwardTest() throws InterruptedException {
        driver.get(SITE_TWO);
        driver.navigate().back();
        driver.navigate().forward();
        Assert.assertTrue(driver.getCurrentUrl().contains(SITE_TWO));
    }

    @Test
    public void refreshTest() throws InterruptedException {
        driver.navigate().refresh();
        Assert.assertTrue(driver.getCurrentUrl().contains(SITE_ONE));
    }

    @Test
    public void byIdTest(){
        WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                presenceOfElementLocated(By.id("haha")));
        Assert.assertTrue(element.isEnabled());
    }

    @Test
    public void byNameTest(){
        WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                presenceOfElementLocated(By.name("AllowScriptAccess")));
        Assert.assertTrue(element.isEnabled());
    }

    @Test
    public void byClassNameTest(){
        driver.get(SITE_TWO);
        WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                presenceOfElementLocated(By.className("no-compress")));
        Assert.assertTrue(element.isEnabled());
    }

    @Test
    public void byTagNameTest(){
        WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                presenceOfElementLocated(By.tagName("script")));
        Assert.assertTrue(element.isEnabled());
    }

    @Test
    public void byLinkTextTest(){
        WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                presenceOfElementLocated(By.linkText("Англия")));
        Assert.assertTrue(element.isEnabled());
    }

    @Test
    public void byPartialLinkTextTest(){
        WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                presenceOfElementLocated(By.partialLinkText("глия")));
        Assert.assertTrue(element.isEnabled());
    }

    @Test
    public void byCSSSelectorTest(){
        WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                presenceOfElementLocated(By.cssSelector("script")));
        Assert.assertTrue(element.isEnabled());
    }

    @Test
    public void byXPathTest(){
        WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//script")));
        Assert.assertTrue(element.isEnabled());
    }

    @Test
    public void findElementTest(){
        WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                presenceOfElementLocated(By.xpath(".//*[@id='contacts']/a[2]")));
        Assert.assertEquals(element.getText(), "Регистрация");

    }

    @Test
    public void findElementsTest(){
        List<WebElement> elements = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.tagName("script")));
        Assert.assertEquals(elements.size(), 67);
    }
}
