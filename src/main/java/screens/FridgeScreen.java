package screens;

import core.base.BaseScreen;
import core.utils.AllureHelper;
import core.utils.WaitingHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class FridgeScreen extends BaseScreen {

    // ===== Fridge UI =====
    private final By btnNavigateFridge = By.xpath("//android.widget.TextView[@text='Tủ lạnh']");
    private final By header = By.xpath("//android.widget.TextView[@text='Tủ lạnh cá nhân']");
    private final By headerNotLogin = By.xpath("//android.widget.TextView[@text='Đăng nhập']");

    // ===== MANUAL ADD SHEET =====
    private final By sheet = By.xpath("//android.view.View[@content-desc='Thêm']");
    private final By sheetHeader = By.xpath("//android.widget.TextView[@text='Thêm nguyên liệu']");

    private final By ingredientName = By.xpath("//android.widget.ScrollView/android.widget.EditText[1]");
    private final By number = By.xpath("//android.widget.ScrollView/android.widget.EditText[2]");
    private final By donVi = By.xpath("//android.widget.ScrollView/android.widget.EditText[3]");
    private final By expiryDate = By.xpath("//android.widget.ScrollView/android.widget.EditText[4]");

    // ===== COMPLETE =====
    private final By sheetBtnSave = By.xpath("//android.widget.TextView[@text='Lưu']");
    private final By sheetBtnCancle = By.xpath("//android.widget.TextView[@text='Hủy']");

    @Step("Open FridgeScreen")
    public void openFridgeScreen() {
        click(btnNavigateFridge);
        WaitingHelper.sleepSeconds(1);
        AllureHelper.attachScreenshot("Fridge Screen opened");
    }
    // ================= VERIFY =================
    @Step("Verify initial Fridge UI")
    public void verifyInitialUI() {
        WaitingHelper.waitForVisible(header);
        AllureHelper.attachScreenshot("Fridge initial UI OK");
    }
    // ================= MANUAL ADD =================
    @Step("Open manual add sheet")
    public void openManualAddSheet() {
        click(sheet);
        WaitingHelper.waitForVisible(sheetHeader);
        WaitingHelper.waitForVisible(sheetBtnCancle);
        WaitingHelper.waitForVisible(sheetBtnSave);
        AllureHelper.attachScreenshot("Manual sheet opened");
    }
    public boolean verifyScreenNotLogin() {
        logStep("Verify Screen Not Login ");
        try {
            WaitingHelper.waitForVisible(headerNotLogin);
            return isDisplayed(headerNotLogin);
        } catch (Exception e) {
            logStep("Screen display");
            return false;
        }
    }
    public boolean verifySheetAdd() {
        logStep("Verify Sheet Add Ingredient");
        try {
            WaitingHelper.waitForVisible(sheetHeader);
            return isDisplayed(sheetHeader);
        } catch (Exception e) {
            logStep("Sheet not display");
            return false;
        }
    }
    @Step("Close manual add sheet")
    public void cancleManualAddSheet() {
        click(sheetBtnCancle);
        WaitingHelper.sleepSeconds(1);
        AllureHelper.attachScreenshot("Manual sheet closed");
    }
    @Step("Add manual ingredient: name={name}, qty={qty}, unit={unit}")
    public void addManualIngredient(String name, String qty, String unit, String dueDate) {
        openManualAddSheet();

        type(ingredientName, name);
        try { getDriver().hideKeyboard(); } catch (Exception ignored) {}

        type(number, qty);
        try { getDriver().hideKeyboard(); } catch (Exception ignored) {}

        type(donVi, unit);
        try { getDriver().hideKeyboard(); } catch (Exception ignored) {}

        type(expiryDate, dueDate);
        try { getDriver().hideKeyboard(); } catch (Exception ignored) {}

        click(sheetBtnSave);
        WaitingHelper.sleepSeconds(1);

        AllureHelper.attachScreenshot("Manual ingredient added");
    }
}