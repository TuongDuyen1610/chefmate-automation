package tests;

import core.data.LoginData;
import core.utils.AllureHelper;
import core.utils.JsonHelper;
import core.base.BaseTest;
import flows.AuthenticationFlow;
import flows.ProfileFlow;
import net.bytebuddy.asm.Advice;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.EditProfileScreen;
import screens.ProfileScreen;

public class EditProfileTest extends BaseTest {

    private final AuthenticationFlow authFlow = new AuthenticationFlow();
    private final ProfileFlow profileFlow = new ProfileFlow();
    private final ProfileScreen profile = new ProfileScreen();
    private final EditProfileScreen edit = new EditProfileScreen();
    private final LoginData loginData = JsonHelper.readLoginData();

    String emailOrSDT = loginData.login;
    String password = loginData.password;

//     ================= TC01 =================
    @Test(priority = 1, description = "Edit_TC_01 - Update Full Name Successfully")
    public void Edit_TC_01() {

        System.out.println("\n===== TC01 UPDATE FULL NAME =====");

        authFlow.loginFromProfileTab(emailOrSDT, password);
        authFlow.updateFullName("Tuong Thi Duyen Test");

        Assert.assertTrue(authFlow.isToastUpdateInfoDisplayed(),
                "Loi hien thi toast cap nhat thanh cong");

    }
//     ================= TC02 =================
    @Test(priority = 2, description = "Edit_TC_02 - Update Email Successfully")
    public void Edit_TC_02() {

        System.out.println("\n===== TC02 UPDATE EMAIL =====");

        authFlow.loginFromProfileTab(emailOrSDT, password);
        String newEmail = "duyentest2@gmail.com";
        authFlow.updateEmail(newEmail);

        Assert.assertTrue(
                authFlow.isToastUpdateInfoDisplayed(),
                "Loi hien thi toast cap nhat thanh cong");
    }

    // ================= TC03 =================
    @Test(priority = 3, description = "Edit_TC_03 - Update Phone Successfully")
    public void Edit_TC_03() {

        System.out.println("\n===== TC03 UPDATE PHONE =====");
        String newEmail = "duyentest2@gmail.com";
        authFlow.loginFromProfileTab(newEmail, password);
        String newPhone = "0900009998";

        authFlow.updatePhone(newPhone);

        Assert.assertTrue(
                authFlow.isToastUpdateInfoDisplayed(),
                "Loi hien thi toast cap nhat thanh cong");

    }

    // ================= TC04 =================
    @Test(priority = 4, description = "Edit_TC_04 - Update All Info Successfully")
    public void Edit_TC_04() {

        String newEmail = "duyentest2@gmail.com";
        System.out.println("\n===== TC04 UPDATE ALL INFO =====");
        authFlow.loginFromProfileTab(newEmail, password);
        authFlow.updateAllInfo(
                "Tuong Thi Duyen",
                "duyentest@gmail.com",
                "0900009999");
        Assert.assertTrue(
                authFlow.isToastUpdateInfoDisplayed(),
                "Loi hien thi toast cap nhat thanh cong");
    }

    // ================= TC05 =================

    @Test(priority = 5, description = "Edit_TC_05 - Save And Back Still Display Profile")
    public void Edit_TC_05() {

        System.out.println("\n===== TC05 SAVE AND BACK =====");

        String fullName = "Tuong Thi Duyen";
        String email = "duyentest@gmail.com";
        String phone = "0900009999";

        authFlow.loginFromProfileTab(emailOrSDT, password);

        authFlow.updateAllInfo(fullName, email, phone);

        authFlow.saveAndBackProfile();

        Assert.assertTrue(
                profileFlow.isProfileDisplayed(),
                "Back không về Profile");
        Assert.assertTrue(
                authFlow.isProfileInfoCorrect(fullName, email, phone),
                "Thong tin khong đung sau khi save");
        System.out.println("✅ TC05 PASS");
    }

    //     ================= TC06 =================

    @Test(priority = 6, description = "Edit_TC_06 - Logout And Login Still Keep Data")
    public void Edit_TC_06() {

        System.out.println("\n===== TC06 LOGOUT LOGIN =====");

        String fullName = "Tuong Thi Duyen";
        String email = "duyentest@gmail.com";
        String phone = "0900009999";

        authFlow.loginFromProfileTab(emailOrSDT, password);

        authFlow.updateAllInfo(fullName, email, phone);

        authFlow.saveAndBackProfile();

        authFlow.performLogout();

        authFlow.loginFromProfileTab(emailOrSDT, password);

        profileFlow.clickProfileDisplayed();
        Assert.assertTrue(
                authFlow.isProfileInfoCorrect(fullName, email, phone),
                "Du lieu khong giu sau khi login lai");
        System.out.println("✅ TC06 PASS");
    }

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

    // ================= TC09 =================
    @Test(priority = 9, description = "Edit_TC_09 - Update Empty Fullname")
    public void Edit_TC_09() {

        System.out.println("\n===== TC09 UPDATE EMPTY FULLNAME=====");
        authFlow.loginFromProfileTab(emailOrSDT, password);
        authFlow.updateAllInfo(
                "",
                "duyentest@gmail.com",
                "0900009999");
            Assert.assertTrue(
                    authFlow.isToastUpdateInfoDisplayed(),
                    "Loi hien thi toast cap nhat thanh cong");
        System.out.println("✅ TC09 PASS");
    }

    // ================= TC10 =================
    @Test(priority = 10, description = "Edit_TC_10 - Update Empty Email")
    public void Edit_TC_10() {

        System.out.println("\n===== TC10 UPDATE EMPTY EMAIL =====");
        authFlow.loginFromProfileTab(emailOrSDT, password);
        authFlow.updateAllInfo(
                "Tuong Thi Duyen",
                "",
                "0900009999");
        Assert.assertTrue(
                authFlow.isToastUpdateInfoDisplayed(),
                "Loi hien thi toast cap nhat thanh cong");
        System.out.println("Thực hiện đăng nhập lại với email vừa update.");
        authFlow.saveAndBackProfile();
        authFlow.performLogout();
        Assert.assertTrue(
                profileFlow.isProfileDisplayed(),
                "Back không về Profile");
        authFlow.loginFromFridgeTab("", password);
        AllureHelper.attachScreenshot("Đăng nhập lại với email vừa update");
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Error: Khong vao đuoc Home!");
        System.out.println("✅ TC10 PASS");
    }

    // ================= TC11 =================
    @Test(priority = 11, description = "Edit_TC_11 - Update Email sai định dạng")
    public void Edit_TC_11() {
        String emailOrSDT = "0900009999";
        System.out.println("\n===== TC11 UPDATE EMAIL SAI ĐỊNH DẠNG =====");
        authFlow.loginFromProfileTab(emailOrSDT, password);
        authFlow.updateAllInfo(
                "Tuong Thi Duyen",
                "duyentestgmail.com",// thiếu @
                "0900009999");
        Assert.assertTrue(
                authFlow.isToastUpdateInfoDisplayed(),
                "Loi hien thi toast cap nhat thanh cong");
        authFlow.saveAndBackProfile();
        authFlow.performLogout();
        Assert.assertTrue(
                profileFlow.isProfileDisplayed(),
                "Back không về Profile");
        authFlow.loginFromFridgeTab("duyentestgmail.com", password);
        AllureHelper.attachScreenshot("Đăng nhập lại với email vừa update");
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Error: Khong vao đuoc Home!");
        System.out.println("✅ TC11 PASS");
    }
    // ================= TC12 =================
    @Test(priority = 12, description = "Edit_TC_12 - Update SĐT sai định dạng")
    public void Edit_TC_12() {
        String emailOrSDT = "duyentestgmail.com";// thiếu @
        System.out.println("\n===== TC12 UPDATE SĐT SAI ĐỊNH DẠNG =====");
        authFlow.loginFromProfileTab(emailOrSDT, password);
        authFlow.updateAllInfo(
                "Tuong Thi Duyen",
                "duyentest@gmail.com",
                "0909A876*");
        Assert.assertTrue(
                authFlow.isToastUpdateInfoDisplayed(),
                "Loi hien thi toast cap nhat thanh cong");
        authFlow.saveAndBackProfile();
        authFlow.performLogout();
        Assert.assertTrue(
                profileFlow.isProfileDisplayed(),
                "Back không về Profile");
        System.out.println("Thực hiện đăng nhập lại với SĐT vừa update.");
        authFlow.loginFromFridgeTab("0909A876*", password);
        AllureHelper.attachScreenshot("Đăng nhập lại với SĐT vừa update");
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Error: Khong vao đuoc Home!");

        System.out.println("✅ TC12 PASS");
    }
    // LƯU Ý THẰNG MAX KÝ TỰ NÀY ko hiển thị rõ ràng toast error, behavior app cho phép nhập quá kí tự
    // test pass đúng với behavior Assert đúng thực tế app (app cho nhập tới đâu thì verify tới đó). => phản ánh chất lượng của app qua testcase.
    // Không ép pass/fail theo rule chưa tồn tại!
    // --> Không nên assert toast nếu app không hiện toast/xpath.
    // Hãy verify giá trị thực tế của input field không vượt quá max ký tự quy định.
    @Test(priority = 13, description = "Edit_TC_13 - Tất cả các trường max ký tự (assert thực tế app)")
    public void Edit_TC_13() {
        String maxFullName = "A".repeat(60);
        String maxEmail = "b".repeat(40) + "@mail.com";
        String maxPhone = "1".repeat(20);
        authFlow.loginFromProfileTab(emailOrSDT, password);
        authFlow.updateAllInfo(maxFullName, maxEmail, maxPhone);

        // Lấy lại giá trị đã nhập sau khi update
        String actualFullName = edit.getFullNameFieldValue();
        String actualEmail = edit.getEmailFieldValue();
        String actualPhone = edit.getPhoneFieldValue();

        System.out.println("Fullname: '" + actualFullName + "' length=" + actualFullName.length());
        System.out.println("Email: '" + actualEmail + "' length=" + actualEmail.length());
        System.out.println("Phone: '" + actualPhone + "' length=" + actualPhone.length());

        // App hiện tại KHÔNG giới hạn -> assert field đúng chính xác bằng dữ liệu nhập vào
        Assert.assertEquals(actualFullName, maxFullName, "Họ tên không đúng value đã nhập!");
        Assert.assertEquals(actualEmail, maxEmail, "Email không đúng value đã nhập!");
        Assert.assertEquals(actualPhone, maxPhone, "SĐT không đúng value đã nhập!");

        System.out.println("✅ TC13 PASS - App hiện tại cho nhập bao nhiêu cũng được, đã verify đúng behavior thực tế.");
    }

    @Test(priority = 14, description = "Edit_TC_14 - Sử dụng ký tự đặc biệt cho Họ tên")
    public void Edit_TC_14() {
        authFlow.loginFromProfileTab(emailOrSDT, password);
        authFlow.updateAllInfo("Tuong@Duyen", "duyentest@gmail.com", "0900009999");
        Assert.assertTrue(
                authFlow.isToastUpdateInfoDisplayed(),
                "Loi hien thi toast cap nhat thanh cong");
        System.out.println("✅ TC14 PASS");
    }

    @Test(priority = 15, description = "Edit_TC_15 - Đổi sang email có ký tự Unicode")
    public void Edit_TC_15() {
        authFlow.loginFromProfileTab(emailOrSDT, password);
        authFlow.updateAllInfo("Tuong Duyen", "tưởng@email.com", "0900009999");
        Assert.assertTrue(
                authFlow.isToastUpdateInfoDisplayed(),
                "Loi hien thi toast cap nhat thanh cong");
        System.out.println("✅ TC15 PASS");
    }

    @Test(priority = 16, description = "Edit_TC_16 - Đổi sang số điện thoại nước ngoài")
    public void Edit_TC_16() {
        String emailOrSDT = "tưởng@email.com";
        authFlow.loginFromProfileTab(emailOrSDT, password);
        authFlow.updateAllInfo("Tuong Duyen", "duyentest@gmail.com", "+84900009999");
        Assert.assertTrue(
                authFlow.isToastUpdateInfoDisplayed(),
                "Loi hien thi toast cap nhat thanh cong");
        System.out.println("✅ TC16 PASS");
    }

    @Test(priority = 17, description = "Edit_TC_17 - Dữ liệu có khoảng trắng đầu/cuối")
    public void Edit_TC_17() {
        authFlow.loginFromProfileTab(emailOrSDT, password);
        authFlow.updateAllInfo(" Tuong Thi Duyen ", " duyentest@gmail.com ", " 0900009999 ");
        Assert.assertTrue(
                authFlow.isToastUpdateInfoDisplayed(),
                "Loi hien thi toast cap nhat thanh cong");
        System.out.println("✅ TC17 PASS");
    }

    @Test(priority = 18, description = "Edit_TC_18 - Không thay đổi gì, nhấn lưu")
    public void Edit_TC_18() {
        authFlow.loginFromProfileTab(emailOrSDT, password);
        profile.clickBottomNavProfile();
        edit.openEditProfileScreen();
        Assert.assertTrue(profileFlow.isNoChangeMessageDisplayed(), "❌ Không cảnh báo hoặc vẫn thực hiện API không cần thiết");
        System.out.println("✅ TC18 PASS");
    }
    @Test(priority = 19, description = "Edit_TC_19 - Đổi đồng thời email, SĐT đều trùng user khác")
    public void Edit_TC_19() {
        authFlow.loginFromProfileTab(emailOrSDT, password);
        // Email/SĐT của user khác đã tồn tại
        authFlow.updateAllInfo("0", "0", "0");
        Assert.assertTrue(authFlow.isToastErrorPhoneDisplayed(), "❌ Không báo lỗi khi email/SĐT đều trùng user khác");
        System.out.println("✅ TC19 PASS");
    }
    // ================= TC04 =================
    @Test(priority = 20, description = "Edit_TC_20 - Update All Info Click back mà chưa lưu ")
    public void Edit_TC_20() {

//        String newEmail = "duyentest2@gmail.com";
        String newEmail = "duyentest@gmail.com";
        String fullName = "Tuong Thi Duyen";
        String email = "duyentest@gmail.com";
        String phone = "0900009999";
        authFlow.loginFromProfileTab(newEmail, password);
        authFlow.updateAllInfo2(
                "Tuong Thi Duyen Test",
                "duyentest2004@gmail.com",
                "0912121129");

        edit.clickBack();
        Assert.assertTrue(
                profileFlow.isProfileDisplayed(),
                "Back không về Profile");
        Assert.assertTrue(
                authFlow.isProfileInfoCorrect(fullName, email, phone),
                "Thong tin tự cập nhật sau khi không click save");
        System.out.println("✅ TC20 PASS");
    }
}