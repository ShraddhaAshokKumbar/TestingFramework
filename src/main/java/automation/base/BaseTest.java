package automation.base;

import automation.driver.DriverFactory;
import automation.utils.ConfigReader;
import automation.utils.ExtentReportManager;
import com.aventstack.extentreports.ExtentReports;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected WebDriver driver = driver = DriverFactory.getDriver();

    @BeforeMethod
    public void setUp() {
        String browser = ConfigReader.getProperty("browser");
        String url = ConfigReader.getProperty("baseUrl");

        logger.info("Initializing WebDriver for browser: {}", browser);
        DriverFactory.initDriver(browser);


        driver.manage().window().maximize();

        logger.info("Navigating to application target URL: {}", url);
        driver.get(url);
        logger.debug("WebDriver initialized successfully and contextual focus synchronized.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            logger.info("Quitting target browser instance context safely via DriverFactory.");
            DriverFactory.quitDriver();
        } else {
            logger.warn("Teardown lifecycle invoked, but the corresponding WebDriver instance was already null.");
        }
    }


}