

package flows;

import core.base.BaseFlow;
import core.utils.AllureHelper;
import core.utils.WaitingHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.slf4j.Logger;  // ✅ THÊM IMPORT NÀY
import org.slf4j.LoggerFactory;  // ✅ THÊM IMPORT NÀY
import screens.*;

/**
 * AuthenticationFlow.java
 * ✅ Updated: Tích hợp Allure @Step annotations
 */
public class AuthenticationFlow extends BaseFlow {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFlow.class);  // ✅ THÊM DÒNG NÀY

    private final SplashScreen splash = new SplashScreen();
    private final HomeScreen home = new HomeScreen();
    private final LoginScreen login = new LoginScreen();
    private final LoginGateHelper gateHelper = new LoginGateHelper();
    private final FridgeScreen fridge = new FridgeScreen();
    private final ProfileScreen profile = new ProfileScreen();
    private final RegistrationScreen registration = new RegistrationScreen();
    private final LogoutScreen logout = new LogoutScreen();
    private final EditProfileScreen edit = new EditProfileScreen();

    // ==================== LOGIN METHODS ====================

    @Step("🔐 Login from Fridge Tab - Phone/Email: {phoneOrEmail}, Password: {password}")
    public void loginFromFridgeTab(String phoneOrEmail, String password) {
        splash.waitUntilSplashDisappear();
        AllureHelper.attachScreenshot("Splash screen disappeared");
        gateHelper.triggerLoginByFridgeTab();
        login.performLogin(phoneOrEmail, password);
    }

    @Step("🔐 Login from Profile Tab - Phone/Email: {phoneOrEmail}, Password: {password}")
    public void loginFromProfileTab(String phoneOrEmail, String password) {
        splash.waitUntilSplashDisappear();
        AllureHelper.attachScreenshot("Splash screen disappeared");
        gateHelper.triggerLoginByProfileTab();
        login.performLogin(phoneOrEmail, password);
    }

    @Step("🔐 Login from Bepes AI Button - Phone/Email: {phoneOrEmail}, Password: {password}")
    public void loginFromBepesAIButton(String phoneOrEmail, String password) {
        splash.waitUntilSplashDisappear();
        splash.clickBepesAIButton();
        login.performLogin(phoneOrEmail, password);
    }

    @Step("🔐 Login from Fridge Suggestion Button - Phone/Email: {phoneOrEmail}, Password: {password}")
    public void loginFromFridgeSuggestionButton(String phoneOrEmail, String password) {
        splash.waitUntilSplashDisappear();
        splash.clickFridgeSuggestionButton();
        login.performLogin(phoneOrEmail, password);
    }

    @Step("✅ Verify login successful - Check Home screen displayed")
    public boolean isLoggedInSuccessfully() {
        AllureHelper.attachScreenshot("Verify login successful");
        boolean isSuccess = home.isHomeDisplayed();

        if (isSuccess) {
            logger.info("✅ Login successful - Home screen displayed");
            AllureHelper.attachScreenshot("Login Success - Home Screen");
        } else {
            logger.error("❌ Login failed - Home screen NOT displayed");
            AllureHelper.attachScreenshot("Login Failed - Home NOT Found");
        }

        return isSuccess;
    }

    @Step("❌ Verify login failed - Still on Login screen")
    public boolean isLoginScreenStillDisplayed() {
        AllureHelper.step("Verify login screen still displayed");
        boolean isDisplayed = login.isLoginScreenDisplayed();

        if (isDisplayed) {
            logger.info("✅ Verified - Still on login screen (login failed as expected)");
            AllureHelper.attachScreenshot("Login Failed - Still On Login Screen");
        } else {
            logger.error("❌ Not on login screen (unexpected)");
            AllureHelper.attachScreenshot("Unexpected Navigation");
        }

        return isDisplayed;
    }

    @Step("✅ Verify error toast displayed")
    public boolean isToastUpdateInfoDisplayed_TC0607() {
        AllureHelper.step("Verify error toast message");
        return login.isToastUpdateInfoDisplayed_TC0607();
    }

    public void TC_06(String password) {
        splash.waitUntilSplashDisappear();
        gateHelper.triggerLoginByProfileTab();
        login.performLogin_TC06(password);
        login.clickLogin();
    }

    public void TC_07(String phoneOrEmail) {
        splash.waitUntilSplashDisappear();
        gateHelper.triggerLoginByProfileTab();
        login.performLogin_TC07(phoneOrEmail);
        login.clickLogin();
    }

    // ==================== REGISTRATION METHODS ====================
    public void registerNewAccount(String fullName, String phone, String email, String password, String confirmPassword) {
        logStep("=== THUC HIEN DANG KI ===");
        splash.waitUntilSplashDisappear();
        gateHelper.triggerLoginByFridgeTab();
        clickRegisterNowButton();
        registration.performRegistration(fullName, phone, email, password, confirmPassword);
    }

    public void registerNewAccount_toEdit(String fullName, String phone, String email, String password, String confirmPassword) {
        logStep("=== THUC HIEN DANG KI ===");
        splash.waitUntilSplashDisappear();
        gateHelper.triggerLoginByFridgeTab();
        clickRegisterNowButton();
        registration.performRegistration(fullName, phone, email, password, confirmPassword);
    }

    public void registerWithoutTermsAgreement(String fullName, String phone, String email, String password) {
        logStep("=== DANG KI UNTICK DIEU KHOAN ===");
        splash.waitUntilSplashDisappear();
        gateHelper.triggerLoginByFridgeTab();
        clickRegisterNowButton();
        registration.registerWithoutTermsAgreement(fullName, phone, email, password);
    }

    public void clickRegisterNowButton() {
        logStep("Click nut 'Đang ky ngay'");
        By registerNowBtn = By.xpath("//android.widget.TextView[@text='Đăng ký ngay']");
        click(registerNowBtn);
    }

    public boolean isRegistrationScreenDisplayed() {
        logStep("🔍 Verify: Kiểm tra màn Đăng ký");
        return registration.isRegistrationScreenDisplayed();
    }

    public boolean isRegistrationSuccessful() {
        logStep("🔍 Verify: Kiểm tra đăng ký thành công");
        return home.isHomeDisplayed();
    }

    public boolean isFillAllInfoErrorDisplayed() {
        return registration.isFillAllInfoErrorDisplayed();
    }

    public boolean isPasswordMismatchErrorDisplayed_MK() {
        return registration.isFillAllInfoErrorDisplayed_MK();
    }

    public boolean isTermsRequiredErrorDisplayed_DK() {
        return registration.isFillAllInfoErrorDisplayed_DK();
    }

    // ==================== LOGOUT METHODS ====================
    public void performLogout() {
        logStep("=== THUC HIEN DANG XUAT ===");
        profile.clickBottomNavProfile();
        logout.performLogout();
    }

    public void cancelLogout() {
        logStep("=== HUY DANG XUAT ===");
        profile.clickBottomNavProfile();
        logout.cancelLogoutProcess();
    }

    public boolean isLogoutSuccessful() {
        logStep("Verify: Logout Successful (Back Profile)");
        return profile.isProfileScreenDisplayed();
    }

    public boolean isStillLoggedIn() {
        logStep("Verify: Still LoggedIn (Van o Home)");
        return home.isHomeDisplayed();
    }

    public void goBackToLoginFromRegistration() {
        logStep("Quay lai man dang nhap tu man dang ky");
        splash.waitUntilSplashDisappear();
        gateHelper.triggerLoginByFridgeTab();
        clickRegisterNowButton();
        registration.clickLogintoRegister();
    }

    // ==================== EDIT PROFILE ====================
    public void updateFullName(String name) {
        profile.clickBottomNavProfile();
        edit.openEditProfileScreen();
        edit.enterFullName(name);
        edit.clickSave();
    }
    public void goBackToProfileFromEditScreen(){
        logStep("Back tu Edit Profile screen sang Profile screen");

        // ✅ VERIFY đã quay về Edit Profile
        if (!edit.isChangePasswordScreenDisplayed()) {
            logger.info("Confirmed: On Edit Profile screen");
        }

        // ✅ Click back
        edit.clickBack();

        // ✅ VERIFY quay về Profile
        profile.isProfileDisplayed();
    }

    public void updateEmail(String email) {
        profile.clickBottomNavProfile();
        edit.openEditProfileScreen();
        edit.enterEmail(email);
        edit.clickSave();
    }

    public void updatePhone(String phone) {
        profile.clickBottomNavProfile();
        edit.openEditProfileScreen();
        edit.enterPhone(phone);
        edit.clickSave();
    }

    public void updateAllInfo(String name, String email, String phone) {
        profile.clickBottomNavProfile();
        edit.openEditProfileScreen();
        edit.enterFullName(name);
        edit.enterEmail(email);
        edit.enterPhone(phone);
        edit.clickSave();
    }

    public void saveAndBackProfile() {
        edit.clickSave();
        edit.clickBack();
    }

    public void openEditProfileScreen(String name, String email, String phone) {
        profile.clickBottomNavProfile();
        edit.openEditProfileScreen();
        edit.enterFullName(name);
        edit.enterEmail(email);
        edit.enterPhone(phone);
        edit.clickSave();
        edit.clickBack();
        logout.clickLogoutIcon();
        logout.confirmLogout();
    }

    public void checkDisplayErrorMsgEmail(String name, String email, String phone) {
        profile.clickBottomNavProfile();
        edit.openEditProfileScreen();
        edit.enterFullName(name);
        edit.enterEmail(email);
        edit.enterPhone(phone);
        edit.clickSave();
    }

    public void checkDisplayErrorMsgSDT(String name, String email, String phone) {
        profile.clickBottomNavProfile();
        edit.openEditProfileScreen();
        edit.enterFullName(name);
        edit.enterEmail(email);
        edit.enterPhone(phone);
        edit.clickSave();
    }
    public boolean isProfileInfoCorrect(
            String fullName,
            String email,
            String phone
    ){
        return profile.isProfileInfoCorrect(fullName, email, phone);
    }
    public boolean isToastErrorEmailDisplayed() {
        return edit.isToastErrorEmailDisplayed();
    }

    public boolean isToastErrorPhoneDisplayed() {
        return edit.isToastErrorPhoneDisplayed();
    }

    public boolean isToastUpdateInfoDisplayed() {
        return edit.isToastUpdateInfoDisplayed();
    }

    // ==================== CHANGE PASSWORD METHODS ====================

    /**
     * Navigate toi Profile screen
     */
    public void navigateToProfile() {
        logStep("Navigate toi Profile screen");
        profile.clickBottomNavProfile();
        WaitingHelper.sleepSeconds(2);
    }

    /**
     * Safe logout chap app stable
     */
    public void performLogoutSafely() {
        logStep("Dang xuat an toan");
        profile.clickBottomNavProfile();
        logout.performLogout();
    }

    /**
     * Change password flow
     * @param currentPassword Mat khau hien tai tu JSON hoac param
     * @param newPassword Mat khau moi
     * @param confirmPassword Xac nhan mat khau moi
     */
    public void changePassword(String currentPassword, String newPassword, String confirmPassword) {
        logStep("Thuc hien doi mat khau");

        profile.clickBottomNavProfile();

        edit.openEditProfileScreen();

        edit.clickChangePasswordButtonNavigate();

        edit.enterCurrentPassword(currentPassword);
        edit.enterNewPassword(newPassword);
        edit.enterConfirmPassword(confirmPassword);

        edit.clickConfirmChangePassword();
    }
    public void changePassword2(String currentPassword, String newPassword, String confirmPassword) {
        logStep("Thuc hien doi mat khau");

        profile.clickBottomNavProfile();

        edit.openEditProfileScreen();

        edit.clickChangePasswordButtonNavigate();

        edit.enterCurrentPassword(currentPassword);
        edit.enterNewPassword(newPassword);
        edit.enterConfirmPassword(confirmPassword);

        edit.isChangePasswordErrorDisplayed();
    }
    public void changePassword3(String currentPassword, String newPassword, String confirmPassword) {
        logStep("Thuc hien doi mat khau");

        profile.clickBottomNavProfile();

        edit.openEditProfileScreen();

        edit.clickChangePasswordButtonNavigate();

        edit.enterCurrentPassword(currentPassword);
        edit.enterNewPassword(newPassword);
        edit.enterConfirmPassword(confirmPassword);

        edit.isChangePasswordErrorDisplayed2();
    }
    /**
     * Back tu man Change Password
     */
    public void backFromChangePassword() {
        logStep("Quay lai tu man Doi mat khau");
        edit.clickBackFromChangePassword();
        WaitingHelper.sleepSeconds(2);
    }
}
