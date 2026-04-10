package smoke;

import core.base.BaseTest;
import flows.BepesAIFlow;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BepesAIChatTest extends BaseTest {

    private final BepesAIFlow ai = new BepesAIFlow();

    @Test(priority = 1, description = "Smoke 04 - Chat với Bepes AI thành công")
    public void TC04_BepesAI_Chat_Success() {
        ai.chatWithBepes("Cách nấu phở bò");
        Assert.assertTrue(ai.verifyAIResponse(), "Bepes AI không trả lời");
        System.out.println("✅ TC04 PASS - Bepes AI trả lời đúng");
    }
}