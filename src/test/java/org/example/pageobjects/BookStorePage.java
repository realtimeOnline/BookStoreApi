package org.example.pageobjects;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.util.HashMap;
import java.util.Map;

public class BookStorePage {
    private static final String BASE_URL = "https://bookstore.toolsqa.com";
    private static final String BOOKS_ENDPOINT = "/BookStore/v1/Books";
    private static final String DELETE_BOOK_ENDPOINT = "/BookStore/v1/Book";
    private static final String USER_ENDPOINT = "/Account/v1/User";

    public Response addBook(String token, String userId, String isbn) {
        Map<String, Object> body = new HashMap<>();
        body.put("userId", userId);
        body.put("collectionOfIsbns", new Object[]{new HashMap<String, String>() {{ put("isbn", isbn); }}});
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when()
                .post(BOOKS_ENDPOINT);
    }

    public Response updateBook(String token, String userId, String isbn, String newIsbn) {
        Map<String, String> body = new HashMap<>();
        body.put("userId", userId);
        body.put("isbn", newIsbn);
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when()
                .put(BOOKS_ENDPOINT+"/"+isbn);
    }

    public Response deleteBook(String token, String userId, String isbn) {
        Map<String, String> body = new HashMap<>();
        body.put("isbn", isbn);
        body.put("userId", userId);
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when()
                .delete(DELETE_BOOK_ENDPOINT);
    }

}

