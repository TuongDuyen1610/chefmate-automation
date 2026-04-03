package smoke;

import core.base.BaseTest;
import flows.AuthenticationFlow;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EditProfileTest extends BaseTest {

    private final AuthenticationFlow authFlow = new AuthenticationFlow();

    String email = "00000@gmail.com";
    String password = "000000";

    // ================= TC01 =================

//    @Test(priority = 1,
//            description = "Edit_TC_01 - Update Full Name Successfully")
//    public void Edit_TC_01_UpdateFullNameSuccessfully() {
//
//        System.out.println("\n===== TC01 UPDATE FULL NAME =====");
//
//        authFlow.loginFromProfileTab(email, password);
//
//        authFlow.updateFullName("Tuong Thi Duyen Updated");
//
//        Assert.assertTrue(
//                authFlow.isEditProfileDisplayed(),
//                "Không quay lại màn Profile"
//        );
//    }

    // ================= TC02 =================
//
//    @Test(priority = 2,
//            description = "Edit_TC_02 - Update Email Successfully")
//    public void Edit_TC_02_UpdateEmailSuccessfully() {
//
//        System.out.println("\n===== TC02 UPDATE EMAIL =====");
//
//        authFlow.loginFromProfileTab(email, password);
//
//        authFlow.updateEmail("duyennew@gmail.com");
//
//        Assert.assertTrue(
//                authFlow.isEditProfileDisplayed(),
//                "Không quay lại màn Profile"
//        );
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
//        authFlow.loginFromProfileTab(email, password);
//
//        authFlow.updatePhone("0988888888");
//
//        Assert.assertTrue(
//                authFlow.isEditProfileDisplayed(),
//                "Không quay lại màn Profile"
//        );
//    }

    // ================= TC04 =================

//    @Test(priority = 4,
//            description = "Edit_TC_04 - Update All Info Successfully")
//    public void Edit_TC_04_UpdateAllInfoSuccessfully() {
//
//        System.out.println("\n===== TC04 UPDATE ALL INFO =====");
//
//        authFlow.loginFromProfileTab(email, password);
//
//        authFlow.updateAllInfo(
//                "Tuong Thi Duyen",
//                "duyenupdate@gmail.com",
//                "0977777777"
//        );
//
//        Assert.assertTrue(
//                authFlow.isEditProfileDisplayed(),
//                "Không quay lại màn Profile"
//        );
//    }

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
//                "Tuong Thi Duyen",
//                "duyenupdate@gmail.com",
//                "0977777777"
//        );
//
//        authFlow.saveAndBackProfile();
//
//        Assert.assertTrue(
//                authFlow.isEditProfileDisplayed(),
//                "Back không về Profile"
//        );
//    }

    // ================= TC06 =================

    @Test(priority = 6,
            description = "Edit_TC_06 - Logout And Login Still Keep Data")
    public void Edit_TC_06_DataStillAfterLogoutLogin() {

        System.out.println("\n===== TC06 LOGOUT LOGIN =====");

        authFlow.loginFromProfileTab(email, password);

        authFlow.updateFullName("Tuong Thi Duyen Test");

        authFlow.performLogout();

        authFlow.loginFromProfileTab(email, password);

        Assert.assertTrue(
                authFlow.isLoggedInSuccessfully(),
                "Login lại thất bại"
        );
    }
}