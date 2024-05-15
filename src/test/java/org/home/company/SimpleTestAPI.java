package org.home.company;

import org.assertj.core.api.SoftAssertions;
import org.home.company.data.AppResponseData;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.home.company.data.ResponseData;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class SimpleTestAPI {
    @Owner("Zhenya")
    @Link(name = "link", url = "https://reqres.in/api/users")
    @Description("success post")
    @Test
    public void testPostCreate() {
        String name = "String";
        String job = "String";
        Map<String, String> payload = Map.of(
                "name", name,
                "job", job);
        Response response = given()
                .header("Accept", "application/json")
                .baseUri("https://reqres.in")
                .body(payload)
                .when()
                .post("/api/users")
                .then()
                .extract()
                .response();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(response.statusCode()).as("status code").isEqualTo(200);
        ResponseData responseData = response.as(ResponseData.class);
        assertTrue(responseData.getName(), payload.containsValue(name));
        assertTrue(responseData.getJob(), payload.containsValue(job));
    }

    @Owner("Zhenya")
    @Link(name = "link", url = "https://reqres.in/api/users?page=2")
    @Description("GET page users")
    @Test
    public void testGetPageUsers() {
        Response response = given()
                .header("Accept", "application/json")
                .baseUri("https://reqres.in")
                .when()
                .get("/api/users?page=2")
                .then()
                .extract()
                .response();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(response.statusCode()).as("status code").isEqualTo(200);
        AppResponseData responseData = response.as(AppResponseData.class);
        softly.assertThat(responseData.getPage()).as(String.valueOf(2));
        assertTrue(responseData.getData().get(1).getFirst_name().contains("Lindsay"));
        softly.assertAll();
    }
}
