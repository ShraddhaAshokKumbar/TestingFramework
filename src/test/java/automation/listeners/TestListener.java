package automation.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import automation.driver.DriverFactory;
import automation.utils.ExtentReportManager;
import automation.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private ExtentTest extentTest;
    private ExtentReports extentReports;

    @Override
    public void onStart(ITestContext context) {
        extentReports = ExtentReportManager.initExtentReports();
    }

    @Override
    public void onTestStart(ITestResult result) {
//        extentTest = extentReports.createTest(
//                result.getTestClass().getRealClass().getSimpleName() + " - " + result.getMethod().getMethodName(),
//                result.getMethod().getDescription() != null ? result.getMethod().getDescription() : ""
//        );
        extentTest = ExtentReportManager.getTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.pass("Test passed successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.fail(result.getThrowable());

        // Capture screenshot on failure
        try {
            WebDriver driver = DriverFactory.getDriver();
            if (driver != null) {
                String screenshotPath = ScreenshotUtil.captureScreenshot(
                        driver,
                        result.getMethod().getMethodName()
                );

                // Attach screenshot to report
                if (screenshotPath != null) {
                    extentTest.fail("Test Failed - Screenshot attached below:")
                            .addScreenCaptureFromPath(screenshotPath);
                }
            }
        } catch (Exception e) {
            extentTest.info("Failed to capture screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.skip("Test skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not implemented
    }
//
    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.flush();
    }
}