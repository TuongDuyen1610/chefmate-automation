package flows;

import core.base.BaseFlow;
import screens.LoginScreen;
import screens.SplashScreen;

public class AuthenticationFlow extends BaseFlow {

    private final SplashScreen splash = new SplashScreen();
    private final LoginScreen login = new LoginScreen();

    public void loginSuccessfully(String phoneOrEmail, String password) {
        logStep("Bắt đầu luồng đăng nhập thành công");

        splash.waitUntilSplashDisappear();

        login.enterPhoneOrEmail(phoneOrEmail);
        login.enterPassword(password);
        login.clickLogin();

        logStep("Đã nhấn Đăng nhập - Chờ chuyển sang Home");
    }

    public void loginFailed(String phoneOrEmail, String wrongPassword) {
        logStep("Bắt đầu luồng đăng nhập thất bại");

        splash.waitUntilSplashDisappear();

        login.enterPhoneOrEmail(phoneOrEmail);
        login.enterPassword(wrongPassword);
        login.clickLogin();
    }

    public boolean isLoginScreenStillDisplayed() {
        return login.isLoginScreenDisplayed();
    }
}