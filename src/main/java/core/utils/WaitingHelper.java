package core.utils;

import core.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * WaitingHelper.java
 *
 * MỤC ĐÍCH:
 * - Class chuyên xử lý chờ (wait) và sleep
 * - Tái sử dụng ở tất cả Screen và Flow
 * - Tránh lặp code sleep và wait
 */
public class WaitingHelper {

    private static final int EXPLICIT_WAIT = 20; // giây

    private static WebDriverWait getWait() {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT));
    }

    public static void waitForVisible(By locator) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForClickable(By locator) {
        getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Sleep an toàn (không throw exception)
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Sleep theo số giây (dễ dùng hơn)
     */
    public static void sleepSeconds(int seconds) {
        sleep(seconds * 1000L);
    }
}