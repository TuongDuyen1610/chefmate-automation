package flows;

import core.base.BaseFlow;
import screens.HomeScreen;
import screens.RecipeDetailScreen;

public class RecipeFlow extends BaseFlow {

    private final HomeScreen home = new HomeScreen();
    private final RecipeDetailScreen detail = new RecipeDetailScreen();

    public void viewFirstRecipeDetail() {
        logStep("Từ Home mở chi tiết công thức đầu tiên");
        home.openFirstRecipe();
    }

    public void addCurrentRecipeToFavorite() {
        logStep("Thêm công thức hiện tại vào yêu thích");
        detail.addToFavorite();
    }

    public boolean isRecipeDetailDisplayed() {
        return detail.isRecipeDetailDisplayed();
    }
}