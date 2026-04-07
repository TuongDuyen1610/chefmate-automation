package core.utils;

import core.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotUtil {

    public static String captureScreenshot(String testId) {

        try {

            File folder =
                    new File("target/cloudinary-screenshots");

            if (!folder.exists()) {
                folder.mkdirs();
            }

            String fileName =
                    testId + "_" + System.currentTimeMillis() + ".png";

            String path =
                    "target/cloudinary-screenshots/" + fileName;

            File src =
                    ((TakesScreenshot)
                            DriverManager.getDriver())
                            .getScreenshotAs(OutputType.FILE);

            Files.copy(
                    src.toPath(),
                    Paths.get(path)
            );

            return path;

        } catch (Exception e) {

            return null;
        }
    }
}