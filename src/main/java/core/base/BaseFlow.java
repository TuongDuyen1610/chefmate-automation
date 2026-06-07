package core.base;

import core.driver.DriverManager;
import core.utils.WaitingHelper;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

/**
 * BaseFlow.java
 * ✅ Updated: Thêm helper methods để có thể interact với UI
 *
 * MỤC ĐÍCH:
 * - Lớp cơ sở cho tất cả các Functional Flow (AuthenticationFlow, RecipeFlow, FridgeFlow...)
 * - Cho phép các Flow sau này kế thừa và mở rộng các phương thức chung
 * - Tách biệt rõ ràng giữa "luồng nghiệp vụ" và "tương tác UI"
 */

public abstract class BaseFlow {

    // ==================== DRIVER ACCESS ====================

    protected AndroidDriver getDriver() {
        return DriverManager.getDriver();
    }
    // ==================== BASIC ACTIONS ====================

    /**
     * ✅ Click trên element (với wait)
     */
    protected void click(By locator) {
        WaitingHelper.waitForClickable(locator);
        getDriver().findElement(locator).click();
        WaitingHelper.sleep(800);
    }

    /**
     * ✅ Type text vào field
     */
    protected void type(By locator, String text) {
        WaitingHelper.waitForVisible(locator);
        getDriver().findElement(locator).clear();
        getDriver().findElement(locator).sendKeys(text);
        WaitingHelper.sleep(600);
    }

    /**
     * ✅ Lấy text từ element
     */
    protected String getText(By locator) {
        WaitingHelper.waitForVisible(locator);
        return getDriver().findElement(locator).getText();
    }

    /**
     * ✅ Kiểm tra element có hiển thị không
     */
    protected boolean isDisplayed(By locator) {
        try {
            WaitingHelper.waitForVisible(locator);
            return getDriver().findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== LOGGING ====================

    protected void logStep(String stepName) {
        System.out.println("📍 [FLOW] Executing: " + stepName);
    }

}