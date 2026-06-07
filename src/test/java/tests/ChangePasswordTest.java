package tests;

import core.data.LoginData;
import core.utils.AllureHelper;
import core.utils.JsonHelper;
import core.base.BaseTest;
import flows.AuthenticationFlow;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.EditProfileScreen;

/**
 * ChangePasswordTest.java
 * Test case cho chuc nang Doi Mat khau
 * Load data tu JSON + Revert lai sau test
 */
public class ChangePasswordTest extends BaseTest {

    private final AuthenticationFlow authFlow = new AuthenticationFlow();
    private final EditProfileScreen edit = new EditProfileScreen();
    //
//    String emailOrSDT = "test09@gmail.com";
//    String password = "2***3 ";
//     Load LoginData tu JSON
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
        Assert.assertTrue(authFlow.isChangePasswordSuccessDisplayed(), "Toast not displayed");
        System.out.println("TC01 PASS");
    }


    @Test(priority = 2, description = "DoiMatKhau_TC_02 - Verify can login with new password")
    public void DoiMatKhau_TC_02() {
        System.out.println("\n===== TC02: DOI MAT KHAU VA DANG NHAP LAI THANH CONG =====");
        String oldPassword = "1234567";
        String newPassword = "123456";
        authFlow.loginFromProfileTab(emailOrSDT, oldPassword);
        authFlow.changePassword(oldPassword, newPassword, newPassword);
        Assert.assertTrue(authFlow.isChangePasswordSuccessDisplayed(), "Toast not displayed");
        // ✅ BACK 2 LẦN
        authFlow.backFromChangePassword();      // Change Password -> Edit Profile
        authFlow.goBackToProfileFromEditScreen(); // Edit Profile -> Profile

        authFlow.performLogoutSafely();

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
        authFlow.changePassword2(password, password, password);// đã thực hện verify toast tại hàm này rồi
    }

    // ================= TC04: UNHAPPY CASE - Kiểm tra mật khẩu mới và xác nhận mật khẩu không khớp =================
    @Test(priority = 4,
            description = "DoiMatKhau_TC_04 - Kiểm tra mật khẩu mới và xác nhận mật khẩu không khớp")
    public void DoiMatKhau_TC_04() {

        authFlow.loginFromProfileTab(emailOrSDT, password);
        String new1 = "1234567";
        String new2 = "12345678";
        authFlow.changePassword3(password, new1, new2);// đã thực hện verify toast tại hàm này rồi
    }


    @Test(priority = 5, description = "DoiMatKhau_TC_05 - Pass hiện tại sai")
    public void DoiMatKhau_TC_05() {
        authFlow.loginFromProfileTab(emailOrSDT, password);
        authFlow.changePassword("saimatkhaucu", "testnewpass", "testnewpass");
        Assert.assertTrue(authFlow.isWrongCurrentPasswordErrorDisplayed(), "❌ Không báo lỗi khi nhập sai mật khẩu hiện tại");
    }

    @Test(priority = 6, description = "DoiMatKhau_TC_06 - Để trống Pass hiện tại")
    public void DoiMatKhau_TC_06() {
        authFlow.loginFromProfileTab(emailOrSDT, password);
        authFlow.changePassword("", "testnewpass", "testnewpass");
        edit.clickConfirmChangePassword();
    }

    @Test(priority = 7, description = "DoiMatKhau_TC_07 - Để trống Pass mới")
    public void DoiMatKhau_TC_07() {
        authFlow.loginFromProfileTab(emailOrSDT, password);
        authFlow.changePassword(password, "", "abc@123");
        edit.clickConfirmChangePassword();
    }

    @Test(priority = 8, description = "DoiMatKhau_TC_08 - Để trống Xác nhận pass mới")
    public void DoiMatKhau_TC_08() {
        authFlow.loginFromProfileTab(emailOrSDT, password);
        authFlow.changePassword(password, "abc@123", "");
        edit.clickConfirmChangePassword();
    }

    @Test(priority = 9, description = "DoiMatKhau_TC_09 - Pass mới ngắn")
    public void DoiMatKhau_TC_09() {
        // Ngắn
        authFlow.loginFromProfileTab(emailOrSDT, password);
        authFlow.changePassword(password, "2", "2");
        Assert.assertTrue(authFlow.isChangePasswordSuccessDisplayed(), "Toast not displayed");

    }
//    @Test(priority = 10, description = "DoiMatKhau_TC_10 - Pass mới quá dài") // cần login lại với this longpass
//    public void DoiMatKhau_TC_10() {
//        String password = "2";
//        authFlow.loginFromProfileTab(emailOrSDT, password);
//        String longPasss = "b".repeat(200);
//        authFlow.changePassword(password, longPasss, longPasss);
//        Assert.assertTrue(authFlow.isChangePasswordSuccessDisplayed(), "Toast not displayed");
//    }

    @Test(priority = 11, description = "DoiMatKhau_TC_11 - Pass mới có ký tự đặc biệt")
    public void DoiMatKhau_TC_11() {
        String password = "2";
        authFlow.loginFromProfileTab(emailOrSDT, password);
        authFlow.changePassword(password, "!@#$%^&*", "!@#$%^&*");
        Assert.assertTrue(authFlow.isChangePasswordSuccessDisplayed(), "Toast not displayed");
    }

    @Test(priority = 12, description = "DoiMatKhau_TC_12 - UI hiện/ẩn đúng với các ô password")
    public void DoiMatKhau_TC_12() {
        String password = "!@#$%^&*";
        authFlow.loginFromProfileTab(emailOrSDT, password);
        authFlow.changePassword(password, "123456", "123456");
        authFlow.checkShowHidePasswordIconAllFields();
    }

    @Test(priority = 13, description = "DoiMatKhau_TC_13 - Đổi pass có khoảng trắng đầu/cuối")
    public void DoiMatKhau_TC_13() {
        String password = "!@#$%^&*";
        authFlow.loginFromProfileTab(emailOrSDT, password);
        authFlow.changePassword(password, " 123456 ", " 123456 ");
        Assert.assertTrue(authFlow.isChangePasswordSuccessDisplayed(), "Toast not displayed");
    }

    @Test(priority = 14, description = "DoiMatKhau_TC_14 - Đổi xong đăng nhập với mật khẩu cũ phải sai")
    public void DoiMatKhau_TC_14() {
        String newPass = "1234567";
        authFlow.loginFromProfileTab(emailOrSDT, password);
        authFlow.changePassword(password, newPass, newPass);
        Assert.assertTrue(authFlow.isChangePasswordSuccessDisplayed(), "Toast not displayed");
        // ✅ BACK 2 LẦN
        authFlow.backFromChangePassword();      // Change Password -> Edit Profile
        authFlow.goBackToProfileFromEditScreen(); // Edit Profile -> Profile

        authFlow.performLogoutSafely();
        authFlow.loginFromProfileTab(emailOrSDT, password);
        AllureHelper.attachScreenshot("Login fail");
        Assert.assertTrue(authFlow.isToastLoginFail(), "Loi: Toast error khong hien thi");
    }
}