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
 * ✅ Capture screenshot riêng
 * ✅ Upload Cloudinary
 * ✅ Map Test ID
 * ✅ Update Google Sheet
 * ✅ Không ảnh hưởng Allure
 */
public class GoogleSheetTestListener implements ITestListener {

    private static final Logger logger =
            LoggerFactory.getLogger(GoogleSheetTestListener.class);

    /**
     * ================= PASS =================
     */
    @Override
    public void onTestSuccess(ITestResult result) {

        sendToSheet(result, "Passed");
    }

    /**
     * ================= FAIL =================
     */
    @Override
    public void onTestFailure(ITestResult result) {

        sendToSheet(result, "Failed");
    }

    /**
     * ================= SKIP =================
     */
    @Override
    public void onTestSkipped(ITestResult result) {

        sendToSheet(result, "N/A");
    }

    /**
     * ================= SEND TO SHEET =================
     */
    private void sendToSheet(ITestResult result, String status) {

        try {

            String testId = getTestId(result);

            logger.info("📊 Update Google Sheet: " + testId);

            /**
             * Capture Screenshot riêng
             */
            String screenshotPath =
                    ScreenshotUtil.captureScreenshot(testId);
            String evidenceLink = "N/A";
            /**
             * Upload Cloudinary
             */
            if (screenshotPath != null) {

                evidenceLink =
                        CloudinaryService.uploadScreenshot(
                                screenshotPath,
                                testId + "_" + System.currentTimeMillis()
                        );
            }

            /**
             * Test Date
             */
            String date =
                    new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                            .format(new Date());

            /**
             * Data
             */
            TestResultData data =
                    new TestResultData(
                            testId,
                            status,
                            evidenceLink,
                            date
                    );

            /**
             * Update Sheet
             */
            GoogleSheetService.updateTestResultById(data);

            logger.info("✅ Sheet Updated: " + testId);

        } catch (Exception e) {

            logger.error("❌ Google Sheet Listener Error: " + e.getMessage());
        }
    }

    /**
     * ================= GET TEST ID =================
     */
    private String getTestId(ITestResult result) {

        String description =
                result.getMethod().getDescription();

        if (description == null || description.isEmpty()) {

            return result.getMethod().getMethodName();
        }

        if (description.contains("-")) {

            return description.split("-")[0].trim();
        }

        return description.trim();
    }
}