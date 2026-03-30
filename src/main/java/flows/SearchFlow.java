package flows;

import core.base.BaseFlow;
import screens.HomeScreen;

public class SearchFlow extends BaseFlow {
    private final HomeScreen homeScreen = new HomeScreen();

    public void searchRecipe(String recipeName) {
        logStep("Searching for recipe: " + recipeName);
        // Logic to search recipe
    }
}