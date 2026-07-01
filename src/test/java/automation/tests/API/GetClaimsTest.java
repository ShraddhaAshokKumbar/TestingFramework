package automation.tests.API;

import automation.api.services.GetClaimsService;
import automation.api.services.LoginService;

import automation.base.BaseApiTest;
import org.testng.annotations.Test;


public class GetClaimsTest extends BaseApiTest {

    @Test
    public void getClaims() {

        // Login and get token
        String token = LoginService
                .loginUser("devansh@gmail.com", "123456")
                .jsonPath()
                .getString("token");

        // Create policy using token
        GetClaimsService.getClaims(
                        token,9)
                .then()
                .log().all()
                .statusCode(200);
    }
}