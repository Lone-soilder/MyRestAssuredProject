package com.Lone_soilder.api;

import com.Lone_soilder.pojo.Playlist;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static com.Lone_soilder.api.Route.API;
import java.util.HashMap;

import static com.Lone_soilder.api.Route.TOKEN;
import static com.Lone_soilder.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {

    public static Response post (String path , String accessToken , Object requestPlaylist){

        return given().
                    spec(getRequestSpec()).
                    body(requestPlaylist).
                    header("Authorization" , "Bearer "+ accessToken).
                when().
                    post(path).
                then().
                    spec(getResponseSpec()).
                    extract().response();
    }

    public static Response postAccount(HashMap<String , String> formParams){
        return given(getAccountRequestSpec()).
                    formParams(formParams).
                    //header("Authorization", "Basic "+"ZmFjZWRjODM1YzY1NDYwNDhkMGFkOWQwMzJjYTNjNTY6ZDQ3MDQ3MWM2NmU3NDBjMmI5MjdlNjNiZGJiNThmNjQ").
                when().
                    post(API+TOKEN).
                then().
                    spec(SpecBuilder.getResponseSpec()).
                    extract().response();

    }

    public static Response get (String path, String accessToken){
        return given().
                    spec(getRequestSpec()).
                    header("Authorization" , "Bearer "+ accessToken).
                when().
                    get(path).
                then().
                    spec(getResponseSpec()).
                    extract().
                    response();
    }

    public static Response update( String path, String accessToken ,Object requestPlaylist ){
        return given().
                    spec(getRequestSpec()).
                    header("Authorization" , "Bearer "+ accessToken).
                    body(requestPlaylist).
                when().
                    put(path).
                then().
                    spec(getResponseSpec()).
                    extract().
                    response();
    }
}
