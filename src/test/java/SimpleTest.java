
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import page.LoginPage;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;


public class SimpleTest {
    private final String INVALID_PHONE_NUMBER = "1234567890";
    private final String VALID_PASSWORD = "1234567890";
    private final String BASE_URL = "http://tl.af-ctf.ru/#inputForAuth";
    private final String VALID_NAME = "Evgeniya";
    private final String VALID_EMAIL = "eapiskunova@gmail.com";


    @Owner("Zhenya")
    @Link(name = "link", url = "http://tl.af-ctf.ru/#inputForAuth")
    @Description("unsuccess login")
    @Test
    public void testUserGetsMessageOnInvalidPhoneNumberInput() {
        new LoginPage(BASE_URL)
                .putName(VALID_NAME)
                .putEmail(VALID_EMAIL)
                .putPhoneNumber(INVALID_PHONE_NUMBER)
                .putPasswordField(VALID_PASSWORD)
                .confirmPassowrd(VALID_PASSWORD)
                .clickSubmit()
                .assertErrorMessageVisible();

    }

    @Owner("Zhenya")
    @Link(name = "link", url = "http://tl.af-ctf.ru/#inputForAuth")
    @Description("unsuccess login")
    @Test
    public void testUserGetsMessageOnInvalidPasswordInput() {
        new LoginPage(BASE_URL)
                .putName(VALID_NAME)
                .putEmail(VALID_EMAIL)
                .putPhoneNumber(LoginPage.randomPhoneNumber())
                .putPasswordField(LoginPage.randomInvalidPassword())
                .confirmPassowrd(LoginPage.randomInvalidPassword())
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
        assertEquals(response.statusCode(), 200);
        assertTrue(response.jsonPath().getBoolean("type"));
        assertEquals("Сейчас на ваш телефон поступит звонок, последние 4 цифры являются кодом",
                response.jsonPath().getString("text"));
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
        assertEquals(response.statusCode(), 200);
        assertFalse(response.jsonPath().getBoolean("type"));
        assertEquals("\"Сотовый номер\" должен содержать только числа или +",
                response.jsonPath().getString("message"));
    }
}
