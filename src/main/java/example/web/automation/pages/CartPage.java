package example.web.automation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends Page {

    @FindBy(css = "#cart_contents_container")
    WebElement root;

    @FindBy(className = "cart_item")
    List<WebElement> cartItems;

    @FindBy(className = "cart_checkout_link")
    WebElement checkoutLink;

    public CartPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @Step("Number of items in the cart")
    public int getCartItemsCount(){
        return  cartItems.size();
    }

    public boolean isPageLoaded() {
        wait.until(ExpectedConditions.visibilityOf(root));
        return root.isDisplayed();
    }

    @Step("Navigate to checkout page one")
    public CheckoutStepOnePage checkout(){
        checkoutLink.click();
        return new CheckoutStepOnePage(webDriver);
    }

}
