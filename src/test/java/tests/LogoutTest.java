package tests;

import core.base.BaseTest;
import flows.AuthenticationFlow;
import flows.ProfileFlow;
import screens.LogoutScreen;
import org.testng.Assert;
import org.testng.annotations.Test;
import core.data.LoginData;
import core.utils.JsonHelper;
/**
 * LogoutTest.java
 * ✅ Updated: Test case đăng xuất
 */
public class LogoutTest extends BaseTest {

    private final ProfileFlow profileFlow = new ProfileFlow();
    private final AuthenticationFlow authFlow = new AuthenticationFlow();
    private final LogoutScreen logout = new LogoutScreen();
    private final LoginData loginData = JsonHelper.readLoginData();

    String phoneOrEmail = loginData.login;
    String password = loginData.password;
    // ==================== HAPPY CASE ====================

    @Test(priority = 1, description = "DangXuat_TC_01 - Đang xuat thanh cong")
    public void DangXuat_TC_01() {
        System.out.println("\n=== DangXuat_TC_01 - ĐANG XUAT THANH CONG ===");

        // 1. Login trước
        authFlow.loginFromFridgeTab(phoneOrEmail, password);
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Đang nhap thanh cong!");

        // 2. Đăng xuất
        authFlow.performLogout();

        // 3. Verify: Quay lại Màn Profile
        Assert.assertTrue(authFlow.isLogoutSuccessful(), " Quay lai man Profile o trang thai chua dang nhap");
    }

    @Test(priority = 2, description = "DangXuat_TC_02 - Hủy đăng xuất")
    public void DangXuat_TC_02() {

        // 1. Login trước
        authFlow.loginFromFridgeTab(phoneOrEmail, password);
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "❌ Error: Login Fail!");

        // 2. Hủy đăng xuất
        authFlow.cancelLogout();

        // 3. Verify: Vẫn ở Profile
        Assert.assertTrue(profileFlow.isProfileDisplayed(), "❌ Error: Van phai o Profile!");

        System.out.println("✅ DangXuat_TC_02 PASS");
    }
    //     ==================== UNHAPPY CASE ====================
    @Test(priority = 3, description = "DangXuat_TC_03 - Đăng xuất khi chưa đăng nhập")
    public void DangXuat_TC_03() {
        System.out.println("\n=== DangXuat_TC_03 - ĐANG XUAT KHI CHUA LOGIN ===");

        // 1. Không login

        // 2. Đăng xuất
        authFlow.performLogout();

        // 3. Verify: Quay lại Màn Profile
        Assert.assertTrue(authFlow.isLogoutNotLogin(),
                "❌ Error: Không báo lỗi khi đang ở trạng thái chưa đăng nhập mà nhấn logout.");
    }

    @Test(priority = 4, description = "DangXuat_TC_04 - Đang xuat re-login")
    public void DangXuat_TC_04() {
        System.out.println("\n=== DangXuat_TC_04- ĐANG XUAT RE-LOGIN ===");

        // 1. Login trước
        authFlow.loginFromFridgeTab(phoneOrEmail, password);
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Đang nhap thanh cong!");

        // 2. Đăng xuất
        authFlow.performLogout();

        // 3. Verify: Quay lại Màn Profile
        Assert.assertTrue(authFlow.isLogoutSuccessful(), " Quay lai man Profile o trang thai chua dang nhap");

        authFlow.loginFromFridgeTab(phoneOrEmail, password);
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "❌ Không login lại được sau khi đã logout");
    }
}