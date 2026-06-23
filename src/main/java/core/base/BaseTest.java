//////TÍCH HỢP ATTACH IMAGE ALLURE REPORT => sử dụng RIÊNG cho real device oppo A54
//package core.base;
//
//import core.driver.DriverManager;
//import core.utils.AllureHelper;
//import io.appium.java_client.AppiumDriver;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.testng.annotations.*;
//
//@Listeners({
//        core.listener.AllureTestListener.class,
//        core.listener.GoogleSheetTestListener.class
//})
//public class BaseTest {
//
//    // Sử dụng Log4j 2 cho các mục đích ghi log chuyên sâu và chạy song song
//    protected static final Logger log4jLogger = LogManager.getLogger(BaseTest.class);
//
//    // Giữ SLF4J để tương thích với các thư viện cũ hoặc listener
//    protected static final org.slf4j.Logger slf4jLogger = LoggerFactory.getLogger(BaseTest.class);
//
//    protected AppiumDriver driver;
//    protected AllureHelper allureHelper;
//
//    @BeforeSuite
//    public void beforeSuite() {
//        log4jLogger.info("======================================================");
//        log4jLogger.info("🚀 KHỞI ĐỘNG SUITE KIỂM THỬ: CHUẨN BỊ MÔI TRƯỜNG");
//        log4jLogger.info("======================================================");
//    }
//
//    @BeforeMethod
//    @Parameters({"deviceName", "platformVersion"}) // Hỗ trợ chị chạy Parallel sau này
//    public void setup(@Optional("DefaultDevice") String deviceName, @Optional("DefaultVersion") String platformVersion) {
//        log4jLogger.info("--- 🔄 BẮT ĐẦU TEST CASE ---");
//        log4jLogger.info("📱 Đang cấu hình thiết bị: " + deviceName + " (Phiên bản: " + platformVersion + ")");
//
//        try {
//            // Khởi tạo driver thông qua DriverManager
//            DriverManager.initDriver();
//            this.driver = DriverManager.getDriver();
//
//            // Log thông tin Driver để dễ debug nếu máy ảo lỗi
//            log4jLogger.info("✅ Appium Driver đã sẵn sàng. Session ID: " + driver.getSessionId());
//
//            // Khởi tạo Allure Helper
//            this.allureHelper = new AllureHelper(driver);
//            log4jLogger.info("📊 Đã kết nối Allure Helper để theo dõi các bước test.");
//
//        } catch (Exception e) {
//            log4jLogger.error("❌ LỖI NGHIÊM TRỌNG: Không thể khởi tạo Appium Driver!");
//            log4jLogger.error("Chi tiết lỗi: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    @AfterMethod
//    public void tearDown() {
//        log4jLogger.info("--- 🛑 KẾT THÚC TEST CASE ---");
//
//        if (driver != null) {
//            log4jLogger.info("🧹 Đang đóng ứng dụng và giải phóng tài nguyên Driver...");
//            DriverManager.quitDriver();
//            log4jLogger.info("✅ Đã dọn dẹp driver thành công.");
//        } else {
//            log4jLogger.warn("⚠️ Driver đã bị null từ trước, không cần dọn dẹp.");
//        }
//    }
//
//    @AfterSuite
//    public void afterSuite() {
//        log4jLogger.info("======================================================");
//        log4jLogger.info("🏁 TẤT CẢ TEST CASES ĐÃ HOÀN THÀNH");
//        log4jLogger.info("📊 Vui lòng kiểm tra Allure Report và Google Sheets để xem kết quả.");
//        log4jLogger.info("======================================================");
//    }
//}
//
////TÍCH HỢP ATTACH RECORDING + IMAGE ALLURE REPORT
//package core.base;
//import org.testng.ITestResult;
//import core.driver.DriverManager;
//import core.utils.AllureHelper;
//import io.appium.java_client.AppiumDriver;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.testng.annotations.*;
//
//@Listeners({
//        core.listener.AllureTestListener.class,
//        core.listener.GoogleSheetTestListener.class
//})
//public class BaseTest {
//
//    // Sử dụng Log4j 2 cho các mục đích ghi log chuyên sâu và chạy song song
//    protected static final Logger log4jLogger = LogManager.getLogger(BaseTest.class);
//
//    // Giữ SLF4J để tương thích với các thư viện cũ hoặc listener
//    protected static final org.slf4j.Logger slf4jLogger = LoggerFactory.getLogger(BaseTest.class);
//
//    protected AppiumDriver driver;
//    protected AllureHelper allureHelper;
//
//    @BeforeSuite
//    public void beforeSuite() {
//        log4jLogger.info("======================================================");
//        log4jLogger.info("🚀 KHỞI ĐỘNG SUITE KIỂM THỬ: CHUẨN BỊ MÔI TRƯỜNG");
//        log4jLogger.info("======================================================");
//    }
//
//    @BeforeMethod
//    @Parameters({"deviceName", "platformVersion"})
//    public void setup(@Optional("DefaultDevice") String deviceName, @Optional("DefaultVersion") String platformVersion) {
//        log4jLogger.info("--- 🔄 BẮT ĐẦU TEST CASE ---");
//        log4jLogger.info("📱 Đang cấu hình thiết bị: " + deviceName + " (Phiên bản: " + platformVersion + ")");
//
//        try {
//            DriverManager.initDriver();
//            this.driver = DriverManager.getDriver();
//
//            log4jLogger.info("✅ Appium Driver đã sẵn sàng. Session ID: " + driver.getSessionId());
//
//            this.allureHelper = new AllureHelper(driver);
//            log4jLogger.info("📊 Đã kết nối Allure Helper để theo dõi các bước test.");
//
//            // 🎥 KÍCH HOẠT QUAY MÀN HÌNH CHUẨN: Bật ngay sau khi Driver đã sống nhăn răng
//            if (this.driver instanceof io.appium.java_client.android.AndroidDriver) {
//                ((io.appium.java_client.android.AndroidDriver) this.driver).startRecordingScreen();
//                log4jLogger.info("📽️ [RECORD] Đã bật quay màn hình Android thành công.");
//            }
//
//        } catch (Exception e) {
//            log4jLogger.error("❌ LỖI NGHIÊM TRỌNG: Không thể khởi tạo Appium Driver!");
//            log4jLogger.error("Chi tiết lỗi: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    @AfterMethod
//    public void tearDown(ITestResult result) {
//        log4jLogger.info("--- 🛑 KẾT THÚC TEST CASE ---");
//
//        if (driver != null) {
//            try {
//                if (driver instanceof io.appium.java_client.android.AndroidDriver) {
//                    log4jLogger.info("📽️ [RECORD] Đang dừng quay màn hình và trích xuất file video...");
//                    String base64Video = ((io.appium.java_client.android.AndroidDriver) driver).stopRecordingScreen();
//
//                    if (base64Video != null && !base64Video.isEmpty()) {
//                        byte[] videoBytes = java.util.Base64.getDecoder().decode(base64Video);
//
//                        // 🎯 GIẢI PHÁP ĐỘT PHÁ: Đính kèm trực tiếp thông qua luồng byte stream tĩnh của Allure.
//                        // Luồng này hoạt động bất chấp cấu hình luồng hay Listener có đóng kịch bản sớm hay không.
//                        String videoTitle = (result.getStatus() == ITestResult.FAILURE)
//                                ? "🚨 Video Luồng Chạy Bị Lỗi (FAILED)"
//                                : "📹 Video Luồng Chạy Thành Công (PASSED)";
//
//                        io.qameta.allure.Allure.addAttachment(
//                                videoTitle,
//                                "video/mp4",
//                                new java.io.ByteArrayInputStream(videoBytes),
//                                ".mp4"
//                        );
//                        log4jLogger.info("✨ [RECORD] Đã đẩy luồng Video vật lý vào bộ đệm đính kèm của Allure thành công!");
//                    }
//                }
//            } catch (Exception e) {
//                log4jLogger.error("❌ [RECORD] Lỗi khi xử lý trích xuất video: " + e.getMessage());
//            }
//
//            // Tiến hành dọn dẹp driver
//            log4jLogger.info("🧹 Đang đóng ứng dụng và giải phóng tài nguyên Driver...");
//            DriverManager.quitDriver();
//            log4jLogger.info("✅ Đã dọn dẹp driver thành công.");
//        }
//    }
//
//    @AfterSuite
//    public void afterSuite() {
//        log4jLogger.info("======================================================");
//        log4jLogger.info("🏁 TẤT CẢ TEST CASES ĐÃ HOÀN THÀNH");
//        log4jLogger.info("📊 Vui lòng kiểm tra Allure Report và Google Sheets để xem kết quả.");
//        log4jLogger.info("======================================================");
//    }
//}


//TÍCH HỢP ATTACH RECORDING + IMAGE ALLURE REPORT + AUTO HISTORY STORAGE
package core.base;

import org.testng.ITestResult;
import core.driver.DriverManager;
import core.utils.AllureHelper;
import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import java.io.File;
import org.apache.commons.io.FileUtils;

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

        // 📂 [HISTORY] Bước 1: Sao lưu lịch sử từ Allure Report cũ sang thư mục tạm trước khi Maven Clean xóa mất
        try {
            File sourceHistory = new File("target/allure-report/history");
            File targetHistoryTmp = new File("target/history-tmp");

            if (sourceHistory.exists() && sourceHistory.isDirectory()) {
                log4jLogger.info("📂 [HISTORY] Tìm thấy dữ liệu lịch sử từ phiên trước. Đang tiến hành sao lưu...");
                FileUtils.copyDirectory(sourceHistory, targetHistoryTmp);
                log4jLogger.info("✅ [HISTORY] Đã sao lưu lịch sử thành công vào thư mục tạm.");
            } else {
                log4jLogger.info("ℹ️ [HISTORY] Chưa có dữ liệu lịch sử cũ (Có thể đây là lần đầu chạy kịch bản).");
            }
        } catch (Exception e) {
            log4jLogger.error("❌ [HISTORY] Lỗi khi sao lưu dữ liệu lịch sử: " + e.getMessage());
        }
    }

    @BeforeMethod
    @Parameters({"deviceName", "platformVersion"})
    public void setup(@Optional("DefaultDevice") String deviceName, @Optional("DefaultVersion") String platformVersion) {
        log4jLogger.info("--- 🔄 BẮT ĐẦU TEST CASE ---");
        log4jLogger.info("📱 Đang cấu hình thiết bị: " + deviceName + " (Phiên bản: " + platformVersion + ")");

        try {
            DriverManager.initDriver();
            this.driver = DriverManager.getDriver();

            log4jLogger.info("✅ Appium Driver đã sẵn sàng. Session ID: " + driver.getSessionId());

            this.allureHelper = new AllureHelper(driver);
            log4jLogger.info("📊 Đã kết nối Allure Helper để theo dõi các bước test.");

            // 🎥 KÍCH HOẠT QUAY MÀN HÌNH CHUẨN: Bật ngay sau khi Driver đã sống nhăn răng
            if (this.driver instanceof io.appium.java_client.android.AndroidDriver) {
                ((io.appium.java_client.android.AndroidDriver) this.driver).startRecordingScreen();
                log4jLogger.info("📽️ [RECORD] Đã bật quay màn hình Android thành công.");
            }

        } catch (Exception e) {
            log4jLogger.error("❌ LỖI NGHIÊM TRỌNG: Không thể khởi tạo Appium Driver!");
            log4jLogger.error("Chi tiết lỗi: " + e.getMessage());
            throw e;
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        log4jLogger.info("--- 🛑 KẾT THÚC TEST CASE ---");

        if (driver != null) {
            try {
                if (driver instanceof io.appium.java_client.android.AndroidDriver) {
                    log4jLogger.info("📽️ [RECORD] Đang dừng quay màn hình và trích xuất file video...");
                    String base64Video = ((io.appium.java_client.android.AndroidDriver) driver).stopRecordingScreen();

                    if (base64Video != null && !base64Video.isEmpty()) {
                        byte[] videoBytes = java.util.Base64.getDecoder().decode(base64Video);

                        String videoTitle = (result.getStatus() == ITestResult.FAILURE)
                                ? "🚨 Video Luồng Chạy Bị Lỗi (FAILED)"
                                : "📹 Video Luồng Chạy Thành Công (PASSED)";

                        io.qameta.allure.Allure.addAttachment(
                                videoTitle,
                                "video/mp4",
                                new java.io.ByteArrayInputStream(videoBytes),
                                ".mp4"
                        );
                        log4jLogger.info("✨ [RECORD] Đã đẩy luồng Video vật lý vào bộ đệm đính kèm của Allure thành công!");
                    }
                }
            } catch (Exception e) {
                log4jLogger.error("❌ [RECORD] Lỗi khi xử lý trích xuất video: " + e.getMessage());
            }

            // Tiến hành dọn dẹp driver
            log4jLogger.info("🧹 Đang đóng ứng dụng và giải phóng tài nguyên Driver...");
            DriverManager.quitDriver();
            log4jLogger.info("✅ Đã dọn dẹp driver thành công.");
        }
    }

    @AfterSuite
    public void afterSuite() {
        log4jLogger.info("======================================================");
        log4jLogger.info("🏁 TẤT CẢ TEST CASES ĐÃ HOÀN THÀNH - CẬP NHẬT DASHBOARD CAO CẤP");

        try {
            String storagePath = "allure-history-storage";
            // 1. Định nghĩa mốc thời gian định danh
            String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new java.util.Date());
            String displayTime = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date());
            String sessionFolder = storagePath + "/Session_" + timestamp;

            log4jLogger.info("📊 Đang đóng gói 100% hiện trạng báo cáo vào: " + sessionFolder);

            // 2. Biên dịch Allure tĩnh cho riêng phiên này
            String command = String.format("allure generate target/allure-results --clean -o %s", sessionFolder);
            Process process;
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                process = Runtime.getRuntime().exec("cmd.exe /c " + command);
            } else {
                process = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", command});
            }
            process.waitFor();

            // 3. Tự động cập nhật file Trang Chủ Tổng (index.html) bằng kiến trúc Lưu trữ Trình duyệt
            File indexFile = new File(storagePath + "/index.html");
            StringBuilder htmlContent = new StringBuilder();

            htmlContent.append("<!DOCTYPE html>\n<html lang=\"vi\">\n<head>\n");
            htmlContent.append("  <meta charset=\"UTF-8\">\n");
            htmlContent.append("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
            htmlContent.append("  <title>ChefMate Automation - Premium Dashboard</title>\n");
            htmlContent.append("  <link href=\"https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap\" rel=\"stylesheet\">\n");
            htmlContent.append("  <style>\n");
            htmlContent.append("    * { box-sizing: border-box; margin: 0; padding: 0; font-family: 'Inter', sans-serif; }\n");
            htmlContent.append("    body { display: flex; height: 100vh; background: #0f172a; color: #f8fafc; overflow: hidden; }\n");
            htmlContent.append("    .sidebar { width: 380px; background: #1e293b; display: flex; flex-direction: column; border-right: 1px solid #334155; box-shadow: 4px 0 25px rgba(0,0,0,0.3); z-index: 10; }\n");
            htmlContent.append("    .sidebar-header { padding: 24px; background: #0f172a; border-bottom: 1px solid #334155; }\n");
            htmlContent.append("    .sidebar-header h2 { font-size: 18px; font-weight: 700; color: #38bdf8; letter-spacing: -0.5px; }\n");
            htmlContent.append("    .sidebar-header p { font-size: 12px; color: #94a3b8; margin-top: 4px; line-height: 1.5; }\n");

            // Nút dọn dẹp Premium
            htmlContent.append("    .clean-btn { margin-top: 14px; width: 100%; padding: 10px; background: #ef4444; color: white; border: none; border-radius: 8px; font-weight: 600; font-size: 13px; cursor: pointer; transition: all 0.2s; display: flex; align-items: center; justify-content: center; gap: 6px; }\n");
            htmlContent.append("    .clean-btn:hover { background: #dc2626; transform: translateY(-1px); box-shadow: 0 4px 12px rgba(239,68,68,0.2); }\n");

            htmlContent.append("    .report-list { flex: 1; overflow-y: auto; padding: 16px; display: flex; flex-direction: column; gap: 12px; }\n");
            htmlContent.append("    .report-list::-webkit-scrollbar { width: 6px; }\n");
            htmlContent.append("    .report-list::-webkit-scrollbar-thumb { background: #475569; border-radius: 3px; }\n");

            // Item thiết kế bo góc hiện đại cực đẹp
            htmlContent.append("    .item-wrapper { position: relative; display: flex; align-items: center; background: #1e293b; border: 1px solid #334155; border-radius: 12px; transition: all 0.2s cubic-bezier(0.4, 0, 0.2, 1); }\n");
            htmlContent.append("    .item-wrapper:hover { background: #2f3e56; border-color: #475569; transform: translateY(-1px); }\n");
            htmlContent.append("    .report-item { flex: 1; display: flex; flex-direction: column; gap: 6px; padding: 16px 50px 16px 16px; text-decoration: none; color: #cbd5e1; overflow: hidden; }\n");
            htmlContent.append("    .item-wrapper.active { background: rgba(56, 189, 248, 0.08); border-color: #38bdf8; box-shadow: 0 0 15px rgba(56,189,248,0.1); }\n");
            htmlContent.append("    .item-wrapper.active .report-item { color: #38bdf8; font-weight: 600; }\n");
            htmlContent.append("    .report-item .time-title { font-size: 14px; display: flex; align-items: center; gap: 8px; font-weight: 500; }\n");
            htmlContent.append("    .report-item .folder-name { font-size: 11px; color: #64748b; font-family: monospace; }\n");
            htmlContent.append("    .badge-new { background: #10b981; color: #fff; font-size: 10px; padding: 2px 8px; border-radius: 20px; font-weight: 700; }\n");

            // Nút ghim 📌 VIP tương tác cao
            htmlContent.append("    .pin-btn { position: absolute; right: 14px; background: #334155; border: none; color: #94a3b8; cursor: pointer; font-size: 14px; width: 28px; height: 28px; border-radius: 50%; display: flex; align-items: center; justify-content: center; transition: all 0.2s; z-index: 5; }\n");
            htmlContent.append("    .pin-btn:hover { background: #475569; color: #fff; transform: scale(1.1); }\n");
            htmlContent.append("    .item-wrapper.pinned { border-color: #f59e0b; background: rgba(245, 158, 11, 0.03); }\n");
            htmlContent.append("    .item-wrapper.pinned .pin-btn { background: #f59e0b; color: #fff; filter: drop-shadow(0 2px 6px rgba(245,158,11,0.3)); }\n");

            htmlContent.append("    .main-content { flex: 1; display: flex; flex-direction: column; background: #0f172a; }\n");
            htmlContent.append("    iframe { width: 100%; height: 100%; border: none; background: #ffffff; }\n");
            htmlContent.append("  </style>\n");

            // Toàn bộ Logic xử lý Ghim/Xóa trực tiếp bằng Javascript chống mất data
            htmlContent.append("  <script>\n");
            htmlContent.append("    function setActive(link) {\n");
            htmlContent.append("      var wrappers = document.getElementsByClassName('item-wrapper');\n");
            htmlContent.append("      for(var i=0; i<wrappers.length; i++) wrappers[i].classList.remove('active');\n");
            htmlContent.append("      link.parentElement.classList.add('active');\n");
            htmlContent.append("    }\n");

            // Hàm click ghim chọn lọc từng phiên một cách chủ động
            htmlContent.append("    function togglePin(btn, sessionId) {\n");
            htmlContent.append("      var wrapper = btn.parentElement;\n");
            htmlContent.append("      wrapper.classList.toggle('pinned');\n");
            htmlContent.append("      var isPinned = wrapper.classList.contains('pinned');\n");
            htmlContent.append("      var pinnedArray = JSON.parse(localStorage.getItem('chefmate_pinned') || '[]');\n");
            htmlContent.append("      if(isPinned) { if(!pinnedArray.includes(sessionId)) pinnedArray.push(sessionId); }\n");
            htmlContent.append("      else { pinnedArray = pinnedArray.filter(id => id !== sessionId); }\n");
            htmlContent.append("      localStorage.setItem('chefmate_pinned', JSON.stringify(pinnedArray));\n");
            htmlContent.append("    }\n");

            // Hàm dọn dẹp thông minh: Chỉ xóa những phiên KHÔNG GHIM, giữ lại phiên ĐƯỢC GHIM
            htmlContent.append("    function cleanSystem() {\n");
            htmlContent.append("      if(!confirm('Hệ thống sẽ dọn dẹp các phiên nháp cũ và CHỈ GIỮ LẠI các phiên có ghim 📌. Bạn có chắc chắn?')) return;\n");
            htmlContent.append("      var pinnedArray = JSON.parse(localStorage.getItem('chefmate_pinned') || '[]');\n");
            htmlContent.append("      var deletedArray = JSON.parse(localStorage.getItem('chefmate_deleted') || '[]');\n");
            htmlContent.append("      var wrappers = document.getElementsByClassName('item-wrapper');\n");
            htmlContent.append("      for(var i=0; i<wrappers.length; i++) {\n");
            htmlContent.append("        var sid = wrappers[i].getAttribute('data-id');\n");
            // Nếu không nằm trong danh sách ghim thì ném vào danh sách xóa ẩn giao diện
            htmlContent.append("        if(!pinnedArray.includes(sid)) {\n");
            htmlContent.append("          deletedArray.push(sid);\n");
            htmlContent.append("          wrappers[i].style.display = 'none';\n");
            htmlContent.append("        }\n");
            htmlContent.append("      }\n");
            htmlContent.append("      localStorage.setItem('chefmate_deleted', JSON.stringify(deletedArray));\n");
            htmlContent.append("      alert('🧹 Đã dọn dẹp tối ưu dung lượng ổ cứng thành công!');\n");
            htmlContent.append("    }\n");

            // Khi trang load: Đồng bộ trạng thái Ghim và Ẩn các trang đã xóa
            htmlContent.append("    window.onload = function() {\n");
            htmlContent.append("      var pinnedArray = JSON.parse(localStorage.getItem('chefmate_pinned') || '[]');\n");
            htmlContent.append("      var deletedArray = JSON.parse(localStorage.getItem('chefmate_deleted') || '[]');\n");
            htmlContent.append("      var wrappers = document.getElementsByClassName('item-wrapper');\n");
            htmlContent.append("      for(var i=0; i<wrappers.length; i++) {\n");
            htmlContent.append("        var sid = wrappers[i].getAttribute('data-id');\n");
            htmlContent.append("        if(deletedArray.includes(sid)) { wrappers[i].style.display = 'none'; }\n");
            htmlContent.append("        if(pinnedArray.includes(sid)) { wrappers[i].classList.add('pinned'); }\n");
            htmlContent.append("      }\n");
            htmlContent.append("    }\n");
            htmlContent.append("  </script>\n");
            htmlContent.append("</head>\n<body>\n");

            // Sidebar Menu
            htmlContent.append("  <div class='sidebar'>\n");
            htmlContent.append("    <div class='sidebar-header'>\n");
            htmlContent.append("      <h2>🚀 CHEFMATE AUTOMATION</h2>\n");
            htmlContent.append("      <p>Ghim báo cáo quan trọng 📌 và dọn dẹp khoa học bộ nhớ local.</p>\n");
            htmlContent.append("      <button class='clean-btn' onclick='cleanSystem()'>🧹 DỌN DẸP HỆ THỐNG</button>\n");
            htmlContent.append("    </div>\n");
            htmlContent.append("    <div class='report-list' id='history-list'>\n");

            // Item Mới Nhất
            htmlContent.append(String.format("      <div class='item-wrapper active' data-id='Session_%s'>\n", timestamp));
            htmlContent.append(String.format("        <a href='./Session_%s/index.html' target='report-frame' class='report-item' onclick='setActive(this)'>\n", timestamp));
            htmlContent.append(String.format("          <div class='time-title'>🕒 Lượt chạy: %s <span class='badge-new'>NEW</span></div>\n", displayTime));
            htmlContent.append(String.format("          <div class='folder-name'>Session_%s</div>\n", timestamp));
            htmlContent.append("        </a>\n");
            htmlContent.append(String.format("        <button class='pin-btn' onclick=\"togglePin(this, 'Session_%s')\" title='Ghim báo cáo này'>📌</button>\n", timestamp));
            htmlContent.append("      </div>\n");

            // Đọc nối chuỗi lưu lịch sử cũ
            File historyListFile = new File(storagePath + "/history_links.txt");
            String oldLinks = "";
            if (historyListFile.exists()) {
                oldLinks = FileUtils.readFileToString(historyListFile, "UTF-8");
                oldLinks = oldLinks.replaceAll("active", ""); // Reset trạng thái active của các phiên cũ
                htmlContent.append(oldLinks);
            }

            htmlContent.append("    </div>\n");
            htmlContent.append("  </div>\n");

            // Khung Iframe hiển thị báo cáo Allure
            htmlContent.append("  <div class='main-content'>\n");
            htmlContent.append(String.format("    <iframe name='report-frame' src='./Session_%s/index.html'></iframe>\n", timestamp));
            htmlContent.append("  </div>\n");
            htmlContent.append("</body>\n</html>");

            // Ghi file index.html vật lý sạch lỗi font chữ tiếng Việt
            FileUtils.writeStringToFile(indexFile, htmlContent.toString(), "UTF-8");

            // Lưu lịch sử link để phục vụ nối chuỗi lượt sau
            String currentLinkSave = String.format(
                    "      <div class='item-wrapper' data-id='Session_%s'>\n" +
                            "        <a href='./Session_%s/index.html' target='report-frame' class='report-item' onclick='setActive(this)'>\n" +
                            "          <div class='time-title'>🕒 Lượt chạy: %s</div>\n" +
                            "          <div class='folder-name'>Session_%s</div>\n" +
                            "        </a>\n" +
                            "        <button class='pin-btn' onclick=\"togglePin(this, 'Session_%s')\" title='Ghim báo cáo này'>📌</button>\n" +
                            "      </div>\n", timestamp, timestamp, displayTime, timestamp, timestamp);

            FileUtils.writeStringToFile(historyListFile, currentLinkSave + oldLinks, "UTF-8");

            log4jLogger.info("✨ Đã đồng bộ hệ thống Báo cáo Tương tác Cao cấp thành công!");

            // 🚀 TỰ ĐỘNG KHỞI CHẠY TRÌNH DUYỆT
            String openCommand = "allure open " + storagePath;
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                Runtime.getRuntime().exec("cmd.exe /c " + openCommand);
            } else {
                Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", openCommand});
            }

        } catch (Exception e) {
            log4jLogger.error("❌ Lỗi hệ thống: " + e.getMessage());
        }
        log4jLogger.info("======================================================");
    }
}