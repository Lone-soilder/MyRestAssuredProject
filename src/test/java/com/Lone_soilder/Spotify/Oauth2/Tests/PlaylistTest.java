package com.Lone_soilder.Spotify.Oauth2.Tests;

import com.Lone_soilder.api.SpecBuilder;
import com.Lone_soilder.api.applicationApi.PlaylistApi;
import com.Lone_soilder.pojo.Error;
import com.Lone_soilder.pojo.InnerError;
import com.Lone_soilder.pojo.Playlist;
import com.Lone_soilder.utils.DataLoader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.Lone_soilder.api.SpecBuilder.getRequestSpec;
import static com.Lone_soilder.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTest {

    public Playlist playlistBuilder(String name , String description , boolean _public){
        return new Playlist().setName(name).setDescription(description).setPublic(_public);
    }

    public void assertPlaylistEqual(Playlist requestPlaylist , Playlist responsePlaylist ){
        assertThat(responsePlaylist.getName() , equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.getPublic(), equalTo(requestPlaylist.getPublic()));
    }

    public void assertStatusCode(int actual , int expected){
        assertThat(actual , equalTo(expected));
    }

    public void assertError(Error error , int expectedStatusCode , String message){
        assertThat(error.getError().getStatus(),equalTo(expectedStatusCode));
        assertThat(error.getError().getMessage(),equalTo(message));
    }

    @Test
    public void shouldBeAbleToCreatePlaylist() {
        // Implement the test logic for creating a playlist

        Playlist requestPlaylist = playlistBuilder("New Playlist 1 may26", "New playlist description", false);

        Response response = PlaylistApi.post(requestPlaylist);

        assertStatusCode(response.statusCode() , 201);

        assertPlaylistEqual(requestPlaylist , response.as(Playlist.class) );

    }

    @Test
    public void shouldBeAbleToGetPlaylist(){

        Playlist requestPlaylist = playlistBuilder("New Playlist 1 may26", "Biswajit playlist description", true);
        Response response = PlaylistApi.get(DataLoader.getInstance().getGet_playlist_id());
        assertStatusCode(response.statusCode(), 200);
        assertPlaylistEqual(requestPlaylist, response.as(Playlist.class));

    }

    @Test
    public void shouldBeAbleToUpdatePlaylist(){

        Playlist requestPlaylist = playlistBuilder("New Playlist 1 may26", "Biswajit playlist description", true);
        Response response = PlaylistApi.update(requestPlaylist, DataLoader.getInstance().getUpdate_playlist_id());
        assertStatusCode(response.statusCode() , 200);

    }

    @Test
    public void shouldNotBeAbleToCreatePlaylistWithName(){

        Playlist requestPlaylist = playlistBuilder("","Biswajit playlist description", false )
        Response response = PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(), 401);
        assertError(response.as(Error.class) , 401 , "Missing required field: name" );
    }

    @Test
    public  void shouldNotBeAbleToCreatePlaylistWithExpiredToken(){

        String invalid_token = "12345";
        Playlist requestPlaylist =  playlistBuilder("Biswajit Playlist 1 may26", "Biswajit playlist description", false)
        Response response = PlaylistApi.post(invalid_token , requestPlaylist);
        assertStatusCode(response.statusCode(),401);
        assertError(response.as(Error.class),401 , "Invalid access token");
    }




}
