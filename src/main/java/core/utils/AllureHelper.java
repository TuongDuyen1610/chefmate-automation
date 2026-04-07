package core.utils;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;

/**
 * AllureHelper.java
 * ✅ Cung cấp utility methods cho Allure Report
 */
public class AllureHelper {
    private static final Logger logger = LoggerFactory.getLogger(AllureHelper.class);
    private static AppiumDriver driver;

    public AllureHelper(AppiumDriver driver) {
        AllureHelper.driver = driver;
    }

    /**
     * ✅ Chụp ảnh và attach vào Allure
     */
    public static void attachScreenshot(String attachmentName) {
        try {
            if (driver == null) {
                logger.warn("❌ Driver is null, cannot take screenshot");
                return;
            }

            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);

            Allure.addAttachment(
                    attachmentName,
                    "image/png",
                    new ByteArrayInputStream(screenshot),
                    ".png"
            );
            logger.info("✅ Screenshot attached: " + attachmentName);
        } catch (Exception e) {
            logger.error("❌ Failed to attach screenshot: " + e.getMessage());
        }
    }

    /**
     * ✅ Attach ảnh khi fail
     */
    public static void attachScreenshotOnFailure() {
        attachScreenshot("❌ Failure Screenshot");
    }

    /**
     * ✅ Attach ảnh khi pass
     */
    public static void attachScreenshotOnSuccess() {
        attachScreenshot("✅ Success Screenshot");
    }

    /**
     * ✅ Attach log
     */
    public static void attachLog(String logMessage) {
        try {
            Allure.addAttachment(
                    "Test Log",
                    "text/plain",
                    logMessage,
                    ".txt"
            );
            logger.info("✅ Log attached");
        } catch (Exception e) {
            logger.error("❌ Failed to attach log: " + e.getMessage());
        }
    }

    /**
     * ✅ Attach error message
     */
    public static void attachErrorMessage(String errorMessage) {
        try {
            Allure.addAttachment(
                    "💥 Error Message",
                    "text/plain",
                    errorMessage,
                    ".txt"
            );
            logger.error("❌ Error attached: " + errorMessage);
        } catch (Exception e) {
            logger.error("❌ Failed to attach error: " + e.getMessage());
        }
    }

    /**
     * ✅ Record step action
     */
    @Step("📍 {0}")
    public static void step(String stepName) {
        logger.info("📍 STEP: " + stepName);
    }

    /**
     * ✅ Record step với 1 parameter
     */
    @Step("📍 {0}: {1}")
    public static void stepWithParam(String stepName, String param) {
        logger.info("📍 STEP: " + stepName + " | Param: " + param);
    }
}