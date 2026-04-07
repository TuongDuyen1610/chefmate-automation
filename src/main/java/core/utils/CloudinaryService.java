package core.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Properties;
import java.io.InputStream;
import java.io.File;
import java.util.Map;

/**
 * CloudinaryService.java
 * ✅ Upload screenshots to Cloudinary
 */
public class CloudinaryService {
    private static final Logger logger = LoggerFactory.getLogger(CloudinaryService.class);

    private static Cloudinary cloudinary;

    static {

        try {

            Properties prop = new Properties();

            InputStream input =
                    CloudinaryService.class
                            .getClassLoader()
                            .getResourceAsStream(
                                    "config/application.properties"
                            );

            prop.load(input);

            cloudinary = new Cloudinary(
                    ObjectUtils.asMap(
                            "cloud_name",
                            prop.getProperty("cloudinary.cloud_name"),

                            "api_key",
                            prop.getProperty("cloudinary.api_key"),

                            "api_secret",
                            prop.getProperty("cloudinary.api_secret")
                    )
            );

            logger.info("✅ Cloudinary initialized");

        } catch (Exception e) {

            logger.error("❌ Cloudinary init failed: " + e.getMessage());
        }
    }

    /**
     * ✅ Upload screenshot to Cloudinary
     */
    public static String uploadScreenshot(String imagePath, String publicId) {
        try {
            logger.info("📤 Uploading screenshot to Cloudinary: " + imagePath);

            File file = new File(imagePath);
            if (!file.exists()) {
                logger.error("❌ Image file not found: " + imagePath);
                return "N/A";
            }

            Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.asMap(
                    "public_id", publicId,
                    "resource_type", "auto",
                    "folder", "chefmate-automation"
            ));

            String url = (String) uploadResult.get("secure_url");
            logger.info("✅ Screenshot uploaded: " + url);

            return url;

        } catch (Exception e) {
            logger.error("❌ Failed to upload screenshot: " + e.getMessage());
            return "N/A";
        }
    }

    /**
     * ✅ Delete screenshot from Cloudinary
     */
    public static void deleteScreenshot(String publicId) {
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            logger.info("✅ Screenshot deleted: " + publicId);
        } catch (Exception e) {
            logger.error("❌ Failed to delete screenshot: " + e.getMessage());
        }
    }
}