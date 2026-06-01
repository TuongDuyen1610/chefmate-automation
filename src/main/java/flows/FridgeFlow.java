package flows;

import core.utils.AllureHelper;
import screens.*;


public class FridgeFlow {

    private final AuthenticationFlow authFlow = new AuthenticationFlow();

    private final FridgeScreen fridge = new FridgeScreen();

    public void login(String email, String password) {
        authFlow.loginFromFridgeTab(email, password);
        if (!authFlow.isLoggedInSuccessfully()) {
            AllureHelper.attachScreenshot("LOGIN FAILED");
            throw new AssertionError("❌ Login failed");
        }
        AllureHelper.attachScreenshot("LOGIN SUCCESS");
    }

    public void openFridge() {
        fridge.openFridgeScreen();
        fridge.verifyInitialUI();
    }

    public void addManualIngredient(String name, String qty, String unit, String dueDate) {
        fridge.addManualIngredient(name, qty, unit, dueDate);
    }
}