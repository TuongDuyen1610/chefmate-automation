package flows;

import core.base.BaseFlow;
import screens.HomeScreen;
import screens.SearchScreen;

public class SearchFlow extends BaseFlow {

    private final HomeScreen home = new HomeScreen();
    private final SearchScreen search = new SearchScreen();

    public void searchRecipe(String keyword) {
        logStep("Tìm kiếm công thức: " + keyword);
        home.clickSearch();
        search.search(keyword);
    }

    public void openFirstSearchResult() {
        logStep("Mở kết quả tìm kiếm đầu tiên");
        search.openFirstResult();
    }
}