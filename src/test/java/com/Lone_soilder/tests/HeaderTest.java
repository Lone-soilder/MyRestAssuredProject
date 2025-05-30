package com.Lone_soilder.tests;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class HeaderTest {
    @Test
    public void getByHeader1(){
        baseURI = "https://reqres.in/api";

        given().
                header("Custom-Header", "HeaderValue").
        when().
                get("/users?page=2").
        then().
                body("page", equalTo(2));
    }

    @Test
    public void getByHeader2(){
        baseURI = "https://reqres.in/api";
        Header customHeader = new Header("Custom-Header", "HeaderValue");

        given().
                header(customHeader).
        when().
                get("/users?page=2").
        then().
                body("page", equalTo(2));
    }

    @Test
    public void getByHeader3(){
        baseURI = "https://reqres.in.api";
        HashMap<String, String> reqHeaders = new HashMap<String, String>();
        reqHeaders.put("Custom-Header", "HeaderValue");

        given().
                headers(reqHeaders).
                log().headers().
        when().
                get("https://reqres.in/api/users/2").
        then().
                body("data.id", equalTo(2));


    }

    @Test
    public void ResponseHeaderAssert(){
        baseURI = "https://reqres.in.api";
        HashMap<String, String> resHeaders = new HashMap<String, String>();
        resHeaders.put("Content-Type", "application/json; charset=utf-8");

        given().
                get("https://reqres.in/api/users/2").
        then().
                log().headers().
                assertThat().
                statusCode(200).
                header("Content-Type", equalTo("application/json; charset=utf-8"));
    }

    @Test
    public void printResponseHeader(){

        Headers responseHeaders=
                given().
                        header("Custom-Header", "HeaderValue").
                        get("https://reqres.in/api/users/2").
                then().
                        assertThat().
                        statusCode(200).
                        extract().
                        headers();
        for(Header header: responseHeaders){
            System.out.print("header name = "+ header.getName() + ", ");
            System.out.println("header value = " + header.getValue());
        }
    }
}
