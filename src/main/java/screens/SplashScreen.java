package screens;

import core.base.BaseScreen;
import core.utils.WaitingHelper;
import org.openqa.selenium.By;

/**
 * SplashScreen.java
 *
 * MỤC ĐÍCH:
 * - Đại diện cho màn hình Splash (màn hình chào mừng đầu tiên khi mở app)
 * - Kiểm tra xem Splash có hiển thị không và chờ Splash hoàn tất để chuyển sang màn hình tiếp theo
 * - Đây thường là màn hình đầu tiên trong luồng Authentication Flow
 */
public class SplashScreen extends BaseScreen {


    private final By splashScreen = By.xpath("//android.widget.ProgressBar");
    private final By btnBepesAI = By.xpath("//android.widget.TextView[@text='Trò chuyện với Bepes']");
    private final By btnFridgeSuggestion = By.xpath("//android.widget.TextView[@text='Gợi ý từ tủ lạnh']");
    /**
     * Kiểm tra Splash Screen có đang hiển thị không
     */
    /**
     * ✅ Kiểm tra Splash Screen có hiển thị không
     */
    public boolean isSplashDisplayed() {
        logStep("Verify: Kiểm tra Splash Screen hiển thị");
        try {
            return isDisplayed(splashScreen);
        } catch (Exception e) {
            logStep("❌ Splash Screen không hiển thị");
            return false;
        }
    }
    public void waitUntilSplashDisappear() {
        logStep("Chờ Splash Screen biến mất");
        try {
            WaitingHelper.waitForVisible(splashScreen);
        } catch (Exception e) {
            logStep("Splash không xuất hiện hoặc đã biến mất");
        }
        WaitingHelper.sleepSeconds(2);
        logStep("Splash Screen completed");
    }

    public void clickBepesAIButton() {
        logStep("Click nút 'Trò chuyện với Bepes'");
        WaitingHelper.waitForClickable(btnBepesAI);
        click(btnBepesAI);
        WaitingHelper.sleepSeconds(2);
    }

    public void clickFridgeSuggestionButton() {
        logStep("Click nút 'Gợi ý từ tủ lạnh'");
        WaitingHelper.waitForClickable(btnFridgeSuggestion);
        click(btnFridgeSuggestion);
        WaitingHelper.sleepSeconds(2);
    }
}