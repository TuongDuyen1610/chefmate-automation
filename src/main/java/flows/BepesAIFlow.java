package flows;

import core.base.BaseFlow;
import screens.BepesAIScreen;
import screens.HomeScreen;

public class BepesAIFlow extends BaseFlow {

    private final HomeScreen home = new HomeScreen();
    private final BepesAIScreen ai = new BepesAIScreen();

    public void chatWithBepes(String question) {
        logStep("Bắt đầu chat với Bepes: " + question);
        home.openBepesAI();
        ai.sendQuestion(question);
    }

    public boolean verifyAIResponse() {
        logStep("Kiểm tra Bepes đã trả lời");
        return ai.isAIResponseDisplayed();
    }
}