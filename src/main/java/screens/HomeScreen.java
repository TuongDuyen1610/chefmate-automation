package screens;

import core.base.BaseScreen;
import org.openqa.selenium.By;
/**
 * HomeScreen.java
 *
 * MỤC ĐÍCH: Màn hình Trang chủ - Chứa các nút trigger Login
 */
public class HomeScreen extends BaseScreen {

    private final By txtNauNgon = By.xpath("//android.widget.TextView[@text='Nấu ngon']");
    private final By btnBepesAI = By.xpath("//android.widget.TextView[@text='Trò chuyện với Bepes']");
    private final By btnGoiYTuTuLanh = By.xpath("//android.widget.TextView[@text='Gợi ý từ tủ lạnh']");
    private final By btnSearch = By.xpath("//android.widget.ImageView[@content-desc='Tìm kiếm']");
    private final By recipeCardFirst = By.xpath("//android.widget.ScrollView/android.view.View[1]");
    private final By bottomNavHome = By.xpath("//android.widget.TextView[@text='Trang chủ']");
    private final By bottomNavFridge = By.xpath("//android.widget.TextView[@text='Tủ lạnh']");
    private final By bottomNavProfile = By.xpath("//android.widget.TextView[@text='Tài khoản']");

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

    public void openFirstRecipe() {
        logStep("Mở công thức đầu tiên");
        click(recipeCardFirst);
    }

    public boolean isHomeDisplayed() {
        return isDisplayed(txtNauNgon);
    }

    // ==================== THÊM ĐỂ TRIGGER LOGIN ====================
    public void clickBottomNavFridge() {
        logStep("Click tab Tủ lạnh trên Bottom Navigation");
        click(bottomNavFridge);
    }

    public void clickBottomNavProfile() {
        logStep("Click tab Tài khoản trên Bottom Navigation");
        click(bottomNavProfile);
    }
}