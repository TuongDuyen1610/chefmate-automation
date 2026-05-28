package screens;
import org.openqa.selenium.By;
import core.base.BaseScreen;
import core.utils.WaitingHelper;

import core.utils.AllureHelper;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginScreen extends BaseScreen {
    private static final Logger logger = LoggerFactory.getLogger(LoginScreen.class);

    private final By phoneEmailField = By.xpath("//android.widget.EditText[1]");
    private final By passwordField = By.xpath("//android.widget.EditText[2]");
    private final By loginButton = By.xpath("//android.widget.ScrollView/android.view.View[2]/android.widget.Button");
    private final By errorToast = By.xpath("//android.widget.Toast[@text='Vui lòng nhập đầy đủ thông tin']");
    private final By errorToastLoginFail = By.xpath("//android.widget.Toast[@text='Đăng nhập thất bại. Vui lòng thử lại!']");
    private final By iconShowHide = By.xpath("//android.widget.ScrollView/android.widget.EditText[2]/android.view.View/android.widget.Button");
    @Step("📍 Enter phone/email: {value}")
    public void enterPhoneOrEmail(String value) {
        logStep("Enter email: " + value);
        AllureHelper.attachScreenshot("Enter email");

        type(phoneEmailField, value);

    }

    @Step("📍 Enter password")
    public void enterPassword(String password) {
        logStep("Enter password: "+ password);
        AllureHelper.attachScreenshot("Enter password");

        type(passwordField, password);
    }
    @Step("📍 Click login button")
    public void clickLogin() {
        logStep("Click btn Login");
        AllureHelper.attachScreenshot("Click login button");

        click(loginButton);

    }
    public void clickShowHidePassword(){
        logStep("Click icon show/hide password");
        AllureHelper.attachScreenshot("Click icon show/hide password");
        click(iconShowHide);
    }
    @Step("📍 Perform login with credentials")
    public void performLogin(String phoneOrEmail, String password) {
        logStep("=== THUC HIEN DANG NHAP ===");

        enterPhoneOrEmail(phoneOrEmail);
        enterPassword(password);
        clickLogin();
    }
    @Step("📍 Perform login with credentials")
    public void performLogin2(String phoneOrEmail, String password) {
        logStep("=== THUC HIEN DANG NHAP ===");

        enterPhoneOrEmail(phoneOrEmail);
        enterPassword(password);
        clickShowHidePassword();
        AllureHelper.attachScreenshot("Icon show password");
        clickShowHidePassword();
        AllureHelper.attachScreenshot("Icon hide password");

    }
    @Step("📍 Verify login screen displayed")
    public boolean isLoginScreenDisplayed() {
        logger.info("🔍 Verifying login screen is displayed");
        AllureHelper.step("Verify login screen displayed");

        boolean isDisplayed = isDisplayed(phoneEmailField);

        if (isDisplayed) {
            logger.info("✅ Login screen is displayed");
            AllureHelper.attachScreenshot("Login Screen Displayed");
        } else {
            logger.error("❌ Login screen NOT displayed");
            AllureHelper.attachScreenshot("Login Screen NOT Found");
        }

        return isDisplayed;
    }

    // ✅ COPY LOGIC CŨ - performLogin_TC10
    public void performLogin_TC06(String password) {
        logStep("=== THUC HIEN DANG NHAP ===");
        enterPassword(password);
    }

    // ✅ COPY LOGIC CŨ - performLogin_TC11
    public void performLogin_TC07(String phoneOrEmail) {
        logStep("=== THUC HIEN DANG NHAP ===");
        enterPhoneOrEmail(phoneOrEmail);
    }

    // ✅ COPY LOGIC CŨ - isToastUpdateInfoDisplayed_TC0607
    public boolean isToastUpdateInfoDisplayed_TC0607() {
        logStep("🔍 Verify Toast: Update Success");
        AllureHelper.step("Verify error toast");

        try {
            logger.info("⏳ Waiting for toast to appear (2 seconds)...");
            Thread.sleep(0000); // chờ toast xuất hiện

            // ✅ LOGIC CŨ CỦA CHỊ
            getDriver().findElement(errorToast);

            logger.info("✅ Toast displayed successfully");
            AllureHelper.attachScreenshot("✅ Toast Message Found");
            return true;
        } catch (Exception e) {
            logger.error("❌ Toast NOT displayed: " + e.getMessage());
            AllureHelper.attachScreenshot("❌ Toast NOT Found");
            AllureHelper.attachErrorMessage("Toast not found: " + e.getMessage());
            return false;
        }
    }
    public boolean isToastLoginFail() {
        logStep("🔍 Verify Toast: Update Success");
        AllureHelper.step("Verify error toast");

        try {
            logger.info("⏳ Waiting for toast to appear (2 seconds)...");
            Thread.sleep(0000); // chờ toast xuất hiện

            // ✅ LOGIC CŨ CỦA CHỊ
            getDriver().findElement(errorToastLoginFail);

            logger.info("✅ Toast displayed successfully");
            AllureHelper.attachScreenshot("✅ Toast Message Found");
            return true;
        } catch (Exception e) {
            logger.error("❌ Toast NOT displayed: " + e.getMessage());
            AllureHelper.attachScreenshot("❌ Toast NOT Found");
            AllureHelper.attachErrorMessage("Toast not found: " + e.getMessage());
            return false;
        }
    }

}