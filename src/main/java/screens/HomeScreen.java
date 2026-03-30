package screens;

import core.base.BaseScreen;
import org.openqa.selenium.By;

public class HomeScreen extends BaseScreen {

    // Header & Title
    private final By txtNauNgon = By.xpath("//android.widget.TextView[@text='Nấu ngon']");
    private final By txtBepesTitle = By.xpath("//android.widget.TextView[@text='Bepes - Trợ lý bếp thông minh']");

    // AI Chat & Gợi ý
    private final By btnBepesAI = By.xpath("//android.widget.TextView[@text='Trò chuyện với Bepes']");
    private final By btnGoiYTuTuLanh = By.xpath("//android.widget.TextView[@text='Gợi ý từ tủ lạnh']");

    // Search
    private final By btnSearch = By.xpath("//android.widget.ImageView[@content-desc='Tìm kiếm']");

    // Recipe cards
    private final By recipeCardFirst = By.xpath("//android.widget.ScrollView/android.view.View[1]");

    // Bottom Navigation
    private final By bottomNavHome = By.xpath("//android.widget.TextView[@text='Trang chủ']");
    private final By bottomNavFridge = By.xpath("//android.widget.TextView[@text='Tủ lạnh']");
    private final By bottomNavProfile = By.xpath("//android.widget.TextView[@text='Tài khoản']");

    // Icon chuông
    private final By notificationBell = By.xpath("//z0.h0/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.widget.Button");

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
}