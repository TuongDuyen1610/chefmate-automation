package smoke;

import core.data.LoginData;
import core.utils.JsonHelper;
import core.base.BaseTest;
import flows.AuthenticationFlow;
import flows.ProfileFlow;
import org.testng.Assert;
import core.utils.WaitingHelper;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;

/**
 * ChangePasswordTest.java
 * Test case cho chuc nang Doi Mat khau
 * Load data tu JSON + Revert lai sau test
 */
public class ChangePasswordTest extends BaseTest {

    private final AuthenticationFlow authFlow = new AuthenticationFlow();
    private final ProfileFlow profileFlow = new ProfileFlow();

    // Load LoginData tu JSON
    private final LoginData loginData = JsonHelper.readLoginData();

    // Lay email/SDT va mat khau tu JSON
    String emailOrSDT = loginData.login;           // "duyentest@gmail.com"
    String jsonPassword = loginData.password;      // "123456" (mat khau goc tu JSON)

    // ================= TC01: HAPPY CASE - Doi mat khau thanh cong =================

//    @Test(priority = 1, description = "DoiMatKhau_TC_01 - Doi mat khau thanh cong")
//    public void DoiMatKhau_TC_01_ChangePasswordSuccessfully() {
//        System.out.println("\n===== TC01: DOI MAT KHAU THANH CONG =====");
//
//        String newPassword = "1234567";
//
//        authFlow.loginFromProfileTab(emailOrSDT, jsonPassword);
//        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Login that bai");
//
//        authFlow.changePassword(jsonPassword, newPassword, newPassword);
////        Assert.assertTrue(authFlow.isChangePasswordSuccessDisplayed(), "Toast thanh cong khong hien thi");
//
//        authFlow.backFromChangePassword();
//
//        System.out.println("TC01 PASS");
//    }
//
//    @AfterMethod(description = "Revert password after TC01")
//    public void revertPasswordAfterTC01() {
//        System.out.println("\n===== CLEANUP TC01 =====");
//        try {
//            authFlow.loginFromProfileTab(emailOrSDT, "NewPass123");
//            if (authFlow.isLoggedInSuccessfully()) {
//                authFlow.changePassword("NewPass123", jsonPassword, jsonPassword);
//                authFlow.backFromChangePassword();
//                authFlow.performLogoutSafely();
//                System.out.println("Revert thanh cong");
//            }
//        } catch (Exception e) {
//            System.out.println("Revert that bai: " + e.getMessage());
//        }
//    }

    // ================= TC02: HAPPY CASE - Doi mat khau va dang nhap lai =================
//
//    @Test(priority = 2, description = "DoiMatKhau_TC_02 - Verify can login with new password")
//    public void DoiMatKhau_TC_02_LoginWithNewPassword() {
//        System.out.println("\n===== TC02: DOI MAT KHAU VA DANG NHAP LAI =====");
//
//        String newPassword = "1234567";
//
//        // ✅ Login voi mat khau tu JSON
//        authFlow.loginFromProfileTab(emailOrSDT, jsonPassword);
//        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Login that bai");
//
//        // ✅ Doi mat khau: JSON password -> NewPass456
//        authFlow.changePassword(jsonPassword, newPassword, newPassword);
//
//        // ✅ BACK 2 LẦN
//        authFlow.backFromChangePassword();      // Change Password -> Edit Profile
//        WaitingHelper.sleepSeconds(1);
//        authFlow.goBackToProfileFromEditScreen(); // Edit Profile -> Profile
//
//        authFlow.performLogoutSafely();
//        WaitingHelper.sleepSeconds(2);
//
//        // ✅ Login lai voi mat khau moi
//        authFlow.loginFromProfileTab(emailOrSDT, newPassword);
//        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Dang nhap voi mat khau moi that bai");
//
//        System.out.println("TC02 PASS");
//    }
//
//        @AfterMethod(description = "Revert password after TC02")
//        public void revertPasswordAfterTC02() {
//            System.out.println("\n===== CLEANUP TC02 =====");
//            try {
//                authFlow.loginFromProfileTab(emailOrSDT, "NewPass456");
//                if (authFlow.isLoggedInSuccessfully()) {
//                    authFlow.changePassword("NewPass456", jsonPassword, jsonPassword);
//                    authFlow.backFromChangePassword();
//                    authFlow.performLogoutSafely();
//                    System.out.println("Revert thanh cong");
//                }
//            } catch (Exception e) {
//                System.out.println("Revert that bai: " + e.getMessage());
//            }
//        }


    // ================= TC03: UNHAPPY CASE - Khong dang nhap duoc voi mat khau cu =================

    @Test(priority = 3,
            description = "DoiMatKhau_TC_03 - Verify cannot login with old password after change")
    public void DoiMatKhau_TC_03_CannotLoginWithOldPassword() {

        System.out.println("\n===== TC03: KHONG DANG NHAP DUOC VOI MAT KHAU CU =====");

        String newPassword = "1234567";

        // ✅ Login voi mat khau tu JSON
        authFlow.loginFromProfileTab(emailOrSDT, jsonPassword);
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Login that bai");

        // ✅ Doi mat khau: JSON password -> NewPass789
        authFlow.changePassword(jsonPassword, newPassword, newPassword);

        // ✅ Back 2 lần
        authFlow.backFromChangePassword();
        authFlow.goBackToProfileFromEditScreen();

        // ✅ Logout
        authFlow.performLogoutSafely();
        WaitingHelper.sleepSeconds(2);

        // ✅ Thử login lại voi mat khau CU (jsonPassword) - SHOULD FAIL
        authFlow.loginFromProfileTab(emailOrSDT, jsonPassword);

        // ✅ Verify: Vẫn ở Login screen (login thất bại)
        Assert.assertTrue(authFlow.isLoginScreenStillDisplayed(),
                "Login voi mat khau cu phai that bai nhung thanh cong - LOI!");

        System.out.println("TC03 PASS - Cannot login with old password");
    }

    @AfterMethod(description = "Revert password after TC03")
    public void revertPasswordAfterTC03() {
        System.out.println("\n===== CLEANUP TC03 =====");
        try {
            // ✅ Login voi mat khau MỚI
            authFlow.loginFromProfileTab(emailOrSDT, "NewPass789");
            if (authFlow.isLoggedInSuccessfully()) {
                // ✅ Doi lai mat khau CU
                authFlow.changePassword("NewPass789", jsonPassword, jsonPassword);
                authFlow.backFromChangePassword();
                authFlow.goBackToProfileFromEditScreen();
                authFlow.performLogoutSafely();
                System.out.println("Revert thanh cong");
            }
        } catch (Exception e) {
            System.out.println("Revert that bai: " + e.getMessage());
        }
    }

//    // ================= TC05: UNHAPPY CASE - Mat khau moi khong khop =================
//
//    @Test(priority = 5,
//            description = "DoiMatKhau_TC_05 - Verify error when passwords dont match")
//    public void DoiMatKhau_TC_05_PasswordsMismatch() {
//
//        System.out.println("\n===== TC05: MAT KHAU KHONG KHOP =====");
//
//        // Login voi mat khau tu JSON
//        authFlow.loginFromProfileTab(emailOrSDT, jsonPassword);
//        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Login failed");
//
//        // Doi mat khau voi mat khau moi khong khop
//        authFlow.changePassword(jsonPassword, "NewPass111", "DifferentPass222");
//
//        // Verify error toast
//        Assert.assertTrue(
//                authFlow.isChangePasswordErrorDisplayed(),
//                "Error toast not displayed"
//        );
//
//        System.out.println("TC05 PASS - Password mismatch error displayed");
//    }
//
//    // ================= TC06: UNHAPPY CASE - Mat khau hien tai sai =================
//
//    @Test(priority = 6,
//            description = "DoiMatKhau_TC_06 - Verify error when current password is wrong")
//    public void DoiMatKhau_TC_06_WrongCurrentPassword() {
//
//        System.out.println("\n===== TC06: MAT KHAU HIEN TAI SAI =====");
//
//        // Login voi mat khau tu JSON
//        authFlow.loginFromProfileTab(emailOrSDT, jsonPassword);
//        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Login failed");
//
//        // Doi mat khau voi mat khau hien tai sai
//        authFlow.changePassword("WrongPassword123", "NewPass123", "NewPass123");
//
//        // Verify error toast
//        Assert.assertTrue(
//                authFlow.isChangePasswordErrorDisplayed(),
//                "Error toast not displayed"
//        );
//
//        System.out.println("TC06 PASS - Wrong current password error displayed");
//    }
}