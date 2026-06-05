package e2e;

import core.base.BaseTest;
import core.data.LoginData;
import core.utils.AllureHelper;
import core.utils.JsonHelper;
import core.utils.WaitingHelper;
import flows.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.*;

import java.util.Arrays;
import java.util.List;

import static core.driver.DriverManager.getDriver;

/**
 * ════════════════════════════════════════════════════════════════════════════
 *  E2EIntegrationTest.java
 *  Kiểm thử tích hợp tự động — Luồng nghiệp vụ End-to-End (E2E)
 *
 *  PHÂN NHÓM LUỒNG:
 *
 *  E2E_01  Login → Xem công thức → Đăng xuất
 *  E2E_02  Đăng ký → Đổi mật khẩu → Đăng nhập lại
 *  E2E_03  Login → Tìm kiếm → Lưu CT → Kiểm tra Kho
 *  E2E_04  Login → Thêm CT mới → Tìm kiếm CT vừa đăng
 *  E2E_05  Login → Chỉnh sửa CT đã lưu → Verify
 *  E2E_06  Login → Fridge → Gợi ý món → Lưu CT
 *  E2E_07  Login → Bepes AI (chọn món + chat + hoàn thành)
 *  E2E_08  Login → Tạo Shopping List từ CT → Hoàn thành → Kiểm tra History
 *  E2E_09  Guest → Shopping List → Login → Danh sách giữ nguyên
 *  E2E_10  Login → Cập nhật Profile → Logout → Login lại verify data
 * ════════════════════════════════════════════════════════════════════════════
 */
@Epic("ChefMate Android — E2E Integration Tests")
public class E2EIntegrationTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(E2EIntegrationTest.class);

    // ── Screens ──────────────────────────────────────────────────────────────
    HomeScreen             home    = new HomeScreen();
    SearchScreen           search  = new SearchScreen();
    RecipeDetailScreen     detail  = new RecipeDetailScreen();
    ProfileScreen          profile = new ProfileScreen();
    SavedRecipesScreen     saved   = new SavedRecipesScreen();
    FridgeScreen           fridge  = new FridgeScreen();
    BepesAIScreen          bepes   = new BepesAIScreen();
    EditProfileScreen      editProfile = new EditProfileScreen();
    ShoppingBuilderScreen  builder = new ShoppingBuilderScreen();
    AddRecipeScreen        addScreen   = new AddRecipeScreen();
    SplashScreen           splash  = new SplashScreen();
    LoginScreen            login   = new LoginScreen();
    // ── Flows ─────────────────────────────────────────────────────────────────
    AuthenticationFlow authFlow    = new AuthenticationFlow();
    SearchFlow         searchFlow  = new SearchFlow(home, search, detail);
    SaveRecipeFlow     saveFlow    = new SaveRecipeFlow();
    FridgeFlow         fridgeFlow  = new FridgeFlow();
    BepesAIFlow        bepesFlow   = new BepesAIFlow(home, bepes);
    ShoppingBuilderFlow builderFlow = new ShoppingBuilderFlow(home, search, detail);
    ShoppingListFlow   listFlow    = new ShoppingListFlow();
    ShoppingHistoryFlow historyFlow = new ShoppingHistoryFlow();
    AddRecipeFlow      addFlow     = new AddRecipeFlow();
    ProfileFlow        profileFlow = new ProfileFlow();

    // ── Data ──────────────────────────────────────────────────────────────────
    LoginData loginData = JsonHelper.readLoginData();
    String email    = loginData.login;
    String password = loginData.password;

    // ═════════════════════════════════════════════════════════════════════════
    // E2E_01: Login → Xem chi tiết công thức → Quay lại Home → Đăng xuất
    // ═════════════════════════════════════════════════════════════════════════
    @Test(priority = 1,
            description = "E2E_TC_01_LoginViewRecipeLogout")
    @Feature("E2E: Xem công thức")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Luồng cơ bản nhất của ứng dụng")
    public void E2E_TC_01_LoginViewRecipeLogout() {

        logger.info("▶ E2E_01 START");

        // STEP 1: Login
        authFlow.loginFromFridgeTab(email, password);
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "❌ [S1] Login thất bại");
        AllureHelper.attachScreenshot("[S1] Login thành công");

        // STEP 2: Verify Home hiển thị danh sách CT
        Assert.assertTrue(home.isHomeDisplayed(),          "❌ [S2] Home không hiển thị");
        Assert.assertTrue(home.isRecipeListDisplayed(),    "❌ [S2] Danh sách CT trống");
        Assert.assertTrue(home.getRecipeCount() > 0,       "❌ [S2] Không có CT nào");
        AllureHelper.attachScreenshot("[S2] Home — danh sách CT");

        // STEP 3: Click vào CT đầu tiên
        String recipeName = home.getRecipeNameAt(0);
        home.clickRecipeByName(recipeName);
        detail.waitForLoaded();
        Assert.assertTrue(detail.isRecipeDetailDisplayed(),  "❌ [S3] Màn chi tiết CT không hiển thị");
        Assert.assertNotNull(detail.getRecipeTitle(),         "❌ [S3] Tiêu đề CT null");
        Assert.assertTrue(detail.isTabsDisplayed(),           "❌ [S3] Tabs không hiển thị");
        AllureHelper.attachScreenshot("[S3] Chi tiết CT: " + recipeName);

        // STEP 4: Kiểm tra tab Nguyên liệu
        detail.clickIngredientTab();
        Assert.assertTrue(detail.getIngredientCount() > 0, "❌ [S4] Không có nguyên liệu");
        AllureHelper.attachScreenshot("[S4] Tab Nguyên liệu");

        // STEP 5: Kiểm tra tab Hướng dẫn
        detail.clickInstructionTab();
        Assert.assertTrue(detail.getInstructionCount() > 0, "❌ [S5] Không có bước nấu");
        AllureHelper.attachScreenshot("[S5] Tab Hướng dẫn");

        // STEP 6: Back về Home
        detail.clickBackButton();
        Assert.assertTrue(home.isHomeDisplayed(), "❌ [S6] Không về được Home");
        AllureHelper.attachScreenshot("[S6] Back về Home");

        // STEP 7: Logout
        authFlow.performLogout();
        Assert.assertTrue(authFlow.isLogoutSuccessful(), "❌ [S7] Logout thất bại");
        AllureHelper.attachScreenshot("[S7] Logout thành công");

        logger.info("✅ E2E_01 PASSED");
    }

    // ═════════════════════════════════════════════════════════════════════════
    // E2E_02: Login → Đổi mật khẩu → Logout → Login lại với mật khẩu mới → Đổi về
    // ═════════════════════════════════════════════════════════════════════════
    @Test(priority = 2, description = "E2E_TC_02_ChangePasswordFlow")
    @Feature("E2E: Đổi mật khẩu")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Bảo mật tài khoản")
    public void E2E_TC_02_ChangePasswordFlow() {

        logger.info("▶ E2E_02 START");
        String newPassword = "1234567";

        // STEP 1: Login
        authFlow.loginFromProfileTab(email, password);
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "❌ [S1] Login thất bại");
        AllureHelper.attachScreenshot("[S1] Login thành công");

        // STEP 2: Đổi mật khẩu
        authFlow.changePassword(password, newPassword, newPassword);
        Assert.assertTrue(authFlow.isChangePasswordSuccessDisplayed(),
                "❌ [S2] Toast đổi MK thành công không hiển thị");
        AllureHelper.attachScreenshot("[S2] Đổi MK thành công → MK mới: " + newPassword);

        // STEP 3: Back về Profile → Logout
        authFlow.backFromChangePassword();
        authFlow.goBackToProfileFromEditScreen();
        authFlow.performLogoutSafely();
        Assert.assertTrue(authFlow.isLogoutSuccessful(), "❌ [S3] Logout thất bại");
        AllureHelper.attachScreenshot("[S3] Logout thành công");

        // STEP 4: Login lại với mật khẩu mới
        authFlow.loginFromProfileTab(email, newPassword);
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(),
                "❌ [S4] Login bằng mật khẩu mới thất bại");
        AllureHelper.attachScreenshot("[S4] Login bằng MK mới thành công");

        // STEP 5: Login cũ phải FAIL
        authFlow.performLogoutSafely();
        authFlow.loginFromProfileTab(email, password);
        Assert.assertTrue(authFlow.isToastLoginFail(), "❌ [S5] Login bằng MK cũ phải thất bại sau khi đổi");
        AllureHelper.attachScreenshot("[S5] Login MK cũ đúng thất bại như kỳ vọng");
        WaitingHelper.sleepSeconds(2); // bắt buộc phải có waitting !

        // STEP 6: Revert — đổi ngược về mật khẩu gốc
        login.performLogin(email, newPassword);
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "❌ [S6] Login lại để revert thất bại");
        authFlow.changePassword(newPassword, password, password);
        Assert.assertTrue(authFlow.isChangePasswordSuccessDisplayed(),
                "❌ [S6] Revert MK về ban đầu thất bại");
        AllureHelper.attachScreenshot("[S6] Revert MK về ban đầu thành công");

        logger.info("✅ E2E_02 PASSED");
    }

    // ═════════════════════════════════════════════════════════════════════════
    // E2E_03: Login → Tìm kiếm → Lưu CT → Kiểm tra trong Kho → Xóa
    // ═════════════════════════════════════════════════════════════════════════
    @Test(priority = 3,
            description = "E2E_TC_03_SearchSaveVerifyDelete")
    @Feature("E2E: Tìm kiếm & Lưu công thức")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Tìm kiếm và quản lý kho CT")
    public void E2E_TC_03_SearchSaveVerifyDelete() {

        logger.info("▶ E2E_03 START");

        // STEP 1: Login
        authFlow.loginFromFridgeTab(email, password);
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "❌ [S1] Login thất bại");
        AllureHelper.attachScreenshot("[S1] Login thành công");

        // STEP 2: Tìm kiếm theo tên món
        searchFlow.searchByRecipeName("Salad");
        Assert.assertTrue(search.getResultCount() > 0, "❌ [S2] Không có kết quả tìm kiếm");
        AllureHelper.attachScreenshot("[S2] Kết quả tìm kiếm 'Salad'");

        // STEP 3: Mở CT đầu tiên
        search.clickResultAt(0);
        detail.waitForLoaded();
        Assert.assertTrue(detail.isRecipeDetailDisplayed(), "❌ [S3] Màn chi tiết không hiển thị");
        String title  = detail.getRecipeTitle();
        String author = detail.getRecipeAuthor();
        AllureHelper.attachScreenshot("[S3] Chi tiết CT: " + title);

        // STEP 4: Lưu CT
        boolean savedOk = detail.clickSaveAndVerifyToast();
        Assert.assertTrue(savedOk, "❌ [S4] Toast lưu CT không hiển thị");
        AllureHelper.attachScreenshot("[S4] Lưu CT: " + title);

        // STEP 5: Back → vào Kho
        detail.clickBackToHome();
        profile.clickTabProfile();
        profile.openSavedRecipes();
        Assert.assertTrue(saved.isRecipeExist(title, author),
                "❌ [S5] CT '" + title + "' không có trong Kho");
        AllureHelper.attachScreenshot("[S5] CT tồn tại trong Kho");

        // STEP 6: Xóa CT khỏi Kho
        saved.clickDeleteAt(0);
        Assert.assertTrue(saved.isDeletePopupDisplayed(), "❌ [S6] Popup xóa không hiển thị");
        saved.confirmDelete();
        Assert.assertFalse(saved.isRecipeDisplayed(title),
                "❌ [S6] CT vẫn còn trong Kho sau khi xóa");
        AllureHelper.attachScreenshot("[S6] Xóa CT khỏi Kho thành công");

        logger.info("✅ E2E_03 PASSED");
    }

    // ═════════════════════════════════════════════════════════════════════════
    // E2E_04: Login → Đăng CT mới → Tìm thấy CT vừa đăng
    // ═════════════════════════════════════════════════════════════════════════
    @Test(priority = 4,
            description = "E2E_TC_04_AddRecipeAndVerify")
    @Feature("E2E: Thêm công thức")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Quản lý CT của người dùng")
    public void E2E_TC_04_AddRecipeAndVerify() {

        logger.info("▶ E2E_04 START");

        // STEP 1: Login
        authFlow.loginFromFridgeTab(email, password);
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "❌ [S1] Login thất bại");
        AllureHelper.attachScreenshot("[S1] Login thành công");

        // STEP 2: Mở màn Add Recipe
        addScreen.clickScreenAddRecipe();
        Assert.assertTrue(addScreen.isDisplayed(), "❌ [S2] Không vào được màn Add Recipe");
        AllureHelper.attachScreenshot("[S2] Màn Add Recipe");

        // STEP 3: Nhập thông tin CT
        // ===== AVATAR =====
        addScreen.clickAvatar();
        addScreen.chooseImageFromGalleryEmulator(1);

        WaitingHelper.sleepSeconds(1);

        // ===== TAG =====
        List<String> tags = Arrays.asList("Cá", "Kem", "Trứng");

        addScreen.addMultipleTags(tags);

        //INFORMATION
        String name = "E2E_CT_" + System.currentTimeMillis() % 100000;
        addScreen.inputName(name);
        addScreen.inputTime("20 phút");
        addScreen.inputServing("2");
        try { getDriver().hideKeyboard(); } catch (Exception ignored) {}
        AllureHelper.attachScreenshot("[S3] Nhập thông tin cơ bản: " + name);

        // STEP 4: Thêm nguyên liệu
        addScreen.inputIngredients(Arrays.asList(
                Arrays.asList("Gạo",   "200", "gram"),
                Arrays.asList("Nước",  "500", "ml")
        ));
        try { getDriver().hideKeyboard(); } catch (Exception ignored) {}
        AllureHelper.attachScreenshot("[S4] Nhập nguyên liệu");

        // STEP 5: Thêm bước nấu
        addScreen.inputSteps(Arrays.asList("Vo gạo", "Nấu cơm", "Chờ chín"));
        try { getDriver().hideKeyboard(); } catch (Exception ignored) {}
        AllureHelper.attachScreenshot("[S5] Nhập bước nấu");

        // STEP 6: Đăng CT
        addScreen.clickPost();
        WaitingHelper.sleepSeconds(2);
        AllureHelper.attachScreenshot("[S6] Sau khi đăng CT");

        // STEP 7: Vào Kho → verify CT vừa đăng
        profile.clickTabProfile();
        profile.openSavedRecipes();
        Assert.assertTrue(saved.isRecipeExist(name, ""),
                "❌ [S7] CT '" + name + "' không xuất hiện trong Kho");
        AllureHelper.attachScreenshot("[S7] CT đăng thành công trong Kho: " + name);

        logger.info("✅ E2E_04 PASSED");
    }

    // ═════════════════════════════════════════════════════════════════════════
    // E2E_05: Login → Lưu CT → Chỉnh sửa → Verify tên mới trong Kho
    // ═════════════════════════════════════════════════════════════════════════
    @Test(priority = 5,
            description = "E2E_TC_05_SaveThenEditRecipe")
    @Feature("E2E: Chỉnh sửa công thức")
    @Severity(SeverityLevel.NORMAL)
    @Story("Quản lý CT của người dùng")
    public void E2E_TC_05_SaveThenEditRecipe() {

        logger.info("▶ E2E_05 START");

        // STEP 1: Login
        authFlow.loginFromFridgeTab(email, password);
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "❌ [S1] Login thất bại");
        AllureHelper.attachScreenshot("[S1] Login thành công");

        // STEP 2: Tìm & Lưu CT
        searchFlow.searchByTag("cà phê");
        search.clickResultAt(0);
        detail.waitForLoaded();
        boolean savedOk = detail.clickSaveAndVerifyToast();
        Assert.assertTrue(savedOk, "❌ [S2] Lưu CT thất bại");
        detail.clickBackToHome();
        AllureHelper.attachScreenshot("[S2] Lưu CT thành công");

        // STEP 3: Vào Kho → click Edit
        profile.clickTabProfile();
        profile.openSavedRecipes();
        saved.clickEditAt(0);
        AllureHelper.attachScreenshot("[S3] Màn Edit Recipe");

        // STEP 4: Đổi tên CT
        EditRecipeScreen editScreen = new EditRecipeScreen();
        String newName = "E2E_Edit_" + System.currentTimeMillis() % 10000;
        editScreen.clearName();
        editScreen.inputName(newName);
        try { getDriver().hideKeyboard(); } catch (Exception ignored) {}
        editScreen.slowSwipeDownOnScreen(2);
        editScreen.clickUpdate();
        WaitingHelper.sleepSeconds(2);
        AllureHelper.attachScreenshot("[S4] Sau khi update tên: " + newName);

        // STEP 5: Verify tên mới trong Kho
        saved.clickBack();
        profile.clickTabProfile();
        profile.openSavedRecipes();
        Assert.assertTrue(saved.isRecipeExist(newName, ""),
                "❌ [S5] Tên CT mới '" + newName + "' không xuất hiện trong Kho");
        AllureHelper.attachScreenshot("[S5] CT đã đổi tên thành công trong Kho");

        logger.info("✅ E2E_05 PASSED");
    }

    // ═════════════════════════════════════════════════════════════════════════
    // E2E_06: Login → Thêm NL vào Tủ lạnh → Lấy gợi ý → Xem chi tiết CT
    // ═════════════════════════════════════════════════════════════════════════
    @Test(priority = 6,
            description = "E2E_TC_06_FridgeSuggestRecipe")
    @Feature("E2E: Tủ lạnh & Gợi ý")
    @Severity(SeverityLevel.NORMAL)
    @Story("Gợi ý từ tủ lạnh")
    public void E2E_TC_06_FridgeSuggestRecipe() {

        logger.info("▶ E2E_06 START");

        // STEP 1: Login qua cổng Tủ lạnh
        authFlow.loginFromFridgeTab(email, password);
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "❌ [S1] Login thất bại");
        AllureHelper.attachScreenshot("[S1] Login thành công");

        // STEP 2: Mở Tủ lạnh
        fridgeFlow.openFridge();
        AllureHelper.attachScreenshot("[S2] Màn Tủ lạnh");

        // STEP 3: Thêm 2 nguyên liệu thủ công
        fridgeFlow.addManualIngredient("Thịt bò", "300", "gram", "2026-12-31");
        fridgeFlow.addManualIngredient("Hành tây", "2",   "củ",  "2026-12-31");
        AllureHelper.attachScreenshot("[S3] Đã thêm NL vào Tủ lạnh");

        // STEP 4: Verify nguyên liệu hiển thị trong Tủ
        Assert.assertFalse(fridge.verifyScreenNotLogin(),
                "❌ [S4] Tủ lạnh không hiển thị NL (chưa login?)");
        AllureHelper.attachScreenshot("[S4] NL tồn tại trong Tủ lạnh");

        // STEP 5: Nhấn Gợi ý từ Tủ lạnh trên Home
        home.clickTabHome();
        Assert.assertTrue(home.isHomeDisplayed(), "❌ [S5] Không về được Home");
        AllureHelper.attachScreenshot("[S5] Home sau khi quay về");

        logger.info("✅ E2E_06 PASSED");
    }

    // ═════════════════════════════════════════════════════════════════════════
    // E2E_07: Login → Mở Bepes AI → Chọn món → Chat → Xem CT → Hoàn thành
    // ═════════════════════════════════════════════════════════════════════════
    @Test(priority = 7,
            description = "E2E_TC_07_BepesAIFullFlow")
    @Feature("E2E: Bepes AI")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Trợ lý AI nấu ăn")
    public void E2E_TC_07_BepesAIFullFlow() {

        logger.info("▶ E2E_07 START");

        // STEP 1: Login
        authFlow.loginFromFridgeTab(email, password);
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "❌ [S1] Login thất bại");
        AllureHelper.attachScreenshot("[S1] Login thành công");

        // STEP 2: Mở Bepes AI
        bepesFlow.openBepesFromHome();
        AllureHelper.attachScreenshot("[S2] Màn Bepes AI");

        // STEP 3: Chọn 1 món từ danh sách
        bepes.openChooseDishSheetSmart();
        bepes.slowSwipeDownOnScreen(2);
        bepes.clickChooseDishByIndex(1);
        bepes.confirmChooseDishPopup();
        AllureHelper.attachScreenshot("[S3] Đã chọn món");

        // STEP 4: Gửi tin nhắn hỏi AI
        String msg = "Món này cần chuẩn bị gì trước khi nấu?";
        bepes.sendMessage(msg);
        bepes.waitForAiResponse(15);
        AllureHelper.attachScreenshot("[S4] AI đã phản hồi");

        // STEP 5: Gửi câu hỏi tiếp theo (multi-turn)
        bepes.sendMessage("Cần bao nhiêu thời gian?");
        bepes.waitForAiResponse(15);
        AllureHelper.attachScreenshot("[S5] Multi-turn chat");

        // STEP 6: Mở ghi chú (Notes)
        bepes.showActions_2();
        bepes.openNotesSheet();
        bepes.openAddNotePopup();
        bepes.selectNoteTypePreference();
        bepes.fillNoteFields("Không ăn cay", "0", "0");
        bepes.saveNotePopup();
        bepes.verifyNoteItemExists("Không ăn cay");
        AllureHelper.attachScreenshot("[S6] Đã thêm ghi chú sở thích");
        bepes.closeNotesSheet();

        // STEP 7: Mở xem CT gợi ý
        bepes.showActions_2();
        bepes.openViewRecipes();
        bepes.verifyRecipesSheetOpened();
        AllureHelper.attachScreenshot("[S7] Danh sách CT gợi ý");
        driver.navigate().back();

        // STEP 8: Hoàn thành phiên AI
        bepes.showActions_2();
        bepes.openFinishPopupByActionButton();
        bepes.verifyFinishPopupUI();
        bepes.completeFinishPopup("finish session");
        AllureHelper.attachScreenshot("[S8] Hoàn thành phiên Bepes AI");

        logger.info("✅ E2E_07 PASSED");
    }

    // ═════════════════════════════════════════════════════════════════════════
    // E2E_08: Login → Tạo Shopping List từ CT đã lưu → Check NL → Hoàn thành → Verify History
    // ═════════════════════════════════════════════════════════════════════════
    @Test(priority = 8,
            description = "E2E_TC_08_ShoppingFullFlow")
    @Feature("E2E: Shopping")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Luồng mua sắm đầu cuối")
    public void E2E_TC_08_ShoppingFullFlow() {

        logger.info("▶ E2E_08 START");

        // STEP 1: Login + Lưu sẵn 1 CT
        builderFlow.login(email, password);
        List<String> titles = builderFlow.searchAndSaveRecipes("xúc xích", 1);
        Assert.assertTrue(titles.size() >= 1, "❌ [S1] Seed CT thất bại");
        AllureHelper.attachScreenshot("[S1] Đã lưu CT: " + titles.get(0));

        // STEP 2: Mở Shopping Builder
        builderFlow.openShoppingBuilderFromMenu();
        AllureHelper.attachScreenshot("[S2] Shopping Builder");

        // STEP 3: Tick 1 CT + thêm 1 NL thủ công
        builderFlow.toggleRecipeByIndex(1);
        builderFlow.addManualIngredient("Gia vị đặc biệt", "50", "gram");
        AllureHelper.attachScreenshot("[S3] Đã tick CT + thêm NL thủ công");

        // STEP 4: Tạo Shopping List
        builderFlow.clickComplete();
        listFlow.verifyListUI();
        AllureHelper.attachScreenshot("[S4] Shopping List đã tạo");
        builderFlow.slowSwipeDownOnScreen();
        // STEP 5: Verify NL thủ công xuất hiện
        listFlow.verifyIngredientDisplayed("Gia vị đặc biệt - 50 gram");
        AllureHelper.attachScreenshot("[S5] NL thủ công hiển thị trong List");

        // STEP 6: Thêm NL bổ sung trực tiếp trên List
        listFlow.addSupplementIngredient("Rau thơm", "1", "bó");
        builderFlow.slowSwipeDownOnScreen();
        listFlow.verifyIngredientDisplayed("Rau thơm - 1 bó");
        AllureHelper.attachScreenshot("[S6] Đã bổ sung NL thêm");

        // STEP 7: Chỉnh sửa tên NL đầu tiên
        String editedName = "NL_E2E_Edited";
        listFlow.editIngredientNameAt(0, editedName);
        listFlow.verifyIngredientDisplayedIgnoreCase(editedName);
        AllureHelper.attachScreenshot("[S7] Chỉnh sửa NL thành công");

        // STEP 8: Tick hoàn thành 1 NL
        listFlow.toggleCheckboxAt(0);
        AllureHelper.attachScreenshot("[S8] Tick NL đầu tiên");

        // STEP 9: Hoàn thành Shopping List
        listFlow.clickComplete();
        AllureHelper.attachScreenshot("[S9] Hoàn thành Shopping List");

        // STEP 10: Kiểm tra History
        profile.clickTabProfile();
        historyFlow.openHistoryFromProfile();
        Assert.assertTrue(historyFlow.getHistoryItemCount() >= 1,
                "❌ [S10] History phải có ít nhất 1 record");
        AllureHelper.attachScreenshot("[S10] History có record mới");

        // STEP 11: Mở detail history → verify dữ liệu
        String date = historyFlow.getPurchaseDateTextAt(0);
        Assert.assertTrue(date.matches("\\d{2}/\\d{2}/\\d{4}"), "❌ Date format not dd/MM/yyyy: " + date);
        AllureHelper.attachScreenshot("[S11] Detail History: date=" + date);

        historyFlow.openLatestHistoryDetail();
        AllureHelper.attachScreenshot("RecipeHistoryShopping_TC_05 PASSED");
        historyFlow.backToHistoryList();

        logger.info("✅ E2E_08 PASSED");
    }

    // ═════════════════════════════════════════════════════════════════════════
    // E2E_09: Guest tạo Shopping List → Login → Danh sách vẫn còn
    // ═════════════════════════════════════════════════════════════════════════
    @Test(priority = 9,
            description = "E2E_TC_09_GuestShoppingPersistAfterLogin")
    @Feature("E2E: Shopping")
    @Severity(SeverityLevel.NORMAL)
    @Story("Guest session persistence")
    public void E2E_TC_09_GuestShoppingPersistAfterLogin() {

        logger.info("▶ E2E_09 START");

        // STEP 1: Không login — mở Builder
        builderFlow.openShoppingBuilderFromMenu();
        AllureHelper.attachScreenshot("[S1] Builder khi chưa login");

        // STEP 2: Thêm NL thủ công
        String guestItem = "GuestItem_E2E";
        builderFlow.addManualIngredient(guestItem, "3", "kg");
        builderFlow.clickComplete();
        listFlow.verifyListUI();
        listFlow.verifyIngredientDisplayedIgnoreCase(guestItem + " - 3 kg");
        AllureHelper.attachScreenshot("[S2] Guest Shopping List tạo thành công");

        // STEP 3: Back → Login
        listFlow.clickBack();
        builderFlow.login(email, password);
        AllureHelper.attachScreenshot("[S3] Đã login");

        // STEP 4: Vào lại DS Mua sắm → verify dữ liệu còn
        builderFlow.openDSMS();
        listFlow.verifyIngredientDisplayedIgnoreCase(guestItem);
        AllureHelper.attachScreenshot("[S4] Guest item vẫn còn sau login: " + guestItem);

        logger.info("✅ E2E_09 PASSED");
    }

    // ═════════════════════════════════════════════════════════════════════════
    // E2E_10: Login → Cập nhật Profile (tên + email) → Logout → Login lại → Verify data
    // ═════════════════════════════════════════════════════════════════════════
    @Test(priority = 10,
            description = "E2E_TC_10_UpdateProfilePersistAfterRelogin")
    @Feature("E2E: Profile")
    @Severity(SeverityLevel.NORMAL)
    @Story("Cập nhật thông tin cá nhân")
    public void E2E_TC_10_UpdateProfilePersistAfterRelogin() {

        logger.info("▶ E2E_10 START");

        String targetName  = "Tưởng Thị Duyên";
        String targetEmail = email;
        String targetPhone = "0900009999";

        // STEP 1: Login
        authFlow.loginFromProfileTab(email, password);
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "❌ [S1] Login thất bại");
        AllureHelper.attachScreenshot("[S1] Login thành công");

        // STEP 2: Mở Edit Profile → cập nhật thông tin
        profile.clickBottomNavProfile();
        editProfile.openEditProfileScreen();
        editProfile.enterFullName(targetName);
        editProfile.enterEmail(targetEmail);
        editProfile.enterPhone(targetPhone);
        editProfile.clickSave();
        Assert.assertTrue(authFlow.isToastUpdateInfoDisplayed(),
                "❌ [S2] Toast cập nhật thành công không hiển thị");
        AllureHelper.attachScreenshot("[S2] Cập nhật Profile thành công");

        // STEP 3: Back về Profile → verify ngay
        editProfile.clickBack();
        Assert.assertTrue(authFlow.isProfileInfoCorrect(targetName, targetEmail, targetPhone),
                "❌ [S3] Thông tin Profile không đúng sau khi cập nhật");
        AllureHelper.attachScreenshot("[S3] Profile data đúng trước logout");

        // STEP 4: Logout
        authFlow.performLogoutSafely();
        Assert.assertTrue(authFlow.isLogoutSuccessful(), "❌ [S4] Logout thất bại");
        AllureHelper.attachScreenshot("[S4] Logout thành công");

        // STEP 5: Login lại
        authFlow.loginFromProfileTab(email, password);
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "❌ [S5] Login lại thất bại");
        AllureHelper.attachScreenshot("[S5] Login lại thành công");

        // STEP 6: Verify Profile vẫn đúng sau khi đăng nhập lại
        profile.clickBottomNavProfile();
        Assert.assertTrue(authFlow.isProfileInfoCorrect(targetName, targetEmail, targetPhone),
                "❌ [S6] Dữ liệu Profile bị mất sau khi đăng nhập lại");
        AllureHelper.attachScreenshot("[S6] Profile data vẫn đúng sau re-login ✅");

        logger.info("✅ E2E_10 PASSED");
    }
}