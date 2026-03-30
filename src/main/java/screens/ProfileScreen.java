package screens;

import core.base.BaseScreen;
import org.openqa.selenium.By;

public class ProfileScreen extends BaseScreen {

    private final By txtUserName = By.xpath("//android.widget.TextView[@text='Tuong Thi Duyen']");
    private final By btnEditProfile = By.xpath("//android.view.View[@content-desc='Edit']");
    private final By btnLogout = By.xpath("//z0.h0/android.view.View/android.view.View/android.view.View[1]/android.widget.Button");

    public boolean isProfileDisplayed() {
        return isDisplayed(txtUserName);
    }

    public void clickEditProfile() {
        logStep("Nhấn Chỉnh sửa hồ sơ");
        click(btnEditProfile);
    }

    public void logout() {
        logStep("Đăng xuất");
        click(btnLogout);
    }
}