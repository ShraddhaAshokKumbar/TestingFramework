package automation.api.services;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetPolicyService {

    public static Response getPolicy(String token) {

        System.out.println(token);

        return given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/api/policies");
    }
}
