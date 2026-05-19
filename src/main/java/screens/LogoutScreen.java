package screens;

import core.base.BaseScreen;
import core.utils.WaitingHelper;
import org.openqa.selenium.By;

/**
 * LogoutScreen.java
 * Quản lý các element trong phần Logout (Profile tab)
 * Locators từ chị cung cấp
 */
public class LogoutScreen extends BaseScreen {

    // ==================== LOGOUT ICON LOCATORS ====================
    // Nút icon logout biểu tượng trên trang cá nhân tại tab tài khoản
    private final By logoutIcon = By.xpath("//z0.h0/android.view.View/android.view.View/android.view.View[1]/android.widget.Button");

    // ==================== POPUP LOGOUT LOCATORS ====================
    // Popup hiển thị logout
    private final By popupCloseSheet = By.xpath("//android.view.View[@content-desc='Close sheet']");
    private final By popupDragHandle = By.xpath("//android.view.View[@content-desc='Drag handle']");
    private final By popupHeader = By.xpath("//android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]");

    // ==================== CANCEL BUTTON LOCATORS ====================
    // Nút "X" để hủy đăng xuất tài khoản
//    private final By cancelButtonX = By.xpath("//android.widget.Button1");
//    private final By cancelButtonX = By.xpath("//android.view.View[@content-desc='Close sheet']");
    private final By cancelButtonX = By.xpath("//android.view.View[@content-desc='Đóng trang tính']");
    private final By cancelButtonImageView = By.xpath("//android.widget.ImageView[@content-desc='Account Logout']");

    // ==================== CONFIRM LOGOUT LOCATORS ====================
    // Thông báo xác nhận
    private final By confirmTitle = By.xpath("//android.widget.TextView[@text='Đăng xuất tài khoản']");
    private final By confirmMessage = By.xpath("//android.widget.TextView[@text='Bạn có chắc muốn đăng xuất không?']");

    // Nút đăng xuất cuối cùng
    private final By confirmLogoutButton = By.xpath("//android.widget.TextView[@text='Đăng xuất']");
    private final By confirmLogoutButtonAlt = By.xpath("//android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]");

    // ==================== ACTIONS ====================

    public boolean isLogoutPopupDisplayed() {
        logStep("Verify: Kiem tra popup Logout hien thi");
        try {
            WaitingHelper.waitForVisible(confirmTitle);
            return isDisplayed(confirmTitle);
        } catch (Exception e) {
            logStep("❌ Popup Logout khong hien thi");
            return false;
        }
    }

    public void clickLogoutIcon() {
        logStep("Click icon Logout tren trang ca nhan");
        WaitingHelper.waitForClickable(logoutIcon);
        click(logoutIcon);
        logStep("✓ Da click icon Logout");
    }

    public void confirmLogout() {
        logStep("Click nut Đang xuat đe xac nhan");
        WaitingHelper.waitForClickable(confirmLogoutButton);
        click(confirmLogoutButton);
        logStep("✓ Đa click nut Đang xuat");
    }

    public void cancelLogout() {
        logStep("Click nut X hoac Huy đe huy đang xuat");
        WaitingHelper.waitForClickable(cancelButtonX);
        click(cancelButtonX);
        logStep("Da click duoc nut Huy");
    }

    public void performLogout() {
        logStep("=== THUC HIEN ĐANG XUAT ===");
        clickLogoutIcon();

        if (isLogoutPopupDisplayed()) {
            confirmLogout();
        } else {
            logStep("❌ Popup Logout khong hien thi!");
        }
    }

    public void cancelLogoutProcess() {
        logStep("=== HUY ĐANG XUAT ===");
        clickLogoutIcon();

        if (isLogoutPopupDisplayed()) {
            cancelLogout();
        } else {
            logStep("❌ Popup Logout khong hien thi!");
        }
    }
}