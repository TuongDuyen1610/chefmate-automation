package flows;

import screens.ShoppingListScreen;

public class ShoppingListFlow {

    private final ShoppingListScreen list = new ShoppingListScreen();

    public void verifyListUI() { list.verifyUI(); }

    public void openAndCloseSupplementPopup() {
        list.openSupplementPopup();
        list.closeSupplementPopup();
        list.verifyUI();
    }

    public void addSupplementIngredient(String name, String qty, String unit) {
        list.addSupplementIngredient(name, qty, unit);
    }

    public void toggleCheckboxAt(int index) { list.toggleCheckboxAt(index); }

    public void clickComplete() { list.clickComplete(); }

    public void clickBack() { list.clickBack(); }

    public void verifyRecipeTabExists(String recipeTitle) { list.verifyRecipeTabExists(recipeTitle); }
    public void verifyRecipeTabNotExists(String recipeTitle) { list.verifyRecipeTabNotExists(recipeTitle); }

    public void verifyIngredientDisplayed(String text) { list.verifyIngredientDisplayed(text); }
    public void verifyIngredientDisplayedIgnoreCase(String text) { list.verifyIngredientDisplayedIgnoreCase(text); }

    // ===== EDIT =====
    public void editIngredientNameAt(int index, String newName) {
        list.openEditAt(index);
        list.updateIngredientInEditPopup(newName, null, null);
    }

    public void editIngredientQtyUnitAt(int index, String newQty, String newUnit) {
        list.openEditAt(index);
        list.updateIngredientInEditPopup(null, newQty, newUnit);
    }
    public void editIngredientAllAt(int index, String newName, String newQty, String newUnit) {
        list.openEditAt(index);
        list.updateIngredientInEditPopup(newName, newQty, newUnit);
    }
    public boolean isCheckboxCheckedAt(int index) { return list.isCheckboxCheckedAt(index); }

    // ===== DELETE =====
    public void clickDeleteByExactIndex(int index1Based) {
        list.clickDeleteByExactIndex(index1Based);
    }
    public void clickDeleteByIngredientTextIgnoreCase(String ingredientText) {
        list.clickDeleteByIngredientTextIgnoreCase(ingredientText);
    }
    public int getIngredientCount() { return list.getIngredientCount(); }

    public String getIngredientTextAt(int index) { return list.getIngredientTextAt(index); }

    public void swipeDown(int times) { list.slowSwipeDownOnScreen(times); }
}