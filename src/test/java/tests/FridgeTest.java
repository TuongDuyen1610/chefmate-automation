package tests;

import core.base.BaseTest;
import core.data.LoginData;
import core.utils.AllureHelper;
import core.utils.JsonHelper;
import flows.AuthenticationFlow;
import flows.FridgeFlow;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.*;

public class FridgeTest extends BaseTest {

    private final AuthenticationFlow authFlow = new AuthenticationFlow();
    private final FridgeFlow fridgeFlow = new FridgeFlow();
    private final FridgeScreen fridge = new FridgeScreen();
    private final SplashScreen splash = new SplashScreen();
    private final LoginGateHelper gateHelper = new LoginGateHelper();
    LoginData loginData = JsonHelper.readLoginData();
    String email = loginData.login;
    String password = loginData.password;


    // =============== FEATURE 7 TESTCASES ===============

    @Test(priority = 1)
    public void Fridge_TC_01() {
        authFlow.loginFromFridgeTab(email, password);
        fridgeFlow.openFridge();
    }

    @Test(priority = 2)
    public void Fridge_TC_02() {
        authFlow.loginFromFridgeTab(email, password);
        fridgeFlow.openFridge();
        // Lan 1
        fridge.openManualAddSheet();
        fridge.cancleManualAddSheet();
        fridge.verifyInitialUI();
        // Lan 2
        fridge.openManualAddSheet();
        fridge.cancleManualAddSheet();
        fridge.verifyInitialUI();
    }

    @Test(priority = 3)
    public void Fridge_TC_03() {
        authFlow.loginFromFridgeTab(email, password);
        fridgeFlow.openFridge();

        fridgeFlow.addManualIngredient("Test_1", "4", "g", "2022-02-12");
        fridgeFlow.addManualIngredient("Test_2", "5", "g", "2023-03-13");
        fridgeFlow.addManualIngredient("Test_3", "6", "g", "2023-04-14");
    }

    @Test(priority = 4)
    public void Fridge_TC_04() {
        splash.waitUntilSplashDisappear();
        AllureHelper.attachScreenshot("Splash screen disappeared");
        fridgeFlow.openFridge();
        Assert.assertTrue(fridge.verifyScreenNotLogin(), "Screen Fridge displayed");
    }

    @Test(priority = 5)
    public void Fridge_TC_05() {
        authFlow.loginFromFridgeTab(email, password);
        fridgeFlow.openFridge();

        fridgeFlow.addManualIngredient("", "", "", "");
        Assert.assertTrue(fridge.verifySheetAdd(), "Button Save van hoat dong, sheet closed");
    }

    @Test(priority = 6)
    public void Fridge_TC_06() {
        authFlow.loginFromFridgeTab(email, password);
        fridgeFlow.openFridge();

        String Name ="!#^%&**&%^&%*&^*";
        String Qty = "&$&I**(^%(*&^";
        String Unit = "Ơ:+:)(*&*%^";
        String Date = "7686&^&^%^%&";
        fridgeFlow.addManualIngredient(Name, Qty, Unit, Date);
        Assert.assertTrue(fridge.verifySheetAdd(), "Button Save van hoat dong, sheet closed");
    }

    @Test(priority = 7)
    public void Fridge_TC_07() {
        authFlow.loginFromFridgeTab(email, password);
        fridgeFlow.openFridge();

        String maxName = "Test".repeat(200);
        String maxQty = "9".repeat(200);
        String maxUnit = "gam".repeat(200);
        String maxDate = "2021-12-01".repeat(200);
        fridgeFlow.addManualIngredient(maxName, maxQty, maxUnit, maxDate);
    }
}