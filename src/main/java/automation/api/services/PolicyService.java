package automation.api.services;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PolicyService {

    public static Response createPolicy(String token,
                                        String name,
                                        String premiumType,
                                        int price,
                                        int agentId) {

        System.out.println(token);
        Map<String, Object> body = new HashMap<>();

        body.put("name", name);
        body.put("premiumType", premiumType);
        body.put("price", price);

        return given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when()
                .post("/api/policies/agent/" + agentId + "/create");
    }
}
