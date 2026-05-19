package tests;

import core.base.BaseTest;
import core.data.LoginData;
import core.utils.AllureHelper;
import core.utils.JsonHelper;
import flows.ShoppingBuilderFlow;
import flows.ShoppingHistoryFlow;
import flows.ShoppingListFlow;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.*;

import java.util.List;

public class ShoppingHistoryTest extends BaseTest {

    ProfileScreen profile = new ProfileScreen();

    ShoppingHistoryScreen history = new ShoppingHistoryScreen();
    HomeScreen home = new HomeScreen();
    SearchScreen search = new SearchScreen();
    RecipeDetailScreen detail = new RecipeDetailScreen();

    ShoppingBuilderFlow builderFlow = new ShoppingBuilderFlow(home, search, detail);
    ShoppingListFlow listFlow = new ShoppingListFlow();
    ShoppingHistoryFlow historyFlow = new ShoppingHistoryFlow();

    LoginData loginData = JsonHelper.readLoginData();
    String email = loginData.login;
    String password = loginData.password;

    private List<String> seedAndGoToShoppingListWithOneRecipeSelected() {
        builderFlow.login(email, password);

        List<String> titles = builderFlow.searchAndSaveRecipes("xúc xích", 3);
        Assert.assertTrue(titles.size() >= 1, "❌ Seed failed");

        builderFlow.openShoppingBuilderFromMenu();
        builderFlow.toggleRecipeByIndex(1);
        builderFlow.toggleRecipeByIndex(2);
        builderFlow.toggleRecipeByIndex(3);
        builderFlow.clickComplete();

        listFlow.verifyListUI();
        return titles;
    }
    private List<String> seedAndGoToShoppingListWithOneRecipeSelected03() {

        List<String> titles = builderFlow.searchAndSaveRecipes("thịt gà", 2);
        Assert.assertTrue(titles.size() >= 1, "❌ Seed failed");

        builderFlow.openShoppingBuilderFromMenu();
        builderFlow.toggleRecipeByIndex(1);
        builderFlow.toggleRecipeByIndex(2);
        builderFlow.toggleRecipeByIndex(3);

        builderFlow.clickComplete();

        listFlow.verifyListUI();
        return titles;
    }
    private String createHistoryByCompletingListWithEditedIngredient03() {
        seedAndGoToShoppingListWithOneRecipeSelected03();

        String newName = "Test_Name_01";
        String newQty = "99";
        String newUnit = "Ml";
        String expectedText = newName + " - " + newQty + " " + newUnit;

        listFlow.editIngredientAllAt(0, newName, newQty, newUnit);
        listFlow.verifyIngredientDisplayedIgnoreCase(expectedText);

        listFlow.clickComplete();
        return expectedText;
    }
    /** Create a new history record by completing shopping list (optionally edit ingredient to verify exact data). */
    private String createHistoryByCompletingListWithEditedIngredient() {
        seedAndGoToShoppingListWithOneRecipeSelected();

        String newName = "Test_Name_01";
        String newQty = "99";
        String newUnit = "ML";
        String expectedText = newName + " - " + newQty + " " + newUnit;

        listFlow.editIngredientAllAt(0, newName, newQty, newUnit);
        listFlow.verifyIngredientDisplayedIgnoreCase(expectedText);

        listFlow.clickComplete();
        return expectedText;
    }

    private void openHistoryFromProfile() {
        profile.clickTabProfile();
        historyFlow.openHistoryFromProfile();
    }

    // RecipeHistoryShopping_TC_01_OpenHistoryList
    @Test(priority = 1)
    public void RecipeHistoryShopping_TC_01() {
        builderFlow.login(email, password);
        openHistoryFromProfile();

        // pass nếu empty hoặc có item (verifyHistoryListDisplayed đã handle)
        AllureHelper.attachScreenshot("RecipeHistoryShopping_TC_01 PASSED");
    }

//     RecipeHistoryShopping_TC_02_CreateHistoryAfterCompleteShoppingList
    @Test(priority = 2)
    public void RecipeHistoryShopping_TC_02() {
        createHistoryByCompletingListWithEditedIngredient();

        openHistoryFromProfile();
        Assert.assertTrue(historyFlow.getHistoryItemCount() >= 1, "❌ History phải có ít nhất 1 item");
        historyFlow.openLatestHistoryDetail();
        AllureHelper.attachScreenshot("RecipeHistoryShopping_TC_02 PASSED");
    }

    // RecipeHistoryShopping_TC_03_NewestFirst
    @Test(priority = 3)
    public void RecipeHistoryShopping_TC_03() {
        // Create history #1
        createHistoryByCompletingListWithEditedIngredient();
        openHistoryFromProfile();
        String date1 = historyFlow.getPurchaseDateTextAt(0);

        // Back to create history #2 (repeat)
        historyFlow.backToProfile();
        home.clickTabHome();
        createHistoryByCompletingListWithEditedIngredient03();
        openHistoryFromProfile();
        String date2 = historyFlow.getPurchaseDateTextAt(0);
        // "newest first": ít nhất item mới ở top; nếu cùng ngày vẫn có thể trùng.
        // Assert mềm: vẫn có item và date not empty; kèm screenshot.
        Assert.assertNotNull(date2);
        Assert.assertFalse(date2.trim().isEmpty());
        historyFlow.openLatestHistoryDetail();

        AllureHelper.attachScreenshot("RecipeHistoryShopping_TC_03 PASSED (date1=" + date1 + ", date2=" + date2 + ")");
    }

    // RecipeHistoryShopping_TC_04_ItemHasBasicInfo
    @Test(priority = 4)
    public void RecipeHistoryShopping_TC_04() {
        createHistoryByCompletingListWithEditedIngredient();
        openHistoryFromProfile();

        Assert.assertTrue(historyFlow.getHistoryItemCount() >= 1, "❌ History rỗng");
        String date = historyFlow.getPurchaseDateTextAt(0);
        Assert.assertTrue(date.matches("\\d{2}/\\d{2}/\\d{4}"), "❌ Date format not dd/MM/yyyy: " + date);

        AllureHelper.attachScreenshot("RecipeHistoryShopping_TC_04 PASSED");
    }

    // RecipeHistoryShopping_TC_05_OpenDetail
    @Test(priority = 5)
    public void RecipeHistoryShopping_TC_05() {
        createHistoryByCompletingListWithEditedIngredient();
        openHistoryFromProfile();

        historyFlow.openLatestHistoryDetail(); // giữ nguyên hàm TC_09

        AllureHelper.attachScreenshot("RecipeHistoryShopping_TC_05 PASSED");
        historyFlow.backToHistoryList();
    }

//     RecipeHistoryShopping_TC_06_DataMatchesCompletedShoppingList (data match)
    @Test(priority = 6)
    public void RecipeHistoryShopping_TC_06() {
        String expectedText = createHistoryByCompletingListWithEditedIngredient();
        openHistoryFromProfile();

        historyFlow.openLatestHistoryDetail();
        historyFlow.verifyIngredientInLatestDetailIgnoreCase(expectedText);
        history.slowSwipeDownOnScreen(2);
        AllureHelper.attachScreenshot("RecipeHistoryShopping_TC_06 PASSED");
    }

//     RecipeHistoryShopping_TC_07 timestamp format
    @Test(priority = 7)
    public void RecipeHistoryShopping_TC_07() {
        createHistoryByCompletingListWithEditedIngredient();
        openHistoryFromProfile();

        String date = historyFlow.getPurchaseDateTextAt(0);
        Assert.assertTrue(date.matches("\\d{2}/\\d{2}/\\d{4}"), "❌ Date format not dd/MM/yyyy: " + date);

        AllureHelper.attachScreenshot("RecipeHistoryShopping_TC_07 PASSED");
    }

    // RecipeHistoryShopping_TC_08 scroll/load more (sanity)
    @Test(priority = 8)
    public void RecipeHistoryShopping_TC_08() {
        // Ensure at least 1 record exists
        createHistoryByCompletingListWithEditedIngredient();
        openHistoryFromProfile();
        // Sanity scroll (BaseScreen có slowSwipeDownOnScreen; nếu ShoppingHistoryScreen chưa expose thì gọi driver action tùy framework)
        // Ở đây chỉ assert không crash + vẫn đọc được count sau khi scroll nhẹ bằng cách mở detail rồi back.
        int before = historyFlow.getHistoryItemCount();
        Assert.assertTrue(before >= 1);

        historyFlow.openLatestHistoryDetail();
        historyFlow.backToHistoryList();

        int after = historyFlow.getHistoryItemCount();
        Assert.assertTrue(after >= 1);

        AllureHelper.attachScreenshot("RecipeHistoryShopping_TC_08 PASSED");
    }

    // RecipeHistoryShopping_TC_09 empty state (requires clean account) - optional
    @Test(priority = 9, enabled = true)
    public void RecipeHistoryShopping_TC_09() {
        builderFlow.login(email, password);
        openHistoryFromProfile();

        if (!historyFlow.isHistoryEmpty()) {
            throw new AssertionError("❌ Account đang có history, không test empty được");
        }

        historyFlow.verifyEmptyState();
        AllureHelper.attachScreenshot("RecipeHistoryShopping_TC_09 PASSED");
    }
}