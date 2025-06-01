package com.Lone_soilder.api.applicationApi;

import com.Lone_soilder.api.RestResource;
import com.Lone_soilder.pojo.Playlist;
import com.Lone_soilder.utils.ConfigLoader;
import io.restassured.response.Response;

import static com.Lone_soilder.api.Route.PLAYLISTS;
import static com.Lone_soilder.api.Route.USERS;
import static com.Lone_soilder.api.TokenManager.getToken;

public class PlaylistApi {

    static String accessToken = "BQBvcgy9dowN4bjpdwye0507ubP5JZNra-Bwj9dPnNCiTdNH3RCadKiY2vUf7QoQMXkcMJLbL9jp1WpccS3pQzEtYl3cZjzcl10SZgjw81q0Lp5iP0rYfHUSoqoqvOgIIUprqTLIv-dJifG46gCJoeCte0SHp-NLJnM8TILff65hnTAidVaGFcvnOCVLbaeR4Ni-_hmoS1aUzrJBEI755c_AkdYuWuzEGDEppXHV5T9qcGoKfCkLSWSyju0RdRFfg5vJqs0xphCZ1uCAOXKTakt8N69ET_NqjYS6oVsOpx3EokQlv1nSQ5WS";
    public static Response post (Playlist requestPlaylist){
        return RestResource.post(USERS +"/" + ConfigLoader.getInstance().getUserId() +  PLAYLISTS, getToken() , requestPlaylist);
        //return RestResource.post(USERS +"/" + ConfigLoader.getInstance().getUserId() +  PLAYLISTS, "BQBvcgy9dowN4bjpdwye0507ubP5JZNra-Bwj9dPnNCiTdNH3RCadKiY2vUf7QoQMXkcMJLbL9jp1WpccS3pQzEtYl3cZjzcl10SZgjw81q0Lp5iP0rYfHUSoqoqvOgIIUprqTLIv-dJifG46gCJoeCte0SHp-NLJnM8TILff65hnTAidVaGFcvnOCVLbaeR4Ni-_hmoS1aUzrJBEI755c_AkdYuWuzEGDEppXHV5T9qcGoKfCkLSWSyju0RdRFfg5vJqs0xphCZ1uCAOXKTakt8N69ET_NqjYS6oVsOpx3EokQlv1nSQ5WS" , requestPlaylist);

    }
    public static Response post (String token , Playlist requestPlaylist){
        return RestResource.post(USERS +"/"+ ConfigLoader.getInstance().getUserId() + PLAYLISTS , token , requestPlaylist);
    }
    public static Response get (String playlistId){
       return RestResource.get(PLAYLISTS +"/"+ playlistId , getToken());
       // return RestResource.get(PLAYLISTS +"/"+ playlistId , accessToken);
    }
    public static Response update( Playlist requestPlaylist , String playlistId){
        return RestResource.update(PLAYLISTS +"/"+ playlistId , getToken() , requestPlaylist);
        //return RestResource.update(PLAYLISTS +"/"+ playlistId , accessToken , requestPlaylist);
    }
}
