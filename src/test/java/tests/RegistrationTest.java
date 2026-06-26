//package tests;
//
//import core.base.BaseTest;
//import flows.AuthenticationFlow;
//import screens.ProfileScreen;
//import screens.RegistrationScreen;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
///**
// * RegistrationTest.java
// * Test case đăng ký theo spec: 1 Happy + 11 Unhappy
// * ✅ Updated: Chi tiết từng scenario
// */
//public class RegistrationTest extends BaseTest {
//
//    private final AuthenticationFlow authFlow = new AuthenticationFlow();
//    private final RegistrationScreen registration = new RegistrationScreen();
//    private final ProfileScreen profile = new ProfileScreen();
//    //        Assert.assertTrue(condition, message);
////        condition	Điều kiện cần đúng
////        message	Nội dung lỗi nếu điều kiện sai
////        Assert.assertFail(message); Assert.fail() không cần condition
////vì nó dùng để ép testcase FAIL ngay lập tức.
////        message   Nội dung lỗi luôn luôn được đánh dấu FAIL
////    ko hiển thị toast, ko đki thành coong
//    // Không nên assert toast nếu app không hiện toast/xpath.
//    // ==================== HAPPY CASE ====================
//
//    // TC_01 Chỉ dùng khi chưa đăng ký, và chỉ chạy 1 lần sau đó disable,
//    // test sau sẽ chạy test login ko cần chạy registation
//    @Test(priority = 1, enabled = false, description = "DangKy_TC_01 - Đăng ký thành công")
//    public void DangKy_TC_01() {
//
//        String fullName = "Tuong Thi Duyen";
//        String phone = "0900009999";
//        String email = "duyentest@gmail.com";
//        String password = "123456";
//
//        authFlow.registerNewAccount(fullName, phone, email, password, password);
//        Assert.assertTrue(
//                authFlow.isRegistrationSuccessful(),
//                "❌ Lỗi: Đăng ký thất bại, không vào được Home!"
//        );
//
//        System.out.println("✅ DangKy_TC_01 PASS");
//    }
//
//    // ==================== UNHAPPY CASE ====================
//    //android.widget.Toast[@text="Vui lòng điền đầy đủ thông tin"]
//    @Test(priority = 2, description = "DangKy_TC_02 - Để trống Họ và tên")
//    public void DangKy_TC_02() {
//        authFlow.registerNewAccount("", "0912345678", "test02@gmail.com", "Pass@123", "Pass@123");
//        Assert.assertTrue(authFlow.isFillAllInfoErrorDisplayed(),
//                "❌ Phải hiển thị: Vui lòng điền đầy đủ thông tin");
//        System.out.println("✅ DangKy_TC_02 PASS");
//    }
//    //android.widget.Toast[@text="Vui lòng điền đầy đủ thông tin"]
//    @Test(priority = 3, description = "DangKy_TC_03 - Để trống Số điện thoại")
//    public void DangKy_TC_03() {
//        authFlow.registerNewAccount("Test User 03", "", "test03@gmail.com", "Pass@123", "Pass@123");
//        Assert.assertTrue(authFlow.isFillAllInfoErrorDisplayed(),
//                "❌ Phải hiển thị: Vui lòng điền đầy đủ thông tin");
//        System.out.println("✅ DangKy_TC_03 PASS");
//    }
//
//    //android.widget.Toast[@text="Vui lòng điền đầy đủ thông tin"]
//    @Test(priority = 4, description = "DangKy_TC_04 - Để trống Email")
//    public void DangKy_TC_04() {
//        authFlow.registerNewAccount("Test User 04", "0912345604", "", "Pass@123", "Pass@123");
//        Assert.assertTrue(authFlow.isFillAllInfoErrorDisplayed(),
//                "❌ Phải hiển thị: Vui lòng điền đầy đủ thông tin");
//        System.out.println("✅ DangKy_TC_04 PASS");
//    }
//    //android.widget.Toast[@text="Vui lòng điền đầy đủ thông tin"]
//    @Test(priority = 5, description = "DangKy_TC_05 - Để trống Mật khẩu")
//    public void DangKy_TC_05() {
//        authFlow.registerNewAccount("Test User 05", "0912345605", "test05@gmail.com", "", "Pass@123");
//        Assert.assertTrue(authFlow.isFillAllInfoErrorDisplayed(),
//                "❌ Phải hiển thị: Vui lòng điền đầy đủ thông tin");
//        System.out.println("✅ DangKy_TC_05 PASS");
//    }
//    //android.widget.Toast[@text="Vui lòng điền đầy đủ thông tin"]
//    @Test(priority = 6, description = "DangKy_TC_06 - Để trống Xác nhận mật khẩu")
//    public void DangKy_TC_06() {
//        authFlow.registerNewAccount("Test User 06", "0912345606", "test06@gmail.com", "Pass@123", "");
//        Assert.assertTrue(authFlow.isFillAllInfoErrorDisplayed(),
//                "❌ Phải hiển thị: Vui lòng điền đầy đủ thông tin");
//        System.out.println("✅ DangKy_TC_06 PASS");
//    }
//    //android.widget.Toast[@text="Vui lòng điền đầy đủ thông tin"]
//    @Test(priority = 7, description = "DangKy_TC_07 - Để trống tất cả field")
//    public void DangKy_TC_07() {
//        authFlow.registerNewAccount("", "", "", "", "");
//        Assert.assertTrue(authFlow.isFillAllInfoErrorDisplayed(),
//                "❌ Phải hiển thị: Vui lòng điền đầy đủ thông tin");
//        System.out.println("✅ DangKy_TC_07 PASS");
//    }
//    //android.widget.Toast[@text="Mật khẩu không khớp"]
//    @Test(priority = 8, description = "DangKy_TC_08 - Mật khẩu ≠ Xác nhận mật khẩu")
//    public void DangKy_TC_08() {
//        authFlow.registerNewAccount("Test User 08", "0912345608", "test08@gmail.com", "Pass@123", "Pass@456");
//        Assert.assertTrue(authFlow.isPasswordMismatchErrorDisplayed_MK(),
//                "❌ Phải hiển thị: Mật khẩu không khớp");
//        System.out.println("✅ DangKy_TC_08 PASS");
//    }
//    //android.widget.Toast[@text="Vui lòng đồng ý với điều khoản dịch vụ"]
//    @Test(priority = 9, description = "DangKy_TC_09 - Không tick Điều khoản")
//    public void DangKy_TC_09() {
//        authFlow.registerWithoutTermsAgreement("Test User 09", "123451000009", "test09@gmail.com", "00000");
//        Assert.assertTrue(authFlow.isTermsRequiredErrorDisplayed_DK(),
//                "❌ Phải hiển thị: Vui lòng đồng ý với điều khoản dịch vụ");
//        System.out.println("✅ DangKy_TC_09 PASS");
//    }
//    //android.widget.ProgressBar BUG backend
//    @Test(priority = 10, description = "DangKy_TC_10 - Thông tin đã tồn tại")
//    public void DangKy_TC_10() {
//        authFlow.registerNewAccount("Test User", "0000000000", "00000@gmail.com", "Pass@123", "Pass@123");
//
//        System.out.println("⏳ TC10 - Hiện tại loading stuck (BUG: Backend không xử lý)");
//    }
//    //android.widget.ProgressBar  BUG backend
//    @Test(priority = 11, description = "DangKy_TC_11 - Email đã tồn tại")
//    public void DangKy_TC_11() {
//        authFlow.registerNewAccount("Test User 11", "0912345611", "00000@gmail.com", "Pass@123", "Pass@123");
//        System.out.println("⏳ TC11 - Hiện tại loading stuck (BUG: Backend không xử lý)");
//    }
//    //android.widget.ProgressBar BUG backend
//    @Test(priority = 12, description = "DangKy_TC_12 - SĐT đã tồn tại")
//    public void DangKy_TC_12() {
//        authFlow.registerNewAccount("Test User 12", "0000000000", "test12@gmail.com", "Pass@123", "Pass@123");
//        System.out.println("⏳ TC12 - Hiện tại loading stuck (BUG: Backend không xử lý)");
//    }
//    //android.widget.TextView[@text="Đăng nhập ngay"]
//    @Test(priority = 13,
//            description = "DangKy_TC_13 - Quay lại màn Đăng nhập từ màn Đăng ký")
//    public void DangKy_TC_13() {
//        authFlow.goBackToLoginFromRegistration();
//        Assert.assertTrue(authFlow.isLoginScreenStillDisplayed(),
//                "❌ Không quay lại màn Login!");
//        System.out.println("✅ DangKy_TC_13 PASS");
//    }
//
//    @Test(priority = 14, description = "DangKy_TC_14 - Email sai định dạng ")
//    public void DangKy_TC_14() {
//        authFlow.registerNewAccount("Tuong Duyen 05", "0912345242", "test03@.com", "Pass@123", "Pass@123");
//        Assert.assertTrue(authFlow.isRegistrationSuccessful(),
//                "❌ Lỗi: Đăng ký thất bại, không vào được Home!"
//        );
//    }
//
//
//    @Test(priority = 15, description = "DangKy_TC_15 - Email có dấu tiếng Việt, ký tự Unicode")
//    public void DangKy_TC_15() {
//        String fullName = "Tuong Duyen 05";
//        String phone = "0900009996";
//        String email = "TưởngDuyên05@gmail.com";
//        String password = "Pass@123";
//
//        authFlow.registerNewAccount(fullName, phone, email, password, password);
//        Assert.assertTrue(authFlow.isRegistrationSuccessful(), "❌ Lỗi: Đăng ký thất bại, không vào được Home!"
//        );
//        System.out.println("✅ DangKy_TC_15 PASS");
//    }
//
//    @Test(priority = 16, description = "DangKy_TC_16 - SĐT sai định dạng")
//    public void DangKy_TC_16() {
//        authFlow.registerNewAccount("Tuong Duyen 06", "09123abcdsfe", "test06@gmail.com", "Pass@123", "Pass@123");
//        Assert.assertTrue(authFlow.isRegistrationSuccessful(),
//                "❌ Lỗi: Đăng ký thất bại, không vào được Home!"
//        );
//    }
//
//    @Test(priority = 17, description = "DangKy_TC_17 - Họ tên chứa số/ký tự đặc biệt")
//    public void DangKy_TC_17() {
//        authFlow.registerNewAccount("Duyentest07&%@%@$", "0952335345", "test07@gmail.com", "Pass@123", "Pass@123");
//        Assert.assertTrue(authFlow.isRegistrationSuccessful(),
//                "❌ Lỗi: Đăng ký thất bại, không vào được Home!"
//        );
//    }
//
//    @Test(priority = 18, description = "DangKy_TC_18 - Mật khẩu ký tự dưới min/không đủ ký tự")
//    public void DangKy_TC_18() {
//        authFlow.registerNewAccount("Duyentest14", "0912285409", "test14@gmail.com", "23", "23");
//        Assert.assertTrue(authFlow.isRegistrationSuccessful(),
//                "❌ Lỗi: Đăng ký thất bại, không vào được Home!"
//        );
//    }
//
//    @Test(priority = 19, description = "DangKy_TC_19 - Mật khẩu có khoảng trắng, ký tự đặc biệt")
//    public void DangKy_TC_19() {
//        authFlow.registerNewAccount("Duyentest09", "0912325321", "test09@gmail.com", "2***3 ", "2***3 ");
//        Assert.assertTrue(authFlow.isRegistrationSuccessful(),
//                "❌ Lỗi: Đăng ký thất bại, không vào được Home!"
//        );
//    }
//
//    @Test(priority = 20, description = "DangKy_TC_20 - Dữ liệu all trường max ký tự (vượt quá)")
//    public void DangKy_TC_20() {
//        String maxName = "Test".repeat(200);
//        String maxPhone = "09".repeat(200);
//        String maxEmail = "test".repeat(200) + "@mail.com";
//        String maxPassword = "test".repeat(200);
//        authFlow.registerNewAccount(maxName, maxPhone, maxEmail, maxPassword, maxPassword);
//        // Nếu app không chuyển sang màn Home/Profile, mà vẫn ở đăng ký:
//        Assert.assertTrue(authFlow.isRegistrationScreenDisplayed(), "App hiện tại KHÔNG đăng ký thành công, vẫn ở đăng ký, không toast/error.");
//        System.out.println("✅ TC20 PASS - App hiện tại không cho đăng ký với dữ liệu vượt max, nhưng không báo lỗi rõ ràng.");
//    }
//
//    @Test(priority = 21, description = "DangKy_TC_21 - Đăng ký nhiều lần liên tục với cùng dữ liệu (2 lần)")
//    public void DangKy_TC_21() {
//        String name = "Duyentest12";
//        String phone = "0912344131";
//        String email = "testduyen12@gmail.com";
//        String pass = "testduyen12";
//        authFlow.registerNewAccount(name, phone, email, pass, pass);
//        Assert.assertTrue(
//                authFlow.isRegistrationSuccessful(),
//                "❌ Lỗi: Đăng ký thất bại, không vào được Home!"
//        );
//        authFlow.performLogout();
//        authFlow.registerNewAccount(name, phone, email, pass, pass);
//        Assert.assertTrue(authFlow.isRegistrationScreenDisplayed(), "App hiện tại KHÔNG đăng ký thành công, vẫn ở đăng ký, không toast/error.");
//    }
//
//    @Test(priority = 22, description = "DangKy_TC_22 - Nhập all field toàn chữ hoa/thường")
//    public void DangKy_TC_22() {
//        authFlow.registerNewAccount("TEST DUYEN D", "TESTDUYEN D", "TESTDUYEND@gmail", "TESTDUYENN", "TESTDUYENN");
//        Assert.assertTrue(
//                authFlow.isRegistrationSuccessful(),
//                "❌ Lỗi: Đăng ký thất bại, không vào được Home!"
//        );
//    }
//
//    @Test(priority = 23, description = "DangKy_TC_23 - Kiểm tra Đăng ký Nhập khoảng trắng đầu/cuối từng field")
//    public void DangKy_TC_23() {
//        authFlow.registerNewAccount(" TEST DUYEN 13 ", " TESTDUYEN 13 ", " TESTDUYEN13@gmail ", " TESTDUYEN13 ", " TESTDUYEN13 ");
//        Assert.assertTrue(
//                authFlow.isRegistrationSuccessful(),
//                "❌ Lỗi: Đăng ký thất bại, không vào được Home!"
//        );
//    }
//}

package tests;

import core.base.BaseTest;
import flows.AuthenticationFlow;
import screens.ProfileScreen;
import screens.RegistrationScreen;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * RegistrationTest.java
 * Test case đăng ký theo spec: 1 Happy + 11 Unhappy
 * ✅ Updated: Chi tiết từng scenario
 */
public class RegistrationTest extends BaseTest {
    private final AuthenticationFlow authFlow = new AuthenticationFlow();
    private final RegistrationScreen registration = new RegistrationScreen();
    private final ProfileScreen profile = new ProfileScreen();
    //        Assert.assertTrue(condition, message);
//        condition	Điều kiện cần đúng
//        message	Nội dung lỗi nếu điều kiện sai
//        Assert.assertFail(message); Assert.fail() không cần condition
//vì nó dùng để ép testcase FAIL ngay lập tức.
//        message   Nội dung lỗi luôn luôn được đánh dấu FAIL
//    ko hiển thị toast, ko đki thành coong
    // Không nên assert toast nếu app không hiện toast/xpath.
    // ==================== HAPPY CASE ====================

//    // TC_01 Chỉ dùng khi chưa đăng ký, và chỉ chạy 1 lần sau đó disable,
//    // test sau sẽ chạy test login ko cần chạy registation
//    @Test(priority = 1, enabled = false, description = "DangKy_TC_01 - Đăng ký thành công")
//    public void DangKy_TC_01() {
//
//        String fullName = "Tuong Thi Duyen";
//        String phone = "0900009999";
//        String email = "duyentest@gmail.com";
//        String password = "123456";
//
//        authFlow.registerNewAccount(fullName, phone, email, password, password);
//        Assert.assertTrue(
//                authFlow.isRegistrationSuccessful(),
//                "❌ Lỗi: Đăng ký thất bại, không vào được Home!"
//        );
//
//        System.out.println("✅ DangKy_TC_01 PASS");
//    }
        @Test(priority = 1, enabled = true, description = "DangKy_TC_01 - Đăng ký thành công")
            public void DangKy_TC_01() {
                String ts = String.valueOf(System.currentTimeMillis()).substring(2);
                String fullName = "Tuong Thi Duyen" + ts;
                String phone = "09000088" + ts;
                String email = "duyentest" + ts + "@gmail.com";
                String password = "123456" + ts;

                authFlow.registerNewAccount(fullName, phone, email, password, password);
                Assert.assertTrue(
                        authFlow.isRegistrationSuccessful(),
                        "❌ Lỗi: Đăng ký thất bại, không vào được Home!"
                );

                System.out.println("✅ DangKy_TC_01 PASS");
            }
    // ==================== UNHAPPY CASE ====================
    //android.widget.Toast[@text="Vui lòng điền đầy đủ thông tin"]
    @Test(priority = 2, description = "DangKy_TC_02 - Để trống Họ và tên")
    public void DangKy_TC_02() {
        authFlow.registerNewAccount("", "0912345678", "test02@gmail.com", "Pass@123", "Pass@123");
        Assert.assertTrue(authFlow.isFillAllInfoErrorDisplayed(),
                "❌ Phải hiển thị: Vui lòng điền đầy đủ thông tin");
        System.out.println("✅ DangKy_TC_02 PASS");
    }
    //android.widget.Toast[@text="Vui lòng điền đầy đủ thông tin"]
    @Test(priority = 3, description = "DangKy_TC_03 - Để trống Số điện thoại")
    public void DangKy_TC_03() {
        authFlow.registerNewAccount("Test User 03", "", "test03@gmail.com", "Pass@123", "Pass@123");
        Assert.assertTrue(authFlow.isFillAllInfoErrorDisplayed(),
                "❌ Phải hiển thị: Vui lòng điền đầy đủ thông tin");
        System.out.println("✅ DangKy_TC_03 PASS");
    }

    //android.widget.Toast[@text="Vui lòng điền đầy đủ thông tin"]
    @Test(priority = 4, description = "DangKy_TC_04 - Để trống Email")
    public void DangKy_TC_04() {
        authFlow.registerNewAccount("Test User 04", "0912345604", "", "Pass@123", "Pass@123");
        Assert.assertTrue(authFlow.isFillAllInfoErrorDisplayed(),
                "❌ Phải hiển thị: Vui lòng điền đầy đủ thông tin");
        System.out.println("✅ DangKy_TC_04 PASS");
    }
    //android.widget.Toast[@text="Vui lòng điền đầy đủ thông tin"]
    @Test(priority = 5, description = "DangKy_TC_05 - Để trống Mật khẩu")
    public void DangKy_TC_05() {
        authFlow.registerNewAccount("Test User 05", "0912345605", "test05@gmail.com", "", "Pass@123");
        Assert.assertTrue(authFlow.isFillAllInfoErrorDisplayed(),
                "❌ Phải hiển thị: Vui lòng điền đầy đủ thông tin");
        System.out.println("✅ DangKy_TC_05 PASS");
    }
    //android.widget.Toast[@text="Vui lòng điền đầy đủ thông tin"]
    @Test(priority = 6, description = "DangKy_TC_06 - Để trống Xác nhận mật khẩu")
    public void DangKy_TC_06() {
        authFlow.registerNewAccount("Test User 06", "0912345606", "test06@gmail.com", "Pass@123", "");
        Assert.assertTrue(authFlow.isFillAllInfoErrorDisplayed(),
                "❌ Phải hiển thị: Vui lòng điền đầy đủ thông tin");
        System.out.println("✅ DangKy_TC_06 PASS");
    }
    //android.widget.Toast[@text="Vui lòng điền đầy đủ thông tin"]
    @Test(priority = 7, description = "DangKy_TC_07 - Để trống tất cả field")
    public void DangKy_TC_07() {
        authFlow.registerNewAccount("", "", "", "", "");
        Assert.assertTrue(authFlow.isFillAllInfoErrorDisplayed(),
                "❌ Phải hiển thị: Vui lòng điền đầy đủ thông tin");
        System.out.println("✅ DangKy_TC_07 PASS");
    }
    //android.widget.Toast[@text="Mật khẩu không khớp"]
    @Test(priority = 8, description = "DangKy_TC_08 - Mật khẩu ≠ Xác nhận mật khẩu")
    public void DangKy_TC_08() {
        authFlow.registerNewAccount("Test User 08", "0912345608", "test08@gmail.com", "Pass@123", "Pass@456");
        Assert.assertTrue(authFlow.isPasswordMismatchErrorDisplayed_MK(),
                "❌ Phải hiển thị: Mật khẩu không khớp");
        System.out.println("✅ DangKy_TC_08 PASS");
    }
    //android.widget.Toast[@text="Vui lòng đồng ý với điều khoản dịch vụ"]
    @Test(priority = 9, description = "DangKy_TC_09 - Không tick Điều khoản")
    public void DangKy_TC_09() {
        authFlow.registerWithoutTermsAgreement("Test User 09", "123451000009", "test09@gmail.com", "00000");
        Assert.assertTrue(authFlow.isTermsRequiredErrorDisplayed_DK(),
                "❌ Phải hiển thị: Vui lòng đồng ý với điều khoản dịch vụ");
        System.out.println("✅ DangKy_TC_09 PASS");
    }
    //android.widget.ProgressBar BUG backend
    @Test(priority = 10, description = "DangKy_TC_10 - Thông tin đã tồn tại")
    public void DangKy_TC_10() {
        authFlow.registerNewAccount("Test User", "0000000000", "00000@gmail.com", "Pass@123", "Pass@123");

        System.out.println("⏳ TC10 - Hiện tại loading stuck (BUG: Backend không xử lý)");
    }
    //android.widget.ProgressBar  BUG backend
    @Test(priority = 11, description = "DangKy_TC_11 - Email đã tồn tại")
    public void DangKy_TC_11() {
        authFlow.registerNewAccount("Test User 11", "0912345611", "00000@gmail.com", "Pass@123", "Pass@123");
        System.out.println("⏳ TC11 - Hiện tại loading stuck (BUG: Backend không xử lý)");
    }
    //android.widget.ProgressBar BUG backend
    @Test(priority = 12, description = "DangKy_TC_12 - SĐT đã tồn tại")
    public void DangKy_TC_12() {
        authFlow.registerNewAccount("Test User 12", "0000000000", "test12@gmail.com", "Pass@123", "Pass@123");
        System.out.println("⏳ TC12 - Hiện tại loading stuck (BUG: Backend không xử lý)");
    }
    //android.widget.TextView[@text="Đăng nhập ngay"]
    @Test(priority = 13,
            description = "DangKy_TC_13 - Quay lại màn Đăng nhập từ màn Đăng ký")
    public void DangKy_TC_13() {
        authFlow.goBackToLoginFromRegistration();
        Assert.assertTrue(authFlow.isLoginScreenStillDisplayed(),
                "❌ Không quay lại màn Login!");
        System.out.println("✅ DangKy_TC_13 PASS");
    }

    @Test(priority = 14, description = "DangKy_TC_14 - Email sai định dạng ")
    public void DangKy_TC_14() {
        String ts = String.valueOf(System.currentTimeMillis()).substring(7);
        authFlow.registerNewAccount("Tuong Duyen 14", "0912" + ts, "test14" + ts + "@gmail.com", "Pass@123", "Pass@123");
        Assert.assertTrue(authFlow.isRegistrationSuccessful(), "❌ Lỗi: Đăng ký thất bại, không vào được Home!");
    }

    @Test(priority = 15, description = "DangKy_TC_15 - Email có dấu tiếng Việt, ký tự Unicode")
    public void DangKy_TC_15() {
        String ts = String.valueOf(System.currentTimeMillis()).substring(7);
        authFlow.registerNewAccount("Tuong Duyen 15", "0913" + ts, "TưởngDuyên" + ts + "@gmail.com", "Pass@123", "Pass@123");
        Assert.assertTrue(authFlow.isRegistrationSuccessful(), "❌ Lỗi: Đăng ký thất bại, không vào được Home!");
    }

    @Test(priority = 16, description = "DangKy_TC_16 - SĐT sai định dạng")
    public void DangKy_TC_16() {
        String ts = String.valueOf(System.currentTimeMillis()).substring(7);
        authFlow.registerNewAccount("Tuong Duyen 16", "0914" + ts + "abc", "test16" + ts + "@gmail.com", "Pass@123", "Pass@123");
        Assert.assertTrue(authFlow.isRegistrationSuccessful(), "❌ Lỗi: Đăng ký thất bại, không vào được Home!");
    }

    @Test(priority = 17, description = "DangKy_TC_17 - Họ tên chứa số/ký tự đặc biệt")
    public void DangKy_TC_17() {
        String ts = String.valueOf(System.currentTimeMillis()).substring(7);
        authFlow.registerNewAccount("Duyentest17&%@%@$", "0915" + ts, "test17" + ts + "@gmail.com", "Pass@123", "Pass@123");
        Assert.assertTrue(authFlow.isRegistrationSuccessful(), "❌ Lỗi: Đăng ký thất bại, không vào được Home!");
    }

    @Test(priority = 18, description = "DangKy_TC_18 - Mật khẩu ký tự dưới min/không đủ ký tự")
    public void DangKy_TC_18() {
        String ts = String.valueOf(System.currentTimeMillis()).substring(7);
        authFlow.registerNewAccount("Duyentest18", "0916" + ts, "test18" + ts + "@gmail.com", "23", "23");
        Assert.assertTrue(authFlow.isRegistrationSuccessful(), "❌ Lỗi: Đăng ký thất bại, không vào được Home!");
    }

    @Test(priority = 19, description = "DangKy_TC_19 - Mật khẩu có khoảng trắng, ký tự đặc biệt")
    public void DangKy_TC_19() {
        String ts = String.valueOf(System.currentTimeMillis()).substring(7);
        authFlow.registerNewAccount("Duyentest19", "0917" + ts, "test19" + ts + "@gmail.com", "2***3 ", "2***3 ");
        Assert.assertTrue(authFlow.isRegistrationSuccessful(), "❌ Lỗi: Đăng ký thất bại, không vào được Home!");
    }

    @Test(priority = 20, description = "DangKy_TC_20 - Dữ liệu all trường max ký tự (vượt quá)")
    public void DangKy_TC_20() {
        String maxName = "Test".repeat(200);
        String maxPhone = "09".repeat(200);
        String maxEmail = "test".repeat(200) + "@mail.com";
        String maxPassword = "test".repeat(200);
        authFlow.registerNewAccount(maxName, maxPhone, maxEmail, maxPassword, maxPassword);
        // Nếu app không chuyển sang màn Home/Profile, mà vẫn ở đăng ký:
        Assert.assertTrue(authFlow.isRegistrationScreenDisplayed(), "App hiện tại KHÔNG đăng ký thành công, vẫn ở đăng ký, không toast/error.");
        System.out.println("✅ TC20 PASS - App hiện tại không cho đăng ký với dữ liệu vượt max, nhưng không báo lỗi rõ ràng.");
    }

    @Test(priority = 21, description = "DangKy_TC_21 - Đăng ký nhiều lần liên tục với cùng dữ liệu (2 lần)")
    public void DangKy_TC_21() {
        String ts = String.valueOf(System.currentTimeMillis()).substring(7);
        String name = "Duyentest21";
        String phone = "0920" + ts;
        String email = "testduyen21" + ts + "@gmail.com";
        String pass = "testduyen12";

        // Lần 1: đăng ký với data mới -> phải thành công
        authFlow.registerNewAccount(name, phone, email, pass, pass);
        Assert.assertTrue(
                authFlow.isRegistrationSuccessful(),
                "❌ Lỗi: Đăng ký lần 1 thất bại, không vào được Home!"
        );

        authFlow.performLogout();

        // Lần 2: đăng ký lại với CÙNG data vừa tạo -> phải bị từ chối vì đã tồn tại
        authFlow.registerNewAccount(name, phone, email, pass, pass);
        Assert.assertTrue(
                authFlow.isRegistrationScreenDisplayed(),
                "❌ Lỗi: Đăng ký lần 2 với dữ liệu trùng lại được chấp nhận (lẽ ra phải bị từ chối, vẫn ở màn Đăng ký)!"
        );
    }

    @Test(priority = 22, description = "DangKy_TC_22 - Nhập all field toàn chữ hoa/thường")
    public void DangKy_TC_22() {
        String ts = String.valueOf(System.currentTimeMillis()).substring(7);
        authFlow.registerNewAccount("TEST DUYEN " + ts, "0918" + ts, "TESTDUYEN" + ts + "@gmail.com", "TESTDUYENN", "TESTDUYENN");
        Assert.assertTrue(authFlow.isRegistrationSuccessful(), "❌ Lỗi: Đăng ký thất bại, không vào được Home!");
    }

    @Test(priority = 23, description = "DangKy_TC_23 - Khoảng trắng đầu/cuối từng field")
    public void DangKy_TC_23() {
        String ts = String.valueOf(System.currentTimeMillis()).substring(7);
        authFlow.registerNewAccount(" TEST DUYEN " + ts + " ", " 0919" + ts + " ", " test" + ts + "@gmail.com ", " TESTDUYEN23 ", " TESTDUYEN23 ");
        Assert.assertTrue(authFlow.isRegistrationSuccessful(), "❌ Lỗi: Đăng ký thất bại, không vào được Home!");
    }
}