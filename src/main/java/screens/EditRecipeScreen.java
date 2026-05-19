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
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import java.util.Random;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class EditRecipeScreen extends BaseScreen {
    private static final Logger logger = LoggerFactory.getLogger(EditRecipeScreen.class);

    // ===== HEADER =====
    private final By header = By.xpath("//android.widget.TextView[@text='Sửa công thức']");
    private final By btnBack = By.xpath("//android.view.View[@content-desc='back']");

    // ===== BASIC FIELDS =====
    private final By inputName = By.xpath("//android.widget.ScrollView/android.widget.EditText[1]");
    private final By inputTime = By.xpath("//android.widget.ScrollView/android.widget.EditText[2]");
    private final By inputServing = By.xpath("//android.widget.ScrollView/android.widget.EditText[4]");
    private final By inputStep1 = By.xpath("//android.widget.TextView[@text='Các bước nấu']/following::android.widget.EditText[1]");
    private final By inputStep2 = By.xpath("//android.widget.ScrollView/android.widget.EditText[2]");
    private final By inputStep3 = By.xpath("//android.widget.ScrollView/android.view.View[1]/android.widget.EditText[1]");
    private final By inputStep4 = By.xpath("//android.widget.ScrollView/android.view.View[1]/android.widget.EditText[2]");
    private final By inputStep5 = By.xpath("//android.widget.ScrollView/android.view.View[1]/android.widget.EditText[3]");

            // ===== UPDATE BUTTON =====
    private final By btnUpdate = By.xpath("//android.widget.TextView[@text='Cập nhật công thức']");

    // ===== INGREDIENT =====
    private final By btnDeleteIngredient = By.xpath("//android.view.View[@content-desc='Xóa nguyên liệu']");
    private final By btnAddIngredient = By.xpath("//android.view.View[@content-desc='Thêm nguyên liệu']");

    // ===== STEP =====
    private final By btnDeleteStep = By.xpath("//android.view.View[@content-desc='Xóa bước']");
    private final By btnAddStep = By.xpath("//android.view.View[@content-desc='Thêm bước nấu']");

    // ===== TOAST =====
    private final By toastRequired =
            By.xpath("//android.widget.Toast[@text='Vui lòng điền đầy đủ thông tin']");

    private final By toastInvalid =
            By.xpath("//android.widget.Toast[@text='Cần ít nhất 1 nguyên liệu và 1 bước nấu hợp lệ']");

    // ===== AVATAR =====
    private final By avatar =
            By.xpath("//android.widget.ScrollView/android.view.View[1]");

    // ===== GALLERY EMULATOR=====
    private final By imageEmulator_1 =
            By.xpath("(//android.widget.ImageView[@resource-id='com.google.android.documentsui:id/icon_thumb'])[2]");

    private final By imageEmulator_2 =
            By.xpath("(//android.widget.ImageView[@resource-id='com.google.android.documentsui:id/icon_thumb'])[3]");

    // ===== GALLERY VIEW MODE REAL =====
    private final By btnListView =
            By.xpath("//android.widget.Button[@content-desc='Chế độ xem danh sách']");

    // ===== IMAGE (FIX CỨNG 2 ẢNH) =====
    private final By image1 =
            By.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Image_1777131646616.jpg']");

    private final By image2 =
            By.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Image_1777131512742.jpg']");

    // ================= TAG (THEO XPATH CHỊ CUNG CẤP) =================

    // Title popup
    private final By popupTitle = By.xpath("//android.widget.TextView[@text='Thêm tag']");

    // ✅ Nút "Thêm" ngoài màn Edit: scope theo section Tags để tránh click nhầm
    // (tránh following:: để không dính lỗi XPath engine)
    private final By btnAddTagOutside =
            By.xpath("//android.widget.TextView[@text='Thêm']");

    // Nếu cấu trúc parent khác nhau trên vài máy, fallback nhẹ:
    private final By btnAddTagOutside_Fallback =
            By.xpath("(//android.widget.TextView[@text='Tags']/following::android.widget.TextView[@text='Thêm'])[1]");

    // ✅ label "Tên tag" (để click giống manual)
    private final By lblTagName = By.xpath("//android.widget.TextView[@text='Tên tag']");

    // ✅ EditText thật trong popup: lấy EditText đầu tiên sau title "Thêm tag"
    private final By inputTagEditText = By.xpath("//android.widget.EditText");

    // ✅ Button "Thêm" trong popup: lấy Button đầu tiên sau TextView "Thêm tag"
    // (tránh sibling vì sibling có thể fail)
    private final By btnAddInPopup =
            By.xpath("(//android.widget.Button)[1]");

    // ✅ Close popup
    private final By btnClosePopup =
            By.xpath("//android.view.View[@content-desc='Close']");

    // ✅ Chip tags trong popup: chỉ lấy chip trong popup (HorizontalScrollView sau title)
    private final By tagChipInPopup =
            By.xpath("//android.widget.TextView[@text='Thêm tag']/following::android.widget.HorizontalScrollView[1]//android.widget.TextView");
    private final By anyTextViews = By.className("android.widget.TextView");

    // ✅ Chip tags ngoài màn Edit: dùng verify theo text để không cần following::
    private By tagChipOutsideByText(String tagName) {
        return By.xpath("//android.widget.HorizontalScrollView//android.widget.TextView[@text='" + tagName + "']");
    }
    // ================= VERIFY =================

    public boolean isDisplayed() {return isDisplayed(header);}

    public boolean isFormLoaded() {
        return isDisplayed(inputName)
                && isDisplayed(inputTime)
                && isDisplayed(inputServing);
    }

    public void clickBack() {
        click(btnBack); }
    // ================= ACTION =================

    public void clearName() {
        var el = getDriver().findElement(inputName);
        el.click();
        el.clear();

        if (!el.getText().isEmpty()) {
            el.sendKeys("\u0008\u0008\u0008\u0008\u0008\u0008");
        }
    }

    public void clearTime() {type(inputTime, "");}
    public void clearServing() {type(inputServing, "");}
    public void clearInput1() {type(inputStep1, "");}
    public void clearInput3() {type(inputStep3, "");}
    public void clearInput4() {type(inputStep4, "");}
    public void clearInput5() {type(inputStep5, "");}
    public void clickUpdate() {click(btnUpdate);}

    // ===== INGREDIENT =====
    public void deleteAllIngredients() {

        List<WebElement> items = getDriver().findElements(btnDeleteIngredient);

        while (items.size() > 1) {
            click(btnDeleteIngredient);
            items = getDriver().findElements(btnDeleteIngredient);
        }
    }

    // ===== STEP =====
    public void deleteAllSteps() {

        List<WebElement> items = getDriver().findElements(btnDeleteStep);

        while (items.size() > 0) {
            click(btnDeleteStep);
            items = getDriver().findElements(btnDeleteStep);
        }
    }

    // ================= TOAST =================

    public boolean isRequiredToastDisplayed() {
        try {
            getDriver().findElement(toastRequired);
            logger.info("Toast error displayed");
            AllureHelper.attachScreenshot("Toast Error Displayed");
            return true;
        } catch (Exception e) {
            logger.error("Toast error NOT displayed");
            return false;
        }
    }

    public boolean isInvalidToastDisplayed() {
        try {
            getDriver().findElement(toastInvalid);
            logger.info("Toast error displayed");
            AllureHelper.attachScreenshot("Toast Error Displayed");
            return true;
        } catch (Exception e) {
            logger.error("Toast error NOT displayed");
            return false;
        }
    }

    public void inputName(String text) { type(inputName, text); }
    public void inputTime(String text) { type(inputTime, text); }
    public void inputServing(String text) { type(inputServing, text); }
    public void inputStep1(String text) { type(inputStep1, text); }
    public void inputStep2(String text) { type(inputStep2, text); }
    public void inputStep3(String text) { type(inputStep3, text); }
    public void inputStep4(String text) { type(inputStep4, text); }
    public void inputStep5(String text) { type(inputStep5, text); }
    public void clickAddStep() { click(btnAddStep); }
    public void clickAddIngredient() { click(btnAddIngredient); }

    @Step("Slow swipe down on screen (times={times})")
    public void slowSwipeDownOnScreen(int times) {
        AllureHelper.stepWithParam("Slow swipe down on screen", String.valueOf(times));
        logStep("Chạm giữ nhẹ và lướt xuống từ từ để biểu diễn danh sách. times=" + times);

        Dimension size = getDriver().manage().window().getSize();

        int x = size.width / 2;
        int startY = (int) (size.height * 0.6);
        int endY = (int) (size.height * 0.4);

        for (int i = 1; i <= times; i++) {
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 1);

            swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, startY));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            // “từ từ”: 900–1200ms là ổn
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), x, endY));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            getDriver().perform(Collections.singletonList(swipe));


            // nếu chị muốn nhiều ảnh để demo rõ: bật dòng này
            AllureHelper.attachScreenshot("Swipe demo step " + i);
        }
    }

    // ================= AVATAR =================

    public void clickAvatar() {
        click(avatar);
        logger.info("Click avatar");
    }
//    public void switchToListView() {
//        try {
//            if (isDisplayed(btnListView)) {
//                click(btnListView);
//                logger.info("Switched to LIST VIEW");
//                WaitingHelper.sleepSeconds(2);
//            }
//        } catch (Exception e) {
//            logger.warn("List view button not found (maybe already in list view)");
//        }
//    }
    public void chooseImageFromGalleryReal() {

        // 👉 Bước 1: chuyển sang list view
            click(btnListView);
            logger.info("Switched to List View");

        // 👉 Bước 2: chọn ảnh ưu tiên
        try {
            click(image1);
            logger.info("Chọn ảnh 1");
            return;
        } catch (Exception e) {
            logger.warn("Không thấy ảnh 1, thử ảnh 2");
        }

        try {
            click(image2);
            logger.info("Chọn ảnh 2");
            return;
        } catch (Exception e) {
            logger.error("Không tìm thấy ảnh nào");
        }

        throw new RuntimeException("❌ Không chọn được ảnh trong gallery");
    }

    public void chooseImageFromGalleryEmulator(int index) {

        WaitingHelper.sleepSeconds(2);

        if (index == 1) {
            click(imageEmulator_1);
            logger.info("Selected image EMULATOR 1");
        } else {
            click(imageEmulator_2);
            logger.info("Selected image EMULATOR 2");
        }
    }

    // ================= TAG FLOW - ĐÚNG QUY TRÌNH CHỊ MÔ TẢ =================

    // ================= TAG - ACTIONS (đúng quy trình) =================

    // ================= TAG - ACTIONS (đúng quy trình) =================

    @Step("B1: Click Thêm (Tags) ngoài màn Edit")
    public void openAddTagPopup() {
        logStep("B1: Click 'Thêm' tại section Tags (màn Sửa công thức)");

        // ✅ tránh click nhầm "Thêm" ở chỗ khác
        try {
            click(btnAddTagOutside);
        } catch (Exception e) {
            click(btnAddTagOutside_Fallback);
        }

        WaitingHelper.waitForVisible(popupTitle);
        AllureHelper.attachScreenshot("Popup Thêm tag opened");
    }

    @Step("B2: Click vào ô Tên tag")
    public void clickTagNameInput() {
        logStep("B2: Click 'Tên tag' (label) và focus vào EditText");

        // click label giống manual
        try {
            WaitingHelper.waitForClickable(lblTagName);
            click(lblTagName);
        } catch (Exception ignored) {}

        // ✅ đảm bảo focus vào EditText thật
        WaitingHelper.waitForVisible(inputTagEditText);
        click(inputTagEditText);
    }
    @Step("B3: Nhập tag '{keyword}' và bỏ focus")
    public String typeKeywordAndSelectDropdownTag(String keyword, String expectedSelectedText) {

        WaitingHelper.waitForVisible(inputTagEditText);
        WebElement input = getDriver().findElement(inputTagEditText);

        // 👉 nhập text
        input.click();
        try { input.clear(); } catch (Exception ignored) {}
        input.sendKeys(keyword);

        AllureHelper.attachScreenshot("After typing tag");

        // 🔥 FIX CHUẨN THEO YÊU CẦU CHỊ
        // 👉 click vào title "Thêm tag" để mất focus (ẩn dropdown)
        try {
            click(popupTitle);
        } catch (Exception e) {
            // fallback nếu click thường fail → tap tọa độ
            WebElement el = getDriver().findElement(popupTitle);
            int x = el.getLocation().getX() + el.getSize().getWidth() / 2;
            int y = el.getLocation().getY() + el.getSize().getHeight() / 2;
            tapByCoordinates(x, y);
        }

        WaitingHelper.sleepSeconds(1);

        // 👉 trả về chính text đã nhập
        return keyword;
    }

    @Step("B4: Click Thêm trong popup")
    public void clickAddInPopup() {
        logStep("B4: Click 'Thêm' trong popup");
        WaitingHelper.waitForVisible(btnAddInPopup);

        try {
            click(btnAddInPopup);
        } catch (Exception e) {
            WebElement el = getDriver().findElement(btnAddInPopup);
            int x = el.getLocation().getX() + el.getSize().getWidth() / 2;
            int y = el.getLocation().getY() + el.getSize().getHeight() / 2;
            tapByCoordinates(x, y);
        }

        WaitingHelper.sleepSeconds(1);
        AllureHelper.attachScreenshot("After click Add in popup");
    }

    @Step("Verify tag '{tagName}' hiển thị chip trong popup")
    public void verifyTagChipDisplayedInPopup(String tagName) {
        logStep("Verify tag chip in popup: " + tagName);

        WaitingHelper.waitForVisible(tagChipInPopup);
        List<WebElement> chips = getDriver().findElements(tagChipInPopup);

        boolean found = chips.stream().anyMatch(e -> tagName.equalsIgnoreCase(safeText(e)));
        if (!found) {
            AllureHelper.attachScreenshot("Tag chip NOT found in popup");
            throw new AssertionError("❌ Tag chip không hiển thị trong popup: " + tagName);
        }
        AllureHelper.attachScreenshot("Tag chip found in popup");
    }

    @Step("B5: Click Close popup")
    public void closePopup() {
        logStep("B5: Close popup");
        WaitingHelper.waitForClickable(btnClosePopup);
        click(btnClosePopup);
        WaitingHelper.sleepSeconds(1);
        AllureHelper.attachScreenshot("Popup closed");
    }

    @Step("Verify tag '{tagName}' hiển thị ngoài màn Edit (row Tags)")
    public void verifyTagOutside(String tagName) {
        logStep("Verify tag ngoài màn Edit: " + tagName);

        By chip = tagChipOutsideByText(tagName);
        WaitingHelper.waitForVisible(chip);

        if (!isDisplayed(chip)) {
            AllureHelper.attachScreenshot("Tag NOT found outside");
            throw new AssertionError("❌ Không thấy tag ngoài màn Edit: " + tagName);
        }
        AllureHelper.attachScreenshot("Tag found outside");
    }

    @Step("Add 1 tag flow: keyword='{keyword}', expected='{expectedSelectedText}'")
    public String addOneTagFlow(String keyword, String expectedSelectedText) {
        openAddTagPopup();
        clickTagNameInput();

        String tag = typeKeywordAndSelectDropdownTag(keyword, expectedSelectedText);

        clickAddInPopup();
        verifyTagChipDisplayedInPopup(tag);

        closePopup();
        verifyTagOutside(tag);

        logger.info("✅ Add tag flow done: " + tag);
        return tag;
    }

    // ====== utils ======
    private void tapByCoordinates(int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);

        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        getDriver().perform(Collections.singletonList(tap));
    }

    private String safeText(WebElement el) {
        try {
            String t = el.getText();
            if (t == null || t.trim().isEmpty()) t = el.getAttribute("text");
            return t != null ? t.trim() : "";
        } catch (Exception e) {
            return "";
        }
    }

    @Step("Add 1 tag full flow: {tagName}")
    public void addOneTagFullFlow(String tagName) {

        openAddTagPopup();

        clickTagNameInput();

        typeKeywordAndSelectDropdownTag(tagName, tagName);

        clickAddInPopup();

        closePopup();
    }
    @Step("Add multiple tags (mỗi lần 1 popup)")
    public void addMultipleTags(List<String> tags) {

        for (String tag : tags) {

            logger.info("👉 Adding tag: " + tag);

            addOneTagFullFlow(tag);
            AllureHelper.attachScreenshot("All tags is displayed");
            WaitingHelper.sleepSeconds(1); // tránh UI chưa kịp load
        }
    }
}
