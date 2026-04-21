package core.driver;

import config.CapabilityConfig;
import config.EnvironmentConfig;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;
public class DriverManager {

    private static final ThreadLocal<AndroidDriver> driverThreadLocal = new ThreadLocal<>();
    private static final int DRIVER_TIMEOUT_SECONDS = 10; // ⏱️ 60 GIÂY TIMEOUT
    public static AndroidDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void initDriver() {
        try {
            String appiumUrl = EnvironmentConfig.get("appium.server.url");

            AndroidDriver driver = new AndroidDriver(
                    new URL(appiumUrl),
                    CapabilityConfig.getAndroidCapabilities()
            );
            // ✅ THÊM TIMEOUT 10 GIÂY
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

            driverThreadLocal.set(driver);

            System.out.println("✅ [DRIVER] Appium Driver initialized successfully on thread: "
                    + Thread.currentThread().getId());

        } catch (Exception e) {
            throw new RuntimeException("❌ [DRIVER] Failed to initialize Appium Driver", e);
        }
    }

    public static void quitDriver() {
        try {
            AndroidDriver driver = getDriver();
            if (driver != null) {
                driver.quit();
                driverThreadLocal.remove();
                System.out.println("✅ [DRIVER] Appium Driver quit successfully");
            }
        } catch (Exception e) {
            System.err.println("⚠️ [DRIVER] Error while quitting driver: " + e.getMessage());
        }
    }
}