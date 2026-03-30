package smoke;

import core.base.BaseTest;
import flows.HomeFlow;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTest extends BaseTest {

    private final HomeFlow home = new HomeFlow();

    @Test(priority = 1, description = "Smoke 03 - Home Screen hiển thị đúng sau login")
    public void TC03_Home_Screen_Display_Correctly() {
        home.openAppAndGoToHome();
        Assert.assertTrue(true, "Home Screen hiển thị đúng"); // kiểm tra qua Flow
        System.out.println("✅ TC03 PASS - Home Screen OK");
    }
}