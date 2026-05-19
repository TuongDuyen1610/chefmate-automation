////package core.base;
////
////import core.driver.DriverManager;
////import core.listener.AllureTestListener;
////import core.utils.AllureHelper;
////import io.appium.java_client.AppiumDriver;
////import org.slf4j.Logger;
////import org.slf4j.LoggerFactory;
////import org.testng.annotations.*;
////
/////**
//// * BaseTest.java
//// *
//// * MỤC ĐÍCH:
//// * - Là lớp cơ sở cho tất cả các Test Case
//// * - Quản lý vòng đời của Appium Driver (khởi tạo trước test, đóng sau test)
//// * - Đảm bảo mỗi test case đều có driver sạch và độc lập
//// * ✅ Updated: Tích hợp Allure Reporter
//// * - Khởi tạo AllureHelper
//// * - Quản lý Allure attachment
//// * */
////@Listeners({
////        core.listener.AllureTestListener.class,
////        core.listener.GoogleSheetTestListener.class
////})public class BaseTest {
////
////    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
////    protected AppiumDriver driver;
////    protected AllureHelper allureHelper;
////
////    @BeforeSuite
////    public void beforeSuite() {
////        logger.info("\n========== 🚀 BEFORE SUITE ==========");
////    }
////
////    @BeforeTest
////    public void beforeTest() {
////        logger.info("\n========== 🚀 BEFORE TEST ==========");
////    }
////
////    @BeforeMethod
////    public void setup() {
////        logger.info("\n========== 🚀 SETUP - Initializing Appium Driver ==========");
////
////        // Initialize driver
////        DriverManager.initDriver();
////        this.driver = DriverManager.getDriver();
////
////        // Initialize Allure Helper
////        this.allureHelper = new AllureHelper(driver);
////
////        logger.info("✅ Driver initialized successfully");
////    }
////
////    @AfterMethod
////    public void tearDown() {
////        logger.info("\n========== 🛑 TEARDOWN - Quitting Appium Driver ==========");
////
////        // Quit driver
////        DriverManager.quitDriver();
////
////        logger.info("✅ Driver quit successfully");
////    }
////
////    @AfterTest
////    public void afterTest() {
////        logger.info("\n========== 🛑 AFTER TEST ==========");
////    }
////
////    @AfterSuite
////    public void afterSuite() {
////        logger.info("\n========== 🛑 AFTER SUITE ==========");
////    }
////
////}
//
//package core.base;
//
//import core.driver.DriverManager;
//import core.listener.AllureTestListener;
//import core.utils.AllureHelper;
//import io.appium.java_client.AppiumDriver;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.apache.logging.log4j.LogManager;
//import org.testng.annotations.*;
//
///**
// * BaseTest.java
// *
// * MỤC ĐÍCH:
// * - Là lớp cơ sở cho tất cả các Test Case
// * - Quản lý vòng đời của Appium Driver (khởi tạo trước test, đóng sau test)
// * - Đảm bảo mỗi test case đều có driver sạch và độc lập
// * ✅ Updated: Tích hợp Allure Reporter
// * - Khởi tạo AllureHelper
// * - Quản lý Allure attachment
// * */
//@Listeners({
//        core.listener.AllureTestListener.class,
//        core.listener.GoogleSheetTestListener.class
//})public class BaseTest {
//
//    // 1. Dùng SLF4J (Cái chị đang dùng cũ) - Gọi trực tiếp đường dẫn để không trùng tên
//    protected static final org.slf4j.Logger logger = LoggerFactory.getLogger(BaseTest.class);
//
//    // 2. Dùng Log4j 2 (Cái chị mới thêm vào) - Cũng gọi trực tiếp đường dẫn
//    protected static final org.apache.logging.log4j.Logger log4jLogger = LogManager.getLogger(BaseTest.class);
//    protected AppiumDriver driver;
//    protected AllureHelper allureHelper;
//
//    @BeforeSuite
//    public void beforeSuite() {
//        logger.info("\n========== 🚀 BEFORE SUITE ==========");
//        log4jLogger.info("Đây là log từ Log4j");
//    }
//
//    @BeforeTest
//    public void beforeTest() {
//        logger.info("\n========== 🚀 BEFORE TEST ==========");
//        log4jLogger.info("Đây là log từ Log4j");
//    }
//
//    @BeforeMethod
//    public void setup() {
//        logger.info("\n========== 🚀 SETUP - Initializing Appium Driver ==========");
//        log4jLogger.info("Đây là log từ Log4j");
//        // Initialize driver
//        DriverManager.initDriver();
//        this.driver = DriverManager.getDriver();
//
//        // Initialize Allure Helper
//        this.allureHelper = new AllureHelper(driver);
//
//        logger.info("✅ Driver initialized successfully");
//        log4jLogger.info("Đây là log từ Log4j");
//    }
//
//    @AfterMethod
//    public void tearDown() {
//        logger.info("\n========== 🛑 TEARDOWN - Quitting Appium Driver ==========");
//        log4jLogger.info("Đây là log từ Log4j");
//        // Quit driver
//        DriverManager.quitDriver();
//
//        logger.info("✅ Driver quit successfully");
//        log4jLogger.info("Đây là log từ Log4j");
//    }
//
//    @AfterTest
//    public void afterTest() {
//        logger.info("\n========== 🛑 AFTER TEST ==========");
//        log4jLogger.info("Đây là log từ Log4j");
//    }
//
//    @AfterSuite
//    public void afterSuite() {
//        logger.info("\n========== 🛑 AFTER SUITE ==========");
//        log4jLogger.info("Đây là log từ Log4j");
//    }
//
//}

package core.base;

import core.driver.DriverManager;
import core.utils.AllureHelper;
import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

@Listeners({
        core.listener.AllureTestListener.class,
        core.listener.GoogleSheetTestListener.class
})
public class BaseTest {

    // Sử dụng Log4j 2 cho các mục đích ghi log chuyên sâu và chạy song song
    protected static final Logger log4jLogger = LogManager.getLogger(BaseTest.class);

    // Giữ SLF4J để tương thích với các thư viện cũ hoặc listener
    protected static final org.slf4j.Logger slf4jLogger = LoggerFactory.getLogger(BaseTest.class);

    protected AppiumDriver driver;
    protected AllureHelper allureHelper;

    @BeforeSuite
    public void beforeSuite() {
        log4jLogger.info("======================================================");
        log4jLogger.info("🚀 KHỞI ĐỘNG SUITE KIỂM THỬ: CHUẨN BỊ MÔI TRƯỜNG");
        log4jLogger.info("======================================================");
    }

    @BeforeMethod
    @Parameters({"deviceName", "platformVersion"}) // Hỗ trợ chị chạy Parallel sau này
    public void setup(@Optional("DefaultDevice") String deviceName, @Optional("DefaultVersion") String platformVersion) {
        log4jLogger.info("--- 🔄 BẮT ĐẦU TEST CASE ---");
        log4jLogger.info("📱 Đang cấu hình thiết bị: " + deviceName + " (Phiên bản: " + platformVersion + ")");

        try {
            // Khởi tạo driver thông qua DriverManager
            DriverManager.initDriver();
            this.driver = DriverManager.getDriver();

            // Log thông tin Driver để dễ debug nếu máy ảo lỗi
            log4jLogger.info("✅ Appium Driver đã sẵn sàng. Session ID: " + driver.getSessionId());

            // Khởi tạo Allure Helper
            this.allureHelper = new AllureHelper(driver);
            log4jLogger.info("📊 Đã kết nối Allure Helper để theo dõi các bước test.");

        } catch (Exception e) {
            log4jLogger.error("❌ LỖI NGHIÊM TRỌNG: Không thể khởi tạo Appium Driver!");
            log4jLogger.error("Chi tiết lỗi: " + e.getMessage());
            throw e;
        }
    }

    @AfterMethod
    public void tearDown() {
        log4jLogger.info("--- 🛑 KẾT THÚC TEST CASE ---");

        if (driver != null) {
            log4jLogger.info("🧹 Đang đóng ứng dụng và giải phóng tài nguyên Driver...");
            DriverManager.quitDriver();
            log4jLogger.info("✅ Đã dọn dẹp driver thành công.");
        } else {
            log4jLogger.warn("⚠️ Driver đã bị null từ trước, không cần dọn dẹp.");
        }
    }

    @AfterSuite
    public void afterSuite() {
        log4jLogger.info("======================================================");
        log4jLogger.info("🏁 TẤT CẢ TEST CASES ĐÃ HOÀN THÀNH");
        log4jLogger.info("📊 Vui lòng kiểm tra Allure Report và Google Sheets để xem kết quả.");
        log4jLogger.info("======================================================");
    }
}