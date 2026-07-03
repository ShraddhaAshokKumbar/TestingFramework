package automation.tests.UI;

import automation.base.BaseTest;
import automation.pages.RequestPolicyPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RequestPolicyTest extends BaseTest {

    RequestPolicyPage rp= new RequestPolicyPage(driver);


    @Test
    public void request() {
        rp.clickPolicy();
        rp.ViewPolices();
        rp.request();
    }
}
