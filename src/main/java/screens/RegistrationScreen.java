package screens;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.TimeoutException;
//import java.time.Duration;
import core.base.BaseScreen;
import core.utils.AllureHelper;
import core.utils.WaitingHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import core.utils.AllureHelper;
import io.qameta.allure.Step;

import java.time.Duration;
import java.util.Collections;
//import org.openqa.selenium.Keys;
//import java.util.Map;
//import java.util.HashMap;
/**
 * RegistrationScreen.java
 * Đại diện cho màn hình Đăng ký
 * ✅ Fixed: Import WebElement, clear field, checkbox handling
 */
public class RegistrationScreen extends BaseScreen {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationScreen.class);

    // ==================== HEADER LOCATORS ====================
    private final By headerTitle = By.xpath("(//android.widget.TextView[@text='Đăng ký'])[1]");
    private final By headerSubtitle = By.xpath("//android.widget.TextView[@text='Chào mừng đến với BepTroLy - Bepes']");

    // ==================== INPUT FIELDS LOCATORS ====================
    private final By fullNameField = By.xpath("//android.widget.ScrollView/android.widget.EditText[1]");
    private final By phoneField = By.xpath("//android.widget.ScrollView/android.widget.EditText[2]");
    private final By emailField = By.xpath("//android.widget.ScrollView/android.widget.EditText[3]");
    private final By passwordField = By.xpath("//android.widget.ScrollView/android.widget.EditText[4]");
    private final By confirmPasswordField = By.xpath("//android.widget.ScrollView/android.widget.EditText[5]");

    // ==================== CHECKBOX & BUTTON ====================
    private final By checkboxTerms = By.xpath("//android.widget.CheckBox");
    private final By registerButton = By.xpath("(//android.widget.TextView[@text='Đăng ký'])[2]");
    // ==================== LABELS ====================
    private final By lblFullName = By.xpath("//android.widget.TextView[@text='Họ và tên']");
    private final By lblPhone = By.xpath("//android.widget.TextView[@text='Số điện thoại']");
    private final By lblEmail = By.xpath("//android.widget.TextView[@text='Email']");
    private final By lblPassword = By.xpath("//android.widget.TextView[@text='Mật khẩu']");
    private final By lblConfirmPassword = By.xpath("//android.widget.TextView[@text='Xác nhận mật khẩu']");
    private final BaseScreen base = new BaseScreen();

    // ==================== ERROR MESSAGE LOCATORS ====================

    // TC02 → TC07
    private final By toastFillAllInfo = By.xpath("//android.widget.Toast[@text='Vui lòng điền đầy đủ thông tin']");

    // TC08
    private final By toastPasswordMismatch = By.xpath("//android.widget.Toast[@text='Mật khẩu không khớp']");

    // TC09
    private final By toastTermsRequired = By.xpath("//android.widget.Toast[@text='Vui lòng đồng ý với điều khoản dịch vụ']");

    //TC13
    private final By loginToRegister =
            By.xpath("//android.widget.TextView[@text='Đăng nhập ngay']");
    // ==================== VERIFY SCREEN ====================

    public boolean isRegistrationScreenDisplayed() {
        logStep("Verify RegisterScreen");
        try {
//            WaitingHelper.waitForVisible(headerTitle);
            return isDisplayed(headerTitle);
        } catch (Exception e) {
            logStep("RegisterScreen not display");
            return false;
        }
    }

    // ==================== INPUT ACTIONS ====================

    public void enterFullName(String fullName) {
        logStep("Enter fullName: " + fullName);
//        WaitingHelper.waitForVisible(fullNameField);
        type(fullNameField, fullName);
    }

    public void enterPhone(String phone) {
        logStep("Enter phone: " + phone);
//        WaitingHelper.waitForVisible(phoneField);
        type(phoneField, phone);
    }

    public void enterEmail(String email) {
        logStep("Enter Email: " + email);
//        WaitingHelper.waitForVisible(emailField);
        type(emailField, email);
    }

    public void enterPassword(String password) {
        logStep("Enter password: " + password);
//        WaitingHelper.waitForVisible(passwordField);
        type(passwordField, password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        logStep("Enter confirmPassword: " + confirmPassword);
//        WaitingHelper.waitForVisible(confirmPasswordField);
        type(confirmPasswordField, confirmPassword);
    }

    public void tickTermsCheckbox() {
        logStep("Tick checkbox Term");
        try {
//            WaitingHelper.waitForClickable(checkboxTerms);
            WebElement checkbox = getDriver().findElement(checkboxTerms);

            // Check nếu chưa được tick
            if (!checkbox.isSelected()) {
                click(checkboxTerms);
                logStep("✓ Ticked checkbox");
            } else {
                logStep("⚠️ Checkbox tick available");
            }
        } catch (Exception e) {
            logStep("❌ Untick checkbox: " + e.getMessage());
            throw e;
        }
    }

    public void untickTermsCheckbox() {
        logStep("Don't tick checkbox term");
        try {
//            WaitingHelper.waitForClickable(checkboxTerms);
            WebElement checkbox = getDriver().findElement(checkboxTerms);

            // Uncheck nếu đã được tick
            if (checkbox.isSelected()) {
                click(checkboxTerms);
                logStep("✓ Unticked checkbox");
            } else {
                logStep("⚠️ Checkbox untick");
            }
        } catch (Exception e) {
            logStep("❌ Can't tick checkbox: " + e.getMessage());
            throw e;
        }
    }

    public boolean isCheckboxTicked() {
        logStep("Verify: checkbox ticked ?");
        try {
            WebElement checkbox = getDriver().findElement(checkboxTerms);
            return checkbox.isSelected();
        } catch (Exception e) {
            logStep("❌ Not check status checkbox");
            return false;
        }
    }

    public void clickRegisterButton() {

        try {
            slowSwipeDownOnScreen(1);
            click(registerButton);
            logStep(" Clicked nut dang ky");
        } catch (Exception e) {
            logStep("Khong click duoc dang ky");
            throw e;
        }

//        WaitingHelper.sleepSeconds(7); // Chờ backend xử lý
    }

    public void clickLogintoRegister(){
        logStep("Click nut Dang nhap ngay");
        try {
            scrollToText("Đăng nhập ngay");
            click(loginToRegister);
            logStep("Da click dang nhap ngay tu man dang ky");
        } catch (Exception e) {
            logStep("Khong click duoc dang nhap ngay");
            throw e;
        }
    }
    // ==================== HELPER METHODS ====================

    public void performRegistration(String fullName, String phone, String email, String password, String confirmPassword) {
        logStep("=== THUC HIEN DANG KY ===");
        enterFullName(fullName);
        enterPhone(phone);
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        tickTermsCheckbox();
        logStep("Click nut Dang ky");
        try {
            slowSwipeDownOnScreen(1);
            scrollToText("Đăng ký");
            click(registerButton);
            logStep("Da click dang ky tu man dang ky");
        } catch (Exception e) {
            logStep("Khong click duoc dang ky");
            throw e;
        }
    }

    // ==================== CLEAR FIELD METHODS ====================

    public void clearField(By field) {
        logStep("Clear field");
        try {
            WaitingHelper.waitForVisible(field);
            WebElement element = getDriver().findElement(field);
            element.clear();
            logStep(" Cleared field");
        } catch (Exception e) {
            logStep("❌ Can't clear field: " + e.getMessage());
        }
    }

    public void clearFullNameField() {
        logStep("Clear Fullname");
        clearField(fullNameField);
    }

    public void clearPhoneField() {
        logStep("Clear phone");
        clearField(phoneField);
    }

    public void clearEmailField() {
        logStep("Clear Email");
        clearField(emailField);
    }

    public void clearPasswordField() {
        logStep("Clear password");
        clearField(passwordField);
    }

    public void clearConfirmPasswordField() {
        logStep("Clear confirm password");
        clearField(confirmPasswordField);
    }

    public void clearAllFields() {
        logStep("Clear All field");
        clearFullNameField();
        clearPhoneField();
        clearEmailField();
        clearPasswordField();
        clearConfirmPasswordField();
    }

//    // ==================== VERIFY FIELD VALUES ====================
//
//    public String getFullNameValue() {
//        WebElement element = getDriver().findElement(fullNameField);
//        return element.getAttribute("text");
//    }
//
//    public String getPhoneValue() {
//        WebElement element = getDriver().findElement(phoneField);
//        return element.getAttribute("text");
//    }
//
//    public String getEmailValue() {
//        WebElement element = getDriver().findElement(emailField);
//        return element.getAttribute("text");
//    }

    // ==================== VERIFY ERROR MESSAGES ====================
    public boolean isFillAllInfoErrorDisplayed() {

        logStep("🔍 Verify Toast: Vui long đien đay đu thong tin");

        try {
            getDriver().findElement(toastFillAllInfo);
            logger.info("✅ Toast error msg hien thi thanh cong");
            AllureHelper.attachScreenshot("Toast error displayed");
            logStep("✅ Toast displayed");
            return true;
        } catch (Exception e) {
            logStep("❌ Not Toast");
            logger.info("✅ Toast error msg khong hien thi ");
            AllureHelper.attachScreenshot("Toast error not displayed");
            return false;
        }

    }

    public boolean isFillAllInfoErrorDisplayed_MK() {

        logStep("🔍 Verify Toast: Vui long đien đay đu thong tin");

        try {
            getDriver().findElement(toastPasswordMismatch);
            logger.info("✅ Toast error msg hien thi thanh cong");
            AllureHelper.attachScreenshot("Toast error displayed");
            logStep("✅ Toast displayed");
            return true;
        } catch (Exception e) {
            logger.info("✅ Toast error msg khong hien thi ");
            AllureHelper.attachScreenshot("Toast error not displayed");
            return false;
        }
    }
    public boolean isFillAllInfoErrorDisplayed_DK() {

        logStep("🔍 Verify Toast: Vui long đien đay đu thong tin");

        try {
            getDriver().findElement(toastTermsRequired);
            logger.info("✅ Toast error msg hien thi thanh cong");
            AllureHelper.attachScreenshot("Toast error displayed");
            logStep("✅ Toast displayed");
            return true;
        } catch (Exception e) {
            logger.info("✅ Toast error msg khong hien thi ");
            AllureHelper.attachScreenshot("Toast error not displayed");
            return false;
        }
    }

    // ==================== UNHAPPY CASE SCENARIOS ====================

    /**
     * Scenario: Để trống field nhưng vẫn click Đăng ký
     */
    public void registerWithoutFullName(String phone, String email, String password) {
        logStep("=== Register Without FullName ===");
        clearFullNameField();
        enterPhone(phone);
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(password);
        tickTermsCheckbox();
        clickRegisterButton();
    }

    public void registerWithoutPhone(String fullName, String email, String password) {
        logStep("=== Register Without Phone ===");
        enterFullName(fullName);
        clearPhoneField();
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(password);
        tickTermsCheckbox();
        clickRegisterButton();
    }

    public void registerWithoutEmail(String fullName, String phone, String password) {
        logStep("=== Register Without Email ===");
        enterFullName(fullName);
        enterPhone(phone);
        clearEmailField();
        enterPassword(password);
        enterConfirmPassword(password);
        tickTermsCheckbox();
        clickRegisterButton();
    }

    public void registerWithoutPassword(String fullName, String phone, String email) {
        logStep("=== Register Without Password ===");
        enterFullName(fullName);
        enterPhone(phone);
        enterEmail(email);
        clearPasswordField();
        enterConfirmPassword("");
        tickTermsCheckbox();
        clickRegisterButton();
    }

    public void registerWithoutConfirmPassword(String fullName, String phone, String email, String password) {
        logStep("=== Register Without Confirm Password ===");
        enterFullName(fullName);
        enterPhone(phone);
        enterEmail(email);
        enterPassword(password);
        clearConfirmPasswordField();
        tickTermsCheckbox();
        clickRegisterButton();
    }

    public void registerWithMismatchPassword(String fullName, String phone, String email, String password, String confirmPassword) {
        logStep("=== Register With Mismatch Password ===");
        enterFullName(fullName);
        enterPhone(phone);
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        tickTermsCheckbox();
        clickRegisterButton();
    }

    public void registerWithoutTermsAgreement(String fullName, String phone, String email, String password) {
        logStep("=== Register Without Terms Agreement ===");
        enterFullName(fullName);
        enterPhone(phone);
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(password);
        // NOT tick checkbox
        clickRegisterButton();
    }

    public void registerWithAllFieldsEmpty() {
        logStep("=== Register With All Fields Empty ===");
        clearAllFields();
        // NOT tick checkbox
        clickRegisterButton();
    }
    @Step("Swipe down on screen (times={times})")
    public void slowSwipeDownOnScreen(int times) {
        Dimension size = getDriver().manage().window().getSize();

        int x = size.width / 2;
        int startY = (int) (size.height * 0.75);
        int endY = (int) (size.height * 0.30);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        for (int i = 1; i <= times; i++) {
            Sequence swipe = new Sequence(finger, 1);
            swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, startY));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), x, endY));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            getDriver().perform(Collections.singletonList(swipe));
            WaitingHelper.sleepSeconds(1);
        }

        // ❌ bỏ attachScreenshot ở đây
    }
}