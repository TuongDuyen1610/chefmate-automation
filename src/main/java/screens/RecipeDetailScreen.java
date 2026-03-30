package screens;

import core.base.BaseScreen;
import org.openqa.selenium.By;

public class RecipeDetailScreen extends BaseScreen {
    private final By recipeTitle = By.id("recipe_title");

    public String getRecipeTitle() {
        return getText(recipeTitle);
    }
}