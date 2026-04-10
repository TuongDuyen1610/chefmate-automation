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
    private final By cancelButtonX = By.xpath("//android.widget.Button");
    private final By cancelButtonImageView = By.xpath("//android.widget.ImageView[@content-desc='Account Logout']");

    // ==================== CONFIRM LOGOUT LOCATORS ====================
    // Thông báo xác nhận
    private final By confirmTitle = By.xpath("//android.widget.TextView[@text='Đăng xuất tài khoản']");
    private final By confirmMessage = By.xpath("//android.widget.TextView[@text='Bạn có chắc muốn đăng xuất không?']");

    // Nút đăng xuất cuối cùng
    private final By confirmLogoutButton = By.xpath("//android.widget.TextView[@text='Đăng xuất']");
    private final By confirmLogoutButtonAlt = By.xpath("//android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]");

    // ==================== ACTIONS ====================

    /**
     * ✅ Verify Logout Popup hiển thị
     */
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

    /**
     * ✅ Click icon Logout trên Profile tab
     */
    public void clickLogoutIcon() {
        logStep("Click icon Logout tren trang ca nhan");
//        try {
            WaitingHelper.waitForClickable(logoutIcon);
            click(logoutIcon);
            logStep("✓ Da click icon Logout");
//            WaitingHelper.sleepSeconds(2);
//        } catch (Exception e) {
//            logStep("❌ Khong click đuoc icon Logout: " + e.getMessage());
//            throw e;
//        }
    }

    /**
     * ✅ Click nút Đăng xuất để xác nhận
     */
    public void confirmLogout() {
        logStep("Click nut Đang xuat đe xac nhan");
//        try {
            WaitingHelper.waitForClickable(confirmLogoutButton);
            click(confirmLogoutButton);
            logStep("✓ Đa click nut Đang xuat");
//            WaitingHelper.sleepSeconds(3);
//        } catch (Exception e) {
//            logStep("⚠️ Locator 1 thất bại, thử locator 2");
//            try {
//                click(confirmLogoutButtonAlt);
//                logStep("✓ Đã click nút Đăng xuất (locator 2)");
//                WaitingHelper.sleepSeconds(3);
//            } catch (Exception e2) {
//                logStep("❌ Không click được nút Đăng xuất");
//                throw e2;
//            }
//        }
    }

    /**
     * ✅ Click nút X hoặc Hủy để không đăng xuất
     */
    public void cancelLogout() {
        logStep("Click nut X hoac Huy đe huy đang xuat");
////        try {
////            // Try Close Sheet first
////            try {
//                WaitingHelper.waitForClickable(popupCloseSheet);
//                click(popupCloseSheet);
//                logStep("✓ Đa click Close Sheet");
//                WaitingHelper.sleepSeconds(1);
//                return;
//            } catch (Exception e1) {
//                logStep("⚠️ Close Sheet that bai, thu Button X");
//            }

//            // Try Button X
//            try {
                WaitingHelper.waitForClickable(cancelButtonX);
                click(cancelButtonX);
                logStep("✓ Đa click nut X");
//                WaitingHelper.sleepSeconds(1);
//                return;
//            } catch (Exception e2) {
//                logStep("⚠️ Button X thất bại, thử ImageView");
//            }

//            // Try ImageView
//            try {
//                WaitingHelper.waitForClickable(cancelButtonImageView);
//                click(cancelButtonImageView);
//                logStep("✓ Đã click ImageView");
//                WaitingHelper.sleepSeconds(1);
//                return;
//            } catch (Exception e3) {
                logStep("Da click duoc nut Huy");
//                throw e3;
//            }
//
//        } catch (Exception e) {
//            logStep("❌ Hủy đăng xuất thất bại: " + e.getMessage());
//            throw e;
//        }
    }

    /**
     * ✅ Wrapper: Thực hiện logout hoàn chỉnh
     */
    public void performLogout() {
        logStep("=== THUC HIEN ĐANG XUAT ===");
        clickLogoutIcon();

        if (isLogoutPopupDisplayed()) {
            confirmLogout();
        } else {
            logStep("❌ Popup Logout khong hien thi!");
        }
    }

    /**
     * ✅ Wrapper: Hủy logout
     */
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