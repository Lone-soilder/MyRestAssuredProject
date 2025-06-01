package com.Lone_soilder.utils;

import java.io.FileNotFoundException;
import java.util.Properties;

public class DataLoader {
    private final Properties properties;
    private static DataLoader dataLoader;


    private DataLoader() {
        try {
            properties = PropertyUtils.propertyLoader("src/test/resources/data.properties") ;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static DataLoader getInstance(){
        if (dataLoader == null){
            dataLoader = new DataLoader();
        }
        return dataLoader;
    }

    public String getGet_playlist_id(){
        String prop = properties.getProperty("get_playlist_id");
        if (prop != null){
            return prop;
        }else {
            throw new RuntimeException("get_playlist_id is not specified for data.properties file");
        }
    }

    public String getUpdate_playlist_id(){
        String prop = properties.getProperty("update_playlist_id");
        //properties.replace("update_playlist_id","i1234");
        if (prop != null){
            return prop;
        }else {
            throw new RuntimeException("update_playlist_id is not specified for data.properties file");
        }
    }

}
