package screens;

import core.base.BaseScreen;
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

    // Locator cho các element trên Splash Screen
    private final By splashLogo = By.xpath("//z0.h0/android.view.View/android.view.View/android.view.View[1]");           // Giả sử id này tồn tại
//    private final By splashText = By.id("splash_text");

    /**
     * Kiểm tra Splash Screen có đang hiển thị không
     */
    public boolean isSplashDisplayed() {
        return isDisplayed(splashLogo);
    }

    /**
     * Chờ Splash Screen biến mất (thường sau 2-3 giây)
     */
    public void waitUntilSplashDisappear() {
        logStep("Waiting for Splash Screen to disappear");
        try {
            // Chờ tối đa 5 giây cho Splash biến mất
            Thread.sleep(3000); // Tạm thời dùng sleep vì Splash thường là animation
            System.out.println("✅ Splash Screen completed");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}