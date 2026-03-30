package core.driver;

import config.CapabilityConfig;
import config.EnvironmentConfig;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class DriverManager {

    private static final ThreadLocal<AndroidDriver> driverThreadLocal = new ThreadLocal<>();

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