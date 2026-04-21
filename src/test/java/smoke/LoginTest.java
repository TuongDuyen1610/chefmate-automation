
package smoke;

import core.data.LoginData;
import core.utils.JsonHelper;
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
    private final LoginData loginData = JsonHelper.readLoginData();

    String phoneOrEmail = loginData.login;
    String password = loginData.password;
//     ==================== HAPPY CASE: PHONE ====================
//
//    @Test(priority = 1, description = "DangNhap_TC_01 - Đăng nhập Tủ lạnh")
//    @Story("Login from Fridge Tab")
//    @Description("User dapat login successfully from Fridge tab")
//    @Severity(SeverityLevel.CRITICAL)
//    public void DangNhap_TC_01_LoginFridgePhone() {
//        logger.info("\n╔═══════════════════════════════════╗");
//        logger.info("║ DangNhap_TC_01 - ĐĂNG NHẬP TỦ LẠNH  ║");
//        logger.info("╚═════════════════════════════════════╝");
//
//        try {
//            // ACT
//            authFlow.loginFromFridgeTab(phoneOrEmail, password);
//
//            // ASSERT
//            boolean isSuccess = authFlow.isLoggedInSuccessfully();
//            Assert.assertTrue(isSuccess, "Lỗi: Không vào được Home!");
//
//            logger.info("✅ DangNhap_TC_01 PASSED\n");
//        } catch (Exception e) {
//            logger.error("❌ DangNhap_TC_01 FAILED: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    @Test(priority = 2, description = "DangNhap_TC_02 - Đăng nhập Tài khoản")
//    @Story("Login from Profile Tab")
//    @Description("User dapat login successfully from Profile tab")
//    @Severity(SeverityLevel.CRITICAL)
//    public void DangNhap_TC_02_LoginProfilePhone() {
//        logger.info("\n╔══════════════════════════════════════╗");
//        logger.info("║ DangNhap_TC_02 - ĐĂNG NHẬP TÀI KHOẢN   ║");
//        logger.info("╚════════════════════════════════════════╝");
//
//        try {
//
//            authFlow.loginFromProfileTab(phoneOrEmail, password);
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

    @Test(priority = 3, description = "DangNhap_TC_03 - Đăng nhập Trò chuyện với Bepes")
    @Story("Login from Bepes AI Button")
    @Description("User dapat login successfully from Bepes AI button")
    @Severity(SeverityLevel.CRITICAL)
    public void DangNhap_TC_03_LoginBepesPhone() {
        logger.info("\n╔══════════════════════════════════╗");
        logger.info("║ DangNhap_TC_03 - ĐĂNG NHẬP BEPES   ║");
        logger.info("╚════════════════════════════════════╝");

        try {

            authFlow.loginFromBepesAIButton(phoneOrEmail, password);

            boolean isSuccess = authFlow.isLoggedInSuccessfully();
            Assert.assertTrue(isSuccess, "Lỗi: Không vào được Home!");

            logger.info("✅ DangNhap_TC_03 PASSED\n");
        } catch (Exception e) {
            logger.error("❌ DangNhap_TC_03 FAILED: " + e.getMessage());
            throw e;
        }
    }
//
//    @Test(priority = 4, description = "DangNhap_TC_04 - Đăng nhập Gợi ý từ tủ lạnh")
//    @Story("Login from Fridge Suggestion Button")
//    @Description("User dapat login successfully using valid phone number from Fridge suggestion button")
//    @Severity(SeverityLevel.CRITICAL)
//    public void DangNhap_TC_04_LoginSuggestionPhone() {
//        logger.info("\n╔═══════════════════════════════════╗");
//        logger.info("║ DangNhap_TC_04 - ĐĂNG NHẬP GỢI Ý    ║");
//        logger.info("╚═════════════════════════════════════╝");
//
//        try {
//
//            authFlow.loginFromFridgeSuggestionButton(phoneOrEmail, password);
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
//    // ==================== UNHAPPY CASE ====================
//
//    @Test(priority = 5, description = "DangNhap_TC_05 - Tài khoản/mật khẩu không hợp lệ")
//    @Story("Login Error Handling")
//    @Description("Verify user cannot login with invalid credentials - should remain on login screen")
//    @Severity(SeverityLevel.CRITICAL)
//    public void DangNhap_TC_05_LoginInvalidCredentials() {
//        logger.info("\n╔══════════════════════════════════════════════════╗");
//        logger.info("║ DangNhap_TC_05 - TÀI KHOẢN/MẬT KHẨU KHÔNG HỢP LỆ   ║");
//        logger.info("╚════════════════════════════════════════════════════╝");
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
//            logger.info("✅ DangNhap_TC_05 PASSED\n");
//        } catch (Exception e) {
//            logger.error("❌ DangNhap_TC_05 FAILED: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    @Test(priority = 6, description = "DangNhap_TC_06- Để trống SĐT/Email")
//    @Story("Login Validation")
//    @Description("Verify user cannot login with empty phone/email field - should show error toast")
//    @Severity(SeverityLevel.NORMAL)  // ✅ FIX: Dùng NORMAL thay vì HIGH
//    public void DangNhap_TC_06_LoginEmptyPhone() {
//        logger.info("\n╔════════════════════════════════════════════╗");
//        logger.info("║ DangNhap_TC_06 - ĐỂ TRỐNG SĐT/EMAIL          ║");
//        logger.info("╚══════════════════════════════════════════════╝");
//
//        try {
//            String password = "000000";
//
//            authFlow.TC_06(password);  // ✅ Method đã tồn tại
//
//            boolean isToastDisplayed = authFlow.isToastUpdateInfoDisplayed_TC0607();
//            Assert.assertTrue(isToastDisplayed, "Lỗi: Toast error không hiển thị");
//
//            logger.info("✅ DangNhap_TC_06 PASSED\n");
//        } catch (Exception e) {
//            logger.error("❌ DangNhap_TC_06 FAILED: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    @Test(priority = 7, description = "DangNhap_TC_07 - Để trống Mật khẩu")
//    @Story("Login Validation")
//    @Description("Verify user cannot login with empty password field - should show error toast")
//    @Severity(SeverityLevel.NORMAL)  // ✅ FIX: Dùng NORMAL thay vì HIGH
//    public void DangNhap_TC_07_LoginEmptyPassword() {
//        logger.info("\n╔════════════════════════════════════════════════╗");
//        logger.info("║ DangNhap_TC_07 - ĐỂ TRỐNG MẬT KHẨU           ║");
//        logger.info("╚════════════════════════════════════════════════╝");
//
//        try {
//            String phoneOrEmail = "0000000000";
//
//            authFlow.TC_07(phoneOrEmail);  // ✅ Method đã tồn tại
//
//            boolean isToastDisplayed = authFlow.isToastUpdateInfoDisplayed_TC0607();
//            Assert.assertTrue(isToastDisplayed, "Lỗi: Toast error không hiển thị");
//
//            logger.info("✅ DangNhap_TC_07 PASSED\n");
//        } catch (Exception e) {
//            logger.error("❌ DangNhap_TC_07 FAILED: " + e.getMessage());
//            throw e;
//        }
//    }
}