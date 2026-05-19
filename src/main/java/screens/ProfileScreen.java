package screens;

import core.base.BaseScreen;
import core.utils.AllureHelper;
import core.utils.WaitingHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * ProfileScreen.java
 * ✅ Updated: Fix locators để match actual app data format
 */
public class ProfileScreen extends BaseScreen {
    private static final Logger logger = LoggerFactory.getLogger(ProfileScreen.class);

    // ==================== LOCATORS ====================
    private final By userName = By.xpath("//android.widget.TextView[@text='Trang cá nhân']");
    private final By btnEdit = By.xpath("//android.view.View[@content-desc='Edit']");
    private final By btnLogout = By.xpath("//z0.h0/android.view.View/android.view.View/android.view.View[1]/android.widget.Button");
    private final By bottomNavProfile = By.xpath("//android.widget.TextView[@text='Tài khoản']");
    private final By sessionLogout = By.xpath("(//android.widget.TextView[@text=\"Đang cập nhật...\"])[1]");
    private final By btnSavedRecipes = By.xpath("//android.widget.TextView[@text='Kho công thức']");
    private final By bottomNavHome = By.xpath("//android.widget.TextView[@text='Trang chủ']");

    // ==================== PROFILE INFO - DYNAMIC LOCATORS ====================
    // Sử dụng contains() để match dữ liệu động

    /**
     * ✅ FIX: Match tên với contains() thay vì exact text
     * Vì app có thể hiển thị: "Tuong Thi Duyen", "Tuong Thi Duyen Test", v.v.
     */
    private By getTxtFullName(String fullName) {
        return By.xpath("//android.widget.TextView[contains(@text, '" + fullName + "')]");
    }

    /**
     * ✅ FIX: Match SĐT - app hiển thị với "+84" format
     * Input: "0900009999" → App display: "+84 900009999"
     */
    private By getTxtPhone(String phone) {
        // Nếu input là "0900009999", match "+84 900009999"
        String displayPhone = phone.startsWith("0")
                ? "+84 " + phone.substring(1)
                : phone;
        return By.xpath("//android.widget.TextView[contains(@text, '" + displayPhone + "')]");
    }

    /**
     * ✅ FIX: Match Email - sử dụng contains()
     */
    private By getTxtEmail(String email) {
        return By.xpath("//android.widget.TextView[contains(@text, '" + email + "')]");
    }

    // ==================== VERIFY PROFILE SCREEN ====================

    public boolean isProfileDisplayed() {
        boolean isDisplayed = isDisplayed(userName);

        if (isDisplayed) {
            logger.info("✅ Profile screen is displayed");
            AllureHelper.attachScreenshot("Profile Screen Displayed");
        } else {
            logger.error("❌ Profile screen NOT displayed");
            AllureHelper.attachScreenshot("Profile Screen NOT Found");
        }

        return isDisplayed;
    }

    public boolean isProfileScreenDisplayed() {
        logger.info("🔍 Kiem tra man Profile khi chua login");
        AllureHelper.step("Verify Profile screen status (not logged in)");

        boolean isDisplayed = isDisplayed(sessionLogout);

        if (isDisplayed) {
            logger.info("✅ Profile screen (not logged in) is displayed");
            AllureHelper.attachScreenshot("Profile Screen Not Logged In");
        } else {
            logger.error("❌ Profile screen (not logged in) NOT displayed");
            AllureHelper.attachScreenshot("Profile Screen NOT Found");
        }

        return isDisplayed;
    }

    // ==================== WAIT FOR PROFILE ====================

    public void waitForProfileScreen() {
        logStep("Cho man Tai khoan hien thi");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userName));
    }

    // ==================== PROFILE ACTIONS ====================

    public void clickEditProfile() {
        logStep("Mo Chinh sua ho so");
        WaitingHelper.waitForClickable(btnEdit);
        click(btnEdit);
        WaitingHelper.sleepSeconds(2);
    }

    public void logout() {
        logStep("Đang xuat");
        WaitingHelper.waitForClickable(btnLogout);
        click(btnLogout);
        WaitingHelper.sleepSeconds(1);
    }

    // ==================== LOGIN GATE (Khi chưa đăng nhập) ====================

    public void clickLoginOnProfileGate() {
        By btnDangNhapNgay = By.xpath("//android.widget.TextView[@text='Đăng nhập ngay']");
        logStep("Click nut Đang nhap ngay tren Trang ca nhan");
        WaitingHelper.waitForClickable(btnDangNhapNgay);
        click(btnDangNhapNgay);
        WaitingHelper.sleepSeconds(2);
    }

    // ==================== BOTTOM NAV PROFILE ====================

    public void clickBottomNavProfile() {
        logStep("Click tab Tai khoan");
        click(bottomNavProfile);
    }

    public void clickTabProfile() {
        logStep("Click tab Profile");
        clickBottomNavProfile();
    }
    public void clickTabHome() {
        logStep("Click tab Tai khoan");
        click(bottomNavHome);
    }

    // ==================== VERIFY PROFILE INFO - UPDATED ====================

    /**
     * ✅ FIX: Verify dữ liệu profile với dynamic locators
     */
    public boolean isProfileInfoCorrect(
            String fullName,
            String email,
            String phone
    ) {
        logStep("🔍 Verify Profile Info");
        logger.info("Verifying: fullName=[" + fullName + "], email=[" + email + "], phone=[" + phone + "]");

        boolean nameCorrect = isDisplayed(getTxtFullName(fullName));
        logger.info("✓ Full name check: " + (nameCorrect ? "PASS" : "FAIL"));

        boolean emailCorrect = isDisplayed(getTxtEmail(email));
        logger.info("✓ Email check: " + (emailCorrect ? "PASS" : "FAIL"));

        boolean phoneCorrect = isDisplayed(getTxtPhone(phone));
        logger.info("✓ Phone check: " + (phoneCorrect ? "PASS" : "FAIL"));

        if (nameCorrect && emailCorrect && phoneCorrect) {
            logger.info("✅ Profile info CORRECT");
            AllureHelper.attachScreenshot("Profile Info Correct");
            return true;
        }

        logger.error("❌ Profile info INCORRECT");
        logger.error("  - Full name correct: " + nameCorrect);
        logger.error("  - Email correct: " + emailCorrect);
        logger.error("  - Phone correct: " + phoneCorrect);
        AllureHelper.attachScreenshot("Profile Info Incorrect");

        return false;
    }

    public void openSavedRecipes() {
        logStep("Open Saved Recipes");
        click(btnSavedRecipes);
        AllureHelper.attachScreenshot("OPEN SAVED RECIPES");
    }


}