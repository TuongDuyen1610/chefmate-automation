package smoke;

import core.data.LoginData;
import core.utils.JsonHelper;
import core.base.BaseTest;
import flows.AuthenticationFlow;
import flows.ProfileFlow;
import org.testng.Assert;
import core.utils.WaitingHelper;
import org.testng.annotations.Test;

public class EditProfileTest extends BaseTest {

    private final AuthenticationFlow authFlow = new AuthenticationFlow();
    private final ProfileFlow profileFlow = new ProfileFlow();

    private final LoginData loginData = JsonHelper.readLoginData();

    String emailOrSDT = loginData.login;
    String password = loginData.password;

////     ================= TC01 =================
//
//    @Test(priority = 1,
//            description = "Edit_TC_01 - Update Full Name Successfully")
//    public void Edit_TC_01_UpdateFullNameSuccessfully() {
//
//        System.out.println("\n===== TC01 UPDATE FULL NAME =====");
//
//        authFlow.loginFromProfileTab(emailOrSDT, password);
//        authFlow.updateFullName("Tuong Thi Duyen Test");
//
//        Assert.assertTrue(
//                authFlow.isToastUpdateInfoDisplayed(),
//                "Loi hien thi toast cap nhat thanh cong"
//        );
//        WaitingHelper.sleepSeconds(2);
//        authFlow.goBackToProfileFromEditScreen();
//    }
//
////     ================= TC02 =================
//
//    @Test(priority = 2,
//            description = "Edit_TC_02 - Update Email Successfully")
//    public void Edit_TC_02_UpdateEmailSuccessfully() {
//
//        System.out.println("\n===== TC02 UPDATE EMAIL =====");
//
//        authFlow.loginFromProfileTab(emailOrSDT, password);
//        String newEmail = "duyentest@gmail.com";
//
//        authFlow.updateEmail(newEmail);
//
//        Assert.assertTrue(
//                authFlow.isToastUpdateInfoDisplayed(),
//                "Loi hien thi toast cap nhat thanh cong"
//        );
//        WaitingHelper.sleepSeconds(2);
//        authFlow.goBackToProfileFromEditScreen();
//
//        authFlow.updateEmail(emailOrSDT);
//    }
//
//    // ================= TC03 =================
//
//    @Test(priority = 3,
//            description = "Edit_TC_03 - Update Phone Successfully")
//    public void Edit_TC_03_UpdatePhoneSuccessfully() {
//
//        System.out.println("\n===== TC03 UPDATE PHONE =====");
//
//        authFlow.loginFromProfileTab(emailOrSDT, password);
//        String newPhone = "0900009999";
//
//        authFlow.updatePhone(newPhone);
//
//        Assert.assertTrue(
//                authFlow.isToastUpdateInfoDisplayed(),
//                "Loi hien thi toast cap nhat thanh cong"
//        );
//        WaitingHelper.sleepSeconds(2);
//        authFlow.goBackToProfileFromEditScreen();
////        authFlow.updatePhone("0900009999");
//    }
//
////     ================= TC04 =================
//
//    @Test(priority = 1,
//            description = "Edit_TC_04 - Update All Info Successfully")
//    public void Edit_TC_04_UpdateAllInfoSuccessfully() {
//
//        System.out.println("\n===== TC04 UPDATE ALL INFO =====");
//        authFlow.loginFromProfileTab(emailOrSDT, password);
//        authFlow.updateAllInfo(
//                "Tuong Thi Duyen",
//                "duyentest@gmail.com",
//                "0900009999"
//        );
//        Assert.assertTrue(
//                authFlow.isToastUpdateInfoDisplayed(),
//                "Loi hien thi toast cap nhat thanh cong"
//        );
//        WaitingHelper.sleepSeconds(2);
//        authFlow.goBackToProfileFromEditScreen();
//    }
//
//    // ================= TC05 =================
//
//    @Test(priority = 5,
//            description = "Edit_TC_05 - Save And Back Still Display Profile")
//    public void Edit_TC_05_DataUpdatedAfterBack() {
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
//                "Back không về Profile"
//        );
//
//        Assert.assertTrue(
//                authFlow.isProfileInfoCorrect(fullName, email, phone),
//                "Thông tin không đúng sau khi save"
//        );
//
//        System.out.println("✅ TC05 PASS");
//    }
//
////     ================= TC06 =================
//
//    @Test(priority = 6,
//            description = "Edit_TC_06 - Logout And Login Still Keep Data")
//    public void Edit_TC_06_DataStillAfterLogoutLogin() {
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
//                "Dữ liệu không giữ sau khi login lại"
//        );
//
//        System.out.println("✅ TC06 PASS");
//    }

// ================= TC7 ================= -->  Error : ko hiển thị, mà update info email test@gmail.com success??
@Test(priority = 7,
        description = "Edit_TC_07 - Verify error when updating existing email")
public void Edit_TC_07_ErrorEmailTonTai() {

    System.out.println("\n===== TC07 EMAIL ALREADY EXISTS =====");

    // ✅ User hiện tại
    String currentName = "Tuong Thi Duyen";
    String currentEmail = "duyentest@gmail.com";
    String currentPhone = "0900009999";

    // ✅ Email của user khác (đã tồn tại trong DB)
    // SĐT: 0987654321, MK: 123456
    String existingEmail = "test@gmail.com"; // ← Email của user 0987654321

    authFlow.loginFromProfileTab(emailOrSDT, password);
    WaitingHelper.sleepSeconds(2);

    // ✅ Try update với email đã tồn tại
    authFlow.checkDisplayErrorMsgEmail(currentName, existingEmail, currentPhone);

    // ✅ Verify error toast hiển thị
    Assert.assertTrue(
            authFlow.isToastErrorEmailDisplayed(),
            "Không hiển thị thông báo lỗi email đã tồn tại"
    );
    profileFlow.clickProfileDisplayed();
    authFlow.updateEmail(currentEmail); // ← Revert email về cũ
    WaitingHelper.sleepSeconds(2);
    System.out.println("✅ TC07 PASS - Error displayed correctly");
}
//
//    // ================= TC8 =================
//    @Test(priority = 8,
//            description = "Edit_TC_08 - Verify error when updating existing phone")
//    public void Edit_TC_08_ErrorSDTTonTai() {
//
//        System.out.println("\n===== TC08 PHONE ALREADY EXISTS =====");
//
//        // ✅ User hiện tại
//        String currentName = "Tuong Thi Duyen";
//        String currentEmail = "duyentest@gmail.com";
//        String currentPhone = "0900009999";
//
//        // ✅ SĐT của user khác (đã tồn tại trong DB)
//        // SĐT: 0987654321, MK: 123456
//        String existingPhone = "0987654321"; // ← SĐT của user thứ 2
//
//        authFlow.loginFromProfileTab(emailOrSDT, password);
//        WaitingHelper.sleepSeconds(2);
//
//        // ✅ Try update với SĐT đã tồn tại
//        authFlow.checkDisplayErrorMsgSDT(currentName, currentEmail, existingPhone);
//
//        // ✅ Verify error toast hiển thị
//        Assert.assertTrue(
//                authFlow.isToastErrorPhoneDisplayed(),
//                "Không hiển thị thông báo lỗi SĐT đã tồn tại"
//        );
//
//        System.out.println("✅ TC08 PASS - Error displayed correctly");
//    }
}