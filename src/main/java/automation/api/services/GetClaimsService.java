package automation.api.services;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetClaimsService {

    public static Response getClaims(String token, int clientId) {

        System.out.println(token);

        return given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/api/claims/client/"+clientId);
    }
}
