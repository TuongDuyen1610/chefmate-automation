package flows;

import core.base.BaseFlow;
import screens.RecipeDetailScreen;

public class RecipeFlow extends BaseFlow {
    private final RecipeDetailScreen recipeDetailScreen = new RecipeDetailScreen();

    public String getRecipeTitle() {
        return recipeDetailScreen.getRecipeTitle();
    }
}