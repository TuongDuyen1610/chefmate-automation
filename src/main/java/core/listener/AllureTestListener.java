package core.listener;

import core.utils.AllureHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/// **
// * AllureTestListener.java
// * ✅ Tự động capture screenshot khi test fail
// * ✅ Record test status và thời gian
// */
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

////TÍCH HỢP ATTACH RECORDING + IMAGE ALLURE REPORT
//package core.listener;
//
//import core.utils.AllureHelper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//public class AllureTestListener implements ITestListener {
//    private static final Logger logger = LoggerFactory.getLogger(AllureTestListener.class);
//
//    @Override
//    public void onStart(ITestContext context) {
//        logger.info("========== 🚀 TEST SUITE STARTED: " + context.getName() + " ==========");
//    }
//
//    @Override
//    public void onFinish(ITestContext context) {
//        logger.info("========== ✅ TEST SUITE FINISHED: " + context.getName() + " ==========");
//        logger.info("✅ Passed: " + context.getPassedTests().size());
//        logger.info("❌ Failed: " + context.getFailedTests().size());
//        logger.info("⊘ Skipped: " + context.getSkippedTests().size());
//    }
//
//    @Override
//    public void onTestStart(ITestResult result) {
//        logger.info("\n▶️  TEST STARTED: " + result.getName());
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        logger.info("✅ TEST PASSED: " + result.getName());
//        // Chỉ giữ lại chụp ảnh báo cáo thành công
////        AllureHelper.attachScreenshotOnSuccess();
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//        logger.error("❌ TEST FAILED: " + result.getName());
//
//        // Chỉ giữ lại chụp ảnh báo cáo lỗi
////        AllureHelper.attachScreenshotOnFailure();
//
//        if (result.getThrowable() != null) {
//            String errorMessage = result.getThrowable().getMessage();
//            String stackTrace = getStackTrace(result.getThrowable());
//
//            AllureHelper.attachErrorMessage("Error: " + errorMessage);
//            AllureHelper.attachLog("Stack Trace:\n" + stackTrace);
//
//            logger.error("Error: " + errorMessage);
//        }
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult result) {
//        logger.warn("⊘ TEST SKIPPED: " + result.getName());
//        if (result.getThrowable() != null) {
//            AllureHelper.attachLog("Skipped reason: " + result.getThrowable().getMessage());
//        }
//    }
//
//    private static String getStackTrace(Throwable throwable) {
//        StringBuilder sb = new StringBuilder();
//        StackTraceElement[] stackTrace = throwable.getStackTrace();
//        for (StackTraceElement element : stackTrace) {
//            sb.append(element.toString()).append("\n");
//        }
//        return sb.toString();
//    }
//}
