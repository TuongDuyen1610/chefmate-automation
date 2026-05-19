package screens;

import core.base.BaseScreen;
import core.utils.AllureHelper;
import core.utils.WaitingHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * EditProfileScreen.java
 * Man hinh chinh sua thong tin ca nhan + Doi mat khau
 */
public class EditProfileScreen extends BaseScreen {
    private static final Logger logger = LoggerFactory.getLogger(EditProfileScreen.class);

    // ==================== HEADER =====
    private final By titleEditProfile = By.xpath("//android.widget.TextView[@text='Trang cá nhân']");

    // ===== ICON EDIT (tu Profile) =====
    private final By iconEditProfile = By.xpath("//android.widget.TextView[@text='Sửa']");

    // ===== INPUT FIELD (EDIT INFO) =====
    private final By inputFullName = By.xpath("//android.widget.ScrollView/android.widget.EditText[1]");
    private final By inputEmail = By.xpath("//android.widget.ScrollView/android.widget.EditText[2]");
    private final By inputPhone = By.xpath("//android.widget.ScrollView/android.widget.EditText[3]");

    // ===== BUTTON EDIT INFO =====
    private final By btnSave = By.xpath("//android.widget.TextView[@text='Lưu thay đổi']");
    private final By btnChangePassword = By.xpath("//android.widget.TextView[@text='Đổi mật khẩu']");
//    private final By btnBack = By.xpath("//android.widget.ScrollView/android.widget.Button");

    // ==================== CHANGE PASSWORD SCREEN ====================

    // ===== HEADER (CHANGE PASSWORD) =====
    private final By titleChangePassword = By.xpath("//android.widget.TextView[@text='Chỉnh sửa thông tin']");

    // ===== LABELS (CHANGE PASSWORD) =====
    private final By labelCurrentPassword = By.xpath("//android.widget.TextView[@text='Mật khẩu hiện tại']");
    private final By labelNewPassword = By.xpath("//android.widget.TextView[@text='Mật khẩu mới']");
    private final By labelConfirmPassword = By.xpath("//android.widget.TextView[@text='Xác nhận mật khẩu mới']");

    // ===== INPUT FIELD (CHANGE PASSWORD) =====
    private final By inputCurrentPassword = By.xpath("//android.widget.ScrollView/android.widget.EditText[1]");
    private final By inputNewPassword = By.xpath("//android.widget.ScrollView/android.widget.EditText[2]");
    private final By inputConfirmPassword = By.xpath("//android.widget.ScrollView/android.widget.EditText[3]");

    // ===== EYE ICON TOGGLE =====
    private final By eyeIconCurrentPassword = By.xpath("//android.widget.ScrollView/android.widget.EditText[1]/android.widget.Button");
    private final By eyeIconNewPassword = By.xpath("//android.widget.ScrollView/android.widget.EditText[2]/android.widget.Button");
    private final By eyeIconConfirmPassword = By.xpath("//android.widget.ScrollView/android.widget.EditText[3]/android.widget.Button");

    // ===== BUTTON (CHANGE PASSWORD) =====
    // Nút Đổi mật khẩu ở màn Change Password (để confirm)
    private final By btnConfirmChangePassword = By.xpath("//android.widget.TextView[@text='Đổi mật khẩu']");

    // Nút Đổi mật khẩu ở màn Edit Profile (để navigate)
    private final By btnChangePasswordNavigate = By.xpath("//android.widget.TextView[@text='Đổi mật khẩu']");

//    private final By btnBackFromChangePassword = By.xpath("//android.widget.ScrollView/android.widget.Button");
    // ===== BUTTON =====
    // ✅ Nút Back DƯỚI (trong ScrollView) - Change Password -> Edit Profile
    private final By btnBackFromChangePassword = By.xpath("(//android.widget.ScrollView/android.widget.Button)[1]");

    // ✅ Nút Back TRÊN (ở header, ngoài ScrollView) - Edit Profile -> Profile
    private final By btnBack = By.xpath("//android.widget.Button[not(ancestor::android.widget.ScrollView)]");
    // ===== TOAST MESSAGE =====
    private final By toastErrorPhone = By.xpath("//android.widget.Toast[@text='This phone is already exist']");
    private final By toastErrorEmail = By.xpath("//android.widget.Toast[@text='This email is already exist']");
    private final By toastUpdate = By.xpath("//android.widget.Toast[@text='Cập nhật thành công']");
    private final By toastChangePasswordSuccess = By.xpath("//android.widget.Toast[@text='Đổi mật khẩu thành công']");
    private final By toastChangePasswordError = By.xpath("//android.widget.Toast[@text='Mật khẩu mới không được trùng với mật khẩu hiện tại']");
    private final By toastChangePasswordError2 = By.xpath("//android.widget.Toast[@text='Mật khẩu mới và xác nhận mật khẩu không khớp']");

    // ==================== EDIT PROFILE ACTIONS ====================

    public void openEditProfileScreen() {
        logStep("Mo man chinh sua thong tin");
        click(iconEditProfile);
//        WaitingHelper.sleepSeconds(2);
    }

    public void enterFullName(String name) {
        logStep("Nhap ho va ten: " + name);
        WaitingHelper.waitForClickable(inputFullName);
        clearTextField(inputFullName);
        type(inputFullName, name);
    }

    public void enterEmail(String email) {
        logStep("Nhap email: " + email);
        WaitingHelper.waitForClickable(inputEmail);
        clearTextField(inputEmail);
        type(inputEmail, email);
    }

    public void enterPhone(String phone) {
        logStep("Nhap so dien thoai: " + phone);
        WaitingHelper.waitForClickable(inputPhone);
        clearTextField(inputPhone);
        type(inputPhone, phone);
    }

    public void clickSave() {
        logStep("Click Luu thay doi");
        WaitingHelper.waitForClickable(btnSave);
        click(btnSave);
        WaitingHelper.sleepSeconds(2);
    }

    public void clickBack() {
        logStep("Click Back ve Profile");
        WaitingHelper.waitForClickable(btnBack);
        click(btnBack);
        WaitingHelper.sleepSeconds(2);
    }

    // ==================== CHANGE PASSWORD ACTIONS ====================

    public void clickChangePasswordButtonNavigate() {
        logStep("Click nut Doi mat khau de navigate");
        WaitingHelper.waitForClickable(btnChangePasswordNavigate);
        click(btnChangePasswordNavigate);
        WaitingHelper.sleepSeconds(2);
    }
    public boolean isChangePasswordScreenDisplayed() {
        logStep("Kiem tra man Doi mat khau hien thi");
        boolean isDisplayed = isDisplayed(labelCurrentPassword);

        if (isDisplayed) {
            logger.info("Change password screen displayed");
            AllureHelper.attachScreenshot("Change Password Screen");
        } else {
            logger.error("Change password screen NOT displayed");
            AllureHelper.attachScreenshot("Change Password Screen NOT Found");
        }

        return isDisplayed;
    }

    // ==================== PASSWORD INPUT METHODS ====================

    public void enterCurrentPassword(String password) {
        logStep("Nhap mat khau hien tai");
        WaitingHelper.waitForClickable(inputCurrentPassword);
        clearTextField(inputCurrentPassword);
        type(inputCurrentPassword, password);
    }

    public void enterNewPassword(String password) {
        logStep("Nhap mat khau moi");
        WaitingHelper.waitForClickable(inputNewPassword);
        clearTextField(inputNewPassword);
        type(inputNewPassword, password);
    }

    public void enterConfirmPassword(String password) {
        logStep("Nhap xac nhan mat khau moi");
        WaitingHelper.waitForClickable(inputConfirmPassword);
        clearTextField(inputConfirmPassword);
        type(inputConfirmPassword, password);
    }

    // ==================== EYE ICON TOGGLE =====

    public void toggleCurrentPasswordVisibility() {
        logStep("Toggle hien thi an mat khau hien tai");
        WaitingHelper.waitForClickable(eyeIconCurrentPassword);
        click(eyeIconCurrentPassword);
        WaitingHelper.sleepSeconds(1);
    }

    public void toggleNewPasswordVisibility() {
        logStep("Toggle hien thi an mat khau moi");
        WaitingHelper.waitForClickable(eyeIconNewPassword);
        click(eyeIconNewPassword);
        WaitingHelper.sleepSeconds(1);
    }

    public void toggleConfirmPasswordVisibility() {
        logStep("Toggle hien thi an xac nhan mat khau moi");
        WaitingHelper.waitForClickable(eyeIconConfirmPassword);
        click(eyeIconConfirmPassword);
        WaitingHelper.sleepSeconds(1);
    }

    // ==================== CONFIRM & BACK ====================

    public void clickConfirmChangePassword() {
        logStep("Click nut Doi mat khau confirm");
        click(btnConfirmChangePassword);
        WaitingHelper.sleepSeconds(1);

        try {
            getDriver().findElement(toastChangePasswordSuccess);
            logger.info("Toast update success displayed");
            AllureHelper.attachScreenshot("Toast Update Success Displayed");
        } catch (Exception e) {
            logger.error("Toast update success NOT displayed" + e.getMessage());
            AllureHelper.attachScreenshot("Change Password Success Toast NOT Found");
            throw new AssertionError("Toast doi mat khau khong hien thi");
        }
    }

    public void clickBackFromChangePassword() {
        logStep("Click Back tu man Doi mat khau");
        WaitingHelper.waitForClickable(btnBackFromChangePassword);
        click(btnBackFromChangePassword);
        WaitingHelper.sleepSeconds(2);
    }

    // ==================== TOAST VERIFICATION =====

//    public boolean isChangePasswordSuccessDisplayed() {
//        click(btnChangePassword);
//        WaitingHelper.sleepSeconds(1);
//        try {
//            getDriver().findElement(toastChangePasswordSuccess);
//            logger.info("Toast error displayed");
//            AllureHelper.attachScreenshot("Toast Error Displayed");
//            return true;
//        } catch (Exception e) {
//            logger.error("Toast error NOT displayed");
//            return false;
//        }
//    }

    public boolean isChangePasswordErrorDisplayed() {
        click(btnChangePassword);
        WaitingHelper.sleepSeconds(1);
        try {
            getDriver().findElement(toastChangePasswordError);
            logger.info("Toast error displayed");
            AllureHelper.attachScreenshot("Toast Error Displayed");
            return true;
        } catch (Exception e) {
            logger.error("Toast error NOT displayed");
            return false;
        }
    }

    public boolean isChangePasswordErrorDisplayed2() {
        click(btnChangePassword);
        WaitingHelper.sleepSeconds(1);
        try {
            getDriver().findElement(toastChangePasswordError2);
            logger.info("Toast error displayed");
            AllureHelper.attachScreenshot("Toast Error Displayed");
            return true;
        } catch (Exception e) {
            logger.error("Toast error NOT displayed");
            return false;
        }
    }

    // ==================== LEGACY METHODS ====================

    public boolean isToastErrorPhoneDisplayed() {
        logStep("Kiem tra Toast Co loi xay ra");
        try {
            getDriver().findElement(toastErrorPhone);
            logger.info("Toast error displayed");
            AllureHelper.attachScreenshot("Toast Error Displayed");
            return true;
        } catch (Exception e) {
            logger.error("Toast error NOT displayed");
            return false;
        }
    }

    public boolean isToastErrorEmailDisplayed() {
        logStep("Kiem tra Toast Co loi xay ra");
        WaitingHelper.sleepSeconds(1);

        try {
            getDriver().findElement(toastErrorEmail);
            logger.info("Toast error displayed");
            AllureHelper.attachScreenshot("Toast Error Displayed");
            return true;
        } catch (Exception e) {
            logger.error("Toast error NOT displayed");
            return false;
        }
    }

    public boolean isToastUpdateInfoDisplayed() {
        logStep("Kiem tra Toast Cap nhat thanh cong");
        WaitingHelper.sleepSeconds(1);

        try {
            getDriver().findElement(toastUpdate);
            logger.info("Toast update success displayed");
            AllureHelper.attachScreenshot("Toast Update Success Displayed");
            return true;
        } catch (Exception e) {
            logger.error("Toast update success NOT displayed");
            return false;
        }
    }

    // ==================== HELPER METHOD - XOA TEXT FIELD =====

    /**
     * Xoa text trong field bang Select All + Delete
     */
    private void clearTextField(By locator) {
        try {
            getDriver().findElement(locator).sendKeys(Keys.CONTROL + "a");
            getDriver().findElement(locator).sendKeys(Keys.DELETE);
        } catch (Exception e) {
            logger.warn("Khong the xoa field: " + e.getMessage());
        }
    }
}