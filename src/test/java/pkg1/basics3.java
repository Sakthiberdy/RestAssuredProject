package pkg1;

import files.resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.containsString;

public class basics3 {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://60065e263698a80017de1693.mockapi.io";
        Response res = given().body("{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}")
                .when().post(resources.usersResource()).then().assertThat().statusCode(201)
                .and().contentType(ContentType.JSON).body("createdAt",containsString("2021"))
                .extract().response();
        String response = res.asString();
        System.out.println("Response of create API is: "+response);

        JsonPath jp = new JsonPath(response);
        String id = jp.get("id");
        System.out.println("Id extracted from the Response of create API is: "+id);

        Response res1 = when().get(resources.usersResource()+id).then().assertThat().statusCode(200)
                .extract().response();
        String response1 = res1.asPrettyString();
        System.out.println("Response of get API is: "+response1);

        given().body("").when().delete(resources.usersResource()+id).then().assertThat().statusCode(200);

        Response res2 = when().get(resources.usersResource()+id).then().assertThat().statusCode(404)
                .extract().response();
        String response2 = res2.asString();
        System.out.println("Response of get API after the deletion is: "+response2);


    }
}
