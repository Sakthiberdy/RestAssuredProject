package pkg1;

import files.resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.containsString;

public class basics4 {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://60065e263698a80017de1693.mockapi.io";
        Response res = given().body("").log().all()
                .when().get(resources.usersResource()).then().assertThat().statusCode(200).log().all()
                .extract().response();
        String response = res.asString();
        System.out.println("Response of get API is: "+response);

        JsonPath jp = new JsonPath(response);
        int count = jp.get("array.size()");
        System.out.println(count);

        for (int i=0; i<count; i++) {
            String id = jp.get("["+i+"].id");
            System.out.print("Id : "+id);
            System.out.print(", ");
            String names = jp.get("["+i+"].name");
            System.out.println("Name : "+names);
        }

        /*String id = jp.get("id");
        System.out.println("Id extracted from the Response of create API is: "+id);*/
    }
}
