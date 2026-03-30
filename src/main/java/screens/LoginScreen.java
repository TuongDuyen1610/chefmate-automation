package screens;

import core.base.BaseScreen;
import org.openqa.selenium.By;

/**
 * LoginScreen.java
 *
 * MỤC ĐÍCH:
 * - Đại diện cho màn hình Đăng nhập của app ChefMate
 * - Chứa tất cả locator và các hành động cơ bản trên màn hình Login
 * - Đây là nơi duy nhất chứa locator (theo đúng triết lý chúng ta đã thống nhất)
 */
public class LoginScreen extends BaseScreen {

    // ==================== LOCATORS ====================
    private final By emailField = By.xpath("//android.widget.ScrollView/android.widget.EditText[1]");           // Cần inspect thực tế
    private final By passwordField = By.xpath("//android.widget.ScrollView/android.widget.EditText[2]");
    private final By loginButton = By.xpath("//android.widget.ScrollView/android.view.View[2]/android.widget.Button");
    private final By errorMessage = By.id("error_message");
//    private final By forgotPasswordLink = By.id("forgot_password");

    // ==================== ACTIONS ====================

    /**
     * Nhập email vào ô email
     */
    public void enterEmail(String email) {
        logStep("Entering email: " + email);
        type(emailField, email);
    }

    /**
     * Nhập mật khẩu vào ô password
     */
    public void enterPassword(String password) {
        logStep("Entering password");
        type(passwordField, password);
    }

    /**
     * Nhấn nút Đăng nhập
     */
    public void clickLoginButton() {
        logStep("Clicking Login button");
        click(loginButton);
    }

    /**
     * Kiểm tra thông báo lỗi có hiển thị không (dùng cho test case login fail)
     */
//    public boolean isErrorMessageDisplayed() {
//        return isDisplayed(errorMessage);
//    }

    /**
     * Kiểm tra màn hình Login có đang hiển thị không
     */
    public boolean isLoginScreenDisplayed() {
        return isDisplayed(emailField);
    }
}