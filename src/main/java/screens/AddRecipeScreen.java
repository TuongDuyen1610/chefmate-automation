package screens;

import core.base.BaseScreen;
import org.openqa.selenium.By;

public class AddRecipeScreen extends BaseScreen {

    private final By recipeName = By.xpath("//android.widget.ScrollView/android.widget.EditText[1]");
    private final By cookingTime = By.xpath("//android.widget.ScrollView/android.widget.EditText[2]");
    private final By servings = By.xpath("//android.widget.ScrollView/android.widget.EditText[4]");
    private final By addIngredientBtn = By.xpath("//android.widget.TextView[@text='Thêm nguyên liệu']");
    private final By submitButton = By.xpath("//android.widget.ScrollView/android.view.View[6]/android.widget.Button");

    public void fillRecipeBasicInfo(String name, String time, String serving) {
        type(recipeName, name);
        type(cookingTime, time);
        type(servings, serving);
    }

    public void submitRecipe() {
        click(submitButton);
    }
}