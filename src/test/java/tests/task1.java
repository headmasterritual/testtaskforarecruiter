package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Api tests")
public class task1 extends basetest{
    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://localhost";
//		RestAssured.port=8080; //commented as our API doesnot use port
        RestAssured.proxy("localhost", 3000);
    }
    @Test
    void task1() {
        given().accept(ContentType.JSON)
                .filter(new AllureRestAssured())
                .when().get(" /posts")
                .then()
                .statusCode(200);

    }
}
