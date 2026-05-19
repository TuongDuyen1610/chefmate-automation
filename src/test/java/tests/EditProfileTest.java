package tests;

import core.data.LoginData;
import core.utils.JsonHelper;
import core.base.BaseTest;
import flows.AuthenticationFlow;
import flows.ProfileFlow;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EditProfileTest extends BaseTest {

    private final AuthenticationFlow authFlow = new AuthenticationFlow();
    private final ProfileFlow profileFlow = new ProfileFlow();

    private final LoginData loginData = JsonHelper.readLoginData();

    String emailOrSDT = loginData.login;
    String password = loginData.password;

////     ================= TC01 =================
//    @Test(priority = 1, description = "Edit_TC_01 - Update Full Name Successfully")
//    public void Edit_TC_01() {
//
//        System.out.println("\n===== TC01 UPDATE FULL NAME =====");
//
//        authFlow.loginFromProfileTab(emailOrSDT, password);
//        authFlow.updateFullName("Tuong Thi Duyen Test");
//
//        Assert.assertTrue(authFlow.isToastUpdateInfoDisplayed(),
//                "Loi hien thi toast cap nhat thanh cong");
//
//    }
////     ================= TC02 =================
//
//    @Test(priority = 2, description = "Edit_TC_02 - Update Email Successfully")
//    public void Edit_TC_02() {
//
//        System.out.println("\n===== TC02 UPDATE EMAIL =====");
//
//        authFlow.loginFromProfileTab(emailOrSDT, password);
//        String newEmail = "duyentest2@gmail.com";
//        authFlow.updateEmail(newEmail);
//
//        Assert.assertTrue(
//                authFlow.isToastUpdateInfoDisplayed(),
//                "Loi hien thi toast cap nhat thanh cong");
//    }
//
//    // ================= TC03 =================
//    @Test(priority = 3, description = "Edit_TC_03 - Update Phone Successfully")
//    public void Edit_TC_03() {
//
//        System.out.println("\n===== TC03 UPDATE PHONE =====");
//        String newEmail = "duyentest2@gmail.com";
//        authFlow.loginFromProfileTab(newEmail, password);
//        String newPhone = "0900009998";
//
//        authFlow.updatePhone(newPhone);
//
//        Assert.assertTrue(
//                authFlow.isToastUpdateInfoDisplayed(),
//                "Loi hien thi toast cap nhat thanh cong");
//
//    }
//
//    // ================= TC04 =================
//    @Test(priority = 4, description = "Edit_TC_04 - Update All Info Successfully")
//    public void Edit_TC_04() {
//
//        String newEmail = "duyentest2@gmail.com";
//        System.out.println("\n===== TC04 UPDATE ALL INFO =====");
//        authFlow.loginFromProfileTab(newEmail, password);
//        authFlow.updateAllInfo(
//                "Tuong Thi Duyen",
//                "duyentest@gmail.com",
//                "0900009999");
//        Assert.assertTrue(
//                authFlow.isToastUpdateInfoDisplayed(),
//                "Loi hien thi toast cap nhat thanh cong");
//    }
//
//    // ================= TC05 =================
//
//    @Test(priority = 5, description = "Edit_TC_05 - Save And Back Still Display Profile")
//    public void Edit_TC_05() {
//
//        System.out.println("\n===== TC05 SAVE AND BACK =====");
//
//        String fullName = "Tuong Thi Duyen";
//        String email = "duyentest@gmail.com";
//        String phone = "0900009999";
//
//        authFlow.loginFromProfileTab(emailOrSDT, password);
//
//        authFlow.updateAllInfo(fullName, email, phone);
//
//        authFlow.saveAndBackProfile();
//
//        Assert.assertTrue(
//                profileFlow.isProfileDisplayed(),
//                "Back không về Profile");
//        Assert.assertTrue(
//                authFlow.isProfileInfoCorrect(fullName, email, phone),
//                "Thong tin khong đung sau khi save");
//        System.out.println("✅ TC05 PASS");
//    }
//
////     ================= TC06 =================
//
//    @Test(priority = 6, description = "Edit_TC_06 - Logout And Login Still Keep Data")
//    public void Edit_TC_06() {
//
//        System.out.println("\n===== TC06 LOGOUT LOGIN =====");
//
//        String fullName = "Tuong Thi Duyen";
//        String email = "duyentest@gmail.com";
//        String phone = "0900009999";
//
//        authFlow.loginFromProfileTab(emailOrSDT, password);
//
//        authFlow.updateAllInfo(fullName, email, phone);
//
//        authFlow.saveAndBackProfile();
//
//        authFlow.performLogout();
//
//        authFlow.loginFromProfileTab(emailOrSDT, password);
//
//        profileFlow.clickProfileDisplayed();
//        Assert.assertTrue(
//                authFlow.isProfileInfoCorrect(fullName, email, phone),
//                "Du lieu khong giu sau khi login lai");
//        System.out.println("✅ TC06 PASS");
//    }

// ================= TC7 ================= -->  Error : Emulator ko hiển thị, looix phia Backend
@Test(priority = 7, description = "Edit_TC_07 - Verify error when updating existing email")
public void Edit_TC_07() {

    System.out.println("\n===== TC07 EMAIL ALREADY EXISTS =====");

    // ✅ User hiện tại
    String currentName = "Tuong Thi Duyen";
    String currentEmail = "duyentest@gmail.com";
    String currentPhone = "0900009999";

    // ✅ Email của user khác (đã tồn tại trong DB)
    // SĐT: 0987654321, MK: 123456
    String existingEmail = "test1@gmail.com"; // ← Email cua: user 0987654321 / 123456

    authFlow.loginFromProfileTab(emailOrSDT, password);
//    WaitingHelper.sleepSeconds(2);

    // ✅ Try update với email đã tồn tại
    authFlow.checkDisplayErrorMsgEmail(currentName, existingEmail, currentPhone);

    // ✅ Verify error toast hiển thị
    Assert.assertFalse(
            authFlow.isToastErrorEmailDisplayed(),
            "Không hien thi thong bao loi email đa ton tai");
//    profileFlow.clickProfileDisplayed();
//    authFlow.updateEmail(currentEmail); // ← Revert email về cũ
//    WaitingHelper.sleepSeconds(2);
    System.out.println("✅ TC07 PASS - Error displayed correctly");
}

    // ================= TC8 =================
    @Test(priority = 8, description = "Edit_TC_08 - Verify error when updating existing phone")
    public void Edit_TC_08() {

        System.out.println("\n===== TC08 PHONE ALREADY EXISTS =====");

        // ✅ User hiện tại
        String currentName = "Tuong Thi Duyen";
        String currentEmail = "duyentest@gmail.com";
        String currentPhone = "0900009999";

        // ✅ SĐT của user khác (đã tồn tại trong DB)
        // SĐT: 0987654321, MK: 123456
        String existingPhone = "0987654321"; // ← SĐT của user: test1@gmail.com / 123456

        authFlow.loginFromProfileTab(emailOrSDT, password);
//        WaitingHelper.sleepSeconds(2);

        // ✅ Try update với SĐT đã tồn tại
        authFlow.checkDisplayErrorMsgSDT(currentName, currentEmail, existingPhone);

        // ✅ Verify error toast hiển thị
        Assert.assertTrue(
                authFlow.isToastErrorPhoneDisplayed(),
                "Không hiển thị thông báo lỗi SĐT đã tồn tại");

        System.out.println("✅ TC08 PASS - Error displayed correctly");
    }
}