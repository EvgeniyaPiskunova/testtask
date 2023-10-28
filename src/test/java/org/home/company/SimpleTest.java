package org.home.company;

import com.github.javafaker.Faker;
import org.assertj.core.api.SoftAssertions;
import org.home.company.data.AppResponseData;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import page.LoginPage;

import java.util.Map;

import static org.home.company.data.TestData.*;
import static io.restassured.RestAssured.given;


public class SimpleTest {

    private final static Faker faker = new Faker();
    private final static int PHONE_N_LEN = 9;

    @Owner("Zhenya")
    @Link(name = "link", url = "http://tl.af-ctf.ru/#inputForAuth")
    @Description("unsuccess login")
    @Test
    public void testUserGetsMessageOnInvalidPhoneNumberInput() {
        new LoginPage(BASE_URL.val)
                .putName(VALID_NAME.val)
                .putEmail(VALID_EMAIL.val)
                .putPhoneNumber(INVALID_PHONE_NUMBER.val)
                .putPasswordField(VALID_PASSWORD.val)
                .confirmPassowrd(VALID_PASSWORD.val)
                .clickSubmit()
                .assertErrorMessageVisible();
    }

    @Owner("Zhenya")
    @Link(name = "link", url = "http://tl.af-ctf.ru/#inputForAuth")
    @Description("unsuccess login")
    @Test
    public void testUserGetsMessageOnInvalidPasswordInput() {
        new LoginPage(BASE_URL.val)
                .putName(VALID_NAME.val)
                .putEmail(VALID_EMAIL.val)
                .putPhoneNumber(faker.phoneNumber().subscriberNumber(PHONE_N_LEN))
                .putPasswordField(faker.business().creditCardNumber())
                .confirmPassowrd(faker.business().creditCardNumber())
                .clickCheckBox()
                .clickCheckBox2()
                .clickSubmit()
                .assertErrorMessage2Visible();
    }

    @Owner("Zhenya")
    @Link(name = "link", url = "http://tl.af-ctf.ru/#inputForAuth")
    @Description("success login")
    @Test
    public void testPostCreate() {
        String login = "evgeniyapopova02@gmail.com";
        String userName = "EvgeniyaPiskunova";
        String password = "1234567890";
        String passwordValidation = "1234567890";
        String phoneNumber = "79254506020";
        Map<String, String> payload = Map.of(
                "login", login,
                "userName", userName,
                "password", password,
                "passwordValidation", passwordValidation,
                "phoneNumber", phoneNumber);
        Response response = given()
                .header("Content-type", "application/json")
                .header("Accept", "application/json")
                .baseUri("http://tl.af-ctf.ru")
                .body(payload)
                .when()
                .post("/calluserforsignup")
                .then()
                .extract()
                .response();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(response.statusCode()).as("status code").isEqualTo(200);
        AppResponseData responseData = response.as(AppResponseData.class);
        softly.assertThat(responseData.getType()).as("data type").isTrue();
        softly.assertThat(responseData.getText()).as("data text")
                .isEqualTo("Сейчас на ваш телефон поступит звонок, последние 4 цифры являются кодом");
        softly.assertAll();
    }

    @Owner("Zhenya")
    @Link(name = "link", url = "http://tl.af-ctf.ru/#inputForAuth")
    @Description("success login")
    @Test
    public void testPostPhoneNumberInvalid() {
        String login = "evgeniyapopova02@gmail.com";
        String userName = "EvgeniyaPiskunova";
        String password = "1234567890";
        String passwordValidation = "1234567890";
        String phoneNumber = "";
        Map<String, String> payload = Map.of(
                "login", login,
                "userName", userName,
                "password", password,
                "passwordValidation", passwordValidation,
                "phoneNumber", phoneNumber);
        Response response = given()
                .header("Content-type", "application/json")
                .header("Accept", "application/json")
                .baseUri("http://tl.af-ctf.ru")
                .body(payload)
                .when()
                .post("/calluserforsignup")
                .then()
                .extract()
                .response();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(response.statusCode()).as("status code").isEqualTo(200);
        softly.assertThat(response.jsonPath().getBoolean("type")).as("data type").isFalse();
        softly.assertThat(response.jsonPath().getString("message")).as("data text")
                .isEqualTo("\"Сотовый номер\" должен содержать только числа или +");
        softly.assertAll();
    }
}
