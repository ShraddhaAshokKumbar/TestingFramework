package automation.stepDefinitions;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And; // <-- Added the And import
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ClientSteps {

        WebDriver driver;
        WebDriverWait wait;
        String baseUrl = "https://trueclaim-fe.onrender.com/";

        @Before
        public void setup() {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        }

        // ==========================================
        // BACKGROUND (Runs before every scenario)
        // ==========================================
        @Given("the user is on the login page")
        public void the_user_is_on_the_login_page() {
            driver.get(baseUrl + "login");
        }

        @When("the user logs in with valid client credentials")
        public void the_user_logs_in_with_valid_client_credentials() {
            driver.findElement(By.xpath("//app-ui-input[@label='Email']//input")).sendKeys("test@test.com");
            driver.findElement(By.xpath("//app-ui-input[@label='Password']//input")).sendKeys("Pass123");
            driver.findElement(By.cssSelector("button[type='submit']")).click();
        }

        @Then("the user should be redirected to the dashboard")
        public void the_user_should_be_redirected_to_the_dashboard() {
            wait.until(ExpectedConditions.urlContains("dashboard"));
            Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Not redirected to dashboard");
        }

        // ==========================================
        // SCENARIO 1: Request a Home Insurance Policy
        // ==========================================
        @When("the user navigates to the policies list")
        public void the_user_navigates_to_the_policies_list() {
            driver.findElement(By.cssSelector("a[href='/policies']")).click();
            wait.until(ExpectedConditions.urlContains("policies"));
        }

        @And("the user clicks request on a policy")
        public void the_user_clicks_request_on_a_policy() {
            driver.findElement(By.cssSelector("a[href^='/policies/']")).click();
            WebElement requestBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Request')]")));
            requestBtn.click();
        }

        @Then("the policy request status should show as {string}")
        public void the_policy_request_status_should_show_as(String expectedStatus) {
            wait.until(ExpectedConditions.urlContains("my-requests"));
            WebElement statusBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), '" + expectedStatus + "')]")));
            Assert.assertTrue(statusBadge.isDisplayed(), "Status is not showing as " + expectedStatus);
        }

        // ==========================================
        // SCENARIO 2: Cancel a Pending Request
        // ==========================================
        @When("the user navigates to My Requests")
        public void the_user_navigates_to_my_requests() {
            driver.findElement(By.cssSelector("a[href='/my-requests']")).click();
            wait.until(ExpectedConditions.urlContains("my-requests"));
        }

        @And("the user clicks cancel on a pending request")
        public void the_user_clicks_cancel_on_a_pending_request() {
            WebElement cancelBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Cancel')]")));
            cancelBtn.click();
        }

        @Then("the request should be removed from the list")
        public void the_request_should_be_removed_from_the_list() {
            boolean isEmptyStateVisible = driver.findElements(By.cssSelector("app-ui-empty-state")).size() > 0;
            Assert.assertTrue(isEmptyStateVisible, "The request was not successfully removed.");
        }

        // ==========================================
        // SCENARIO 3: Session Timeout Handling
        // ==========================================
        @When("the user clears their browser session tokens")
        public void the_user_clears_their_browser_session_tokens() {
            driver.manage().deleteAllCookies();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.localStorage.clear();");
            js.executeScript("window.sessionStorage.clear();");
        }

        @And("the user attempts to navigate to the dashboard")
        public void the_user_attempts_to_navigate_to_the_dashboard() {
            driver.get(baseUrl + "dashboard");
        }

        @Then("the user should be redirected to the login page")
        public void the_user_should_be_redirected_to_the_login_page() {
            wait.until(ExpectedConditions.urlContains("login"));
            Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Session timeout did not redirect to login.");
        }


        // ==========================================
        // SCENARIO 4: Update Client Profile Details
        // ==========================================
        @When("the user navigates to the Profile page")
        public void the_user_navigates_to_the_profile_page() {
            driver.findElement(By.cssSelector("a[href='/profile']")).click();
            wait.until(ExpectedConditions.urlContains("profile"));
        }

        @And("the user attempts to update their date of birth to {string}")
        public void the_user_attempts_to_update_their_date_of_birth_to(String dob) {
            // Since the DOB field doesn't exist, we expect this to fail.
            // We will catch the exception and fail the test explicitly with a descriptive message.
            try {
                WebElement dobInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-ui-input[@label='Date of birth']//input")));
                dobInput.clear();
                dobInput.sendKeys(dob);
            } catch (org.openqa.selenium.TimeoutException e) {
                Assert.fail("Defect Found: The 'Date of birth' input field is completely missing from the Profile page.");
            }
        }

        @And("the user clicks the save changes button")
        public void the_user_clicks_the_save_changes_button() {
            // We won't reach this step because the test will fail on the previous step,
            // but this accurately reflects the intended action if the field did exist.
            WebElement saveBtn = driver.findElement(By.xpath("//button[contains(text(), 'Save')]"));
            Assert.assertTrue(saveBtn.isEnabled(), "Defect Found: The 'Save changes' button is disabled and unclickable.");
            saveBtn.click();
        }

        @Then("a success message should appear confirming the profile update")
        public void a_success_message_should_appear_confirming_the_profile_update() {
            WebElement successToast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("app-ui-alert[type='success']")));
            Assert.assertTrue(successToast.isDisplayed(), "Profile update success message did not appear.");
        }

        // ==========================================
        // TEARDOWN (Runs after every scenario)
        // ==========================================
        @After
        public void teardown(Scenario scenario) {
            if (scenario.isFailed()) {
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Screenshot of failure");
            }

            if (driver != null) {
                driver.quit();
            }
        }
    }

