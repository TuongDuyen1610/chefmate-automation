package core.utils;

public class ErrorHandler {

    public static void handle(Exception e, String step) {
        System.err.println("❌ [ERROR] " + step + " → " + e.getMessage());
        e.printStackTrace();
    }

    public static void warn(String message) {
        System.out.println("⚠️ [WARNING] " + message);
    }
}