package config;

import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.File;
import java.time.Duration;

public class CapabilityConfig {

    public static UiAutomator2Options getAndroidCapabilities() {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName(EnvironmentConfig.get("platform"));
        options.setDeviceName(EnvironmentConfig.get("deviceName"));
        options.setAutomationName(EnvironmentConfig.get("automationName"));

//        options.setAppPackage(EnvironmentConfig.get("appPackage"));
//        options.setAppActivity(EnvironmentConfig.get("appActivity"));

        // 🎯 ĐIỂM MẤU CHỐT: Cấu hình đường dẫn tuyệt đối động cho file APK
        // System.getProperty("user.dir") giúp lấy thư mục gốc của project dù chạy ở máy chị hay máy ảo GitHub
        String appPath = System.getProperty("user.dir") + File.separator + "src"
                + File.separator + "test" + File.separator + "resources"
                + File.separator + "chefmate-app.debug.apk";

        File apkFile = new File(appPath);
        if (apkFile.exists()) {
            options.setApp(apkFile.getAbsolutePath());
            System.out.println("📦 [CAPABILITY] Đã tìm thấy và cấu hình nạp file APK: " + apkFile.getAbsolutePath());
        } else {
            System.out.println("⚠️ [CAPABILITY] Không thấy file APK tại resources, thử chạy bằng Package/Activity có sẵn...");
            options.setAppPackage(EnvironmentConfig.get("appPackage"));
            options.setAppActivity(EnvironmentConfig.get("appActivity"));
        }

        // Tối ưu cho automation
        options.setNoReset(false); // reset lại app Chefmate
        options.setFullReset(false);
        options.setAutoGrantPermissions(true);

        // SỬA LỖI Ở ĐÂY: Phải dùng Duration.ofSeconds()
        int timeoutSeconds = EnvironmentConfig.getInt("new.command.timeout", 300);
        options.setNewCommandTimeout(Duration.ofSeconds(timeoutSeconds));

        // Chạy ShareTesting cho Oppo A54 hệ điều Android 11
        options.setCapability("skipDeviceInitialization", true);
        options.setCapability("ignoreHiddenApiPolicyError", true);
        options.setCapability("dontStopAppOnReset", true);

        System.out.println("✅ [CAPABILITY] Android capabilities configured successfully");
        return options;

    }
}

//package config;
//
//import io.appium.java_client.android.options.UiAutomator2Options;
//
//import java.io.File;
//import java.time.Duration;
//
//public class CapabilityConfig {
//
//    public static UiAutomator2Options getAndroidCapabilities() {
//        UiAutomator2Options options = new UiAutomator2Options();
//
//        options.setPlatformName(EnvironmentConfig.get("platform"));
//        options.setDeviceName(EnvironmentConfig.get("deviceName"));
//        options.setAutomationName(EnvironmentConfig.get("automationName"));
//
////        options.setAppPackage(EnvironmentConfig.get("appPackage"));
////        options.setAppActivity(EnvironmentConfig.get("appActivity"));
//
//        // 🎯 ĐIỂM MẤU CHỐT: Cấu hình đường dẫn tuyệt đối động cho file APK
//        // System.getProperty("user.dir") giúp lấy thư mục gốc của project dù chạy ở máy chị hay máy ảo GitHub
//        String appPath = System.getProperty("user.dir") + File.separator + "src"
//                + File.separator + "test" + File.separator + "resources"
//                + File.separator + "chefmate-app.debug.apk";
//
//        File apkFile = new File(appPath);
//        if (apkFile.exists()) {
//            options.setApp(apkFile.getAbsolutePath());
//            System.out.println("📦 [CAPABILITY] Đã tìm thấy và cấu hình nạp file APK: " + apkFile.getAbsolutePath());
//        } else {
//            System.out.println("⚠️ [CAPABILITY] Không thấy file APK tại resources, thử chạy bằng Package/Activity có sẵn...");
//            options.setAppPackage(EnvironmentConfig.get("appPackage"));
//            options.setAppActivity(EnvironmentConfig.get("appActivity"));
//        }
//
//        // Tối ưu cho automation
//        options.setNoReset(true); // no reset lại app Chefmate => sử dụng RIÊNG cho real device oppo A54
//        options.setFullReset(false);
//        options.setAutoGrantPermissions(true);
//
//        // SỬA LỖI Ở ĐÂY: Phải dùng Duration.ofSeconds()
//        int timeoutSeconds = EnvironmentConfig.getInt("new.command.timeout", 300);
//        options.setNewCommandTimeout(Duration.ofSeconds(timeoutSeconds));
//
//        // Chạy ShareTesting cho Oppo A54 hệ điều Android 11
//        options.setCapability("skipDeviceInitialization", true);
//        options.setCapability("ignoreHiddenApiPolicyError", true);
//        options.setCapability("dontStopAppOnReset", true);
//
//        System.out.println("✅ [CAPABILITY] Android capabilities configured successfully");
//        return options;
//
//    }
//}

