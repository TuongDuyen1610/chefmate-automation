package tests;

import core.base.BaseTest;
import core.data.LoginData;
import core.utils.AllureHelper;
import core.utils.JsonHelper;
import core.utils.WaitingHelper;
import flows.AuthenticationFlow;
import flows.BepesAIFlow;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.BepesAIScreen;
import screens.HomeScreen;

/**
 * ✅ 37 testcases đúng ID: BepesAI_TC_01 ... BepesAI_TC_37
 * ✅ Login giống SaveRecipeTest
 * ✅ Open Bepes bằng HomeScreen.openBepesAI()
 */
public class BepesAITest extends BaseTest {

    // ===== SCREEN =====
    private final HomeScreen home = new HomeScreen();
    private final BepesAIScreen bepes = new BepesAIScreen();

    // ===== FLOW =====
    private final AuthenticationFlow authFlow = new AuthenticationFlow();
    private final BepesAIFlow bepesFlow = new BepesAIFlow(home, bepes);

    // ===== DATA =====
    private final LoginData loginData = JsonHelper.readLoginData();
    private final String email = loginData.login;
    private final String password = loginData.password;

    private void login() {
        authFlow.loginFromFridgeTab(email, password);
        WaitingHelper.sleepSeconds(2);
        Assert.assertTrue(authFlow.isLoggedInSuccessfully(), "❌ Login failed");
        AllureHelper.attachScreenshot("LOGIN SUCCESS");
    }

    private void openBepes() {
        bepesFlow.openBepesFromHome();
    }

    // ========== TC_01 ==========
    @Test(priority = 1)
    public void BepesAI_TC_01() {
        login();
        openBepes();
    }

    // ========== TC_02 ==========
    @Test(priority = 2)
    public void BepesAI_TC_02() {
        login();
        for (int i = 0; i < 3; i++) {
            openBepes();
            bepes.clickBack();
            WaitingHelper.sleepSeconds(1);
        }
        AllureHelper.attachScreenshot("Back/Resume loops done");
    }

    // ========== TC_03 ==========
    @Test(priority = 3)
    public void BepesAI_TC_03() {
        login();
        openBepes();
        for (int i = 0; i < 5; i++) {
            bepes.hideActions();
            bepes.showActions();
        }
    }

    // ========== TC_04 ==========
    @Test(priority = 4)
    public void BepesAI_TC_04() {
        login();
        openBepes();
        bepes.showActions();
        bepes.openFinishPopupByActionButton();
        bepes.verifyFinishPopupUI();
        bepes.completeFinishPopup("finish session");
        for (int i = 0; i < 3; i++) {
            bepes.openChooseDishSheetSmart();
            // close sheet: back (sheet này không thấy Close sheet trong locator chị đưa)
            driver.navigate().back();
            WaitingHelper.sleepSeconds(1);
        }
    }

    // ========== TC_05 ==========
    @Test(priority = 5)
    public void BepesAI_TC_05() {
        login();
        openBepes();
        bepes.showActions();
        bepes.openFinishPopupByActionButton();
        bepes.completeFinishPopup("finish session");
        bepes.openChooseDishSheet();
        bepes.slowSwipeDownOnScreen(1);
        bepes.clickChooseDishByIndex(1);
        bepes.cancelChooseDishPopup();

        bepes.clickChooseDishByIndex(2);
        bepes.cancelChooseDishPopup();
    }
    // SỬA TÁI SỬ DỤNG
    // ========== TC_06 ==========
    @Test(priority = 6)
    public void BepesAI_TC_06() {

        login();
        openBepes();

        // ===== MÓN 1 =====
        bepes.openChooseDishSheetSmart();
        bepes.slowSwipeDownOnScreen(1);
        bepes.clickChooseDishByIndex(1);
        bepes.confirmChooseDishPopup();

        // ===== MÓN 2 =====
        bepes.openChooseDishSheetSmart();
        bepes.slowSwipeDownOnScreen(2);
        bepes.clickChooseDishByIndex(2);
        bepes.confirmChooseDishPopup();

        // ===== MÓN 3 =====
        bepes.openChooseDishSheetSmart();
        bepes.slowSwipeDownOnScreen(3);
        bepes.clickChooseDishByIndex(3);
        bepes.confirmChooseDishPopup();

        // ===== MÓN 4 =====
        bepes.openChooseDishSheetSmart();
        bepes.slowSwipeDownOnScreen(4);
        bepes.clickChooseDishByIndex(4);
        bepes.confirmChooseDishPopup();

        // ===== MÓN 5 =====
        bepes.openChooseDishSheetSmart();
        bepes.slowSwipeDownOnScreen(4);
        bepes.clickChooseDishByIndex(5);
        bepes.confirmChooseDishPopup();
        AllureHelper.attachScreenshot("Selected multiple dishes");
    }
    // ========== TC_07 ==========
    @Test(priority = 7)
    public void BepesAI_TC_07() {
        login();
        openBepes();
        bepes.openChooseDishSheetSmart();
        bepes.verifyFoodSheetIconsExist();
    }

    // ========== TC_08 ==========
    @Test(priority = 8)
    public void BepesAI_TC_08() {
        login();
        openBepes();
        bepes.openChooseDishSheetSmart();
        bepes.clickMoveUpFirst();
    }

    // ========== TC_09 ==========
    @Test(priority = 9)
    public void BepesAI_TC_09() {
        login();
        openBepes();
        bepes.openChooseDishSheetSmart();
        bepes.clickMoveDownFirst();
    }

    // ========== TC_10 ==========
    @Test(priority = 10)
    public void BepesAI_TC_10() {
        login();
        openBepes();
        bepes.openChooseDishSheetSmart();
        for (int i = 0; i < 10; i++) {
            bepes.clickMoveUpFirst();
            bepes.clickMoveDownFirst();
        }
        driver.navigate().back();
        WaitingHelper.sleepSeconds(1);
        bepes.openChooseDishSheetSmart();
    }

    // ========== TC_11 ==========
    @Test(priority = 11)
    public void BepesAI_TC_11() {
        login();
        openBepes();
        bepes.openChooseDishSheetSmart();
        bepes.clickDeleteDishFirst();
        driver.navigate().back();
        WaitingHelper.sleepSeconds(1);
        bepes.openChooseDishSheetSmart();
    }

    // ========== TC_12 ==========
    @Test(priority = 12)
    public void BepesAI_TC_12() {
        login();
        openBepes();
        bepes.openChooseDishSheetSmart();
        bepes.clickPrioritizeFirst();
        bepes.verifyPrioritizedLabelExists();
        driver.navigate().back();
        WaitingHelper.sleepSeconds(1);
        bepes.openChooseDishSheetSmart();
        bepes.verifyPrioritizedLabelExists();
    }

    // ========== TC_13 ==========
    @Test(priority = 13)
    public void BepesAI_TC_13() {
        login();
        openBepes();
        bepes.openChooseDishSheetSmart();
        bepes.clickMoveUpFirst();
        bepes.clickMoveDownFirst();
    }

    // ========== TC_14 ==========
    @Test(priority = 14)
    public void BepesAI_TC_14() {
        login();
        openBepes();
        bepes.showActions_2();
        for (int i = 0; i < 3; i++) {
            bepes.openNotesSheet();
            bepes.closeNotesSheet();
        }
    }

    // ========== TC_15 ==========
    @Test(priority = 15)
    public void BepesAI_TC_15() {
        login();
        openBepes();
        bepes.showActions_2();
        bepes.openNotesSheet();
        bepes.openAddNotePopup();
        bepes.cancelNotePopup();
    }

    // helper tạo note theo tab
    private void createNote(String tab, String title, boolean toggleOn) {
        bepes.openAddNotePopup();
        switch (tab) {
            case "Dị ứng" -> bepes.selectNoteTypeAllergy();
            case "Hạn chế" -> bepes.selectNoteTypeRestriction();
            case "Sở thích" -> bepes.selectNoteTypePreference();
            case "Sức khỏe" -> bepes.selectNoteTypeHealth();
        }
        bepes.fillNoteFields(title, "12", "12");
        if (toggleOn) bepes.toggleNoteActiveInPopup();
        bepes.saveNotePopup();
        bepes.verifyNoteItemExists(title);
    }
    private void createNoteOFF(String tab, String title) {
        bepes.openAddNotePopup();
        switch (tab) {
            case "Dị ứng" -> bepes.selectNoteTypeAllergy();
            case "Hạn chế" -> bepes.selectNoteTypeRestriction();
            case "Sở thích" -> bepes.selectNoteTypePreference();
            case "Sức khỏe" -> bepes.selectNoteTypeHealth();
        }
        bepes.fillNoteFields(title, "12", "12");
        bepes.toggleNoteActiveInPopup();
        bepes.saveNotePopup();
        bepes.verifyNoteItemExists(title);
    }

    // ========== TC_16 ==========
    @Test(priority = 16)
    public void BepesAI_TC_16() {
        login();
        openBepes();
        bepes.showActions_2();
        bepes.openNotesSheet();

        createNote("Dị ứng", "Test_Allergy", true);
        createNote("Hạn chế", "Test_Restrict", true);
        createNote("Sở thích", "Test_Pref", true);
        createNote("Sức khỏe", "Test_Health", true);
    }

    // ========== TC_17 ==========
    @Test(priority = 17)
    public void BepesAI_TC_17() {
        login();
        openBepes();
        bepes.showActions_2();
        bepes.openNotesSheet();
        String before = bepes.getNotesCounterText();

        createNote("Dị ứng", "Test_ON", false);

        String after = bepes.getNotesCounterText();
        AllureHelper.attachScreenshot("Counter before=" + before + " after=" + after);
    }

    // ========== TC_18 ==========
    @Test(priority = 18)
    public void BepesAI_TC_18() {
        login();
        openBepes();
        bepes.showActions_2();
        bepes.openNotesSheet();
        String before = bepes.getNotesCounterText();

        createNoteOFF("Hạn chế", "Test_OFF");

        String after = bepes.getNotesCounterText();
        AllureHelper.attachScreenshot("Counter before=" + before + " after=" + after);
    }
    // ========== TC_19 ==========
    @Test(priority = 19)
    public void BepesAI_TC_19() {
        login();
        openBepes();
        bepes.showActions_2();
        bepes.openNotesSheet();
        String before = bepes.getNotesCounterText();

        bepes.openAddNotePopup();
        bepes.selectNoteTypePreference();
        bepes.fillNoteFields("Test_CANCEL", "12", "12");
        bepes.toggleNoteActiveInPopup();
        bepes.cancelNotePopup();

        String after = bepes.getNotesCounterText();
        AllureHelper.attachScreenshot("Counter before=" + before + " after=" + after);
    }
    // ========== TC_20 ==========
    @Test(priority = 20)
    public void BepesAI_TC_20() {
        login();
        openBepes();
        bepes.showActions_2();
        bepes.openNotesSheet();

        String title = "Test1";
        if (!bepes.isNotePresent(title)) {
            createNote("Dị ứng", title, true);
        }

        bepes.clickEditNoteByTitle(title);
        bepes.fillEditNoteFields(title + "_EDIT", "12", "12");
        bepes.saveNotePopup();

        bepes.verifyNoteItemExists(title + "_EDIT");
    }

    // ========== TC_21 ==========
    @Test(priority = 21)
    public void BepesAI_TC_21() {
        login();
        openBepes();
        bepes.showActions_2();
        bepes.openNotesSheet();

        String title = "Test";
        if (!bepes.isNotePresent(title)) {
            createNote("Dị ứng", title, true);
        }

        bepes.clickEditNoteByTitle(title);
        bepes.fillEditNoteFields(title + "_EDIT", "12", "12");
        bepes.cancelNotePopup();

        bepes.verifyNoteItemExists(title);
    }

    // ========== TC_22 ==========
    @Test(priority = 22)
    public void BepesAI_TC_22() {
        login();
        openBepes();
        bepes.showActions_2();
        bepes.openNotesSheet();

        String title = "Test_DELETE";
        if (!bepes.isNotePresent(title)) {
            createNote("Sở thích", title, true);
        }

        String before = bepes.getNotesCounterText();
        bepes.clickDeleteNoteByTitle(title);
        String after = bepes.getNotesCounterText();

        AllureHelper.attachScreenshot("Counter before=" + before + " after=" + after);
    }

    // ========== TC_23 ==========
    @Test(priority = 23)
    public void BepesAI_TC_23() {
        login();
        openBepes();
        bepes.showActions_2();
        bepes.openNotesSheet();
        bepes.openAddNotePopup();
        bepes.fillNoteFields("", "", "");
        bepes.saveNotePopup(); // validate hoặc không crash
        AllureHelper.attachScreenshot("Tried save empty note");
    }

    //     ========== TC_24 ==========
    @Test(priority = 24)
    public void BepesAI_TC_24() {
        login();
        openBepes();

        // precondition: chọn 1 món để mở công thức
        bepes.openChooseDishSheetSmart();
        bepes.slowSwipeDownOnScreen(5);
        bepes.clickChooseDishByIndex(5);
        bepes.confirmChooseDishPopup();

        for (int i = 0; i < 3; i++) {
            bepes.showActions_2();
            bepes.openViewRecipes();
            bepes.verifyRecipesSheetOpened();
            driver.navigate().back();
            WaitingHelper.sleepSeconds(1);
        }
    }

    // ========== TC_25 ==========
    @Test(priority = 25)
    public void BepesAI_TC_25() {
        login();
        openBepes();
        // precondition: chọn 1 món để mở công thức
        bepes.openChooseDishSheetSmart();
        bepes.slowSwipeDownOnScreen(2);
        bepes.clickChooseDishByIndex(2);
        bepes.confirmChooseDishPopup();

        bepes.showActions_2();
        bepes.openViewRecipes();
        bepes.openDetailRecipes();
        // nếu có món -> sheet mở; nếu chưa -> toast. testcase chỉ yêu cầu mượt/không crash
        AllureHelper.attachScreenshot("Open recipe detail (best effort)");
    }

    // ========== TC_26 ==========
    @Test(priority = 26)
    public void BepesAI_TC_26() {
        login();
        openBepes();
        bepes.showActions_2();
        bepes.verifyNeedChooseDishToast();
    }

    // ========== TC_27 ==========
    @Test(priority = 27)
    public void BepesAI_TC_27() {
        login();
        openBepes();

        // điều kiện: phải chat có phản hồi xong mới thấy hoàn thành
        bepes.sendMessage("Cho mình gợi ý món dễ nấu nhé");
        bepes.waitForAiResponse(10);

        bepes.showActions_2();
        // mở popup hoàn thành từ action button (text)
        bepes.openFinishPopupByActionButton();
        bepes.verifyFinishPopupUI();
        bepes.cancelFinishPopup();

        bepes.showActions_2();
        // lặp 2 lần
        bepes.openFinishPopupByActionButton();
        bepes.cancelFinishPopup();
    }

    // ========== TC_28 ==========
    @Test(priority = 28)
    public void BepesAI_TC_28() {
        login();
        openBepes();

        bepes.sendMessage("Cho mình gợi ý món dễ nấu nhé");
        bepes.waitForAiResponse(10);

        bepes.showActions();
        // icon chỉ hiện khi Ẩn thao tác
        bepes.hideActions();
        Assert.assertTrue(bepes.isHeaderCompleteIconVisible(), "❌ Icon Hoàn thành trên header không hiển thị");

        // toggle vài lần để check ổn định
        for (int i = 0; i < 3; i++) {
            bepes.showActions();
            bepes.hideActions();
            Assert.assertTrue(bepes.isHeaderCompleteIconVisible(), "❌ Icon Hoàn thành bị mất sau toggle");
        }
    }

    // ========== TC_29 ==========
    @Test(priority = 29)
    public void BepesAI_TC_29() {
        login();
        openBepes();

        bepes.sendMessage("Cho mình gợi ý món dễ nấu nhé");
        bepes.waitForAiResponse(10);
        Assert.assertTrue(bepes.isHeaderCompleteIconVisible(), "❌ Icon Hoàn thành trên header không hiển thị");

        bepes.openFinishPopupByHeaderIcon();
        bepes.verifyFinishPopupUI();
    }

    // ========== TC_30 ==========
    @Test(priority = 30)
    public void BepesAI_TC_30() {
        login();
        openBepes();

        bepes.sendMessage("Cho mình gợi ý món dễ nấu nhé");
        bepes.waitForAiResponse(10);
        bepes.showActions_2();
        bepes.openFinishPopupByActionButton();
        bepes.cancelFinishPopup();
        AllureHelper.attachScreenshot("Finish popup cancelled OK");
    }

    // ========== TC_31 ==========
    @Test(priority = 31)
    public void BepesAI_TC_31() {
        login();
        openBepes();

        // điều kiện: chat + response
        bepes.sendMessage("Cho mình gợi ý món dễ nấu nhé");
        bepes.waitForAiResponse(10);

        bepes.showActions_2();
        bepes.openFinishPopupByActionButton();
        bepes.verifyFinishPopupUI();
        bepes.completeFinishPopup("finish session");
    }

    // ========== TC_32 ==========
    @Test(priority = 32)
    public void BepesAI_TC_32() {
        login();
        openBepes();

        bepes.showActions_2();
        // chưa chat -> icon thường không hiện
        Assert.assertFalse(bepes.isHeaderCompleteIconVisible(), "❌ Icon Hoàn thành KHÔNG nên hiển thị khi chưa chat");
        AllureHelper.attachScreenshot("Icon complete hidden as expected");
    }
    // Xóa các món đã chọn trước khi ChatAI
    @Test(priority = 33)
    public void BepesAI_TC_DacBiet() {
        login();
        openBepes();
        bepes.openChooseDishSheetSmart();
        bepes.clickDeleteDishFirst();
        bepes.clickDeleteDishFirst();
        bepes.clickDeleteDishFirst();
        bepes.clickDeleteDishFirst();
        bepes.clickDeleteDishFirst();
//        driver.navigate().back();
    }
    // ========== TC_33 ==========
    @Test(priority = 34)
    public void BepesAI_TC_33() {
        login();
        openBepes();
//         precondition: chọn 1 món để mở công thức
        bepes.openChooseDishSheetSmart();
        bepes.slowSwipeDownOnScreen(2);
        bepes.clickChooseDishByIndex(2);
        bepes.confirmChooseDishPopup();
        bepes.sendMessage("Gợi ý cho mình làm nhanh dễ nấu nhé");
        bepes.waitForAiResponse(10);

    }

    // ========== TC_34 ==========
    @Test(priority = 35)
    public void BepesAI_TC_34() {
        login();
        openBepes();
//         precondition: chọn 1 món để mở công thức
        bepes.openChooseDishSheetSmart();
        bepes.slowSwipeDownOnScreen(5);
        bepes.clickChooseDishByIndex(5);
        bepes.confirmChooseDishPopup();
        String[] msgs = {
                "Công thức này ngon không?",
                "Có cách nào không dùng đường?",
                "Công thức này làm như thế nào?",
                "Bạn dùng những nguyên liệu gì để chế biến?",
                "Cảm ơn bạn"
        };

        for (String m : msgs) {
            bepes.sendMessage(m);
            bepes.waitForAiResponse(12);
        }

        // scroll history best effort
        for (int i = 0; i < 3; i++) {
            bepes.slowSwipeDownOnScreen(1);
            WaitingHelper.sleepSeconds(1);
        }
        AllureHelper.attachScreenshot("Chat history scrolled");
    }

    // ========== TC_35 ==========
    @Test(priority = 36)
    public void BepesAI_TC_35() {
        login();
        openBepes();

        bepes.openChooseDishSheetSmart();
        bepes.clickDeleteDishFirst();
        bepes.clickDeleteDishFirst();
        bepes.slowSwipeDownOnScreen(3);
        bepes.clickChooseDishByIndex(3);
        bepes.confirmChooseDishPopup();

        bepes.sendMessage("Món này nấu trong bao lâu?");
        bepes.waitForAiResponse(10);
    }

    // ========== TC_36 ==========
    @Test(priority = 37)
    public void BepesAI_TC_36() {
        login();
        openBepes();

        // cần chọn món trước (theo testcase chị)
        bepes.openChooseDishSheetSmart();
        bepes.slowSwipeDownOnScreen(3);
        bepes.clickChooseDishByIndex(3);
        bepes.confirmChooseDishPopup();

        // Test empty
        bepes.sendMessage("");
        AllureHelper.attachScreenshot("Tried send empty");

        // Test space
        bepes.sendMessage("   ");
        AllureHelper.attachScreenshot("Tried send space");
    }

    // ========== TC_37 ==========
    @Test(priority = 38)
    public void BepesAI_TC_37() {
        login();
        openBepes();
        bepes.showActions_2();
        bepes.openFinishPopupByActionButton();
        bepes.verifyFinishPopupUI();
        bepes.completeFinishPopup("finish session");

        // Test empty
        bepes.sendMessage("Công thức này làm như thế nào?");
        Assert.assertTrue(
                bepes.toastSendAI(),
                "Loi hien thi toast send msg thanh cong"
        );
    }
}