package starter.page;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

public class ProductPage extends PageObject {
    private By cart = By.xpath("//a[@class='shopping_cart_link']");

    private By goodsInCart = By.xpath("//a/span[@data-test='shopping-cart-badge']");
    private By addToCart = By.id("add-to-cart");

    private By remove = By.id("remove");

    public ProductPage checkCartIsEmpty (){
        find(goodsInCart).deselect();
        return this;
    }
    public ProductPage clickAddToCart (){
        find(addToCart).click();
        return this;
    }

    public ProductPage removeVisible (){
        find(remove).isVisible();
        return this;
    }
}
