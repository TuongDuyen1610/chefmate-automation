package tests;

import core.base.BaseTest;
import core.data.LoginData;
import core.utils.JsonHelper;
import flows.AuthenticationFlow;
import screens.HomeScreen;
import screens.RecipeDetailScreen;
import core.utils.AllureHelper;
import core.utils.WaitingHelper;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RecipeDisplayTest.java
 * ✅ TÍNH NĂNG 1: Hiển thị công thức nấu ăn
 * 5 Happy Case test
 */
@Feature("TÍNH NĂNG 1: Hiển thị công thức nấu ăn")
public class RecipeDisplayTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(RecipeDisplayTest.class);

    private final AuthenticationFlow authFlow = new AuthenticationFlow();
    private final HomeScreen homeScreen = new HomeScreen();
    private final RecipeDetailScreen recipeDetailScreen = new RecipeDetailScreen();

    private final LoginData loginData = JsonHelper.readLoginData();
    private String phoneOrEmail = loginData.login;
    private String password = loginData.password;

    @Test(priority = 1, description = "RecipeDisplay_TC_01 - Kiểm tra hiển thị danh sách công thức khi mở app lần đầu")
    public void RecipeDisplay_TC_01() {

            authFlow.loginFromFridgeTab(phoneOrEmail, password);
            Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "❌ FAILED: Không đăng nhập được!");
            AllureHelper.attachScreenshot("✅ Login Success");

            Assert.assertTrue(homeScreen.isHomeDisplayed(), "❌ FAIL: Home screen không hiển thị!");
            AllureHelper.attachScreenshot("Step 1 - Home screen displayed");

            Assert.assertTrue(homeScreen.isRecipeListDisplayed(), "❌ FAIL: Recipe list không hiển thị!");
            AllureHelper.attachScreenshot("Step 2 - Recipe list displayed");

            Assert.assertTrue(homeScreen.getRecipeCount() > 0, "❌ FAIL: Không tìm thấy công thức nào!");
            AllureHelper.attachScreenshot("Step 3 - Recipe count: ");

            Assert.assertNotNull(homeScreen.getRecipeNameAt(0), "❌ FAIL: Không lấy được tên công thức!");
            AllureHelper.attachScreenshot("Step 4 - First recipe: ");

            homeScreen.scrollDownToLoadMore();
            int recipeCountAfterScroll = homeScreen.getRecipeCount();
            AllureHelper.attachScreenshot("Step 5 - After scroll: " + recipeCountAfterScroll + " recipes");

           logger.info("✅ RecipeDisplay_TC_01 PASSED");

    }

    @Test(priority = 2,
            description = "RecipeDisplay_TC_02 - Kiểm tra hiển thị màn hình chi tiết công thức khi click vào item")
    public void RecipeDisplay_TC_02() {
            authFlow.loginFromFridgeTab(phoneOrEmail, password);
            Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "❌ Login failed!");
            AllureHelper.attachScreenshot("Precondition - Login Success");

            homeScreen.waitForRecipeListLoad();
//          Scroll xuống 5 lần để load thêm recipes
            for (int j = 0; j < 5; j++) {
                homeScreen.scrollDownToLoadMore();
            }

            Assert.assertNotNull(homeScreen.getRecipeNameAt(0), "❌ FAIL: Không lấy được tên công thức!");
            AllureHelper.attachScreenshot("Step 2 - Recipe name: ");

            homeScreen.clickRecipeByName(homeScreen.getRecipeNameAt(0));
            AllureHelper.attachScreenshot("Step 4 - Clicked recipe: ");

            Assert.assertTrue(recipeDetailScreen.isRecipeDetailDisplayed(), "❌ FAIL: Chi tiết công thức không hiển thị!");
            AllureHelper.attachScreenshot("Step 5  - Recipe detail displayed");

            Assert.assertNotNull(recipeDetailScreen.getRecipeTitle(), "❌ FAIL: Không lấy được tên công thức!");

            Assert.assertTrue(recipeDetailScreen.isTabsDisplayed(), "❌ FAIL: Tabs không hiển thị!");
            AllureHelper.attachScreenshot("Step 7 - Tabs displayed");

            recipeDetailScreen.clickInstructionTab();
            AllureHelper.attachScreenshot("Step 8 - Instruction tab clicked");

            Assert.assertTrue(recipeDetailScreen.getInstructionCount() > 0, "❌ FAIL: Không có các bước nấu nào!");
            AllureHelper.attachScreenshot("Step 9 - Instructions: " + recipeDetailScreen.getInstructionCount() + " steps");


            logger.info("RecipeDisplay_TC_02 PASSED");

    }

    @Test(priority = 3,
            description = "RecipeDisplay_TC_03 - Kiểm tra chuyển qua công thức khác, dữ liệu cập nhật chính xác")
    public void RecipeDisplay_TC_03() {

        authFlow.loginFromFridgeTab(phoneOrEmail, password);
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "❌ Login failed!");
        homeScreen.waitForRecipeListLoad();

        String recipeAName = homeScreen.getRecipeNameAt(0);
        homeScreen.clickRecipeByName(recipeAName);
        AllureHelper.attachScreenshot("Step 2 - Recipe A clicked");

        String recipeATitle = recipeDetailScreen.getRecipeTitle();
        String recipeAAuthor = recipeDetailScreen.getRecipeAuthor();

        AllureHelper.attachScreenshot("Step 3 - Recipe A info");

        recipeDetailScreen.clickBackButton();
        homeScreen.waitForRecipeListLoad();

        String recipeBName = homeScreen.getRecipeNameAt(1);
        homeScreen.clickRecipeByName(recipeBName);

        AllureHelper.attachScreenshot("Step 5 - Recipe B clicked");

        String recipeBTitle = recipeDetailScreen.getRecipeTitle();
        String recipeBAuthor = recipeDetailScreen.getRecipeAuthor();

        AllureHelper.attachScreenshot("Step 6 - Recipe B info");
        Assert.assertNotEquals(recipeATitle, recipeBTitle,
                "❌ FAIL: Dữ liệu công thức phải khác nhau!");

    }
    @Test(priority = 4,
            description = "RecipeDisplay_TC_04 - Kiểm tra quay lại danh sách, vị trí scroll giữ nguyên")
    public void RecipeDisplay_TC_04() {

            authFlow.loginFromFridgeTab(phoneOrEmail, password);
            Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "❌ Login failed!");

            homeScreen.waitForRecipeListLoad();

            for (int j = 0; j < 5; j++) {
                homeScreen.scrollDownToLoadMore();
                WaitingHelper.sleepSeconds(1);
            }

            int scrollBefore = homeScreen.getScrollPosition();

            homeScreen.scrollDownToLoadMore();
            WaitingHelper.sleepSeconds(1);
            int scrollAfter = homeScreen.getScrollPosition();
            AllureHelper.attachScreenshot("Step 4 - After scroll: position " + scrollAfter);

            int recipeCount = homeScreen.getRecipeCount();
            int safeIndex = Math.min(1, recipeCount - 1);  // Chọn index an toàn
            if (safeIndex < 0) {
                safeIndex = 0;
            }
            String recipeName = homeScreen.getRecipeNameAt(safeIndex);
            Assert.assertNotNull(recipeName, "❌ FAIL: Không lấy được tên công thức!");

            homeScreen.clickRecipeByName(recipeName);
            WaitingHelper.sleepSeconds(2);
            AllureHelper.attachScreenshot("Step 6 - Clicked recipe");


            recipeDetailScreen.clickBackButton();

            AllureHelper.attachScreenshot("Step 7 - Back to list");

            int scrollFinal = homeScreen.getScrollPosition();

            // So sánh vị trí scroll (cho phép sai số nhỏ)
            int tolerance = 50; // pixels
            boolean isScrollPreserved = Math.abs(scrollFinal - scrollAfter) <= tolerance;
            Assert.assertTrue(isScrollPreserved,
                    "❌ FAIL: Vị trí scroll không được giữ! Before: " + scrollAfter + ", After: " + scrollFinal);
            logger.info("   ✅ Vị trí scroll được giữ nguyên");
            logger.info("RecipeDisplay_TC_04 PASSED");
    }
    @Test(priority = 5,
            description = "RecipeDisplay_TC_05 - Kiểm tra mở liên tiếp nhiều công thức không bị lag/crash")
    public void RecipeDisplay_TC_05() {

        logger.info("\n📝 PRECONDITION: Đăng nhập");
        authFlow.loginFromFridgeTab(phoneOrEmail, password);
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "❌ Login failed!");

        homeScreen.waitForRecipeListLoad();

        int recipeCount = homeScreen.getRecipeCount();
        int iterations = Math.min(5, recipeCount);  // Không vượt quá số recipes
        long startTime = System.currentTimeMillis();
        long minTime = Long.MAX_VALUE;
        long maxTime = 0;

        for (int i = 0; i < iterations; i++) {
            logger.info("\n📍 ITERATION " + (i + 1) + "/" + iterations);

                long iterationStart = System.currentTimeMillis();

                int recipeIndex = i % recipeCount;
                String recipeName = homeScreen.getRecipeNameAt(recipeIndex);
                logger.info("   Step 1: Click công thức (index " + recipeIndex + "): " + recipeName);
                homeScreen.clickRecipeByName(recipeName);

                logger.info("   Step 2: Verify detail hiển thị");
                Assert.assertTrue(recipeDetailScreen.isRecipeDetailDisplayed(), "❌ FAIL: Chi tiết không hiển thị!");

                // Quay lại
                logger.info("   Step 3: Quay lại danh sách");
                recipeDetailScreen.clickBackButton();
                homeScreen.waitForRecipeListLoad();

                long iterationEnd = System.currentTimeMillis();
                long iterationTime = iterationEnd - iterationStart;
                minTime = Math.min(minTime, iterationTime);
                maxTime = Math.max(maxTime, iterationTime);

                AllureHelper.attachScreenshot("Iteration " + (i + 1) + " completed in " + iterationTime + "ms");

        }

        long totalDuration = System.currentTimeMillis() - startTime;
        double avgTime = (double) totalDuration / iterations;
        logger.info("║ ✅ RecipeDisplay_TC_05 PASSED");

    }
}