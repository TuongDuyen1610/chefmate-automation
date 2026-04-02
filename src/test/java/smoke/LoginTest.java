package smoke;

import core.base.BaseTest;
import flows.AuthenticationFlow;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private final AuthenticationFlow authFlow = new AuthenticationFlow();

    @Test(priority = 1, description = "TC01 - Đăng nhập từ Tủ lạnh")
    public void TC01_Login_FromFridge() {
        System.out.println("\n=== TC01 - DANG NHAP TU TU LANH ===");
        authFlow.loginFromFridgeTab("0000000000", "000000");
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Lỗi: Không vào được Home!");
        System.out.println("✅ TC01 PASS");
    }

    @Test(priority = 2, description = "TC02 - Đăng nhập từ Tài khoản")
    public void TC02_Login_FromProfile() {
        System.out.println("\n=== TC02 - DANG NHAP TU TAI KHOAN ===");
        authFlow.loginFromProfileTab("0000000000", "000000");
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Lỗi: Không vào được Home!");
        System.out.println("✅ TC02 PASS");
    }

    @Test(priority = 3, description = "TC03 - Đăng nhập từ Bepes AI")
    public void TC03_Login_FromBepesAI() {
        System.out.println("\n=== TC03 - DANG NHAP TU BEPES AI ===");
        authFlow.loginFromBepesAIButton("0000000000", "000000");
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Lỗi: Không vào được Home!");
        System.out.println("✅ TC03 PASS");
    }

    @Test(priority = 4, description = "TC04 - Đăng nhập từ Gợi ý Tủ lạnh")
    public void TC04_Login_FromFridgeSuggestion() {
        System.out.println("\n=== TC04 - DANG NHAP TU GOI Y TU LANH ===");
        authFlow.loginFromFridgeSuggestionButton("0000000000", "000000");
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Lỗi: Không vào được Home!");
        System.out.println("✅ TC04 PASS");
    }

    @Test(priority = 5, description = "TC05 - Đăng nhập sai mật khẩu")
    public void TC05_Login_Failed() {
        System.out.println("\n=== TC05 - DANG NHAP SAI MAT KHAU ===");
        authFlow.loginFromFridgeTab("0000000000", "WrongPassword123");
        Assert.assertTrue(authFlow.isLoginScreenStillDisplayed(), "Lỗi: Phải ở lại màn Login!");
        System.out.println("✅ TC05 PASS");
    }
}