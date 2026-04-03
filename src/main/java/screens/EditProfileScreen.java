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
    private final By titleEditProfile = By.xpath("//android.widget.TextView[@text='Chỉnh sửa thông tin']");

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

    // ===== BACK =====
    private final By btnBack = By.xpath("//android.widget.Button");

    // ================== ACTION ==================

    public void openEditProfileScreen() {
        logStep("Mở màn chỉnh sửa thông tin");

        WaitingHelper.waitForClickable(iconEditProfile);
        click(iconEditProfile);

        WaitingHelper.sleepSeconds(2);
    }

    public boolean isEditProfileDisplayed() {
        logStep("Verify màn chỉnh sửa thông tin");

        return isDisplayed(titleEditProfile);
    }
// ================= INPUT =================
    public void enterFullName(String name) {

        logStep("Nhập họ và tên: " + name);

        type(inputFullName, name);
    }

    public void enterEmail(String email) {

        logStep("Nhập email: " + email);

        type(inputEmail, email);
    }

    public void enterPhone(String phone) {

        logStep("Nhập số điện thoại: " + phone);

        type(inputPhone, phone);
    }


    public void clickSave() {
        logStep("Click Lưu thay đổi");

        WaitingHelper.waitForClickable(btnSave);
        click(btnSave);

        WaitingHelper.sleepSeconds(3);
    }

    public void clickBack() {
        logStep("Back về Profile");

        click(btnBack);
        WaitingHelper.sleepSeconds(2);
    }

    public boolean isToastErrorDisplayed() {
        logStep("Verify Toast Error");

        try {
            return isDisplayed(toastError);
        } catch (Exception e) {
            return false;
        }
    }
}