package automation.tests.UI;

import automation.base.BaseTest;
import automation.pages.RegisterPage;
import automation.utils.ConfigReader;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class RegisterUITest extends BaseTest {

    @Test
    void verifyUserRegistrationFlow() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        RegisterPage registerPage = new RegisterPage(driver);



        registerPage.ClickSignUpBtn();
        wait.until(ExpectedConditions.urlContains("/register"));

        registerPage.setFullName("Automation Tester");
        registerPage.setEmailField("tester@test.com");

        registerPage.setPasswordField("123456");
        registerPage.setPhoneField("9876543210");
        registerPage.setRoleSelectField("Client");
        registerPage.setGenderField("Male");
        registerPage.setDate("12-06-2026");
        registerPage.register();
        wait.until(ExpectedConditions.urlContains("/dashboard"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard"), "Dashboard URL failed to load.");
  }
}