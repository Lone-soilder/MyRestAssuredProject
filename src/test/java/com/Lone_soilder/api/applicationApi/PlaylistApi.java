package com.Lone_soilder.api.applicationApi;

import com.Lone_soilder.api.RestResource;
import com.Lone_soilder.pojo.Playlist;
import com.Lone_soilder.utils.ConfigLoader;
import io.restassured.response.Response;

import static com.Lone_soilder.api.Route.PLAYLISTS;
import static com.Lone_soilder.api.Route.USERS;
import static com.Lone_soilder.api.TokenManager.getToken;

public class PlaylistApi {

    //static String accessToken = "BQBkmVAJBJmwTVDfpVhXRNUdSyaPhxMbaYO30nw9Jwlw78xjUXaQ7k6hg-0e_6vRk-LUQ09MdzYIDGoXz5Bi6yKuwZw3EPOhbB7jgA7YlFcoZ_nRL6vd5gLE7YENpcyoIvOBArrwiSofR6H7gIVUbZQINBWHPsiDViiNNgdP2RXcy43A223_2_7pXG69MLINLalBxpjFhx0MDOTi4k7cdKUvtflq_gifgbr0UuBxkFh1vEPXxDtDg_LHOtTTFidkwbevkVlzAYkt8akIV2OLeiHtI3sc-31yOlXEgT2_DodNf8GcQhcWP50E";

    public static Response post (Playlist requestPlaylist){
        return RestResource.post(USERS +"/" + ConfigLoader.getInstance().getUserId() +  PLAYLISTS, getToken() , requestPlaylist);
    }
    public static Response post (String token , Playlist requestPlaylist){
        return RestResource.post(USERS +"/"+ ConfigLoader.getInstance().getUserId() + PLAYLISTS , token , requestPlaylist);
    }
    public static Response get (String playlistId){
        return RestResource.get(PLAYLISTS +"/"+ playlistId , getToken());
    }
    public static Response update( Playlist requestPlaylist , String playlistId){
        return RestResource.update(PLAYLISTS +"/"+ playlistId , getToken() , requestPlaylist);
    }
}
