

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class BepesAIScreen extends BaseScreen {
    private static final Logger logger = LoggerFactory.getLogger(EditRecipeScreen.class);

    // ===== MAIN =====
    private final By headerBepes = By.xpath("//android.widget.TextView[@text='Bepes']");
    private final By btnBack = By.xpath("//android.view.View[@content-desc='Quay lại']");

    private final By txtSelectedDish = By.xpath("//android.widget.TextView[contains(@text,'Món đang chọn:')]");
    private final By txtNotesInfo = By.xpath("//android.widget.TextView[contains(@text,'Ghi chú ăn uống:')]");

    // Toggle actions
    private final By toggleHideActions = By.xpath("//android.view.View[@content-desc='Ẩn thao tác']");
    private final By toggleShowActions = By.xpath("//android.view.View[@content-desc='Hiện thao tác']");

    // Action buttons row
    private final By btnChooseDish = By.xpath("//android.widget.TextView[@text='Chọn món']");
    private final By btnFood = By.xpath("//android.widget.TextView[@text='Món ăn']");
    private final By btnNotes = By.xpath("//android.widget.TextView[@text='Ghi chú']");
    private final By btnViewRecipes = By.xpath("//android.widget.TextView[@text='Xem công thức']");
    private final By btnCompleteText = By.xpath("//android.widget.TextView[@text='Hoàn thành']");

    // Header icon complete (only after AI responded + hide actions)
    private final By iconCompleteHeader = By.xpath("//android.view.View[@content-desc='Hoàn thành']");

    // Chat
    private final By chatInput = By.xpath("//android.widget.EditText");
    private final By btnSend = By.xpath("//android.view.View[@content-desc='Gửi']");
    // ===== SHEET COMMON =====
    private final By sheetDragHandle = By.xpath("//android.view.View[@content-desc='Drag handle']");
    private final By closeSheet = By.xpath("//android.view.View[@content-desc='Nút kéo']"); // for Notes/Recipes sheet
    // ===== CHOOSE DISH / FOOD SHEET =====
    private final By sheetChooseDishTitle = By.xpath("//android.widget.TextView[@text='Chọn món cho phiên chat']");
    private final By sheetSuggestedHeader = By.xpath("//android.widget.TextView[@text='Món đề xuất']");
    private final By sheetSelectedListHeader = By.xpath("//android.widget.TextView[@text='Danh sách món đã chọn']");

    private By chooseDishButtonByIndex(int index) {
        return By.xpath("(//android.widget.TextView[@text='Chọn món'])[" + index + "]");
    }
    // Confirm popup choose dish

    private final By popupChooseDishTitle = By.xpath("//android.widget.TextView[@text='Chọn món này?']");
    private final By popupBtnCancel = By.xpath("//android.widget.TextView[@text='Hủy']");
    private final By popupBtnConfirm = By.xpath("//android.widget.TextView[@text='Xác nhận']");

    // Food item actions
    private final By iconMoveUp = By.xpath("//android.view.View[@content-desc='Đưa món lên']");
    private final By iconMoveDown = By.xpath("//android.view.View[@content-desc='Đưa món xuống']");
    private final By iconDeleteDish = By.xpath("//android.view.View[@content-desc='Xóa món']");
    private final By btnPrioritizeDish = By.xpath("//android.widget.TextView[@text='Ưu tiên món này']");
    private final By lblPrioritized = By.xpath("//android.widget.TextView[@text='Đang ưu tiên']");

    // ===== NOTES =====
    private final By notesSheetTitle = By.xpath("//android.widget.TextView[@text='Ghi chú ăn uống']");
    private final By notesBtnRefresh = By.xpath("//android.widget.TextView[@text='Làm mới']");
    private final By notesBtnAdd = By.xpath("//android.widget.TextView[@text='Thêm']");
    private final By notesCounter = By.xpath("//android.widget.TextView[contains(@text,'Đang bật:')]");

    // Add note popup
    private final By editNoteTitle = By.xpath("//android.widget.TextView[@text='Cập nhật ghi chú']");
    private final By addNoteTitle = By.xpath("//android.widget.TextView[@text='Thêm ghi chú']");
    private final By addNoteTypeHeader = By.xpath("//android.widget.TextView[@text='Loại ghi chú']");
    private final By tabAllergy = By.xpath("//android.widget.TextView[@text='Dị ứng']");
    private final By tabRestriction = By.xpath("//android.widget.TextView[@text='Hạn chế']");
    private final By tabPreference = By.xpath("//android.widget.TextView[@text='Sở thích']");
    private final By tabHealth = By.xpath("//android.widget.TextView[@text='Sức khỏe']");

    private final By addNoteInput1 = By.xpath("(//android.widget.EditText)[1]");
    private final By addNoteInput2 = By.xpath("(//android.widget.EditText)[2]");
    private final By addNoteInput3 = By.xpath("(//android.widget.EditText)[3]");

    private final By addNoteToggleText = By.xpath("//android.widget.ScrollView/android.view.View[5]");
    private final By addNoteBtnCancel = By.xpath("//android.widget.TextView[@text='Hủy']");
    private final By addNoteBtnSave = By.xpath("//android.widget.TextView[@text='Lưu']");

    private By noteItemTitle(String title) {
        return By.xpath("//android.widget.TextView[@text='" + title + "']");
    }

    private By noteItemEditByTitle(String title) {

        return By.xpath(
                "//android.view.View[.//android.widget.TextView[@text='" + title + "']]"
                        + "//android.widget.TextView[@text='Sửa']"
        );
    }
    private By noteItemDeleteByTitle(String title) {

        return By.xpath(
                "//android.view.View[.//android.widget.TextView[@text='" + title + "']]"
                        + "//android.widget.TextView[@text='Xóa']"
        );
    }

    // ===== VIEW RECIPES =====
    private final By recipesSheetTitle = By.xpath("//android.widget.TextView[@text='Công thức trong phiên']");
    private final By toastNeedChooseDish = By.xpath("//android.widget.Toast[@text='Hãy chọn món trước khi mở công thức']");
    private final By openDetailRecipes = By.xpath("//android.widget.ScrollView/android.view.View[1]");
    private final By toastSendAI = By.xpath("//android.widget.Toast[@text='Chọn món xong mới có thể chat']");
    // ===== FINISH SESSION POPUP =====
    private final By finishPopupTitle = By.xpath("//android.widget.TextView[@text='Hoàn tất phiên nấu']");
    private final By finishCompleted = By.xpath("//android.widget.TextView[@text='Completed']");
    private final By finishAbandoned = By.xpath("//android.widget.TextView[@text='Abandoned']");
    private final By finishMarkAllDone = By.xpath("//android.widget.TextView[@text='Đánh dấu tất cả là xong']");
    private final By finishMarkAllSkip = By.xpath("//android.widget.TextView[@text='Đánh dấu tất cả là bỏ qua']");
    private final By finishKeepState = By.xpath("//android.widget.TextView[@text='Giữ nguyên trạng thái hiện tại']");
    private final By finishNoteHeader = By.xpath("//android.widget.TextView[@text='Ghi chú']");
    private final By finishNoteInput = By.xpath("//android.widget.EditText");
    private final By finishBtnCancel = By.xpath("//android.widget.TextView[@text='Hủy']");
    private final By finishBtnDone = By.xpath("//android.widget.TextView[@text='Hoàn thành']");

    // ================= BASIC =================
    public void waitForBepesLoaded() {
        WaitingHelper.waitForVisible(headerBepes);
    }
    public void openDetailRecipes(){click(openDetailRecipes);}
//    public void verifyBepesMainUI() {
//        WaitingHelper.waitForVisible(headerBepes);
//        WaitingHelper.waitForVisible(txtSelectedDish);
//        WaitingHelper.waitForVisible(txtNotesInfo);
//
//        if (!isDisplayed(toggleHideActions) && !isDisplayed(toggleShowActions)) {
//            AllureHelper.attachScreenshot("Missing toggle Ẩn/Hiện thao tác");
//            throw new AssertionError("❌ Không thấy toggle Ẩn/Hiện thao tác");
//        }
//
//        // chị expect đủ nút thao tác ở TC_01
//        WaitingHelper.waitForVisible(btnNotes);
//        WaitingHelper.waitForVisible(btnViewRecipes);
//        WaitingHelper.waitForVisible(btnCompleteText);
//
//        AllureHelper.attachScreenshot("Bepes main UI OK");
//    }

    public void verifyBepesMainUI() {

        WaitingHelper.waitForVisible(headerBepes);
        WaitingHelper.waitForVisible(txtSelectedDish);
        WaitingHelper.waitForVisible(txtNotesInfo);

        // app có thể đang hiện hoặc ẩn actions
        if (!isDisplayed(toggleHideActions)
                && !isDisplayed(toggleShowActions)) {

            AllureHelper.attachScreenshot("Missing action toggle");

            throw new RuntimeException("❌ Không thấy toggle thao tác");
        }

        AllureHelper.attachScreenshot("Bepes main UI OK");
    }
    public void clickBack() { click(btnBack); }

    // ================= TOGGLE ACTIONS =================
    @Step("Toggle: Hide actions")
    public void hideActions() {
        if (isDisplayed(toggleHideActions)) click(toggleHideActions);
        WaitingHelper.sleepSeconds(1);
        AllureHelper.attachScreenshot("Hide actions");
    }

    @Step("Toggle: Show actions")
    public void showActions() {
        if (isDisplayed(toggleShowActions)) click(toggleShowActions);
        WaitingHelper.sleepSeconds(1);
        AllureHelper.attachScreenshot("Show actions");
    }

    public boolean isHeaderCompleteIconVisible() {
        return getDriver().findElements(iconCompleteHeader).size() > 0;
    }

    // ================= CHAT =================
    @Step("Send message: {msg}")
    public void sendMessage(String msg) {
        WaitingHelper.waitForVisible(chatInput);
        type(chatInput, msg);
        click(btnSend);
        AllureHelper.attachScreenshot("Sent message");
    }

    /** Best-effort wait for AI response: đảm bảo UI không treo + chờ 1 khoảng hợp lý */
    public void waitForAiResponse(int timeoutSeconds) {
        long end = System.currentTimeMillis() + timeoutSeconds * 1000L;
        while (System.currentTimeMillis() < end) {
            WaitingHelper.sleepSeconds(1);
            // still responsive
            if (getDriver().findElements(chatInput).size() > 0) {
                // no-op
            }
        }
        AllureHelper.attachScreenshot("Wait AI response ~" + timeoutSeconds + "s");
    }

    // ================= CHOOSE DISH / FOOD =================
    public void openChooseDishSheet() {
        click(btnChooseDish);
        WaitingHelper.sleepSeconds(1);
        AllureHelper.attachScreenshot("Choose dish sheet opened");
    }

    @Step("Click choose dish by index: {index}")
    public void clickChooseDishByIndex(int index) {

        List<WebElement> buttons =
                getDriver().findElements(By.xpath("//android.widget.TextView[@text='Chọn món']"));

        click(chooseDishButtonByIndex(index));

        WaitingHelper.waitForVisible(popupChooseDishTitle);

        AllureHelper.attachScreenshot("Choose dish popup index: " + index);
    }

    public void cancelChooseDishPopup() {
        click(popupBtnCancel);
        WaitingHelper.sleepSeconds(1);
        AllureHelper.attachScreenshot("Cancel choose dish popup");
    }

    public void confirmChooseDishPopup() {
        click(popupBtnConfirm);
        WaitingHelper.sleepSeconds(1);
        AllureHelper.attachScreenshot("Confirm choose dish popup");
    }

    public void verifyFoodSheetIconsExist() {
        WaitingHelper.waitForVisible(iconMoveUp);
        WaitingHelper.waitForVisible(iconMoveDown);
        WaitingHelper.waitForVisible(iconDeleteDish);
        AllureHelper.attachScreenshot("Food icons up/down/delete visible");
    }

    public void clickMoveUpFirst() {
        List<WebElement> els = getDriver().findElements(iconMoveUp);
        if (els.isEmpty()) throw new RuntimeException("❌ Không tìm thấy icon Đưa món lên");
        els.get(0).click();
        WaitingHelper.sleepSeconds(1);
        AllureHelper.attachScreenshot("Clicked move up");
    }

    public void clickMoveDownFirst() {
        List<WebElement> els = getDriver().findElements(iconMoveDown);
        if (els.isEmpty()) throw new RuntimeException("❌ Không tìm thấy icon Đưa món xuống");
        els.get(0).click();
        WaitingHelper.sleepSeconds(1);
        AllureHelper.attachScreenshot("Clicked move down");
    }

    public void clickDeleteDishFirst() {
        List<WebElement> els = getDriver().findElements(iconDeleteDish);
        if (els.isEmpty()) throw new RuntimeException("❌ Không tìm thấy icon Xóa món");
        els.get(0).click();
        WaitingHelper.sleepSeconds(1);
        AllureHelper.attachScreenshot("Clicked delete dish");
    }

    public void clickPrioritizeFirst() {
        List<WebElement> els = getDriver().findElements(btnPrioritizeDish);
        if (els.isEmpty()) throw new RuntimeException("❌ Không tìm thấy nút Ưu tiên món này");
        els.get(0).click();
        WaitingHelper.sleepSeconds(1);
        AllureHelper.attachScreenshot("Clicked prioritize dish");
    }

    public void verifyPrioritizedLabelExists() {
        WaitingHelper.waitForVisible(lblPrioritized);
        AllureHelper.attachScreenshot("Prioritized label exists");
    }

    // ================= NOTES =================
    public void openNotesSheet() {
        click(btnNotes);
        WaitingHelper.waitForVisible(notesSheetTitle);
        WaitingHelper.waitForVisible(notesBtnAdd);
        WaitingHelper.waitForVisible(notesCounter);
        AllureHelper.attachScreenshot("Notes sheet opened");
    }

    public void closeNotesSheet() {
        if (isDisplayed(closeSheet)) {
            click(closeSheet);
        } else {
            getDriver().navigate().back();
        }
        WaitingHelper.sleepSeconds(1);
        AllureHelper.attachScreenshot("Notes sheet closed");
    }

    public String getNotesCounterText() {
        WaitingHelper.waitForVisible(notesCounter);
        return getText(notesCounter);
    }

    public void openAddNotePopup() {
        click(notesBtnAdd);
        WaitingHelper.waitForVisible(addNoteTitle);
        WaitingHelper.waitForVisible(addNoteTypeHeader);
        WaitingHelper.waitForVisible(tabAllergy);
        WaitingHelper.waitForVisible(tabRestriction);
        WaitingHelper.waitForVisible(tabPreference);
        WaitingHelper.waitForVisible(tabHealth);
        AllureHelper.attachScreenshot("Add note popup opened");
    }

    public void selectNoteTypeAllergy() { click(tabAllergy); }
    public void selectNoteTypeRestriction() { click(tabRestriction); }
    public void selectNoteTypePreference() { click(tabPreference); }
    public void selectNoteTypeHealth() { click(tabHealth); }

    public void fillNoteFields(String f1, String f2, String f3) {
        type(addNoteInput1, f1);
        type(addNoteInput2, f2);
        type(addNoteInput3, f3);
        hideKeyboardIfVisible();
        AllureHelper.attachScreenshot("Filled note fields");
    }

    public void toggleNoteActiveInPopup() {
        click(addNoteToggleText);
        WaitingHelper.sleepSeconds(1);
        AllureHelper.attachScreenshot("Toggled note active");
    }

    public void saveNotePopup() {
        click(addNoteBtnSave);
        WaitingHelper.sleepSeconds(1);
        AllureHelper.attachScreenshot("Saved note");
    }

    public void cancelNotePopup() {
        click(addNoteBtnCancel);
        WaitingHelper.sleepSeconds(1);
        AllureHelper.attachScreenshot("Cancelled note popup");
    }

    public void verifyNoteItemExists(String title) {
        WaitingHelper.waitForVisible(noteItemTitle(title));
        AllureHelper.attachScreenshot("Note exists: " + title);
    }

    public boolean isNotePresent(String title) {
        try {
            return getDriver().findElements(noteItemTitle(title)).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickEditNoteByTitle(String title) {

        By editLocator = noteItemEditByTitle(title);

        WaitingHelper.waitForVisible(editLocator);

        getDriver().findElement(editLocator).click();

        WaitingHelper.waitForVisible(editNoteTitle);

        AllureHelper.attachScreenshot("Edit note popup opened: " + title);
    }
    public void fillEditNoteFields(String f1, String f2, String f3) {

        WebElement input1 = getDriver().findElement(addNoteInput1);
        input1.clear();
        input1.sendKeys(f1);

        WebElement input2 = getDriver().findElement(addNoteInput2);
        input2.clear();
        input2.sendKeys(f2);

        WebElement input3 = getDriver().findElement(addNoteInput3);
        input3.clear();
        input3.sendKeys(f3);

        hideKeyboardIfVisible();

        AllureHelper.attachScreenshot("Filled edit note fields");
    }
    public void clickDeleteNoteByTitle(String title) {

        By deleteLocator = noteItemDeleteByTitle(title);

        WaitingHelper.waitForVisible(deleteLocator);

        getDriver().findElement(deleteLocator).click();

        WaitingHelper.sleepSeconds(1);

        AllureHelper.attachScreenshot("Deleted note: " + title);
    }

    // ================= VIEW RECIPES =================
    public void openViewRecipes() {
        click(btnViewRecipes);
        WaitingHelper.sleepSeconds(1);
        AllureHelper.attachScreenshot("Clicked view recipes");
    }

    public void verifyRecipesSheetOpened() {
        WaitingHelper.waitForVisible(recipesSheetTitle);
        AllureHelper.attachScreenshot("Recipes sheet opened");
    }

    public boolean verifyNeedChooseDishToast() {
        click(btnViewRecipes);
        WaitingHelper.sleepSeconds(1);
        AllureHelper.attachScreenshot("Clicked view recipes");
        try {
            getDriver().findElement(toastNeedChooseDish);
            logger.info("Toast error displayed");
            AllureHelper.attachScreenshot("Toast Error Displayed");
            return true;
        } catch (Exception e) {
            logger.error("Toast error NOT displayed");
            return false;
        }
    }
    public boolean toastSendAI() {
        click(btnSend);
        WaitingHelper.sleepSeconds(1);
        AllureHelper.attachScreenshot("Clicked Sens msg");
        try {
            getDriver().findElement(toastSendAI);
            logger.info("Toast error displayed");
            AllureHelper.attachScreenshot("Toast Error Displayed");
            return true;
        } catch (Exception e) {
            logger.error("Toast error NOT displayed");
            return false;
        }
    }
    // ================= FINISH SESSION =================
    public void openFinishPopupByActionButton() {
        click(btnCompleteText);
        WaitingHelper.waitForVisible(finishPopupTitle);
        AllureHelper.attachScreenshot("Finish popup opened (action button)");
    }

    public void openFinishPopupByHeaderIcon() {

        click(iconCompleteHeader);
        AllureHelper.attachScreenshot("Finish popup opened (header icon)");
    }

    public void verifyFinishPopupUI() {
        WaitingHelper.waitForVisible(finishPopupTitle);
        WaitingHelper.waitForVisible(finishCompleted);
        WaitingHelper.waitForVisible(finishAbandoned);
        WaitingHelper.waitForVisible(finishMarkAllDone);
        WaitingHelper.waitForVisible(finishMarkAllSkip);
        WaitingHelper.waitForVisible(finishKeepState);
        WaitingHelper.waitForVisible(finishNoteHeader);
        WaitingHelper.waitForVisible(finishBtnCancel);
        WaitingHelper.waitForVisible(finishBtnDone);
        AllureHelper.attachScreenshot("Finish popup UI OK");
    }

    public void cancelFinishPopup() {
        click(finishBtnCancel);
        WaitingHelper.sleepSeconds(1);
        AllureHelper.attachScreenshot("Finish popup cancelled");
    }

    public void completeFinishPopup(String note) {
        if (note != null) {
            type(finishNoteInput, note);
            hideKeyboardIfVisible();
        }
        click(finishBtnDone);
        WaitingHelper.sleepSeconds(2);
        AllureHelper.attachScreenshot("Finish popup completed");
    }
    public boolean exists(By locator) {
        return getDriver().findElements(locator).size() > 0;
    }

    public boolean isActionsVisible() {

        return exists(btnChooseDish)
                || exists(btnFood)
                || exists(btnNotes)
                || exists(btnViewRecipes)
                || exists(btnCompleteText);
    }
    @Step("Ensure actions visible")
    public void showActions_2() {

        // actions đã hiện -> bỏ qua ngay
        if (isActionsVisible()) {
            return;
        }

        // nếu thấy nút Hiện thao tác -> click NGAY
        if (exists(toggleShowActions)) {

            getDriver().findElement(toggleShowActions).click();

            WaitingHelper.sleepSeconds(1);

            return;
        }

        throw new RuntimeException("❌ Không tìm thấy nút Hiện thao tác");
    }

        @Step("Open choose dish sheet smart")
        public void openChooseDishSheetSmart() {

            // nếu sheet đã mở
            if (exists(sheetChooseDishTitle)) {
                return;
            }

            // đảm bảo actions hiện
            showActions_2();

            // ưu tiên chọn món
            if (exists(btnChooseDish)) {

                getDriver().findElement(btnChooseDish).click();

                WaitingHelper.sleepSeconds(1);

                return;
            }

            // fallback món ăn
            if (exists(btnFood)) {

                getDriver().findElement(btnFood).click();

                WaitingHelper.sleepSeconds(1);

                return;
            }

            throw new RuntimeException("❌ Không mở được Choose Dish Sheet");
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