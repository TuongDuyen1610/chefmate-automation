package flows;

import core.base.BaseFlow;
import screens.BepesAIScreen;

public class BepesAIFlow extends BaseFlow {
    private final BepesAIScreen bepesAIScreen = new BepesAIScreen();

    public void chatWithAI(String message) {
        bepesAIScreen.typeInChat(message);
    }
}