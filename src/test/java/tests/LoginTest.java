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
import screens.LoginGateHelper;
import screens.LoginScreen;
import screens.SplashScreen;


@Feature("Authentication")
public class LoginTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);
    private final AuthenticationFlow authFlow = new AuthenticationFlow();
    private final LoginData loginData = JsonHelper.readLoginData();
    private final LoginScreen login = new LoginScreen();
    private final SplashScreen splash = new SplashScreen();
    private final LoginGateHelper gateHelper = new LoginGateHelper();
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

    @Test(priority = 5, description = "DangNhap_TC_05 - Đăng nhập Tài khoản")
    public void DangNhap_TC_05() {
        authFlow.loginFromProfileTab(phoneOrEmail, password);
        AllureHelper.attachScreenshot("Login successful");
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Error: Khong vao đuoc Home!");
        logger.info("✅ DangNhap_TC_05 PASSED");

    }

    @Test(priority = 6, description = "DangNhap_TC_06 - Đăng nhập Tài khoản")
    public void DangNhap_TC_06() {
        String phoneOrEmail = "0900009999";
        authFlow.loginFromProfileTab(phoneOrEmail, password);
        AllureHelper.attachScreenshot("Login successful");
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Error: Khong vao đuoc Home!");
        logger.info("✅ DangNhap_TC_06 PASSED");

    }
    // ==================== UNHAPPY CASE ====================

    @Test(priority = 7, description = "DangNhap_TC_07 - Tài khoản/mật khẩu không hợp lệ")
    public void DangNhap_TC_07() {
        String phoneOrEmail = "wrongaccount@gmail.com";
        String password = "wrongpassword";
        authFlow.loginFromFridgeTab(phoneOrEmail, password);
        AllureHelper.attachScreenshot("Login fail");
        Assert.assertTrue(authFlow.isLoginScreenStillDisplayed(), "Error: Phai o lai man Login!");
        logger.info("✅ DangNhap_TC_07 PASSED\n");
    }

    @Test(priority = 8, description = "DangNhap_TC_08- Để trống Email")
    public void DangNhap_TC_08() {
        String password = "000000";
        authFlow.TC_06(password);
        Assert.assertTrue(authFlow.isToastUpdateInfoDisplayed_TC0607(), "Error: Toast error khong hien thi");
        logger.info("✅ DangNhap_TC_08 PASSED\n");
    }

    @Test(priority = 9, description = "DangNhap_TC_09 - Để trống Mật khẩu")
    public void DangNhap_TC_09() {
        String phoneOrEmail = "0000000000";
        authFlow.TC_07(phoneOrEmail);
        Assert.assertTrue(authFlow.isToastUpdateInfoDisplayed_TC0607(), "Loi: Toast error khong hien thi");
        logger.info("✅ DangNhap_TC_09 PASSED\n");

    }

    @Test(priority = 10, description = "DangNhap_TC_10 - Để trống cả 2 trường")
    public void DangNhap_TC_10() {
        splash.waitUntilSplashDisappear();
        AllureHelper.attachScreenshot("Splash screen disappeared");
        gateHelper.triggerLoginByFridgeTab();
        login.clickLogin();
        Assert.assertTrue(authFlow.isToastUpdateInfoDisplayed_TC0607(), "Loi: Toast error khong hien thi");
        logger.info("✅ DangNhap_TC_10 PASSED\n");

    }

    @Test(priority = 11, description = "DangNhap_TC_11 - Email sai định dạng")
    public void DangNhap_TC_11() {
        authFlow.loginFromFridgeTab("wrongaccount.com", "123456");
        AllureHelper.attachScreenshot("Login fail");
        Assert.assertTrue(authFlow.isToastLoginFail(), "Loi: Toast error khong hien thi");
        logger.info("✅ DangNhap_TC_11 PASSED\n");
    }
    @Test(priority = 12, description = "DangNhap_TC_12 - SĐT sai định dạng")
    public void DangNhap_TC_12() {
        authFlow.loginFromFridgeTab("1234abc", "123456");
        AllureHelper.attachScreenshot("Login fail");
        Assert.assertTrue(authFlow.isToastLoginFail(), "Loi: Toast error khong hien thi");
        logger.info("✅ DangNhap_TC_12 PASSED\n");
    }
    @Test(priority = 13, description = "DangNhap_TC_13 - field có dấu cách/trước/sau")
    public void DangNhap_TC_13() {
        authFlow.loginFromFridgeTab(" duyentest@gmail.com ", " 123456 ");
        AllureHelper.attachScreenshot("Login fail");
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Error: Khong vao đuoc Home!");
        logger.info("✅ DangNhap_TC_13 PASSED\n");
    }
    @Test(priority = 14, description = "DangNhap_TC_14 - field có dấu tiếng Việt, ký tự đặc biệt")
    public void DangNhap_TC_14() {
        authFlow.loginFromFridgeTab("duyêntest@gmail.com", "abc@#%$%^%&*&%"); // dữ liệu dị biệt
        AllureHelper.attachScreenshot("Login fail");
        Assert.assertTrue(authFlow.isToastLoginFail(), "Loi: Toast error khong hien thi");
        logger.info("✅ DangNhap_TC_14 PASSED\n");
    }
    @Test(priority = 15, description = "DangNhap_TC_15 -  Email, mật khẩu max ký tự quy định")
    public void DangNhap_TC_15() {
        String longEmail = "a".repeat(245) + "@gmail.com"; // > max cho phép
        String longPassword = "a".repeat(100);             // password dài bất thường
        authFlow.loginFromFridgeTab(longEmail, longPassword);
        AllureHelper.attachScreenshot("Login fail");
        Assert.assertTrue(authFlow.isToastLoginFail(), "Loi: Toast error khong hien thi");
        logger.info("✅ DangNhap_TC_15 PASSED\n");
    }
    @Test(priority = 16, description = "DangNhap_TC_16 -  icon ẩn/ hiển password")
    public void DangNhap_TC_16() {
        splash.waitUntilSplashDisappear();
        AllureHelper.attachScreenshot("Splash screen disappeared");
        gateHelper.triggerLoginByFridgeTab();
        login.performLogin2("0900009999", "123456");
        logger.info("✅ DangNhap_TC_16 PASSED");
    }
    @Test(priority = 17, description = "DangNhap_TC_17 -  toàn chữ hoa/thường")
    public void DangNhap_TC_17() {
        authFlow.loginFromFridgeTab("DUYENTEST@GMAIL.COM", "123456");
        AllureHelper.attachScreenshot("Login fail");
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "Error: Khong vao đuoc Home!");
        logger.info("✅ DangNhap_TC_17 PASSED\n");
    }

}