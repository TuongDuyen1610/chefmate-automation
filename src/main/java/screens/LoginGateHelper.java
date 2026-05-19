package screens;

import core.base.BaseScreen;
import core.utils.AllureHelper;
import core.utils.WaitingHelper;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import java.time.Duration;
import java.util.Arrays;
import io.qameta.allure.Step;

public class LoginGateHelper extends BaseScreen {

    private final By tabFridge = By.xpath("//android.widget.TextView[@text='Tủ lạnh']");
    private final By tabProfile = By.xpath("//android.widget.TextView[@text='Tài khoản']");
    private final By btnOpenLoginFormFridge = By.xpath("//android.widget.TextView[@text='Đăng nhập']");
    private final By btnOpenLoginFormProfile = By.xpath("//android.widget.TextView[@text='Đăng nhập ngay']");
    public void triggerLoginByFridgeTab() {
        logStep("1. Click vao Tab Tu lanh");
        click(tabFridge);
        AllureHelper.attachScreenshot("Clicked Fridge tab");
        click(btnOpenLoginFormFridge);
    }

    public void triggerLoginByProfileTab() {
        logStep("1. Click vao Tab Tai khoan");
        click(tabProfile);
        AllureHelper.attachScreenshot("Clicked Profile tab");
        click(btnOpenLoginFormProfile);
    }

}