package screens;

import core.base.BaseScreen;
import core.utils.WaitingHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

/**
 * ProfileScreen.java
 * ✅ Updated: Thêm các method còn thiếu cho logout flow
 */
public class ProfileScreen extends BaseScreen {

    // ==================== LOCATORS ====================
    private final By userName = By.xpath("//android.widget.TextView[@text='Tuong Thi Duyen']");
    private final By btnEdit = By.xpath("//android.view.View[@content-desc='Edit']");
    private final By btnLogout = By.xpath("//z0.h0/android.view.View/android.view.View/android.view.View[1]/android.widget.Button");
    private final By bottomNavProfile = By.xpath("//android.widget.TextView[@text='Tài khoản']");
    private final By sessionLogout = By.xpath("//android.view.View[@content-desc='Close sheet']");
    // ==================== VERIFY PROFILE SCREEN ====================

    public boolean isProfileDisplayed() {
        return isDisplayed(userName);
    }

    public boolean isProfileScreenDisplayed() {
        logStep("Verify: Kiểm tra màn Trang cá nhân");
        try {
            return isDisplayed(sessionLogout);
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== WAIT FOR PROFILE ====================

    public void waitForProfileScreen() {
        logStep("Chờ màn Tài khoản hiển thị");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userName));
    }

    // ==================== PROFILE ACTIONS ====================

    public void clickEditProfile() {
        logStep("Mở Chỉnh sửa hồ sơ");
        WaitingHelper.waitForClickable(btnEdit);
        click(btnEdit);
        WaitingHelper.sleepSeconds(2);
    }

    public void logout() {
        logStep("Đăng xuất");
        WaitingHelper.waitForClickable(btnLogout);
        click(btnLogout);
        WaitingHelper.sleepSeconds(2);
    }

    // ==================== LOGIN GATE (Khi chưa đăng nhập) ====================

    public void clickLoginOnProfileGate() {
        By btnDangNhapNgay = By.xpath("//android.widget.TextView[@text='Đăng nhập ngay']");
        logStep("Click nút Đăng nhập ngay trên Trang cá nhân");
        WaitingHelper.waitForClickable(btnDangNhapNgay);
        click(btnDangNhapNgay);
        WaitingHelper.sleepSeconds(2);
    }

    // ==================== BOTTOM NAV PROFILE (MỚI THÊM) ====================

    /**
     * ✅ Click tab Tài khoản ở bottom navigation
     */
    public void clickBottomNavProfile() {
        logStep("Click tab Tài khoản");
        WaitingHelper.waitForClickable(bottomNavProfile);
        click(bottomNavProfile);
        WaitingHelper.sleepSeconds(1);
    }

    /**
     * ✅ Alias method (nếu code cũ dùng tên này)
     */
    public void clickTabProfile() {
        logStep("Click tab Profile");
        clickBottomNavProfile();
    }
}