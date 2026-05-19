package flows;

import core.utils.AllureHelper;
import core.utils.WaitingHelper;
import screens.HomeScreen;
import screens.RecipeDetailScreen;
import screens.SearchScreen;
import screens.ShoppingBuilderScreen;

import java.util.ArrayList;
import java.util.List;

public class ShoppingBuilderFlow {

    private final AuthenticationFlow authFlow = new AuthenticationFlow();

    private final HomeScreen home;
    private final SearchScreen search;
    private final RecipeDetailScreen detail;

    private final SearchFlow searchFlow;
    private final ShoppingBuilderScreen builder = new ShoppingBuilderScreen();

    public ShoppingBuilderFlow(HomeScreen home, SearchScreen search, RecipeDetailScreen detail) {
        this.home = home;
        this.search = search;
        this.detail = detail;
        this.searchFlow = new SearchFlow(home, search, detail);
    }

    public void login(String email, String password) {
        authFlow.loginFromFridgeTab(email, password);
//        WaitingHelper.sleepSeconds(2);
        if (!authFlow.isLoggedInSuccessfully()) {
            AllureHelper.attachScreenshot("LOGIN FAILED");
            throw new AssertionError("❌ Login failed");
        }
        AllureHelper.attachScreenshot("LOGIN SUCCESS");
    }

    /**
     * Search -> mở detail -> Save toast -> back. Lặp để save đủ target.
     * Return danh sách title đã save (để tick/untick).
     */
    public List<String> searchAndSaveRecipes(String tagOrKeyword, int target) {
        List<String> titles = new ArrayList<>();

        searchFlow.searchByTag(tagOrKeyword);

        int guard = 0;
        while (titles.size() < target && guard < 20) {
            search.clickResultAt(0);

            detail.waitForLoaded();

            String title = detail.getRecipeTitle();

            boolean ok = detail.clickSaveAndVerifyToast();
            WaitingHelper.sleepSeconds(1);

            if (ok && !titles.contains(title)) titles.add(title);

            detail.clickBack();
            WaitingHelper.sleepSeconds(1);

            // scroll để đổi item
            try { search.slowSwipeDownOnScreen(1); } catch (Exception ignored) {}

            guard++;
        }

        // back về Home
        try { search.clickBack(); } catch (Exception ignored) {}
        AllureHelper.attachScreenshot("Saved recipes count = " + titles.size());

        return titles;
    }

    public void openShoppingBuilderFromMenu() {
        builder.openFromHomeMenu();
        builder.verifyInitialUI();
    }
    public void openDSMS(){
        builder.openDSMS();
    }
    public void toggleRecipeByIndex(int index1Based) {
        builder.toggleRecipeByIndex(index1Based);
    }
    public void addManualIngredient(String name, String qty, String unit) {
        builder.addManualIngredient(name, qty, unit);
    }

    public void clickComplete() {
        builder.clickComplete();
    }

    public void slowSwipeDownOnScreen(){
        builder.slowSwipeDownOnScreen(2);
    }
    public void clickBack() {
        builder.clickBack();
    }
}
//// Tick A, tick B, untick A
//        builderFlow.toggleRecipeByIndex(1); // tick công thức A (item 1)
//        builderFlow.toggleRecipeByIndex(2); // tick thêm công thức B (item 2)
//        builderFlow.toggleRecipeByIndex(3); // tick thêm công thức B (item 2)
//        builderFlow.toggleRecipeByIndex(4); // tick thêm công thức B (item 2)
//        builderFlow.toggleRecipeByIndex(5); // tick thêm công thức B (item 2)
//        builderFlow.toggleRecipeByIndex(6); // tick thêm công thức B (item 2)
//        builder.slowSwipeDownInside("//z0.h0/android.view.View/android.view.View[2]", 2);
//        builderFlow.toggleRecipeByIndex(3); // tick công thức A (item 1)
//        builderFlow.toggleRecipeByIndex(4); // tick công thức A (item 1)
//        builderFlow.toggleRecipeByIndex(5); // tick công thức A (item 1)
//        builderFlow.toggleRecipeByIndex(6); // tick công thức A (item 1)
//
//// preview: chưa có xpath -> verify sau khi complete bằng tab recipe
//        builderFlow.toggleRecipeByIndex(7); // untick lại A
//
//completeToListAndVerify();
//
//        listFlow.verifyRecipeTabExists(titles.get(1));
//        builder.slowSwipeDownOnScreen(7);
//        listFlow.verifyRecipeTabNotExists(titles.get(0));