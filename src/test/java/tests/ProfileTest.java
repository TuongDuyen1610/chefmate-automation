package tests;

import core.base.BaseTest;
import flows.ProfileFlow;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BaseTest {

    private final ProfileFlow profile = new ProfileFlow();

    @Test(priority = 1, description = "Smoke 08 - Mở Trang cá nhân")
    public void TC08_Profile_Screen_Success() {
        profile.openProfile();
        Assert.assertTrue(profile.isProfileDisplayed(), "Không vào được Profile");
        System.out.println("✅ TC08 PASS - Profile OK");
    }
}