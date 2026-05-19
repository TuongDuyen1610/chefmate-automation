package core.listener;

import core.utils.AllureHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * AllureTestListener.java
 * ✅ Tự động capture screenshot khi test fail
 * ✅ Record test status và thời gian
 */
public class AllureTestListener implements ITestListener {
    private static final Logger logger = LoggerFactory.getLogger(AllureTestListener.class);

    @Override
    public void onStart(ITestContext context) {
        logger.info("========== 🚀 TEST SUITE STARTED: " + context.getName() + " ==========");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("========== ✅ TEST SUITE FINISHED: " + context.getName() + " ==========");
        logger.info("✅ Passed: " + context.getPassedTests().size());
        logger.info("❌ Failed: " + context.getFailedTests().size());
        logger.info("⊘ Skipped: " + context.getSkippedTests().size());
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("\n▶️  TEST STARTED: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("✅ TEST PASSED: " + result.getName());
        // Attach screenshot khi pass
        AllureHelper.attachScreenshotOnSuccess();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("❌ TEST FAILED: " + result.getName());

        // Attach screenshot khi fail (BẮT BUỘC)
        AllureHelper.attachScreenshotOnFailure();

        // Attach exception message
        if (result.getThrowable() != null) {
            String errorMessage = result.getThrowable().getMessage();
            String stackTrace = getStackTrace(result.getThrowable());

            AllureHelper.attachErrorMessage("Error: " + errorMessage);
            AllureHelper.attachLog("Stack Trace:\n" + stackTrace);

            logger.error("Error: " + errorMessage);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("⊘ TEST SKIPPED: " + result.getName());
        if (result.getThrowable() != null) {
            AllureHelper.attachLog("Skipped reason: " + result.getThrowable().getMessage());
        }
    }

    /**
     * ✅ Convert Throwable to Stack Trace String
     */
    private static String getStackTrace(Throwable throwable) {
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = throwable.getStackTrace();

        for (StackTraceElement element : stackTrace) {
            sb.append(element.toString()).append("\n");
        }

        return sb.toString();
    }
}


//package core.listener;
//
//import core.utils.AllureHelper;
//import io.qameta.allure.Allure;
//import io.qameta.allure.Attachment;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
///**
// * AllureTestListener.java
// * ✅ Tối ưu hóa đính kèm log ngay cả khi file đang bị chiếm dụng
// */
//public class AllureTestListener implements ITestListener {
//    private static final Logger logger = LoggerFactory.getLogger(AllureTestListener.class);
//    private static final String LOG_FILE_PATH = "logs/appium_test.log";
//
//    @Override
//    public void onStart(ITestContext context) {
//        logger.info("======================================================");
//        logger.info("🚀 BẮT ĐẦU CHẠY SUITE: " + context.getName());
//        logger.info("======================================================");
//    }
//
//    @Override
//    public void onFinish(ITestContext context) {
//        logger.info("======================================================");
//        logger.info("🏁 HOÀN THÀNH SUITE: " + context.getName());
//        logger.info("======================================================");
//    }
//
//    @Override
//    public void onTestStart(ITestResult result) {
//        logger.info("▶️ Đang thực thi Test Case: " + result.getName());
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        logger.info("✅ KẾT QUẢ: PASSED - " + result.getName());
//        AllureHelper.attachScreenshotOnSuccess();
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//        logger.error("❌ KẾT QUẢ: FAILED - " + result.getName());
//
//        // 1. Chụp ảnh màn hình
//        AllureHelper.attachScreenshotOnFailure();
//
//        // 2. ÉP ĐÍNH KÈM LOG (Dùng Allure API trực tiếp để tránh lỗi không hiện file)
//        try {
//            // Ép Log4j xả dữ liệu xuống file
//            org.apache.logging.log4j.core.LoggerContext ctx = (org.apache.logging.log4j.core.LoggerContext) org.apache.logging.log4j.LogManager.getContext(false);
//            ctx.reconfigure();
//
//            File logFile = new File(LOG_FILE_PATH);
//            if (logFile.exists() && logFile.length() > 0) {
//                byte[] logContent = Files.readAllBytes(Paths.get(LOG_FILE_PATH));
//                // Sử dụng InputStream để đính kèm đảm bảo Allure đọc được ngay
//                Allure.addAttachment("Full System Log", "text/plain", new ByteArrayInputStream(logContent), ".log");
//                logger.info("✅ Đã đính kèm System Log thành công vào Allure.");
//            } else {
//                logger.warn("⚠️ Không tìm thấy file log hoặc file rỗng tại: " + logFile.getAbsolutePath());
//            }
//        } catch (Exception e) {
//            logger.error("❌ Lỗi khi đọc file log: " + e.getMessage());
//        }
//
//        // 3. Xử lý lỗi và StackTrace
//        if (result.getThrowable() != null) {
//            saveTextLog("Chi tiết lỗi: " + result.getThrowable().getMessage());
//            saveTextLog("Stack Trace:\n" + getStackTrace(result.getThrowable()));
//        }
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult result) {
//        logger.warn("⊘ KẾT QUẢ: SKIPPED - " + result.getName());
//    }
//
//    /**
//     * ✅ Giữ lại để dùng cho các mục đích ghi chú nhanh khác
//     */
//    @Attachment(value = "{0}", type = "text/plain")
//    public static String saveTextLog(String message) {
//        return message;
//    }
//
//    private static String getStackTrace(Throwable throwable) {
//        StringBuilder sb = new StringBuilder();
//        StackTraceElement[] stackTrace = throwable.getStackTrace();
//        int limit = Math.min(stackTrace.length, 15);
//        sb.append(throwable.toString()).append("\n");
//        for (int i = 0; i < limit; i++) {
//            sb.append("\tat ").append(stackTrace[i].toString()).append("\n");
//        }
//        return sb.toString();
//    }
//}