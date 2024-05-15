package starter.page;

import net.serenitybdd.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

@DefaultUrl(" https://www.saucedemo.com/ ")
public class LoginPage extends PageObject {
    private  By loginField = By.id("login-button");
    private By passwordField = By.id("password");
    private By signButton = By.id("login-button");

    public LoginPage clickLoginButton (){
        find(signButton).click();
        return this;
    }
    public LoginPage inputLogin (String log){
        find(loginField).sendKeys(log);
        return this;
    }

    public LoginPage inputPassword (String pas){
        find(passwordField).sendKeys(pas);
        return this;
    }

}
