package com.Lone_soilder.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static com.Lone_soilder.api.Route.BASE_PATH;

public class SpecBuilder {



    public static RequestSpecification getRequestSpec(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://api.spotify.com").
                setBasePath(BASE_PATH).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL);

        return  requestSpecBuilder.build();
    }

    public static RequestSpecification getAccountRequestSpec(){
        return  new RequestSpecBuilder().
                setBaseUri("https://accounts.spotify.com").
                setContentType(ContentType.URLENC).
                addHeader("Authorization", "Basic "+"ZmFjZWRjODM1YzY1NDYwNDhkMGFkOWQwMzJjYTNjNTY6ZDQ3MDQ3MWM2NmU3NDBjMmI5MjdlNjNiZGJiNThmNjQ").
                log(LogDetail.ALL).
                build();
    }
    public static ResponseSpecification getResponseSpec(){
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                log(LogDetail.ALL);

        return responseSpecBuilder.build();
    }
}
