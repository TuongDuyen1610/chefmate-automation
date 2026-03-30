package flows;

import core.base.BaseFlow;
import screens.LoginScreen;
import screens.SplashScreen;

/**
 * AuthenticationFlow.java
 *
 * MỤC ĐÍCH:
 * - Xây dựng các luồng chức năng liên quan đến Authentication (Đăng nhập)
 * - Kết hợp nhiều Screen lại để tạo thành một luồng hoàn chỉnh theo nghiệp vụ
 * - Đây là nơi thể hiện "Test theo luồng chức năng" thay vì test theo element
 */
public class AuthenticationFlow extends BaseFlow {

    private final SplashScreen splashScreen = new SplashScreen();
    private final LoginScreen loginScreen = new LoginScreen();

    /**
     * LUỒNG CHỨC NĂNG 1: Đăng nhập thành công với tài khoản hợp lệ
     */
    public void loginSuccessfully(String email, String password) {
        logStep("Starting Login Successfully Flow");

        // Bước 1: Chờ Splash màn hình (nếu có)
        splashScreen.waitUntilSplashDisappear();

        // Bước 2: Thực hiện đăng nhập
        loginScreen.enterEmail(email);
        loginScreen.enterPassword(password);
        loginScreen.clickLoginButton();

        logStep("Login action completed - Waiting for Home Screen");
    }

    /**
     * LUỒNG CHỨC NĂNG 2: Đăng nhập thất bại (sai mật khẩu)
     */
    public void loginFailed(String email, String wrongPassword) {
        logStep("Starting Login Failed Flow");

        splashScreen.waitUntilSplashDisappear();

        loginScreen.enterEmail(email);
        loginScreen.enterPassword(wrongPassword);
        loginScreen.clickLoginButton();
    }

    /**
     * Kiểm tra xem đã vào màn hình Home sau khi login chưa (sẽ dùng sau)
     */
    public boolean isLoggedInSuccessfully() {
        // Tạm thời return true, sau này sẽ kiểm tra bằng HomeScreen
        return true;
    }
}