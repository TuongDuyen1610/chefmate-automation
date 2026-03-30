package screens;

import core.base.BaseScreen;
import org.openqa.selenium.By;

public class SearchScreen extends BaseScreen {

    private final By searchEditText = By.xpath("//android.widget.EditText");
    private final By resultItem = By.xpath("//android.widget.ScrollView/android.view.View[1]");

    public void search(String keyword) {
        logStep("Tìm kiếm: " + keyword);
        type(searchEditText, keyword);
    }

    public void openFirstResult() {
        click(resultItem);
    }
}