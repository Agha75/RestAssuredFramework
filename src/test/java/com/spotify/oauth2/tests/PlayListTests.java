package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.spotify.oauth2.pojos.Error;
import com.spotify.oauth2.pojos.Playlist;
import com.spotify.oauth2.utils.DataLoader;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.oauth2.utils.FakerUtils.generateDescription;
import static com.spotify.oauth2.utils.FakerUtils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Spotify OAuth2.0 Apis") //Collection which is used
@Feature("Playlist APIs") //Module which is used
public class PlayListTests extends BaseTest {

    @Story("QA-12345") //On which story you are working
    @Link("https://example.org") //link on which you are working
    @Link(name = "allure", type = "myLink")
    @TmsLink("TC-12345")  // Teams management system: used for test cases link
    @Issue("Bug-12345")   // If there is some issue raised related to the test case
    @Description("Playlist will be created")
    @Test(priority = 1, description = "Create playlist")
    public void createPlayListTest() {
        //Below function can be also used and also the constructor of this class can be also used
        //Playlist playlist = playlistBuilder("pojo playlist", "pojo example", false);

        // FakerApi Used for fake data
        Playlist playlist = playlistBuilder(generateName(), generateDescription(), false);

        Response response = PlaylistApi.post(playlist);

        // These functions are called for assertions from below in the same class
        // Enum are used for assertions and statusCode class is created for Enum to have separate class
        assertStatusCode(response.statusCode(), StatusCode.CODE_201.getCode());
        assertPlaylistEqual(response.as(Playlist.class), playlist);


        //this way is also correct
//        assertThat(response.getStatusCode(), equalTo(201));
//        Playlist deserializePlaylist = response.as(Playlist.class);
//        assertThat(deserializePlaylist.getName(), equalTo(playlist.getName()));
//        assertThat(deserializePlaylist.getDescription(), equalTo(playlist.getDescription()));
//        assertThat(deserializePlaylist.getPublic(), equalTo(playlist.getPublic()));
    }

    @Test(priority = 2, description = "Get playlist")
    public void getPlayListTest() {
        Playlist playlist = new Playlist();
        playlist.setName("pojo playlist");
        playlist.setDescription("pojo example");
        playlist.set_public(false);

        Response response = PlaylistApi.get(DataLoader.getInstance().getPlaylistId());
        assertThat(response.getStatusCode(), equalTo(200));

        Playlist deserializePlaylist = response.as(Playlist.class);
        assertThat(deserializePlaylist.getName(), equalTo(playlist.getName()));
        assertThat(deserializePlaylist.getDescription(), equalTo(playlist.getDescription()));
        assertThat(deserializePlaylist.get_public(), equalTo(playlist.get_public()));
    }

    @Test(priority = 3)
    public void updatePlayListTest() {
        Playlist playlist = new Playlist();
        playlist.setName("updated pojo playlist");
        playlist.setDescription("updated pojo example");
        playlist.set_public(false);

        Response response = PlaylistApi.put(playlist, DataLoader.getInstance().updatePlaylistId());
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test(groups = {"negative"}, priority = 4)
    public void Negative_createPlayListTest() {
        Playlist playlist = new Playlist();
        playlist.setName("");
        playlist.setDescription("empty name pojo example");
        playlist.set_public(false);

        Response response = PlaylistApi.post(playlist);
        assertThat(response.getStatusCode(), equalTo(400));

        //calling the above function for error assertion
        assertError(response.as(Error.class), 400, "Missing required field: name");

        //this way is also correct
//        Error error = response.as(Error.class);
//        assertThat(error.getError().getStatus(), equalTo(400));
//        assertThat(error.getError().getMessage(), equalTo("Missing required field: name"));
    }

    @Test(groups = {"negative"}, priority = 5)
    public void negativeAccessToken_createPlayListTest() {
        Playlist playlist = new Playlist();
        playlist.setName("negative access token");
        playlist.setDescription("negative access pojo example");
        playlist.set_public(false);

        Response response = PlaylistApi.post("dummyTestPassed", playlist);

        //the above can be also done for this
        Error error = response.as(Error.class);

        assertThat(error.getError().getStatus(), equalTo(401));
        assertThat(error.getError().getMessage(), equalTo("Invalid access token"));

    }

    //The methods can be also used in specific classed
    //for body
    @Step
    public Playlist playlistBuilder(String name, String description, boolean _public) {
        Playlist playlist = new Playlist();
        playlist.setName(name);
        playlist.setDescription(description);
        playlist.set_public(false);
        return playlist;

        //builder pattern used for chaining
//        return Playlist.builder()
//                .name(name)
//                .description(description)
//                ._public(_public)
//                .build();
    }

    //for assertion
    @Step
    public void assertPlaylistEqual(Playlist responsePlaylist, Playlist requestPlaylist) {
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        // assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription())); commented just for now
        assertThat(responsePlaylist.get_public(), equalTo(requestPlaylist.get_public()));
    }

    @Step
    public void assertStatusCode(int actualStatusCode, int expectedStatusCode) {
        assertThat(actualStatusCode, equalTo(expectedStatusCode));
    }

    public void assertError(Error responseErr, int expectedStatusCode, String expectedMsg) {
        assertThat(responseErr.getError().getStatus(), equalTo(expectedStatusCode));
        assertThat(responseErr.getError().getMessage(), equalTo(expectedMsg));
    }
    public void testMethod(){
        System.out.println("new command added");
    }
}
