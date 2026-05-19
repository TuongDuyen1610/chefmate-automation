package tests;

import core.base.BaseTest;
import core.data.LoginData;
import core.utils.AllureHelper;
import core.utils.JsonHelper;
import flows.AuthenticationFlow;
import flows.ShoppingBuilderFlow;
import flows.ShoppingListFlow;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.HomeScreen;
import screens.RecipeDetailScreen;
import screens.SearchScreen;
import screens.ShoppingBuilderScreen;

import java.util.List;

public class ShoppingBuilderTest extends BaseTest {

    ShoppingBuilderScreen builder = new ShoppingBuilderScreen();
    HomeScreen home = new HomeScreen();
    SearchScreen search = new SearchScreen();
    RecipeDetailScreen detail = new RecipeDetailScreen();

    private final AuthenticationFlow authFlow = new AuthenticationFlow();
    ShoppingBuilderFlow builderFlow = new ShoppingBuilderFlow(home, search, detail);
    ShoppingListFlow listFlow = new ShoppingListFlow();

    LoginData loginData = JsonHelper.readLoginData();
    String email = loginData.login;
    String password = loginData.password;

    // =============== COMMON ===============
    private List<String> seedSavedRecipes(int target) {
        builderFlow.login(email, password);
        // tag có thể đổi tùy data app
        List<String> titles = builderFlow.searchAndSaveRecipes("xúc xích", target);
        Assert.assertTrue(titles.size() >= Math.min(1, target), "❌ Seed saved recipes failed");
        return titles;
    }

    private void openBuilder() {
        builderFlow.openShoppingBuilderFromMenu();
    }

    private void completeToListAndVerify() {
        builderFlow.clickComplete();
        listFlow.verifyListUI();
    }

    // =============== FEATURE 7 TESTCASES ===============

    @Test(priority = 1)
    public void RecipeShopping_TC_01() {
        seedSavedRecipes(3);
        openBuilder();
        // verify UI đã nằm trong openShoppingBuilderFromMenu()
    }

    @Test(priority = 2)
    public void RecipeShopping_TC_02() {
        List<String> titles = seedSavedRecipes(10);
        Assert.assertTrue(titles.size() >= 10, "❌ Cần >=2 công thức để tick/untick");

        openBuilder();

        // Tick A, tick B, untick A
        builderFlow.toggleRecipeByIndex(1); //  tick công thức titles.get(0)
        builderFlow.toggleRecipeByIndex(2); //  tick công thức titles.get(1)
        builderFlow.toggleRecipeByIndex(3); //  tick công thức titles.get(2)
        builderFlow.toggleRecipeByIndex(4); //  tick công thức titles.get(3)
        builderFlow.toggleRecipeByIndex(5); //  tick công thức titles.get(4)
        builderFlow.toggleRecipeByIndex(6); //  tick công thức titles.get(5)
        builder.slowSwipeDownInside("//z0.h0/android.view.View/android.view.View[2]", 2);
        builderFlow.toggleRecipeByIndex(3); // tick công thức A (item 1)
        builderFlow.toggleRecipeByIndex(4); // tick công thức A (item 1)
        builderFlow.toggleRecipeByIndex(5); // tick công thức A (item 1)
        builderFlow.toggleRecipeByIndex(6); // tick công thức A (item 1)

        // preview: chưa có xpath -> verify sau khi complete bằng tab recipe
        builderFlow.toggleRecipeByIndex(1); // untick lại titles.get(0)

        completeToListAndVerify();

        //Verify item tick
        listFlow.verifyRecipeTabExists(titles.get(0));// xuat hien index 1
        listFlow.verifyRecipeTabExists(titles.get(1)); // xuat hien index 2
        listFlow.verifyRecipeTabExists(titles.get(2)); // xuat hien index 3

        //Verify item untick
        listFlow.verifyRecipeTabNotExists(titles.get(4)); // khong xuat hien index 4 (vì bị ảo do index)

        builder.slowSwipeDownOnScreen(8);

    }
    @Test(priority = 3)
    public void RecipeShopping_TC_03() {
        seedSavedRecipes(2);
        openBuilder();

        builderFlow.addManualIngredient("Test_1", "4", "g");
        builderFlow.addManualIngredient("Test_2", "8", "g");
        builderFlow.addManualIngredient("Test_3", "9", "g");

        completeToListAndVerify();

        listFlow.verifyIngredientDisplayed("Test_1 - 4 g");
        builderFlow.slowSwipeDownOnScreen();
        listFlow.verifyIngredientDisplayed("Test_2 - 8 g");
        builderFlow.slowSwipeDownOnScreen();
        listFlow.verifyIngredientDisplayed("Test_3 - 9 g");
    }

    @Test(priority = 4)
    public void RecipeShopping_TC_04() {
//       Seed 1 recipe → tick được index 1 (View[1]):
//        theo “index chết”:
//        Seed 1 recipe → tick được index 1
//        Seed 2 recipe → tick được index 1 và 2
//        Seed 3 recipe → tick được index 1, 2, 3
        List<String> titles = seedSavedRecipes(1);
        openBuilder();

        builderFlow.toggleRecipeByIndex(1); // tick công thức A (item 1)
        builderFlow.addManualIngredient("Manual_1", "1", "kg");

        completeToListAndVerify();

        listFlow.verifyRecipeTabExists(titles.get(0));
        listFlow.verifyIngredientDisplayed("Manual_1 - 1 kg");
    }

    @Test(priority = 5)
    public void RecipeShopping_TC_05() {
        seedSavedRecipes(1);
        openBuilder();

        // Open sheet -> close -> open again (thực hiện bằng add 1 lần, rồi add lần 2)
        builderFlow.addManualIngredient("Popup_1", "1", "g");
        builderFlow.addManualIngredient("Popup_2", "2", "g");

        completeToListAndVerify();
        listFlow.verifyIngredientDisplayed("Popup_1 - 1 g");
        listFlow.verifyIngredientDisplayed("Popup_2 - 2 g");
    }

    @Test(priority = 6)
    public void RecipeShopping_TC_06() {
        seedSavedRecipes(1);
        openBuilder();

        builderFlow.addManualIngredient("Cà chua", "2", "kg");

        // verify ở list
        completeToListAndVerify();
        listFlow.verifyIngredientDisplayed("Cà chua - 2 kg");
    }

    @Test(priority = 7)
    public void RecipeShopping_TC_07() {
        seedSavedRecipes(1);
        openBuilder();

        // add 5 items
        for (int i = 1; i <= 5; i++) {
            builderFlow.addManualIngredient("Multi_" + i, String.valueOf(i), "g");
        }

        completeToListAndVerify();

        for (int i = 1; i <= 5; i++) {
            listFlow.verifyIngredientDisplayed("Multi_" + i + " - " + i + " g");
        }
    }

    @Test(priority = 8)
    public void RecipeShopping_TC_08() {
        // Guest: KHÔNG login, KHÔNG seed
        openBuilder(); // vẫn phải vào được theo mô tả “Guest vẫn tạo được”
        builderFlow.addManualIngredient("Guest_1", "1", "g");

        completeToListAndVerify();
        listFlow.verifyIngredientDisplayed("Guest_1 - 1 g");
    }

    @Test(priority = 9)
    public void RecipeShopping_TC_09() {
        // 1) tạo guest list
        openBuilder();
        builderFlow.addManualIngredient("GuestPersist", "2", "g");
        completeToListAndVerify();
        listFlow.verifyIngredientDisplayedIgnoreCase("GuestPersist - 2 g");
        listFlow.clickBack();
        authFlow.performLogout();
        // 2) login sau
        builderFlow.login(email, password);

        // 3) vào lại builder -> complete -> verify còn giữ
        builderFlow.openDSMS();
        listFlow.verifyIngredientDisplayedIgnoreCase("GuestPersist - 2 g");
    }

    @Test(priority = 10)
    public void RecipeShopping_TC_10() {
        seedSavedRecipes(1);
        openBuilder();

        // behavior hiện tại: rỗng vẫn tạo
        builderFlow.addManualIngredient("", "", "");

        completeToListAndVerify();
        // không assert text vì rỗng khó match 100% UI; chỉ verify không crash & vào list được
        AllureHelper.attachScreenshot("TC10 - empty manual added");
    }

    @Test(priority = 11)
    public void RecipeShopping_TC_11() {
        seedSavedRecipes(1);
        openBuilder();

        builderFlow.addManualIngredient("@@@###!!!", "-10", "");
        completeToListAndVerify();

        // chỉ verify không crash
        AllureHelper.attachScreenshot("TC11 - abnormal manual added");
    }


    //Rule merge = cùng tên + cùng đơn vị ⇒ cộng dồn khối lượng
    @Test(priority = 12)
    public void RecipeShopping_TC_12() {
        seedSavedRecipes(1);
        openBuilder();

        // add duplicate twice
        builderFlow.addManualIngredient("Cà chua", "2", "kg");
        builderFlow.addManualIngredient("Cà chua", "2", "kg");

        completeToListAndVerify();
        // verify ít nhất 1 item tồn tại; muốn verify 2 item cần locator theo index list item text
        listFlow.verifyIngredientDisplayedIgnoreCase("Cà chua - 4 kg");
    }
}