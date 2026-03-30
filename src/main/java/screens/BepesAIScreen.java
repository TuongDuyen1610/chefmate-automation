package screens;

import core.base.BaseScreen;
import org.openqa.selenium.By;

public class BepesAIScreen extends BaseScreen {

    private final By chatInput = By.xpath("//android.widget.EditText");
    private final By btnSend = By.xpath("//android.widget.EditText/android.view.View/android.widget.Button");
    private final By aiResponse = By.xpath("//android.widget.TextView[contains(@text,'Bepes')]");

    public void sendQuestion(String question) {
        logStep("Gửi câu hỏi cho Bepes: " + question);
        type(chatInput, question);
        click(btnSend);
    }

    public boolean isAIResponseDisplayed() {
        return isDisplayed(aiResponse);
    }
}