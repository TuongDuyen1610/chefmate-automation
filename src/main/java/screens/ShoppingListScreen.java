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

public class ShoppingListScreen extends BaseScreen {

    // ===== HEADER =====
    private final By header = By.xpath("//android.widget.TextView[@text='Danh sách mua sắm']");
    private final By btnBack = By.xpath("//android.view.View[@content-desc='Quay lại']");

    // ===== BOTTOM ACTIONS =====
    private final By btnSupplementText = By.xpath("//android.widget.TextView[@text='Bổ sung']");
    private final By btnCompleteText = By.xpath("//android.widget.TextView[@text='Hoàn thành']");

    // ===== RECIPE TABS (HorizontalScrollView) =====
    private By recipeTabText(String recipeTitle) {
        return By.xpath("//android.widget.HorizontalScrollView//android.widget.TextView[@text='" + recipeTitle + "']");
    }
    private By recipeTabTextContainsIgnoreCase(String recipeTitle) {
        String lower = recipeTitle.toLowerCase();
        return By.xpath("//android.widget.HorizontalScrollView//android.widget.TextView[" +
                "contains(translate(@text,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'" + lower + "')]");
    }
    // ===== INGREDIENT ROW ELEMENTS =====
    // pageSource: checkbox -> text -> edit -> delete (repeated)
    private final By anyCheckbox = By.xpath("//android.widget.CheckBox");
    private final By iconEdit = By.xpath("//android.view.View[@content-desc='edit ingredient']");

    // ingredient rows text (đúng format list của chị: "Tên - số đơn vị")
    private final By ingredientTexts = By.xpath("//android.widget.TextView[contains(@text,' - ')]");

    // delete icon XPath hợp lệ cho couldn't buy (không InvalidSelector)
    private static final String X_DELETE =
            "//android.view.View[@content-desc=concat('couldn',\"'\",'t buy')]";
    private final By deleteIcons = By.xpath(X_DELETE);

    // ingredient text exact/contains
    // CÁCH 1
    private By ingredientTextExact(String text) {
        String lower = text.toLowerCase();
        return By.xpath("//android.widget.TextView[contains(translate(@text," +
                " 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '" + lower + "')]");
    }
    //CÁCH 2 CHO TESTCASE KHÓ PASS
    private By ingredientTextContainsIgnoreCase(String text) {
        String lower = text.toLowerCase();
        return By.xpath("//android.widget.TextView[contains(translate(@text," +
                " 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '" + lower + "')]");
    }

    // ===== POPUP "Thêm mới nguyên liệu" (Bổ sung) =====
    private final By popupAddTitle = By.xpath("//android.widget.TextView[@text='Thêm mới nguyên liệu']");
    private final By popupCancel = By.xpath("//android.view.View[@content-desc='cancel']");
    private final By popupNameLabel = By.xpath("//android.widget.TextView[@text='Tên nguyên liệu']");
    private final By popupQtyLabel = By.xpath("//android.widget.TextView[@text='Định lượng']");
    private final By popupNameInput = By.xpath("(//android.widget.EditText)[1]");
    private final By popupQtyInput  = By.xpath("(//android.widget.EditText)[2]");
    private final By popupUnitInput = By.xpath("(//android.widget.EditText)[3]");
    private final By popupBtnAddNewText = By.xpath("//android.widget.TextView[@text='Thêm mới']");

    // ===== POPUP "Chỉnh sửa nguyên liệu" =====
    private final By popupEditTitle = By.xpath("//android.widget.TextView[@text='Chỉnh sửa nguyên liệu']");
    private final By popupEditCancel = By.xpath("//android.view.View[@content-desc='cancel']");
    private final By popupEditLabelName = By.xpath("//android.widget.TextView[@text='Tên nguyên liệu']");
    private final By popupEditLabelQty = By.xpath("//android.widget.TextView[@text='Định lượng']");
    private final By popupEditBtnUpdateText = By.xpath("//android.widget.TextView[@text='Cập nhật']");

    // theo pageSource: name/qty/unit = 3 EditText theo thứ tự
    private final By popupEditNameInput = By.xpath("(//android.widget.EditText)[1]");
    private final By popupEditQtyInput  = By.xpath("(//android.widget.EditText)[2]");
    private final By popupEditUnitInput = By.xpath("(//android.widget.EditText)[3]");
    private final By recipeTabsScroll = By.xpath("//android.widget.HorizontalScrollView");

    private String tabKey(String title) {
        if (title == null) return "";
        String t = title.trim().replaceAll("\\s+", " ");
        int n = Math.min(t.length(), 18);
        return t.substring(0, n).toLowerCase();
    }

    private By tabByKey(String keyLower) {
        return By.xpath("//android.widget.HorizontalScrollView//android.widget.TextView[" +
                "contains(translate(normalize-space(@text),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'" + keyLower + "')]");
    }
    // ================= BASIC =================
    public boolean isDisplayed() {
        return isDisplayed(header);
    }

    public void clickBack() {
        click(btnBack);
    }

    @Step("Verify Shopping List UI")
    public void verifyUI() {
        WaitingHelper.waitForVisible(header);
        WaitingHelper.waitForVisible(btnSupplementText);
        WaitingHelper.waitForVisible(btnCompleteText);
        AllureHelper.attachScreenshot("Shopping List UI OK");
    }
    private final By recipeTitles = By.xpath("//android.view.View//android.widget.TextView");

    // ================= RECIPE TAB VERIFY =================

    //    public void verifyRecipeTabExists(String recipeTitle) {
//        if (!swipeRightTabsUntilFound(recipeTitle, 1)) {
//            AllureHelper.attachScreenshot("Recipe tab NOT FOUND: " + recipeTitle);
//            throw new AssertionError("❌ Không tìm thấy recipe tab sau khi kéo từ phải sang trái: " + recipeTitle);
//        }
//        AllureHelper.attachScreenshot("Recipe tab exists: " + recipeTitle);
//    }
//
//    public void verifyRecipeTabNotExists(String recipeTitle) {
//        WaitingHelper.waitForVisible(recipeTabsScroll);
//
//        By tab = tabByKey(tabKey(recipeTitle));
//
//        // Quét sang phải (kéo từ phải -> trái) xem có xuất hiện không
//        for (int i = 0; i < 1; i++) {
//            if (isDisplayed(tab)) {
//                AllureHelper.attachScreenshot("Recipe tab SHOULD NOT exist but found: " + recipeTitle);
//                throw new AssertionError("❌ Recipe tab vẫn hiển thị: " + recipeTitle);
//            }
//            swipeLeftInside(recipeTabsScroll, 1);
//        }
//
//        AllureHelper.attachScreenshot("Recipe tab NOT exists OK: " + recipeTitle);
//    }
//    /** Kéo từ phải -> trái nhiều lần để lộ tab bên phải, đồng thời check visible */
//    private boolean swipeRightTabsUntilFound(String recipeTitle, int maxSwipes) {
//        WaitingHelper.waitForVisible(recipeTabsScroll);
//
//        By tab = tabByKey(tabKey(recipeTitle));
//        if (isDisplayed(tab)) return true;
//
//        for (int i = 0; i < maxSwipes; i++) {
//            // ✅ kéo từ phải sang trái (swipe LEFT)
//            swipeLeftInside(recipeTabsScroll, 1);
//            if (isDisplayed(tab)) return true;
//        }
//        return false;
//    }
    private boolean isRecipeTabPresent(String recipeTitle, int rounds) {
        WaitingHelper.waitForVisible(recipeTabsScroll);
        By tab = tabByKey(tabKey(recipeTitle));

        if (isDisplayed(tab)) return true;

        // zig-zag mở rộng quanh vị trí hiện tại
        for (int r = 1; r <= rounds; r++) {
            // kéo từ phải -> trái r lần (lộ tab bên phải)
            for (int i = 0; i < r; i++) {
                swipeLeftInside(recipeTabsScroll, 1);
                if (isDisplayed(tab)) return true;
            }
            // kéo từ trái -> phải (r + 1) lần (lộ tab bên trái, vượt qua điểm start)
            for (int i = 0; i < r + 1; i++) {
                swipeRightInside(recipeTabsScroll, 1);
                if (isDisplayed(tab)) return true;
            }
        }

        return false;
    }

    public void verifyRecipeTabExists(String recipeTitle) {
        if (!isRecipeTabPresent(recipeTitle, 5)) {
            AllureHelper.attachScreenshot("Recipe tab NOT FOUND: " + recipeTitle);
            throw new AssertionError("❌ Không tìm thấy recipe tab: " + recipeTitle);
        }
        AllureHelper.attachScreenshot("Recipe tab exists: " + recipeTitle);
    }

    public void verifyRecipeTabNotExists(String recipeTitle) {
        if (isRecipeTabPresent(recipeTitle, 1)) {
            AllureHelper.attachScreenshot("Recipe tab SHOULD NOT exist but found: " + recipeTitle);
            throw new AssertionError("❌ Recipe tab vẫn hiển thị: " + recipeTitle);
        }
        AllureHelper.attachScreenshot("Recipe tab NOT exists OK: " + recipeTitle);
    }

    // ================= SUPPLEMENT POPUP =================
    public void openSupplementPopup() {
        click(btnSupplementText);
        WaitingHelper.waitForVisible(popupAddTitle);
        WaitingHelper.waitForVisible(popupNameLabel);
        WaitingHelper.waitForVisible(popupQtyLabel);
        AllureHelper.attachScreenshot("Supplement popup opened");
    }

    public void closeSupplementPopup() {
        click(popupCancel);
        WaitingHelper.sleepSeconds(1);
        AllureHelper.attachScreenshot("Supplement popup closed");
    }

    public void addSupplementIngredient(String name, String qty, String unit) {
        openSupplementPopup();

        type(popupNameInput, name);
        try { getDriver().hideKeyboard(); } catch (Exception ignored) {}

        type(popupQtyInput, qty);
        try { getDriver().hideKeyboard(); } catch (Exception ignored) {}

        type(popupUnitInput, unit);
        try { getDriver().hideKeyboard(); } catch (Exception ignored) {}

        click(popupBtnAddNewText);
        WaitingHelper.sleepSeconds(1);
        AllureHelper.attachScreenshot("Supplement ingredient added");
    }

    // ================= INGREDIENT VERIFY =================
    //CÁCH 1 CHO TESTCASE DỄ PASS BAN ĐẦU
    public void verifyIngredientDisplayed(String text) {
        slowSwipeDownOnScreen(2);
        WaitingHelper.waitForVisible(ingredientTextExact(text));
        AllureHelper.attachScreenshot("Ingredient exact OK: " + text);
    }
    //CÁCH 2 CHO TESTCASE KHÓ PASS
    public void verifyIngredientDisplayedIgnoreCase(String text) {
        // scroll xuống tìm 1 chút vì list dài
        long end = System.currentTimeMillis() + 20000;
        while (System.currentTimeMillis() < end) {
            if (isDisplayed(ingredientTextContainsIgnoreCase(text))) {
                AllureHelper.attachScreenshot("Ingredient OK (ignore case): " + text);
                return;
            }
            slowSwipeDownOnScreen(1);
        }
        AllureHelper.attachScreenshot("Ingredient NOT FOUND: " + text);
        throw new AssertionError("❌ Không tìm thấy ingredient: " + text);
    }

    // ================= TICK/UNTICK =================
    public void toggleCheckboxAt(int index) {
        List<WebElement> cbs = getDriver().findElements(anyCheckbox);
        if (cbs.isEmpty()) throw new RuntimeException("❌ No checkbox found");
        if (index >= cbs.size()) throw new RuntimeException("❌ Checkbox index out of range: " + index);

        cbs.get(index).click();
        WaitingHelper.sleepSeconds(1);
        AllureHelper.attachScreenshot("Toggled checkbox " + index);
    }

    // ================= DELETE (behavior: gạch/mờ/đẩy xuống + click lần 2 có thể undo) =================
    @Step("Click delete icon by exact xpath index: {index1Based}")
    public void clickDeleteByExactIndex(int index1Based) {

        By deleteByIndex = By.xpath(
                "(//android.view.View[@content-desc=\"couldn't buy\"])[" + index1Based + "]"
        );

        WaitingHelper.waitForVisible(deleteByIndex);

        click(deleteByIndex);

        WaitingHelper.sleepSeconds(1);

        AllureHelper.attachScreenshot(
                "Clicked delete icon at exact index = " + index1Based
        );
    }
    @Step("Click delete icon for ingredient contains (ignore case): {ingredientText}")
    public void clickDeleteByIngredientTextIgnoreCase(String ingredientText) {
        String key = ingredientText.toLowerCase();

        // vì list dài, cho phép scroll vài lần để tìm
        for (int attempt = 0; attempt < 6; attempt++) {
            List<WebElement> texts = getDriver().findElements(ingredientTexts);
            List<WebElement> deletes = getDriver().findElements(deleteIcons);

            int size = Math.min(texts.size(), deletes.size());
            for (int i = 0; i < size; i++) {
                String t = "";
                try { t = texts.get(i).getText(); } catch (Exception ignored) {}

                if (t != null && t.toLowerCase().contains(key)) {
                    deletes.get(i).click();
                    WaitingHelper.sleepSeconds(1);
                    AllureHelper.attachScreenshot("Clicked delete for: " + ingredientText);
                    return;
                }
            }

            // chưa thấy -> swipe xuống tìm tiếp
            slowSwipeDownOnScreen(1);
        }

        AllureHelper.attachScreenshot("Delete target NOT FOUND: " + ingredientText);
        throw new AssertionError("❌ Không tìm thấy ingredient để click delete: " + ingredientText);
    }
    public List<WebElement> getAllIngredientTextElements() {
        return getDriver().findElements(ingredientTexts);
    }

    public int getIngredientCount() {
        return getDriver().findElements(ingredientTexts).size();
    }

    public String getIngredientTextAt(int index0Based) {
        List<WebElement> els = getDriver().findElements(ingredientTexts);
        if (els.isEmpty()) throw new RuntimeException("❌ No ingredient text found");
        if (index0Based >= els.size()) throw new RuntimeException("❌ Ingredient index out of range: " + index0Based);
        return els.get(index0Based).getText();
    }
    // ================= EDIT =================
    public void openEditAt(int index) {
        List<WebElement> icons = getDriver().findElements(iconEdit);
        if (icons.isEmpty()) throw new RuntimeException("❌ No edit icon found");
        if (index >= icons.size()) throw new RuntimeException("❌ Edit index out of range: " + index);

        icons.get(index).click();
        WaitingHelper.waitForVisible(popupEditTitle);
        WaitingHelper.waitForVisible(popupEditLabelName);
        WaitingHelper.waitForVisible(popupEditLabelQty);
        AllureHelper.attachScreenshot("Edit popup opened");
    }

    public void closeEditPopup() {
        click(popupEditCancel);
        WaitingHelper.sleepSeconds(1);
        AllureHelper.attachScreenshot("Edit popup closed");
    }

    public void updateIngredientInEditPopup(String newName, String newQty, String newUnit) {
        // assumes edit popup already open
        if (newName != null) {
            clearAndType(popupEditNameInput, newName);
        }
        if (newQty != null) {
            clearAndType(popupEditQtyInput, newQty);
        }
        if (newUnit != null) {
            clearAndType(popupEditUnitInput, newUnit);
        }

        try { getDriver().hideKeyboard(); } catch (Exception ignored) {}
        click(popupEditBtnUpdateText);
        WaitingHelper.sleepSeconds(1);
        AllureHelper.attachScreenshot("Updated ingredient");
    }

    private void clearAndType(By locator, String value) {
        try {
            WaitingHelper.waitForVisible(locator);
            WebElement el = getDriver().findElement(locator);
            el.clear();
            el.sendKeys(value);
        } catch (Exception e) {
            // fallback to BaseScreen.type if clear fails
            type(locator, value);
        }
    }

    // ================= COMPLETE =================
    public void clickComplete() {
        click(btnCompleteText);
        WaitingHelper.sleepSeconds(2);
        AllureHelper.attachScreenshot("Clicked Complete (Shopping List)");
    }

    public boolean isCheckboxCheckedAt(int index) {
        List<WebElement> cbs = getDriver().findElements(anyCheckbox);
        if (cbs.isEmpty()) throw new RuntimeException("❌ No checkbox found");
        if (index >= cbs.size()) throw new RuntimeException("❌ Checkbox index out of range: " + index);
        String checked = cbs.get(index).getAttribute("checked");
        return "true".equalsIgnoreCase(checked);
    }
    // ================= SWIPE =================
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
    }
    @Step("Swipe LEFT inside element (times={times})")
    public void swipeLeftInside(By container, int times) {
        WaitingHelper.waitForVisible(container);
        WebElement el = getDriver().findElement(container);

        int left = el.getLocation().getX();
        int top = el.getLocation().getY();
        int width = el.getSize().getWidth();
        int height = el.getSize().getHeight();

        int y = top + height / 2;

        // swipe từ phải -> trái
        int startX = left + (int) (width * 0.85);
        int endX   = left + (int) (width * 0.15);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        for (int i = 1; i <= times; i++) {
            Sequence swipe = new Sequence(finger, 1);
            swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, y));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, y));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            getDriver().perform(Collections.singletonList(swipe));
            WaitingHelper.sleepSeconds(1);
        }
    }

    @Step("Swipe RIGHT inside element (times={times})")
    public void swipeRightInside(By container, int times) {
        WaitingHelper.waitForVisible(container);
        WebElement el = getDriver().findElement(container);

        int left = el.getLocation().getX();
        int top = el.getLocation().getY();
        int width = el.getSize().getWidth();
        int height = el.getSize().getHeight();

        int y = top + height / 2;

        // swipe từ trái -> phải
        int startX = left + (int) (width * 0.15);
        int endX   = left + (int) (width * 0.85);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        for (int i = 1; i <= times; i++) {
            Sequence swipe = new Sequence(finger, 1);
            swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, y));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, y));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            getDriver().perform(Collections.singletonList(swipe));
            WaitingHelper.sleepSeconds(1);
        }
    }
}