package com.Lone_soilder.wipro;

import io.cucumber.java.BeforeAll;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class UserRegistration {

    @BeforeTest
    public static void setup() {
        baseURI = "https://bookstore.toolsqa.com/";
    }

    @Test
    public void userRegisterPostSuccessMethod(){
        baseURI = "https://bookstore.toolsqa.com/";

        JSONObject request = new JSONObject();
        request.put("userName", "BiswajitSahoooo"); // change the value each time you run
        request.put("password", "Biswajit@227"); // change the value each time you run

        given()
                .headers("Content-type","application/json")
                .body(request.toJSONString())
        .when()
                .post("Account/v1/User").
        then()
                .statusCode(201)
                //.body("userID", equalTo("663d3701-a347-4d27-bc07-7edad3369c31"))
                //.body("UserID", notNullValue())
                .body("username", equalTo("BiswajitSahoooo"))
                .body("books", notNullValue())         // Validate that 'books' field exists and is not null
                .body("books.size()", equalTo(0))
                .log().all();
    }

    @Test
    public void userRegisterPostFailureMethod(){
        JSONObject request = new JSONObject();
        request.put("userName", "geethaaug");
        request.put("password", "Geetha@22");

        given()
                .headers("Content-type","application/json")
                .body(request.toJSONString())
        .when()
                .post("Account/v1/User").
        then()
                .statusCode(406).
                body("code", equalTo("1204"))
                .body("message", equalTo("User exists!"));
    }
}
