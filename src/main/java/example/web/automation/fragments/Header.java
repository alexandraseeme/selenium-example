package example.web.automation.fragments;

import example.web.automation.pages.CartPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header {

    WebDriver driver;

    @FindBy(css = ".fa-layers-counter.shopping_cart_badge")
    WebElement numberOfItemsInCart;

    @FindBy(id = "shopping_cart_container")
    WebElement cartElement;

    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @Step("Number of items in the cart as shown in Header")
    public int getNumberOfItemsInCart(){
        if (!numberOfItemsInCart.isDisplayed()){
            return 0;
        } else {
            return Integer.parseInt(numberOfItemsInCart.getText());
        }
    }

    @Step("Navigate to Cart page")
    public CartPage goToCartPage(){
        cartElement.click();
        return new CartPage(driver);
    }

}
