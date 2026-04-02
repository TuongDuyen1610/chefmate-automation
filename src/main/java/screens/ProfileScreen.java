package screens;

import core.base.BaseScreen;
import org.openqa.selenium.By;

public class ProfileScreen extends BaseScreen {

    private final By userName = By.xpath("//android.widget.TextView[@text='Tuong Thi Duyen']");
    private final By btnEdit = By.xpath("//android.view.View[@content-desc='Edit']");
    private final By btnLogout = By.xpath("//z0.h0/android.view.View/android.view.View/android.view.View[1]/android.widget.Button");

    public boolean isProfileDisplayed() {
        return isDisplayed(userName);
    }

    public void clickEditProfile() {
        logStep("Mở Chỉnh sửa hồ sơ");
        click(btnEdit);
    }

    public void logout() {
        logStep("Đăng xuất");
        click(btnLogout);
    }
    public void clickLoginOnProfileGate() {
        By btnDangNhapNgay = By.xpath("//android.widget.TextView[@text='Đăng nhập ngay']");
        logStep("Click nút Đăng nhập ngay trên Trang cá nhân");
        click(btnDangNhapNgay);
    }
    public boolean isProfileScreenDisplayed() {
        logStep("Verify: Kiểm tra màn Trang cá nhân");
        try {
            return isDisplayed(userName);
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForProfileScreen() {
        logStep("Chờ màn Tài khoản hiển thị");
        org.openqa.selenium.support.ui.WebDriverWait wait =
                new org.openqa.selenium.support.ui.WebDriverWait(
                        getDriver(), java.time.Duration.ofSeconds(20));
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(userName));
    }
}