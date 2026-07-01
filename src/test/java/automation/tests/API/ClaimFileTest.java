package automation.tests.API;

import automation.api.services.ClaimFileService;
import automation.api.services.LoginService;

import automation.base.BaseApiTest;
import org.testng.annotations.Test;

public class ClaimFileTest extends BaseApiTest {

    @Test
    public void getClaims() {

        // Login and get token
        String token = LoginService
                .loginUser("devansh@gmail.com", "123456")
                .jsonPath()
                .getString("token");

        // Create policy using token
        ClaimFileService.createClaim(
                        token,
                        20,
                        "2026-05-20",
                        "Farmland",
                        5000,
                        "Birds destroyed farm "
                )
                .then()
                .log().all()
                .statusCode(201);
    }
}