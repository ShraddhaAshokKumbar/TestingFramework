package automation.tests.API;

import automation.api.services.GetPolicyService;
import automation.api.services.LoginService;
import automation.base.BaseApiTest;
import automation.base.BaseTest;

import org.testng.annotations.Test;


public class GetPolicyTest extends BaseApiTest {

    @Test
    public void testCreatePolicy() {

        // Login and get token
        String token = LoginService
                .loginUser("agent@gmail.com", "12345678")
                .jsonPath()
                .getString("token");

        // Create policy using token
        GetPolicyService.getPolicy(
                        token)
                .then()
                .log().all()
                .statusCode(200);
    }
}