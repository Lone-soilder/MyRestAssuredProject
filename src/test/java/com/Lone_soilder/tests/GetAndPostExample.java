package com.Lone_soilder.tests;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

@Test
public class GetAndPostExample {

     public void testGet(){
          baseURI = "https://api.restful-api.dev";

          given()
                  .get("/objects")
                  .then()
                  .statusCode(200)
                  .body("[2].name", equalTo("Apple iPhone 12 Pro Max"));

          given().get("/objects")
                  .then().body("[2].data.'capacity GB'", equalTo(512));
     }

     public void testPost(){
          baseURI = "https://reqres.in/api";

//          Map<String , Object> map = new HashMap<String, Object>();
//          map.put("name", "Biswajit");
//          map.put("Job", "Teacher");
//          System.out.println(map.toString());

          JSONObject request = new JSONObject();
          request.put("name", "Biswajit");
          request.put("job", "Teacher");
          System.out.println(request.toJSONString());

          given().
                  header("Content-Type", "application/json").         //optional
                  contentType(ContentType.JSON).accept(ContentType.JSON).  //optional
                  body(request.toJSONString()).
          when().
                  post("/users").
          then().
                  statusCode(201)
                  .log().all();

     }
}
