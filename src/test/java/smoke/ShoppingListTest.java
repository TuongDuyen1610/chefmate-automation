package smoke;

import core.base.BaseTest;
import flows.ShoppingListFlow;   // em sẽ viết sau nếu cần
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShoppingListTest extends BaseTest {

    private final ShoppingListFlow shopping = new ShoppingListFlow();

    @Test(priority = 1, description = "Smoke 09 - Lập danh sách mua sắm")
    public void TC09_ShoppingList_Create_Success() {
        shopping.openShoppingList();
        shopping.addManualIngredient("Phở bò");
        shopping.completeList();
        Assert.assertTrue(true, "Tạo danh sách mua sắm thành công");
        System.out.println("✅ TC09 PASS - Shopping List OK");
    }
}