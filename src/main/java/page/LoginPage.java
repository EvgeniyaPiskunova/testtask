package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.Random;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage  {
    public LoginPage(String url){
        Selenide.open(url);
    }
    private final static String ERROR_MESSAGE1 = "Сотовый номер должен содержать только цифры и начинаться с 7 или 8";
    private final static String ERROR_MESSAGE2 = "Ваши пароли не совпадают";

    private final SelenideElement userNameField = $("[id='userName']");
    private final SelenideElement emailField = $("[id='email']");
    private final SelenideElement phoneNumberField = $("[id='phoneNumber']");
    private final SelenideElement passowrdField = $("[id='password']");
    private final SelenideElement passowrdValidationField = $("[id='passwordValidation']");
    private final SelenideElement checkBox1 = $x("//*[@id='inputForAuth']/div[3]/label[1]");
    private final SelenideElement checkBox2 = $x("//*[@id='inputForAuth']/div[3]/label[2]");
    private final SelenideElement submit = $("[id='submitLogin']");
    private final SelenideElement errorMessage1 = $x("//div[@class='signUpForm__data']/child::div[text()='Сотовый номер должен содержать только цифры и начинаться с 7 или 8']");
    private final SelenideElement errorMessage2 = $x("//div[@class='signUpForm__data']/child::div[text()='Ваши пароли не совпадают']");

    public LoginPage() {

    }

    public LoginPage putName( String name){
        userNameField.setValue(name);
        return new LoginPage();
    }
    public LoginPage putEmail(String email){
        emailField.setValue(email);
        return new LoginPage();
    }
    public LoginPage putPhoneNumber(String phoneNumber){
        phoneNumberField.setValue(phoneNumber);
        return new LoginPage();
    }


    public LoginPage putPasswordField(String password){
        passowrdField.setValue(password);
        return new LoginPage();
    }

    public LoginPage confirmPassowrd(String password){
        passowrdValidationField.setValue(password);
        return new LoginPage();
    }


    public LoginPage clickCheckBox(){
        checkBox1.click();
        return new LoginPage();
    }


    public LoginPage clickCheckBox2(){
        checkBox2.click();
        return new LoginPage();
    }

    public LoginPage clickSubmit (){
        submit.click();
        return new LoginPage();
    }


    public void assertErrorMessageVisible(){
      errorMessage1.shouldBe(Condition.visible);
               errorMessage1.shouldBe(Condition.text(ERROR_MESSAGE1));
    }

    public void assertErrorMessage2Visible(){
        errorMessage2.shouldBe(Condition.visible);
        errorMessage2.shouldBe(Condition.text(ERROR_MESSAGE2));
    }
    public static String randomPhoneNumber() {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        sb.append(7);
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }
    public static String randomInvalidPassword() {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        sb.append(1);
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(5));
        }
        return sb.toString();
    }

}
