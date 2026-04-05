package screens;

import core.base.BaseScreen;
import core.utils.WaitingHelper;
import org.openqa.selenium.By;

/**
 * EditProfileScreen
 * Màn hình chỉnh sửa thông tin cá nhân
 */

public class EditProfileScreen extends BaseScreen {

    // ===== HEADER =====
    private final By titleEditProfile = By.xpath("//android.widget.TextView[@text='Trang cá nhân']");

    // ===== ICON EDIT (từ Profile) =====
//    private final By iconEditProfile = By.xpath("//android.view.View[@content-desc='Edit']");
    private final By iconEditProfile = By.xpath("//android.widget.TextView[@text='Sửa']");

    // ===== INPUT FIELD =====
    private final By inputFullName = By.xpath("//android.widget.ScrollView/android.widget.EditText[1]");
    private final By inputEmail = By.xpath("//android.widget.ScrollView/android.widget.EditText[2]");
    private final By inputPhone = By.xpath("//android.widget.ScrollView/android.widget.EditText[3]");

    // ===== BUTTON =====
    private final By btnSave = By.xpath("//android.widget.TextView[@text='Lưu thay đổi']");

    // ===== TOAST ERROR =====
    private final By toastError = By.xpath("//android.widget.Toast[@text='Có lỗi xảy ra, vui lòng thử lại']");

    // ===== TOAST UPDATE INFO THANH CONG ======
    private final By toastUpdate = By.xpath("//android.widget.Toast[@text='Cập nhật thành công']");

    // ===== BACK =====
    private final By btnBack = By.xpath("//android.widget.Button");
    private final By registerButton = By.xpath("(//android.widget.TextView[@text='Đăng ký'])[2]");

    // ================== ACTION ==================

    public void openEditProfileScreen() {
        logStep("Mở màn chỉnh sửa thông tin");

//        WaitingHelper.waitForClickable(iconEditProfile);
        click(iconEditProfile);

//        WaitingHelper.sleepSeconds(2);
    }

//    public boolean isEditProfileDisplayed() {
//        logStep("Verify màn chỉnh sửa thông tin");
//
//        return isDisplayed(titleEditProfile);
//    }
// ================= INPUT =================
    public void enterFullName(String name) {
        logStep("Nhap ho và ten: " + name);
        type(inputFullName, name);
    }

    public void enterEmail(String email) {
        logStep("Nhap email: " + email);
        type(inputEmail, email);
    }

    public void enterPhone(String phone) {
        logStep("Nhap so đien thoai: " + phone);
        type(inputPhone, phone);
    }

    public void clickSave() {
        logStep("Click Luu thay đoi");
//        WaitingHelper.waitForClickable(btnSave);
        click(btnSave);
//        WaitingHelper.sleepSeconds(4);
    }

    public void clickBack() {
        logStep("Back Tai khoan");
        click(btnBack);
        WaitingHelper.sleepSeconds(2);
    }

    public boolean isToastErrorDisplayed() {

        logStep("🔍 Verify Toast: Co loi xay ra, vui long thuc hien lai");

        try {
            Thread.sleep(0000); // chờ toast xuất hiện
            getDriver().findElement(toastError);
            logStep("✅ Toast đã hiển thị");
            return true;
        } catch (Exception e) {
            logStep("❌ Không thấy Toast");
            return false;
        }
    }

    public boolean isToastUpdateInfoDisplayed() {

        logStep("🔍 Verify Toast: Cap nhat thanh cong");

        try {
            Thread.sleep(0000); // chờ toast xuất hiện
            getDriver().findElement(toastUpdate);
            logStep("✅ Toast đã hiển thị");
            return true;
        } catch (Exception e) {
            logStep("❌ Không thấy Toast");
            return false;
        }
    }
//    public void clickRegister(){
//            scrollToText("Đăng ký");
//            WaitingHelper.waitForClickable(registerButton);
//            click(registerButton);
//            logStep("Da click dang ky thanh cong");
//    }
}