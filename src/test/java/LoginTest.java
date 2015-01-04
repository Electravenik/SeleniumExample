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

public class LoginTest {

    private WebDriver driver;
    private static final String BASE_URL = "http://the-internet.herokuapp.com";

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

    public void LoginTest(String input_username, String input_password){
        WebElement ref = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                presenceOfElementLocated(By.linkText("Form Authentication")));
        ref.click();
        WebElement username = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                presenceOfElementLocated(By.id("username")));
        WebElement password = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                presenceOfElementLocated(By.id("password")));
        WebElement button = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                presenceOfElementLocated(By.cssSelector("button")));
        username.sendKeys(input_username);
        password.sendKeys(input_password);
        button.click();
    }

    @Test
    public void positiveLoginTest(){
        LoginTest("tomsmith","SuperSecretPassword!");
        Assert.assertTrue(driver.getCurrentUrl().contains("http://the-internet.herokuapp.com/secure"));
    }

    @Test
    public void wrongPasswordLoginTest(){
        LoginTest("tomsmith","supersecretpassword!");
        Assert.assertFalse(driver.getCurrentUrl().contains("http://the-internet.herokuapp.com/secure"));
    }

    @Test
    public void wrongUsernameLoginTest(){
        LoginTest("TomSmith","SuperSecretPassword!");
        Assert.assertFalse(driver.getCurrentUrl().contains("http://the-internet.herokuapp.com/secure"));
    }
}
