package flows;

import core.base.BaseFlow;
import screens.HomeScreen;

public class HomeFlow extends BaseFlow {
    private final HomeScreen homeScreen = new HomeScreen();

    public void validateHomePage() throws InterruptedException {
        logStep("Validating Home Page");
        homeScreen.validateRecipeListVisible();
    }
}