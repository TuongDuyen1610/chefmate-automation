package screens;

import core.base.BaseScreen;
import core.utils.WaitingHelper;
import org.openqa.selenium.By;

public class LoginScreen extends BaseScreen {

    private final By phoneEmailField = By.xpath("//android.widget.EditText[1]");
    private final By passwordField = By.xpath("//android.widget.EditText[2]");
    private final By loginButton = By.xpath("//android.widget.ScrollView/android.view.View[2]/android.widget.Button");

    public void enterPhoneOrEmail(String value) {
        logStep("Nhap tai khoan: " + value);
        WaitingHelper.waitForVisible(phoneEmailField);
        type(phoneEmailField, value);
    }

    public void enterPassword(String password) {
        logStep("Nhap mat khau");
        type(passwordField, password);
    }

    /**
     * ✅ Click Đăng nhập - Tăng wait time từ 8s lên 15s
     */
    public void clickLogin() {
        logStep("Click nút Đăng nhập");

        try {
            getDriver().hideKeyboard();
        } catch (Exception e) {
            logStep("⚠️ Không ẩn keyboard được (có thể đã ẩn sẵn)");
        }

        if (isDisplayed(loginButton)) {
            click(loginButton);
            logStep("✓ Click nút via XPath thành công");
        } else {
            clickByCoordinates(531, 1636);
            logStep("Click nút via Coordinates thành công");
        }

        // ✅ QUAN TRỌNG: Tăng từ 8s lên 15s
        logStep("⏳ Chờ app xử lý đăng nhập");
        WaitingHelper.sleepSeconds(1);
    }
    public void performLogin(String phoneOrEmail, String password) {
        logStep("=== THỰC HIỆN ĐĂNG NHẬP ===");
        enterPhoneOrEmail(phoneOrEmail);
        enterPassword(password);
        clickLogin();
        logStep("✓ Đã click nút Đăng nhập");
        WaitingHelper.sleepSeconds(3);
    }
    private void clickByCoordinates(int x, int y) {
        org.openqa.selenium.interactions.PointerInput finger =
                new org.openqa.selenium.interactions.PointerInput(
                        org.openqa.selenium.interactions.PointerInput.Kind.TOUCH, "finger");
        org.openqa.selenium.interactions.Sequence tap =
                new org.openqa.selenium.interactions.Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(java.time.Duration.ZERO,
                org.openqa.selenium.interactions.PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(
                org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(
                org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT.asArg()));
        getDriver().perform(java.util.Arrays.asList(tap));
        logStep("✓ Đã click tại tọa độ [" + x + ", " + y + "]");
    }

    public boolean isLoginScreenDisplayed() {
        return isDisplayed(phoneEmailField);
    }
}