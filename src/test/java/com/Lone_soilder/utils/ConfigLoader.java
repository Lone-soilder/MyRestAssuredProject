package com.Lone_soilder.utils;

import java.io.FileNotFoundException;
import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;


    private ConfigLoader() {
        try {
            properties = PropertyUtils.propertyLoader("src/test/resources/config.properties") ;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static ConfigLoader getInstance(){
        if (configLoader == null){
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getClientId(){
        String prop = properties.getProperty("client_id");
        if (prop != null){
            return prop;
        }else {
            throw new RuntimeException("client id is not specified for config.properties file");
        }
    }

    public String getAuth_grant_type(){
        return properties.getProperty("auth_grant_type");
    }

    public String getGrantType(){
        String prop = properties.getProperty("grant_type");
        if (prop != null){
            return prop;
        }else {
            throw new RuntimeException("grant type is not specified for config.properties file");
        }
    }

    public String getRefreshToken(){
        String prop = properties.getProperty("refresh_token");
        if (prop != null){
            return prop;
        }else {
            throw new RuntimeException("refresh token is not specified for config.properties file");
        }
    }

    public void setRefreshToken(String refreshToken){
        properties.setProperty("refresh_token",refreshToken);
    }

    public void setAccessToken(String accessToken){
        properties.setProperty("access_token",accessToken);
    }

    public String getAuthCode(){
        String prop = properties.getProperty("auth_code");
        if (prop != null){
            return prop;
        }
        else {
            throw new RuntimeException(("auth code is not specified in config.properties file"));
        }
    }

    public String getRedirectUrl(){
        String prop = properties.getProperty("redirect_uri");
        if (prop != null){
            return prop;
        }
        else {
            throw new RuntimeException("Redirect URL is wrong");
        }
    }


    public String getUserId(){
        String prop = properties.getProperty("user_id");
        if (prop != null){
            return prop;
        }else {
            throw new RuntimeException("user_id is not specified for config.properties file");
        }
    }





}
