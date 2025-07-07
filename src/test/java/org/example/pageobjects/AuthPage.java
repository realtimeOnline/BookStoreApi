package org.example.pageobjects;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.util.HashMap;
import java.util.Map;

public class AuthPage {
    private static final String BASE_URL = "https://bookstore.toolsqa.com";
    private static final String GENERATE_TOKEN_ENDPOINT = "/Account/v1/GenerateToken";
    private static final String USERNAME = "Rama.mgy";
    private static final String PASSWORD = "Pass@1234";
    private static final String USERID = "d6b44853-2b65-4942-8b3c-8fbcb770d1c4";


    public String generateToken() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("userName", USERNAME);
        credentials.put("password", PASSWORD);

        Response response = given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(credentials)
                .when()
                .post(GENERATE_TOKEN_ENDPOINT)
                .then()
                .statusCode(200)
                .extract().response();
        return response.jsonPath().getString("token");
    }

    public String getUserId() {
        return USERID;
    }
}

