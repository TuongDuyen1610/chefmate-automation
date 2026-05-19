//package screens;
//
//import core.base.BaseScreen;
//import core.utils.AllureHelper;
//import core.utils.WaitingHelper;
//import io.qameta.allure.Step;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//
//import java.util.List;
//
//public class ShoppingHistoryScreen extends BaseScreen {
//
//    // ===== Header / Back =====
//    private final By header = By.xpath("//android.widget.TextView[@text='Lịch sử mua sắm']");
//    private final By btnBack = By.xpath("//android.view.View[@content-desc='Quay lại']");
//
//    // ===== Entry from Profile tab =====
//    private final By entryHistory = By.xpath("//android.widget.TextView[@text='Lịch sử mua sắm']");
//
//    // ===== History list =====
//    private final By iconHistory = By.xpath("//android.widget.ImageView[@content-desc='History']");
//    private final By labelPurchaseDate = By.xpath("//android.widget.TextView[@text='Ngày mua']");
//    private final By btnViewDetailText = By.xpath("//android.widget.TextView[@text='Xem chi tiết']");
//
//    // ===== History detail =====
//    private final By btnBackText = By.xpath("//android.widget.TextView[@text='Trở lại']");
//
//    private final By anyCheckbox = By.xpath("//android.widget.CheckBox");
//
//    private By recipeTabText(String recipeTitle) {
//        return By.xpath("//android.widget.HorizontalScrollView//android.widget.TextView[@text='" + recipeTitle + "']");
//    }
//
//    private By ingredientTextContainsIgnoreCase(String text) {
//        String lower = text.toLowerCase();
//        return By.xpath("//android.widget.TextView[contains(translate(@text," +
//                " 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '" + lower + "')]");
//    }
//
//    // ================= Open =================
//    @Step("Open Shopping History from Profile tab")
//    public void openFromProfileTab() {
//        click(entryHistory);
//        WaitingHelper.waitForVisible(header);
//        WaitingHelper.waitForVisible(iconHistory);
//        WaitingHelper.waitForVisible(labelPurchaseDate);
//        AllureHelper.attachScreenshot("Opened Shopping History list");
//    }
//
//    @Step("Verify Shopping History list displayed")
//    public void verifyHistoryListDisplayed() {
//        WaitingHelper.waitForVisible(header);
//        WaitingHelper.waitForVisible(btnViewDetailText);
//        AllureHelper.attachScreenshot("History list displayed");
//    }
//
//    // ================= Detail =================
//    @Step("Open history detail at index={index0Based}")
//    public void openDetailAt(int index0Based) {
//        List<WebElement> btns = getDriver().findElements(btnViewDetailText);
//        if (btns.isEmpty()) throw new RuntimeException("❌ Không tìm thấy nút 'Xem chi tiết'");
//        if (index0Based >= btns.size()) throw new RuntimeException("❌ Index xem chi tiết vượt quá size=" + btns.size());
//
//        btns.get(index0Based).click();
//        WaitingHelper.sleepSeconds(1);
//
//        WaitingHelper.waitForVisible(header);
//        AllureHelper.attachScreenshot("Opened history detail index=" + index0Based);
//    }
//
//    @Step("Verify history detail has ingredients")
//    public void verifyHasIngredientsInDetail() {
//        List<WebElement> cbs = getDriver().findElements(anyCheckbox);
//        if (cbs.isEmpty()) {
//            AllureHelper.attachScreenshot("No checkbox in history detail");
//            throw new AssertionError("❌ History detail không có nguyên liệu");
//        }
//    }
//
//    @Step("Verify ingredient exists in history detail (ignore case): {text}")
//    public void verifyIngredientInDetailIgnoreCase(String text) {
//        WaitingHelper.waitForVisible(ingredientTextContainsIgnoreCase(text));
//        AllureHelper.attachScreenshot("History detail contains: " + text);
//    }
//
//    @Step("Verify recipe tab exists in history detail: {recipeTitle}")
//    public void verifyRecipeTabExistsInDetail(String recipeTitle) {
//        WaitingHelper.waitForVisible(recipeTabText(recipeTitle));
//        AllureHelper.attachScreenshot("History detail tab exists: " + recipeTitle);
//    }
//
//    // ================= Back =================
//    @Step("Back from history detail by 'Trở lại'")
//    public void backFromDetailByText() {
//        click(btnBackText);
//        AllureHelper.attachScreenshot("Back from detail (Trở lại)");
//    }
//
//    @Step("Click back (Quay lại)")
//    public void clickBack() {
//        click(btnBack);
//        AllureHelper.attachScreenshot("Back (Quay lại)");
//    }
//    // ===== HISTORY LIST HELPERS =====
//
//    public int getHistoryItemCount() {
//        return getDriver().findElements(btnViewDetailText).size();
//    }
//
//    public String getPurchaseDateTextAt(int index0Based) {
//
//        List<WebElement> dates =
//                getDriver().findElements(labelPurchaseDate);
//
//        if (dates.isEmpty()) {
//            throw new RuntimeException("❌ Không có purchase date");
//        }
//
//        if (index0Based >= dates.size()) {
//            throw new RuntimeException("❌ Index vượt quá size");
//        }
//
//        return dates.get(index0Based).getText();
//    }
//
////    public void scrollHistoryList() {
////        slowSwipeDownOnScreen(1);
////    }
//
//    public boolean isHistoryEmpty() {
//
//        List<WebElement> items =
//                getDriver().findElements(btnViewDetailText);
//
//        return items.isEmpty();
//    }
//
//    private final By txtEmpty =
//            By.xpath("//android.widget.TextView[contains(@text,'Chưa có lịch sử')]");
//
//    public void verifyEmptyState() {
//
//        WaitingHelper.waitForVisible(txtEmpty);
//
//        AllureHelper.attachScreenshot(
//                "History empty state displayed"
//        );
//    }
//}
package screens;

import core.base.BaseScreen;
import core.utils.AllureHelper;
import core.utils.WaitingHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class ShoppingHistoryScreen extends BaseScreen {

    // ===== Header / Back =====
    private final By header = By.xpath("//android.widget.TextView[@text='Lịch sử mua sắm']");
    private final By btnBack = By.xpath("//android.view.View[@content-desc='Quay lại']");

    // ===== Entry from Profile tab =====
    private final By entryHistory = By.xpath("//android.widget.TextView[@text='Lịch sử mua sắm']");

    // ===== History list =====
    private final By iconHistory = By.xpath("//android.widget.ImageView[@content-desc='History']");
    private final By labelPurchaseDate = By.xpath("//android.widget.TextView[@text='Ngày mua']");
    private final By purchaseDateValue =
            By.xpath("//android.widget.TextView[@text='Ngày mua']/following-sibling::android.widget.TextView[1]");
    private final By btnViewDetailText = By.xpath("//android.widget.TextView[@text='Xem chi tiết']");

    // ===== History detail =====
    private final By btnBackText = By.xpath("//android.widget.TextView[@text='Trở lại']");
    private final By recipeTabsScroll = By.xpath("//android.widget.HorizontalScrollView");
    private final By anyCheckbox = By.xpath("//android.widget.CheckBox");

    private By recipeTabText(String recipeTitle) {
        return By.xpath("//android.widget.HorizontalScrollView//android.widget.TextView[@text='" + recipeTitle + "']");
    }

    private By ingredientTextContainsIgnoreCase(String text) {
        String lower = text.toLowerCase();
        return By.xpath("//android.widget.TextView[contains(translate(@text," +
                " 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '" + lower + "')]");
    }

    // ================= Open =================
    @Step("Open Shopping History from Profile tab")
    public void openFromProfileTab() {
        click(entryHistory);
        WaitingHelper.waitForVisible(header);
        AllureHelper.attachScreenshot("Opened Shopping History screen");
    }

    @Step("Verify Shopping History list displayed")
    public void verifyHistoryListDisplayed() {
        WaitingHelper.waitForVisible(header);
        WaitingHelper.waitForVisible(btnBack);
        AllureHelper.attachScreenshot("History list displayed (HAS ITEMS)");
    }

    // ================= List helpers =================
    public int getHistoryItemCount() {
        return getDriver().findElements(btnViewDetailText).size();
    }

    /** GIỮ NGUYÊN TÊN HÀM, nhưng trả về đúng value ngày mua */
    public String getPurchaseDateTextAt(int index0Based) {
        List<WebElement> dates = getDriver().findElements(purchaseDateValue);
        if (dates.isEmpty()) throw new RuntimeException("❌ Không có purchase date value");
        if (index0Based >= dates.size()) throw new RuntimeException("❌ Index vượt quá size=" + dates.size());
        return dates.get(index0Based).getText();
    }

    public boolean isHistoryEmpty() {
        return getHistoryItemCount() == 0;
    }

    public void verifyEmptyState() {
        WaitingHelper.waitForVisible(header);
        WaitingHelper.waitForVisible(btnBack);
        int count = getHistoryItemCount();
        if (count != 0) {
            AllureHelper.attachScreenshot("History is NOT empty");
            throw new AssertionError("❌ History không rỗng, count=" + count);
        }
        AllureHelper.attachScreenshot("History empty state displayed");
    }

    // ================= Detail =================
    @Step("Open history detail at index={index0Based}")
    public void openDetailAt(int index0Based) {
        List<WebElement> btns = getDriver().findElements(btnViewDetailText);
        if (btns.isEmpty()) throw new RuntimeException("❌ Không tìm thấy nút 'Xem chi tiết'");
        if (index0Based >= btns.size()) throw new RuntimeException("❌ Index xem chi tiết vượt quá size=" + btns.size());

        btns.get(index0Based).click();
        WaitingHelper.sleepSeconds(1);

        // ✅ confirm detail bằng "Trở lại"
        WaitingHelper.waitForVisible(btnBackText);
        AllureHelper.attachScreenshot("Opened history detail index=" + index0Based);
    }

    @Step("Verify history detail has ingredients")
    public void verifyHasIngredientsInDetail() {
        List<WebElement> cbs = getDriver().findElements(anyCheckbox);
        if (cbs.isEmpty()) {
            AllureHelper.attachScreenshot("No checkbox in history detail");
            throw new AssertionError("❌ History detail không có nguyên liệu");
        }
    }

    @Step("Verify ingredient exists in history detail (ignore case): {text}")
    public void verifyIngredientInDetailIgnoreCase(String text) {
        WaitingHelper.waitForVisible(ingredientTextContainsIgnoreCase(text));
        AllureHelper.attachScreenshot("History detail contains: " + text);
    }

    @Step("Verify recipe tab exists in history detail: {recipeTitle}")
    public void verifyRecipeTabExistsInDetail(String recipeTitle) {
        WaitingHelper.waitForVisible(recipeTabsScroll);
        WaitingHelper.waitForVisible(recipeTabText(recipeTitle));
        AllureHelper.attachScreenshot("History detail tab exists: " + recipeTitle);
    }

    // ================= Back =================
    @Step("Back from history detail by 'Trở lại'")
    public void backFromDetailByText() {
        click(btnBackText);
        WaitingHelper.sleepSeconds(1);
        WaitingHelper.waitForVisible(header);
        AllureHelper.attachScreenshot("Back from detail to history list");
    }

    @Step("Click back (Quay lại)")
    public void clickBack() {
        click(btnBack);
        AllureHelper.attachScreenshot("Back to Profile (Quay lại)");
    }
    @Step("Swipe down on screen (times={times})")
    public void slowSwipeDownOnScreen(int times) {
        Dimension size = getDriver().manage().window().getSize();

        int x = size.width / 2;
        int startY = (int) (size.height * 0.75);
        int endY = (int) (size.height * 0.30);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        for (int i = 1; i <= times; i++) {
            Sequence swipe = new Sequence(finger, 1);
            swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, startY));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), x, endY));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            getDriver().perform(Collections.singletonList(swipe));
            WaitingHelper.sleepSeconds(1);
        }

        // ❌ bỏ attachScreenshot ở đây
    }
}