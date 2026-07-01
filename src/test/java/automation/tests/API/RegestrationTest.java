package automation.tests.API;

import automation.api.services.GetPolicyService;
import automation.api.services.LoginService;
import automation.api.services.RegestrationService;
import automation.base.BaseApiTest;
import automation.base.BaseTest;

import org.testng.annotations.Test;


public class RegestrationTest extends BaseApiTest {

    @Test
    public void testRegisterUser() {

        RegestrationService.registerUser(
                        "devansh",
                        "devansh4@gmail.com",
                        "123456",
                        "7634287234",
                        "CLIENT",
                        "2000-06-12",
                        "male"
                )
                .then()
                .log().all()
                .statusCode(201); // Change if your API returns 200
    }
}