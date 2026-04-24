package smoke;

import core.base.BaseTest;
import core.data.LoginData;
import core.utils.AllureHelper;
import core.utils.JsonHelper;
import core.utils.WaitingHelper;
import flows.AuthenticationFlow;
import flows.RecipeFlow;
import flows.SaveRecipeFlow;
import flows.SearchFlow;
import flows.ShareFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.HomeScreen;
import screens.ProfileScreen;
import screens.RecipeDetailScreen;
import screens.SearchScreen;
import screens.SavedRecipesScreen;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import static core.driver.DriverManager.getDriver;
import org.openqa.selenium.By;
public class SaveRecipeTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(SaveRecipeTest.class);

    // ===== SCREEN =====
    HomeScreen home = new HomeScreen();
    SearchScreen search = new SearchScreen();
    RecipeDetailScreen detail = new RecipeDetailScreen();
    ProfileScreen profile = new ProfileScreen();
    SavedRecipesScreen saved = new SavedRecipesScreen();

    // ===== FLOW =====
    SearchFlow searchFlow = new SearchFlow(home, search, detail);
    SaveRecipeFlow saveFlow = new SaveRecipeFlow();
    AuthenticationFlow authFlow = new AuthenticationFlow();
    ShareFlow shareFlow = new ShareFlow(detail);
    private final HomeScreen homeScreen = new HomeScreen();
    private final RecipeDetailScreen recipeDetailScreen = new RecipeDetailScreen();

    // ===== DATA =====
    LoginData loginData = JsonHelper.readLoginData();
    String email = loginData.login;
    String password = loginData.password;

    // ===== LOGIN =====
    private void login() {
        authFlow.loginFromFridgeTab(email, password);
        WaitingHelper.sleepSeconds(2);
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "❌ Login failed");
        AllureHelper.attachScreenshot("LOGIN SUCCESS");
    }
    private void openRecipeDetail() {

        login();

        searchFlow.searchByTag("cà phê"); // hoặc data chị muốn test
        search.clickResultAt(0);

        detail.waitForLoaded();
        // 🔥 ĐẢM BẢO KHÔNG BỊ KEYBOARD CHẶN
        try {
            getDriver().hideKeyboard();
        } catch (Exception ignored) {}

        // 🔥 SCROLL 2 LẦN (đủ dùng)
        search.slowSwipeDownOnScreen(1);
        Assert.assertTrue(
                detail.isRecipeDetailDisplayed(),
                "❌ Không vào được màn chi tiết công thức"
        );
    }

//    // =========================
//    // TC_01 - SAVE SUCCESS (TOAST)
//    // =========================
//    @Test(priority = 1)
//    public void RecipeSave_TC_01() {
//
//        login();
//
//        searchFlow.searchByTag("nước sốt");
//        search.clickResultAt(0);
//
//        detail.waitForLoaded();
//
//        boolean savedOk = detail.clickSaveAndVerifyToast();
//
//        Assert.assertTrue(savedOk, "❌ Không hiển thị toast 'Đã lưu'");
//    }
//
////    // =========================
////    // TC_02 - VERIFY IN SAVED LIST
////    // =========================
//    @Test(priority = 2)
//    public void RecipeSave_TC_02() {
//
//        login();
//
//        searchFlow.searchByTag("nước sốt");
//        search.clickResultAt(0);
//
//        detail.waitForLoaded();
//
//        String title = detail.getRecipeTitle();
//        String author = detail.getRecipeAuthor();
//
//        detail.clickSaveAndVerifyToast();
//        WaitingHelper.sleepSeconds(1);
//        detail.clickBackToHome();
//
//        // 🔥 VERIFY HOME TRƯỚC KHI CLICK
//        home.backToHome();
//
//        Assert.assertTrue(home.isHomeDisplayed(), "❌ Chưa về Home thật");
//
//        profile.clickTabProfile();
//        profile.openSavedRecipes();
//
//        Assert.assertTrue(
//                saved.isRecipeExist(title, author),
//                "❌ Công thức không xuất hiện trong Kho công thức"
//        );
//    }

    // =========================
    // TC_03 - MULTI SAVE + COUNT
    // =========================
//    @Test(priority = 3)
//    public void RecipeSave_TC_03() {
//
//        login();
//
//        int before = saveFlow.getSavedCount();
//
//        searchFlow.searchByTag("thịt gà");
//
//        int target = 6;
//        int saved = 0;
//
//        while (saved < target) {
//
//            int total = search.getResultCount();
//
//            // 🔥 LUÔN CLICK ITEM CUỐI CÙNG TRÊN MÀN
//            int indexToClick = total - 1;
//
//            try {
//                search.clickResultAt(indexToClick);
//
//                detail.waitForLoaded();
//                boolean isSaved = detail.clickSaveAndVerifyToast();
//
//                if (isSaved) {
//                    saved++;
//                }
//
//                detail.clickBack();
//
//                // 👉 scroll sau khi save để load item mới
//                search.slowSwipeDownOnScreen(1);
//
//            } catch (Exception e) {
//                logger.error("❌ Error: " + e.getMessage());
//                search.clickBack();
//            }
//        }
//        search.clickBack();
//        int after = saveFlow.getSavedCount();
//
//        Assert.assertTrue(
//                after >= before + saved,
//                "❌ Số lượng không cập nhật đúng"
//        );
//    }
//    // =========================
//    // TC_04 - ORDER NEWEST FIRST
//    // =========================
//    @Test(priority = 4)
//    public void RecipeSave_TC_04() {
//
//        login();
//
//        searchFlow.searchByTag("gà");
//
//        // Save 1
//        search.clickResultAt(0);
//        detail.waitForLoaded();
//        String title1 = detail.getRecipeTitle();
//        detail.clickSaveAndVerifyToast();
//        detail.clickBack();
//
//        // Save 2 (newest)
//        search.clickResultAt(1);
//        detail.waitForLoaded();
//        String title2 = detail.getRecipeTitle();
//        detail.clickSaveAndVerifyToast();
//        detail.clickBack();
//
//        profile.clickTabProfile();
//        profile.openSavedRecipes();
//
//        Assert.assertTrue(
//                saved.isNewestOnTop(title2),
//                "❌ Công thức mới nhất không nằm đầu"
//        );
////    }
//    @Test(priority = 4)
//    public void RecipeSave_TC_04() {
//
//        login();
//
//        searchFlow.searchByTag("CÀ PHÊ");
//
//        int target = 3;
//        int savedCount = 0;
//
//        List<String> savedTitles = new ArrayList<>();
//
//        while (savedCount < target) {
//
//            int total = search.getResultCount();
//            int indexToClick = total - 1;
//
//            try {
//                search.clickResultAt(indexToClick);
//
//                detail.waitForLoaded();
//
//                String title = detail.getRecipeTitle();
//
//                boolean isSaved = detail.clickSaveAndVerifyToast();
//
//                if (isSaved && !savedTitles.contains(title)) {
//                    savedTitles.add(title);
//                    savedCount++;
//                }
//
//                detail.clickBack();
//
//                // 🔥 FIX CHÍNH Ở ĐÂY
//                search.waitForResultOrEmpty();   // ⬅️ THÊM DÒNG NÀY
//
//                search.slowSwipeDownOnScreen(1);
//
//            } catch (Exception e) {
//                detail.clickBack();
//            }
//        }
//
//        // 👉 thoát search về Home
//        search.clickBack();
//
//        // 👉 vào Kho công thức
//        profile.clickTabProfile();
//        profile.openSavedRecipes();
//
////        // 👉 verify đơn giản theo title --> BỎ CÁCH NÀY ĐI
////        for (int i = 0; i < savedTitles.size(); i++) {
////
////            // 🔥 LẤY NGƯỢC
////            String expected = savedTitles.get(savedTitles.size() - 1 - i);
////
////            String actual = saved.getTitleAt(i);
////
////            Assert.assertTrue(
////                    actual.contains(expected),
////                    "❌ Sai thứ tự tại index " + i +
////                            " | Expected: " + expected +
////                            " | Actual: " + actual
////            );
////        }
//
//        // =========================
//        // 🔥 VERIFY TOP 1 (QUAN TRỌNG)
//        // =========================
//
//        String newest = savedTitles.get(savedTitles.size() - 1); // item save cuối
//        String actualTop = saved.getTitleAt(0); // item đầu list
//
//        System.out.println("EXPECTED NEWEST: " + newest);
//        System.out.println("ACTUAL TOP: " + actualTop);
//
//        Assert.assertTrue(
//                actualTop.contains(newest),
//                "❌ Item mới nhất không nằm trên cùng" +
//                        "\nExpected: " + newest +
//                        "\nActual: " + actualTop
//        );
//    }

//    // =========================
//    // TC_05 - ICON STATE (NO TOGGLE)
//    // =========================
//    @Test(priority = 5)
//    public void RecipeSave_TC_05() {
//
//        login();
//
//        searchFlow.searchByTag("gà");
//        search.clickResultAt(0);
//
//        detail.waitForLoaded();
//
//        detail.clickSaveAndVerifyToast();
//        String title = detail.getRecipeTitle();
//
//        detail.clickBack();
//
//        // mở lại
//        search.clickResultAt(0);
//        detail.waitForLoaded();
//
//        boolean isStillSaved = detail.isSaveStatePersist();
//
//        Assert.assertTrue(isStillSaved, "❌ Icon không giữ trạng thái đã lưu");
//    }

//    // =========================
//    // TC_06 - SAVE BEFORE LOGIN
//    // =========================
//    @Test(priority = 6)
//    public void RecipeSave_TC_06() {
//
//        // ❌ KHÔNG login ở đầu
//
//        searchFlow.searchByTag("gà");
//
//        // 👉 Click 1 item
//        search.clickResultAt(0);
//
//        detail.waitForLoaded();
//
//        String title = detail.getRecipeTitle();
//
//        // 👉 Save khi chưa login
//        detail.clickSaveAndVerifyToast();
//
//        detail.clickBack();
//        search.clickBack();
//
//        // =========================
//        // 👉 LOGIN SAU
//        // =========================
//        login();
//
//        // =========================
//        // 👉 VÀO KHO CÔNG THỨC
//        // =========================
//        profile.clickTabProfile();
//        profile.openSavedRecipes();
//
//        // =========================
//        // 👉 VERIFY
//        // =========================
//        String actualTop = saved.getTitleAt(0);
//
//        Assert.assertTrue(
//                actualTop.contains(title),
//                "❌ Công thức không được lưu sau khi login"
//        );
//    }
    // =========================
    // TC_07 - INTERACTION LIKE
    // =========================
//    @Test(priority = 7,
//            description = "RecipeLike_TC_01 - Click like -> icon đổi trạng thái + số like tăng")
//    public void RecipeLike_TC_01() {
//
//        login();
//
//        // 👉 mở 1 công thức ---> Nhớ khi test phải đổi món kêywword
//        searchFlow.searchByTag("Bánh mỳ");
//        search.clickResultAt(0);
//
//        detail.waitForLoaded();
//
//        int before = detail.getLikeCount();
//
//        detail.clickLike();
//
//        int after = before;
//
//        for (int i = 0; i < 5; i++) {
//            WaitingHelper.sleepSeconds(1);
//            after = detail.getLikeCount();
//            if (after > before) break;
//        }
//
//        Assert.assertTrue(
//                after > before,
//                "❌ Like không tăng | Before: " + before + " | After: " + after
//        );
//        AllureHelper.attachScreenshot("LIKE SUCCESS");
//    }

//     // =========================
//     // TC COMMENT
//    // =========================
//     @Test(priority = 8,
//             description = "RecipeComment_TC_01 - Kiểm tra nhập thêm bình luận click gửi bình luận xuất hiện với tên user, avatar, timestamp")
//     public void RecipeComment_TC_01() {
//
//         openRecipeDetail(); // 🔥 FIX
//
//         String comment = "Auto test comment " + System.currentTimeMillis();
//
//         detail.enterComment(comment);
//         detail.clickSendComment();
//
//         boolean isDisplayed = detail.isCommentDisplayed(comment);
//
//         Assert.assertTrue(isDisplayed, "❌ Comment không hiển thị");
//
//     }
//    @Test(priority = 9,
//            description = "RecipeComment_TC_02 - Kiểm tra bình luận text/emoji/ký tự đặc biệt hiển thị đúng chính xác")
//    public void RecipeComment_TC_02() {
//
//        openRecipeDetail(); // 🔥 FIX
//
//        String comment = "Test 😍🔥 @#$%^&*()";
//
//        detail.enterComment(comment);
//        detail.clickSendComment();
//
//        Assert.assertTrue(
//                detail.isCommentDisplayed(comment),
//                "❌ Comment emoji không hiển thị đúng"
//        );
//    }
//    @Test(priority = 10,
//            description = "RecipeComment_TC_03 - Bình luận rỗng bằng dấu cách")
//    public void RecipeComment_TC_03() {
//
//        openRecipeDetail();
//
//        detail.enterComment("   ");
//        // 🔥 ĐÓNG KEYBOARD NGAY
//        try {
//            getDriver().hideKeyboard();
//        } catch (Exception ignored) {}
//
//        detail.clickSendComment();
//
//        // 🔥 BẮT TOAST NGAY LẬP TỨC (KHÔNG SLEEP)
//        boolean errorDisplayed = false;
//
//        try {
//            getDriver().findElement(
//                    By.xpath("//android.widget.Toast[@text='Vui lòng thử lại']")
//            );
//            AllureHelper.attachScreenshot("Toast Displayed");
//            errorDisplayed = true;
//        } catch (Exception e) {
//            errorDisplayed = false;
//        }
//
//        Assert.assertTrue(errorDisplayed, "❌ Không hiển thị toast 'Vui lòng thử lại'");
//    }
//
//    @Test(priority = 11,
//            description = "RecipeComment_TC_04 - Bình luận dài hiển thị toast lỗi")
//    public void RecipeComment_TC_04() {
//
//        openRecipeDetail();
//
//        String longComment = "a".repeat(2100);
//
//        detail.enterComment(longComment);
//
//        // 🔥 đóng keyboard
//        try {
//            getDriver().hideKeyboard();
//        } catch (Exception ignored) {}
//
//        detail.clickSendComment();
//
//        // 🔥 bắt toast NGAY
//        boolean errorDisplayed = false;
//
//        try {
//            getDriver().findElement(
//                    By.xpath("//android.widget.Toast[@text='Vui lòng thử lại']")
//            );
//            AllureHelper.attachScreenshot("Toast Displayed");
//            errorDisplayed = true;
//        } catch (Exception e) {
//            errorDisplayed = false;
//        }
//
//        Assert.assertTrue(errorDisplayed, "❌ Không hiển thị toast khi comment quá dài");
//    }
//
//    @Test(priority = 12,
//            description = "RecipeComment_TC_05 - Kiểm tra bình luận khi chưa login")
//    public void RecipeComment_TC_05() {
//
//        // ❌ Không login
//
//        searchFlow.searchByTag("cà phê");
//        search.clickResultAt(0);
//
//        detail.waitForLoaded();
//
//        search.slowSwipeDownOnScreen(1);
//
//        // ✅ CHỈ VERIFY MESSAGE (KHÔNG COMMENT)
//        Assert.assertTrue(
//                detail.isLoginRequiredMessageDisplayed(),
//                "❌ Không hiển thị message yêu cầu đăng nhập"
//        );
//    }

    //=========================
    //   SHARE TEST CASES
    //=========================
    //   TC_01 - OPEN SHARE DIALOG
//    @Test(priority = 13,
//            description = "RecipeShare_TC_01 - Click share hiển thị native dialog")
//    public void RecipeShare_TC_01() {
//
//        openRecipeDetail();
//
//        detail.clickShare();
//
//        Assert.assertTrue(
//                detail.isShareOptionsDisplayed(),
//                "❌ Share dialog không hiển thị"
//        );
//
//        AllureHelper.attachScreenshot("SHARE DIALOG");
//    }
    // TC_02 - SHARE ZALO
//    @Test(priority = 14,
//            description = "RecipeShare_TC_02 - Share qua Zalo thành công")
//    public void RecipeShare_TC_02() {
//
//        openRecipeDetail();
//
//        boolean result = shareFlow.shareViaZalo();
//
//        Assert.assertTrue(result, "❌ Share Zalo thất bại");
//    }

    // TC_03 - SHARE MESSENGER
//    @Test(priority = 15,
//            description = "RecipeShare_TC_03 - Share qua Messenger thành công")
//    public void RecipeShare_TC_03() {
//
//        openRecipeDetail();
//
//        boolean result = shareFlow.shareViaMessenger();
//
//        Assert.assertTrue(result, "❌ Share Messenger thất bại");
//    }

    // TC_04 - SHARE GMAIL
//    @Test(priority = 16,
//            description = "RecipeShare_TC_04 - Share qua Gmail thành công")
//    public void RecipeShare_TC_04() {
//
//        openRecipeDetail();
//
//        boolean result = shareFlow.shareViaGmail();
//
//        Assert.assertTrue(result, "❌ Share Gmail thất bại");
//    }

    // TC_05 - MULTI SHARE
//    @Test(priority = 17,
//            description = "RecipeShare_TC_05 - Share nhiều lần qua Zalo, Messenger, Gmail")
//    public void RecipeShare_TC_05() {
//
//        openRecipeDetail();
//
//        // =========================
//        // ZALO
//        // =========================
//        Assert.assertTrue(shareFlow.shareViaZalo(), "❌ Zalo fail");
//
//        // 🔥 FIX CỨNG: ép back về app
//        for (int i = 0; i < 3; i++) {
//            try {
//                getDriver().navigate().back();
//                WaitingHelper.sleepSeconds(1);
//
//                if (detail.isRecipeDetailDisplayed()) break;
//
//            } catch (Exception ignored) {}
//        }
//
//        detail.waitForLoaded();
//
//        // =========================
//        // MESSENGER
//        // =========================
//        Assert.assertTrue(shareFlow.shareViaMessenger(), "❌ Messenger fail");
//
//        // 🔥 FIX: quay lại app
//        for (int i = 0; i < 3; i++) {
//            try {
//                getDriver().navigate().back();
//                WaitingHelper.sleepSeconds(1);
//
//                if (detail.isRecipeDetailDisplayed()) break;
//
//            } catch (Exception ignored) {}
//        }
//
//        detail.waitForLoaded();
//
//        // =========================
//        // GMAIL
//        // =========================
//        Assert.assertTrue(shareFlow.shareViaGmail(), "❌ Gmail fail");
//    }
    // TC_06 - SHARE WITHOUT LOGIN
    @Test(priority = 18,
            description = "RecipeShare_TC_06 - Share khi chưa login")
    public void RecipeShare_TC_06() {

        // ❌ Không login

        searchFlow.searchByTag("cà phê");
        search.clickResultAt(0);

        detail.waitForLoaded();

        detail.clickShare();

        boolean result =
                detail.isShareOptionsDisplayed()
                        || detail.isLoginRequiredMessageDisplayed();

        Assert.assertTrue(
                result,
                "❌ Không hiển thị share hoặc yêu cầu login"
        );
    }
}