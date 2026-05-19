package tests;

import core.data.LoginData;
import core.utils.AllureHelper;
import core.utils.JsonHelper;
import core.base.BaseTest;
import flows.AuthenticationFlow;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * ChangePasswordTest.java
 * Test case cho chuc nang Doi Mat khau
 * Load data tu JSON + Revert lai sau test
 */
public class ChangePasswordTest extends BaseTest {

    private final AuthenticationFlow authFlow = new AuthenticationFlow();
//    private final ProfileFlow profileFlow = new ProfileFlow();

    // Load LoginData tu JSON
    private final LoginData loginData = JsonHelper.readLoginData();

    // Lay email/SDT va mat khau tu JSON
    String emailOrSDT = loginData.login;           // "duyentest@gmail.com"
    String password = loginData.password;      // "123456" (mat khau goc tu JSON)

    // ================= TC01: HAPPY CASE - Doi mat khau thanh cong =================
    @Test(priority = 1, description = "DoiMatKhau_TC_01 - Doi mat khau thanh cong")
    public void DoiMatKhau_TC_01() {
        System.out.println("===== TC01: DOI MAT KHAU THANH CONG =====");
        authFlow.loginFromProfileTab(emailOrSDT, password);
        AllureHelper.attachScreenshot("Login thanh cong");

        String newPassword = "1234567";
        authFlow.changePassword(password, newPassword, newPassword);
        System.out.println("TC01 PASS");
    }


    @Test(priority = 2, description = "DoiMatKhau_TC_02 - Verify can login with new password")
    public void DoiMatKhau_TC_02() {
        System.out.println("\n===== TC02: DOI MAT KHAU VA DANG NHAP LAI =====");
        String oldPassword = "1234567";
        String newPassword = "123456";
        authFlow.loginFromProfileTab(emailOrSDT, oldPassword);
        authFlow.changePassword(oldPassword, newPassword, newPassword);
        // ✅ BACK 2 LẦN
        authFlow.backFromChangePassword();      // Change Password -> Edit Profile
        authFlow.goBackToProfileFromEditScreen(); // Edit Profile -> Profile

        authFlow.performLogoutSafely();
//        WaitingHelper.sleepSeconds(2);

        // ✅ Login lai voi mat khau moi
        authFlow.loginFromProfileTab(emailOrSDT, newPassword);
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Dang nhap voi mat khau moi that bai");

        System.out.println("TC02 PASS");
    }


    // ================= TC03: UNHAPPY CASE - Kiểm tra mật khẩu hiện tại/ mới trùng nhau =================
    @Test(priority = 3,
            description = "DoiMatKhau_TC_03 - Kiểm tra mật khẩu hiện tại/ mới trùng nhau")
    public void DoiMatKhau_TC_03() {

        authFlow.loginFromProfileTab(emailOrSDT, password);
        authFlow.changePassword2(password, password, password);
    }

    // ================= TC04: UNHAPPY CASE - Kiểm tra mật khẩu mới và xác nhận mật khẩu không khớp =================
    @Test(priority = 4,
            description = "DoiMatKhau_TC_04 - Kiểm tra mật khẩu mới và xác nhận mật khẩu không khớp")
    public void DoiMatKhau_TC_04() {

        authFlow.loginFromProfileTab(emailOrSDT, password);
        String new1 = "Test_12335";
        String new2 = "Test_4543";
        authFlow.changePassword3(password, new1, new2);
    }

}