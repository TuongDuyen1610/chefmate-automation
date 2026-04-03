package core.base;

import core.driver.DriverManager;
import core.utils.WaitingHelper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

/**
 * BaseScreen.java - ĐÃ SỬA LỖI
 * Sử dụng WaitingHelper để quản lý wait và sleep
 */
public class BaseScreen {

    protected AndroidDriver getDriver() {
        return DriverManager.getDriver();
    }

    protected void click(By locator) {
        WaitingHelper.waitForClickable(locator);
        getDriver().findElement(locator).click();
        WaitingHelper.sleep(800);           // Đợi UI phản hồi nhẹ
    }

    protected void type(By locator, String text) {
        WaitingHelper.waitForVisible(locator);
        getDriver().findElement(locator).clear();
        getDriver().findElement(locator).sendKeys(text);
        WaitingHelper.sleep(600);
    }

    protected String getText(By locator) {
        WaitingHelper.waitForVisible(locator);
        return getDriver().findElement(locator).getText();
    }

    protected boolean isDisplayed(By locator) {
        try {
            WaitingHelper.waitForVisible(locator);   // Chỉ chờ visible
            return getDriver().findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void scrollToText(String text){

        logStep("Scroll to: " + text);

        getDriver().findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true))"
                                + ".scrollIntoView(new UiSelector().text(\"" + text + "\"))"
                )
        );
    }
    protected void logStep(String stepName) {
        System.out.println("📍 [SCREEN] " + stepName);
    }
}