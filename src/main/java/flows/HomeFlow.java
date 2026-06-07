package flows;

import core.base.BaseFlow;
import core.utils.AllureHelper;
import core.utils.WaitingHelper;
import screens.HomeScreen;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HomeFlow.java
 * ✅ Flow cho Home Screen
 */
public class HomeFlow extends BaseFlow {
    private static final Logger logger = LoggerFactory.getLogger(HomeFlow.class);

    private HomeScreen homeScreen;

    // ==================== CONSTRUCTOR ====================
    public HomeFlow(HomeScreen homeScreen) {
        this.homeScreen = homeScreen;
    }

    // ==================== ACTIONS ====================

    /**
     * ✅ MỞ HOME SCREEN
     */
    @Step("Open Home Screen")
    public void openHomeScreen() {
        logStep("Mở Home Screen");
        homeScreen.waitForRecipeListLoad();
        logger.info("Home screen opened");
    }

    /**
     * ✅ VERIFY HOME DISPLAYED
     */
    @Step("Verify home screen displayed")
    public void verifyHomeScreenDisplayed() {
        logStep("Kiểm tra Home screen hiển thị");
        assert homeScreen.isHomeDisplayed() : "Home screen not displayed";
        AllureHelper.attachScreenshot("✅ Home screen displayed");
        logger.info("Home screen verified");
    }

    /**
     * ✅ VERIFY RECIPE LIST DISPLAYED
     */
    @Step("Verify recipe list displayed")
    public void verifyRecipeListDisplayed() {
        logStep("Kiểm tra danh sách công thức hiển thị");
        assert homeScreen.isRecipeListDisplayed() : "Recipe list not displayed";
        AllureHelper.attachScreenshot("✅ Recipe list displayed");
        logger.info("Recipe list verified");
    }

    /**
     * ✅ VERIFY BEPES SECTION DISPLAYED
     */
    @Step("Verify Bepes section displayed")
    public void verifyBepesSectionDisplayed() {
        logStep("Kiểm tra Bepes section hiển thị");
        assert homeScreen.isBepesDisplayed() : "Bepes section not displayed";
        AllureHelper.attachScreenshot("✅ Bepes section displayed");
        logger.info("Bepes section verified");
    }

    /**
     * ✅ GET RECIPE COUNT
     */
    @Step("Get recipe count")
    public int getRecipeCount() {
        logStep("Lấy số lượng công thức");
        int count = homeScreen.getRecipeCount();
        AllureHelper.stepWithParam("Recipe count", String.valueOf(count));
        logger.info("Recipe count: " + count);
        return count;
    }

    /**
     * ✅ CLICK BEPES AI
     */
    @Step("Click Bepes AI")
    public void clickBepesAI() {
        logStep("Click Bepes AI");
        homeScreen.openBepesAI();
        logger.info("Bepes AI clicked");
    }

    /**
     * ✅ CLICK FRIDGE SUGGESTION
     */
    @Step("Click Fridge Suggestion")
    public void clickFridgeSuggestion() {
        logStep("Click Fridge Suggestion");
        homeScreen.openGoiYTuTuLanh();
        logger.info("Fridge suggestion clicked");
    }

    /**
     * ✅ CLICK SEARCH
     */
    @Step("Click Search")
    public void clickSearch() {
        logStep("Click Search");
        homeScreen.clickSearch();
        logger.info("Search clicked");
    }

    /**
     * ✅ CLICK BOTTOM NAV FRIDGE
     */
    @Step("Click Bottom Nav - Fridge")
    public void clickBottomNavFridge() {
        logStep("Click Bottom Nav - Fridge");
        homeScreen.clickBottomNavFridge();
        logger.info("Bottom nav fridge clicked");
    }

    /**
     * ✅ CLICK BOTTOM NAV PROFILE
     */
    @Step("Click Bottom Nav - Profile")
    public void clickBottomNavProfile() {
        logStep("Click Bottom Nav - Profile");
        homeScreen.clickBottomNavProfile();
        logger.info("Bottom nav profile clicked");
    }
}