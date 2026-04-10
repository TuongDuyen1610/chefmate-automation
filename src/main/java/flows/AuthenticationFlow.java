//package flows;
//
//import core.base.BaseFlow;
//import core.base.BaseScreen;
//import core.utils.WaitingHelper;
//import screens.HomeScreen;
//import screens.LoginScreen;
//import screens.LoginGateHelper;
//import screens.SplashScreen;
//import screens.FridgeScreen;
//import screens.ProfileScreen;
//import screens.RegistrationScreen;
//import screens.LogoutScreen;
//import org.openqa.selenium.By;  // ✅ THÊM IMPORT NÀY
//import screens.EditProfileScreen;
///**
// * AuthenticationFlow.java
// * ✅ Updated: Thêm extra wait + better logging
// */
//public class AuthenticationFlow extends BaseFlow {
//
//    private final SplashScreen splash = new SplashScreen();
//    private final HomeScreen home = new HomeScreen();
//    private final LoginScreen login = new LoginScreen();
//    private final LoginGateHelper gateHelper = new LoginGateHelper();
//    private final FridgeScreen fridge = new FridgeScreen();
//    private final ProfileScreen profile = new ProfileScreen();
//    private final RegistrationScreen registration = new RegistrationScreen();
//    private final LogoutScreen logout = new LogoutScreen();  // ✅ FIX: Khai báo variable
//    private final EditProfileScreen edit = new EditProfileScreen();
//    // ==================== LOGIN METHODS ====================
//    // ==================== Way 1: Từ Tủ lạnh ====================
//    public void loginFromFridgeTab(String phoneOrEmail, String password) {
//        logStep("=== WAY 1: ĐĂNG NHẬP TỪ TỦ LẠNH (TAB) ===");
//        splash.waitUntilSplashDisappear();
//        gateHelper.triggerLoginByFridgeTab();
//        login.performLogin(phoneOrEmail, password);
//    }
//
//    // ==================== Way 2: Từ Tài khoản (Profile) ====================
//    public void loginFromProfileTab(String phoneOrEmail, String password) {
//        logStep("=== WAY 2: ĐĂNG NHẬP TỪ TÀI KHOẢN (PROFILE) ===");
//        splash.waitUntilSplashDisappear();
//        gateHelper.triggerLoginByProfileTab();
//        login.performLogin(phoneOrEmail, password);
//    }
//
//    // ==================== Way 3: Từ nút Bepes AI ====================
//    public void loginFromBepesAIButton(String phoneOrEmail, String password) {
//        logStep("=== WAY 3: ĐĂNG NHẬP TỪ NÚT BEPES AI ===");
//        splash.waitUntilSplashDisappear();
//        splash.clickBepesAIButton();
//        login.performLogin(phoneOrEmail, password);
//    }
//
//    // ==================== Way 4: Từ nút Gợi ý Tủ lạnh ====================
//    public void loginFromFridgeSuggestionButton(String phoneOrEmail, String password) {
//        logStep("=== WAY 4: ĐĂNG NHẬP TỪ NÚT GỢI Ý TỦ LẠNH ===");
//        splash.waitUntilSplashDisappear();
//        splash.clickFridgeSuggestionButton();
//        login.performLogin(phoneOrEmail, password);
//    }
//    public void TC_10(String password){
//        splash.waitUntilSplashDisappear();
//        gateHelper.triggerLoginByProfileTab();
//        login.performLogin_TC10(password);
//        login.clickLogin();
////        login.isToastUpdateInfoDisplayed_TC1011();
//    }
//    public void TC_11(String phoneOrEmail) {
//        splash.waitUntilSplashDisappear();
//        gateHelper.triggerLoginByProfileTab();
//        login.performLogin_TC11(phoneOrEmail);
//        login.clickLogin();
////        login.isToastUpdateInfoDisplayed_TC1011();
//    }
//    public boolean isToastUpdateInfoDisplayed_TC1011(){
//        return login.isToastUpdateInfoDisplayed_TC1011();
//    }
////    public void loginSuccessfully(String phoneOrEmail, String password) {
////        logStep("=== BẮT ĐẦU LUỒNG ĐĂNG NHẬP THÀNH CÔNG ===");
////        splash.waitUntilSplashDisappear();
////        //Tủ lạnh
////        gateHelper.triggerLoginByFridgeTab();
////        //Tài khoản
////        gateHelper.triggerLoginByProfileTab();
////
////        if (login.isLoginScreenDisplayed()) {
////            login.enterPhoneOrEmail(phoneOrEmail);
////            login.enterPassword(password);
////            login.clickLogin();
////
////            logStep("✓ Đã click nút Đăng nhập");
////            logStep("✓ Chờ thêm 3 giây để chắc app xử lý xong...");
////            WaitingHelper.sleepSeconds(3);
////
////        } else {
////            logStep("❌ LỖI: Form Đăng nhập không hiển thị!");
////        }
////    }
//
////    public void loginFailed(String phoneOrEmail, String wrongPassword) {
////        logStep("=== BẮT ĐẦU LUỒNG ĐĂNG NHẬP THẤT BẠI ===");
////        splash.waitUntilSplashDisappear();
////        //Tủ lạnh
////        gateHelper.triggerLoginByFridgeTab();
////        //Tài khoản
////        gateHelper.triggerLoginByProfileTab();
////
////        if (login.isLoginScreenDisplayed()) {
////            login.enterPhoneOrEmail(phoneOrEmail);
////            login.enterPassword(wrongPassword);
////            login.clickLogin();
////
////            logStep("✓ Đã click nút Đăng nhập với mật khẩu sai");
////            WaitingHelper.sleepSeconds(4);
////        }
////    }
//
//    public boolean isLoggedInSuccessfully() {
//        logStep("🔍 Verify: Kiểm tra đăng nhập thành công");
//        return home.isHomeDisplayed();
//    }
//
//    public boolean isLoginScreenStillDisplayed() {
//        logStep("🔍 Verify: Kiểm tra còn ở màn Login không");
//        return login.isLoginScreenDisplayed();
//    }
//
//    // ==================== REGISTRATION METHODS ====================
//
//    public void registerNewAccount(String fullName, String phone, String email, String password, String confirmPassword) {
//        logStep("=== THỰC HIỆN ĐĂNG KÝ ===");
//        splash.waitUntilSplashDisappear();
//        gateHelper.triggerLoginByFridgeTab();
//        clickRegisterNowButton();
//        registration.performRegistration(fullName, phone, email, password, confirmPassword);
//    }
//    public void registerNewAccount_toEdit(String fullName, String phone, String email, String password, String confirmPassword) {
//        logStep("=== THỰC HIỆN ĐĂNG KÝ ===");
//        splash.waitUntilSplashDisappear();
//        gateHelper.triggerLoginByFridgeTab();
//        clickRegisterNowButton();
//        registration.performRegistration(fullName, phone, email, password, confirmPassword);
//    }
//    public void registerWithoutTermsAgreement(String fullName, String phone, String email, String password) {
//        logStep("=== ĐĂNG KÝ KHÔNG TICK ĐIỀU KHOẢN ===");
//        splash.waitUntilSplashDisappear();
//        gateHelper.triggerLoginByFridgeTab();
//        clickRegisterNowButton();
//        registration.registerWithoutTermsAgreement(fullName, phone, email, password);
//    }
//    public void clickRegisterNowButton() {
//        logStep("Click nut 'Đang ky ngay'");
//        By registerNowBtn = org.openqa.selenium.By.xpath("//android.widget.TextView[@text='Đăng ký ngay']");
//        WaitingHelper.waitForClickable(registerNowBtn);
//        click(registerNowBtn);
//        WaitingHelper.sleepSeconds(3);
//    }
//
//    public boolean isRegistrationScreenDisplayed() {
//        logStep("🔍 Verify: Kiểm tra màn Đăng ký");
//        return registration.isRegistrationScreenDisplayed();
//    }
//
//    public boolean isRegistrationSuccessful() {
//        logStep("🔍 Verify: Kiểm tra đăng ký thành công");
//        return home.isHomeDisplayed();
//    }
//
//    public boolean isFillAllInfoErrorDisplayed() {
//        return registration.isFillAllInfoErrorDisplayed();
//    }
//
//    public boolean isPasswordMismatchErrorDisplayed_MK() {
//        return registration. isFillAllInfoErrorDisplayed_MK();
//    }
//
//    public boolean isTermsRequiredErrorDisplayed_DK() {
//        return registration. isFillAllInfoErrorDisplayed_DK();
//    }
//    // ==================== LOGOUT METHODS ====================
//
//    public void performLogout() {
//        logStep("=== THỰC HIỆN ĐĂNG XUẤT ===");
//        profile.clickBottomNavProfile();
//        logout.performLogout();
//    }
//
//    public void cancelLogout() {
//        logStep("=== HỦY ĐĂNG XUẤT ===");
//        profile.clickBottomNavProfile();
//        logout.cancelLogoutProcess();
//    }
//
//    public boolean isLogoutSuccessful() {
//        logStep("🔍 Verify: Kiểm tra đăng xuất thành công (quay lại Profile)");
//        WaitingHelper.sleepSeconds(1);
//        return profile.isProfileScreenDisplayed();
//    }
//
//    public boolean isStillLoggedIn() {
//        logStep("🔍 Verify: Kiểm tra vẫn đăng nhập (vẫn ở Home)");
//        return home.isHomeDisplayed();
//    }
//
//    public void goBackToLoginFromRegistration(){
//        logStep("Quay lai man dang nhap tu man dang ky");
//        splash.waitUntilSplashDisappear();
//        gateHelper.triggerLoginByFridgeTab();
//        clickRegisterNowButton();
//        registration.clickLogintoRegister();
//    }
//
//    // ==================== EDIT PROFILE ====================
////
//////    public void openEditProfileScreen() {
//////        logStep("=== MỞ MÀN CHỈNH SỬA THÔNG TIN ===");
////////        splash.waitUntilSplashDisappear();
//////        edit.clickSave();
//////        edit.clickBack();
//////        profile.clickBottomNavProfile();
//////        edit.openEditProfileScreen();
//////    }
////
//    public void updateFullName(String name) {
//        profile.clickBottomNavProfile();
//        edit.openEditProfileScreen();
//        edit.enterFullName(name);
//        edit.clickSave();
//        edit.clickBack();
//    }
//
//    public void updateEmail(String email) {
//        profile.clickBottomNavProfile();
//        edit.openEditProfileScreen();
//        edit.enterEmail(email);
//        edit.clickSave();
//        edit.clickBack();
//    }
//
//    public void updatePhone(String phone) {
//        profile.clickBottomNavProfile();
//        edit.openEditProfileScreen();
//        edit.enterPhone(phone);
//        edit.clickSave();
//        edit.clickBack();
//    }
//
//    public void updateAllInfo(String name, String email, String phone) {
//        profile.clickBottomNavProfile();
//        edit.openEditProfileScreen();
//        edit.enterFullName(name);
//        edit.enterEmail(email);
//        edit.enterPhone(phone);
//        edit.clickSave();
//        edit.clickBack();
//    }
//
//    public void saveAndBackProfile() {
//        edit.clickSave();
//        edit.clickBack();
//    }
//
//    public void openEditProfileScreen(String name, String email, String phone){
//        profile.clickBottomNavProfile();
//        edit.openEditProfileScreen();
//        edit.enterFullName(name);
//        edit.enterEmail(email);
//        edit.enterPhone(phone);
//        edit.clickSave();
//        edit.clickBack();
//        logout.clickLogoutIcon();
//        logout.confirmLogout();
//    }
//
//    public void checkDisplayErrorMsgEmail(String name, String email, String phone){
//        profile.clickBottomNavProfile();
//        edit.openEditProfileScreen();
//        edit.enterFullName(name);
//        edit.enterEmail(email);
//        edit.enterPhone(phone);
//        edit.clickSave();
//    }
//    public void checkDisplayErrorMsgSDT(String name, String email, String phone){
//        profile.clickBottomNavProfile();
//        edit.openEditProfileScreen();
//        edit.enterFullName(name);
//        edit.enterEmail(email);
//        edit.enterPhone(phone);
//        edit.clickSave();
//    }
//    public boolean isToastErrorDisplayed() {
//        return  edit.isToastErrorDisplayed();
//    }
//    public boolean isToastUpdateInfoDisplayed(){
//        return edit.isToastUpdateInfoDisplayed();
//    }
////    public void clickDangky(){
////        edit.clickRegister();
////    }
//}

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
        logStep("=== WAY 1: ĐĂNG NHẬP TỪ TỦ LẠNH ===");
        AllureHelper.step("Login from Fridge Tab");

        splash.waitUntilSplashDisappear();
        AllureHelper.step("Splash screen disappeared");

        gateHelper.triggerLoginByFridgeTab();
        AllureHelper.step("Clicked Fridge tab");

        login.performLogin(phoneOrEmail, password);
        AllureHelper.step("Login credentials entered and button clicked");
    }

    @Step("🔐 Login from Profile Tab - Phone/Email: {phoneOrEmail}, Password: {password}")
    public void loginFromProfileTab(String phoneOrEmail, String password) {
        logStep("=== WAY 2: ĐĂNG NHẬP TỪ TÀI KHOẢN ===");
        AllureHelper.step("Login from Profile Tab");

        splash.waitUntilSplashDisappear();
        AllureHelper.step("Splash screen disappeared");

        gateHelper.triggerLoginByProfileTab();
        AllureHelper.step("Clicked Profile tab");

        login.performLogin(phoneOrEmail, password);
        AllureHelper.step("Login credentials entered and button clicked");
    }

    @Step("🔐 Login from Bepes AI Button - Phone/Email: {phoneOrEmail}, Password: {password}")
    public void loginFromBepesAIButton(String phoneOrEmail, String password) {
        logStep("=== WAY 3: ĐĂNG NHẬP TỪ BEPES AI ===");
        AllureHelper.step("Login from Bepes AI Button");

        splash.waitUntilSplashDisappear();
        splash.clickBepesAIButton();
        AllureHelper.step("Clicked Bepes AI button");

        login.performLogin(phoneOrEmail, password);
        AllureHelper.step("Login credentials entered and button clicked");
    }

    @Step("🔐 Login from Fridge Suggestion Button - Phone/Email: {phoneOrEmail}, Password: {password}")
    public void loginFromFridgeSuggestionButton(String phoneOrEmail, String password) {
        logStep("=== WAY 4: ĐĂNG NHẬP TỪ GỢI Ý TỦ LẠNH ===");
        AllureHelper.step("Login from Fridge Suggestion Button");

        splash.waitUntilSplashDisappear();
        splash.clickFridgeSuggestionButton();
        AllureHelper.step("Clicked Fridge suggestion button");

        login.performLogin(phoneOrEmail, password);
        AllureHelper.step("Login credentials entered and button clicked");
    }

    @Step("✅ Verify login successful - Check Home screen displayed")
    public boolean isLoggedInSuccessfully() {
        logStep("🔍 Verify: Kiểm tra đăng nhập thành công");
        AllureHelper.step("Verify login successful");

        WaitingHelper.sleepSeconds(1); // Chờ load Home

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
        logStep("🔍 Verify: Kiểm tra còn ở màn Login");
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
    public boolean isToastUpdateInfoDisplayed_TC1011() {
        logStep("🔍 Verify: Toast error message");
        AllureHelper.step("Verify error toast message");

        return login.isToastUpdateInfoDisplayed_TC1011();
    }

    // ✅ THÊM CÁC METHOD NÀY CHO TC_10 VÀ TC_11
    public void TC_10(String password) {
        splash.waitUntilSplashDisappear();
        gateHelper.triggerLoginByProfileTab();
        login.performLogin_TC10(password);
        login.clickLogin();
    }

    public void TC_11(String phoneOrEmail) {
        splash.waitUntilSplashDisappear();
        gateHelper.triggerLoginByProfileTab();
        login.performLogin_TC11(phoneOrEmail);
        login.clickLogin();
    }

    // ==================== REGISTRATION METHODS ====================
    public void registerNewAccount(String fullName, String phone, String email, String password, String confirmPassword) {
        logStep("=== THỰC HIỆN ĐĂNG KÝ ===");
        splash.waitUntilSplashDisappear();
        gateHelper.triggerLoginByFridgeTab();
        clickRegisterNowButton();
        registration.performRegistration(fullName, phone, email, password, confirmPassword);
    }

    public void registerNewAccount_toEdit(String fullName, String phone, String email, String password, String confirmPassword) {
        logStep("=== THỰC HIỆN ĐĂNG KÝ ===");
        splash.waitUntilSplashDisappear();
        gateHelper.triggerLoginByFridgeTab();
        clickRegisterNowButton();
        registration.performRegistration(fullName, phone, email, password, confirmPassword);
    }

    public void registerWithoutTermsAgreement(String fullName, String phone, String email, String password) {
        logStep("=== ĐĂNG KÝ KHÔNG TICK ĐIỀU KHOẢN ===");
        splash.waitUntilSplashDisappear();
        gateHelper.triggerLoginByFridgeTab();
        clickRegisterNowButton();
        registration.registerWithoutTermsAgreement(fullName, phone, email, password);
    }

    public void clickRegisterNowButton() {
        logStep("Click nut 'Đang ky ngay'");
        By registerNowBtn = By.xpath("//android.widget.TextView[@text='Đăng ký ngay']");
        WaitingHelper.waitForClickable(registerNowBtn);
        click(registerNowBtn);
        WaitingHelper.sleepSeconds(1);
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
        logStep("=== THỰC HIỆN ĐĂNG XUẤT ===");
        profile.clickBottomNavProfile();
        logout.performLogout();
    }

    public void cancelLogout() {
        logStep("=== HỦY ĐĂNG XUẤT ===");
        profile.clickBottomNavProfile();
        logout.cancelLogoutProcess();
    }

    public boolean isLogoutSuccessful() {
        logStep("🔍 Verify: Kiểm tra đăng xuất thành công (quay lại Profile)");
//        WaitingHelper.sleepSeconds(1);
        return profile.isProfileScreenDisplayed();
    }

    public boolean isStillLoggedIn() {
        logStep("🔍 Verify: Kiểm tra vẫn đăng nhập (vẫn ở Home)");
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
//        edit.clickBack();
    }
    public void goBackToProfileFromEditScreen(){
        logStep("Back tu Edit Profile screen sang Profile screen");

        // ✅ WAIT để màn Edit Profile load xong
        WaitingHelper.sleepSeconds(2);

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
//        edit.clickBack();
    }

    public void updatePhone(String phone) {
        profile.clickBottomNavProfile();
        edit.openEditProfileScreen();
        edit.enterPhone(phone);
        edit.clickSave();
//        edit.clickBack();
    }

    public void updateAllInfo(String name, String email, String phone) {
        profile.clickBottomNavProfile();
        edit.openEditProfileScreen();
        edit.enterFullName(name);
        edit.enterEmail(email);
        edit.enterPhone(phone);
        edit.clickSave();
//        edit.clickBack();
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
    public boolean isToastErrorDisplayed() {
        return edit.isToastErrorDisplayed();
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
//        WaitingHelper.sleepSeconds(2);
        profile.clickBottomNavProfile();
//        WaitingHelper.sleepSeconds(2);
        logout.performLogout();
//        WaitingHelper.sleepSeconds(3);
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

//        if (!edit.isChangePasswordScreenDisplayed()) {
//            logger.error("Change password screen khong hien thi");
//            AllureHelper.attachScreenshot("Change Password Screen NOT Found");
//            throw new AssertionError("Change password screen khong hien thi");
//        }

        edit.enterCurrentPassword(currentPassword);
        edit.enterNewPassword(newPassword);
        edit.enterConfirmPassword(confirmPassword);

        edit.clickConfirmChangePassword();
        logStep("Change password flow completed");
    }

    /**
     * Verify toast change password success
     */
    public boolean isChangePasswordSuccessDisplayed() {
        logStep("Kiem tra Toast Doi mat khau thanh cong");
        return edit.isChangePasswordSuccessDisplayed();
    }

    /**
     * Verify toast change password error
     */
    public boolean isChangePasswordErrorDisplayed() {
        logStep("Kiem tra Toast Loi doi mat khau");
        return edit.isChangePasswordErrorDisplayed();
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
