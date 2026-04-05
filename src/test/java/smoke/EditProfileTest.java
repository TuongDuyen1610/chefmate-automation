package smoke;

import core.base.BaseTest;
import flows.AuthenticationFlow;
import flows.ProfileFlow;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EditProfileTest extends BaseTest {

    private final AuthenticationFlow authFlow = new AuthenticationFlow();
    private final ProfileFlow profileFlow = new ProfileFlow();
    String email = "09090909";
    String password = "09090909";
    private static int registrationCounter = 0;

//    // ================= TC01 =================
//
//    @Test(priority = 1,
//            description = "Edit_TC_01 - Update Full Name Successfully")
//    public void Edit_TC_01_UpdateFullNameSuccessfully() {
//
//        System.out.println("\n===== TC01 UPDATE FULL NAME =====");
//
////        authFlow.loginFromProfileTab(email, password);
//        registrationCounter++;
//
//        String fullName = "Duyen189" + registrationCounter;
//        String phone = "1489" + (1000000 + registrationCounter);
//        String email = "141898" + registrationCounter + "@gmail.com";
//        String password = "1911";
//
//        authFlow.registerNewAccount_toEdit(fullName, phone, email, password, password);
//        authFlow.updateFullName("Tuong Thi Duyen Updated08");
//
//        Assert.assertTrue(
//                profileFlow.isProfileDisplayed(),
//                "Không quay lại màn Profile"
//        );
//    }

//     ================= TC02 =================

    @Test(priority = 2,
            description = "Edit_TC_02 - Update Email Successfully")
    public void Edit_TC_02_UpdateEmailSuccessfully() {

        System.out.println("\n===== TC02 UPDATE EMAIL =====");
// Do thực hiện edit từ tài khoản đăng nhập đang lỗi thông tin và lỗi phần hiển th data trên luồng chỉnh sửa thông tin --> nên chuyền sang luồng đki để edit
        registrationCounter++;

        String fullName = "Duyen186" + registrationCounter;
        String phone = "68" + (1000000 + registrationCounter);
        String email = "14816" + registrationCounter + "@gmail.com";
        String password = "11186";

        authFlow.registerNewAccount_toEdit(fullName, phone, email, password, password);
//        authFlow.loginFromProfileTab(email, password);

        authFlow.updateEmail("duyennew186@gmail.com");

        Assert.assertTrue(
                profileFlow.isProfileDisplayed(),
                "Không quay lại màn Profile"
        );
    }
//
//    // ================= TC03 =================
//
//    @Test(priority = 3,
//            description = "Edit_TC_03 - Update Phone Successfully")
//    public void Edit_TC_03_UpdatePhoneSuccessfully() {
//
//        System.out.println("\n===== TC03 UPDATE PHONE =====");
//        registrationCounter++;
//
//        String fullName = "Duyen1855" + registrationCounter;
//        String phone = "188554" + (1000000 + registrationCounter);
//        String email = "145581" + registrationCounter + "@gmail.com";
//        String password = "1811585";
//
//        authFlow.registerNewAccount_toEdit(fullName, phone, email, password, password);
////        authFlow.loginFromProfileTab(email, password);
//
//        authFlow.updatePhone("09888888858881");
//
//        Assert.assertTrue(
//                profileFlow.isProfileDisplayed(),
//                "Không quay lại màn Profile"
//        );
//    }
//
////     ================= TC04 =================
//
//    @Test(priority = 4,
//            description = "Edit_TC_04 - Update All Info Successfully")
//    public void Edit_TC_04_UpdateAllInfoSuccessfully() {
//
//        System.out.println("\n===== TC04 UPDATE ALL INFO =====");
//        registrationCounter++;
//
//        String fullName = "Duyenn188" + registrationCounter;
//        String phone = "14888" + (1000000 + registrationCounter);
//        String email = "14881" + registrationCounter + "@gmail.com";
//        String password = "118881";
//
//        authFlow.registerNewAccount_toEdit(fullName, phone, email, password, password);
////        authFlow.loginFromProfileTab(email, password);
//
//        authFlow.updateAllInfo(
//                "Tuong Thi Duyen18",
//                "duyenupdate188@gmail.com",
//                "0977787778771"
//        );
//
//        Assert.assertTrue(profileFlow.isProfileDisplayed(),
//                "Không quay lại màn Profile"
//        );
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
//        authFlow.loginFromProfileTab(email, password);
//
//        authFlow.updateAllInfo(
//                "Tuongg Thi Duyen00",
//                "duyennupdatee@gmail.com",
//                "0977777770777"
//        );
//
//        authFlow.saveAndBackProfile();
//
//        Assert.assertTrue(
//                profileFlow.isProfileDisplayed(),
//                "Back không về Profile"
//        );
//    }
//
////     ================= TC06 =================
//
//    @Test(priority = 6,
//            description = "Edit_TC_06 - Logout And Login Still Keep Data")
//    public void Edit_TC_06_DataStillAfterLogoutLogin() {
//        System.out.println("\n===== TC06 LOGOUT LOGIN =====");
//        authFlow.loginFromProfileTab(email, password);
//        authFlow.openEditProfileScreen("Tuong Thi Duyen", "030303@gmail.com", "09090");
//        authFlow.loginFromProfileTab(email, password);
//        Assert.assertTrue(
//                authFlow.isLoggedInSuccessfully(),
//                "Login lại thất bại"
//        );
//        profileFlow.isProfileDisplayed();
//        System.out.println("✅ DangKy_TC_06 PASS");
//    }
//
//    // ================= TC7 =================
//
//    @Test(priority = 7,
//            description = "Edit_TC_07 - Kiem tra error msg email ton tai")
//    public void Edit_TC_07_ErrorEmailTonTai() {
//
//        System.out.println("\n===== TC07 EMAIL ERROR MSG =====");
//
//        authFlow.loginFromProfileTab(email, password);
//
//        authFlow.checkDisplayErrorMsgEmail("Tuong Thi Duyen", "00000@gmail.com", "090123422");
//
//        Assert.assertTrue( authFlow.isToastErrorDisplayed(),
//                "Khong hien thi thong bao Co loi xay ra"
//                );
//        System.out.println("✅ DangKy_TC_07 PASS");
//    }
//    // ================= TC8 =================
//
//    @Test(priority = 8,
//            description = "Edit_TC_08 - Kiem tra error msg SDT ton tai")
//    public void Edit_TC_08_ErrorSDTTonTai() {
//
//        System.out.println("\n===== TC08 SDT ERROR MSG =====");
//
//        authFlow.loginFromProfileTab(email, password);
//
//        authFlow.checkDisplayErrorMsgSDT("Tuong Thi Duyen", "2323@gmail", "0000000000");
//
//        Assert.assertTrue( authFlow.isToastErrorDisplayed(),
//                "Khong hien thi thong bao Co loi xay ra"
//        );
//        System.out.println("✅ DangKy_TC_08 PASS");
//    }
}