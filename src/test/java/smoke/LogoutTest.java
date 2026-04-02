package smoke;

import core.base.BaseTest;
import flows.AuthenticationFlow;
import screens.LogoutScreen;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * LogoutTest.java
 * ✅ Updated: Test case đăng xuất
 */
public class LogoutTest extends BaseTest {

    private final AuthenticationFlow authFlow = new AuthenticationFlow();
    private final LogoutScreen logout = new LogoutScreen();

    // ==================== HAPPY CASE ====================

    @Test(priority = 1, description = "DangXuat_TC_01 - Đang xuat thanh cong")
    public void DangXuat_TC_01_LogoutSuccessfully() {
        System.out.println("\n=== DangXuat_TC_01 - ĐANG XUAT THANH CONG ===");

        // 1. Login trước
        System.out.println("📝 Step 1: Thuc hien Đang nhap");
        authFlow.loginFromFridgeTab("0000000000", "000000");
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Đang nhap thanh cong!");
        System.out.println("✅ Đang nhap thanh cong");

        // 2. Đăng xuất
        System.out.println("📝 Step 2: Thuc hien Đang xuat");
        authFlow.performLogout();

        // 3. Verify: Quay lại Màn Profile
        System.out.println("📝 Step 3: Verify quay lai Profile");
        Assert.assertTrue(authFlow.isLogoutSuccessful(), " Quay lai man Profile o trang thai chua dang nhap");
        System.out.println("✅ DangXuat_TC_01 PASS");
    }

    // ==================== UNHAPPY CASE ====================

    @Test(priority = 2, description = "DangXuat_TC_02 - Hủy đăng xuất")
    public void DangXuat_TC_02_CancelLogout() {
        System.out.println("\n=== DangXuat_TC_02 - HỦY ĐĂNG XUẤT ===");

        // 1. Login trước
        System.out.println("📝 Step 1: Thực hiện Đăng nhập");
        authFlow.loginFromFridgeTab("0000000000", "000000");
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "❌ Lỗi: Đăng nhập thất bại!");
        System.out.println("✅ Đăng nhập thành công");

        // 2. Hủy đăng xuất
        System.out.println("📝 Step 2: Hủy Đăng xuất");
        authFlow.cancelLogout();

        // 3. Verify: Vẫn ở Home
        System.out.println("📝 Step 3: Verify vẫn ở Home");
        Assert.assertTrue(authFlow.isStillLoggedIn(), "❌ Lỗi: Vẫn phải ở Home!");

        System.out.println("✅ DangXuat_TC_02 PASS");
    }
}