package automation.tests.API;

import automation.api.services.GetPolicyService;
import automation.api.services.LoginService;
import automation.base.BaseApiTest;
import automation.base.BaseTest;

import org.testng.annotations.Test;



public class LoginTest extends BaseApiTest {

    @Test
    public void testLoginUser() {

        LoginService.loginUser("devansh@gmail.com", "123456")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();   // change if API returns something else
    }
}