package automation.utils;

import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;

import java.io.File;

import java.io.IOException;

import java.nio.file.Files;

import java.nio.file.Paths;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {

    private static final String SCREENSHOT_DIR = "test-output/screenshots/";

    /**

     * Captures a screenshot and returns it as a Base64 string.

     * Useful for standalone HTML reporting (Extent Reports).

     */

    public static String captureScreenshotAsBase64(WebDriver driver) {

        try {

            TakesScreenshot screenshot = (TakesScreenshot) driver;

            // Capture directly as a Base64 string

            return screenshot.getScreenshotAs(OutputType.BASE64);

        } catch (Exception e) {

            e.printStackTrace();

            return null;

        }

    }

    /**

     * Keeping your original method in case you still want to physically

     * save files to the 'test-output/screenshots/' folder for archiving.

     */

    public static String captureScreenshot(WebDriver driver, String testName) {

        try {

            Files.createDirectories(Paths.get(SCREENSHOT_DIR));

            String timestamp = LocalDateTime.now()

                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-SSS"));

            String fileName = testName + "_" + timestamp + ".png";

            String filePath = SCREENSHOT_DIR + fileName;

            TakesScreenshot screenshot = (TakesScreenshot) driver;

            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

            File destFile = new File(filePath);

            Files.copy(srcFile.toPath(), destFile.toPath());

            return filePath;

        } catch (IOException e) {

            e.printStackTrace();

            return null;

        }

    }

}
