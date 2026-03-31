package screens;

import core.base.BaseScreen;
import core.utils.WaitingHelper;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class LoginGateHelper extends BaseScreen {

    // Tab Tủ lạnh trên thanh Navigation
    private final By tabFridge = AppiumBy.androidUIAutomator("new UiSelector().description(\"Tủ lạnh\")");

    // Tiêu đề để xác nhận đã vào đúng màn "Tủ lạnh cá nhân"
    private final By tvFridgeTitle = By.xpath("//android.widget.TextView[@text='Tủ lạnh cá nhân']");

    // Nút "Đăng nhập" màu cam nằm giữa màn hình Tủ lạnh cá nhân
    private final By btnOpenLoginForm = By.xpath("//android.widget.Button[contains(@text, 'Đăng nhập')]");

    public void goToLoginScreenViaFridge() {
        logStep("1. Click vào Tab Tủ lạnh");
        WaitingHelper.waitForClickable(tabFridge);
        click(tabFridge);

        logStep("2. Chờ màn hình 'Tủ lạnh cá nhân' hiển thị");
        WaitingHelper.waitForVisible(tvFridgeTitle);

        logStep("3. Click nút Đăng nhập để mở Form nhập liệu");
        WaitingHelper.waitForClickable(btnOpenLoginForm);
        click(btnOpenLoginForm);
    }
}