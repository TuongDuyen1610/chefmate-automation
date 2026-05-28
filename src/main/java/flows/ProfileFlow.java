package flows;

import core.base.BaseFlow;
import screens.HomeScreen;
import screens.ProfileScreen;

public class ProfileFlow extends BaseFlow {

    private final HomeScreen home = new HomeScreen();
    private final ProfileScreen profile = new ProfileScreen();

    public void openProfile() {
        logStep("Từ Home mở Trang cá nhân");
        // Chưa có BottomNavigationComponent nên tạm dùng cách khác nếu cần
    }

    public void logout() {
        logStep("Đăng xuất từ Profile");
        profile.logout();
    }

    public boolean isProfileDisplayed() {
        return profile.isProfileDisplayed();
    }
    public void clickProfileDisplayed() {
        profile.clickBottomNavProfile();
    }
    public boolean isNoChangeMessageDisplayed() {
        return profile.isNoChangeMessageDisplayed();
    }
}