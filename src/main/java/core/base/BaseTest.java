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

//TÍCH HỢP ATTACH RECORDING + IMAGE ALLURE REPORT
// package core.base;
// import org.testng.ITestResult;
// import core.driver.DriverManager;
// import core.utils.AllureHelper;
// import io.appium.java_client.AppiumDriver;
// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.testng.annotations.*;

// @Listeners({
//         core.listener.AllureTestListener.class,
//         core.listener.GoogleSheetTestListener.class
// })
// public class BaseTest {

//     // Sử dụng Log4j 2 cho các mục đích ghi log chuyên sâu và chạy song song
//     protected static final Logger log4jLogger = LogManager.getLogger(BaseTest.class);

//     // Giữ SLF4J để tương thích với các thư viện cũ hoặc listener
//     protected static final org.slf4j.Logger slf4jLogger = LoggerFactory.getLogger(BaseTest.class);

//     protected AppiumDriver driver;
//     protected AllureHelper allureHelper;

//     @BeforeSuite
//     public void beforeSuite() {
//         log4jLogger.info("======================================================");
//         log4jLogger.info("🚀 KHỞI ĐỘNG SUITE KIỂM THỬ: CHUẨN BỊ MÔI TRƯỜNG");
//         log4jLogger.info("======================================================");
//     }

//     @BeforeMethod
//     @Parameters({"deviceName", "platformVersion"})
//     public void setup(@Optional("DefaultDevice") String deviceName, @Optional("DefaultVersion") String platformVersion) {
//         log4jLogger.info("--- 🔄 BẮT ĐẦU TEST CASE ---");
//         log4jLogger.info("📱 Đang cấu hình thiết bị: " + deviceName + " (Phiên bản: " + platformVersion + ")");

//         try {
//             DriverManager.initDriver();
//             this.driver = DriverManager.getDriver();

//             log4jLogger.info("✅ Appium Driver đã sẵn sàng. Session ID: " + driver.getSessionId());

//             this.allureHelper = new AllureHelper(driver);
//             log4jLogger.info("📊 Đã kết nối Allure Helper để theo dõi các bước test.");

//             // 🎥 KÍCH HOẠT QUAY MÀN HÌNH CHUẨN: Bật ngay sau khi Driver đã sống nhăn răng
//             if (this.driver instanceof io.appium.java_client.android.AndroidDriver) {
//                 ((io.appium.java_client.android.AndroidDriver) this.driver).startRecordingScreen();
//                 log4jLogger.info("📽️ [RECORD] Đã bật quay màn hình Android thành công.");
//             }

//         } catch (Exception e) {
//             log4jLogger.error("❌ LỖI NGHIÊM TRỌNG: Không thể khởi tạo Appium Driver!");
//             log4jLogger.error("Chi tiết lỗi: " + e.getMessage());
//             throw e;
//         }
//     }

//     @AfterMethod
//     public void tearDown(ITestResult result) {
//         log4jLogger.info("--- 🛑 KẾT THÚC TEST CASE ---");

//         if (driver != null) {
//             try {
//                 if (driver instanceof io.appium.java_client.android.AndroidDriver) {
//                     log4jLogger.info("📽️ [RECORD] Đang dừng quay màn hình và trích xuất file video...");
//                     String base64Video = ((io.appium.java_client.android.AndroidDriver) driver).stopRecordingScreen();

//                     if (base64Video != null && !base64Video.isEmpty()) {
//                         byte[] videoBytes = java.util.Base64.getDecoder().decode(base64Video);

//                         // 🎯 GIẢI PHÁP ĐỘT PHÁ: Đính kèm trực tiếp thông qua luồng byte stream tĩnh của Allure.
//                         // Luồng này hoạt động bất chấp cấu hình luồng hay Listener có đóng kịch bản sớm hay không.
//                         String videoTitle = (result.getStatus() == ITestResult.FAILURE)
//                                 ? "🚨 Video Luồng Chạy Bị Lỗi (FAILED)"
//                                 : "📹 Video Luồng Chạy Thành Công (PASSED)";

//                         io.qameta.allure.Allure.addAttachment(
//                                 videoTitle,
//                                 "video/mp4",
//                                 new java.io.ByteArrayInputStream(videoBytes),
//                                 ".mp4"
//                         );
//                         log4jLogger.info("✨ [RECORD] Đã đẩy luồng Video vật lý vào bộ đệm đính kèm của Allure thành công!");
//                     }
//                 }
//             } catch (Exception e) {
//                 log4jLogger.error("❌ [RECORD] Lỗi khi xử lý trích xuất video: " + e.getMessage());
//             }

//             // Tiến hành dọn dẹp driver
//             log4jLogger.info("🧹 Đang đóng ứng dụng và giải phóng tài nguyên Driver...");
//             DriverManager.quitDriver();
//             log4jLogger.info("✅ Đã dọn dẹp driver thành công.");
//         }
//     }

//     @AfterSuite
//     public void afterSuite() {
//         log4jLogger.info("======================================================");
//         log4jLogger.info("🏁 TẤT CẢ TEST CASES ĐÃ HOÀN THÀNH");
//         log4jLogger.info("📊 Vui lòng kiểm tra Allure Report và Google Sheets để xem kết quả.");
//         log4jLogger.info("======================================================");
//     }
// }
// chú thích vì reload video record trên CI/CD bị lỗi
