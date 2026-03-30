package core.base;

/**
 * BaseFlow.java
 *
 * MỤC ĐÍCH:
 * - Lớp cơ sở cho tất cả các Functional Flow (AuthenticationFlow, RecipeFlow, FridgeFlow...)
 * - Cho phép các Flow sau này kế thừa và mở rộng các phương thức chung
 * - Tách biệt rõ ràng giữa "luồng nghiệp vụ" và "tương tác UI"
 */
public abstract class BaseFlow {

    protected void logStep(String stepName) {
        System.out.println("📍 [FLOW] Executing: " + stepName);
    }
}