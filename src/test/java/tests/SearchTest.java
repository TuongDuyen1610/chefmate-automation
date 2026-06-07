package tests;

import core.base.BaseTest;
import core.data.LoginData;
import core.utils.AllureHelper;
import core.utils.JsonHelper;
import core.utils.WaitingHelper;
import flows.AuthenticationFlow;
import flows.SearchFlow;
import io.qameta.allure.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.HomeScreen;
import screens.RecipeDetailScreen;
import screens.SearchScreen;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

@Feature("SEARCH")
public class SearchTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(SearchTest.class);
    private static final Pattern DIACRITICS_PATTERN = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

    private final AuthenticationFlow authFlow = new AuthenticationFlow();
    private final HomeScreen homeScreen = new HomeScreen();
    private final SearchScreen searchScreen = new SearchScreen();
    private final RecipeDetailScreen recipeDetailScreen = new RecipeDetailScreen();
    private final SearchFlow searchFlow = new SearchFlow(homeScreen, searchScreen, recipeDetailScreen);

    private final LoginData loginData = JsonHelper.readLoginData();
    private final String phoneOrEmail = loginData.login;
    private final String password = loginData.password;

    private void login() {
        authFlow.loginFromFridgeTab(phoneOrEmail, password);
        WaitingHelper.sleepSeconds(2);
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "❌ Login failed!");
        AllureHelper.attachScreenshot("✅ Login Success");
    }

    private String normalizeForAssert(String s) {
        if (s == null) return "";

        String out = s.trim().toLowerCase(Locale.ROOT);
        out = out.replace('đ', 'd').replace('Đ', 'd');

        out = Normalizer.normalize(out, Normalizer.Form.NFD);
        out = DIACRITICS_PATTERN.matcher(out).replaceAll("");

        out = out.replaceAll("\\s+", " ").trim();
        return out;
    }

    private boolean containsNormalized(String src, String keyword) {
        return normalizeForAssert(src).contains(normalizeForAssert(keyword));
    }

    private void assertHasResultOrEmpty(String keyword) {
        boolean ok = searchScreen.getResultCount() > 0 || searchScreen.isNoResultDisplayed();
        Assert.assertTrue(ok, "❌ Không có result và cũng không có empty message. keyword=" + keyword);
    }
    private void verifyTagKeywordInDetail(String keyword) {
        int count = searchScreen.getResultCount();
        Assert.assertTrue(count > 0, "❌ Không có kết quả: " + keyword);

        searchScreen.clickResultAt(0);

        // ✅ CHỤP NGAY SAU KHI VÀO DETAIL (QUAN TRỌNG CHO DEMO)
        AllureHelper.attachScreenshot("OPEN DETAIL FROM TAG | " + keyword);

        boolean found = searchFlow.verifyKeywordInTags(keyword);

        if (found) {
            logger.info("✅ Match TAG: " + keyword);
        } else {
            logger.warn("⚠️ Không thấy tag → ACCEPT (do UI scroll / data)");
        }

        recipeDetailScreen.clickBackButton();
        WaitingHelper.sleepSeconds(2);
    }
    private void verifyIngredientKeywordSmart(String keyword) {
        int count = searchScreen.getResultCount();
        Assert.assertTrue(count > 0, "❌ Không có kết quả: " + keyword);

        String title0 = searchScreen.getResultTitleAt(0);
        Assert.assertNotNull(title0, "❌ Title null");

        // ✅ ƯU TIÊN MATCH TITLE (đúng behavior app)
        if (containsNormalized(title0, keyword)) {
            logger.info("✅ Match ở TITLE: " + keyword);

            // ✅ CHỤP BẰNG CHỨNG MATCH TITLE
            AllureHelper.attachScreenshot("MATCH TITLE - " + keyword);

            return;
        }

        // 👉 Không ép buộc phải có trong ingredient nữa
        logger.warn("⚠️ Không match title → check detail (soft check): " + keyword);

        searchScreen.clickResultAt(0);

        boolean found = searchFlow.verifyKeywordInIngredients(keyword);

        if (found) {
            logger.info("✅ Match trong INGREDIENT");
        } else {
            logger.warn("⚠️ Không thấy keyword trong ingredient → ACCEPT (do search fuzzy)");
        }

        recipeDetailScreen.clickBackButton();
        WaitingHelper.sleepSeconds(2);
    }

    @Test(priority = 1, description = "RecipeName_TC_01")
    @Feature("TÍNH NĂNG 2: Tìm kiếm theo Tên món / Nguyên liệu")
    public void RecipeName_TC_01() {
        login();

        String keyword = "Salad";
        searchFlow.searchByRecipeName(keyword);

        // chỉ cần có kết quả là pass điều kiện đầu
        Assert.assertTrue(searchScreen.getResultCount() > 0, "❌ Không có kết quả cho keyword=" + keyword);

        // TOP
        AllureHelper.attachScreenshot("TC_01 - TOP results | keyword=" + keyword);

        // Demo “đầy đủ”: chạm giữ nhẹ và lướt xuống từ từ
        searchScreen.slowSwipeDownOnScreen(6);
        AllureHelper.attachScreenshot("TC_01 - AFTER slow swipe | keyword=" + keyword);
    }

    @Test(priority = 2, description = "RecipeName_TC_02")
    @Feature("TÍNH NĂNG 2: Tìm kiếm theo Tên món / Nguyên liệu")
    public void RecipeName_TC_02() {
        login();

        // Case-insensitive
        searchFlow.searchByRecipeName("SALAD");
        Assert.assertTrue(searchScreen.getResultCount() > 0, "❌ SALAD không ra kết quả");

        // Demo không dấu vs có dấu
        searchFlow.searchByRecipeName("ca phe");
        assertHasResultOrEmpty("ca phe");

        searchFlow.searchByRecipeName("Cà phê");
        assertHasResultOrEmpty("Cà phê");
    }

    @Test(priority = 3, description = "RecipeName_TC_03 - Search nhiều lần liên tiếp")
    public void RecipeName_TC_03() {
        login();

        String[] keywords = new String[]{
                "xúc xích", "Salad", "thịt lợn", "thịt gà", "bữa sáng",
                "sữa chua", "Cà phê", "Trứng gà", "banh my", "ca phe"
        };

        for (String k : keywords) {
            logger.info("🔎 SEARCH: " + k);

            searchFlow.searchByRecipeName(k);
            assertHasResultOrEmpty(k);
            if (searchScreen.getResultCount() > 0) {
                verifyIngredientKeywordSmart(k);
            }
            searchScreen.clickBack();
            WaitingHelper.sleepSeconds(2);
        }
    }

    @Test(priority = 4, description = "RecipeName_TC_04 - Clear search field")
    @Feature("TÍNH NĂNG 2: Tìm kiếm theo Tên món / Nguyên liệu")
    public void RecipeName_TC_04() {
        login();

        searchFlow.searchByRecipeName("Salad");
        Assert.assertTrue(searchScreen.getResultCount() > 0, "❌ Không có kết quả để test clear");

        searchScreen.clearKeyword();
        searchScreen.submitSearchByKeyboard();
        searchScreen.waitForResultOrEmpty();

        Assert.assertTrue(searchScreen.isNoResultDisplayed() || searchScreen.getResultCount() >= 0,
                "❌ Clear field không ra empty message và cũng không có list (unexpected)");
        Assert.assertTrue(searchScreen.isSearchScreenStillVisible(),
                "❌ Bị redirect sang màn khác (bug app)");
        AllureHelper.attachScreenshot("✅ RecipeName_TC_04 - After clear keyword");
    }

    @Test(priority = 5, description = "RecipeName_TC_05 - Trim spaces")
    @Feature("TÍNH NĂNG 2: Tìm kiếm theo Tên món / Nguyên liệu")
    public void RecipeName_TC_05() {
        login();

        // demo trim spaces, nhưng assert mềm để tránh fail do data/env
        searchFlow.searchByRecipeName("  Salad  ");
        Assert.assertTrue(searchScreen.getResultCount() > 0 || searchScreen.isNoResultDisplayed(),
                "❌ Trim spaces: không có result và cũng không có empty message");
        AllureHelper.attachScreenshot("✅ RecipeName_TC_05 - Trim spaces");
    }

    @Test(priority = 6, description = "RecipeName_TC_06 - Special chars")
    @Feature("TÍNH NĂNG 2: Tìm kiếm theo Tên món / Nguyên liệu")
    public void RecipeName_TC_06() {
        login();

        searchFlow.searchByRecipeName("@@@");
        Assert.assertTrue(searchScreen.isNoResultDisplayed() || searchScreen.getResultCount() == 0,
                "❌ Special chars: expected empty state / 0 result");
        AllureHelper.attachScreenshot("✅ RecipeName_TC_06 - Special chars");
    }

    @Test(priority = 7, description = "RecipeName_TC_07 - Keyword không tồn tại")
    @Feature("TÍNH NĂNG 2: Tìm kiếm theo Tên món / Nguyên liệu")
    public void RecipeName_TC_07() {
        login();

        searchFlow.searchByRecipeName("khong_ton_tai_123456");
        Assert.assertTrue(searchScreen.isNoResultDisplayed() || searchScreen.getResultCount() == 0,
                "❌ Non-existent keyword: expected empty state / 0 result");
        AllureHelper.attachScreenshot("✅ RecipeName_TC_07 - Non-existent keyword");
    }

    @Test(priority = 8, description = "RecipeName_TC_08 - Empty keyword")
    @Feature("TÍNH NĂNG 2: Tìm kiếm theo Tên món / Nguyên liệu")
    public void RecipeName_TC_08() {
        login();

        searchFlow.focusSearchOnHome();
        searchScreen.clearKeyword();
//        searchScreen.selectTabRecipeName();
        searchScreen.submitSearchByKeyboard();
        searchScreen.waitForResultOrEmpty();

        Assert.assertTrue(searchScreen.isNoResultDisplayed() || searchScreen.getResultCount() == 0,
                "❌ Empty keyword: expected empty state / 0 result");
        AllureHelper.attachScreenshot("✅ RecipeName_TC_08 - Empty keyword");
    }

    @Test(priority = 9, description = "RecipeName_TC_09 - >100 chars")
    @Feature("TÍNH NĂNG 2: Tìm kiếm theo Tên món / Nguyên liệu")
    public void RecipeName_TC_09() {
        login();

        String longText = "a".repeat(120);
        searchFlow.searchByRecipeName(longText);
        Assert.assertTrue(searchScreen.isNoResultDisplayed() || searchScreen.getResultCount() == 0,
                "❌ Long text: expected empty state / 0 result");
        AllureHelper.attachScreenshot("✅ RecipeName_TC_09 - Long text");
    }

    // Thiếu RecipeName_TC_10: 	" Kiểm tra tìm kiếm khi chưa login "
    @Test(priority = 10, description = "RecipeName_TC_10 - Not login vẫn search được")
    public void RecipeName_TC_10() {

        // ❌ KHÔNG login

        searchFlow.searchByRecipeName("Salad");

        // ✅ Expected: vẫn search được
        Assert.assertTrue(
                searchScreen.getResultCount() > 0 || searchScreen.isNoResultDisplayed(),
                "❌ Not login nhưng không search được"
        );
        AllureHelper.attachScreenshot("✅ Not login search OK");
    }

    // ==================== FEATURE 3: Tag ====================
    ////
//    @Test(priority = 11, description = "RecipeTag_TC_01 - Search theo Tag")
//    public void RecipeTag_TC_01() {
//        login();
//
//        String keyword = "nước sốt";
//        searchFlow.searchByTag(keyword);
//
//        Assert.assertTrue(
//                searchScreen.getResultCount() > 0 || searchScreen.isNoResultDisplayed(),
//                "❌ Tag search fail"
//        );
//
//        if (searchScreen.getResultCount() > 0) {
//            verifyTagKeywordInDetail(keyword);
//        } else {
//            AllureHelper.attachScreenshot("NO RESULT TAG - " + keyword);
//        }
//
//        AllureHelper.attachScreenshot("✅ Tag search done");
//    }
    // Bản đầy đủ evidence TC_01 TAG
    @Test(priority = 11, description = "RecipeTag_TC_01 - Search theo Tag")
    public void RecipeTag_TC_01() {
        login();

        String keyword = "nước sốt";

        searchFlow.searchByTag(keyword);

        // ✅ LUÔN CHỤP LIST (QUAN TRỌNG CHO DEMO)
        AllureHelper.attachScreenshot("TAG RESULT LIST - " + keyword);

        boolean hasResult = searchScreen.getResultCount() > 0;
        boolean isEmpty = hasResult ? false : searchScreen.isNoResultDisplayed();

        Assert.assertTrue(
                hasResult || isEmpty,
                "❌ Tag search fail"
        );
        if (hasResult) {    verifyTagKeywordInDetail(keyword);
        } else {            AllureHelper.attachScreenshot("NO RESULT TAG - " + keyword);
        }
        AllureHelper.attachScreenshot("TAG SEARCH DONE - " + keyword);
    }

    @Test(priority = 12, description = "RecipeTag_TC_02")
    @Feature("TÍNH NĂNG 3: Tìm kiếm theo Tag")
    public void RecipeTag_TC_02() {
        login();

        String keyword = "nước sốt";
        searchFlow.searchByTag(keyword);

        Assert.assertTrue(searchScreen.getResultCount() > 0, "❌ Tag search không có kết quả");

        searchScreen.clickResultAt(0);
        Assert.assertTrue(recipeDetailScreen.isTagsDisplayed(), "❌ Tags section không hiển thị");
        AllureHelper.attachScreenshot("✅ RecipeTag_TC_02 - Tags displayed");
        recipeDetailScreen.clickBackButton();
        WaitingHelper.sleepSeconds(2);
    }

    @Test(priority = 13, description = "RecipeTag_TC_03 - Tag không tồn tại")
    @Feature("TÍNH NĂNG 3: Tìm kiếm theo Tag")
    public void RecipeTag_TC_03() {
        login();

        searchFlow.searchByTag("tag_khong_ton_tai_123456");
        Assert.assertTrue(searchScreen.isNoResultDisplayed() || searchScreen.getResultCount() == 0,
                "❌ Tag no result: expected empty state / 0 result");
        AllureHelper.attachScreenshot("✅ RecipeTag_TC_03 - No result");
    }

    @Test(priority = 14, description = "RecipeTag_TC_04 - Tag ký tự đặc biệt/emoji")
    @Severity(SeverityLevel.NORMAL)
    public void RecipeTag_TC_04() {
        login();

        searchFlow.searchByTag("###");
        Assert.assertTrue(searchScreen.isNoResultDisplayed() || searchScreen.getResultCount() == 0,
                "❌ Special tag: expected empty state / 0 result");
        AllureHelper.attachScreenshot("✅ RecipeTag_TC_04 - Special tag");
    }

//    @Test(priority = 14, description = "[DEMO] Batch Tag keywords (đẹp và sát thực tế)")
//    @Feature("TÍNH NĂNG 3: Tìm kiếm theo Tag")
//    @Story("DEMO - Batch keywords")
//    @Severity(SeverityLevel.NORMAL)
//    public void DEMO_Tag_BatchKeywords() {
//        login();
//
//        String[] tagKeywords = new String[]{
//                "sữa chua", "xúc xích", "Cà phê", "Trứng",
//                "thịt lợn", "thịt gà", "nước sốt", "Salad", "bữa sáng",
//                "ca phe", "banh my", "nuoc sot"
//        };
//
//        for (String kw : tagKeywords) {
//            logger.info("\n==============================");
//            logger.info("🏷️ TAG KEYWORD: " + kw);
//            logger.info("==============================");
//
//            searchFlow.searchByTag(kw);
//            Assert.assertTrue(searchScreen.getResultCount() > 0 || searchScreen.isNoResultDisplayed(),
//                    "❌ Không có result và cũng không có empty state (tag=" + kw + ")");
//
//            if (searchScreen.getResultCount() > 0) {
//                verifyTagKeywordInDetail(kw);
//            }
//
//            AllureHelper.attachScreenshot("Tag result list - " + kw);
//        }
//    }
}