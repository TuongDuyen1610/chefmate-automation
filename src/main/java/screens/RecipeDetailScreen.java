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
 * RecipeDetailScreen.java
 * ✅ Sử dụng getDriver() từ BaseScreen
 */
public class RecipeDetailScreen extends BaseScreen {
    private static final Logger logger = LoggerFactory.getLogger(RecipeDetailScreen.class);

    // ==================== LOCATORS ====================
    private final By btnBack = By.xpath("//android.view.View[@content-desc='Quay lại']");
    private final By btnMark = By.xpath("//android.view.View[@content-desc='Mark']");
    private final By btnShare = By.xpath("//android.view.View[@content-desc='Share']");

    private final By recipeTitle = By.xpath("//android.widget.TextView[1]");
    private final By recipeAuthor = By.xpath("//android.widget.TextView[@text='Hồ Anh Khoa']");
    private final By recipeTimeAgo = By.xpath("//android.widget.TextView[@text='2 tuần trước']");
    private final By btnFollow = By.xpath("//android.widget.TextView[@text='Theo dõi']");

    private final By likeCount = By.xpath("(//android.widget.TextView)[position()=last()-3]");
    private final By commentCount = By.xpath("(//android.widget.TextView)[position()=last()-2]");
    private final By saveCount = By.xpath("(//android.widget.TextView)[position()=last()-1]");
    private final By cookTime = By.xpath("//android.widget.TextView[contains(@text, 'phút')]");

    private final By tagsLabel = By.xpath("//android.widget.TextView[@text='Tags: ']");
    private final By tagsContainer = By.xpath("//android.widget.HorizontalScrollView");

    private final By tabIngredients = By.xpath("(//android.widget.TextView[@text='Nguyên liệu'])[1]");
    private final By tabInstructions = By.xpath("//android.widget.TextView[@text='Cách thực hiện']");

    private final By ingredientsSection = By.xpath("(//android.widget.TextView[@text='Nguyên liệu'])[2]");
    private final By servingSize = By.xpath("//android.widget.TextView[contains(@text, 'người ăn')]");
    private final By ingredientItems = By.xpath(
            "//android.widget.TextView[contains(@text, '-') and " +
                    "not(contains(@text, 'Nguyên')) and " +
                    "not(contains(@text, 'Cách'))]"
    );

    private final By instructionsSection = By.xpath("//android.widget.TextView[@text='Cách thực hiện']");
    private final By stepItems = By.xpath("//android.widget.TextView[starts-with(@text, 'Bước')]");

    private final By commentsSection = By.xpath("//android.widget.TextView[@text='Bình luận']");
    private final By loginMessage = By.xpath("//android.widget.TextView[@text='Vui lòng đăng nhập để bình luận công thức này.']");

    /**
     * ✅ KIỂM TRA DETAIL SCREEN HIỂN THỊ
     */
    @Step("Verify recipe detail screen displayed")
    public boolean isRecipeDetailDisplayed() {
        AllureHelper.step("Verify recipe detail screen displayed");
        logStep("Kiểm tra detail screen hiển thị");
        try {
            boolean result = isDisplayed(btnBack) &&
                    isDisplayed(tabIngredients) &&
                    isDisplayed(tabInstructions);
            if (result) {
                AllureHelper.attachScreenshot("✅ Recipe detail screen displayed");
            }
            return result;
        } catch (Exception e) {
            logger.error("Recipe detail not displayed", e);
            AllureHelper.attachErrorMessage("Recipe detail error: " + e.getMessage());
            return false;
        }
    }

    /**
     * ✅ LẤY TÊN CÔNG THỨC
     */
    @Step("Get recipe title")
    public String getRecipeTitle() {
        AllureHelper.step("Get recipe title");
        logStep("Lấy tên công thức");
        try {
            String title = getDriver().findElement(recipeTitle).getText();
            logger.info("Recipe title: " + title);
            AllureHelper.stepWithParam("Recipe title", title);
            return title;
        } catch (Exception e) {
            logger.error("Error getting recipe title", e);
            AllureHelper.attachErrorMessage("Get title error: " + e.getMessage());
            return null;
        }
    }

    /**
     * ✅ LẤY TÁC GIẢ CÔNG THỨC
     */
    @Step("Get recipe author")
    public String getRecipeAuthor() {
        AllureHelper.step("Get recipe author");
        logStep("Lấy tác giả");
        try {
            String author = getDriver().findElement(recipeAuthor).getText();
            logger.info("Recipe author: " + author);
            AllureHelper.stepWithParam("Recipe author", author);
            return author;
        } catch (Exception e) {
            logger.error("Error getting author", e);
            return null;
        }
    }

    /**
     * ✅ LẤY THỜI GIAN NẤU
     */
    @Step("Get cook time")
    public String getCookTime() {
        AllureHelper.step("Get cook time");
        logStep("Lấy thời gian nấu");
        try {
            String time = getDriver().findElement(cookTime).getText();
            logger.info("Cook time: " + time);
            AllureHelper.stepWithParam("Cook time", time);
            return time;
        } catch (Exception e) {
            logger.error("Error getting cook time", e);
            return null;
        }
    }

    /**
     * ✅ LẤY SỐ LƯỢNG NGUYÊN LIỆU
     */
    @Step("Get ingredient count")
    public int getIngredientCount() {
        AllureHelper.step("Get ingredient count");
        logStep("Lấy số lượng nguyên liệu");
        try {
            List<WebElement> ingredients = getDriver().findElements(ingredientItems);
            int count = ingredients.size();
            logger.info("Ingredient count: " + count);
            AllureHelper.stepWithParam("Ingredient count", String.valueOf(count));
            return count;
        } catch (Exception e) {
            logger.error("Error getting ingredient count", e);
            return 0;
        }
    }

    /**
     * ✅ LẤY NGUYÊN LIỆU TẠI VỊ TRÍ INDEX
     */
    @Step("Get ingredient at index {index}")
    public String getIngredientAt(int index) {
        AllureHelper.stepWithParam("Get ingredient at index", String.valueOf(index));
        logStep("Lấy nguyên liệu ở vị trí " + index);
        try {
            List<WebElement> ingredients = getDriver().findElements(ingredientItems);
            if (index < ingredients.size()) {
                String ingredient = ingredients.get(index).getText();
                logger.info("Ingredient at " + index + ": " + ingredient);
                return ingredient;
            }
        } catch (Exception e) {
            logger.error("Error getting ingredient at index " + index, e);
        }
        return null;
    }

    /**
     * ✅ LẤY SỐ LƯỢNG BƯỚC NẤU
     */
    @Step("Get instruction step count")
    public int getInstructionCount() {
        AllureHelper.step("Get instruction step count");
        logStep("Lấy số lượng bước nấu");
        try {
            List<WebElement> steps = getDriver().findElements(stepItems);
            int count = steps.size();
            logger.info("Instruction count: " + count);
            AllureHelper.stepWithParam("Instruction count", String.valueOf(count));
            return count;
        } catch (Exception e) {
            logger.error("Error getting instruction count", e);
            return 0;
        }
    }

    /**
     * ✅ LẤY NỘI DUNG BƯỚC NẤU TẠI INDEX
     */
    @Step("Get instruction at index {index}")
    public String getInstructionAt(int index) {
        AllureHelper.stepWithParam("Get instruction at index", String.valueOf(index));
        logStep("Lấy bước nấu ở vị trí " + index);
        try {
            List<WebElement> steps = getDriver().findElements(stepItems);
            if (index < steps.size()) {
                String step = steps.get(index).getText();
                logger.info("Step at " + index + ": " + step);
                return step;
            }
        } catch (Exception e) {
            logger.error("Error getting instruction at index " + index, e);
        }
        return null;
    }

    /**
     * ✅ KIỂM TRA TABS HIỂN THỊ
     */
    @Step("Verify tabs displayed")
    public boolean isTabsDisplayed() {
        AllureHelper.step("Verify tabs displayed");
        logStep("Kiểm tra tabs hiển thị");
        try {
            return isDisplayed(tabIngredients) && isDisplayed(tabInstructions);
        } catch (Exception e) {
            logger.error("Tabs not displayed", e);
            return false;
        }
    }

    /**
     * ✅ CLICK TAB NGUYÊN LIỆU
     */
    @Step("Click ingredient tab")
    public void clickIngredientTab() {
        AllureHelper.step("Click ingredient tab");
        logStep("Click tab Nguyên liệu");
        try {
            click(tabIngredients);
            WaitingHelper.sleepSeconds(1);
            logger.info("Clicked ingredient tab");
        } catch (Exception e) {
            logger.error("Error clicking ingredient tab", e);
            throw e;
        }
    }

    /**
     * ✅ CLICK TAB CÁCH THỰC HIỆN
     */
    @Step("Click instruction tab")
    public void clickInstructionTab() {
        AllureHelper.step("Click instruction tab");
        logStep("Click tab Cách thực hiện");
        try {
            click(tabInstructions);
            WaitingHelper.sleepSeconds(1);
            logger.info("Clicked instruction tab");
        } catch (Exception e) {
            logger.error("Error clicking instruction tab", e);
            throw e;
        }
    }

    /**
     * ✅ CLICK NÚT QUAY LẠI
     */
    @Step("Click back button")
    public void clickBackButton() {
        AllureHelper.step("Click back button");
        logStep("Click nút Quay lại");
        try {
            click(btnBack);
            WaitingHelper.sleepSeconds(1);
            logger.info("Clicked back button");
        } catch (Exception e) {
            logger.error("Error clicking back button", e);
            throw e;
        }
    }

    /**
     * ✅ KIỂM TRA TAGS HIỂN THỊ
     */
    @Step("Verify tags displayed")
    public boolean isTagsDisplayed() {
        AllureHelper.step("Verify tags displayed");
        logStep("Kiểm tra tags hiển thị");
        try {
            return isDisplayed(tagsLabel) && isDisplayed(tagsContainer);
        } catch (Exception e) {
            logger.error("Tags not displayed", e);
            return false;
        }
    }

    /**
     * ✅ LẤY DANH SÁCH TAGS
     */
    @Step("Get tags list")
    public List<String> getTags() {
        AllureHelper.step("Get tags list");
        logStep("Lấy danh sách tags");
        try {
            List<WebElement> tagElements = getDriver().findElements(
                    By.xpath("//android.widget.HorizontalScrollView//android.widget.TextView")
            );
            List<String> tags = tagElements.stream()
                    .map(WebElement::getText)
                    .filter(text -> !text.isEmpty() && !text.equals("Tags: "))
                    .toList();
            AllureHelper.stepWithParam("Tags count", String.valueOf(tags.size()));
            return tags;
        } catch (Exception e) {
            logger.error("Error getting tags", e);
            return java.util.Collections.emptyList();
        }
    }
    /**
     * ✅ THÊM METHOD NÀY - CHỜ CHI TIẾT CÔNG THỨC LOAD
     */
    public void waitForRecipeDetailDisplayed() {
        logStep("Chờ màn chi tiết công thức hiển thị");
        try {
            WaitingHelper.waitForVisible(btnBack);
            WaitingHelper.sleepSeconds(1);
            logger.info("✅ Recipe detail screen loaded");
        } catch (Exception e) {
            logger.error("❌ Recipe detail failed to load: " + e.getMessage());
            AllureHelper.attachErrorMessage("Recipe detail load error: " + e.getMessage());
            throw new RuntimeException("Recipe detail failed to load", e);
        }
    }
}