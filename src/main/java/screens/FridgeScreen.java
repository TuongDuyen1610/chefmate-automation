package screens;

import core.base.BaseScreen;
import org.openqa.selenium.By;

public class FridgeScreen extends BaseScreen {

    private final By btnAddIngredient = By.xpath("//z0.h0/android.view.View/android.view.View/android.view.View[1]/android.view.View[2]/android.widget.Button");
    private final By ingredientName = By.xpath("//android.widget.ScrollView/android.widget.EditText[1]");
    private final By quantity = By.xpath("//android.widget.ScrollView/android.widget.EditText[2]");
    private final By unit = By.xpath("//android.widget.ScrollView/android.widget.EditText[3]");
    private final By expiryDate = By.xpath("//android.widget.ScrollView/android.widget.EditText[4]");
    private final By btnSave = By.xpath("//android.widget.Button");

    public void addIngredient(String name, String qty, String unitStr) {
        logStep("Thêm nguyên liệu: " + name);
        click(btnAddIngredient);
        type(ingredientName, name);
        type(quantity, qty);
        type(unit, unitStr);
        click(btnSave);
    }
    public void clickLoginOnFridgeGate() {
        By btnDangNhap = By.xpath("//android.widget.Button[contains(@text,'Đăng nhập')]");
        logStep("Click nút Đăng nhập trên popup Tủ lạnh");
        click(btnDangNhap);
    }
}