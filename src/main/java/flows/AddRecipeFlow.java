package flows;

import screens.AddRecipeScreen;

public class AddRecipeFlow {

    private final AddRecipeScreen add = new AddRecipeScreen();

    public boolean verifyFormLoaded() {
        return add.isFormLoaded();
    }
    public void clearRequiredField() {
        add.clearName();
    }

    public void updateRecipe() {
        add.clickPost();
    }

    public void clearAllIngredients() {
        add.deleteAllIngredients();
    }

    public void clearAllSteps() {
        add.deleteAllSteps();
    }

    public boolean isRequiredToast() {
        return add.isRequiredToastDisplayed();
    }

    public boolean isInvalidToast() {
        return add.isInvalidToastDisplayed();
    }
}