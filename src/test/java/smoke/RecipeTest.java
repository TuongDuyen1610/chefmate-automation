package smoke;

import core.base.BaseTest;
import flows.RecipeFlow;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RecipeTest extends BaseTest {

    private final RecipeFlow recipe = new RecipeFlow();

    @Test(priority = 1, description = "Smoke 06 - Xem chi tiết công thức")
    public void TC06_View_Recipe_Detail_Success() {
        recipe.viewFirstRecipeDetail();
        Assert.assertTrue(recipe.isRecipeDetailDisplayed(), "Không vào được màn chi tiết công thức");
        System.out.println("✅ TC06 PASS - Recipe Detail OK");
    }
}