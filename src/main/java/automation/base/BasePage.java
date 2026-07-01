package automation.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void waitForElementToBeClickable(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void waitForElementToBeVisible(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public String getText(By by) {
        waitForElementToBeVisible(by);
        return driver.findElement(by).getText();
    }

    public void clickElement(By by) {
        waitForElementToBeClickable(by);
        try {
            driver.findElement(by).click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            // Fallback strategy: Bypass rendering overlays via JavaScript execution
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", driver.findElement(by));
        }
    }

    public void enterText(By by, String text) {
        waitForElementToBeVisible(by);
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(text);
    }

    public boolean compareText(By by, String expectedText) {
        waitForElementToBeVisible(by);
        return driver.findElement(by).getText().equals(expectedText);
    }

    public boolean isDisplayed(By by) {
        try {
            waitForElementToBeVisible(by);
            return driver.findElement(by).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}