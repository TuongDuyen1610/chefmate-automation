//package core.listener;
//
//import core.utils.CloudinaryService;
//import core.utils.GoogleSheetService;
//import core.utils.ScreenshotUtil;
//import core.utils.TestResultData;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * GoogleSheetTestListener
// *
// * ✅ Capture screenshot SYNC (chợp ngay)
// * ✅ Upload Cloudinary SYNC (upload ngay)
// * ✅ Update Google Sheet SYNC (cập nhật ngay)
// * ✅ Không chờ, không queue
// */
//public class GoogleSheetTestListener implements ITestListener {
//
//    private static final Logger logger =
//            LoggerFactory.getLogger(GoogleSheetTestListener.class);
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        sendToSheet(result, "Passed");
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//        sendToSheet(result, "Failed");
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult result) {
//        sendToSheet(result, "N/A");
//    }
//
//    /**
//     * ================= SEND TO SHEET (SYNC - NGAY LẬP TỨC) =================
//     * ✅ Chụp ảnh NGAY
//     * ✅ Upload NGAY
//     * ✅ Cập nhật Google Sheet NGAY
//     */
//    private void sendToSheet(ITestResult result, String status) {
//        try {
//            String testId = getTestId(result);
//
//            logger.info("📊 [" + testId + "] START: Capturing screenshot...");
//
//            /**
//             * ✅ CHỤP ẢNH NGAY (SYNC)
//             */
//            String screenshotPath = ScreenshotUtil.captureScreenshot(testId);
//
//            if (screenshotPath != null) {
//                logger.info("✅ [" + testId + "] Screenshot captured: " + screenshotPath);
//            } else {
//                logger.warn("⚠️ [" + testId + "] Screenshot capture FAILED");
//            }
//
//            String evidenceLink = "N/A";
//
//            /**
//             * ✅ UPLOAD ẢNH NGAY (SYNC)
//             */
//            if (screenshotPath != null) {
//                logger.info("📤 [" + testId + "] Uploading to Cloudinary...");
//                evidenceLink = CloudinaryService.uploadScreenshot(
//                        screenshotPath,
//                        testId + "_" + System.currentTimeMillis()
//                );
//                logger.info("✅ [" + testId + "] Upload complete. Link: " + evidenceLink);
//            }
//
//            /**
//             * Test Date
//             */
//            String date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
//                    .format(new Date());
//
//            /**
//             * Data
//             */
//            TestResultData data = new TestResultData(
//                    testId,
//                    status,
//                    evidenceLink,
//                    date
//            );
//
//            /**
//             * ✅ CẬP NHẬT GOOGLE SHEET NGAY (SYNC)
//             */
//            logger.info("📝 [" + testId + "] Updating Google Sheet...");
//            GoogleSheetService.updateTestResultById(data);
//
//            logger.info("✅ [" + testId + "] COMPLETE: Sheet Updated. Evidence: " + evidenceLink);
//
//        } catch (Exception e) {
//            logger.error("❌ Error in sendToSheet: " + e.getMessage(), e);
//        }
//    }
//
//    /**
//     * ================= GET TEST ID =================
//     */
//    private String getTestId(ITestResult result) {
//        String description = result.getMethod().getDescription();
//
//        if (description == null || description.isEmpty()) {
//            return result.getMethod().getMethodName();
//        }
//
//        if (description.contains("-")) {
//            return description.split("-")[0].trim();
//        }
//
//        return description.trim();
//    }
//}
package core.listener;

import core.utils.CloudinaryService;
import core.utils.GoogleSheetService;
import core.utils.ScreenshotUtil;
import core.utils.TestResultData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * GoogleSheetTestListener
 *
 * ✅ Test xong → Chụp ảnh NGAY (có retry)
 * ✅ Chụp xong → Upload NGAY
 * ✅ Upload xong → Update Sheet NGAY
 * ✅ Handle device crash
 */
public class GoogleSheetTestListener implements ITestListener {

    private static final Logger logger =
            LoggerFactory.getLogger(GoogleSheetTestListener.class);

    private static final int MAX_SCREENSHOT_RETRY = 3;
    private static final int RETRY_DELAY_MS = 1000;

    @Override
    public void onTestSuccess(ITestResult result) {
        sendToSheet(result, "Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        sendToSheet(result, "Failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        sendToSheet(result, "N/A");
    }

    /**
     * ================= SEND TO SHEET =================
     * ✅ Test xong → Chụp ảnh (có retry nếu device crash)
     * ✅ Upload + Update Sheet
     */
    private void sendToSheet(ITestResult result, String status) {
        try {
            String testId = getTestId(result);

            logger.info("📊 [" + testId + "] START: Capturing screenshot (max " + MAX_SCREENSHOT_RETRY + " retries)...");

            /**
             * ✅ STEP 1: CHỤP ẢNH NGAY (SYNC) - CÓ RETRY
             */
            String screenshotPath = captureScreenshotWithRetry(testId);

            if (screenshotPath != null) {
                logger.info("✅ [" + testId + "] Screenshot captured: " + screenshotPath);
            } else {
                logger.warn("⚠️ [" + testId + "] Screenshot capture FAILED after " + MAX_SCREENSHOT_RETRY + " retries");
            }

            String evidenceLink = "N/A";

            /**
             * ✅ STEP 2: UPLOAD ẢNH NGAY (SYNC - chỉ upload nếu có ảnh)
             */
            if (screenshotPath != null) {
                logger.info("📤 [" + testId + "] START: Uploading to Cloudinary (max 60s)...");
                evidenceLink = CloudinaryService.uploadScreenshot(
                        screenshotPath,
                        testId + "_" + System.currentTimeMillis()
                );
                logger.info("✅ [" + testId + "] Upload complete. Link: " + evidenceLink);
            } else {
                logger.warn("⚠️ [" + testId + "] Skipped upload - no screenshot available");
            }

            /**
             * ✅ STEP 3: TEST DATE
             */
            String date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                    .format(new Date());

            /**
             * ✅ STEP 4: CREATE DATA
             */
            TestResultData data = new TestResultData(
                    testId,
                    status,
                    evidenceLink,
                    date
            );

            /**
             * ✅ STEP 5: UPDATE GOOGLE SHEET NGAY (SYNC)
             */
            logger.info("📝 [" + testId + "] START: Updating Google Sheet...");
            GoogleSheetService.updateTestResultById(data);

            logger.info("✅ [" + testId + "] COMPLETE: Sheet Updated with Evidence: " + evidenceLink);

        } catch (Exception e) {
            logger.error("❌ Error in sendToSheet: " + e.getMessage(), e);
        }
    }

    /**
     * ================= CAPTURE SCREENSHOT WITH RETRY =================
     * Retry chụp ảnh nếu device crash
     */
    private String captureScreenshotWithRetry(String testId) {
        for (int attempt = 1; attempt <= MAX_SCREENSHOT_RETRY; attempt++) {
            try {
                logger.info("⏳ [" + testId + "] Attempt " + attempt + "/" + MAX_SCREENSHOT_RETRY + ": Capturing...");

                String path = ScreenshotUtil.captureScreenshot(testId);

                if (path != null && !path.isEmpty()) {
                    logger.info("✅ [" + testId + "] Attempt " + attempt + ": SUCCESS - " + path);
                    return path;
                } else {
                    logger.warn("⚠️ [" + testId + "] Attempt " + attempt + ": Returned null/empty");
                }

            } catch (Exception e) {
                logger.warn("⚠️ [" + testId + "] Attempt " + attempt + " failed: " + e.getClass().getSimpleName() + " - " + e.getMessage());

                // Nếu không phải attempt cuối cùng, retry
                if (attempt < MAX_SCREENSHOT_RETRY) {
                    logger.info("🔄 [" + testId + "] Retrying... (Attempt " + (attempt + 1) + " after " + RETRY_DELAY_MS + "ms)");
                    try {
                        Thread.sleep(RETRY_DELAY_MS);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        logger.error("❌ [" + testId + "] Interrupted during retry delay");
                        break;
                    }
                } else {
                    logger.error("❌ [" + testId + "] All " + MAX_SCREENSHOT_RETRY + " attempts failed");
                }
            }
        }

        return null; // Tất cả retry đều thất bại
    }

    /**
     * ================= GET TEST ID =================
     */
    private String getTestId(ITestResult result) {
        String description = result.getMethod().getDescription();

        if (description == null || description.isEmpty()) {
            return result.getMethod().getMethodName();
        }

        if (description.contains("-")) {
            return description.split("-")[0].trim();
        }

        return description.trim();
    }
}