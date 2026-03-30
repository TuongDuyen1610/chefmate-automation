package screens;

import core.base.BaseScreen;
import org.openqa.selenium.By;

public class LoginScreen extends BaseScreen {

    private final By phoneOrEmailField = By.xpath("//android.widget.ScrollView/android.widget.EditText[1]");
    private final By passwordField = By.xpath("//android.widget.ScrollView/android.widget.EditText[2]");
    private final By togglePasswordVisibility = By.xpath("//android.widget.ScrollView/android.widget.EditText[2]/android.view.View/android.widget.Button");
    private final By loginButton = By.xpath("//android.widget.ScrollView/android.view.View[2]/android.widget.Button");
    private final By registerLink = By.xpath("//android.widget.TextView[@text='Đăng ký ngay']");

    public void enterPhoneOrEmail(String value) {
        logStep("Nhập số điện thoại / email: " + value);
        type(phoneOrEmailField, value);
    }

    public void enterPassword(String password) {
        logStep("Nhập mật khẩu");
        type(passwordField, password);
    }

    public void togglePasswordVisibility() {
        logStep("Bật/tắt hiển thị mật khẩu");
        click(togglePasswordVisibility);
    }

    public void clickLoginButton() {
        logStep("Nhấn nút Đăng nhập");
        click(loginButton);
    }

    public void clickRegisterLink() {
        logStep("Nhấn nút Đăng ký ngay");
        click(registerLink);
    }

    public boolean isLoginScreenDisplayed() {
        return isDisplayed(phoneOrEmailField);
    }
}