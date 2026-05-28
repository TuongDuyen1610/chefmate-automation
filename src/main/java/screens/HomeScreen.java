package screens;

import core.base.BaseScreen;
import core.utils.AllureHelper;
import core.utils.WaitingHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HomeScreen.java
 * ✅ Sử dụng getDriver() từ BaseScreen
 */
public class HomeScreen extends BaseScreen {
    private static final Logger logger = LoggerFactory.getLogger(HomeScreen.class);
    private final By btnBack = By.xpath("//android.view.View[@content-desc='Quay lại']");
    // ==================== LOCATORS ====================
    private final By txtNauNgon = By.xpath("//android.widget.TextView[@text='Nấu ngon']");
    private final By btnNotification = By.xpath("//android.view.View[@content-desc='Thông báo']");
    private final By btnSearch = By.xpath("//android.widget.ImageView[@content-desc='Tìm kiếm']");
    private final By searchField = By.xpath("//android.widget.EditText");

    private final By bepesTitle = By.xpath("//android.widget.TextView[@text='Bepes - Trợ lý bếp thông minh']");
    private final By btnBepesAI = By.xpath("//android.widget.TextView[@text='Trò chuyện với Bepes']");
    private final By btnGoiYTuTuLanh = By.xpath("//android.widget.TextView[@text='Gợi ý từ tủ lạnh']");

    private final By lblTopTrendingRecipes = By.xpath("//android.widget.TextView[@text='Top thịnh hành']");
    private final By recipeScrollView = By.xpath("//android.widget.ScrollView");

    private final By recipeItemsGeneric = By.xpath(
            "//android.widget.ScrollView//android.view.View[@content-desc and " +
                    "not(contains(@content-desc, 'Like')) and " +
                    "not(contains(@content-desc, 'Share'))]"
    );

    private final By recipeCardFirst = By.xpath("(//android.widget.ScrollView//android.view.View[@content-desc])[1]");

    private final By bottomNavHome = By.xpath("//android.view.View[@content-desc='Trang chủ']");
    private final By bottomNavFridge = By.xpath("//android.view.View[@content-desc='Tủ lạnh']");
    private final By bottomNavProfile = By.xpath("//android.view.View[@content-desc='Tài khoản']");
    private final By bottomNavOptions = By.xpath("//android.view.View[@content-desc='Options']");

    /**
     * ✅ KIỂM TRA MÀN HOME HIỂN THỊ
     */
//    public boolean isHomeDisplayed() {
//        AllureHelper.step("Verify: Kiểm tra màn Home hiển thị (Chờ tối đa 20s)");
//        logStep("Verify: Đang đợi màn Home hiển thị (Chờ tối đa 20s)...");
//
//        try {
////            WaitingHelper.waitForVisible(txtNauNgon);
//            if (isDisplayed(txtNauNgon) && isDisplayed(lblTopTrendingRecipes) ) {
//                logStep("✅ Verify PASS: Đã vào Home!");
//                logger.info("✅ Home is displayed successful");
//                AllureHelper.attachScreenshot("✅ Home is displayed successful");
//                return true;
//            }
//        } catch (Exception e) {
//            logStep("❌ Home screen not displayed: " + e.getMessage());
//            logger.error("Home screen failed to display", e);
//            AllureHelper.attachErrorMessage("Home screen error: " + e.getMessage());
//            return false;
//        }
//        return false;
//    }
    public boolean isHomeDisplayed() {

//        try {
//            return getDriver().findElements(lblTopTrendingRecipes).size() > 0;
//        } catch (Exception e) {
//            return false;
//        }
        try {
            boolean result = isDisplayed(lblTopTrendingRecipes);
            if (result) {
                AllureHelper.attachScreenshot("✅ Home screen displayed");
            }
            return result;
        } catch (Exception e) {
            logger.error("Home Screen not displayed", e);
            AllureHelper.attachErrorMessage("Home screen error: " + e.getMessage());
            return false;
        }

    }

    /**
     * ✅ KIỂM TRA DANH SÁCH CÔNG THỨC HIỂN THỊ
     */
    @Step("Verify recipe list displayed")
    public boolean isRecipeListDisplayed() {
        AllureHelper.step("Verify recipe list displayed");
        logStep("Kiểm tra danh sách công thức hiển thị");
        try {
            boolean result = isDisplayed(lblTopTrendingRecipes) && isDisplayed(recipeScrollView);
            if (result) {
                AllureHelper.attachScreenshot("✅ Recipe list displayed");
            }
            return result;
        } catch (Exception e) {
            logger.error("Recipe list not displayed", e);
            AllureHelper.attachErrorMessage("Recipe list error: " + e.getMessage());
            return false;
        }
    }

    /**
     * ✅ LẤY SỐ LƯỢNG CÔNG THỨC
     */
    @Step("Get recipe count")
    public int getRecipeCount() {
        AllureHelper.step("Get recipe count");
        logStep("Lấy số lượng công thức");
        try {
            // ✅ Dùng getDriver().findElements()
            List<WebElement> recipes = getDriver().findElements(recipeItemsGeneric);
            int count = recipes.size();
            logger.info("Found " + count + " recipes");
            AllureHelper.stepWithParam("Recipe Count", String.valueOf(count));
            return count;
        } catch (Exception e) {
            logger.error("Error getting recipe count", e);
            AllureHelper.attachErrorMessage("Get recipe count error: " + e.getMessage());
            return 0;
        }
    }

    /**
     * ✅ LẤY TÊN CÔNG THỨC TẠI VỊ TRÍ INDEX
     */
    @Step("Get recipe name at index {index}")
    public String getRecipeNameAt(int index) {
        AllureHelper.stepWithParam("Get recipe name at index", String.valueOf(index));
        logStep("Lấy tên công thức ở vị trí " + index);
        try {
            List<WebElement> recipes = getDriver().findElements(recipeItemsGeneric);
            if (index < recipes.size()) {
                String recipeName = recipes.get(index).getAttribute("content-desc");
                logger.info("Recipe at index " + index + ": " + recipeName);
                AllureHelper.stepWithParam("Recipe name", recipeName);
                return recipeName;
            }
        } catch (Exception e) {
            logger.error("Error getting recipe at index " + index, e);
            AllureHelper.attachErrorMessage("Get recipe at index error: " + e.getMessage());
        }
        return null;
    }

    /**
     * ✅ CLICK VÀO CÔNG THỨC THEO TÊN
     */
    @Step("Click recipe by name: {recipeName}")
    public void clickRecipeByName(String recipeName) {
        AllureHelper.stepWithParam("Click recipe by name", recipeName);
        logStep("Click vào công thức: " + recipeName);
        try {
            // ✅ Tạo By locator rồi dùng click(By)
            By recipeLocator = By.xpath("//android.widget.ScrollView//android.view.View[@content-desc='" +
                    recipeName + "']");
            click(recipeLocator);
            WaitingHelper.sleepSeconds(1);
            logger.info("Clicked recipe: " + recipeName);
            AllureHelper.attachScreenshot("✅ Clicked recipe: " + recipeName);
        } catch (Exception e) {
            logger.error("Error clicking recipe: " + recipeName, e);
            AllureHelper.attachErrorMessage("Click recipe error: " + e.getMessage());
            throw new RuntimeException("Cannot click recipe: " + recipeName, e);
        }
    }

    /**
     * ✅ CLICK VÀO CÔNG THỨC ĐẦU TIÊN
     */
    @Step("Click first recipe")
    public void openFirstRecipe() {
        AllureHelper.step("Click first recipe");
        logStep("Mở công thức đầu tiên");
        try {
            click(recipeCardFirst);
            WaitingHelper.sleepSeconds(1);
            logger.info("Opened first recipe");
        } catch (Exception e) {
            logger.error("Error opening first recipe", e);
            AllureHelper.attachErrorMessage("Open first recipe error: " + e.getMessage());
            throw e;
        }
    }

    /**
     * ✅ MỞ BEPES AI
     */
    @Step("Open Bepes AI")
    public void openBepesAI() {
        AllureHelper.step("Open Bepes AI");
        logStep("Mở Trò chuyện với Bepes");
        try {
            click(btnBepesAI);
            WaitingHelper.sleepSeconds(2);
            logger.info("Opened Bepes AI");
        } catch (Exception e) {
            logger.error("Error opening Bepes AI", e);
            AllureHelper.attachErrorMessage("Open Bepes AI error: " + e.getMessage());
            throw e;
        }
    }

    /**
     * ✅ MỞ GỢI Ý TỪ TỦ LẠNH
     */
    @Step("Open Fridge Suggestion")
    public void openGoiYTuTuLanh() {
        AllureHelper.step("Open Fridge Suggestion");
        logStep("Mở Gợi ý từ tủ lạnh");
        try {
            click(btnGoiYTuTuLanh);
            WaitingHelper.sleepSeconds(2);
            logger.info("Opened Fridge Suggestion");
        } catch (Exception e) {
            logger.error("Error opening Fridge Suggestion", e);
            AllureHelper.attachErrorMessage("Open Fridge Suggestion error: " + e.getMessage());
            throw e;
        }
    }

    /**
     * ✅ SCROLL XUỐNG LOAD THÊM CÔNG THỨC
     */
    @Step("Scroll down to load more recipes")
    public void scrollDownToLoadMore() {
        AllureHelper.step("Scroll down to load more recipes");
        logStep("Scroll xuống tải thêm công thức");
        try {
            WebElement scrollView = getDriver().findElement(recipeScrollView);
            getDriver().executeScript("mobile: scrollGesture",
                    java.util.Map.of(
                            "elementId", ((org.openqa.selenium.remote.RemoteWebElement) scrollView).getId(),
                            "direction", "down",
                            "percent", 0.75
                    )
            );
            WaitingHelper.sleepSeconds(1);
            logger.info("Scrolled down");
        } catch (Exception e) {
            logger.error("Error scrolling down", e);
            AllureHelper.attachErrorMessage("Scroll error: " + e.getMessage());
            throw new RuntimeException("Cannot scroll recipes", e);
        }
    }

    /**
     * ✅ LẤY VỊ TRÍ SCROLL HIỆN TẠI
     */
    @Step("Get scroll position")
    public int getScrollPosition() {
        AllureHelper.step("Get scroll position");
        logStep("Lấy vị trí scroll hiện tại");
        try {
            WebElement scrollView = getDriver().findElement(recipeScrollView);
            String scrollY = scrollView.getAttribute("scrollY");
            int position = Integer.parseInt(scrollY != null ? scrollY : "0");
            logger.info("Scroll position: " + position);
            return position;
        } catch (Exception e) {
            logger.warn("Error getting scroll position", e);
            return 0;
        }
    }

    /**
     * ✅ KIỂM TRA BEPES SECTION HIỂN THỊ
     */
    @Step("Verify Bepes section displayed")
    public boolean isBepesDisplayed() {
        AllureHelper.step("Verify Bepes section displayed");
        logStep("Kiểm tra section Bepes hiển thị");
        try {
            boolean result = isDisplayed(bepesTitle) && isDisplayed(btnBepesAI);
            if (result) {
                AllureHelper.attachScreenshot("✅ Bepes section displayed");
            }
            return result;
        } catch (Exception e) {
            logger.error("Bepes section not displayed", e);
            return false;
        }
    }

    /**
     * ✅ CLICK NÚT BEPES AI
     */
    @Step("Click Bepes AI button")
    public void clickBepesAIButton() {
        AllureHelper.step("Click Bepes AI button");
        logStep("Click nút 'Trò chuyện với Bepes'");
        try {
            click(btnBepesAI);
            WaitingHelper.sleepSeconds(2);
            logger.info("Clicked Bepes AI button");
        } catch (Exception e) {
            logger.error("Error clicking Bepes button", e);
            throw e;
        }
    }

    /**
     * ✅ CLICK NÚT GỢI Ý TỪ TỦ LẠNH
     */
    @Step("Click fridge suggestion button")
    public void clickFridgeSuggestionButton() {
        AllureHelper.step("Click fridge suggestion button");
        logStep("Click nút 'Gợi ý từ tủ lạnh'");
        try {
            click(btnGoiYTuTuLanh);
            WaitingHelper.sleepSeconds(2);
            logger.info("Clicked fridge suggestion button");
        } catch (Exception e) {
            logger.error("Error clicking fridge suggestion button", e);
            throw e;
        }
    }

    /**
     * ✅ CLICK SEARCH BUTTON
     */
    @Step("Click search button")
    public void clickSearch() {
        AllureHelper.step("Click search button");
        logStep("Click icon tìm kiếm");
        try {
            click(btnSearch);
            WaitingHelper.sleepSeconds(1);
            logger.info("Clicked search button");
        } catch (Exception e) {
            logger.error("Error clicking search button", e);
            throw e;
        }
    }

    /**
     * ✅ CLICK BOTTOM NAV - FRIDGE
     */
    @Step("Click bottom nav - Fridge")
    public void clickBottomNavFridge() {
        AllureHelper.step("Click bottom nav - Fridge");
        logStep("Click tab Tủ lạnh");
        try {
            click(bottomNavFridge);
            WaitingHelper.sleepSeconds(1);
            logger.info("Clicked bottom nav fridge");
        } catch (Exception e) {
            logger.error("Error clicking fridge nav", e);
            throw e;
        }
    }

    /**
     * ✅ CLICK BOTTOM NAV - PROFILE
     */
    @Step("Click bottom nav - Profile")
    public void clickBottomNavProfile() {
        AllureHelper.step("Click bottom nav - Profile");
        logStep("Click tab Tài khoản");
        try {
            click(bottomNavProfile);
            WaitingHelper.sleepSeconds(1);
            logger.info("Clicked bottom nav profile");
        } catch (Exception e) {
            logger.error("Error clicking profile nav", e);
            throw e;
        }
    }

    /**
     * ✅ CHỜ DANH SÁCH CÔNG THỨC LOAD
     */
    @Step("Wait for recipe list to load")
    public void waitForRecipeListLoad() {
        AllureHelper.step("Wait for recipe list to load");
        logStep("Chờ danh sách công thức load");
        try {
            WaitingHelper.waitForVisible(lblTopTrendingRecipes);
            WaitingHelper.sleepSeconds(1);
            logger.info("Recipe list loaded");
        } catch (Exception e) {
            logger.error("Recipe list failed to load", e);
            AllureHelper.attachErrorMessage("Recipe list load error: " + e.getMessage());
            throw new RuntimeException("Recipe list failed to load", e);
        }
    }
    public void backToHome2() { click(btnBack);}
    public void backToHome() {

        logStep("Back về Home thật");

        int max = 5;

        while (max-- > 0) {

            if (isHomeDisplayed()) {
                logStep("✅ Đã về Home");
                return;
            }

            // ✅ Ưu tiên click nút back UI
            if (getDriver().findElements(btnBack).size() > 0) {
                click(btnBack);
            } else {
//                 fallback nếu không có nút
                getDriver().navigate().back();
            }

            WaitingHelper.sleepSeconds(1);
        }

        throw new RuntimeException("❌ Không back được về Home");
    }
    public void clickTabHome(){click( bottomNavHome);}

}