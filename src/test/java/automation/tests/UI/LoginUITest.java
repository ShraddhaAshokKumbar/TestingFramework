package automation.tests.UI;

import automation.base.BaseTest;
import automation.pages.LoginPage;
import automation.utils.ConfigReader;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import java.time.Duration;

public class LoginUITest extends BaseTest {

    @Test
    public void verifySuccessfulLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickLoginBtn();
        wait.until(ExpectedConditions.urlContains("/login"));

        loginPage.setUserName(ConfigReader.getProperty("testEmail"));
        loginPage.setPassword(ConfigReader.getProperty("testPassword"));
        loginPage.clickLogin();

        wait.until(ExpectedConditions.urlContains("/dashboard"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard"), "Dashboard URL failed to load.");
    }

//    @AfterClass
//    void logout(){
//        loginPage.clicklogout();
//    }
}