package flows;

import core.base.BaseFlow;

/**
 * ShoppingListFlow.java
 *
 * MỤC ĐÍCH:
 * - Xử lý luồng "Lập danh sách mua sắm"
 * - Bao gồm thêm nguyên liệu thủ công và hoàn thành danh sách
 */
public class ShoppingListFlow extends BaseFlow {

    public void openShoppingList() {
        logStep("Mở màn Lập danh sách mua sắm từ menu");
        System.out.println("→ Mở Shopping List từ menu 3 gạch");
    }

    public void addManualIngredient(String name) {
        logStep("Thêm nguyên liệu thủ công: " + name);
        System.out.println("→ Đã thêm nguyên liệu: " + name);
    }

    public void completeList() {
        logStep("Hoàn thành danh sách mua sắm");
        System.out.println("→ Danh sách mua sắm đã hoàn thành");
    }
}