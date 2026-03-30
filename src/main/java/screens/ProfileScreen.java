package screens;

import core.base.BaseScreen;
import org.openqa.selenium.By;

public class ProfileScreen extends BaseScreen {
    private final By usernameField = By.id("username_field");

    public String getUsername() {
        return getText(usernameField);
    }
}