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
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.text.Normalizer;
import java.util.Collections;
import java.util.Locale;
import java.util.regex.Pattern;
/**
 * RecipeDetailScreen.java
 * ✅ Sử dụng getDriver() từ BaseScreen
 */
public class RecipeDetailScreen extends BaseScreen {
    private static final Logger logger = LoggerFactory.getLogger(RecipeDetailScreen.class);
    private static final Pattern DIACRITICS_PATTERN =
            Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    // ==================== LOCATORS ====================

    private final By recipeTitle = By.xpath("//android.widget.TextView[1]");
    private final By recipeAuthor =
            By.xpath("//android.widget.TextView[1]/following-sibling::android.widget.TextView[1]");
    private final By recipeTimeAgo =
            By.xpath("//android.widget.TextView[1]/following-sibling::android.widget.TextView[2]");
    private final By btnFollow = By.xpath("//android.widget.TextView[@text='Theo dõi']");

    private final By likeCount = By.xpath(
            "//android.view.View[@content-desc='Like']/parent::android.view.View//android.widget.TextView"
    );
    private final By commentCount = By.xpath("(//android.widget.TextView)[position()=last()-2]");
    private final By saveCount = By.xpath("(//android.widget.TextView)[position()=last()-1]");
    private final By cookTime = By.xpath("//android.widget.TextView[contains(@text, 'phút')]");

    private final By tagsLabel = By.xpath("//android.widget.TextView[@text='Tags: ']");
    private final By tagsContainer = By.xpath(
            "//android.widget.TextView[@text='Tags: ']/following-sibling::android.widget.HorizontalScrollView"
    );
    private final By tagItems = By.xpath(
            "//android.widget.TextView[@text='Tags: ']/following-sibling::android.widget.HorizontalScrollView//android.widget.TextView"
    );
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

    // ===== LOCATORS SaveRecipe =====
    private final By btnSave = By.xpath("//android.view.View[@content-desc='Mark']");
    private final By toastSaved = By.xpath("//android.widget.Toast[@text='Đã lưu']");
    private final By btnBack = By.xpath("//android.view.View[@content-desc='Quay lại']");
    private final By btnLike = By.xpath("//android.view.View[@content-desc='Like']");
    private final By btnShare = By.xpath("//android.view.View[@content-desc='Share']");

    // ===== COMMENT LOCATOR =====
    private final By inputComment = By.xpath("//android.widget.EditText");
    private final By btnSendComment = By.xpath("//android.view.View[@content-desc='Send']");

    // username của comment (optional check)
    private final By commentUser = By.xpath("(//android.widget.TextView)[last()-2]");
    private final By commentTime = By.xpath("(//android.widget.TextView)[last()-1]");

    // ===== SHARE TARGET =====
    private final By shareSheetTitle =
            By.xpath("//android.widget.TextView[@text='Chia sẻ văn bản']");

    private final By shareMessenger =
            By.xpath("//android.widget.LinearLayout[contains(@content-desc,'Messenger')]");

    private final By shareZalo =
            By.xpath("//android.widget.LinearLayout[contains(@content-desc,'Zalo')]");

    private final By shareGmail =
            By.xpath("//android.widget.LinearLayout[contains(@content-desc,'Gmail')]");

    // ===== SHARE Messenger (Facebook) =====
    private final By fbSearchBtn =
            By.xpath("//android.widget.Button[@content-desc='Tìm kiếm']");

    private final By fbSearchInput =
            By.xpath("//android.widget.AutoCompleteTextView");

    private final By fbResultUser =
            By.xpath("//android.widget.AutoCompleteTextView[@text='Tưởng Duyên']");

    private final By fbSendBtn =
            By.xpath("(//android.view.ViewGroup[@content-desc='GỬI'])[1]");

    private final By fbSentStatus =
            By.xpath("//android.view.ViewGroup[@content-desc='Đã gửi']");

    // ===== SHARE Zalo =====
    private final By toastShared =
            By.xpath("//android.widget.Toast[@text='Đã chia sẻ']");

    // ===== SHARE Gmail =====
    private final By gmailSendBtn =
            By.id("com.google.android.gm:id/send");
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
    private String normalize(String s) {
        if (s == null) return "";

        String out = s.trim().toLowerCase(Locale.ROOT);
        out = out.replace('đ', 'd').replace('Đ', 'd');

        out = Normalizer.normalize(out, Normalizer.Form.NFD);
        out = DIACRITICS_PATTERN.matcher(out).replaceAll("");

        out = out.replaceAll("\\s+", " ").trim();
        return out;
    }
    @Step("Verify tag keyword with horizontal scroll: {keyword}")
    public boolean verifyTagWithScroll(String keyword) {

        waitForRecipeDetailDisplayed();

        if (getDriver().findElements(tagsContainer).isEmpty()) {
            logger.warn("⚠️ Không thấy vùng TAG");
            AllureHelper.attachScreenshot("NO TAG CONTAINER");
            return false;
        }

        String normalizedKeyword = normalize(keyword);

        AllureHelper.attachScreenshot("TAG START | " + keyword);

        int maxScroll = 10;

        for (int i = 0; i < maxScroll; i++) {

            AllureHelper.attachScreenshot("TAG SCAN STEP " + i);

            // ❗ TẮT WAIT
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

            List<WebElement> tags = getDriver().findElements(tagItems);

            for (WebElement tag : tags) {
                String text = tag.getText();

                if (normalize(text).contains(normalizedKeyword)) {

                    AllureHelper.attachScreenshot("MATCH TAG | " + keyword + " | " + text);

                    // 👉 bật lại wait trước khi return
                    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

                    return true;
                }
            }

            // 👉 bật lại wait sau scan
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            swipeTagInsideContainer();

            AllureHelper.attachScreenshot("AFTER SWIPE STEP " + i);

            WaitingHelper.sleepSeconds(0);
        }

        logger.warn("⚠️ Không match tag → ACCEPT");
        AllureHelper.attachScreenshot("NO MATCH TAG | " + keyword);

        return false;
    }
    private void swipeTagInsideContainer() {

        try {
            WebElement container = getDriver().findElement(tagsContainer);

            int startX = container.getLocation().getX() + (int)(container.getSize().width * 0.85);
            int endX   = container.getLocation().getX() + (int)(container.getSize().width * 0.15);

            int y = container.getLocation().getY() + (container.getSize().height / 2);

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 1);

            swipe.addAction(finger.createPointerMove(Duration.ZERO,
                    PointerInput.Origin.viewport(), startX, y));

            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

            swipe.addAction(finger.createPointerMove(Duration.ofMillis(800),
                    PointerInput.Origin.viewport(), endX, y));

            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            getDriver().perform(Collections.singletonList(swipe));

            logger.info("👉 Swipe TAG thành công");

        } catch (Exception e) {
            logger.error("❌ Swipe TAG lỗi", e);
        }
    }
    @Step("Wait Recipe Detail Screen")
    public void waitForLoaded() {
        WaitingHelper.waitForVisible(btnBack);
    }
    public boolean isToastDisplayed(By toastLocator, int timeoutSeconds) {
        logStep("Verify Toast hiển thị");

        long endTime = System.currentTimeMillis() + timeoutSeconds * 1000;

        while (System.currentTimeMillis() < endTime) {
            try {
                if (getDriver().findElement(toastLocator).isDisplayed()) {
                    logger.info("✅ Toast displayed");
                    AllureHelper.attachScreenshot("Toast Displayed");
                    return true;
                }
            } catch (Exception ignored) {}

            WaitingHelper.sleepSeconds(1);
        }

        logger.error("❌ Toast NOT displayed");
        AllureHelper.attachScreenshot("Toast NOT Found");
        return false;
    }
    @Step("Save recipe (double click to trigger toast)")
    public boolean clickSaveAndVerifyToast() {

        logStep("Click Save 2 lần + bắt toast theo kiểu ổn định");

        try {
            // click lần 1
            click(btnSave);

            // delay NGẮN để UI update state
            WaitingHelper.sleepSeconds(1);

            // click lần 2 để trigger toast
            click(btnSave);

            // 🔥 QUAN TRỌNG: delay NGẮN để toast kịp render
            WaitingHelper.sleepSeconds(1);

            // ✅ DÙNG CÁCH CŨ CỦA CHỊ (ổn định hơn polling)
            getDriver().findElement(toastSaved);

            logger.info("✅ Toast displayed");
            AllureHelper.attachScreenshot("TOAST: Đã lưu");

            return true;

        } catch (Exception e) {
            logger.error("❌ Toast NOT displayed: " + e.getMessage());
            AllureHelper.attachScreenshot("TOAST NOT FOUND");
            return false;
        }
    }
    public void clickBack() {
        click(btnBack);
    }
    @Step("Click Back")
    public void clickBackToHome() {

        logStep("Back về Home bằng UI button");

        clickBack(); // Detail -> Search
        clickBack(); // Search -> Home

    }
    public void clickLike(){
        click(btnLike);
    }
    public int getLikeCount() {
        try {
            String text = getDriver().findElement(likeCount).getText();

            System.out.println("LIKE TEXT: " + text);

            return Integer.parseInt(text.replaceAll("[^0-9]", ""));
        } catch (Exception e) {
            return 0;
        }
    }
    public boolean isLiked() {
        try {
            String attr = getDriver().findElement(btnLike).getAttribute("selected");
            return attr != null && attr.equals("true");
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Enter comment: {text}")
    public void enterComment(String text) {
        logStep("Nhập comment: " + text);
        try {
            WebElement input = getDriver().findElement(inputComment);
            input.clear();
            input.sendKeys(text);
        } catch (Exception e) {
            throw new RuntimeException("Cannot input comment", e);
        }
    }

    @Step("Click send comment")
    public void clickSendComment() {
        logStep("Click gửi comment");
        click(btnSendComment);
        WaitingHelper.sleepSeconds(1); // đợi UI render
    }
    public boolean isCommentDisplayed(String text) {
        try {
            WaitingHelper.waitForVisible(commentContentContains(text));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private By commentContentContains(String text) {
        return By.xpath("//android.widget.TextView[contains(@text,'" + text + "')]");
    }

    @Step("Verify login required message displayed")
    public boolean isLoginRequiredMessageDisplayed() {
        try {
            return isDisplayed(
                    By.xpath("//android.widget.TextView[@text='Vui lòng đăng nhập để bình luận công thức này.']")
            );
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Click Share button")
    public void clickShare() {
        logStep("Click Share");
        click(btnShare);
    }
    public boolean isShareOptionsDisplayed() {
        return isDisplayed(shareSheetTitle);
    }

    @Step("Share via Messenger (search + send + verify sent)")
    public boolean shareViaMessengerFullFlow() {

        try {
            click(shareMessenger);
            WaitingHelper.sleepSeconds(3);

            // 👉 click search
            click(fbSearchBtn);
            WaitingHelper.sleepSeconds(1);

            // 👉 nhập tên
            WebElement input = getDriver().findElement(fbSearchInput);
            input.sendKeys("Tưởng Duyên");
            WaitingHelper.sleepSeconds(2);

            // 👉 chọn user
            click(fbResultUser);
            WaitingHelper.sleepSeconds(1);

            // 👉 click gửi
            click(fbSendBtn);

            // 👉 đợi status "Đã gửi"
            for (int i = 0; i < 5; i++) {
                if (isDisplayed(fbSentStatus)) {
                    AllureHelper.attachScreenshot("MESSENGER SENT SUCCESS");
                    return true;
                }
                WaitingHelper.sleepSeconds(1);
            }

            return false;

        } catch (Exception e) {
            AllureHelper.attachErrorMessage("Messenger share failed: " + e.getMessage());
            return false;
        }
    }

    @Step("Share via Zalo (click + verify toast)")
    public boolean shareViaZaloQuick() {
        try {
            click(shareZalo);
            // 👉 Zalo auto gửi → bắt toast
            getDriver().findElement(toastShared);
            logger.info("Toast success displayed");
            AllureHelper.attachScreenshot("ZALO SHARE SUCCESS");
            return true;

        } catch (Exception e) {
            AllureHelper.attachErrorMessage("Zalo share failed: " + e.getMessage());
            return false;
        }
    }

    @Step("Share via Gmail (open + send)")
    public boolean shareViaGmailFlow() {

        try {
            click(shareGmail);
            WaitingHelper.sleepSeconds(2);

            // 👉 verify vào compose
            if (!isDisplayed(gmailSendBtn)) {
                return false;
            }
            // 🔥 CHỤP MÀN HÌNH TRƯỚC KHI GỬI
            AllureHelper.attachScreenshot("GMAIL COMPOSE SCREEN");
            // 👉 click gửi
            click(gmailSendBtn);

            AllureHelper.attachScreenshot("GMAIL SEND SUCCESS");

            return true;

        } catch (Exception e) {
            AllureHelper.attachErrorMessage("Gmail share failed: " + e.getMessage());
            return false;
        }
    }

}