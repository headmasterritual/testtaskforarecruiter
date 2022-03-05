import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Api tests")
class TestImplementation {

    @Test
    void openTest() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "";
        RestAssured.proxy("localhost", 3000);
        given().accept(ContentType.JSON)
                .filter(new AllureRestAssured())
                .when().get("/users")
                .then().assertThat()
                .body("name[0]", equalTo("Leanne Graham"));
        System.out.println("222");
    }
}