package screens;

import core.base.BaseScreen;
import core.utils.AllureHelper;
import core.utils.WaitingHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import java.time.Duration;
import java.util.Arrays;
import core.utils.AllureHelper;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HomeScreen.java
 * ✅ Updated: Thêm multiple fallback checks, explicit logging
 */
public class HomeScreen extends BaseScreen {
    private static final Logger logger = LoggerFactory.getLogger(HomeScreen.class);
    // ==================== LOCATORS ====================
    private final By txtNauNgon = By.xpath("//android.widget.TextView[@text='Nấu ngon']");
    private final By bottomNavHome = By.xpath("//android.widget.TextView[@text='Trang chủ']");

    private final By btnBepesAI = By.xpath("//android.widget.TextView[@text='Trò chuyện với Bepes']");
    private final By btnGoiYTuTuLanh = By.xpath("//android.widget.TextView[@text='Gợi ý từ tủ lạnh']");
    private final By btnSearch = By.xpath("//android.widget.ImageView[@content-desc='Tìm kiếm']");
    private final By recipeCardFirst = By.xpath("//android.widget.ScrollView/android.view.View[1]");

    private final By bottomNavFridge = By.xpath("//android.widget.TextView[@text='Tủ lạnh']");
    //    private final By bottomNavProfile = By.xpath("//android.widget.TextView[@text='Tài khoản']");
    private final By bottomNavProfile = By.xpath("//android.view.View[@content-desc='Tài khoản']");

    // ==================== ACTIONS ====================

    /**
     * ✅ KIỂM TRA MÀN HOME HIỂN THỊ - Có multiple fallback
     */
    public boolean isHomeDisplayed() {
        logStep("Verify: Đang đợi màn Home hiển thị (Chờ tối đa 20s)...");

        try {
            // Wait tối đa 20s cho element "Nấu ngon" xuất hiện
            WaitingHelper.waitForVisible(txtNauNgon);

            // Check 1: Text "Nấu ngon"
            if (isDisplayed(txtNauNgon)) {
                logStep("✅ Verify PASS: Đa vào Home!");
                logger.info("✅ Home is displayed successful");
                AllureHelper.attachScreenshot("Home is displayed successful");
                return true;
            }

        } catch (Exception e) {
            logStep("⚠️ Check 1 failed: Text 'Nấu ngon' không xuất hiện trong 20s");
        }



//        // Check 2: Fallback - Tab "Trang chủ" ở dưới
//        try {
//            if (isDisplayed(bottomNavHome)) {
//                logStep("✅ Verify PASS: Thấy Tab 'Trang chủ' - Đã vào Home!");
//                return true;
//            }
//        } catch (Exception e) {
//            logStep("⚠️ Check 2 failed: Tab 'Trang chủ' không thấy");
//        }
//
//        // Check 3: Fallback - Icon Tìm kiếm (phần t�� độc quyền Home)
//        try {
//            if (isDisplayed(btnSearch)) {
//                logStep("✅ Verify PASS: Thấy icon Tìm kiếm - Đã vào Home!");
//                return true;
//            }
//        } catch (Exception e) {
//            logStep("⚠️ Check 3 failed: Icon Tìm kiếm không thấy");
//        }

//        logStep("❌ FAIL: Không xác nhận được Home screen sau 20s!");
        return false;
    }


    public void openFirstRecipe() {
        logStep("Mở công thức đầu tiên");
        click(recipeCardFirst);
    }

    public void openBepesAI() {
        logStep("Mở Trò chuyện với Bepes");
        click(btnBepesAI);
    }

    public void openGoiYTuTuLanh() {
        logStep("Mở Gợi ý từ tủ lạnh");
        click(btnGoiYTuTuLanh);
    }

    public void clickSearch() {
        logStep("Nhấn icon tìm kiếm");
        click(btnSearch);
    }

    public void clickBottomNavFridge() {
        logStep("Click tab Tủ lạnh");
        click(bottomNavFridge);
    }

    public void clickBottomNavProfile() {
        logStep("Click tab Tài khoản");
        click(bottomNavProfile);
    }

    public void clickBepesAIButton() {
        logStep("Click nút 'Trò chuyện với Bepes'");
        click(btnBepesAI);
        core.utils.WaitingHelper.sleepSeconds(2);
    }

    public void clickFridgeSuggestionButton() {
        logStep("Click nút 'Gợi ý từ tủ lạnh'");
        click(btnGoiYTuTuLanh);
        core.utils.WaitingHelper.sleepSeconds(2);
    }
}