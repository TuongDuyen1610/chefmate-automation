package flows;

import core.base.BaseFlow;
import core.utils.WaitingHelper;
import screens.HomeScreen;
import screens.LoginScreen;
import screens.LoginGateHelper;
import screens.SplashScreen;
import screens.FridgeScreen;
import screens.ProfileScreen;
/**
 * AuthenticationFlow.java
 * ✅ Updated: Thêm extra wait + better logging
 */
public class AuthenticationFlow extends BaseFlow {

    private final SplashScreen splash = new SplashScreen();
    private final HomeScreen home = new HomeScreen();
    private final LoginScreen login = new LoginScreen();
    private final LoginGateHelper gateHelper = new LoginGateHelper();
    private final FridgeScreen fridge = new FridgeScreen();
    private final ProfileScreen profile = new ProfileScreen();

    // ==================== Way 1: Từ Tủ lạnh ====================
    public void loginFromFridgeTab(String phoneOrEmail, String password) {
        logStep("=== WAY 1: ĐĂNG NHẬP TỪ TỦ LẠNH (TAB) ===");
        splash.waitUntilSplashDisappear();
        gateHelper.triggerLoginByFridgeTab();
        login.performLogin(phoneOrEmail, password);
    }

    // ==================== Way 2: Từ Tài khoản (Profile) ====================
    public void loginFromProfileTab(String phoneOrEmail, String password) {
        logStep("=== WAY 2: ĐĂNG NHẬP TỪ TÀI KHOẢN (PROFILE) ===");
        splash.waitUntilSplashDisappear();
        gateHelper.triggerLoginByProfileTab();
//        home.clickBottomNavProfile();
//        profile.waitForProfileScreen();
//        profile.clickLoginOnProfileGate();
        login.performLogin(phoneOrEmail, password);
    }

    // ==================== Way 3: Từ nút Bepes AI ====================
    public void loginFromBepesAIButton(String phoneOrEmail, String password) {
        logStep("=== WAY 3: ĐĂNG NHẬP TỪ NÚT BEPES AI ===");
        splash.waitUntilSplashDisappear();
        splash.clickBepesAIButton();
        login.performLogin(phoneOrEmail, password);
    }

    // ==================== Way 4: Từ nút Gợi ý Tủ lạnh ====================
    public void loginFromFridgeSuggestionButton(String phoneOrEmail, String password) {
        logStep("=== WAY 4: ĐĂNG NHẬP TỪ NÚT GỢI Ý TỦ LẠNH ===");
        splash.waitUntilSplashDisappear();
        splash.clickFridgeSuggestionButton();
        login.performLogin(phoneOrEmail, password);
    }

//    public void loginSuccessfully(String phoneOrEmail, String password) {
//        logStep("=== BẮT ĐẦU LUỒNG ĐĂNG NHẬP THÀNH CÔNG ===");
//        splash.waitUntilSplashDisappear();
//        //Tủ lạnh
//        gateHelper.triggerLoginByFridgeTab();
//        //Tài khoản
//        gateHelper.triggerLoginByProfileTab();
//
//        if (login.isLoginScreenDisplayed()) {
//            login.enterPhoneOrEmail(phoneOrEmail);
//            login.enterPassword(password);
//            login.clickLogin();
//
//            logStep("✓ Đã click nút Đăng nhập");
//            logStep("✓ Chờ thêm 3 giây để chắc app xử lý xong...");
//            WaitingHelper.sleepSeconds(3);
//
//        } else {
//            logStep("❌ LỖI: Form Đăng nhập không hiển thị!");
//        }
//    }

//    public void loginFailed(String phoneOrEmail, String wrongPassword) {
//        logStep("=== BẮT ĐẦU LUỒNG ĐĂNG NHẬP THẤT BẠI ===");
//        splash.waitUntilSplashDisappear();
//        //Tủ lạnh
//        gateHelper.triggerLoginByFridgeTab();
//        //Tài khoản
//        gateHelper.triggerLoginByProfileTab();
//
//        if (login.isLoginScreenDisplayed()) {
//            login.enterPhoneOrEmail(phoneOrEmail);
//            login.enterPassword(wrongPassword);
//            login.clickLogin();
//
//            logStep("✓ Đã click nút Đăng nhập với mật khẩu sai");
//            WaitingHelper.sleepSeconds(4);
//        }
//    }

    public boolean isLoggedInSuccessfully() {
        logStep("🔍 Verify: Kiểm tra đăng nhập thành công");
        return home.isHomeDisplayed();
    }

    public boolean isLoginScreenStillDisplayed() {
        logStep("🔍 Verify: Kiểm tra còn ở màn Login không");
        return login.isLoginScreenDisplayed();
    }

    public void logout() {
        logStep("Thực hiện Đăng xuất");
    }
}