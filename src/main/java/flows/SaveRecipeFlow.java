package flows;

import core.utils.AllureHelper;
import screens.ProfileScreen;
import screens.RecipeDetailScreen;
import screens.SavedRecipesScreen;

public class SaveRecipeFlow {

    RecipeDetailScreen detail = new RecipeDetailScreen();
    ProfileScreen profile = new ProfileScreen();
    SavedRecipesScreen saved = new SavedRecipesScreen();

    public boolean saveAndVerify(String title, String author) {

        // 1. Save
        boolean savedOk = detail.clickSaveAndVerifyToast();

        // 2. Back về Home
        detail.clickBackToHome();

        // 3. Mở tab Tài khoản (DÙNG METHOD CÓ SẴN)
        profile.clickTabProfile();

        // 4. Mở Kho công thức
        profile.openSavedRecipes();

        // 5. Verify
        boolean exist = saved.isRecipeExist(title, author);
        boolean isTop = saved.isNewestOnTop(title);

        return savedOk && exist && isTop;
    }
    public int getSavedCount() {

        profile.clickTabProfile();
        profile.openSavedRecipes();
        AllureHelper.attachScreenshot("Kho cong thuc hien thi");
        saved.slowSwipeDownOnScreen(4);
        int total = saved.getTotalRecipes();
        saved.clickBack();
        profile.clickTabHome();
        return total;
    }
}