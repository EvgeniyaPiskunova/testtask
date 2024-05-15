package starter.steps;

import net.serenitybdd.annotations.Step;
import starter.page.LoginPage;
import starter.page.MainPage;
import starter.page.ProductPage;

public class PageSteps {
    LoginPage loginPage;
    MainPage mainPage;
    ProductPage productPage;

    @Step
    public void inputLogin (String log){
        loginPage.inputLogin(log);
    }

    @Step
    public void inputPassword (String pas){
        loginPage.inputPassword(pas);
    }

    @Step
    public void clickLoginButton (){
        loginPage.clickLoginButton();
    }
    @Step
    public void checkCartEmpty (){
        productPage.checkCartIsEmpty();
    }

    @Step
    public void addToCart (){
        productPage.clickAddToCart();
    }

    @Step
    public void removeVisible (){
        productPage.removeVisible();
    }
    @Step
    public void clickFilter (){
        mainPage.clickFilter();
    }

    @Step
    public void chooseZA (){
        mainPage.chooseZA();
    }

    @Step
    public void clickProduct (){
        mainPage.clickProduct();
    }
}