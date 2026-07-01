package automation.api.services;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RegestrationService {

    public static Response registerUser(String name,
                                        String email,
                                        String password,
                                        String phone,
                                        String role,
                                        String dob,
                                        String gender) {

        Map<String, String> body = new HashMap<>();

        body.put("name", name);
        body.put("email", email);
        body.put("password", password);
        body.put("phone", phone);
        body.put("role", role);
        body.put("dob", dob);
        body.put("gender", gender);

        return given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post("/api/auth/register");
    }
}