package objects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiter {

    public static void waitForTitle(WebDriver driver, final String title){
        new WebDriverWait(driver, 60).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return webDriver.getTitle().equals(title);
            }
        });
    }

    public static void waitForAlert(WebDriver driver, final String alertTitle){
        new WebDriverWait(driver, 60).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                Alert alert = webDriver.switchTo().alert();
                return alert.getText().equals(alertTitle);
            }
        });
    }

    public static void waitForSimpleAlert(WebDriver driver){
        new WebDriverWait(driver, 60).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                Alert alert = webDriver.switchTo().alert();
                return true;
            }
        });
    }

    public static void waitForHandlesMoreThan(WebDriver driver, final int handleCount){
        new WebDriverWait(driver, 60).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return webDriver.getWindowHandles().size() > handleCount;
            }
        });
    }

    public static void waitForElementClickable(WebDriver driver, final By locator){
        new WebDriverWait(driver, 60).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(locator).isEnabled();
            }
        });
    }

    public static void waitForElementsPresentMoreThan(WebDriver driver, final By locator, final int count){
        new WebDriverWait(driver, 60).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElements(locator).size() > count;
            }
        });
    }

    public static void waitForTextInElementPresent(WebDriver driver, final By locator, final String text){
        new WebDriverWait(driver, 60).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(locator).getText().contains(text);
            }
        });
    }
}
