package org.example.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.example.pageobjects.AuthPage;
import org.example.pageobjects.BookStorePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Book Store API Tests")
public class BookStoreApiTest {
    private String token;
    private String userId;
    private final String isbn = "9781449325862"; // Example ISBN
    private final String newIsbn = "9781449331818"; // Example new ISBN
    private AuthPage authPage;
    private BookStorePage bookStorePage;

    @BeforeMethod
    public void setUp() {
        authPage = new AuthPage();
        bookStorePage = new BookStorePage();
        token = authPage.generateToken();
        userId = authPage.getUserId();
    }

    @Test(description = "Add a book to the user's collection")
    @Feature("POST Book")
    @Description("Test to add a book to the user's collection using POST API.")
    public void testAddBook() {
        System.out.println("token: " + token);
        System.out.println("userId: " + userId);
        Response response = bookStorePage.addBook(token, userId, isbn);
        Assert.assertEquals(response.getStatusCode(), 201, "Book not added successfully");
    }

    @Test(description = "Update a book in the user's collection", dependsOnMethods = "testAddBook")
    @Feature("PUT Book")
    @Description("Test to update a book in the user's collection using PUT API.")
    public void testUpdateBook() {
        Response response = bookStorePage.updateBook(token, userId, isbn, newIsbn);
        Assert.assertEquals(response.getStatusCode(), 200, "Book not updated successfully");
    }

    @Test(description = "Delete a book from the user's collection", dependsOnMethods = "testUpdateBook")
    @Feature("DELETE Book")
    @Description("Test to delete a book from the user's collection using DELETE API.")
    public void testDeleteBook() {
        Response response = bookStorePage.deleteBook(token, userId, newIsbn);
        Assert.assertEquals(response.getStatusCode(), 204, "Book not deleted successfully");
    }
}

