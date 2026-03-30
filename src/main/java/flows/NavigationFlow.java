package flows;

import core.base.BaseFlow;
import screens.HomeScreen;

public class NavigationFlow extends BaseFlow {

    private final HomeScreen home = new HomeScreen();

    public void goToHome() {
        logStep("Chuyển sang tab Trang chủ");
        // Click bottom nav home nếu cần
    }

    public void goToFridge() {
        logStep("Chuyển sang tab Tủ lạnh");
        // Click bottom nav fridge
    }

    public void goToProfile() {
        logStep("Chuyển sang tab Tài khoản");
        // Click bottom nav profile
    }
}