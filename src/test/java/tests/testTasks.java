package tests;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.selector.ByAttribute;
import com.google.common.collect.Ordering;
import helpers.jsons.json1;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class testTasks {

    json1 test1 = new json1();
    public static String init() throws IOException {
//        basetest test = new basetest();
//        test.init();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Phone Num(without country index) : ");
            String s = br.readLine();
            System.out.println(s);

        return s;
    }
    public static String a;

    static {
        try {
            a = testTasks.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static ArrayList<String> people = new ArrayList<String>();


    public static void first() throws IOException {
        RestAssured.baseURI = "https://my.ctrs.com.ua";

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("identity", "38" + a);
        JSONObject json = new JSONObject(map);
        String jsonfinal = json.toString();

        System.out.println(jsonfinal);
        int response = given()
                .header("Content-Type", "application/json")
                .body(jsonfinal)
                .when().post("/api/auth/login")
                .then()
                .extract().statusCode();
        people.add(String.valueOf(response));

    }
    public static void second() throws IOException {
        RestAssured.baseURI = "https://api.toplyvo.app";

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("phone", "+38" + a);
        JSONObject json = new JSONObject(map);
        String jsonfinal = json.toString();

        System.out.println(jsonfinal);
        int response = given()
                .header("Content-Type", "application/json")
                .body(jsonfinal)
                .when().post("/api/v3/client/create_code")
                .then()
                .extract().statusCode();
        people.add(String.valueOf(response));

    }
    public static void third() throws IOException {
        RestAssured.baseURI = "https://auth2.multiplex.ua";

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("login", "38" + a);
        JSONObject json = new JSONObject(map);
        String jsonfinal = json.toString();

        System.out.println(jsonfinal);
        int response = given()
                .header("Content-Type", "application/json")
                .body(jsonfinal)
                .when().post("/login")
                .then()
                .extract().statusCode();
        people.add(String.valueOf(response));
        System.out.println(response);

    }
    public static void fourth() throws IOException {
        RestAssured.baseURI = "https://auth2.multiplex.ua";

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("login", "38" + a);
        JSONObject json = new JSONObject(map);
        String jsonfinal = json.toString();

        System.out.println(jsonfinal);
        int response = given()
                .header("Content-Type", "application/json")
                .body(jsonfinal)
                .when().post("/login")
                .then()
                .extract().statusCode();
        people.add(String.valueOf(response));

    }
    public static void fifth() throws IOException {
        RestAssured.baseURI = "https://my.colonnade.com.ua";

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("userName", a);
        JSONObject json = new JSONObject(map);
        String jsonfinal = json.toString();

        System.out.println(jsonfinal);
        String postId =
                given().
                        contentType("application/json").
                        body(jsonfinal).
                        when().
                        post("/Account/LoginByPhoneGetCode").
                        then().
                        extract().
                        path("Id");
        System.out.println(postId);

        Map<String, Object> map2 = new LinkedHashMap<>();
        map2.put("id", postId);
        JSONObject json2 = new JSONObject(map2);
        String jsonfinal2 = json2.toString();
        System.out.println(jsonfinal2);

        int response = given()
                .header("Content-Type", "application/json")
                .body(jsonfinal2)
                .when().post("/uk/api/PM")
                .then()
                .extract().statusCode();
        people.add(String.valueOf(response));

    }
    public static void sixth() throws IOException {
        RestAssured.baseURI = "https://auth.easypay.ua";

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("phone", "38" + a);
        JSONObject json = new JSONObject(map);
        String jsonfinal = json.toString();

        System.out.println(jsonfinal);
        int response = given()
                .header("Content-Type", "application/json")
                .header("partnerkey", "easypay-v2")
                .header("appid", "02e04e95-e967-4c16-891f-e91548b20e45")

                .body(jsonfinal)
                .when().post("/api/check")
                .then()
                .extract().statusCode();
        people.add(String.valueOf(response));

    }
    public static void seventh() throws IOException {
        RestAssured.baseURI = "https://auth.easypay.ua";

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("phone", "38" + a);
        JSONObject json = new JSONObject(map);
        String jsonfinal = json.toString();

        System.out.println(jsonfinal);
        Response response = (Response) given()
                .header("Content-Type", "application/json")
                .header("partnerkey", "easypay-v2")
                .header("appid", "02e04e95-e967-4c16-891f-e91548b20e45")

                .body(jsonfinal)
                .when().post("/api/check")
                .then()
                .extract().response();
        System.out.println(response.asString());

    }
    public static void ss() throws InterruptedException, IOException {
        Configuration.headless = true;
        open("https://privatbank.ua/ru");
        $("#cbp-hrmenu > ul > li.go-pr-24-lg.dropdown.mega-dropdown.individual-p24 > a").click();
        $("#input_tel").setValue("+38" + a);
        $("#p24-form-field > div.inputGroupContainer.form-group.has-feedback > button").click();
        Thread.sleep(7000);
    }
    public static void main(String[] args) throws IOException, InterruptedException {
            first();
            second();
        third();
        fourth();
        fifth();
        sixth();
        Map<String, Integer> frequency = people.stream()
                // собираем элементы листа в карту
                .collect(Collectors.toMap(
                        // ключ - строка
                        e -> e,
                        // значение - число,
                        // количество вхождений
                        e -> 1,
                        // суммируем количество вхождений
                        Integer::sum));


// Обходим карту и выводим содержимое
        frequency.forEach((k, v) -> System.out.println(k + "Result by statuses: " + v));
    }
}
