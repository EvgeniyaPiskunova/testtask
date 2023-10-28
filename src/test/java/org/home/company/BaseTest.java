package org.home.company;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

abstract class BaseTest {

    public void setUpBrowser() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.headless = true;
        Configuration.browserSize = "1920,1080";
    }

    @BeforeMethod
    public void init() {
        setUpBrowser();
    }

    @AfterMethod
    public void tearDown() {
        Selenide.closeWebDriver();
    }

}
