package smoke;

import core.base.BaseTest;
import flows.AuthenticationFlow;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private final AuthenticationFlow authFlow = new AuthenticationFlow();

    // ==================== HAPPY CASE: PHONE ====================

    @Test(priority = 1, description = "DangNhap_TC_01 - Đăng nhập Tủ lạnh + SĐT")
    public void DangNhap_TC_01_LoginFridgePhone() {
        System.out.println("\n=== DangNhap_TC_01 - ĐĂNG NHẬP TỦ LẠNH + SĐT ===");
        authFlow.loginFromFridgeTab("0000000000", "000000");
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Lỗi: Không vào được Home!");
        System.out.println("✅ DangNhap_TC_01 PASS");
    }

    @Test(priority = 2, description = "DangNhap_TC_02 - Đăng nhập Tủ lạnh + Email")
    public void DangNhap_TC_02_LoginFridgeEmail() {
        System.out.println("\n=== DangNhap_TC_02 - ĐĂNG NHẬP TỦ LẠNH + EMAIL ===");
        authFlow.loginFromFridgeTab("00000@gmail.com", "000000");
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Lỗi: Không vào được Home!");
        System.out.println("✅ DangNhap_TC_02 PASS");
    }

    @Test(priority = 3, description = "DangNhap_TC_03 - Đăng nhập Tài khoản + SĐT")
    public void DangNhap_TC_03_LoginProfilePhone() {
        System.out.println("\n=== DangNhap_TC_03 - ĐĂNG NHẬP TÀI KHOẢN + SĐT ===");
        authFlow.loginFromProfileTab("0000000000", "000000");
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Lỗi: Không vào được Home!");
        System.out.println("✅ DangNhap_TC_03 PASS");
    }

    @Test(priority = 4, description = "DangNhap_TC_04 - Đăng nhập Tài khoản + Email")
    public void DangNhap_TC_04_LoginProfileEmail() {
        System.out.println("\n=== DangNhap_TC_04 - ĐĂNG NHẬP TÀI KHOẢN + EMAIL ===");
        authFlow.loginFromProfileTab("00000@gmail.com", "000000");
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Lỗi: Không vào được Home!");
        System.out.println("✅ DangNhap_TC_04 PASS");
    }

    @Test(priority = 5, description = "DangNhap_TC_05 - Đăng nhập Bepes + SĐT")
    public void DangNhap_TC_05_LoginBepesPhone() {
        System.out.println("\n=== DangNhap_TC_05 - ĐĂNG NHẬP BEPES + SĐT ===");
        authFlow.loginFromBepesAIButton("0000000000", "000000");
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Lỗi: Không vào được Home!");
        System.out.println("✅ DangNhap_TC_05 PASS");
    }

    @Test(priority = 6, description = "DangNhap_TC_06 - Đăng nhập Bepes + Email")
    public void DangNhap_TC_06_LoginBepesEmail() {
        System.out.println("\n=== DangNhap_TC_06 - ĐĂNG NHẬP BEPES + EMAIL ===");
        authFlow.loginFromBepesAIButton("00000@gmail.com", "000000");
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Lỗi: Không vào được Home!");
        System.out.println("✅ DangNhap_TC_06 PASS");
    }

    @Test(priority = 7, description = "DangNhap_TC_07 - Đăng nhập Gợi ý + SĐT")
    public void DangNhap_TC_07_LoginSuggestionPhone() {
        System.out.println("\n=== DangNhap_TC_07 - ĐĂNG NHẬP GỢI Ý + SĐT ===");
        authFlow.loginFromFridgeSuggestionButton("0000000000", "000000");
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Lỗi: Không vào được Home!");
        System.out.println("✅ DangNhap_TC_07 PASS");
    }

    @Test(priority = 8, description = "DangNhap_TC_08 - Đăng nhập Gợi ý + Email")
    public void DangNhap_TC_08_LoginSuggestionEmail() {
        System.out.println("\n=== DangNhap_TC_08 - ĐĂNG NHẬP GỢI Ý + EMAIL ===");
        authFlow.loginFromFridgeSuggestionButton("00000@gmail.com", "000000");
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Lỗi: Không vào được Home!");
        System.out.println("✅ DangNhap_TC_08 PASS");
    }

    // ==================== UNHAPPY CASE ====================

    @Test(priority = 9, description = "DangNhap_TC_09 - Sai tài khoản/mật khẩu")
    public void DangNhap_TC_09_LoginInvalidCredentials() {
        System.out.println("\n=== DangNhap_TC_09 - SAI TÀI KHOẢN/MẬT KHẨU ===");
        authFlow.loginFromFridgeTab("wrongaccount@gmail.com", "wrongpassword");
        Assert.assertTrue(authFlow.isLoginScreenStillDisplayed(), "Lỗi: Phải ở lại màn Login!");
        System.out.println("✅ DangNhap_TC_09 PASS");
    }

    @Test(priority = 10, description = "DangNhap_TC_10 - Để trống SĐT/Email")
    public void DangNhap_TC_10_LoginEmptyPhone() {
        System.out.println("\n=== DangNhap_TC_10 - ĐỂ TRỐNG SĐT/EMAIL ===");
        System.out.println("⏳ TC10 - Chưa implement (cần thêm logic để trống field)");
    }

    @Test(priority = 11, description = "DangNhap_TC_11 - Để trống Mật khẩu")
    public void DangNhap_TC_11_LoginEmptyPassword() {
        System.out.println("\n=== DangNhap_TC_11 - ĐỂ TRỐNG MẬT KHẨU ===");
        System.out.println("⏳ TC11 - Chưa implement (cần thêm logic để trống field)");
    }
}