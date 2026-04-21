package smoke;

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
//
////    // ==================== RecipeDisplay_TC_01 ====================
//
//    @Test(priority = 1,
//            description = "RecipeDisplay_TC_01 - Kiểm tra hiển thị danh sách công thức khi mở app lần đầu")
//    @Story("Happy Case - Hiển thị danh sách công thức")
//    @Description("Sau khi đăng nhập thành công, người dùng sẽ thấy danh sách công thức nấu ăn trên màn hình chính (Home)")
//    @Severity(SeverityLevel.CRITICAL)
//    public void RecipeDisplay_TC_01_DisplayListOnFirstOpen() {
//        logger.info("\n╔════════════════════════════════════════════════════════════╗");
//        logger.info("║ RecipeDisplay_TC_01                                        ║");
//        logger.info("║ Kiểm tra hiển thị danh sách công thức khi mở app lần đầu  ║");
//        logger.info("╚════════════════════════════════════════════════════════════╝");
//
//        try {
//            // ================================================================
//            // 🔵 PRECONDITION - LOGIN
//            // ================================================================
//            logger.info("\n┌─── 🔵 PRECONDITION: LOGIN ────────────────────────────┐");
//            logger.info("│ Truy cập ứng dụng ChefMate và đăng nhập                │");
//            logger.info("│    Login: " + phoneOrEmail);
//            logger.info("│    Mật khẩu: ••••••                                    │");
//            logger.info("└────────────────────────────────────────────────────────┘");
//
//            logger.info("\n📍 Step 1: Đăng nhập via Tủ lạnh tab");
//            authFlow.loginFromFridgeTab(phoneOrEmail, password);
//            WaitingHelper.sleepSeconds(2);
//
//            boolean isLoginSuccess = authFlow.isLoggedInSuccessfully();
//            Assert.assertTrue(isLoginSuccess, "❌ FAILED: Không đăng nhập được!");
//            logger.info("✅ Đăng nhập thành công");
//            AllureHelper.attachScreenshot("✅ Login Success");
//
//            // ================================================================
//            // 📝 STEPS - Kiểm tra danh sách công thức hiển thị
//            // ================================================================
//            logger.info("\n┌─── 📝 STEPS: KIỂM TRA DANH SÁCH CÔNG THỨC ────────────┐");
//
//            // ✅ STEP 1: Verify Home screen hiển thị
//            logger.info("│ Step 1: Verify Home screen hiển thị                   │");
//            logger.info("└────────────────────────────────────────────────────────┘");
//            logger.info("\n📍 Step 1: Verify Home screen hiển thị");
//            boolean isHomeDisplayed = homeScreen.isHomeDisplayed();
//            Assert.assertTrue(isHomeDisplayed, "❌ FAIL: Home screen không hiển thị!");
//            logger.info("   ✅ Home screen hiển thị thành công");
//            AllureHelper.attachScreenshot("Step 1 - Home screen displayed");
//
//            // ✅ STEP 2: Verify danh sách công thức hiển thị
//            logger.info("\n📍 Step 2: Verify danh sách công thức hiển thị");
//            boolean isRecipeListDisplayed = homeScreen.isRecipeListDisplayed();
//            Assert.assertTrue(isRecipeListDisplayed, "❌ FAIL: Recipe list không hiển thị!");
//            logger.info("   ✅ Danh sách công thức hiển thị thành công");
//            AllureHelper.attachScreenshot("Step 2 - Recipe list displayed");
//
//            // ✅ STEP 3: Lấy số lượng công thức
//            logger.info("\n📍 Step 3: Lấy số lượng công thức");
//            int recipeCount = homeScreen.getRecipeCount();
//            Assert.assertTrue(recipeCount > 0, "❌ FAIL: Không tìm thấy công thức nào!");
//            logger.info("   ✅ Tìm thấy " + recipeCount + " công thức");
//            AllureHelper.attachScreenshot("Step 3 - Recipe count: " + recipeCount);
//
//            // ✅ STEP 4: Verify danh sách không trống - lấy tên công thức đầu tiên
//            logger.info("\n📍 Step 4: Verify danh sách không trống");
//            String firstRecipeName = homeScreen.getRecipeNameAt(0);
//            Assert.assertNotNull(firstRecipeName, "❌ FAIL: Không lấy được tên công thức!");
//            logger.info("   ✅ Công thức đầu tiên: " + firstRecipeName);
//            AllureHelper.attachScreenshot("Step 4 - First recipe: " + firstRecipeName);
//
//            // ✅ STEP 5: Scroll xuống để kiểm tra thêm công thức
//            logger.info("\n📍 Step 5: Scroll xuống để kiểm tra danh sách công thức");
//            homeScreen.scrollDownToLoadMore();
//            WaitingHelper.sleepSeconds(1);
//            int recipeCountAfterScroll = homeScreen.getRecipeCount();
//            logger.info("   ✅ Sau scroll: " + recipeCountAfterScroll + " công thức");
//            AllureHelper.attachScreenshot("Step 5 - After scroll: " + recipeCountAfterScroll + " recipes");
//
//            // ================================================================
//            // ✅ EXPECTED RESULT
//            // ================================================================
//            logger.info("\n┌─── ✅ EXPECTED RESULT ────────────────────────────────┐");
//            logger.info("│ ✅ Danh sách công thức hiển thị với ít nhất 1 item     │");
//            logger.info("│   - Ban đầu: " + String.format("%-43s│", recipeCount + " công thức"));
//            logger.info("│   - Sau scroll: " + String.format("%-40s│", recipeCountAfterScroll + " công thức"));
//            logger.info("│   - Công thức đầu tiên: " + String.format("%-34s│", firstRecipeName));
//            logger.info("│   - Scroll hoạt động bình thường                      │");
//            logger.info("│   - Ứng dụng không crash                              │");
//            logger.info("│                                                        │");
//            logger.info("│ ✅ TEST PASSED - Đáp ứng tất cả yêu cầu               │");
//            logger.info("└────────────────────────────────────────────────────────┘");
//
//            logger.info("\n╔════════════════════════════════════════════════════════════╗");
//            logger.info("║ ✅ RecipeDisplay_TC_01 PASSED                             ║");
//            logger.info("╚════════════════════════════════════════════════════════════╝\n");
//
//        } catch (AssertionError ae) {
//            logger.error("❌ RecipeDisplay_TC_01 FAILED - Assertion: " + ae.getMessage());
//            AllureHelper.attachScreenshot("❌ RecipeDisplay_TC_01 - ASSERTION FAILED");
//            throw ae;
//        } catch (Exception e) {
//            logger.error("❌ RecipeDisplay_TC_01 FAILED - Exception: " + e.getMessage());
//            AllureHelper.attachErrorMessage("Error: " + e.getMessage());
//            AllureHelper.attachScreenshot("❌ RecipeDisplay_TC_01 - ERROR");
//            throw e;
//        }
//    }
//
//    // ==================== RecipeDisplay_TC_02 ====================
//
//    @Test(priority = 2,
//            description = "RecipeDisplay_TC_02 - Kiểm tra hiển thị màn hình chi tiết công thức khi click vào item")
//    @Story("Happy Case - Chi tiết công thức")
//    @Description("Khi click vào 1 công thức, phải hiển thị chi tiết công thức với đầy đủ thông tin")
//    @Severity(SeverityLevel.CRITICAL)
//    public void RecipeDisplay_TC_02_ShowDetailWhenClickRecipe() {
//        logger.info("\n╔════════════════════════════════════════════════════════════╗");
//        logger.info("║ RecipeDisplay_TC_02                                        ║");
//        logger.info("║ Kiểm tra hiển thị chi tiết công thức khi click vào item   ║");
//        logger.info("╚════════════════════════════════════════════════════════════╝");
//
//        try {
//            // ✅ LOGIN
//            logger.info("\n📝 PRECONDITION: Đăng nhập");
//            authFlow.loginFromFridgeTab(phoneOrEmail, password);
//            WaitingHelper.sleepSeconds(2);
//            Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "❌ Login failed!");
//            logger.info("✅ Đăng nhập thành công");
//            AllureHelper.attachScreenshot("Precondition - Login Success");
//
//            // ✅ STEP 1: Chờ danh sách công thức load
//            logger.info("\n📍 Step 1: Chờ danh sách công thức load");
//            homeScreen.waitForRecipeListLoad();
//            logger.info("   ✅ Danh sách công thức đã load");
//
//            // ✅ STEP 2: Scroll xuống nhiều lần
//            logger.info("\n📍 Step 2: Scroll xuống 5 lần để load thêm recipes");
//            for (int j = 0; j < 5; j++) {
//                logger.info("   - Scroll lần " + (j + 1));
//                homeScreen.scrollDownToLoadMore();
//                WaitingHelper.sleepSeconds(1);
//            }
//            logger.info("   ✅ Scroll xong");
//
//            // ✅ STEP 3: Lấy tên công thức đầu tiên
//            logger.info("\n📍 Step 3: Lấy tên công thức đầu tiên");
//            String firstRecipeName = homeScreen.getRecipeNameAt(0);
//            Assert.assertNotNull(firstRecipeName, "❌ FAIL: Không lấy được tên công thức!");
//            logger.info("   ✅ Công thức: " + firstRecipeName);
//            AllureHelper.attachScreenshot("Step 2 - Recipe name: " + firstRecipeName);
//
//            // ✅ STEP 4: Click vào công thức
//            logger.info("\n📍 Step 4: Click vào công thức: " + firstRecipeName);
//            homeScreen.clickRecipeByName(firstRecipeName);
//            WaitingHelper.sleepSeconds(2);
//            logger.info("   ✅ ��ã click vào công thức");
//            AllureHelper.attachScreenshot("Step 4 - Clicked recipe: " + firstRecipeName);
//
//            // ✅ STEP 5: Verify detail screen hiển thị
//            logger.info("\n📍 Step 5: Verify detail screen hiển thị");
//            boolean isDetailDisplayed = recipeDetailScreen.isRecipeDetailDisplayed();
//            Assert.assertTrue(isDetailDisplayed, "❌ FAIL: Chi tiết công thức không hiển thị!");
//            logger.info("   ✅ Chi tiết công thức hiển thị");
//            AllureHelper.attachScreenshot("Step 5  - Recipe detail displayed");
//
//            // ✅ STEP 6: Verify tên công thức chính xác
//            logger.info("\n📍 Step 6: Verify tên công thức chính xác");
//            String detailTitle = recipeDetailScreen.getRecipeTitle();
//            Assert.assertNotNull(detailTitle, "❌ FAIL: Không lấy được tên công thức!");
//            logger.info("   ✅ Tên công thức: " + detailTitle);
//
//            // ✅ STEP 7: Verify tabs hiển thị
//            logger.info("\n📍 Step 7: Verify tabs (Nguyên liệu, Cách thực hiện) hiển thị");
//            boolean isTabsDisplayed = recipeDetailScreen.isTabsDisplayed();
//            Assert.assertTrue(isTabsDisplayed, "❌ FAIL: Tabs không hiển thị!");
//            logger.info("   ✅ Tabs hiển thị");
//            AllureHelper.attachScreenshot("Step 7 - Tabs displayed");
//
//            // ✅ STEP 8: Click tab "Cách thực hiện"
//            logger.info("\n📍 Step 8: Click tab \"Cách thực hiện\"");
//            recipeDetailScreen.clickInstructionTab();
//            WaitingHelper.sleepSeconds(1);
//            logger.info("   ✅ Đã click tab \"Cách thực hiện\"");
//            AllureHelper.attachScreenshot("Step 8 - Instruction tab clicked");
//
//            // ✅ STEP 9: Verify content hiển thị đầy đủ
//            logger.info("\n📍 Step 9: Verify nội dung tab \"Cách thực hiện\" hiển thị đầy đủ");
//            int stepCount = recipeDetailScreen.getInstructionCount();
//            Assert.assertTrue(stepCount > 0, "❌ FAIL: Không có các bước nấu nào!");
//            logger.info("   ✅ Số bước nấu: " + stepCount);
//            AllureHelper.attachScreenshot("Step 9 - Instructions: " + stepCount + " steps");
//
//            // ================================================================
//            // ✅ EXPECTED RESULT
//            // ================================================================
//            logger.info("\n┌─── ✅ EXPECTED RESULT ────────────────────────────────┐");
//            logger.info("│ ✅ Chi tiết công thức hiển thị đầy đủ                 │");
//            logger.info("│   - Tên công thức: " + String.format("%-37s│", detailTitle));
//            logger.info("│   - Số bước nấu: " + String.format("%-41s│", stepCount));
//            logger.info("│   - Tabs hoạt động bình thường                        │");
//            logger.info("│   - Không crash hoặc lỗi                              │");
//            logger.info("│                                                        │");
//            logger.info("│ ✅ TEST PASSED                                         │");
//            logger.info("└────────────────────────────────────────────────────────┘");
//
//            logger.info("\n╔════════════════════════════════════════════════════════════╗");
//            logger.info("║ ✅ RecipeDisplay_TC_02 PASSED                             ║");
//            logger.info("╚════════════════════════════════════════════════════════════╝\n");
//
//        } catch (AssertionError ae) {
//            logger.error("❌ RecipeDisplay_TC_02 FAILED - Assertion: " + ae.getMessage());
//            AllureHelper.attachScreenshot("❌ RecipeDisplay_TC_02 - ASSERTION FAILED");
//            throw ae;
//        } catch (Exception e) {
//            logger.error("❌ RecipeDisplay_TC_02 FAILED - Exception: " + e.getMessage());
//            AllureHelper.attachErrorMessage("Error: " + e.getMessage());
//            AllureHelper.attachScreenshot("❌ RecipeDisplay_TC_02 - ERROR");
//            throw e;
//        }
//    }
//
//    // ==================== RecipeDisplay_TC_03 ====================
//
//    @Test(priority = 3,
//            description = "RecipeDisplay_TC_03 - Kiểm tra chuyển qua công thức khác, dữ liệu cập nhật chính xác")
//    @Story("Happy Case - Chuyển công thức")
//    @Description("Khi chuyển từ công thức A sang công thức B, dữ liệu phải cập nhật chính xác")
//    public void RecipeDisplay_TC_03_SwitchRecipeUpdateData() {
//        logger.info("\n╔════════════════════════════════════════════════════════════╗");
//        logger.info("║ RecipeDisplay_TC_03                                        ║");
//        logger.info("║ Kiểm tra chuyển qua công thức khác, dữ liệu cập nhật      ║");
//        logger.info("╚════════════════════════════════════════════════════════════╝");
//
//        try {
//            // ✅ LOGIN
//            logger.info("\n📝 PRECONDITION: Đăng nhập");
//            authFlow.loginFromFridgeTab(phoneOrEmail, password);
//            WaitingHelper.sleepSeconds(2);
//            Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "❌ Login failed!");
//            logger.info("✅ Đăng nhập thành công");
//
//            // ✅ STEP 1: Chờ danh sách load
//            logger.info("\n📍 Step 1: Chờ danh sách công thức load");
//            homeScreen.waitForRecipeListLoad();
//
//            // ✅ STEP 2: Scroll xuống nhiều lần
//            logger.info("\n📍 Step 2: Scroll xuống 5 lần để load thêm recipes");
//            for (int j = 0; j < 5; j++) {
//                logger.info("   - Scroll lần " + (j + 1));
//                homeScreen.scrollDownToLoadMore();
//                WaitingHelper.sleepSeconds(1);
//            }
//            logger.info("   ✅ Scroll xong");
//
//            // ✅ STEP 3: Click công thức A
//            logger.info("\n📍 Step 3: Click công thức A");
//            String recipeAName = homeScreen.getRecipeNameAt(0);
//            homeScreen.clickRecipeByName(recipeAName);
//            WaitingHelper.sleepSeconds(2);
//            logger.info("   ✅ Công thức A: " + recipeAName);
//            AllureHelper.attachScreenshot("Step 2 - Recipe A clicked");
//
//            // ✅ STEP 4: Lấy dữ liệu công thức A
//            logger.info("\n📍 Step 4: Ghi nhận thông tin công thức A");
//            String recipeATitle = recipeDetailScreen.getRecipeTitle();
//            String recipeAAuthor = recipeDetailScreen.getRecipeAuthor();
//            logger.info("   ✅ Title A: " + recipeATitle);
//            logger.info("   ✅ Author A: " + recipeAAuthor);
//            AllureHelper.attachScreenshot("Step 4 - Recipe A info");
//
//            // ✅ STEP 5: Quay lại danh sách
//            logger.info("\n📍 Step 5: Quay lại danh sách");
//            recipeDetailScreen.clickBackButton();
//            WaitingHelper.sleepSeconds(2);
//
//            // ✅ THÊM: Scroll lên đầu danh sách để tìm "Top thịnh hành"
//            logger.info("   📍 Scroll lên đầu danh sách...");
//            for (int k = 0; k < 5; k++) {
//                homeScreen.scrollDownToLoadMore();  // Thực chất là cuộn về trên
//                WaitingHelper.sleepSeconds(1);
//            }
//
//            homeScreen.waitForRecipeListLoad();
//            logger.info("   ✅ Đã quay lại danh sách");
//
//            // ✅ STEP 6: Click công thức B
//            logger.info("\n📍 Step 6: Click công thức B");
//            String recipeBName = homeScreen.getRecipeNameAt(1);
//            homeScreen.clickRecipeByName(recipeBName);
//            WaitingHelper.sleepSeconds(2);
//            logger.info("   ✅ Công thức B: " + recipeBName);
//            AllureHelper.attachScreenshot("Step 6 - Recipe B clicked");
//
//            // ✅ STEP 7: Lấy dữ liệu công thức B
//            logger.info("\n📍 Step 7: Ghi nhận thông tin công thức B");
//            String recipeBTitle = recipeDetailScreen.getRecipeTitle();
//            String recipeBAuthor = recipeDetailScreen.getRecipeAuthor();
//            logger.info("   ✅ Title B: " + recipeBTitle);
//            logger.info("   ✅ Author B: " + recipeBAuthor);
//            AllureHelper.attachScreenshot("Step 7 - Recipe B info");
//
//            // ✅ STEP 8: Verify dữ liệu khác nhau
//            logger.info("\n📍 Step 8: Verify dữ liệu công thức A ≠ B");
//            Assert.assertNotEquals(recipeATitle, recipeBTitle,
//                    "❌ FAIL: Dữ liệu công thức phải khác nhau!");
//            logger.info("   ✅ Dữ liệu công thức A ≠ công thức B");
//            logger.info("   ✅ Không hiển thị dữ liệu công thức A");
//
//            // ================================================================
//            // ✅ EXPECTED RESULT
//            // ================================================================
//            logger.info("\n┌─── ✅ EXPECTED RESULT ────────────────────────────────┐");
//            logger.info("│ ✅ Dữ liệu cập nhật chính xác khi chuyển công thức     │");
//            logger.info("│   - Công thức A: " + String.format("%-41s│", recipeATitle));
//            logger.info("│   - Công thức B: " + String.format("%-41s│", recipeBTitle));
//            logger.info("│   - Dữ liệu được cập nhật tương ứng                   │");
//            logger.info("│   - Ứng dụng không crash                              │");
//            logger.info("│                                                        │");
//            logger.info("│ ✅ TEST PASSED                                         │");
//            logger.info("└────────────────────────────────────────────────────────┘");
//
//            logger.info("\n╔════════════════════════════════════════════════════════════╗");
//            logger.info("║ ✅ RecipeDisplay_TC_03 PASSED                             ║");
//            logger.info("╚════════════════════════════════════════════════════════════╝\n");
//
//        } catch (AssertionError ae) {
//            logger.error("❌ RecipeDisplay_TC_03 FAILED - Assertion: " + ae.getMessage());
//            AllureHelper.attachScreenshot("❌ RecipeDisplay_TC_03 - ASSERTION FAILED");
//            throw ae;
//        } catch (Exception e) {
//            logger.error("❌ RecipeDisplay_TC_03 FAILED - Exception: " + e.getMessage());
//            AllureHelper.attachErrorMessage("Error: " + e.getMessage());
//            AllureHelper.attachScreenshot("❌ RecipeDisplay_TC_03 - ERROR");
//            throw e;
//        }
//    }
// ==================== RecipeDisplay_TC_03 ====================

@Test(priority = 3,
        description = "RecipeDisplay_TC_03 - Kiểm tra chuyển qua công thức khác, dữ liệu cập nhật chính xác")
@Story("Happy Case - Chuyển công thức")
@Description("Khi chuyển từ công thức A sang công thức B, dữ liệu phải cập nhật chính xác")
public void RecipeDisplay_TC_03_SwitchRecipeUpdateData() {
    logger.info("\n╔════════════════════════════════════════════════════════════╗");
    logger.info("║ RecipeDisplay_TC_03                                        ║");
    logger.info("║ Kiểm tra chuyển qua công thức khác, dữ liệu cập nhật      ║");
    logger.info("╚════════════════════════════════════════════════════════════╝");

    try {
        // ✅ LOGIN
        logger.info("\n📝 PRECONDITION: Đăng nhập");
        authFlow.loginFromFridgeTab(phoneOrEmail, password);
        WaitingHelper.sleepSeconds(2);
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "❌ Login failed!");
        logger.info("✅ Đăng nhập thành công");

        // ✅ STEP 1: Chờ danh sách load
        logger.info("\n📍 Step 1: Chờ danh sách công thức load");
        homeScreen.waitForRecipeListLoad();

        // ✅ STEP 2: Click công thức A
        logger.info("\n📍 Step 2: Click công thức A");
        String recipeAName = homeScreen.getRecipeNameAt(0);
        homeScreen.clickRecipeByName(recipeAName);
        WaitingHelper.sleepSeconds(2);
        logger.info("   ✅ Công thức A: " + recipeAName);
        AllureHelper.attachScreenshot("Step 2 - Recipe A clicked");

        // ✅ STEP 3: Lấy dữ liệu công thức A
        logger.info("\n📍 Step 3: Ghi nhận thông tin công thức A");
        String recipeATitle = recipeDetailScreen.getRecipeTitle();
        String recipeAAuthor = recipeDetailScreen.getRecipeAuthor();
        logger.info("   ✅ Title A: " + recipeATitle);
        logger.info("   ✅ Author A: " + recipeAAuthor);
        AllureHelper.attachScreenshot("Step 3 - Recipe A info");

        // ✅ STEP 4: Quay lại danh sách
        logger.info("\n📍 Step 4: Quay lại danh sách");
        recipeDetailScreen.clickBackButton();
        WaitingHelper.sleepSeconds(2);
        homeScreen.waitForRecipeListLoad();
        logger.info("   ✅ Đã quay lại danh sách");

        // ✅ STEP 5: Click công thức B
        logger.info("\n📍 Step 5: Click công thức B");
        String recipeBName = homeScreen.getRecipeNameAt(1);
        homeScreen.clickRecipeByName(recipeBName);
        WaitingHelper.sleepSeconds(2);
        logger.info("   ✅ Công thức B: " + recipeBName);
        AllureHelper.attachScreenshot("Step 5 - Recipe B clicked");

        // ✅ STEP 6: Lấy dữ liệu công thức B
        logger.info("\n📍 Step 6: Ghi nhận thông tin công thức B");
        String recipeBTitle = recipeDetailScreen.getRecipeTitle();
        String recipeBAuthor = recipeDetailScreen.getRecipeAuthor();
        logger.info("   ✅ Title B: " + recipeBTitle);
        logger.info("   ✅ Author B: " + recipeBAuthor);
        AllureHelper.attachScreenshot("Step 6 - Recipe B info");

        // ✅ STEP 7: Verify dữ liệu khác nhau
        logger.info("\n📍 Step 7: Verify dữ liệu công thức A ≠ B");
        Assert.assertNotEquals(recipeATitle, recipeBTitle,
                "❌ FAIL: Dữ liệu công thức phải khác nhau!");
        logger.info("   ✅ Dữ liệu công thức A ≠ công thức B");
        logger.info("   ✅ Không hiển thị dữ liệu công thức A");

        // ================================================================
        // ✅ EXPECTED RESULT
        // ================================================================
        logger.info("\n┌─── ✅ EXPECTED RESULT ────────────────────────────────┐");
        logger.info("│ ✅ Dữ liệu cập nhật chính xác khi chuyển công thức     │");
        logger.info("│   - Công thức A: " + String.format("%-41s│", recipeATitle));
        logger.info("│   - Công thức B: " + String.format("%-41s│", recipeBTitle));
        logger.info("│   - Dữ liệu được cập nhật tương ứng                   │");
        logger.info("│   - Ứng dụng không crash                              │");
        logger.info("│                                                        │");
        logger.info("│ ✅ TEST PASSED                                         │");
        logger.info("└────────────────────────────────────────────────────────┘");

        logger.info("\n╔════════════════════════════════════════════════════════════╗");
        logger.info("║ ✅ RecipeDisplay_TC_03 PASSED                             ║");
        logger.info("╚════════════════════════════════════════════════════════════╝\n");

    } catch (AssertionError ae) {
        logger.error("❌ RecipeDisplay_TC_03 FAILED - Assertion: " + ae.getMessage());
        AllureHelper.attachScreenshot("❌ RecipeDisplay_TC_03 - ASSERTION FAILED");
        throw ae;
    } catch (Exception e) {
        logger.error("❌ RecipeDisplay_TC_03 FAILED - Exception: " + e.getMessage());
        AllureHelper.attachErrorMessage("Error: " + e.getMessage());
        AllureHelper.attachScreenshot("❌ RecipeDisplay_TC_03 - ERROR");
        throw e;
    }
}
//    // ==================== RecipeDisplay_TC_04 ====================
//
//    @Test(priority = 4,
//            description = "RecipeDisplay_TC_04 - Kiểm tra quay lại danh sách, vị trí scroll giữ nguyên")
//    @Story("Happy Case - Scroll position preservation")
//    @Description("Khi quay lại từ chi tiết công thức, vị trí scroll phải được giữ nguyên")
//    public void RecipeDisplay_TC_04_BackPreserveScrollPosition() {
//        logger.info("\n╔════════════════════════════════════════════════════════════╗");
//        logger.info("║ RecipeDisplay_TC_04                                        ║");
//        logger.info("║ Kiểm tra quay lại danh sách, vị trí scroll giữ nguyên     ║");
//        logger.info("╚════════════════════════════════════════════════════════════╝");
//
//        try {
//            // ✅ LOGIN
//            logger.info("\n📝 PRECONDITION: Đăng nhập");
//            authFlow.loginFromFridgeTab(phoneOrEmail, password);
//            WaitingHelper.sleepSeconds(2);
//            Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "❌ Login failed!");
//            logger.info("✅ Đăng nhập thành công");
//
//            // ✅ STEP 1: Chờ danh sách load
//            logger.info("\n📍 Step 1: Chờ danh sách công thức load");
//            homeScreen.waitForRecipeListLoad();
//
//            // ✅ STEP 2: Scroll xuống nhiều lần
//            logger.info("\n📍 Step 2: Scroll xuống 5 lần để load thêm recipes");
//            for (int j = 0; j < 5; j++) {
//                logger.info("   - Scroll lần " + (j + 1));
//                homeScreen.scrollDownToLoadMore();
//                WaitingHelper.sleepSeconds(1);
//            }
//            logger.info("   ✅ Scroll xong");
//
//            // ✅ STEP 3: Ghi nhận vị trí scroll trước click
//            logger.info("\n📍 Step 3: Ghi nhận vị trí scroll trước click");
//            int scrollBefore = homeScreen.getScrollPosition();
//            logger.info("   ✅ Vị trí scroll ban đầu: " + scrollBefore);
//
//            // ✅ STEP 4: Scroll xuống
//            logger.info("\n📍 Step 4: Scroll xuống danh sách");
//            homeScreen.scrollDownToLoadMore();
//            WaitingHelper.sleepSeconds(1);
//            int scrollAfter = homeScreen.getScrollPosition();
//            logger.info("   ✅ Vị trí scroll sau scroll: " + scrollAfter);
//            AllureHelper.attachScreenshot("Step 4 - After scroll: position " + scrollAfter);
//
//            // ✅ STEP 5: Lấy tên công thức ở vị trí hiện tại
//            logger.info("\n📍 Step 5: Lấy tên công thức ở vị trí hiện tại");
//            int recipeCount = homeScreen.getRecipeCount();
//            int safeIndex = Math.min(1, recipeCount - 1);  // Chọn index an toàn
//            if (safeIndex < 0) {
//                logger.warn("⚠️ Không đủ recipe để test scroll position, skip step này");
//                safeIndex = 0;
//            }
//            String recipeName = homeScreen.getRecipeNameAt(safeIndex);
//            Assert.assertNotNull(recipeName, "❌ FAIL: Không lấy được tên công thức!");
//            logger.info("   ✅ Công thức: " + recipeName);
//
//            // ✅ STEP 6: Click công thức
//            logger.info("\n📍 Step 6: Click vào công thức");
//            homeScreen.clickRecipeByName(recipeName);
//            WaitingHelper.sleepSeconds(2);
//            logger.info("   ✅ Đã click vào công thức");
//            AllureHelper.attachScreenshot("Step 6 - Clicked recipe");
//
//            // ✅ STEP 7: Quay lại danh sách
//            logger.info("\n📍 Step 7: Quay lại danh sách");
//            recipeDetailScreen.clickBackButton();
//            WaitingHelper.sleepSeconds(2);
//            logger.info("   ✅ Đã quay lại danh sách");
//            AllureHelper.attachScreenshot("Step 7 - Back to list");
//
//            // ✅ STEP 8: Verify vị trí scroll được giữ
//            logger.info("\n📍 Step 8: Verify vị trí scroll được giữ nguyên");
//            int scrollFinal = homeScreen.getScrollPosition();
//            logger.info("   ✅ Vị trí scroll cuối cùng: " + scrollFinal);
//
//            // So sánh vị trí scroll (cho phép sai số nhỏ)
//            int tolerance = 50; // pixels
//            boolean isScrollPreserved = Math.abs(scrollFinal - scrollAfter) <= tolerance;
//            Assert.assertTrue(isScrollPreserved,
//                    "❌ FAIL: Vị trí scroll không được giữ! Before: " + scrollAfter + ", After: " + scrollFinal);
//            logger.info("   ✅ Vị trí scroll được giữ nguyên");
//
//            // ================================================================
//            // ✅ EXPECTED RESULT
//            // ================================================================
//            logger.info("\n┌─── ✅ EXPECTED RESULT ────────────────────────────────┐");
//            logger.info("│ ✅ Vị trí scroll được giữ nguyên khi quay lại          │");
//            logger.info("│   - Vị trí trước quay lại: " + String.format("%-38s│", scrollAfter));
//            logger.info("│   - Vị trí sau quay lại:  " + String.format("%-38s│", scrollFinal));
//            logger.info("│   - Chênh lệch: " + String.format("%-46s│", Math.abs(scrollFinal - scrollAfter) + "px"));
//            logger.info("│   - Không bị đưa về đầu danh sách                      │");
//            logger.info("│   - Ứng dụng hoạt động bình thường                     │");
//            logger.info("│                                                        │");
//            logger.info("│ ✅ TEST PASSED                                         │");
//            logger.info("└─────���──────────────────────────────────────────────────┘");
//
//            logger.info("\n╔════════════════════════════════════════════════════════════╗");
//            logger.info("║ ✅ RecipeDisplay_TC_04 PASSED                             ║");
//            logger.info("╚════════════════════════════════════════════════════════════╝\n");
//
//        } catch (AssertionError ae) {
//            logger.error("❌ RecipeDisplay_TC_04 FAILED - Assertion: " + ae.getMessage());
//            AllureHelper.attachScreenshot("❌ RecipeDisplay_TC_04 - ASSERTION FAILED");
//            throw ae;
//        } catch (Exception e) {
//            logger.error("❌ RecipeDisplay_TC_04 FAILED - Exception: " + e.getMessage());
//            AllureHelper.attachErrorMessage("Error: " + e.getMessage());
//            AllureHelper.attachScreenshot("❌ RecipeDisplay_TC_04 - ERROR");
//            throw e;
//        }
//    }

//    // ==================== RecipeDisplay_TC_05 ====================
//
//    @Test(priority = 5,
//            description = "RecipeDisplay_TC_05 - Kiểm tra mở liên tiếp nhiều công thức không bị lag/crash")
//    @Story("Happy Case - Performance")
//    @Description("Ứng dụng phải mượt mà khi mở liên tiếp nhiều công thức")
//    public void RecipeDisplay_TC_05_MultipleOpenNoLagCrash() {
//        logger.info("\n╔════════════════════════════════════════════════════════════╗");
//        logger.info("║ RecipeDisplay_TC_05                                        ║");
//        logger.info("║ Kiểm tra mở liên tiếp nhiều công thức không bị lag/crash  ║");
//        logger.info("╚════════════════════════════════════════════════════════════╝");
//
//        try {
//            // ✅ LOGIN
//            logger.info("\n📝 PRECONDITION: Đăng nhập");
//            authFlow.loginFromFridgeTab(phoneOrEmail, password);
//            WaitingHelper.sleepSeconds(2);
//            Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "❌ Login failed!");
//            logger.info("✅ Đăng nhập thành công");
//
//            // ✅ STEP 1: Chờ danh sách load
//            homeScreen.waitForRecipeListLoad();
//
//            // ✅ STEP 2: Scroll xuống nhiều lần
//            logger.info("\n📍 Step 1: Scroll xuống 5 lần để load thêm recipes");
//            for (int j = 0; j < 5; j++) {
//                logger.info("   - Scroll lần " + (j + 1));
//                homeScreen.scrollDownToLoadMore();
//                WaitingHelper.sleepSeconds(1);
//            }
//            logger.info("   ✅ Scroll xong");
//
//            int recipeCount = homeScreen.getRecipeCount();
//            logger.info("   ✅ Tổng số recipe: " + recipeCount);
//
//            int iterations = Math.min(5, recipeCount);  // Không vượt quá số recipes
//            long startTime = System.currentTimeMillis();
//            long minTime = Long.MAX_VALUE;
//            long maxTime = 0;
//
//            for (int i = 0; i < iterations; i++) {
//                logger.info("\n📍 ITERATION " + (i + 1) + "/" + iterations);
//
//                try {
//                    long iterationStart = System.currentTimeMillis();
//
//                    if (i > 0) {  // Lần đầu đã wait rồi, từ lần 2 trở đi
//                        homeScreen.waitForRecipeListLoad();
//                    }
//
//                    // Chọn công thức - xoay vòng qua các recipes
//                    int recipeIndex = i % recipeCount;
//                    String recipeName = homeScreen.getRecipeNameAt(recipeIndex);
//                    logger.info("   Step 2: Click công thức (index " + recipeIndex + "): " + recipeName);
//                    homeScreen.clickRecipeByName(recipeName);
//                    WaitingHelper.sleepSeconds(2);  // ✅ THÊM DÒNG NÀY
//
//
//                    // Verify detail
//                    logger.info("   Step 3: Verify detail hiển thị");
//                    boolean isDisplayed = recipeDetailScreen.isRecipeDetailDisplayed();
//                    Assert.assertTrue(isDisplayed, "❌ FAIL: Chi tiết không hiển thị!");
//
//                    // Quay lại
//                    logger.info("   Step 4: Quay lại danh sách");
//                    recipeDetailScreen.clickBackButton();
//                    WaitingHelper.sleepSeconds(1);  // ✅ THÊM
//                    // ✅ THÊM: Scroll lên đầu + wait
//                    logger.info("   📍 Scroll lên đầu danh sách...");
//                    for (int k = 0; k < 3; k++) {
//                        homeScreen.scrollDownToLoadMore();
//                        WaitingHelper.sleepSeconds(500);
//                    }
//
//                    try {
//                        homeScreen.waitForRecipeListLoad();
//                    } catch (Exception e) {
//                        logger.warn("⚠️ Không tìm thấy 'Top thịnh hành', continue...");
//                    }
//
//                    long iterationEnd = System.currentTimeMillis();
//                    long iterationTime = iterationEnd - iterationStart;
//                    minTime = Math.min(minTime, iterationTime);
//                    maxTime = Math.max(maxTime, iterationTime);
//
//                    logger.info("   ✅ Iteration " + (i + 1) + " PASS - Time: " + iterationTime + "ms");
//                    AllureHelper.attachScreenshot("Iteration " + (i + 1) + " completed in " + iterationTime + "ms");
//
//                } catch (Exception e) {
//                    logger.error("❌ Iteration " + (i + 1) + " FAILED: " + e.getMessage());
//                    throw e;
//                }
//            }
//
//            long totalDuration = System.currentTimeMillis() - startTime;
//            double avgTime = (double) totalDuration / iterations;
//
//            // ================================================================
//            // ✅ EXPECTED RESULT
//            // ================================================================
//            logger.info("\n┌─── ✅ EXPECTED RESULT ────────────────────────────────┐");
//            logger.info("│ ✅ Ứng dụng xử lý mượt mà khi mở liên tiếp             │");
//            logger.info("│   - Số lần mở: " + String.format("%-43s│", iterations));
//            logger.info("│   - Thời gian tổng: " + String.format("%-39s│", totalDuration + "ms"));
//            logger.info("│   - Thời gian trung bình: " + String.format("%-35s│", (int)avgTime + "ms"));
//            logger.info("│   - Thời gian tối thiểu: " + String.format("%-36s│", minTime + "ms"));
//            logger.info("│   - Thời gian tối đa: " + String.format("%-37s│", maxTime + "ms"));
//            logger.info("│   - Không crash hoặc lag                              │");
//            logger.info("│   - Dữ liệu hiển thị đúng mỗi lần mở                  │");
//            logger.info("│                                                        │");
//            logger.info("│ ✅ TEST PASSED                                         │");
//            logger.info("└────────────────────────────────────────────────────────┘");
//
//            logger.info("\n╔════════════════════════════════════════════════════════════╗");
//            logger.info("║ ✅ RecipeDisplay_TC_05 PASSED                             ║");
//            logger.info("╚════════════════════════════════════════════════════════════╝\n");
//
//        } catch (AssertionError ae) {
//            logger.error("❌ RecipeDisplay_TC_05 FAILED - Assertion: " + ae.getMessage());
//            AllureHelper.attachScreenshot("❌ RecipeDisplay_TC_05 - ASSERTION FAILED");
//            throw ae;
//        } catch (Exception e) {
//            logger.error("❌ RecipeDisplay_TC_05 FAILED - Exception: " + e.getMessage());
//            AllureHelper.attachErrorMessage("Error: " + e.getMessage());
//            AllureHelper.attachScreenshot("❌ RecipeDisplay_TC_05 - ERROR");
//            throw e;
//        }
//    }
    // ==================== RecipeDisplay_TC_05 ====================

    @Test(priority = 5,
            description = "RecipeDisplay_TC_05 - Kiểm tra mở liên tiếp nhiều công thức không bị lag/crash")
    @Story("Happy Case - Performance")
    @Description("Ứng dụng phải mượt mà khi mở liên tiếp nhiều công thức")
    public void RecipeDisplay_TC_05_MultipleOpenNoLagCrash() {
        logger.info("\n╔════════════════════════════════════════════════════════════╗");
        logger.info("║ RecipeDisplay_TC_05                                        ║");
        logger.info("║ Kiểm tra mở liên tiếp nhiều công thức không bị lag/crash  ║");
        logger.info("╚════════════════════════════════════════════════════════════╝");

        try {
            // ✅ LOGIN
            logger.info("\n📝 PRECONDITION: Đăng nhập");
            authFlow.loginFromFridgeTab(phoneOrEmail, password);
            WaitingHelper.sleepSeconds(2);
            Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "❌ Login failed!");
            logger.info("✅ Đăng nhập thành công");

            // ✅ Thay vòng lặp:
            homeScreen.waitForRecipeListLoad();

            int recipeCount = homeScreen.getRecipeCount();
            logger.info("   ✅ Tổng số recipe: " + recipeCount);

            int iterations = Math.min(5, recipeCount);  // Không vượt quá số recipes
            long startTime = System.currentTimeMillis();
            long minTime = Long.MAX_VALUE;
            long maxTime = 0;

            for (int i = 0; i < iterations; i++) {
                logger.info("\n📍 ITERATION " + (i + 1) + "/" + iterations);

                try {
                    long iterationStart = System.currentTimeMillis();

                    // Chọn công thức - xoay vòng qua các recipes
                    int recipeIndex = i % recipeCount;
                    String recipeName = homeScreen.getRecipeNameAt(recipeIndex);
                    logger.info("   Step 1: Click công thức (index " + recipeIndex + "): " + recipeName);
                    homeScreen.clickRecipeByName(recipeName);
                    WaitingHelper.sleepSeconds(2);

                    // Verify detail
                    logger.info("   Step 2: Verify detail hiển thị");
                    boolean isDisplayed = recipeDetailScreen.isRecipeDetailDisplayed();
                    Assert.assertTrue(isDisplayed, "❌ FAIL: Chi tiết không hiển thị!");

                    // Quay lại
                    logger.info("   Step 3: Quay lại danh sách");
                    recipeDetailScreen.clickBackButton();
                    WaitingHelper.sleepSeconds(1);
                    homeScreen.waitForRecipeListLoad();

                    long iterationEnd = System.currentTimeMillis();
                    long iterationTime = iterationEnd - iterationStart;
                    minTime = Math.min(minTime, iterationTime);
                    maxTime = Math.max(maxTime, iterationTime);

                    logger.info("   ✅ Iteration " + (i + 1) + " PASS - Time: " + iterationTime + "ms");
                    AllureHelper.attachScreenshot("Iteration " + (i + 1) + " completed in " + iterationTime + "ms");

                } catch (Exception e) {
                    logger.error("❌ Iteration " + (i + 1) + " FAILED: " + e.getMessage());
                    throw e;
                }
            }

            long totalDuration = System.currentTimeMillis() - startTime;
            double avgTime = (double) totalDuration / iterations;

            // ================================================================
            // ✅ EXPECTED RESULT
            // ================================================================
            logger.info("\n┌─── ✅ EXPECTED RESULT ────────────────────────────────┐");
            logger.info("│ ✅ Ứng dụng xử lý mượt mà khi mở liên tiếp             │");
            logger.info("│   - Số lần mở: " + String.format("%-43s│", iterations));
            logger.info("│   - Thời gian tổng: " + String.format("%-39s│", totalDuration + "ms"));
            logger.info("│   - Thời gian trung bình: " + String.format("%-35s│", (int)avgTime + "ms"));
            logger.info("│   - Thời gian tối thiểu: " + String.format("%-36s│", minTime + "ms"));
            logger.info("│   - Thời gian tối đa: " + String.format("%-37s│", maxTime + "ms"));
            logger.info("│   - Không crash hoặc lag                              │");
            logger.info("│   - Dữ liệu hiển thị đúng mỗi lần mở                  │");
            logger.info("│                                                        │");
            logger.info("│ ✅ TEST PASSED                                         │");
            logger.info("└────────────────────────────────────────────────────────┘");

            logger.info("\n╔════════════════════════════════════════════════════════════╗");
            logger.info("║ ✅ RecipeDisplay_TC_05 PASSED                             ║");
            logger.info("╚════════════════════════════════════════════════════════════╝\n");

        } catch (AssertionError ae) {
            logger.error("❌ RecipeDisplay_TC_05 FAILED - Assertion: " + ae.getMessage());
            AllureHelper.attachScreenshot("❌ RecipeDisplay_TC_05 - ASSERTION FAILED");
            throw ae;
        } catch (Exception e) {
            logger.error("❌ RecipeDisplay_TC_05 FAILED - Exception: " + e.getMessage());
            AllureHelper.attachErrorMessage("Error: " + e.getMessage());
            AllureHelper.attachScreenshot("❌ RecipeDisplay_TC_05 - ERROR");
            throw e;
        }
    }
}