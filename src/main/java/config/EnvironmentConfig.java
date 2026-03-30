package config;

import java.io.InputStream;
import java.util.Properties;

public class EnvironmentConfig {

    private static final Properties properties = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (InputStream input = EnvironmentConfig.class.getClassLoader()
                .getResourceAsStream("config/framework.properties")) {

            if (input == null) {
                throw new RuntimeException("❌ Không tìm thấy file framework.properties");
            }
            properties.load(input);
            System.out.println("✅ [CONFIG] Loaded framework.properties successfully");
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to load framework.properties", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static String get(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static int getInt(String key, int defaultValue) {
        try {
            return Integer.parseInt(get(key));
        } catch (Exception e) {
            return defaultValue;
        }
    }
}