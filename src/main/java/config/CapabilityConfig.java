package config;

import io.appium.java_client.android.options.UiAutomator2Options;

import java.time.Duration;

public class CapabilityConfig {

    public static UiAutomator2Options getAndroidCapabilities() {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName(EnvironmentConfig.get("platform"));
        options.setDeviceName(EnvironmentConfig.get("deviceName"));
        options.setAutomationName(EnvironmentConfig.get("automationName"));

        options.setAppPackage(EnvironmentConfig.get("appPackage"));
        options.setAppActivity(EnvironmentConfig.get("appActivity"));
        // Tối ưu cho automation
        options.setNoReset(false);
        options.setFullReset(false);
        options.setAutoGrantPermissions(true);

        // SỬA LỖI Ở ĐÂY: Phải dùng Duration.ofSeconds()
        int timeoutSeconds = EnvironmentConfig.getInt("new.command.timeout", 300);
        options.setNewCommandTimeout(Duration.ofSeconds(timeoutSeconds));

        System.out.println("✅ [CAPABILITY] Android capabilities configured successfully");
        return options;
    }
}
