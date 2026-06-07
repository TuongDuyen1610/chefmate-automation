package flows;

import screens.ShoppingHistoryScreen;

public class ShoppingHistoryFlow {

    private final ShoppingHistoryScreen history = new ShoppingHistoryScreen();

    /** Prerequisite: test đã mở Tab Tài khoản/Profile bằng ProfileScreen.clickTabProfile() */
    public void openHistoryFromProfile() {
        history.openFromProfileTab();
        history.verifyHistoryListDisplayed();
    }

    /** GIỮ NGUYÊN HÀM TC_09 */
    public void openLatestHistoryDetail() {
        history.openDetailAt(0);
        history.verifyHasIngredientsInDetail();
    }

    /** GIỮ NGUYÊN HÀM TC_09 */
    public void verifyIngredientInLatestDetailIgnoreCase(String ingredientText) {
        history.verifyIngredientInDetailIgnoreCase(ingredientText);
    }

    public void verifyRecipeTabInLatestDetail(String recipeTitle) {
        history.verifyRecipeTabExistsInDetail(recipeTitle);
    }

    public void backToHistoryList() {
        history.backFromDetailByText();
    }

    public void backToProfile() {
        history.clickBack();
    }

    public int getHistoryItemCount() {
        return history.getHistoryItemCount();
    }

    public String getPurchaseDateTextAt(int index) {
        return history.getPurchaseDateTextAt(index);
    }

    public boolean isHistoryEmpty() {
        return history.isHistoryEmpty();
    }

    public void verifyEmptyState() {
        history.verifyEmptyState();
    }

    // ===== extra for new tests =====
    public void openHistoryDetailAt(int index0Based) {
        history.openDetailAt(index0Based);
        history.verifyHasIngredientsInDetail();
    }

    public void verifyIngredientInDetailIgnoreCase(String ingredientText) {
        history.verifyIngredientInDetailIgnoreCase(ingredientText);
    }
}