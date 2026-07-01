package automation.stepDefinations;

import automation.driver.DriverFactory;
import automation.utils.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void handleBddSetup() {
        // 1. Fetch parameters from your ConfigReader utility
        String browser = ConfigReader.getProperty("browser");
        String url = ConfigReader.getProperty("baseUrl");

        // 2. Initialize your ThreadLocal driver storage factory
        DriverFactory.initDriver(browser);
        DriverFactory.getDriver().manage().window().maximize();

        // 3. Navigate to your Application URL context
        DriverFactory.getDriver().get(url);
    }

    @After
    public void handleBddTeardown() {
        // 4. Safely release resources using your existing factory method
        DriverFactory.quitDriver();
    }
}