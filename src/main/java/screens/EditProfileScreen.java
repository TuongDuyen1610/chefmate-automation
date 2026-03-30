package screens;

import core.base.BaseScreen;
import org.openqa.selenium.By;

public class EditProfileScreen extends BaseScreen {

    private final By fullNameField = By.xpath("//android.widget.EditText[@text='Tuong Thi Duyen']");
    private final By emailField = By.xpath("//android.widget.EditText[@text='00000@gmail.com']");
    private final By saveButton = By.xpath("//android.widget.TextView[@text='Lưu thay đổi']");

    public void updateProfile(String newName, String newEmail) {
        type(fullNameField, newName);
        type(emailField, newEmail);
        click(saveButton);
    }
}