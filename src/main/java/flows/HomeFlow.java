package flows;

import core.base.BaseFlow;
import screens.HomeScreen;

public class HomeFlow extends BaseFlow {

    private final HomeScreen home = new HomeScreen();

    public void openAppAndGoToHome() {
        logStep("Mở app và xác nhận vào màn hình Home");
        home.isHomeDisplayed();
    }

    public void goToBepesAI() {
        logStep("Từ Home → Mở Trò chuyện với Bepes");
        home.openBepesAI();
    }

    public void goToSearch() {
        logStep("Từ Home → Mở tìm kiếm");
        home.clickSearch();
    }

    public void openFirstRecipe() {
        logStep("Từ Home → Mở công thức thịnh hành đầu tiên");
        home.openFirstRecipe();
    }

    public void openGoiYTuTuLanh() {
        logStep("Từ Home → Mở Gợi ý từ tủ lạnh");
        home.openGoiYTuTuLanh();
    }
}