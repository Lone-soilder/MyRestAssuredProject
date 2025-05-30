package com.Lone_soilder.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class TestExample {

    @Test
    public void test_1(){
        Response response = RestAssured.get("https://api.restful-api.dev/objects");
        response.getStatusCode();
        response.getTime();
        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void test_2(){

        baseURI = "https://api.restful-api.dev";

        given().
                get("/objects")
                .then()
                .statusCode(200)
                .body("data[0].color", equalTo("Cloudy White"));

    }
}
