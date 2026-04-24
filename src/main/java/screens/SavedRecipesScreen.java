package screens;

import core.base.BaseScreen;
import core.utils.AllureHelper;
import core.utils.WaitingHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SavedRecipesScreen extends BaseScreen {

    // ===== LOCATOR =====
    private final By header = By.xpath("//android.widget.TextView[@text='Kho công thức']");
    private final By btnBack = By.xpath("//android.view.View[@content-desc='Quay lại']");

    private final By recipeItems =
            By.xpath("//android.view.View[.//android.widget.TextView]");

    private final By title = By.xpath(".//android.widget.TextView[1]");
    private final By author = By.xpath(".//android.widget.TextView[2]");

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
            WaitingHelper.sleepSeconds(1);
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

        logStep("Click nút quay lại (UI)");

        WaitingHelper.waitForClickable(btnBack);
        click(btnBack);

        WaitingHelper.sleepSeconds(1);
    }
    public String getTitleAt(int index) {

        List<WebElement> items = getDriver().findElements(recipeItems);

        List<WebElement> validItems = new java.util.ArrayList<>();

        for (WebElement item : items) {
            try {
                String text = item.findElement(title).getText();

                // ❌ bỏ header
                if (text.equalsIgnoreCase("Kho công thức")) continue;

                // ❌ bỏ item rỗng
                if (text.trim().isEmpty()) continue;

                validItems.add(item);

            } catch (Exception ignored) {}
        }

        if (index >= validItems.size()) {
            throw new RuntimeException("❌ Index vượt quá size: " + index);
        }

        String result = validItems.get(index)
                .findElement(title)
                .getText();

        System.out.println("👉 TITLE[" + index + "] = " + result);

        return result;
    }
}