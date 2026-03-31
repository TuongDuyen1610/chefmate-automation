package flows;

import core.base.BaseFlow;
import core.utils.WaitingHelper;
import screens.HomeScreen;
import screens.LoginScreen;
import screens.LoginGateHelper;
import screens.SplashScreen;

/**
 * AuthenticationFlow.java
 * ✅ Updated: Thêm extra wait + better logging
 */
public class AuthenticationFlow extends BaseFlow {

    private final SplashScreen splash = new SplashScreen();
    private final HomeScreen home = new HomeScreen();
    private final LoginScreen login = new LoginScreen();
    private final LoginGateHelper gateHelper = new LoginGateHelper();

    public void loginSuccessfully(String phoneOrEmail, String password) {
        logStep("=== BẮT ĐẦU LUỒNG ĐĂNG NHẬP THÀNH CÔNG ===");
        splash.waitUntilSplashDisappear();

        gateHelper.triggerLoginByFridgeTab();

        if (login.isLoginScreenDisplayed()) {
            login.enterPhoneOrEmail(phoneOrEmail);
            login.enterPassword(password);
            login.clickLogin();

            logStep("✓ Đã click nút Đăng nhập");
            logStep("✓ Chờ thêm 3 giây để chắc app xử lý xong...");
            WaitingHelper.sleepSeconds(3);

        } else {
            logStep("❌ LỖI: Form Đăng nhập không hiển thị!");
        }
    }

    public void loginFailed(String phoneOrEmail, String wrongPassword) {
        logStep("=== BẮT ĐẦU LUỒNG ĐĂNG NHẬP THẤT BẠI ===");
        splash.waitUntilSplashDisappear();

        gateHelper.triggerLoginByFridgeTab();

        if (login.isLoginScreenDisplayed()) {
            login.enterPhoneOrEmail(phoneOrEmail);
            login.enterPassword(wrongPassword);
            login.clickLogin();

            logStep("✓ Đã click nút Đăng nhập với mật khẩu sai");
            WaitingHelper.sleepSeconds(4);
        }
    }

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