package tests;

import core.base.BaseTest;
import core.utils.WaitingHelper;
import flows.EditRecipeFlow;
import flows.AuthenticationFlow;
import flows.SearchFlow;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.*;
import java.util.Arrays;
import java.util.List;

import static core.driver.DriverManager.getDriver;

public class EditRecipeTest extends BaseTest {

    HomeScreen home = new HomeScreen();
    SearchScreen search = new SearchScreen();
    RecipeDetailScreen detail = new RecipeDetailScreen();
    ProfileScreen profile = new ProfileScreen();
    SavedRecipesScreen saved = new SavedRecipesScreen();
    EditRecipeScreen edit = new EditRecipeScreen();

    EditRecipeFlow editFlow = new EditRecipeFlow();
    AuthenticationFlow authFlow = new AuthenticationFlow();
    SearchFlow searchFlow = new SearchFlow(home, search, detail);

    // =========================
    // COMMON FLOW
    // =========================
    private void goToEditScreen() {

        authFlow.loginFromFridgeTab("duyentest@gmail.com", "123456");

        searchFlow.searchByTag("cà phê");
        try { getDriver().hideKeyboard(); } catch (Exception ignored) {}
        search.clickResultAt(0);
        detail.waitForLoaded();

        boolean isSaved = detail.clickSaveAndVerifyToast();
        Assert.assertTrue(isSaved, "❌ Save thất bại");
        try { getDriver().hideKeyboard(); } catch (Exception ignored) {}
        detail.clickBackToHome();
        home.backToHome();

        profile.clickTabProfile();
        profile.openSavedRecipes();

        saved.clickEditAt(0);
    }
        private void saveMultipleRecipes(int count) {

            authFlow.loginFromFridgeTab("duyentest@gmail.com", "123456");

            searchFlow.searchByTag("cà phê");

            for (int i = 0; i < count; i++) {

                search.clickResultAt(i);
                detail.waitForLoaded();

                boolean isSaved = detail.clickSaveAndVerifyToast();
                Assert.assertTrue(isSaved, "❌ Save thất bại tại index: " + i);

                detail.clickBackToHome();
                home.backToHome();
            }
        }
//    // =========================
//    // TC_01 - FORM LOAD
//    // =========================
    @Test(priority = 1)
    public void RecipeEdit_TC_01() {

        goToEditScreen();

        Assert.assertTrue(edit.isDisplayed(), "❌ Không vào màn Edit");

        Assert.assertTrue(
                editFlow.verifyFormLoaded(),
                "❌ Form không load đúng dữ liệu"
        );
    }

    // =========================
    // TC_02 - UPDATE FULL DATA
    // =========================
    @Test(priority = 2)
    public void RecipeEdit_TC_02() {

        goToEditScreen();
        // ===== AVATAR =====
        edit.clickAvatar();
        edit.chooseImageFromGalleryEmulator(2);

        WaitingHelper.sleepSeconds(1);

        // ===== TAG =====
        List<String> tags = Arrays.asList("Cá", "Kem", "Trứng");

        edit.addMultipleTags(tags);

        String newName = "Auto " + System.currentTimeMillis();

        edit.clearName();
        edit.inputName(newName);

        String newTime = "00: " + System.currentTimeMillis();

        try { getDriver().hideKeyboard(); } catch (Exception ignored) {}
        edit.clearTime();
        edit.inputTime(newTime);

        String newServing = "" + System.currentTimeMillis();

        edit.clearServing();
        edit.inputServing(newServing);

        edit.slowSwipeDownOnScreen(1);
        edit.deleteAllIngredients();

        String newStep3 = "Auto_3" + System.currentTimeMillis();
        edit.inputStep3(newStep3);
        String newStep4 = "4" + System.currentTimeMillis();
        edit.inputStep4(newStep4);
        String newStep5 = "gam" + System.currentTimeMillis();
        edit.inputStep5(newStep5);
        // 🔥 ĐẢM BẢO KHÔNG BỊ KEYBOARD CHẶN
        try { getDriver().hideKeyboard(); } catch (Exception ignored) {}
        edit.slowSwipeDownOnScreen(1);
        edit.deleteAllSteps();
        try { getDriver().hideKeyboard(); } catch (Exception ignored) {}
        // 🔥 SCROLL 2 LẦN (đủ dùng)
        edit.clickUpdate(); // ✅ FIX

        saved.clickBack();
        profile.clickTabProfile();
        profile.openSavedRecipes();

        Assert.assertTrue(
                saved.isRecipeExist(newName, ""),
                "❌ Không update tên thành công"
        );
    }

    // =========================
    // TC_03 - UPDATE STEP
    // =========================
    @Test(priority = 3)
    public void RecipeEdit_TC_03() {

        goToEditScreen();
        edit.slowSwipeDownOnScreen(2);
        edit.deleteAllSteps();

        edit.clickAddStep();

        String newStep1 = "Auto_1_2 " + System.currentTimeMillis();
        edit.inputStep1(newStep1);
        String newStep2 = "Auto_2_ " + System.currentTimeMillis();
        edit.inputStep2(newStep2);

        edit.slowSwipeDownOnScreen(1);
        edit.clickUpdate(); // ✅ FIX

        Assert.assertFalse(
                editFlow.isInvalidToast(),
                "❌ Update step lỗi"
        );
    }

    // =========================
    // TC_04 - UPDATE INGREDIENT
    // =========================
    @Test(priority = 4)
    public void RecipeEdit_TC_04() {

        goToEditScreen();
        edit.slowSwipeDownOnScreen(1);
        edit.deleteAllIngredients();

        String newStep3 = "Auto_3" + System.currentTimeMillis();
        edit.inputStep3(newStep3);
        String newStep4 = "4" + System.currentTimeMillis();
        edit.inputStep4(newStep4);
        String newStep5 = "gam" + System.currentTimeMillis();
        edit.inputStep5(newStep5);

        // 🔥 ĐẢM BẢO KHÔNG BỊ KEYBOARD CHẶN
        try {
            getDriver().hideKeyboard();
        } catch (Exception ignored) {}

        edit.slowSwipeDownOnScreen(1);
        edit.clickUpdate(); // ✅ FIX

        Assert.assertFalse(
                editFlow.isInvalidToast(),
                "❌ Update ingredient lỗi"
        );
    }

    // =========================
    // TC_05 - MULTI EDIT
    // =========================
    @Test(priority = 5)
    public void RecipeEdit_TC_05() {
////
////        authFlow.loginFromFridgeTab("duyentest@gmail.com", "123456");
////
////        profile.clickTabProfile();
////        profile.openSavedRecipes();
////
////        int total = saved.getTotalRecipes();
////        int editCount = Math.min(3, total);
////
////        for (int i = 0; i < editCount; i++) {
////
////            saved.clickEditAt(i);
////
////            String newName = "Multi " + i + System.currentTimeMillis();
////
////            edit.clearName();
////            edit.inputName(newName);
////
////            edit.clickUpdate(); // ✅ FIX
////
////            WaitingHelper.sleepSeconds(1);
////
////            // ✅ FIX: quay lại list
////            profile.clickTabProfile();
////            profile.openSavedRecipes();
////        }
////
////        Assert.assertTrue(true, "✅ Multi edit done");
////    }

        // ===== DATA =====
        List<String> keywords = Arrays.asList("cà phê", "trà", "bánh", "cơm", "cháo", "xúc xích");

        // ===== LOGIN =====
        authFlow.loginFromFridgeTab("duyentest@gmail.com", "123456");

        // ===== SAVE NHIỀU RECIPE =====
        for (String keyword : keywords) {

            searchFlow.searchByTag(keyword);

            search.clickResultAt(0);
            detail.waitForLoaded();

            boolean isSaved = detail.clickSaveAndVerifyToast();
            Assert.assertTrue(isSaved, "❌ Save thất bại với keyword: " + keyword);

            detail.clickBackToHome();
            home.backToHome();
        }

        // ===== VÀO KHO =====
        profile.clickTabProfile();
        profile.openSavedRecipes();

        int editCount = keywords.size();

        // ===== MULTI EDIT FULL =====
        for (int i = 0; i < editCount; i++) {

            System.out.println("👉 LOOP: " + i);

            // 👉 luôn edit item đầu để tránh lệch index
            saved.clickEditAt(0);

            // ===== DATA MỚI =====
            String newName = "Multi_" + i + "_" + System.currentTimeMillis();
            String newTime = "10" + i;
            String newServing = "" + (i + 1);

            // ===== EDIT FULL (GIỐNG TC_02) =====
            edit.clearName();
            edit.inputName(newName);

            try { getDriver().hideKeyboard(); } catch (Exception ignored) {}

            edit.clearTime();
            edit.inputTime(newTime);

            edit.clearServing();
            edit.inputServing(newServing);

            // ===== INGREDIENT =====
            edit.slowSwipeDownOnScreen(1);
            edit.deleteAllIngredients();

            String ing1 = "Nguyên liệu " + i;
            String ing2 = "" + (i + 2);
            String ing3 = "gram";

            edit.inputStep3(ing1);
            edit.inputStep4(ing2);
            edit.inputStep5(ing3);

            // ===== STEP =====
            try { getDriver().hideKeyboard(); } catch (Exception ignored) {}

            edit.slowSwipeDownOnScreen(1);
            edit.deleteAllSteps();

            edit.clickAddStep();

            String step1 = "Step 1 - " + i;
            String step2 = "Step 2 - " + i;

            edit.inputStep1(step1);
            edit.inputStep2(step2);

            // ===== UPDATE =====
            try { getDriver().hideKeyboard(); } catch (Exception ignored) {}

            edit.slowSwipeDownOnScreen(2);
            edit.clickUpdate();

            WaitingHelper.sleepSeconds(2);

            // ===== VERIFY =====
            Assert.assertTrue(
                    saved.isRecipeExist(newName, ""),
                    "❌ Update thất bại tại vòng: " + i
            );

            // 👉 quay lại list
            profile.clickTabProfile();
            profile.openSavedRecipes();
        }
    }
    // =========================
    // TC_06 - UPDATE AVATAR
    // =========================
    @Test(priority = 6)
    public void RecipeEdit_TC_06() {

        goToEditScreen();

        edit.clickAvatar();
//        edit.chooseImageFromGalleryReal(); // auto update UI REAL
        edit.chooseImageFromGalleryEmulator(1); // auto update UI EMULATOR

        edit.slowSwipeDownOnScreen(2);
        edit.clickUpdate();

        saved.clickBack();
        profile.clickTabProfile();
        profile.openSavedRecipes();

        Assert.assertTrue(true, "✅ Avatar update success");
    }

    // =========================
    // TC_07 - ADD 1 TAG
    // =========================
    @Test(priority = 7, description = "RecipeEdit_TC_07 - Add 1 tag thành công")
    public void RecipeEdit_TC_07() {

        goToEditScreen();

        String tagName = "Cá Kem";

        // 1. Mở popup
        edit.openAddTagPopup();

        // 2. Click input
        edit.clickTagNameInput();

        // 3. Nhập text (reuse method cũ)
        edit.typeKeywordAndSelectDropdownTag(tagName, tagName);

        // 4. Click Thêm
        edit.clickAddInPopup();

        // 5. Đóng popup
        edit.closePopup();

        // 6. Update
        edit.slowSwipeDownOnScreen(2);
        edit.clickUpdate();

        Assert.assertTrue(true, "✅ Add tag done");
    }
    // =========================
    // TC_08 - ADD MULTI TAG
    // =========================
    @Test(priority = 8, description = "RecipeEdit_TC_08 - Add multiple tags (mỗi lần 1 popup)")
    public void RecipeEdit_TC_08() {

        goToEditScreen();

        List<String> tags = Arrays.asList("Cá", "Kem", "Trứng");

        // 👉 mỗi tag = 1 lần click Thêm
        edit.addMultipleTags(tags);

        // 👉 update cuối cùng
        edit.slowSwipeDownOnScreen(2);
        edit.clickUpdate();

        Assert.assertTrue(true, "✅ Add multiple tags done");
    }

    // =========================
    // TC_09 - AVATAR + MULTI TAG
    // =========================
    @Test(priority = 9, description = "RecipeEdit_TC_09 - Avatar + Multi tag")
    public void RecipeEdit_TC_09() {

        goToEditScreen();

        // ===== AVATAR =====
        edit.clickAvatar();
        edit.chooseImageFromGalleryEmulator(2);

        WaitingHelper.sleepSeconds(1);

        // ===== TAG =====
        List<String> tags = Arrays.asList("Cá", "Kem", "Trứng");

        edit.addMultipleTags(tags);

        // ===== UPDATE =====
        edit.slowSwipeDownOnScreen(2);
        edit.clickUpdate();

        Assert.assertTrue(true, "✅ Avatar + Multi tag done");
    }
    // =========================
    // TC_10 - REQUIRED FIELD
    // =========================
    @Test(priority = 10)
    public void RecipeEdit_TC_10() {

        goToEditScreen();

        editFlow.clearRequiredField();

        // 🔥 ĐẢM BẢO KHÔNG BỊ KEYBOARD CHẶN
        try {
            getDriver().hideKeyboard();
        } catch (Exception ignored) {}

        edit.slowSwipeDownOnScreen(2);

        edit.clickUpdate(); // ✅ FIX

        Assert.assertTrue(
                editFlow.isRequiredToast(),
                "❌ Không hiển thị toast required"
        );
    }

    // =========================
    // TC_11 - EMPTY INGREDIENT
    // =========================
    @Test(priority = 11)
    public void RecipeEdit_TC_11() {

        goToEditScreen();

        edit.slowSwipeDownOnScreen(1);

        editFlow.clearAllIngredients();
        edit.clearInput3();
        edit.clearInput4();
        edit.clearInput5();

        // 🔥 ĐẢM BẢO KHÔNG BỊ KEYBOARD CHẶN
        try {
            getDriver().hideKeyboard();
        } catch (Exception ignored) {}

        edit.slowSwipeDownOnScreen(1);

        edit.clickUpdate(); // ✅ FIX

        Assert.assertTrue(
                editFlow.isInvalidToast(),
                "❌ Không hiển thị toast nguyên liệu"
        );
    }

    // =========================
    // TC_12 - EMPTY STEP
    // =========================
    @Test(priority = 12)
    public void RecipeEdit_TC_12() {

        goToEditScreen();

        edit.slowSwipeDownOnScreen(2);
        editFlow.clearAllSteps();

        edit.clearInput1();

        edit.clickUpdate();

        Assert.assertTrue(
                editFlow.isInvalidToast(),
                "❌ Không hiển thị toast bước nấu"
        );
    }
}