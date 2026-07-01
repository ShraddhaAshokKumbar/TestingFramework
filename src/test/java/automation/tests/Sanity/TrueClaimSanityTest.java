package automation.tests.Sanity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class TrueClaimSanityTest {

    WebDriver driver;
    String baseUrl = "https://trueclaim-fe.onrender.com/";

    // Class-level variable so the login test uses the exact account we just registered
    String uniqueEmail;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Implicit wait for standard element rendering
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // Add this so the listener can access the active browser session
    public WebDriver getDriver() {
        return driver;
    }

    // TC-SAN-001: Application Launch
    @Test(priority = 1)
    public void testApplicationLaunch() {
        driver.get(baseUrl);

        Assert.assertEquals(driver.getCurrentUrl(), baseUrl, "Landing page did not load correctly.");

        WebElement navBar = driver.findElement(By.cssSelector("app-site-navbar nav"));
        Assert.assertTrue(navBar.isDisplayed(), "Navigation bar is not visible!");
    }

    // TC-SAN-002: User Registration
    @Test(priority = 2)
    public void testUserRegistration() {
        driver.findElement(By.cssSelector("nav a[href='/register'].btn-primary")).click();

        // 1. Generate a unique email to prevent duplicate account errors
        uniqueEmail = "testuser" + System.currentTimeMillis() + "@test.com";

        // 2. Fill all standard fields
        driver.findElement(By.xpath("//app-ui-input[@label='Full name']//input")).sendKeys("Test User");
        driver.findElement(By.xpath("//app-ui-input[@label='Email']//input")).sendKeys(uniqueEmail);
        driver.findElement(By.xpath("//app-ui-input[@label='Password']//input")).sendKeys("Pass123");

        WebElement roleDropdown = driver.findElement(By.cssSelector("select[formcontrolname='role']"));
        new Select(roleDropdown).selectByValue("CLIENT");

        // 3. Fill the extra required fields
        driver.findElement(By.xpath("//app-ui-input[@label='Phone Number']//input")).sendKeys("9876543210");
        driver.findElement(By.xpath("//app-ui-input[@label='Date of birth']//input")).sendKeys("01-01-2000");

        WebElement genderDropdown = driver.findElement(By.cssSelector("app-ui-input[label='Gender'] select"));
        new Select(genderDropdown).selectByValue("male");

        // 4. Submit form
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // 5. Smart Wait: Allow up to 20 seconds for the backend to process and redirect
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("login"));

        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "User was not redirected to the login page after registration.");
    }

    // TC-SAN-003: User Login
    @Test(priority = 3)
    public void testUserLogin() {
        if (!driver.getCurrentUrl().contains("login")) {
            driver.get(baseUrl + "login");
        }

        // Use the exact unique email generated in the previous step
        driver.findElement(By.xpath("//app-ui-input[@label='Email']//input")).sendKeys(uniqueEmail);
        driver.findElement(By.xpath("//app-ui-input[@label='Password']//input")).sendKeys("Pass123");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Smart Wait: Allow up to 20 seconds for the dashboard to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("dashboard"));

        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "User was not redirected to the dashboard after login.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}