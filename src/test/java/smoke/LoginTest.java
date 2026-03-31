package smoke;

import core.base.BaseTest;
import flows.AuthenticationFlow;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private final AuthenticationFlow authFlow = new AuthenticationFlow();

    @Test(priority = 1, description = "TC01 - Đăng nhập thành công")
    public void TC01_Login_Success() {
        // Log tiếng Việt không dấu để tránh lỗi hiển thị trên Console nếu IDE chưa config UTF-8
        System.out.println("\n=== TC01 - DANG NHAP THANH CONG ===");

        authFlow.loginSuccessfully("0000000000", "000000");

        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Lỗi: Không vào được Home sau khi đăng nhập!");
        System.out.println("✅ TC01 PASS");
    }

    @Test(priority = 2, description = "TC02 - Đăng nhập sai mật khẩu")
    public void TC02_Login_Failed() {
        System.out.println("\n=== TC02 - DANG NHAP SAI MAT KHAU ===");

        authFlow.loginFailed("0000000000", "0000000");

        // Kiểm tra xem có còn ở màn hình Login không
        Assert.assertTrue(authFlow.isLoginScreenStillDisplayed(), "Lỗi: Phải ở lại màn hình Login khi nhập sai!");
        System.out.println("✅ TC02 PASS");
    }
}