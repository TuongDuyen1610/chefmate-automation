package tests;
import core.utils.AllureHelper;
import core.data.LoginData;
import core.utils.JsonHelper;
import core.base.BaseTest;
import flows.AuthenticationFlow;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Feature("Authentication")
public class LoginTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);
    private final AuthenticationFlow authFlow = new AuthenticationFlow();
    private final LoginData loginData = JsonHelper.readLoginData();

    String phoneOrEmail = loginData.login;
    String password = loginData.password;

    //     ==================== HAPPY CASE: PHONE ====================
    @Test(priority = 1, description = "DangNhap_TC_01 - Đăng nhập Tủ lạnh")
    public void DangNhap_TC_01() {
        authFlow.loginFromFridgeTab(phoneOrEmail, password);
        AllureHelper.attachScreenshot("Login successful");
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Error: Khong vao đuoc Home!");
        logger.info("✅ DangNhap_TC_01 PASSED");

    }

    @Test(priority = 2, description = "DangNhap_TC_02 - Đăng nhập Tài khoản")
    public void DangNhap_TC_02() {
        authFlow.loginFromProfileTab(phoneOrEmail, password);
        AllureHelper.attachScreenshot("Login successful");
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Error: Khong vao đuoc Home!");
        logger.info("✅ DangNhap_TC_02 PASSED");

    }

    @Test(priority = 3, description = "DangNhap_TC_03 - Đăng nhập Trò chuyện với Bepes")
    public void DangNhap_TC_03() {
        authFlow.loginFromBepesAIButton(phoneOrEmail, password);
        AllureHelper.attachScreenshot("Login successful");
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Error: Khong vao đuoc Home!");
        logger.info("✅ DangNhap_TC_03 PASSED\n");
    }

    @Test(priority = 4, description = "DangNhap_TC_04 - Đăng nhập Gợi ý từ tủ lạnh")
    public void DangNhap_TC_04() {
        authFlow.loginFromFridgeSuggestionButton(phoneOrEmail, password);
        AllureHelper.attachScreenshot("Login successful");
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Error: Khong vao đuoc Home!");
        logger.info("✅ DangNhap_TC_04 PASSED\n");
    }
    // ==================== UNHAPPY CASE ====================

    @Test(priority = 5, description = "DangNhap_TC_05 - Tài khoản/mật khẩu không hợp lệ")
    public void DangNhap_TC_05() {
        String phoneOrEmail = "wrongaccount@gmail.com";
        String password = "wrongpassword";
        authFlow.loginFromFridgeTab(phoneOrEmail, password);
        AllureHelper.attachScreenshot("Login fail");
        Assert.assertTrue(authFlow.isLoginScreenStillDisplayed(), "Error: Phai o lai man Login!");
        logger.info("✅ DangNhap_TC_05 PASSED\n");
    }

    @Test(priority = 6, description = "DangNhap_TC_06- Để trống Email")
    public void DangNhap_TC_06() {
        String password = "000000";
        authFlow.TC_06(password);
        Assert.assertTrue(authFlow.isToastUpdateInfoDisplayed_TC0607(), "Error: Toast error khong hien thi");
        logger.info("✅ DangNhap_TC_06 PASSED\n");
    }

    @Test(priority = 7, description = "DangNhap_TC_07 - Để trống Mật khẩu")
    public void DangNhap_TC_07() {
        String phoneOrEmail = "0000000000";
        authFlow.TC_07(phoneOrEmail);
        Assert.assertTrue(authFlow.isToastUpdateInfoDisplayed_TC0607(), "Loi: Toast error khong hien thi");
        logger.info("✅ DangNhap_TC_07 PASSED\n");

    }
}