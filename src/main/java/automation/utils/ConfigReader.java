package automation.utils;
import java.io.FileInputStream;

import java.io.File;

import java.io.IOException;

import java.util.Properties;

public class ConfigReader {
    //https://trueclaim-fe.onrender.com/dashboard

    private static final Properties properties = new Properties();

    static {

        String mainPath = "src/main/resources/config.properties";

        String testPath = "src/test/resources/config.properties";

        String finalPath = new File(mainPath).exists() ? mainPath : testPath;

        try (FileInputStream fis = new FileInputStream(finalPath)) {

            properties.load(fis);

        } catch (IOException e) {

            throw new RuntimeException("Critical Failure: Configuration file could not be loaded at target resource path: " + finalPath, e);

        }

    }

    public static String getProperty(String key) {

        String value = properties.getProperty(key);

        if (value == null) {

            throw new RuntimeException("Target property key '" + key + "' was not found inside the configurations runtime store.");

        }

        return value.trim();

    }

}
