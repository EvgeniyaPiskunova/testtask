package org.home.company;

import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.home.company.data.AppResponseData;
import org.home.company.data.RequestItem;
import org.home.company.data.ResponseData;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SimpleAPITest {

    @Owner("Zhenya")
    @Link(name = "link", url = "https://reqres.in/api/users")
    @Description("success post")
    @Test
    public void testPostCreate() {
        String name = "String";
        String job = "String";
        Response response = given()
                .header("Content-type", "application/json")
                .header("Accept", "application/json")
                .baseUri("https://reqres.in")
                .body(RequestItem.builder()
                        .name(name)
                        .job(job)
                        .build())
                .when()
                .post("/api/users")
                .then()
                .extract()
                .response();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(response.statusCode()).as("status code").isEqualTo(201);
        ResponseData responseData = response.as(ResponseData.class);
        assertEquals(responseData.getName(), name);
        assertEquals(responseData.getJob(), job);
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
        softly.assertThat(response.statusCode())
                .as("status code")
                .isEqualTo(200);
        AppResponseData responseData = response.as(AppResponseData.class);
        softly.assertThat(responseData.getPage())
                .as("page number")
                .isEqualTo(2);
        assertTrue(responseData.getData().get(1).getFirst_name().contains("Lindsay"));
        softly.assertAll();
    }
}