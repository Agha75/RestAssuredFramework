package com.spotify.oauth2.api;

import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.api.Route.API;
import static com.spotify.oauth2.api.Route.TOKEN;
import static com.spotify.oauth2.api.SpecBuilders.*;
import static io.restassured.RestAssured.given;

public class RestResource {
    //types object is defined that can be used for any api
    public static Response post(String path, String token, Object requestPlaylist) {
        return given().spec(getRequestSpec())
                .body(requestPlaylist)
                .auth().oauth2(token) //this way can be also used for token
                //.header("Authorization", "Bearer " + token)
                .when()
                .post(path)
                .then().spec(getResponseSpec())
                .extract().response();
    }

    //playlist id passed as argument
    public static Response get(String path, String token) {
        return given().spec(getRequestSpec())
                .header("Authorization", "Bearer " + token)
                .when()
                .get(path)
                .then().spec(getResponseSpec())
                .extract().response();
    }

    //playlist id passed as argument
    public static Response postAccount(HashMap<String, String> formParams) {
        return given().spec(getAccountRequestSpec())
                .formParams(formParams)
                .when()
                .post(API + TOKEN)
                .then().spec(getResponseSpec())
                .extract().response();
    }

    //playlist id passed as argument
    public static Response put(String path, String token, Object requestPlaylist) {
        return given().spec(getRequestSpec())
                .body(requestPlaylist)
                .header("Authorization", "Bearer " + token)
                .when()
                .put(path)
                .then().spec(getResponseSpec())
                .extract().response();
    }


}
