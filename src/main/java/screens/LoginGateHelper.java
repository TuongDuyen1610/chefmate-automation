package screens;

import core.base.BaseScreen;
import core.utils.WaitingHelper;
import org.openqa.selenium.By;

/**
 * LoginGateHelper.java
 *
 * MỤC ĐÍCH: Xử lý popup "Bạn cần đăng nhập để quản lý tủ lạnh"
 * và các popup yêu cầu đăng nhập khác
 */
public class LoginGateHelper extends BaseScreen {

    // Popup "Bạn cần đăng nhập để quản lý tủ lạnh"
    private final By popupTitle = By.xpath("//android.widget.TextView[@text='Bạn cần đăng nhập để quản lý tủ lạnh']");

    // Nút "Đăng nhập" trên popup Tủ lạnh
    private final By btnDangNhapOnPopup = By.xpath("//z0.h0/android.view.View/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View/android.widget.Button");

    /**
     * Click tab Tủ lạnh → chờ popup hiện lên → click nút Đăng nhập trên popup
     */
    public void triggerLoginByFridgeTab() {
        logStep("Click tab Tủ lạnh để trigger popup Đăng nhập");

        // Click tab Tủ lạnh (sẽ dùng Bottom Navigation sau)
        click(By.xpath("//android.widget.TextView[@text='Tủ lạnh']"));

        // Chờ popup hiện lên
        WaitingHelper.waitForVisible(popupTitle);
        WaitingHelper.sleepSeconds(1);

        // Click nút Đăng nhập trên popup
        logStep("Click nút 'Đăng nhập' trên popup Tủ lạnh");
        click(btnDangNhapOnPopup);
    }
}