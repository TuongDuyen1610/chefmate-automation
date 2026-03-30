package core.utils;

import core.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitingHelper {

    private static final WebDriverWait wait = new WebDriverWait(
            DriverManager.getDriver(), Duration.ofSeconds(Constants.EXPLICIT_WAIT));

    public static void waitForVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}