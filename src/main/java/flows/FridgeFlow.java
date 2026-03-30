package flows;

import core.base.BaseFlow;
import screens.FridgeScreen;
import screens.HomeScreen;

public class FridgeFlow extends BaseFlow {

    private final HomeScreen home = new HomeScreen();
    private final FridgeScreen fridge = new FridgeScreen();

    public void openFridge() {
        logStep("Từ Home mở Tủ lạnh");
        // Sử dụng Bottom Navigation (sẽ có NavigationFlow hỗ trợ)
        home.openGoiYTuTuLanh(); // hoặc click bottom nav
    }

    public void addIngredient(String name, String qty, String unit) {
        logStep("Thêm nguyên liệu vào tủ lạnh");
        fridge.addIngredient(name, qty, unit);
    }
}