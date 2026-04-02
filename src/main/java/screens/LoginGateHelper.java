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

    // 1. Locators Tủ lạnh né lỗi font chữ
    private final By tabFridge = AppiumBy.androidUIAutomator("new UiSelector().descriptionContains(\"lạnh\")");
    private final By tvFridgeTitle = By.xpath("//android.widget.TextView[contains(@text, 'lạnh')]");

    // 2. Locators Tài khoản né lỗi font chữ
    private final By tabProfile = AppiumBy.androidUIAutomator("new UiSelector().descriptionContains(\"khoản\")");
    private final By tvProfileTitle = By.xpath("//android.widget.TextView[contains(@text, 'khoản')]");


    // XPath Tủ lạnh rút gọn dựa trên cấu trúc chị gửi: //.../android.view.View[2]/android.view.View/android.widget.Button
    private final By btnOpenLoginFormFridge = By.xpath("//android.view.View[2]/android.view.View/android.widget.Button");

    // XPath Tài khoán rút gọn dựa trên cấu trúc chị gửi: //.../android.view.View[2]/android.view.View/android.widget.Button
    private final By btnOpenLoginFormProfile = By.xpath("//android.widget.TextView[@text='Đăng nhập ngay']");
    public void triggerLoginByFridgeTab() {
        logStep("1. Click vao Tab Tu lanh");
        WaitingHelper.waitForClickable(tabFridge);
        click(tabFridge);

        logStep("2. Cho man hinh 'Tu lanh ca nhan' hien thi");
        WaitingHelper.waitForVisible(tvFridgeTitle);
        WaitingHelper.sleepSeconds(2); // Đợi popup render xong

        logStep("3. Thuc hien Click nut Đang nhap (Ket hop XPath & Bounds)");

//        try {
//            // LỚP 1: Thử click bằng XPath rút gọn trước
            WaitingHelper.waitForClickable(btnOpenLoginFormFridge);
            click(btnOpenLoginFormFridge);
            logStep("-> Click thanh cong bang XPath!");
//        } catch (Exception e) {
//            logStep("-> XPath that bai hoac loi Font. Chuyen sang LOP 2: Click theo Bounds [330,1277][632,1382]");
//            clickByCoordinates(481, 1329); // Tâm của nút dựa trên Bounds chị đưa
//        }

        WaitingHelper.sleepSeconds(2);
    }

    public void triggerLoginByProfileTab() {
        logStep("1. Click vao Tab Tai khoan");
        WaitingHelper.waitForClickable(tabProfile);
        click(tabProfile);

        logStep("2. Cho man hinh 'Tu lanh ca nhan' hien thi");
        WaitingHelper.waitForVisible(tvProfileTitle);
        WaitingHelper.sleepSeconds(2); // Đợi popup render xong

        logStep("3. Thuc hien Click nut Đang nhap (Ket hop XPath & Bounds)");


        WaitingHelper.waitForClickable(btnOpenLoginFormProfile);
        click(btnOpenLoginFormProfile);
        logStep("-> Click thanh cong bang XPath!");


        WaitingHelper.sleepSeconds(2);
    }

    /**
     * Hàm hỗ trợ Click theo tọa độ (Dùng chuẩn W3C Actions cho Appium mới nhất)
     */
    private void clickByCoordinates(int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        getDriver().perform(Arrays.asList(tap));
        logStep("-> ĐA thUc hien Tap vao toa đo: [" + x + ", " + y + "]");
    }
}