package tests;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class basetest {

    protected static String requestBody = "{\n" +
            "  \"albumId\": 1,\n" +
            "  \"id\": 22,\n" +
            "  \"title\": \"et ea illo et sit voluptas animi blanditiis porro\",\n" +
            "  \"url\": \"https://via.placeholder.com/600/e924e6\",\n" +
            "  \"thumbnailUrl\": \"https://via.placeholder.com/150/e924e6\" \n}";

    public static void init() {
        RestAssured.baseURI = "http://localhost";
//		localhostRestAssured.port=8080; //commented as API doesnot use port
        RestAssured.proxy("localhost", 3000);
        given().accept(ContentType.JSON)
                .filter(new AllureRestAssured());
    }
}