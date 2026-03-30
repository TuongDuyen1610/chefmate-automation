package screens;

import core.base.BaseScreen;
import org.openqa.selenium.By;

public class ShoppingListScreen extends BaseScreen {

    private final By addManualBtn = By.xpath("//android.widget.TextView[@text='Thêm nguyên liệu thủ công']");
    private final By ingredientNameField = By.xpath("//android.widget.ScrollView/android.widget.EditText[1]");
    private final By completeBtn = By.xpath("//z0.h0/android.view.View/android.view.View[3]/android.widget.Button");

    public void addManualIngredient(String name) {
        click(addManualBtn);
        type(ingredientNameField, name);
    }

    public void completeList() {
        click(completeBtn);
    }
}