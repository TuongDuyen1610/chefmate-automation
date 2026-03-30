package core.base;

import core.driver.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * BaseTest.java
 *
 * MỤC ĐÍCH:
 * - Là lớp cơ sở cho tất cả các Test Case
 * - Quản lý vòng đời của Appium Driver (khởi tạo trước test, đóng sau test)
 * - Đảm bảo mỗi test case đều có driver sạch và độc lập
 */
public class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        System.out.println("🚀 [SETUP] Starting test - Initializing Appium Driver...");
        DriverManager.initDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        System.out.println("🛑 [TEARDOWN] Test finished - Quitting Appium Driver...");
        DriverManager.quitDriver();
    }
}