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
//        options.setDeviceName("Samsung Galaxy A56");
//        options.setUdid("192.168.22.101:40217");
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
//
//package config;
//
//import io.appium.java_client.android.options.UiAutomator2Options;
//
//import java.time.Duration;
//
//public class CapabilityConfig {
//
//    public static UiAutomator2Options getAndroidCapabilities() {
//
//        UiAutomator2Options options = new UiAutomator2Options();
//
//        options.setPlatformName(EnvironmentConfig.get("platform"));
//        options.setDeviceName(EnvironmentConfig.get("deviceName"));
//        options.setAutomationName(EnvironmentConfig.get("automationName"));
//
//        options.setAppPackage(EnvironmentConfig.get("appPackage"));
//        options.setAppActivity(EnvironmentConfig.get("appActivity"));
//
//        // QUAN TRỌNG: phân biệt máy thật và emulator
//        options.setUdid(EnvironmentConfig.get("udid"));
//
//        // tối ưu automation
//        options.setNoReset(false);
//        options.setFullReset(false);
////        options.setAutoGrantPermissions(true);
////
////        int timeoutSeconds = EnvironmentConfig.getInt("new.command.timeout", 300);
////        options.setNewCommandTimeout(Duration.ofSeconds(timeoutSeconds));
////
////        System.out.println("✅ [CAPABILITY] Android capabilities configured successfully");
////
////        return options;
////    }
////}
//package config;
//
//import io.appium.java_client.android.options.UiAutomator2Options;
//
//import java.time.Duration;
//
//public class CapabilityConfig {
//
//    public static UiAutomator2Options getAndroidCapabilities() {
//
//        UiAutomator2Options options = new UiAutomator2Options();
//
//        options.setPlatformName(EnvironmentConfig.get("platform"));
//        options.setAutomationName(EnvironmentConfig.get("automationName"));
//
//        options.setAppPackage(EnvironmentConfig.get("appPackage"));
//        options.setAppActivity(EnvironmentConfig.get("appActivity"));
//
//        options.setAutoGrantPermissions(true);
//        options.setNoReset(true);
//
//        int timeoutSeconds = EnvironmentConfig.getInt("new.command.timeout", 300);
//        options.setNewCommandTimeout(Duration.ofSeconds(timeoutSeconds));
//
//        String device = EnvironmentConfig.get("device");
//
//        if (device.equals("real")) {
//
//            options.setDeviceName("Samsung Galaxy A56");
//            options.setUdid("192.168.22.101:40217");
//
//            System.out.println("📱 Running on REAL DEVICE");
//
//        } else {
//
//            options.setDeviceName("Android Emulator");
//            options.setUdid("emulator-5554");
//
//            System.out.println("🖥 Running on EMULATOR");
//        }
//
//        System.out.println("✅ Capability configured");
//
//        return options;
//    }
//}