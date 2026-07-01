package automation.tests.API;

import automation.api.services.GetPolicyService;
import automation.api.services.LoginService;
import automation.base.BaseApiTest;
import automation.base.BaseTest;

import org.testng.annotations.Test;

public class InvalidLoginTest extends BaseApiTest {

    @Test
    public void testLoginUser() {

        LoginService.loginUser("wrong@gmail.com", "123456")
                .then()
                .log().all()
                .statusCode(404)
                .extract().response();   // change if API returns something else
    }
}