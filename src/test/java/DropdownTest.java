import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DropdownTest {

    private WebDriver driver;
    private static final String BASE_URL = "http://the-internet.herokuapp.com";

    @Test
    public void dropdownTest() throws InterruptedException {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(4, TimeUnit.SECONDS);
        driver.get(BASE_URL);

        WebElement ref = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                presenceOfElementLocated(By.linkText("Dropdown")));
        ref.click();
        WebElement dropdown = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                presenceOfElementLocated(By.id("dropdown")));
        dropdown.click();
        List<WebElement> options = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.cssSelector("option")));
        for (int i = 0; i < options.size(); i++) {
            options.get(i).click();
            Assert.assertTrue(options.get(i).isSelected());
        }

        driver.quit();
    }

}
