package tests;

import core.base.BaseTest;
import core.utils.WaitingHelper;
import flows.AddRecipeFlow;
import flows.AuthenticationFlow;
import flows.SearchFlow;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.*;
import java.util.Arrays;
import java.util.List;

import static core.driver.DriverManager.getDriver;


public class AddRecipeTest extends BaseTest {

    HomeScreen home = new HomeScreen();
    SearchScreen search = new SearchScreen();
    RecipeDetailScreen detail = new RecipeDetailScreen();
    ProfileScreen profile = new ProfileScreen();
    SavedRecipesScreen saved = new SavedRecipesScreen();

    AddRecipeFlow addFlow = new AddRecipeFlow();
    AuthenticationFlow authFlow = new AuthenticationFlow();
    SearchFlow searchFlow = new SearchFlow(home, search, detail);
    AddRecipeScreen add = new AddRecipeScreen();

    // =========================
    // COMMON FLOW
    // =========================
    private void goToAddScreen() {

        authFlow.loginFromFridgeTab("duyentest@gmail.com", "123456");

        add.clickScreenAddRecipe();
    }

//    // =========================
//    // TC_01 - FORM LOAD
//    // =========================
    @Test(priority = 1)
    public void RecipeCreate_TC_01() {

        goToAddScreen();

        Assert.assertTrue(add.isDisplayed(), "❌ Không vào màn Add");

        Assert.assertTrue(
                addFlow.verifyFormLoaded(),
                "❌ Form không load đúng dữ liệu"
        );
    }

//    // =========================
//    // TC_02 - UPDATE FULL DATA
//    // =========================
    @Test(priority = 2)
    public void RecipeCreate_TC_02() {

        goToAddScreen();
        // ===== AVATAR =====
        add.clickAvatar();
        add.chooseImageFromGalleryEmulator(1);

        WaitingHelper.sleepSeconds(1);

        // ===== TAG =====
        List<String> tags = Arrays.asList("Cá", "Kem", "Trứng");

        add.addMultipleTags(tags);

        // ===== BASIC =====
        String name = "Gỏi cuốn " + System.currentTimeMillis();

        add.inputName(name);
        add.inputTime("30 phút");
        add.inputServing("2");

        try { getDriver().hideKeyboard(); } catch (Exception ignored) {}

        // ===== INGREDIENT =====
        List<List<String>> ingredients = Arrays.asList(
                Arrays.asList("Bánh tráng", "10", "cái"),
                Arrays.asList("Tôm", "200", "gram"),
                Arrays.asList("Thịt ba chỉ", "300", "gram")
//                Arrays.asList("Bún", "500", "gram"),
//                Arrays.asList("Rau sống", "1", "bó"),
//                Arrays.asList("Nước mắm", "50", "ml")
        );

        add.inputIngredients(ingredients);
        try { getDriver().hideKeyboard(); } catch (Exception ignored) {}

        // ===== STEP =====
        List<String> steps = Arrays.asList(
                "Luộc tôm và thịt",
                "Chuẩn bị rau và bún",
                "Nhúng bánh tráng",
                "Xếp nguyên liệu"

    //                "Cuốn chặt tay",
    //                "Pha nước chấm"
        );
        add.inputSteps(steps);

        // ===== POST =====
        add.clickPost();

        // ===== VERIFY =====
        profile.clickTabProfile();
        profile.openSavedRecipes();
        Assert.assertTrue(
                saved.isRecipeExist(name, ""),
                "❌ Không add thành công"
        );
    }
    // =========================
    // TC_03 - REQUIRED FIELD
    // =========================
    @Test(priority = 3)
    public void RecipeCreate_TC_03() {

        goToAddScreen();

        addFlow.clearRequiredField();

        // 🔥 ĐẢM BẢO KHÔNG BỊ KEYBOARD CHẶN
        try {
            getDriver().hideKeyboard();
        } catch (Exception ignored) {}

        add.slowSwipeDownOnScreen(2);

        add.clickPost(); // ✅ FIX

        Assert.assertTrue(
                addFlow.isRequiredToast(),
                "❌ Không hiển thị toast required"
        );
    }
    // =========================
    // TC_04 - EMPTY INGREDIENT
    // =========================
        @Test(priority = 4)
        public void RecipeCreate_TC_04() {

        goToAddScreen();
        // ===== AVATAR =====
        add.clickAvatar();
        add.chooseImageFromGalleryEmulator(1);

        WaitingHelper.sleepSeconds(1);
        // ===== TAG =====
        List<String> tags = Arrays.asList("Cá");

        add.addMultipleTags(tags);
        // ===== BASIC =====
        String name = "Gỏi cuốn " + System.currentTimeMillis();

        add.inputName(name);
        add.inputTime("30 phút");
        add.inputServing("2");

        try { getDriver().hideKeyboard(); } catch (Exception ignored) {}
        add.slowSwipeDownOnScreen(2);
        // ===== INGREDIENT =====
        List<List<String>> ingredients = Arrays.asList(
                Arrays.asList("", "", ""),
                Arrays.asList("", "", ""),
                Arrays.asList("", "", "")
        );
        add.inputIngredients(ingredients);
        try { getDriver().hideKeyboard(); } catch (Exception ignored) {}
        // ===== STEP =====
        List<String> steps = Arrays.asList(
                "Luộc tôm và thịt",
                "Chuẩn bị rau và bún",
                "Nhúng bánh tráng",
                "Xếp nguyên liệu"
        );
        add.inputSteps(steps);

        // ===== POST =====
        add.clickPost();

        Assert.assertTrue(
                addFlow.isInvalidToast(),
                "❌ Update ingredient lỗi"
        );
    }
    // =========================
    // TC_05 - EMPTY STEP
    // =========================
    @Test(priority = 5)
    public void RecipeCreate_TC_05() {
        goToAddScreen();
        // ===== AVATAR =====
        add.clickAvatar();
        add.chooseImageFromGalleryEmulator(1);

        WaitingHelper.sleepSeconds(1);
        // ===== TAG =====
        List<String> tags = Arrays.asList("Cá");

        add.addMultipleTags(tags);
        // ===== BASIC =====
        String name = "Gỏi cuốn " + System.currentTimeMillis();

        add.inputName(name);
        add.inputTime("30 phút");
        add.inputServing("2");

        try { getDriver().hideKeyboard(); } catch (Exception ignored) {}
        add.slowSwipeDownOnScreen(2);
        // ===== INGREDIENT =====
        List<List<String>> ingredients = Arrays.asList(
                Arrays.asList("Bánh tráng", "10", "cái"),
                Arrays.asList("Tôm", "200", "gram"),
                Arrays.asList("Thịt ba chỉ", "300", "gram"));

        add.inputIngredients(ingredients);
        try { getDriver().hideKeyboard(); } catch (Exception ignored) {}
        // ===== STEP =====
        List<String> steps = Arrays.asList(
                "",
                "",
                "",
                ""
        );
        add.inputSteps(steps);

        // ===== POST =====
        add.clickPost();

        Assert.assertTrue(
                addFlow.isRequiredToast(),
                "❌ Update steps lỗi"
        );
    }

    // =========================
    // TC_06 - UPDATE FULL DATA STATUS NOT LOG IN
    // =========================
    @Test(priority = 6)
    public void RecipeCreate_TC_06() {
        add.clickScreenAddRecipe();//
//         =====AVATAR == == =
        add.clickAvatar();
        add.chooseImageFromGalleryEmulator(1);

        WaitingHelper.sleepSeconds(1);

        // ===== TAG =====
        List<String> tags = Arrays.asList("Cá");

        add.addMultipleTags(tags);

        // ===== BASIC =====
        String name = "Gỏi cuốn " + System.currentTimeMillis();

        add.inputName(name);
        add.inputTime("30 phút");
        add.inputServing("2");

        try {
            getDriver().hideKeyboard();
        } catch (Exception ignored) {
        }

        // ===== INGREDIENT =====
        List<List<String>> ingredients = Arrays.asList(
                Arrays.asList("Bánh tráng", "10", "cái"),
                Arrays.asList("Tôm", "200", "gram"),
                Arrays.asList("Thịt ba chỉ", "300", "gram")
        );

        add.inputIngredients(ingredients);
        try {
            getDriver().hideKeyboard();
        } catch (Exception ignored) {
        }

        // ===== STEP =====
        List<String> steps = Arrays.asList(
                "Luộc tôm và thịt",
                "Chuẩn bị rau và bún",
                "Nhúng bánh tráng",
                "Xếp nguyên liệu"
        );
        add.inputSteps(steps);

        // ===== POST =====
        add.clickPost();

        // ===== VERIFY =====
        profile.clickTabProfile();
        profile.openSavedRecipes();
        Assert.assertTrue(
                saved.isRecipeExist(name, ""),
                "❌ Không add thành công"
        );
    }
}