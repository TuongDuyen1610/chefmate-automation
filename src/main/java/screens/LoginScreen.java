//package screens;
//
//import core.base.BaseScreen;
//import core.utils.WaitingHelper;
//import org.openqa.selenium.By;
///**
// * LoginScreen.java
// *
// * MỤC ĐÍCH: Đại diện cho màn hình Đăng nhập
// * - Chứa locator và hành động trên màn hình Login
// */
//public class LoginScreen extends BaseScreen {
//
//    // ==================== LOCATORS (dựa trên xpath cung cấp) ====================
//    private final By phoneEmailField = By.xpath("//android.widget.ScrollView/android.widget.EditText[1]");
//    private final By passwordField = By.xpath("//android.widget.ScrollView/android.widget.EditText[2]");
//    private final By togglePassword = By.xpath("//android.widget.ScrollView/android.widget.EditText[2]/android.view.View/android.widget.Button");
//    // Nút Đăng nhập trên màn Login chính
//    private final By loginButton = By.xpath("//android.widget.ScrollView/android.view.View[2]/android.widget.Button");
//    private final By registerLink = By.xpath("//android.widget.TextView[@text='Đăng ký ngay']");
//
//    // ==================== ACTIONS ====================
//    public void enterPhoneOrEmail(String value) {
//        logStep("Nhap so dien thoai / email: " + value);
//        type(phoneEmailField, value);
//        WaitingHelper.sleepSeconds(1);   // Đợi UI cập nhật
//    }
//
//    public void enterPassword(String password) {
//        logStep("Nhap mat khau: ");
//        type(passwordField, password);
//        WaitingHelper.sleepSeconds(1);   // Đợi UI cập nhật
//    }
//
//    public void togglePasswordVisibility() {
//        logStep("Bat / Tat hien thi mat khau");
//        click(togglePassword);
//    }
//
//    /**
//     * Tên method đã chuẩn hóa thành clickLogin()
//     */
//    public void clickLogin() {
//        logStep("Nhan nut dang nhap tren man hinh Login");
//        // Cách 1: Wait clickable + scroll nếu cần
//        WaitingHelper.waitForClickable(loginButton);
//        WaitingHelper.sleepSeconds(2);     // Quan trọng: Đợi nút active sau khi nhập đủ thông tin
//
//        // Cách 2: Thử click nhiều lần nếu cần
//        try {
//            click(loginButton);
//            logStep("Da click nut dang nhap");
//        } catch (Exception e) {
//            logStep("Click lần 1 thất bại, thử lần 2...");
//            WaitingHelper.sleepSeconds(1);
//            click(loginButton);   // Thử lại
//        }
//    }
//
//    public boolean isLoginScreenDisplayed() {
//        return isDisplayed(phoneEmailField);
//    }
//}
package screens;

import core.base.BaseScreen;
import core.utils.WaitingHelper;
import org.openqa.selenium.By;

public class LoginScreen extends BaseScreen {

    // Dùng index hoặc contains để tránh lỗi encoding trên máy chị
    private final By phoneEmailField = By.xpath("//android.widget.EditText[1]");
    private final By passwordField = By.xpath("//android.widget.EditText[2]");

    // Nút Đăng nhập chính: Tìm tất cả Button có chứa chữ 'nhập'
    private final By loginButton = By.xpath("//android.widget.ScrollView/android.view.View[2]/android.widget.Button");

    public void enterPhoneOrEmail(String value) {
        logStep("Nhap tai khoan: " + value);
        type(phoneEmailField, value);
    }

    public void enterPassword(String password) {
        logStep("Nhap mat khau");
        type(passwordField, password);
    }

    public void clickLogin() {
        logStep("Nhan nut Dang nhap");
        WaitingHelper.waitForClickable(loginButton);
        click(loginButton);
        WaitingHelper.sleepSeconds(2);
    }

    public boolean isLoginScreenDisplayed() {
        // Kiểm tra xem trường nhập liệu có hiển thị không
        return isDisplayed(phoneEmailField);
    }
}