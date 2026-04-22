package flows;

import core.utils.AllureHelper;
import core.utils.WaitingHelper;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import screens.HomeScreen;
import screens.RecipeDetailScreen;
import screens.SearchScreen;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public class SearchFlow {
    private static final Logger logger = LoggerFactory.getLogger(SearchFlow.class);
    private static final Pattern DIACRITICS_PATTERN = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

    private final HomeScreen homeScreen;
    private final SearchScreen searchScreen;
    private final RecipeDetailScreen recipeDetailScreen;

    public SearchFlow(HomeScreen homeScreen, SearchScreen searchScreen, RecipeDetailScreen recipeDetailScreen) {
        this.homeScreen = homeScreen;
        this.searchScreen = searchScreen;
        this.recipeDetailScreen = recipeDetailScreen;
    }

    @Step("Focus search on Home (click EditText directly)")
    public void focusSearchOnHome() {
        AllureHelper.step("Focus search on Home (click EditText directly)");
        homeScreen.isHomeDisplayed();
        searchScreen.waitForSearchUiReady();   // wait searchField visible
        searchScreen.focusSearchField();       // click EditText
        WaitingHelper.sleepSeconds(1);
    }

    @Step("Search by 'Tên món' with keyword: {keyword}")
    public void searchByRecipeName(String keyword) {
        focusSearchOnHome();

        // ✅ Theo đúng thứ tự chị yêu cầu: NHẬP keyword -> CHỌN tab -> SUBMIT
        searchScreen.clearKeyword();
        searchScreen.enterKeyword(keyword);
        searchScreen.selectTabRecipeName();

        searchScreen.submitSearchByKeyboard();
        searchScreen.waitForResultOrEmpty();
        // ✅ CHỤP LIST NGAY LÚC UI ỔN ĐỊNH
        AllureHelper.attachScreenshot("Result LIST - " + keyword);
    }

    @Step("Search by 'Tag' with keyword: {keyword}")
    public void searchByTag(String keyword) {
        focusSearchOnHome();

        searchScreen.clearKeyword();
        searchScreen.enterKeyword(keyword);
        searchScreen.selectTabTag();

        searchScreen.submitSearchByKeyboard();
        searchScreen.waitForResultOrEmpty();
        AllureHelper.attachScreenshot("Result - Tag | " + keyword);
    }

    @Step("Verify keyword exists in Ingredients on detail: {keyword}")
    public boolean verifyKeywordInIngredients(String keyword) {
        try {
            recipeDetailScreen.waitForRecipeDetailDisplayed();
            recipeDetailScreen.clickIngredientTab();

            // ✅ ẢNH FULL TAB
            AllureHelper.attachScreenshot("Ingredients tab - FULL | " + keyword);

            int count = recipeDetailScreen.getIngredientCount();
            if (count <= 0) return false;

            String k = normalize(keyword);

            for (int i = 0; i < count; i++) {
                String ing = recipeDetailScreen.getIngredientAt(i);

                if (normalize(ing).contains(k)) {

                    // ✅ LOG MATCH
                    logger.info("✅ MATCH INGREDIENT: " + ing + " | keyword=" + keyword);

                    // ✅ ẢNH MATCH (QUAN TRỌNG NHẤT)
                    AllureHelper.attachScreenshot("MATCH Ingredient | keyword=" + keyword + " | value=" + ing);

                    return true;
                }
            }
            // ❌ KHÔNG MATCH → vẫn phải có evidence
            AllureHelper.attachScreenshot("NO MATCH Ingredient | " + keyword);
            return false;

        } catch (Exception e) {
            logger.error("verifyKeywordInIngredients error: " + e.getMessage());
            return false;
        }
    }

    @Step("Verify keyword exists in Tags on detail: {keyword}")
    public boolean verifyKeywordInTags(String keyword) {
        return recipeDetailScreen.verifyTagWithScroll(keyword);
    }

    private String normalize(String s) {
        if (s == null) return "";

        String out = s.trim().toLowerCase(Locale.ROOT);
        out = out.replace('đ', 'd').replace('Đ', 'd');
        out = Normalizer.normalize(out, Normalizer.Form.NFD);
        out = DIACRITICS_PATTERN.matcher(out).replaceAll("");
        out = out.replaceAll("\\s+", " ").trim();
        return out;
    }
}