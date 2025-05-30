package com.Lone_soilder.tests;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class PutPatchDeleteExample {

    @Test
    public void testPut(){
        baseURI = "https://reqres.in/api";

        JSONObject request = new JSONObject();
        request.put("name", "Biswajit");
        request.put("Job", "employee");

        given().
                header("Content-Type", "application/json").
                body(request.toJSONString()).
        when().
                put("/users/2").
        then().
                statusCode(200)
                .log().all();

    }

    @Test
    public void testPatch(){
        baseURI= "https://reqres.in/api";

        JSONObject request = new JSONObject();
        request.put("name", "Biswajit");
        request.put("job", "cleaner");

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
        when().
                patch("/users/2").
        then().
                statusCode(200).
                log().all();

    }

    @Test
    public void testDelete(){
        baseURI= "https://reqres.in/api";

        when().
                delete("/users/2").
        then().
                statusCode(204)
                .log().all();

    }
}
