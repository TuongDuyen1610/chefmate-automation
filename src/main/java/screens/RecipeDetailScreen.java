package screens;

import core.base.BaseScreen;
import org.openqa.selenium.By;

public class RecipeDetailScreen extends BaseScreen {

    private final By favoriteButton = By.xpath("//android.view.View[@content-desc='Like']");
    private final By ingredientsTab = By.xpath("//android.widget.TextView[@text='Nguyên liệu']");
    private final By stepsTab = By.xpath("//android.widget.TextView[@text='Cách thực hiện']");

    public boolean isRecipeDetailDisplayed() {
        return isDisplayed(favoriteButton);
    }

    public void addToFavorite() {
        logStep("Thêm vào yêu thích");
        click(favoriteButton);
    }
}