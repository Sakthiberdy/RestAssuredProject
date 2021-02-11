package pkg1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class basics {
    public static void main(String[] args) {
        //AIzaSyCB5_PhiyUBg6IDy3W4v0fgSLv4t7o3-gc
        /*RestAssured.baseURI="https://maps.googleapis.com";

        given().param("key", "AIzaSyCB5_PhiyUBg6IDy3W4v0fgSLv4t7o3-gc")
                .param("location", "-33.8670522,151.1957362")
                .param("radius", "1500").
                *//*.param("type", "restaurant")
                .param("keyword", "cruise");*//*
        when().get("/maps/api/place/nearbysearch/json")
                .then().assertThat().statusCode(200).and().contentType(ContentType.JSON);*/
        RestAssured.baseURI = "https://reqres.in";
        Response res = given().queryParam("page", "2").when().get("/api/users").then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON).and().body("data[0].first_name",equalTo("Michael"))
                .and().header("Server", "cloudflare").extract().response();
        String response = res.toString();
        System.out.println(response);



    }
}
