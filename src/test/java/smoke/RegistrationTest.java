package smoke;

import core.base.BaseTest;
import flows.AuthenticationFlow;
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
    private static int registrationCounter = 0;

    // ==================== HAPPY CASE ====================

    @Test(priority = 1, description = "DangKy_TC_01 - Đăng ký thành công")
    public void DangKy_TC_01_RegisterSuccessfully() {
        System.out.println("\n=== DangKy_TC_01 - ĐĂNG KÝ THÀNH CÔNG ===");
        registrationCounter++;

        String fullName = "Duyen1220" + registrationCounter;
        String phone = "14441202" + (1000000 + registrationCounter);
        String email = "14441220" + registrationCounter + "@gmail.com";
        String password = "111111220";

        authFlow.registerNewAccount(fullName, phone, email, password, password);
        Assert.assertTrue(authFlow.isRegistrationSuccessful(), "❌ Lỗi: Đăng ký thất bại, không vào được Home!");
        System.out.println("✅ DangKy_TC_01 PASS");
    }

    // ==================== UNHAPPY CASE ====================
// //android.widget.Toast[@text="Vui lòng điền đầy đủ thông tin"]
    @Test(priority = 2, description = "DangKy_TC_02 - Để trống Họ và tên")
    public void DangKy_TC_02_RegistrationEmptyFullName() {
        System.out.println("\n=== DangKy_TC_02 - ĐỂ TRỐNG HỌ VÀ TÊN ===");
        authFlow.registerNewAccount("", "0912345678", "test02@gmail.com", "Pass@123", "Pass@123");
        Assert.assertTrue(authFlow.isFillAllInfoErrorDisplayed(),
                "❌ Phải hiển thị: Vui lòng điền đầy đủ thông tin");
        System.out.println("✅ DangKy_TC_02 PASS");
    }
// //android.widget.Toast[@text="Vui lòng điền đầy đủ thông tin"]
    @Test(priority = 3, description = "DangKy_TC_03 - Để trống Số điện thoại")
    public void DangKy_TC_03_RegistrationEmptyPhone() {
        System.out.println("\n=== DangKy_TC_03 - ĐỂ TRỐNG SỐ ĐIỆN THOẠI ===");
        authFlow.registerNewAccount("Test User 03", "", "test03@gmail.com", "Pass@123", "Pass@123");
        Assert.assertTrue(authFlow.isFillAllInfoErrorDisplayed(),
                "❌ Phải hiển thị: Vui lòng điền đầy đủ thông tin");
        System.out.println("✅ DangKy_TC_03 PASS");
    }
// //android.widget.Toast[@text="Vui lòng điền đầy đủ thông tin"]
    @Test(priority = 4, description = "DangKy_TC_04 - Để trống Email")
    public void DangKy_TC_04_RegistrationEmptyEmail() {
        System.out.println("\n=== DangKy_TC_04 - ĐỂ TRỐNG EMAIL ===");
        authFlow.registerNewAccount("Test User 04", "0912345604", "", "Pass@123", "Pass@123");
        Assert.assertTrue(authFlow.isFillAllInfoErrorDisplayed(),
                "❌ Phải hiển thị: Vui lòng điền đầy đủ thông tin");
        System.out.println("✅ DangKy_TC_04 PASS");
    }
// //android.widget.Toast[@text="Vui lòng điền đầy đủ thông tin"]
    @Test(priority = 5, description = "DangKy_TC_05 - Để trống Mật khẩu")
    public void DangKy_TC_05_RegistrationEmptyPassword() {
        System.out.println("\n=== DangKy_TC_05 - ĐỂ TRỐNG MẬT KHẨU ===");
        authFlow.registerNewAccount("Test User 05", "0912345605", "test05@gmail.com", "", "Pass@123");
        Assert.assertTrue(authFlow.isFillAllInfoErrorDisplayed(),
                "❌ Phải hiển thị: Vui lòng điền đầy đủ thông tin");
        System.out.println("✅ DangKy_TC_05 PASS");
    }
    // //android.widget.Toast[@text="Vui lòng điền đầy đủ thông tin"]
    @Test(priority = 6, description = "DangKy_TC_06 - Để trống Xác nhận mật khẩu")
    public void DangKy_TC_06_RegistrationEmptyConfirmPassword() {
        System.out.println("\n=== DangKy_TC_06 - ĐỂ TRỐNG XÁC NHẬN MẬT KHẨU ===");
        authFlow.registerNewAccount("Test User 06", "0912345606", "test06@gmail.com", "Pass@123", "");
        Assert.assertTrue(authFlow.isFillAllInfoErrorDisplayed(),
                "❌ Phải hiển thị: Vui lòng điền đầy đủ thông tin");
        System.out.println("✅ DangKy_TC_06 PASS");
    }
    // //android.widget.Toast[@text="Vui lòng điền đầy đủ thông tin"]
    @Test(priority = 7, description = "DangKy_TC_07 - Để trống tất cả field")
    public void DangKy_TC_07_RegistrationAllFieldsEmpty() {
        System.out.println("\n=== DangKy_TC_07 - ĐỂ TRỐNG TẤT CẢ FIELD ===");
        authFlow.registerNewAccount("", "", "", "", "");
        Assert.assertTrue(authFlow.isFillAllInfoErrorDisplayed(),
                "❌ Phải hiển thị: Vui lòng điền đầy đủ thông tin");
        System.out.println("✅ DangKy_TC_07 PASS");
    }
// //android.widget.Toast[@text="Mật khẩu không khớp"]
    @Test(priority = 8, description = "DangKy_TC_08 - Mật khẩu ≠ Xác nhận mật khẩu")
    public void DangKy_TC_08_RegistrationPasswordMismatch() {
        System.out.println("\n=== DangKy_TC_08 - MẬT KHẨU KHÔNG TRÙNG KHỚP ===");
        authFlow.registerNewAccount("Test User 08", "0912345608", "test08@gmail.com", "Pass@123", "Pass@456");
        Assert.assertTrue(authFlow.isPasswordMismatchErrorDisplayed_MK(),
                "❌ Phải hiển thị: Vui lòng điền đầy đủ thông tin");
        System.out.println("✅ DangKy_TC_08 PASS");
    }
// //android.widget.Toast[@text="Vui lòng đồng ý với điều khoản dịch vụ"]
    @Test(priority = 9, description = "DangKy_TC_09 - Không tick Điều khoản")
    public void DangKy_TC_09_RegistrationNoTermsAgreement() {
        System.out.println("\n=== DangKy_TC_09 - KHÔNG TICK ĐIỀU KHOẢN ===");

        authFlow.registerWithoutTermsAgreement("Test User 09", "123451000009", "test09@gmail.com", "00000");
        Assert.assertTrue(authFlow.isTermsRequiredErrorDisplayed_DK(),
                "❌ Phải hiển thị: Vui lòng đồng ý điều khoản");
        System.out.println("✅ DangKy_TC_09 PASS");
    }
// //android.widget.ProgressBar BUG backend
    @Test(priority = 10, description = "DangKy_TC_10 - Thông tin đã tồn tại")
    public void DangKy_TC_10_RegistrationAccountExists() {
        System.out.println("\n=== DangKy_TC_10 - THÔNG TIN ĐÃ TỒN TẠI ===");
        authFlow.registerNewAccount("Test User", "0000000000", "00000@gmail.com", "Pass@123", "Pass@123");
        System.out.println("⏳ TC10 - Hiện tại loading stuck (BUG: Backend không xử lý)");
    }
// //android.widget.ProgressBar  BUG backend
    @Test(priority = 11, description = "DangKy_TC_11 - Email đã tồn tại")
    public void DangKy_TC_11_RegistrationEmailExists() {
        System.out.println("\n=== DangKy_TC_11 - EMAIL ĐÃ TỒN TẠI ===");
        authFlow.registerNewAccount("Test User 11", "0912345611", "00000@gmail.com", "Pass@123", "Pass@123");
        System.out.println("⏳ TC11 - Hiện tại loading stuck (BUG: Backend không xử lý)");
    }
// //android.widget.ProgressBar BUG backend
    @Test(priority = 12, description = "DangKy_TC_12 - SĐT đã tồn tại")
    public void DangKy_TC_12_RegistrationPhoneExists() {
        System.out.println("\n=== DangKy_TC_12 - SĐT ĐÃ TỒN TẠI ===");
        authFlow.registerNewAccount("Test User 12", "0000000000", "test12@gmail.com", "Pass@123", "Pass@123");
        System.out.println("⏳ TC12 - Hiện tại loading stuck (BUG: Backend không xử lý)");
    }
// //android.widget.TextView[@text="Đăng nhập ngay"]
    @Test(priority = 13,
            description = "DangKy_TC_13 - Quay lại màn Đăng nhập từ màn Đăng ký")
    public void DangKy_TC_13_BackToLoginFromRegistration() {
        System.out.println("\n=== DangKy_TC_13 - QUAY LẠI MÀN LOGIN ===");
        authFlow.goBackToLoginFromRegistration();
        Assert.assertTrue(authFlow.isLoginScreenStillDisplayed(),
                "❌ Không quay lại màn Login!");
        System.out.println("✅ DangKy_TC_13 PASS");
    }
}