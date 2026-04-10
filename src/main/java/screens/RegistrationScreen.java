package screens;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.TimeoutException;
//import java.time.Duration;
import core.base.BaseScreen;
import core.utils.AllureHelper;
import core.utils.WaitingHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import core.utils.AllureHelper;
import io.qameta.allure.Step;
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
        logStep("Verify: Kiểm tra màn Đăng ký hiển thị");
        try {
            WaitingHelper.waitForVisible(headerTitle);
            return isDisplayed(headerTitle);
        } catch (Exception e) {
            logStep("❌ Màn Đăng ký không hiển thị");
            return false;
        }
    }

    // ==================== INPUT ACTIONS ====================

    public void enterFullName(String fullName) {
        logStep("Nhập Họ và tên: " + fullName);
        WaitingHelper.waitForVisible(fullNameField);
        type(fullNameField, fullName);
    }

    public void enterPhone(String phone) {
        logStep("Nhập Số điện thoại: " + phone);
        WaitingHelper.waitForVisible(phoneField);
        type(phoneField, phone);
    }

    public void enterEmail(String email) {
        logStep("Nhập Email: " + email);
        WaitingHelper.waitForVisible(emailField);
        type(emailField, email);
    }

    public void enterPassword(String password) {
        logStep("Nhập Mật khẩu");
        WaitingHelper.waitForVisible(passwordField);
        type(passwordField, password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        logStep("Nhập Xác nhận Mật khẩu");
        WaitingHelper.waitForVisible(confirmPasswordField);
        type(confirmPasswordField, confirmPassword);
    }

    public void tickTermsCheckbox() {
        logStep("Tick checkbox Điều khoản");
        try {
            WaitingHelper.waitForClickable(checkboxTerms);
            WebElement checkbox = getDriver().findElement(checkboxTerms);

            // Check nếu chưa được tick
            if (!checkbox.isSelected()) {
                click(checkboxTerms);
                logStep("✓ Đã tick checkbox");
            } else {
                logStep("⚠️ Checkbox đã được tick sẵn");
            }
        } catch (Exception e) {
            logStep("❌ Không thể tick checkbox: " + e.getMessage());
            throw e;
        }
    }

    public void untickTermsCheckbox() {
        logStep("Bỏ tick checkbox Điều khoản");
        try {
            WaitingHelper.waitForClickable(checkboxTerms);
            WebElement checkbox = getDriver().findElement(checkboxTerms);

            // Uncheck nếu đã được tick
            if (checkbox.isSelected()) {
                click(checkboxTerms);
                logStep("✓ Đã bỏ tick checkbox");
            } else {
                logStep("⚠️ Checkbox chưa được tick");
            }
        } catch (Exception e) {
            logStep("❌ Không thể bỏ tick checkbox: " + e.getMessage());
            throw e;
        }
    }

    public boolean isCheckboxTicked() {
        logStep("Verify: Kiểm tra checkbox đã tick chưa");
        try {
            WebElement checkbox = getDriver().findElement(checkboxTerms);
            return checkbox.isSelected();
        } catch (Exception e) {
            logStep("❌ Không thể check trạng thái checkbox");
            return false;
        }
    }

    public void clickRegisterButton() {

        try {
            // Try locator 1
            WaitingHelper.waitForClickable(registerButton);
            click(registerButton);
            logStep(" Da click nut dang ky");
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
            WaitingHelper.waitForClickable(loginToRegister);
            click(loginToRegister);
            logStep("Da click dang nhap ngay tu man dang ky");
        } catch (Exception e) {
            logStep("Khong click duoc dang nhap ngay");
            throw e;
        }
    }
    // ==================== HELPER METHODS ====================

    public void performRegistration(String fullName, String phone, String email, String password, String confirmPassword) {
        logStep("=== THỰC HIỆN ĐĂNG KÝ ===");
        enterFullName(fullName);
        enterPhone(phone);
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        tickTermsCheckbox();
        logStep("Click nut Dang ky");
        try {
            scrollToText("Đăng ký");
            WaitingHelper.waitForClickable(registerButton);
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
            logStep("✓ Đã clear field");
        } catch (Exception e) {
            logStep("❌ Không thể clear field: " + e.getMessage());
        }
    }

    public void clearFullNameField() {
        logStep("Clear Họ và tên");
        clearField(fullNameField);
    }

    public void clearPhoneField() {
        logStep("Clear Số điện thoại");
        clearField(phoneField);
    }

    public void clearEmailField() {
        logStep("Clear Email");
        clearField(emailField);
    }

    public void clearPasswordField() {
        logStep("Clear Mật khẩu");
        clearField(passwordField);
    }

    public void clearConfirmPasswordField() {
        logStep("Clear Xác nhận Mật khẩu");
        clearField(confirmPasswordField);
    }

    public void clearAllFields() {
        logStep("Clear tất cả field");
        clearFullNameField();
        clearPhoneField();
        clearEmailField();
        clearPasswordField();
        clearConfirmPasswordField();
    }

    // ==================== VERIFY FIELD VALUES ====================

    public String getFullNameValue() {
        WebElement element = getDriver().findElement(fullNameField);
        return element.getAttribute("text");
    }

    public String getPhoneValue() {
        WebElement element = getDriver().findElement(phoneField);
        return element.getAttribute("text");
    }

    public String getEmailValue() {
        WebElement element = getDriver().findElement(emailField);
        return element.getAttribute("text");
    }

    // ==================== VERIFY ERROR MESSAGES ====================
    public boolean isFillAllInfoErrorDisplayed() {

        logStep("🔍 Verify Toast: Vui lòng điền đầy đủ thông tin");

        try {
            Thread.sleep(1000); // chờ toast xuất hiện
            getDriver().findElement(toastFillAllInfo);
            logger.info("✅ Toast error msg hien thi thanh cong");
            AllureHelper.attachScreenshot("Toast error msg hien thi thanh cong");

            logStep("✅ Toast đã hiển thị");
            return true;
        } catch (Exception e) {
            logStep("❌ Không thấy Toast");
            logger.info("✅ Toast error msg khong hien thi ");
            AllureHelper.attachScreenshot("Toast error msg khong hien thi");
            return false;
        }

    }

    public boolean isFillAllInfoErrorDisplayed_MK() {

        logStep("🔍 Verify Toast: Vui lòng điền đầy đủ thông tin");

        try {
            Thread.sleep(1000); // chờ toast xuất hiện
            getDriver().findElement(toastPasswordMismatch);
            logger.info("✅ Toast error msg hien thi thanh cong");
            AllureHelper.attachScreenshot("Toast error msg hien thi thanh cong");
            logStep("✅ Toast đã hiển thị");
            return true;
        } catch (Exception e) {
            logger.info("✅ Toast error msg khong hien thi ");
            AllureHelper.attachScreenshot("Toast error msg khong hien thi");
            return false;
        }
    }
    public boolean isFillAllInfoErrorDisplayed_DK() {

        logStep("🔍 Verify Toast: Vui lòng điền đầy đủ thông tin");

        try {
            Thread.sleep(1000); // chờ toast xuất hiện
            getDriver().findElement(toastTermsRequired);
            logger.info("✅ Toast error msg hien thi thanh cong");
            AllureHelper.attachScreenshot("Toast error msg hien thi thanh cong");
            logStep("✅ Toast đã hiển thị");
            return true;
        } catch (Exception e) {
            logStep("❌ Không thấy Toast");
            logger.info("✅ Toast error msg khong hien thi ");
            AllureHelper.attachScreenshot("Toast error msg khong hien thi");
            return false;
        }
    }

    // ==================== UNHAPPY CASE SCENARIOS ====================

    /**
     * Scenario: Để trống field nhưng vẫn click Đăng ký
     */
    public void registerWithoutFullName(String phone, String email, String password) {
        logStep("=== ĐĂNG KÝ KHÔNG CÓ HỌ VÀ TÊN ===");
        clearFullNameField();
        enterPhone(phone);
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(password);
        tickTermsCheckbox();
        clickRegisterButton();
    }

    public void registerWithoutPhone(String fullName, String email, String password) {
        logStep("=== ĐĂNG KÝ KHÔNG CÓ SĐT ===");
        enterFullName(fullName);
        clearPhoneField();
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(password);
        tickTermsCheckbox();
        clickRegisterButton();
    }

    public void registerWithoutEmail(String fullName, String phone, String password) {
        logStep("=== ĐĂNG KÝ KHÔNG CÓ EMAIL ===");
        enterFullName(fullName);
        enterPhone(phone);
        clearEmailField();
        enterPassword(password);
        enterConfirmPassword(password);
        tickTermsCheckbox();
        clickRegisterButton();
    }

    public void registerWithoutPassword(String fullName, String phone, String email) {
        logStep("=== ĐĂNG KÝ KHÔNG CÓ MẬT KHẨU ===");
        enterFullName(fullName);
        enterPhone(phone);
        enterEmail(email);
        clearPasswordField();
        enterConfirmPassword("");
        tickTermsCheckbox();
        clickRegisterButton();
    }

    public void registerWithoutConfirmPassword(String fullName, String phone, String email, String password) {
        logStep("=== ĐĂNG KÝ KHÔNG CÓ XÁC NHẬN MẬT KHẨU ===");
        enterFullName(fullName);
        enterPhone(phone);
        enterEmail(email);
        enterPassword(password);
        clearConfirmPasswordField();
        tickTermsCheckbox();
        clickRegisterButton();
    }

    public void registerWithMismatchPassword(String fullName, String phone, String email, String password, String confirmPassword) {
        logStep("=== ĐĂNG KÝ VỚI MẬT KHẨU KHÔNG TRÙNG KHỚP ===");
        enterFullName(fullName);
        enterPhone(phone);
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        tickTermsCheckbox();
        clickRegisterButton();
    }

    public void registerWithoutTermsAgreement(String fullName, String phone, String email, String password) {
        logStep("=== ĐĂNG KÝ KHÔNG TICK ĐIỀU KHOẢN ===");
        enterFullName(fullName);
        enterPhone(phone);
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(password);
        // NOT tick checkbox
        clickRegisterButton();
    }

    public void registerWithAllFieldsEmpty() {
        logStep("=== ĐĂNG KÝ KHÔNG CÓ THÔNG TIN NÀO ===");
        clearAllFields();
        // NOT tick checkbox
        clickRegisterButton();
    }
}