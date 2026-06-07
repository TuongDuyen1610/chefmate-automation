package flows;

import screens.EditRecipeScreen;

public class EditRecipeFlow {

    private final EditRecipeScreen edit = new EditRecipeScreen();

    public boolean verifyFormLoaded() {
        return edit.isFormLoaded();
    }

    public void clearRequiredField() {
        edit.clearName();
    }

    public void updateRecipe() {
        edit.clickUpdate();
    }

    public void clearAllIngredients() {
        edit.deleteAllIngredients();
    }

    public void clearAllSteps() {
        edit.deleteAllSteps();
    }

    public boolean isRequiredToast() {
        return edit.isRequiredToastDisplayed();
    }

    public boolean isInvalidToast() {
        return edit.isInvalidToastDisplayed();
    }
}