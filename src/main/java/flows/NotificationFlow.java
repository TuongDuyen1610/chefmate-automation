package flows;

import core.base.BaseFlow;
import screens.HomeScreen;

public class NotificationFlow extends BaseFlow {

    private final HomeScreen home = new HomeScreen();

    public void openNotification() {
        logStep("Mở chuông thông báo");
        // Click icon chuông
    }
}