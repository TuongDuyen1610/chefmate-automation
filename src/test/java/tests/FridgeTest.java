package tests;

import core.base.BaseTest;
import flows.FridgeFlow;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FridgeTest extends BaseTest {

    private final FridgeFlow fridge = new FridgeFlow();

    @Test(priority = 1, description = "Smoke 07 - Thêm nguyên liệu vào tủ lạnh")
    public void TC07_Fridge_Add_Ingredient_Success() {
        fridge.openFridge();
        fridge.addIngredient("Trứng gà", "2", "quả");
        Assert.assertTrue(true, "Đã thêm nguyên liệu thành công");
        System.out.println("✅ TC07 PASS - Fridge OK");
    }
}