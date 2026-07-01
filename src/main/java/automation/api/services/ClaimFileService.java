package automation.api.services;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ClaimFileService {

    public static Response createClaim(String token,
                                       int policyRequestId,
                                       String incidentDate,
                                       String damagedItems,
                                       int claimAmount,
                                       String description) {

        Map<String, Object> body = new HashMap<>();

        body.put("policyRequestId", policyRequestId);
        body.put("incidentDate", incidentDate);
        body.put("damagedItems", damagedItems);
        body.put("claimAmount", claimAmount);
        body.put("description", description);

        return given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when()
                .post("/api/claims/file");
    }

}