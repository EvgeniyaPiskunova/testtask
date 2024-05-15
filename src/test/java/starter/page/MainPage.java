package starter.page;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

public class MainPage extends PageObject {
    private By filter = By.xpath("//*[@class='product_sort_container']");
    private By ZtoA = By.xpath("//*[@value='za']");
    private By product = By.id("item_2_title_link");

    public MainPage clickFilter (){
        find(filter).click();
        return this;
    }
    public MainPage chooseZA (){
        find(ZtoA).click();
        return this;
    }

    public MainPage clickProduct (){
        find(product).click();
        return this;
    }
}
