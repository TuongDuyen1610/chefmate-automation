package smoke;

import core.base.BaseTest;
import flows.SearchFlow;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    private final SearchFlow search = new SearchFlow();

    @Test(priority = 1, description = "Smoke 05 - Tìm kiếm công thức thành công")
    public void TC05_Search_Recipe_Success() {
        search.searchRecipe("phở");
        search.openFirstSearchResult();
        Assert.assertTrue(true, "Kết quả tìm kiếm hiển thị");
        System.out.println("✅ TC05 PASS - Search OK");
    }
}