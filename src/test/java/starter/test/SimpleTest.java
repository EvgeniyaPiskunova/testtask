package starter.test;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import starter.steps.PageSteps;

@RunWith(SerenityRunner.class)
public class SimpleTest  {
    private final static String PASWORD = "secret_sauce";
    private final static String LOGIN = "standard_user";
    @Steps
    PageSteps steps;
    @Managed(driver = "chrome")
    WebDriver driver;

    @Test
    public void simpleTest() {
        steps.inputLogin(LOGIN);
        steps.inputPassword(PASWORD);
        steps.clickLoginButton();
        steps.clickFilter();
        steps.chooseZA();
        steps.clickProduct();
        steps.checkCartEmpty();
        steps.addToCart();
        steps.removeVisible();
    }
}