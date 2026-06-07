package flows;

import screens.BepesAIScreen;
import screens.HomeScreen;

public class BepesAIFlow {

    private final HomeScreen home;
    private final BepesAIScreen bepes;

    public BepesAIFlow(HomeScreen home, BepesAIScreen bepes) {
        this.home = home;
        this.bepes = bepes;
    }

    public void openBepesFromHome() {
        home.clickTabHome();

        home.waitForRecipeListLoad();
        home.openBepesAI();

        bepes.waitForBepesLoaded();
        bepes.verifyBepesMainUI();
    }
}