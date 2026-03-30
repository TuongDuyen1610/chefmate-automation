package flows;

import core.base.BaseFlow;
import screens.HomeScreen;

public class HomeFlow extends BaseFlow {

    private final HomeScreen home = new HomeScreen();

    public void openAppAndGoToHome() {
        logStep("Mở app và vào Home");
        home.isHomeDisplayed();
    }

    public void goToBepesAI() {
        home.openBepesAI();
    }

    public void goToSearch() {
        home.clickSearch();
    }
}