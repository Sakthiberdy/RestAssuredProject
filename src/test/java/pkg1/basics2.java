package pkg1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.CoreMatchers.containsString;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class basics2 {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://reqres.in";
        given().body("{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}")
                .when().post("/api/users").then().assertThat().statusCode(201)
                .and().contentType(ContentType.JSON).body("createdAt",containsString("2021-01-19"));


    }
}
