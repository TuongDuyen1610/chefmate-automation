package screens;

import core.base.BaseScreen;
import core.utils.WaitingHelper;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import java.time.Duration;
import java.util.Arrays;

public class LoginGateHelper extends BaseScreen {

    private final By tabFridge = By.xpath("//android.widget.TextView[@text='Tủ lạnh']");
    private final By tabProfile = By.xpath("//android.widget.TextView[@text='Tài khoản']");
    private final By btnOpenLoginFormFridge = By.xpath("//android.view.View[2]/android.view.View/android.widget.Button");
    private final By btnOpenLoginFormProfile = By.xpath("//android.widget.TextView[@text='Đăng nhập ngay']");
    public void triggerLoginByFridgeTab() {
        logStep("1. Click vao Tab Tu lanh");
        WaitingHelper.waitForClickable(tabFridge);
        click(tabFridge);
//        WaitingHelper.sleepSeconds(2);
        WaitingHelper.waitForClickable(btnOpenLoginFormFridge);
        click(btnOpenLoginFormFridge);
//        WaitingHelper.sleepSeconds(2);
    }

    public void triggerLoginByProfileTab() {
        logStep("1. Click vao Tab Tai khoan");
        WaitingHelper.waitForClickable(tabProfile);
        click(tabProfile);
//        WaitingHelper.sleepSeconds(1);
        WaitingHelper.waitForClickable(btnOpenLoginFormProfile);
        click(btnOpenLoginFormProfile);
//        WaitingHelper.sleepSeconds(2);
    }

}