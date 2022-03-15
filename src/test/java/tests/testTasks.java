package tests;
import com.google.common.collect.Ordering;
import helpers.jsons.json1;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Api tests")
public class testTasks extends basetest{

    json1 test1 = new json1();

    @BeforeAll
    public static void init() {
        basetest test = new basetest();
        test.init();
    }
    @Test
    void task1() {
        given()
                .when().get(" /posts")
                .then()
                .statusCode(200);

    }
    @Test
    void task2() {
        given()
                .when().get(" /comments")
                .then()
                .header("Content-Type", "application/json; charset=utf-8");
    }
    @Test
    void task3() {
        given()
                .when().get("/albums/3")
                .then()
                .header("Content-Length","61");
    }
    @Test
    void task4() {
        given()
                .when().get("/albums")
                .then()
                .header("Content-Length", isEmptyOrNullString());
    }
    @Test
    void task5() {
        given()
                .when().get("/albums")
                .then()
                .time(lessThan(10000L));
    }
    @Test
    void task6() {
        given()
                .when().get("/users")
                .then().assertThat()
                .body("address.geo.lat[4]", equalTo("-31.8129"))
                .body("address.geo.lng[4]", equalTo("62.5342"));
    }
    @Test
    void task7() {
        given()
                .when().get("/albums/-1")
                .then().assertThat()
                .statusCode(404);
    }
    @Test
    void task8() {
        given()
                .when().get("/todos")
                .then().assertThat()
                .statusCode(200)
                .body("completed[9]", is(true));

    }
    @Test
    void task9() {
        given()
                .when().queryParam("q","Kulas Light").get("/users")
                .then().assertThat()
                .statusCode(200)
                .body("address.street[0]", equalTo("Kulas Light"));
    }
    @Test
    void task10() {
        given()
                .when().queryParam("id_ne","3").get("/users")
                .then().assertThat()
                .statusCode(200)
                .body("id[2]", is(4));

    }
    @Test
    void task11() {
        ArrayList nums = (ArrayList) given()
                .when()
                .get("/comments?_sort=id&_order=desc")
                .then()
                .extract()
                .jsonPath()
                .getList("id");

        boolean isInAscOrder = Ordering.natural().reverse().isOrdered(nums);
        assertThat(isInAscOrder, is(true));
    }
    @Test
    void task12() {
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/photos/3");
        JsonPath jsonPathEvaluator = response.jsonPath();
        String photo = jsonPathEvaluator.get("url");
        given()
                .when().get("/photos")
                .then().assertThat()
                .statusCode(200)
                .body("url[2]", equalTo(photo));
    }
    @Test
    void task13() {
        given()
                .when().queryParam("q","Gwenborough").get("/users")
                .then().assertThat()
                .statusCode(200)
                .body("address.city[0]", equalTo("Gwenborough"));
    }
    @Test
    void task14() {
        given()
                .when().queryParam("_page","1")
                .queryParam("_limit","10").get("/users")
                .then().assertThat()
                .statusCode(200)
                .body("id[9]", is(10))
                .body("id[10]", isEmptyOrNullString());

    }
    @Test
    void task15() {
        given()
                .get("/posts/55")
                .then().assertThat()
                .statusCode(200)
                .body("id", is(55));
        given()
                .get("/posts/60")
                .then().assertThat()
                .statusCode(200)
                .body("id", is(60));

    }
    @Test
    void task16() {
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.queryParam("_page","5")
                .queryParam("_limit","5").get("/photos");
        int b = 21;
        for (int i = 0; i < 5; i++, b++) {
            response.then().assertThat().body("id[" + i + "]", is(b));
            response.then().assertThat().body("url[" + i + "]", notNullValue());
        }
    }
    @Test
    void task17() {
        File schema = new File("src/test/java/helpers/jsons/schema.json");
        given()
                .when().get("/users/10")
                .then().assertThat()
                .statusCode(200)
                .body(matchesJsonSchema(schema));

    }
    @Test
    void task18() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .when().post("/comments")
                .then().assertThat()
                .statusCode(500);
    }
    @Test
    void task19() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(test1.RandomMapPost())
                .when().post("/664/posts")
                .then().assertThat()
                .statusCode(401);
    }
    @Test
    void task20() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(test1.RandomMapReg())
                .when().post("/register")
                .then().assertThat()
                .statusCode(201);
    }
    @Test
    void task21()  {
        String userId =
                  given()
                            .contentType("application/json")
                            .body(test1.RandomMapReg())
                            .when()
                            .post("/register")
                            .then()
                            .statusCode(201)
                            .extract()
                            .path("accessToken");
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + userId)
                .body(test1.RandomMapPost())
                .when().post("/664/posts")
                .then().assertThat()
                .statusCode(201);
    }
    @Test
    void task22() {
        int postId =
                 given().
                        contentType("application/json").
                        body(test1.RandomMapPost()).
                        when().
                        post("/posts").
                        then().
                        statusCode(201).
                        extract().
                        path("id");
        ArrayList nums = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when().get("/posts")
                .then().assertThat()
                .statusCode(200)
                .extract()
                .path("id");
        int e = (int) nums.get(nums.size() - 1);
        assertThat(e, equalTo(postId));
    }
    @Test
    void task23() {
        given().
                contentType("application/json").
                body(test1.RandomMapPost()).
                when().
                patch("/posts/" + "99999999").
                then().
                statusCode(404);
    }
    @Test
    void task24() {
        int postId =
                given().
                        contentType("application/json").
                        body(test1.RandomMapPost()).
                        when().
                        post("/posts").
                        then().
                        statusCode(201).
                        extract().
                        path("id");
        int postIdSec = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(test1.RandomMapPostSec())
                .when().patch("/posts/" + postId)
                .then().assertThat()
                .statusCode(200)
                .extract()
                .path("userId");
        assertThat(postId, not(postIdSec));
    }
    @Test
    void task25() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when().delete("/posts/" + test1.random())
                .then().assertThat()
                .statusCode(404);
    }
    @Test
    void task26() {
        int postId =
                given().
                        contentType("application/json").
                        body(test1.RandomMapPost()).
                        when().
                        post("/posts").
                        then().
                        statusCode(201).
                        extract().
                        path("id");
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(test1.RandomMapPostSec())
                .when().patch("/posts/" + postId)
                .then().assertThat()
                .statusCode(200);
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when().delete("/posts/" + postId)
                .then().assertThat()
                .statusCode(200);
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when().get("/posts/" + postId)
                .then().assertThat()
                .statusCode(404);
    }
}
