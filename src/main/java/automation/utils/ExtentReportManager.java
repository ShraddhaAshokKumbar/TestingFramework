package automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportManager {

    private static ExtentReports extentReports;
    private static ExtentTest test;
    private static final String REPORT_PATH = "test-output/ExtentReport.html";

    public static ExtentReports initExtentReports() {
        if (extentReports == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_PATH);
            sparkReporter.config().setReportName("Blood Bank Framework - Test Report");
            sparkReporter.config().setDocumentTitle("Test Execution Report");
            sparkReporter.config().setTheme(Theme.DARK);

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
            extentReports.setSystemInfo("OS", System.getProperty("os.name"));
            extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
            extentReports.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
            extentReports.setSystemInfo("Base URL", ConfigReader.getProperty("baseUrl"));
        }
        return extentReports;
    }

    public static ExtentReports getExtentReports() {
        if (extentReports == null) {
            initExtentReports();
        }
        return extentReports;
    }

    public static ExtentTest createTest(String testName){
        return getExtentReports().createTest(testName);
    }

    public static ExtentTest getTest(String testName){
        return createTest(testName);
    }

    public static void flush() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}