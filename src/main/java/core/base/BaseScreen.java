package core.base;

import core.driver.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * BaseScreen.java
 *
 * MỤC ĐÍCH:
 * - Lớp cơ sở cho tất cả các Screen (Page Object)
 * - Cung cấp các phương thức chung để tương tác với UI
 * - Tập trung tất cả locator và hành động cơ bản tại đây
 */
public class BaseScreen {

    protected AndroidDriver driver = DriverManager.getDriver();

    protected WebDriverWait wait = new WebDriverWait(
            DriverManager.getDriver(),
            Duration.ofSeconds(15)
    );

    // ==================== COMMON ACTIONS ====================

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    protected String getText(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void waitForElementVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForElementClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Phương thức ghi log bước thực hiện (dùng chung cho tất cả Screen)
     */
    protected void logStep(String stepName) {
        System.out.println("📍 [SCREEN] " + stepName);
    }
}