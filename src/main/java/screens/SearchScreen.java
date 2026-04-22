package screens;

import core.base.BaseScreen;
import core.utils.AllureHelper;
import core.utils.WaitingHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * SearchScreen.java
 * - Dùng chung cho luồng search (Home -> SearchScreen).
 * - submit duy nhất bằng IME action search.
 */
public class SearchScreen extends BaseScreen {
    private static final Logger logger = LoggerFactory.getLogger(SearchScreen.class);

    // ==================== LOCATORS (theo mô tả chị) ====================
    // Dùng chung cho Home và SearchScreen
    private final By searchField = By.xpath("//android.widget.EditText");

    // SearchScreen có nút Quay lại
    private final By btnBack = By.xpath("//android.view.View[@content-desc='Quay lại']");

    // Header + tabs
    private final By headerSearchBy = By.xpath("//android.widget.TextView[@text='Tìm kiếm theo: ']");
    private final By tabRecipeName = By.xpath("//android.widget.TextView[@text='Tên món']");
    private final By tabTag = By.xpath("//android.widget.TextView[@text='Tag']");

    // Result list
    private final By resultScrollView = By.xpath("//android.widget.ScrollView");
    private final By resultItems = By.xpath(
            "//android.widget.ScrollView//android.view.View[@content-desc and " +
                    "not(contains(@content-desc,'Like')) and " +
                    "not(contains(@content-desc,'Share'))]"
    );

    // Empty state message
    private final By txtNoResult = By.xpath(
            "//android.widget.TextView[contains(@text,'Không có kết quả nào')]"
    );

    // ==================== WAITS ====================

    @Step("Wait Search UI ready")
    public void waitForSearchUiReady() {
        AllureHelper.step("Wait Search UI ready");
        logStep("Chờ UI search sẵn sàng");
        WaitingHelper.waitForVisible(searchField);
        WaitingHelper.sleepSeconds(1);
    }

    @Step("Wait SearchScreen displayed (has Back)")
    public void waitForSearchScreenDisplayed() {
        AllureHelper.step("Wait SearchScreen displayed (has Back)");
        logStep("Chờ SearchScreen hiển thị (có nút Quay lại)");
        WaitingHelper.waitForVisible(btnBack);
        WaitingHelper.sleepSeconds(1);
    }

    // ==================== ACTIONS ====================

    @Step("Enter keyword: {keyword}")
    public void enterKeyword(String keyword) {
        AllureHelper.stepWithParam("Enter keyword", keyword);
        logStep("Nhập keyword: " + keyword);
        WaitingHelper.waitForVisible(searchField);
        type(searchField, keyword);
    }

    @Step("Clear search field")
    public void clearKeyword() {
        AllureHelper.step("Clear search field");
        logStep("Xoá toàn bộ nội dung ô search");
        try {
            WebElement el = getDriver().findElement(searchField);
            el.clear();
        } catch (Exception e) {
            logger.error("Clear keyword failed", e);
            throw e;
        }
    }

    @Step("Select tab 'Tên món'")
    public void selectTabRecipeName() {
        AllureHelper.step("Select tab 'Tên món'");
        logStep("Chọn tab 'Tên món'");
        WaitingHelper.waitForVisible(tabRecipeName);
        click(tabRecipeName);
    }

    @Step("Select tab 'Tag'")
    public void selectTabTag() {
        AllureHelper.step("Select tab 'Tag'");
        logStep("Chọn tab 'Tag'");
        WaitingHelper.waitForVisible(tabTag);
        click(tabTag);
    }

    /**
     * ✅ SUBMIT DUY NHẤT - IME SEARCH (nhất quán, nhanh)
     */
    @Step("Submit search by keyboard (IME search)")
    public void submitSearchByKeyboard() {
        AllureHelper.step("Submit search by keyboard (IME search)");
        logStep("Submit search bằng IME action search");

        try {
            // focus field trước khi submit để IME action ăn chắc
            click(searchField);

            getDriver().executeScript("mobile: performEditorAction", Map.of("action", "search"));

            logger.info("✅ Submitted search via performEditorAction(search)");
        } catch (Exception e) {
            logger.error("❌ Cannot submit search via IME search", e);
            AllureHelper.attachErrorMessage("Submit search error: " + e.getMessage());
            throw new RuntimeException("Cannot submit search via IME search", e);
        }
    }

    @Step("Wait for result list OR no-result message (fast & robust)")
    public void waitForResultOrEmpty() {
        AllureHelper.step("Wait result stable (improved)");

        for (int i = 0; i < 8; i++) {   // giảm từ 20 → 8 (max 8s)
            try {
                int current = getDriver().findElements(resultItems).size();

                // ✅ có data là pass luôn (KHÔNG cần stable)
                if (current > 0) {
                    logger.info("✅ Result loaded: " + current);
                    return;
                }

                // ✅ có empty state là pass luôn
                if (isDisplayed(txtNoResult)) {
                    logger.info("✅ Empty state displayed");
                    return;
                }

            } catch (Exception ignored) {}

            WaitingHelper.sleepSeconds(1);
        }

        AllureHelper.attachScreenshot("Timeout waiting result stable");
    }

    // ==================== GETTERS ====================

    @Step("Get result count")
    public int getResultCount() {
        WaitingHelper.sleepSeconds(0); // đợi UI settle nhẹ
        return getDriver().findElements(resultItems).size();
    }

    @Step("Get result title at index {index}")
    public String getResultTitleAt(int index) {
        AllureHelper.stepWithParam("Get result title at index", String.valueOf(index));
        List<WebElement> items = getDriver().findElements(resultItems);
        if (index < items.size()) {
            return items.get(index).getAttribute("content-desc");
        }
        return null;
    }

    @Step("Click result item at index {index}")
    public void clickResultAt(int index) {
        AllureHelper.stepWithParam("Click result item at index", String.valueOf(index));
        List<WebElement> items = getDriver().findElements(resultItems);
        if (index >= items.size()) throw new RuntimeException("Index out of range: " + index);
        items.get(index).click();
//        WaitingHelper.sleepSeconds(2);
        AllureHelper.attachScreenshot("Clicked result index " + index);
    }

    @Step("Is no-result message displayed")
    public boolean isNoResultDisplayed() {
        return !getDriver().findElements(txtNoResult).isEmpty();
    }

    @Step("Get no-result message text")
    public String getNoResultText() {
        AllureHelper.step("Get no-result message text");
        try {
            if (isDisplayed(txtNoResult)) return getDriver().findElement(txtNoResult).getText();
        } catch (Exception ignored) {}
        return null;
    }

    @Step("Back from SearchScreen")
    public void clickBack() {
        AllureHelper.step("Back from SearchScreen");
        logStep("Bấm Quay lại");
        click(btnBack);
    }
    @Step("Focus search field")
    public void focusSearchField() {
        AllureHelper.step("Focus search field");
        logStep("Click vào ô search");
        click(searchField);
    }
    @Step("Slow swipe down on screen (times={times})")
    public void slowSwipeDownOnScreen(int times) {
        AllureHelper.stepWithParam("Slow swipe down on screen", String.valueOf(times));
        logStep("Chạm giữ nhẹ và lướt xuống từ từ để biểu diễn danh sách. times=" + times);

        Dimension size = getDriver().manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * 0.75);
        int endY = (int) (size.height * 0.25);

        for (int i = 1; i <= times; i++) {
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 1);

            swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, startY));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            // “từ từ”: 900–1200ms là ổn
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(1100), PointerInput.Origin.viewport(), x, endY));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            getDriver().perform(Collections.singletonList(swipe));


            // nếu chị muốn nhiều ảnh để demo rõ: bật dòng này
            AllureHelper.attachScreenshot("Swipe demo step " + i);
        }
    }
    public boolean isSearchScreenStillVisible() {
        try {
            return isDisplayed(searchField);
        } catch (Exception e) {
            return false;
        }
    }
}