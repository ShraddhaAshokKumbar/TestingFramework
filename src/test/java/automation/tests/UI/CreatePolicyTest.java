package automation.tests.UI;

import automation.base.BaseTest;
import automation.pages.CreatePolicyPage;
import automation.pages.LoginPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class CreatePolicyTest extends BaseTest {

    CreatePolicyPage ct = new CreatePolicyPage(driver);
    LoginUITest login=new LoginUITest();



    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    @BeforeMethod
    public void initialSetUp() throws InterruptedException {

        login.verifySuccessfulLogin();



    }

    @Test
    public void CreatePolicy() throws InterruptedException {
        ct.clickPolicyTab();
        ct.setPolicyName("Farm Policy");
        ct.setPremiumType("Monthly");
        ct.setAmount("2000");

        ct.clickPolicy();

        wait.until(ExpectedConditions.urlContains("/agent/policies"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/agent/policies"), "Unable to create policies");

//        Assert.assertEquals(ct.getUrl(), "https://trueclaim-fe.onrender.com/agent/policies");

        // Action: Performs logout immediately after validating policy creation
//        login.logout();
//        Thread.sleep(3000);
    }
}