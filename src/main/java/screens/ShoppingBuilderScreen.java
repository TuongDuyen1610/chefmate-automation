package screens;

import core.base.BaseScreen;
import core.utils.AllureHelper;
import core.utils.WaitingHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import java.util.Collections;

public class ShoppingBuilderScreen extends BaseScreen {

    // ===== NAV (HOME MENU) =====
    private final By menuOptions = By.xpath("//android.view.View[@content-desc='Options']");
    private final By menuShoppingBuilder = By.xpath("//android.widget.TextView[@text='Lập danh sách\n" +
            "mua sắm']");

    // ===== BUILDER UI =====
    private final By header = By.xpath("//android.widget.TextView[@text='Lập danh sách mua sắm']");
    private final By searchIcon = By.xpath("//android.widget.ImageView[@content-desc='search']");
    private final By searchInput = By.xpath("//android.widget.EditText"); // search riêng cho màn
    private final By btnBack = By.xpath("//android.view.View[@content-desc='Quay lại']");

    private final By txtAddByRecipe = By.xpath("//android.widget.TextView[@text='Thêm nguyên liệu qua công thức']");

    // Empty state (không có công thức)
    private final By txtEmpty1 = By.xpath("//android.widget.TextView[@text='Chưa có công thức nào để lựa chọn.']");
    private final By txtEmpty2 = By.xpath("//android.widget.TextView[@text='Thêm công thức mới từ màn hình chính.']");

    private final By txtAddManual = By.xpath("//android.widget.TextView[@text='Thêm nguyên liệu thủ công']");

    // Open manual add bottom sheet
    private final By iconAddManually = By.xpath("//android.view.View[@content-desc='add manually']");

    // ===== MANUAL ADD SHEET =====
    private final By sheetClose = By.xpath("//android.view.View[@content-desc='Close sheet']");
    private final By sheetDragHandle = By.xpath("//android.view.View[@content-desc='Drag handle']");

    private final By sheetHintName = By.xpath("//android.widget.TextView[@text='Nhập tên nguyên liệu']");
    private final By sheetHintQty = By.xpath("//android.widget.TextView[@text='Nhập định lượng']");
    private final By sheetLabelName = By.xpath("//android.widget.TextView[@text='Tên nguyên liệu']");
    private final By sheetLabelQty = By.xpath("//android.widget.TextView[@text='Khối lượng']");
    private final By sheetLabelUnit = By.xpath("//android.widget.TextView[@text='Đơn vị']");

    private final By sheetNameInput = By.xpath("(//android.widget.ScrollView//android.widget.EditText)[1]");
    private final By sheetQtyInput  = By.xpath("(//android.widget.ScrollView//android.widget.EditText)[2]");
    private final By sheetUnitInput = By.xpath("(//android.widget.ScrollView//android.widget.EditText)[3]");

    private final By sheetBtnAddText = By.xpath("//android.widget.TextView[@text='Thêm']");

    // ===== COMPLETE =====
    private final By btnCompleteText = By.xpath("//android.widget.TextView[@text='Hoàn thành']");

    // ===== DYNAMIC =====
    // checkbox list (theo xpath chị cung cấp): ...View[1]/CheckBox, ...View[2]/CheckBox,...
    private By recipeCheckboxByIndex(int index1Based) {
        return By.xpath("//z0.h0/android.view.View/android.view.View[2]/android.view.View["
                + index1Based + "]/android.widget.CheckBox");
    }

    // manual preview on builder (chị mô tả có hiển thị text “Test_1 4 g”…)
    private By manualPreviewText(String previewText) {
        return By.xpath("//android.widget.TextView[@text='" + previewText + "']");
    }

    // ================= NAV =================
    @Step("Open Shopping Builder from Home menu")
    public void openFromHomeMenu() {
        click(menuOptions);
        click(menuShoppingBuilder);
        WaitingHelper.waitForVisible(header);
        AllureHelper.attachScreenshot("Shopping Builder opened");
    }@Step("Open Shopping Builder from Home menu")
    public void openFromHomeMenu2() {
        click(menuOptions);
        click(menuShoppingBuilder);
        AllureHelper.attachScreenshot("Shopping Builder opened");
    }

    public void openDSMS() {
        click(menuOptions);
        click(menuShoppingBuilder);}
    // ================= VERIFY =================
    @Step("Verify initial Builder UI")
    public void verifyInitialUI() {
        WaitingHelper.waitForVisible(header);
        WaitingHelper.waitForVisible(searchIcon);
        WaitingHelper.waitForVisible(searchInput);
        WaitingHelper.waitForVisible(txtAddByRecipe);
        WaitingHelper.waitForVisible(txtAddManual);
        WaitingHelper.waitForVisible(btnCompleteText);
        AllureHelper.attachScreenshot("Builder initial UI OK");
    }

    @Step("Verify empty recipe UI")
    public void verifyEmptyRecipeState() {
        WaitingHelper.waitForVisible(txtEmpty1);
        WaitingHelper.waitForVisible(txtEmpty2);
        AllureHelper.attachScreenshot("Builder empty recipe state OK");
    }

    // ================= RECIPE TICK/UNTICK =================
    @Step("Toggle recipe checkbox by index={index1Based}")
    public void toggleRecipeByIndex(int index1Based) {
        By cb = recipeCheckboxByIndex(index1Based);
        WaitingHelper.waitForVisible(cb);
        click(cb);
        WaitingHelper.sleepSeconds(1);
        AllureHelper.attachScreenshot("Toggled recipe checkbox index=" + index1Based);
    }
    // ================= MANUAL ADD =================
    @Step("Open manual add sheet")
    public void openManualAddSheet() {
        click(iconAddManually);
        WaitingHelper.waitForVisible(sheetHintName);
        WaitingHelper.waitForVisible(sheetDragHandle);
        WaitingHelper.waitForVisible(sheetLabelName);
        WaitingHelper.waitForVisible(sheetLabelQty);
        WaitingHelper.waitForVisible(sheetLabelUnit);
        AllureHelper.attachScreenshot("Manual sheet opened");
    }

    @Step("Close manual add sheet")
    public void closeManualAddSheet() {
        click(sheetClose);
        WaitingHelper.sleepSeconds(1);
        AllureHelper.attachScreenshot("Manual sheet closed");
    }

    @Step("Add manual ingredient: name={name}, qty={qty}, unit={unit}")
    public void addManualIngredient(String name, String qty, String unit) {
        openManualAddSheet();

        type(sheetNameInput, name);
        try { getDriver().hideKeyboard(); } catch (Exception ignored) {}

        type(sheetQtyInput, qty);
        try { getDriver().hideKeyboard(); } catch (Exception ignored) {}

        type(sheetUnitInput, unit);
        try { getDriver().hideKeyboard(); } catch (Exception ignored) {}

        click(sheetBtnAddText);
        WaitingHelper.sleepSeconds(1);

        // sheet có thể tự đóng; nếu chưa thì đóng
        try { if (isDisplayed(sheetClose)) click(sheetClose); } catch (Exception ignored) {}

        AllureHelper.attachScreenshot("Manual ingredient added (Builder)");
    }
    public void clickBack() {
        click(btnBack); }

    @Step("Verify manual preview exists on Builder: {previewText}")
    public void verifyManualPreviewOnBuilder(String previewText) {
        WaitingHelper.waitForVisible(manualPreviewText(previewText));
        AllureHelper.attachScreenshot("Manual preview found: " + previewText);
    }

    // ================= COMPLETE =================
    @Step("Click Hoàn thành (Builder)")
    public void clickComplete() {
        click(btnCompleteText);
        AllureHelper.attachScreenshot("Clicked Complete (Builder)");
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
    @Step("Swipe down inside element (times={times}): {containerXpath}")
    public void slowSwipeDownInside(String containerXpath, int times) {
        By container = By.xpath(containerXpath);
        WaitingHelper.waitForVisible(container);

        org.openqa.selenium.WebElement el = getDriver().findElement(container);

        // ✅ dùng getLocation + getSize thay cho getRect
        int left = el.getLocation().getX();
        int top = el.getLocation().getY();
        int width = el.getSize().getWidth();
        int height = el.getSize().getHeight();

        int x = left + width / 2;
        int startY = top + (int) (height * 0.80);
        int endY   = top + (int) (height * 0.20);

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
}