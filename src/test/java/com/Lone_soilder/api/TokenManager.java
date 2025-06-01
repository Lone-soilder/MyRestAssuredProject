package com.Lone_soilder.api;

import com.Lone_soilder.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import javax.sound.midi.Soundbank;
import java.time.Instant;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class TokenManager {

    //private static String access_token = "BQCfdf5faa0GFgttGBOzyqbyRkSXNayHN7NcFO0n8bS11eZt1ruyYaHPxoDUBxbw6yoremCUhsY3HENYks7lBzsCJ2LXOMpim-a-neUzse0fB3HFww2hvwvvzdZTyyrCcYgWK7ZnCYM8mel8xHVczgA2rLBUl_LrS1-RZjYJuRyGpVSRgeUGpvkOtly36bZwBzz2a3b238Rfy8SD1bmaWPx7exZevW9UfIgqRMUDP0ZfXfZZFJ8BPD16IRa7KrzbpsqBdec4hrOrkXNcQ7vwTU_FvmBuSsruUpBzMzxchwVaU1Q1sY6frFcN";

    private static String access_token;
    private static Instant expiryTime = Instant.now().plusSeconds(3600-300);;
    public static String getToken(){
        //try{
            Response response = getAccessToken();

            access_token= response.path("access_token");
            int expires_in = response.path("expires_in");
            expiryTime = Instant.now().plusSeconds(expires_in-300);

            System.out.println("expiry time of access token "+ expiryTime);

            if (Instant.now().isAfter(expiryTime) ) {
                response = renewToken();
                access_token = response.path("access_token");
                int expiryDurationInSeconds = response.path("expires_in");
                expiryTime = Instant.now().plusSeconds(expiryDurationInSeconds-300);
            }

        return access_token;
    }

    private static Response getAccessToken(){
        HashMap<String , String> formParams = new HashMap<>();

        formParams.put("redirect_uri" , ConfigLoader.getInstance().getRedirectUrl());
        formParams.put("grant_type", ConfigLoader.getInstance().getAuth_grant_type());
        formParams.put("code",ConfigLoader.getInstance().getAuthCode());

        Response response = RestResource.postAccount(formParams);
        if (response.statusCode() != 200){
            return renewToken();
            //throw  new RuntimeException("Abort !! access token failed");
        }else {
            ConfigLoader.getInstance().setRefreshToken(response.path("refresh_token"));
            ConfigLoader.getInstance().setRefreshToken(response.path("access_token"));
            return response;
        }
    }

    private static Response renewToken(){

        HashMap<String , String> formParams = new HashMap<>();

        formParams.put("client_id" , ConfigLoader.getInstance().getClientId());
        formParams.put("grant_type", ConfigLoader.getInstance().getGrantType());
        formParams.put("refresh_token",ConfigLoader.getInstance().getRefreshToken());

        System.out.println(formParams.get("client_id"));
        System.out.println(formParams.get("grant_type"));
        System.out.println(formParams.get("refresh_token"));

        Response response = RestResource.postAccount(formParams);
        if (response.statusCode() != 200){
            throw  new RuntimeException("Abort !! Renew token failed");
        }else {
            return response;
        }


    }
}
