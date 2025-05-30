package com.Lone_soilder.api;

import com.Lone_soilder.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class TokenManager {

    private static String access_token;
    private static Instant expiryTime;
    public static String getToken(){
        try{
            if (access_token == null || Instant.now().isAfter(expiryTime) ) {
                Response response = renewToken();
                access_token = response.path("access_token");
                int expiryDurationInSeconds = response.path("expires_in");
                expiryTime = Instant.now().plusSeconds(expiryDurationInSeconds-300);
            }
            else {
                System.out.println("token is good to use...");
            }
        }catch (Exception e){
            throw new RuntimeException("Abort !! failed to get token");
        }
        return access_token;
    }

    private static Response renewToken(){

        HashMap<String , String> formParams = new HashMap<>();

        formParams.put("client_id" , ConfigLoader.getInstance().getClientId());
        formParams.put("grant_type", ConfigLoader.getInstance().getGrantType());
        formParams.put("refresh_token",ConfigLoader.getInstance().getRefreshToken());

        Response response = RestResource.postAccount(formParams);
        if (response.statusCode() != 200){
            throw  new RuntimeException("Abort !! Renew token failed");
        }else {
            return response;
        }


    }
}
