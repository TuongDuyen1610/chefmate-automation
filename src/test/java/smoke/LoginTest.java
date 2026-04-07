//package smoke;
//
//import core.base.BaseTest;
//import flows.AuthenticationFlow;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//public class LoginTest extends BaseTest {
//
//    private final AuthenticationFlow authFlow = new AuthenticationFlow();
//
//    // ==================== HAPPY CASE: PHONE ====================
//
//    @Test(priority = 1, description = "DangNhap_TC_01 - Đăng nhập Tủ lạnh + SĐT")
//    public void DangNhap_TC_01_LoginFridgePhone() {
//        System.out.println("\n=== DangNhap_TC_01 - ĐĂNG NHẬP TỦ LẠNH + SĐT ===");
//        authFlow.loginFromFridgeTab("0000000000", "000000");
//        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Lỗi: Không vào được Home!");
//        System.out.println("✅ DangNhap_TC_01 PASS");
//    }
//
//    @Test(priority = 2, description = "DangNhap_TC_02 - Đăng nhập Tủ lạnh + Email")
//    public void DangNhap_TC_02_LoginFridgeEmail() {
//        System.out.println("\n=== DangNhap_TC_02 - ĐĂNG NHẬP TỦ LẠNH + EMAIL ===");
//        authFlow.loginFromFridgeTab("00000@gmail.com", "000000");
//        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Lỗi: Không vào được Home!");
//        System.out.println("✅ DangNhap_TC_02 PASS");
//    }
//
//    @Test(priority = 3, description = "DangNhap_TC_03 - Đăng nhập Tài khoản + SĐT")
//    public void DangNhap_TC_03_LoginProfilePhone() {
//        System.out.println("\n=== DangNhap_TC_03 - ĐĂNG NHẬP TÀI KHOẢN + SĐT ===");
//        authFlow.loginFromProfileTab("0000000000", "000000");
//        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Lỗi: Không vào được Home!");
//        System.out.println("✅ DangNhap_TC_03 PASS");
//    }
//
//    @Test(priority = 4, description = "DangNhap_TC_04 - Đăng nhập Tài khoản + Email")
//    public void DangNhap_TC_04_LoginProfileEmail() {
//        System.out.println("\n=== DangNhap_TC_04 - ĐĂNG NHẬP TÀI KHOẢN + EMAIL ===");
//        authFlow.loginFromProfileTab("00000@gmail.com", "000000");
//        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Lỗi: Không vào được Home!");
//        System.out.println("✅ DangNhap_TC_04 PASS");
//    }
//
//    @Test(priority = 5, description = "DangNhap_TC_05 - Đăng nhập Bepes + SĐT")
//    public void DangNhap_TC_05_LoginBepesPhone() {
//        System.out.println("\n=== DangNhap_TC_05 - ĐĂNG NHẬP BEPES + SĐT ===");
//        authFlow.loginFromBepesAIButton("0000000000", "000000");
//        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Lỗi: Không vào được Home!");
//        System.out.println("✅ DangNhap_TC_05 PASS");
//    }
//
//    @Test(priority = 6, description = "DangNhap_TC_06 - Đăng nhập Bepes + Email")
//    public void DangNhap_TC_06_LoginBepesEmail() {
//        System.out.println("\n=== DangNhap_TC_06 - ĐĂNG NHẬP BEPES + EMAIL ===");
//        authFlow.loginFromBepesAIButton("00000@gmail.com", "000000");
//        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Lỗi: Không vào được Home!");
//        System.out.println("✅ DangNhap_TC_06 PASS");
//    }
//
//    @Test(priority = 7, description = "DangNhap_TC_07 - Đăng nhập Gợi ý + SĐT")
//    public void DangNhap_TC_07_LoginSuggestionPhone() {
//        System.out.println("\n=== DangNhap_TC_07 - ĐĂNG NHẬP GỢI Ý + SĐT ===");
//        authFlow.loginFromFridgeSuggestionButton("0000000000", "000000");
//        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Lỗi: Không vào được Home!");
//        System.out.println("✅ DangNhap_TC_07 PASS");
//    }
//
//    @Test(priority = 8, description = "DangNhap_TC_08 - Đăng nhập Gợi ý + Email")
//    public void DangNhap_TC_08_LoginSuggestionEmail() {
//        System.out.println("\n=== DangNhap_TC_08 - ĐĂNG NHẬP GỢI Ý + EMAIL ===");
//        authFlow.loginFromFridgeSuggestionButton("00000@gmail.com", "000000");
//        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Lỗi: Không vào được Home!");
//        System.out.println("✅ DangNhap_TC_08 PASS");
//    }
//
//    // ==================== UNHAPPY CASE ====================
//
//    @Test(priority = 9, description = "DangNhap_TC_09 - Sai tài khoản/mật khẩu")
//    public void DangNhap_TC_09_LoginInvalidCredentials() {
//        System.out.println("\n=== DangNhap_TC_09 - SAI TÀI KHOẢN/MẬT KHẨU ===");
//        authFlow.loginFromFridgeTab("wrongaccount@gmail.com", "wrongpassword");
//        Assert.assertTrue(authFlow.isLoginScreenStillDisplayed(), "Lỗi: Phải ở lại màn Login!");
//        System.out.println("✅ DangNhap_TC_09 PASS");
//    }
//
//    @Test(priority = 10, description = "DangNhap_TC_10 - Để trống SĐT/Email")
//    public void DangNhap_TC_10_LoginEmptyPhone() {
//        System.out.println("\n=== DangNhap_TC_10 - ĐỂ TRỐNG SĐT/EMAIL ===");
//        authFlow.TC_10("00000");
//        Assert.assertTrue(authFlow.isToastUpdateInfoDisplayed_TC1011(), "Lỗi: Khong hien thi toast khi de trong thong tin");
//        System.out.println("⏳ TC10 - Chưa implement (cần thêm logic để trống field)");
//    }
//
//    @Test(priority = 11, description = "DangNhap_TC_11 - Để trống Mật khẩu")
//    public void DangNhap_TC_11_LoginEmptyPassword() {
//        System.out.println("\n=== DangNhap_TC_11 - ĐỂ TRỐNG MẬT KHẨU ===");
//        authFlow.TC_11("00000");
//        Assert.assertTrue(authFlow.isToastUpdateInfoDisplayed_TC1011(), "Lỗi: Khong hien thi toast khi de trong thong tin");
//        System.out.println("⏳ TC11 - Chưa implement (cần thêm logic để trống field)");
//    }
//}


package smoke;

import core.base.BaseTest;
import flows.AuthenticationFlow;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LoginTest.java
 * ✅ COMPLETE: Tích hợp Allure Report 100%
 */
@Feature("Authentication")
public class LoginTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);
    private final AuthenticationFlow authFlow = new AuthenticationFlow();

    // ==================== HAPPY CASE: PHONE ====================

    @Test(priority = 1, description = "DangNhap_TC_01 - Đăng nhập Tủ lạnh + SĐT")
    @Story("Login from Fridge Tab")
    @Description("User dapat login successfully using valid phone number from Fridge tab")
    @Severity(SeverityLevel.CRITICAL)
    public void DangNhap_TC_01_LoginFridgePhone() {
        logger.info("\n╔════════════════════════════════════════════════╗");
        logger.info("║ DangNhap_TC_01 - ĐĂNG NHẬP TỦ LẠNH + SĐT      ║");
        logger.info("╚════════════════════════════════════════════════╝");

        try {
            // ARRANGE
            String phoneOrEmail = "0961960320";
            String password = "123456";

            // ACT
            authFlow.loginFromFridgeTab(phoneOrEmail, password);

            // ASSERT
            boolean isSuccess = authFlow.isLoggedInSuccessfully();
            Assert.assertTrue(isSuccess, "Lỗi: Không vào được Home!");

            logger.info("✅ DangNhap_TC_01 PASSED\n");
        } catch (Exception e) {
            logger.error("❌ DangNhap_TC_01 FAILED: " + e.getMessage());
            throw e;
        }
    }

//    @Test(priority = 2, description = "DangNhap_TC_02 - Đăng nhập Tủ lạnh + Email")
//    @Story("Login from Fridge Tab")
//    @Description("User dapat login successfully using valid email from Fridge tab")
//    @Severity(SeverityLevel.CRITICAL)
//    public void DangNhap_TC_02_LoginFridgeEmail() {
//        logger.info("\n╔════════════════════════════════════════════════╗");
//        logger.info("║ DangNhap_TC_02 - ĐĂNG NHẬP TỦ LẠNH + EMAIL    ║");
//        logger.info("╚════════════════════════════════════════════════╝");
//
//        try {
//            String phoneOrEmail = "123456@gmail.com";
//            String password = "123456";
//
//            authFlow.loginFromFridgeTab(phoneOrEmail, password);
//
//            boolean isSuccess = authFlow.isLoggedInSuccessfully();
//            Assert.assertTrue(isSuccess, "Lỗi: Không vào được Home!");
//
//            logger.info("✅ DangNhap_TC_02 PASSED\n");
//        } catch (Exception e) {
//            logger.error("❌ DangNhap_TC_02 FAILED: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    @Test(priority = 3, description = "DangNhap_TC_03 - Đăng nhập Tài khoản + SĐT")
//    @Story("Login from Profile Tab")
//    @Description("User dapat login successfully using valid phone number from Profile tab")
//    @Severity(SeverityLevel.CRITICAL)
//    public void DangNhap_TC_03_LoginProfilePhone() {
//        logger.info("\n╔════════════════════════════════════════════════╗");
//        logger.info("║ DangNhap_TC_03 - ĐĂNG NHẬP TÀI KHOẢN + SĐT    ║");
//        logger.info("╚════════════════════════════════════════════════╝");
//
//        try {
//            String phoneOrEmail = "0961960320";
//            String password = "123456";
//
//            authFlow.loginFromProfileTab(phoneOrEmail, password);
//
//            boolean isSuccess = authFlow.isLoggedInSuccessfully();
//            Assert.assertTrue(isSuccess, "Lỗi: Không vào được Home!");
//
//            logger.info("✅ DangNhap_TC_03 PASSED\n");
//        } catch (Exception e) {
//            logger.error("❌ DangNhap_TC_03 FAILED: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    @Test(priority = 4, description = "DangNhap_TC_04 - Đăng nhập Tài khoản + Email")
//    @Story("Login from Profile Tab")
//    @Description("User dapat login successfully using valid email from Profile tab")
//    @Severity(SeverityLevel.CRITICAL)
//    public void DangNhap_TC_04_LoginProfileEmail() {
//        logger.info("\n╔════════════════════════════════════════════════╗");
//        logger.info("║ DangNhap_TC_04 - ĐĂNG NHẬP TÀI KHOẢN + EMAIL  ║");
//        logger.info("╚════════════════════════════════════════════════╝");
//
//        try {
//            String phoneOrEmail = "123456@gmail.com";
//            String password = "123456";
//
//            authFlow.loginFromProfileTab(phoneOrEmail, password);
//
//            boolean isSuccess = authFlow.isLoggedInSuccessfully();
//            Assert.assertTrue(isSuccess, "Lỗi: Không vào được Home!");
//
//            logger.info("✅ DangNhap_TC_04 PASSED\n");
//        } catch (Exception e) {
//            logger.error("❌ DangNhap_TC_04 FAILED: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    @Test(priority = 5, description = "DangNhap_TC_05 - Đăng nhập Bepes + SĐT")
//    @Story("Login from Bepes AI Button")
//    @Description("User dapat login successfully using valid phone number from Bepes AI button")
//    @Severity(SeverityLevel.CRITICAL)
//    public void DangNhap_TC_05_LoginBepesPhone() {
//        logger.info("\n╔════════════════════════════════════════════════╗");
//        logger.info("║ DangNhap_TC_05 - ĐĂNG NHẬP BEPES + SĐT        ║");
//        logger.info("╚════════════════════════════════════════════════╝");
//
//        try {
//            String phoneOrEmail = "0961960320";
//            String password = "123456";
//
//            authFlow.loginFromBepesAIButton(phoneOrEmail, password);
//
//            boolean isSuccess = authFlow.isLoggedInSuccessfully();
//            Assert.assertTrue(isSuccess, "Lỗi: Không vào được Home!");
//
//            logger.info("✅ DangNhap_TC_05 PASSED\n");
//        } catch (Exception e) {
//            logger.error("❌ DangNhap_TC_05 FAILED: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    @Test(priority = 6, description = "DangNhap_TC_06 - Đăng nhập Bepes + Email")
//    @Story("Login from Bepes AI Button")
//    @Description("User dapat login successfully using valid email from Bepes AI button")
//    @Severity(SeverityLevel.CRITICAL)
//    public void DangNhap_TC_06_LoginBepesEmail() {
//        logger.info("\n╔════════════════════════════════════════════════╗");
//        logger.info("║ DangNhap_TC_06 - ĐĂNG NHẬP BEPES + EMAIL      ║");
//        logger.info("╚════════════════════════════════════════════════╝");
//
//        try {
//            String phoneOrEmail = "123456@gmail.com";
//            String password = "123456";
//
//            authFlow.loginFromBepesAIButton(phoneOrEmail, password);
//
//            boolean isSuccess = authFlow.isLoggedInSuccessfully();
//            Assert.assertTrue(isSuccess, "Lỗi: Không vào được Home!");
//
//            logger.info("✅ DangNhap_TC_06 PASSED\n");
//        } catch (Exception e) {
//            logger.error("❌ DangNhap_TC_06 FAILED: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    @Test(priority = 7, description = "DangNhap_TC_07 - Đăng nhập Gợi ý + SĐT")
//    @Story("Login from Fridge Suggestion Button")
//    @Description("User dapat login successfully using valid phone number from Fridge suggestion button")
//    @Severity(SeverityLevel.CRITICAL)
//    public void DangNhap_TC_07_LoginSuggestionPhone() {
//        logger.info("\n╔════════════════════════════════════════════════╗");
//        logger.info("║ DangNhap_TC_07 - ĐĂNG NHẬP GỢI Ý + SĐT       ║");
//        logger.info("╚════════════════════════════════════════════════╝");
//
//        try {
//            String phoneOrEmail = "0961960320";
//            String password = "123456";
//
//            authFlow.loginFromFridgeSuggestionButton(phoneOrEmail, password);
//
//            boolean isSuccess = authFlow.isLoggedInSuccessfully();
//            Assert.assertTrue(isSuccess, "Lỗi: Không vào được Home!");
//
//            logger.info("✅ DangNhap_TC_07 PASSED\n");
//        } catch (Exception e) {
//            logger.error("❌ DangNhap_TC_07 FAILED: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    @Test(priority = 8, description = "DangNhap_TC_08 - Đăng nhập Gợi ý + Email")
//    @Story("Login from Fridge Suggestion Button")
//    @Description("User dapat login successfully using valid email from Fridge suggestion button")
//    @Severity(SeverityLevel.CRITICAL)
//    public void DangNhap_TC_08_LoginSuggestionEmail() {
//        logger.info("\n╔════════════════════════════════════════════════╗");
//        logger.info("║ DangNhap_TC_08 - ĐĂNG NHẬP GỢI Ý + EMAIL     ║");
//        logger.info("╚════════════════════════════════════════════════╝");
//
//        try {
//            String phoneOrEmail = "123456@gmail.com";
//            String password = "123456";
//
//            authFlow.loginFromFridgeSuggestionButton(phoneOrEmail, password);
//
//            boolean isSuccess = authFlow.isLoggedInSuccessfully();
//            Assert.assertTrue(isSuccess, "Lỗi: Không vào được Home!");
//
//            logger.info("✅ DangNhap_TC_08 PASSED\n");
//        } catch (Exception e) {
//            logger.error("❌ DangNhap_TC_08 FAILED: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    // ==================== UNHAPPY CASE ====================
//
//    @Test(priority = 9, description = "DangNhap_TC_09 - Sai tài khoản/mật khẩu")
//    @Story("Login Error Handling")
//    @Description("Verify user cannot login with invalid credentials - should remain on login screen")
//    @Severity(SeverityLevel.CRITICAL)
//    public void DangNhap_TC_09_LoginInvalidCredentials() {
//        logger.info("\n╔════════════════════════════════════════════════╗");
//        logger.info("║ DangNhap_TC_09 - SAI TÀI KHOẢN/MẬT KHẨU      ║");
//        logger.info("╚════════════════════════════════════════════════╝");
//
//        try {
//            String phoneOrEmail = "wrongaccount@gmail.com";
//            String password = "wrongpassword";
//
//            authFlow.loginFromFridgeTab(phoneOrEmail, password);
//
//            boolean isStillOnLoginScreen = authFlow.isLoginScreenStillDisplayed();
//            Assert.assertTrue(isStillOnLoginScreen, "Lỗi: Phải ở lại màn Login!");
//
//            logger.info("✅ DangNhap_TC_09 PASSED\n");
//        } catch (Exception e) {
//            logger.error("❌ DangNhap_TC_09 FAILED: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    @Test(priority = 10, description = "DangNhap_TC_10 - Để trống SĐT/Email")
//    @Story("Login Validation")
//    @Description("Verify user cannot login with empty phone/email field - should show error toast")
//    @Severity(SeverityLevel.NORMAL)  // ✅ FIX: Dùng NORMAL thay vì HIGH
//    public void DangNhap_TC_10_LoginEmptyPhone() {
//        logger.info("\n╔════════════════════════════════════════════════╗");
//        logger.info("║ DangNhap_TC_10 - ĐỂ TRỐNG SĐT/EMAIL          ║");
//        logger.info("╚════════════════════════════════════════════════╝");
//
//        try {
//            String password = "000000";
//
//            authFlow.TC_10(password);  // ✅ Method đã tồn tại
//
//            boolean isToastDisplayed = authFlow.isToastUpdateInfoDisplayed_TC1011();
//            Assert.assertTrue(isToastDisplayed, "Lỗi: Toast error không hiển thị");
//
//            logger.info("✅ DangNhap_TC_10 PASSED\n");
//        } catch (Exception e) {
//            logger.error("❌ DangNhap_TC_10 FAILED: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    @Test(priority = 11, description = "DangNhap_TC_11 - Để trống Mật khẩu")
//    @Story("Login Validation")
//    @Description("Verify user cannot login with empty password field - should show error toast")
//    @Severity(SeverityLevel.NORMAL)  // ✅ FIX: Dùng NORMAL thay vì HIGH
//    public void DangNhap_TC_11_LoginEmptyPassword() {
//        logger.info("\n╔════════════════════════════════════════════════╗");
//        logger.info("║ DangNhap_TC_11 - ĐỂ TRỐNG MẬT KHẨU           ║");
//        logger.info("╚════════════════════════════════════════════════╝");
//
//        try {
//            String phoneOrEmail = "0000000000";
//
//            authFlow.TC_11(phoneOrEmail);  // ✅ Method đã tồn tại
//
//            boolean isToastDisplayed = authFlow.isToastUpdateInfoDisplayed_TC1011();
//            Assert.assertTrue(isToastDisplayed, "Lỗi: Toast error không hiển thị");
//
//            logger.info("✅ DangNhap_TC_11 PASSED\n");
//        } catch (Exception e) {
//            logger.error("❌ DangNhap_TC_11 FAILED: " + e.getMessage());
//            throw e;
//        }
//    }
}