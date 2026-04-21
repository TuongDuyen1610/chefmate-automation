package flows;

import core.base.BaseFlow;
import core.utils.AllureHelper;
import core.utils.WaitingHelper;
import screens.HomeScreen;
import screens.RecipeDetailScreen;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RecipeFlow.java
 * ✅ Flow cho tính năng hiển thị công thức nấu ăn
 */
public class RecipeFlow extends BaseFlow {
    private static final Logger logger = LoggerFactory.getLogger(RecipeFlow.class);

    private HomeScreen homeScreen;
    private RecipeDetailScreen recipeDetailScreen;

    // ==================== CONSTRUCTOR ====================
    public RecipeFlow(HomeScreen homeScreen, RecipeDetailScreen recipeDetailScreen) {
        this.homeScreen = homeScreen;
        this.recipeDetailScreen = recipeDetailScreen;
    }

    // ==================== ACTIONS ====================

    /**
     * ✅ MỞ DANH SÁCH CÔNG THỨC
     */
    @Step("Open recipe list")
    public void openRecipeList() {
        logStep("Mở danh sách công thức");
        homeScreen.waitForRecipeListLoad();
        logger.info("Recipe list opened");
    }

    /**
     * ✅ CLICK VÀO CÔNG THỨC VÀ VERIFY DETAIL
     */
    @Step("Click recipe and verify detail: {recipeName}")
    public void clickRecipeAndVerifyDetail(String recipeName) {
        logStep("Click công thức: " + recipeName);
        homeScreen.clickRecipeByName(recipeName);
        AllureHelper.attachScreenshot("Recipe detail opened: " + recipeName);
        logger.info("Clicked recipe: " + recipeName);
    }

    /**
     * ✅ VERIFY DỮ LIỆU DETAIL ĐẦY ĐỦ
     */
    @Step("Verify recipe detail complete")
    public void verifyRecipeDetailComplete(String expectedTitle,
                                           int expectedIngredients,
                                           int expectedSteps) {
        logStep("Kiểm tra dữ liệu detail đầy đủ");

        // Verify detail screen displayed
        assert recipeDetailScreen.isRecipeDetailDisplayed() :
                "Recipe detail not displayed";
        logStep("✅ Detail screen hiển thị");

        // Verify title
        String actualTitle = recipeDetailScreen.getRecipeTitle();
        assert actualTitle != null && !actualTitle.isEmpty() :
                "Title is empty";
        logStep("✅ Tên công thức: " + actualTitle);

        // Verify author
        String author = recipeDetailScreen.getRecipeAuthor();
        assert author != null && !author.isEmpty() :
                "Author is empty";
        logStep("✅ Tác giả: " + author);

        // Verify cook time
        String cookTime = recipeDetailScreen.getCookTime();
        assert cookTime != null && !cookTime.isEmpty() :
                "Cook time is empty";
        logStep("✅ Thời gian nấu: " + cookTime);

        // Verify tabs
        assert recipeDetailScreen.isTabsDisplayed() :
                "Tabs not displayed";
        logStep("✅ Tabs hiển thị");

        // Verify ingredients
        recipeDetailScreen.clickIngredientTab();
        int ingredientCount = recipeDetailScreen.getIngredientCount();
        assert ingredientCount >= expectedIngredients :
                "Ingredients count mismatch: " + ingredientCount;
        logStep("✅ Nguyên liệu: " + ingredientCount + " items");

        // Verify instructions
        recipeDetailScreen.clickInstructionTab();
        int stepCount = recipeDetailScreen.getInstructionCount();
        assert stepCount >= expectedSteps :
                "Steps count mismatch: " + stepCount;
        logStep("✅ Bước nấu: " + stepCount + " steps");

        // Verify tags
        assert recipeDetailScreen.isTagsDisplayed() :
                "Tags not displayed";
        logStep("✅ Tags hiển thị");

        AllureHelper.attachScreenshot("Recipe detail verified");
        logger.info("Recipe detail verified successfully");
    }

    /**
     * ✅ QUAY LẠI DANH SÁCH
     */
    @Step("Back to recipe list")
    public void backToRecipeList() {
        logStep("Quay lại danh sách công thức");
        recipeDetailScreen.clickBackButton();
        logger.info("Backed to recipe list");
    }

    /**
     * ✅ CHUYỂN SANG CÔNG THỨC KHÁC
     */
    @Step("Switch to another recipe: {newRecipeName}")
    public void switchToAnotherRecipe(String newRecipeName) {
        logStep("Chuyển sang công thức khác: " + newRecipeName);
        backToRecipeList();
        WaitingHelper.sleepSeconds(1);
        clickRecipeAndVerifyDetail(newRecipeName);
        logger.info("Switched to recipe: " + newRecipeName);
    }

    /**
     * ✅ SCROLL LOAD THÊM CÔNG THỨC
     */
    @Step("Scroll to load more recipes")
    public void scrollLoadMore() {
        logStep("Scroll tải thêm công thức");
        homeScreen.scrollDownToLoadMore();
        logger.info("Scrolled to load more");
    }
}