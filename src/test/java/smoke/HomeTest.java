package smoke;

import core.base.BaseTest;
import flows.HomeFlow;
import org.testng.annotations.Test;

public class HomeTest extends BaseTest {
    @Test
    public void testHomePage() throws InterruptedException {
        HomeFlow homeFlow = new HomeFlow();
        homeFlow.validateHomePage();
    }
}