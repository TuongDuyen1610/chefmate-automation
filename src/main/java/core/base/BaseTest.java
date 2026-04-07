package core.base;

import core.driver.DriverManager;
import core.listener.AllureTestListener;
import core.utils.AllureHelper;
import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

/**
 * BaseTest.java
 *
 * MỤC ĐÍCH:
 * - Là lớp cơ sở cho tất cả các Test Case
 * - Quản lý vòng đời của Appium Driver (khởi tạo trước test, đóng sau test)
 * - Đảm bảo mỗi test case đều có driver sạch và độc lập
 * ✅ Updated: Tích hợp Allure Reporter
 * - Khởi tạo AllureHelper
 * - Quản lý Allure attachment
 * */
@Listeners({
        core.listener.AllureTestListener.class,
        core.listener.GoogleSheetTestListener.class
})public class BaseTest {

//    @BeforeMethod(alwaysRun = true)
//    public void setup() {
//        System.out.println("🚀 [SETUP] Starting test - Initializing Appium Driver...");
//        DriverManager.initDriver();
//    }
//
//    @AfterMethod(alwaysRun = true)
//    public void tearDown() {
//        System.out.println("🛑 [TEARDOWN] Test finished - Quitting Appium Driver...");
//        DriverManager.quitDriver();
//    }
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected AppiumDriver driver;
    protected AllureHelper allureHelper;

    @BeforeSuite
    public void beforeSuite() {
        logger.info("\n========== 🚀 BEFORE SUITE ==========");
    }

    @BeforeTest
    public void beforeTest() {
        logger.info("\n========== 🚀 BEFORE TEST ==========");
    }

    @BeforeMethod
    public void setup() {
        logger.info("\n========== 🚀 SETUP - Initializing Appium Driver ==========");

        // Initialize driver
        DriverManager.initDriver();
        this.driver = DriverManager.getDriver();

        // Initialize Allure Helper
        this.allureHelper = new AllureHelper(driver);

        logger.info("✅ Driver initialized successfully");
    }

    @AfterMethod
    public void tearDown() {
        logger.info("\n========== 🛑 TEARDOWN - Quitting Appium Driver ==========");

        // Quit driver
        DriverManager.quitDriver();

        logger.info("✅ Driver quit successfully");
    }

    @AfterTest
    public void afterTest() {
        logger.info("\n========== 🛑 AFTER TEST ==========");
    }

    @AfterSuite
    public void afterSuite() {
        logger.info("\n========== 🛑 AFTER SUITE ==========");
    }

}