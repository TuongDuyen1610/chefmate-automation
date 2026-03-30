package screens;

import core.base.BaseScreen;
import org.openqa.selenium.By;

import static core.utils.AssertionHelper.assertTrue;

public class HomeScreen extends BaseScreen {
    private final By homeScreen = By.xpath("//z0.h0/android.view.View/android.view.View/android.view.View[1]");

    public void validateRecipeListVisible() throws InterruptedException {
        assertTrue(isDisplayed(homeScreen));
    }
}