package screens;

import core.base.BaseScreen;
import core.utils.AllureHelper;
import core.utils.WaitingHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class SavedRecipesScreen extends BaseScreen {

    // ===== LOCATOR =====
    private final By header = By.xpath("//android.widget.TextView[@text='Kho công thức']");
    private final By btnBack = By.xpath("//android.view.View[@content-desc='Quay lại']");

    private final By recipeItems =
            By.xpath("//z0.h0/android.view.View/android.view.View[2]/android.view.View[1]");

    private final By title = By.xpath(".//android.widget.TextView[1]");
    private final By author = By.xpath(".//android.widget.TextView[2]");

    // ===== DELETE LOCATORS =====

    // 👉 Nút "Xóa" trong từng item
    private final By btnDeleteInItem =
            By.xpath(".//android.widget.TextView[@text='Xóa']");

    // 👉 Popup title
    private final By popupDeleteTitle =
            By.xpath("//android.widget.TextView[@text='Xóa công thức']");

    // 👉 Nút confirm Xóa (trong popup)
    private final By btnConfirmDelete =
            By.xpath("//android.widget.TextView[@text='Xóa']");

    // 👉 Nút cancel (icon X)
    private final By btnCancelDelete =
            By.xpath("//android.view.View[@content-desc='cancel']");

    private final By btnEdit =
            By.xpath(".//android.widget.TextView[@text='Sửa']");


    public void clickEditAt(int index) {

        List<WebElement> items = getDriver().findElements(recipeItems);

        WebElement item = items.get(index);

        item.findElement(btnEdit).click();
    }
    public boolean isDeletePopupDisplayed() {
        return isDisplayed(popupDeleteTitle);
    }

    public void confirmDelete() {

        logStep("Confirm Xóa công thức");

        WaitingHelper.waitForClickable(btnConfirmDelete);
        click(btnConfirmDelete);

        AllureHelper.attachScreenshot("CONFIRM DELETE");
    }

    public void cancelDelete() {

        logStep("Cancel Xóa công thức");

        WaitingHelper.waitForClickable(btnCancelDelete);
        click(btnCancelDelete);

        AllureHelper.attachScreenshot("CANCEL DELETE");
    }
    public void clickDeleteAt(int index) {

        logStep("Click Xóa tại item index: " + index);

        List<WebElement> items = getDriver().findElements(recipeItems);

        WebElement item = items.get(index);

        WebElement deleteBtn = item.findElement(btnDeleteInItem);

        deleteBtn.click();

        AllureHelper.attachScreenshot("CLICK DELETE ITEM " + index);
    }
    // ===== VERIFY =====

    public boolean isDisplayedScreen() {
        return isDisplayed(header);
    }

    public int getTotalRecipes() {
        return getDriver().findElements(recipeItems).size();
    }

    public boolean isRecipeExist(String expectedTitle, String expectedAuthor) {

        logStep("Verify recipe exist: " + expectedTitle);

        String expected = normalizeText(expectedTitle);

        long end = System.currentTimeMillis() + 20000;

        while (System.currentTimeMillis() < end) {

            List<WebElement> items = getDriver().findElements(recipeItems);

            for (WebElement item : items) {
                try {
                    String t = normalizeText(item.findElement(title).getText());

                    if (t.contains(expected)) {
                        AllureHelper.attachScreenshot("FOUND: " + t);

                        System.out.println("EXPECT: " + expected);
                        System.out.println("FOUND: " + t);
                        return true;
                    }

                } catch (Exception ignored) {}
            }

            scrollDown();

        }

        AllureHelper.attachScreenshot("NOT FOUND: " + expectedTitle);
        return false;
    }
    private String normalizeText(String text) {
        return java.text.Normalizer
                .normalize(text, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase()
                .trim();
    }
    public boolean isNewestOnTop(String expectedTitle) {
        try {
            WebElement first = getDriver().findElements(recipeItems).get(0);
            String topTitle = first.findElement(title).getText();

            return topTitle.equalsIgnoreCase(expectedTitle);

        } catch (Exception e) {
            return false;
        }
    }
    public void clickBack() {
        click(btnBack);
    }
    public String getTitleAt(int index) {

        List<WebElement> items = getDriver().findElements(recipeItems);

        List<WebElement> validItems = new java.util.ArrayList<>();

        for (WebElement item : items) {
            try {
                validItems.add(item);

            } catch (Exception ignored) {}
        }

        if (index >= validItems.size()) {
            throw new RuntimeException("❌ Index vượt quá size: " + index);
        }
        String result = validItems.get(index)
                .getText();

        System.out.println("👉 TITLE[" + index + "] = " + result);
        AllureHelper.attachScreenshot("Kho công thức hiển thị");
        return result;
    }
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
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(400), PointerInput.Origin.viewport(), x, endY));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            getDriver().perform(Collections.singletonList(swipe));


            // nếu chị muốn nhiều ảnh để demo rõ: bật dòng này
//            AllureHelper.attachScreenshot("Swipe demo step " + i);
        }
    }
}