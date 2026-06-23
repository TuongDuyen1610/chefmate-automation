package tests;

import core.base.BaseTest;
import core.data.LoginData;
import core.utils.AllureHelper;
import core.utils.JsonHelper;
import flows.ShoppingBuilderFlow;
import flows.ShoppingListFlow;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.*;
import flows.ShoppingHistoryFlow;

import java.util.List;

public class ShoppingListTest extends BaseTest {
    ProfileScreen profile = new ProfileScreen();
    HomeScreen home = new HomeScreen();
    SearchScreen search = new SearchScreen();
    RecipeDetailScreen detail = new RecipeDetailScreen();
    ShoppingBuilderScreen builder = new ShoppingBuilderScreen();


    ShoppingHistoryFlow historyFlow = new ShoppingHistoryFlow();
    ShoppingBuilderFlow builderFlow = new ShoppingBuilderFlow(home, search, detail);
    ShoppingListFlow listFlow = new ShoppingListFlow();

    LoginData loginData = JsonHelper.readLoginData();
    String email = loginData.login;
    String password = loginData.password;

    private List<String> seedAndGoToShoppingListWithOneRecipeSelected() {
        builderFlow.login(email, password);

        List<String> titles = builderFlow.searchAndSaveRecipes("cà phê", 1);
        Assert.assertTrue(titles.size() >= 1, "❌ Seed failed");

        builderFlow.openShoppingBuilderFromMenu();
        builderFlow.toggleRecipeByIndex(1);
        builderFlow.clickComplete();
        listFlow.verifyListUI();

        return titles;
    }

    // ========== Manage TC_01 ==========
    @Test(priority = 1)
    public void RecipeShoppingManage_TC_01() {
        seedAndGoToShoppingListWithOneRecipeSelected();

        listFlow.openAndCloseSupplementPopup();
    }

    // ========== Manage TC_02 ==========
    @Test(priority = 2)
    public void RecipeShoppingManage_TC_02() {
        seedAndGoToShoppingListWithOneRecipeSelected();

        listFlow.addSupplementIngredient("Nguyên liệu mới", "1", "g");
        listFlow.verifyIngredientDisplayed("Nguyên liệu mới - 1 g");
    }

    // ========== Manage TC_03 ==========
    @Test(priority = 3)
    public void RecipeShoppingManage_TC_03() {
        seedAndGoToShoppingListWithOneRecipeSelected();

        listFlow.addSupplementIngredient("BổSung_1", "1", "g");
        listFlow.addSupplementIngredient("BổSung_2", "2", "g");
        listFlow.addSupplementIngredient("BổSung_3", "3", "g");

        listFlow.verifyIngredientDisplayed("BổSung_1 - 1 g");
        listFlow.verifyIngredientDisplayed("BổSung_2 - 2 g");
        listFlow.verifyIngredientDisplayed("BổSung_3 - 3 g");
    }

    // ========== Manage TC_04 ==========
    @Test(priority = 4)
    public void RecipeShoppingManage_TC_04() {
        seedAndGoToShoppingListWithOneRecipeSelected();

        listFlow.toggleCheckboxAt(0);
        listFlow.toggleCheckboxAt(0);
        listFlow.toggleCheckboxAt(0);
        listFlow.toggleCheckboxAt(0);
    }

    // ========== Manage TC_05 ==========
    @Test(priority = 5)
    public void RecipeShoppingManage_TC_05() {
        builderFlow.login(email, password);

        List<String> titles = builderFlow.searchAndSaveRecipes("cà phê", 1);
        Assert.assertTrue(titles.size() >= 1, "❌ Seed failed");

        builderFlow.openShoppingBuilderFromMenu();
        builderFlow.toggleRecipeByIndex(1);
        builderFlow.addManualIngredient("Popup_1", "1", "g");
        builderFlow.addManualIngredient("Popup_2", "2", "g");
        builderFlow.clickComplete();
        listFlow.verifyListUI();

        // add manual on builder before complete (the flow here is already in list; so we add via supplement)
        listFlow.addSupplementIngredient("Sup_1", "1", "g");
        listFlow.verifyIngredientDisplayed("Sup_1 - 1 g");

        // tick 1 ingredient
        listFlow.toggleCheckboxAt(0);
        listFlow.toggleCheckboxAt(0);
        listFlow.toggleCheckboxAt(0);
        listFlow.toggleCheckboxAt(0);
        // complete shopping list -> should return to Builder
        listFlow.clickComplete();

        // verify quay về builder bằng cách open menu builder lại (fail if app stuck)
        builderFlow.openShoppingBuilderFromMenu();
    }

    // ========== Manage TC_06 ==========
    @Test(priority = 6)
    public void RecipeShoppingManage_TC_06() {
        seedAndGoToShoppingListWithOneRecipeSelected();

        // verify UI không crash + có ingredient rows
        AllureHelper.attachScreenshot("TC06 - verify ingredient fields visually");
    }

    // ========== Manage TC_07 ==========
    @Test(priority = 7)
    public void RecipeShoppingManage_TC_07() {
        seedAndGoToShoppingListWithOneRecipeSelected();
        // chọn 1 nguyên liệu bất kỳ (index 0) -> edit name
        String newName = "Test_Name_01";
        listFlow.editIngredientNameAt(0, newName);

        // verify cập nhật hiển thị ngay
        listFlow.verifyIngredientDisplayedIgnoreCase(newName);
        AllureHelper.attachScreenshot("RecipeShoppingManage_TC_07 PASSED");
    }

    // ========== Manage TC_08 ==========
    @Test(priority = 8)
    public void RecipeShoppingManage_TC_08() {
        seedAndGoToShoppingListWithOneRecipeSelected();
        // edit qty + unit
        String newQty = "99";
        String newUnit = "G";
        listFlow.editIngredientQtyUnitAt(0, newQty, newUnit);

        // verify theo pattern hiển thị thực tế trong app (thường "... - 99 G")
        listFlow.verifyIngredientDisplayedIgnoreCase(" - 99 G");
        AllureHelper.attachScreenshot("RecipeShoppingManage_TC_08 PASSED");
    }

    // ========== Manage TC_09 ==========
    @Test(priority = 9)
    public void RecipeShoppingManage_TC_09() {
        // 1) tạo list -> vào DS mua sắm
        seedAndGoToShoppingListWithOneRecipeSelected();
        // 2) edit ingredient
        String newName = "Test_Name_01";
        String newQty = "99";
        String newUnit = "Ml";
        String expectedText = newName + " - " + newQty + " " + newUnit;
        listFlow.editIngredientAllAt(0, newName, newQty, newUnit);

        // verify ngay trên DS mua sắm
        listFlow.verifyIngredientDisplayedIgnoreCase(expectedText);

        // 3) complete list
        listFlow.clickComplete();

        // 4) mở tab profile -> history
        profile.clickTabProfile();
        historyFlow. openHistoryFromProfile();

        // 5) mở chi tiết item mới nhất
        historyFlow.openLatestHistoryDetail();

        // 6) verify trong lịch sử có đúng dữ liệu cuối
        historyFlow.verifyIngredientInLatestDetailIgnoreCase(expectedText);

        // optional: chụp màn hình cuối testcase
        AllureHelper.attachScreenshot("RecipeShoppingManage_TC_09 PASSED");
    }

    // ========== Manage TC_10 ==========
    @Test(priority = 10)
    public void RecipeShoppingManage_TC_10() {
        seedAndGoToShoppingListWithOneRecipeSelected();

        // behavior: rỗng vẫn thêm
        listFlow.addSupplementIngredient("", "", "");
        listFlow.verifyListUI();
    }

    // ========== Manage TC_11 ==========
    @Test(priority = 11)
    public void RecipeShoppingManage_TC_11() {
        seedAndGoToShoppingListWithOneRecipeSelected();

        listFlow.addSupplementIngredient("Cà chua", "2", "kg");
        listFlow.addSupplementIngredient("Cà chua", "2", "kg");

        listFlow.verifyIngredientDisplayed("Cà chua - 2 kg");
    }

    // ========== Manage TC_12 ==========
    @Test(priority = 12)
    public void RecipeShoppingManage_TC_12() {
        seedAndGoToShoppingListWithOneRecipeSelected();

        listFlow.addSupplementIngredient("@@@###", "999999999", "");
        listFlow.verifyListUI();
    }

    @Test(priority = 13)
    public void RecipeShoppingManage_TC_13() {

        seedAndGoToShoppingListWithOneRecipeSelected();

        // Add 2 ingredients
        listFlow.addSupplementIngredient("Del_1", "1", "g");
        listFlow.addSupplementIngredient("Del_2", "1", "g");

        // Force click đúng icon delete
        listFlow.clickDeleteByExactIndex(7);
        listFlow.clickDeleteByExactIndex(6);
        AllureHelper.attachScreenshot(
                "TC13 - Added 2 ingredients then deleted by exact xpath index"
        );
    }
    // ========== Manage TC_14 ==========
    @Test(priority = 14)
    public void RecipeShoppingManage_TC_14() {
        seedAndGoToShoppingListWithOneRecipeSelected();

        // bổ sung thêm vài item để chắc chắn nhiều nguyên liệu
        listFlow.addSupplementIngredient("DelAll_1", "1", "g");
        listFlow.addSupplementIngredient("DelAll_2", "1", "g");

        int total = listFlow.getIngredientCount();

        for (int i = 1; i <= total; i++) {
            String currentTop = listFlow.getIngredientTextAt(0);   // luôn lấy item đầu hiện tại
            listFlow.clickDeleteByIngredientTextIgnoreCase(currentTop);
        }

        AllureHelper.attachScreenshot("TC14 - Clicked delete for each item by text (total=" + total + ")");
    }
    // ========== Manage TC_15 ==========
    @Test(priority = 15)
    public void RecipeShoppingManage_TC_15() {
        seedAndGoToShoppingListWithOneRecipeSelected();

        // tick 1 nguyên liệu
        listFlow.toggleCheckboxAt(0);
        boolean before = listFlow.isCheckboxCheckedAt(0);

        // Không click "Hoàn thành" ở list, chỉ back ra rồi vào lại
        listFlow.clickBack();
        builder.openFromHomeMenu2();
        listFlow.verifyListUI();

        boolean after = listFlow.isCheckboxCheckedAt(0);
        Assert.assertEquals(after, before, "❌ Trạng thái tick không được giữ khi chưa hoàn thành danh sách");

        AllureHelper.attachScreenshot("TC15 - State persisted without completing list");
    }
}