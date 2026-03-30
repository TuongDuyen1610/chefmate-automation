package screens;

import core.base.BaseScreen;
import org.openqa.selenium.By;

public class BepesAIScreen extends BaseScreen {
    private final By aiChatBox = By.id("ai_chat_box");

    public void typeInChat(String message) {
        type(aiChatBox, message);
    }
}