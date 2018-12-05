package example.web.automation;

import example.web.automation.fragments.Header;
import example.web.automation.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class E2ETestSuite extends TestBase {

    @Test(priority = 0, description="Find a product and checkout happy path")
    public void buyOneProductTest() {
        InventoryPage inventoryPage = loginPage.loginDefault();
        inventoryPage.addProductToCart(0);
        Header header = new Header(driver);
        Assert.assertEquals(header.getNumberOfItemsInCart(), 1);

        CartPage cartPage = header.goToCartPage();
        Assert.assertEquals(cartPage.getCartItemsCount(), 1);

        CheckoutStepOnePage checkoutStepOnePage = cartPage.checkout();
        CheckoutStepTwoPage checkoutStepTwoPage = checkoutStepOnePage.fillInTheFormAndContinue("test", "test", "12345");
        CheckoutCompletePage checkoutCompletePage = checkoutStepTwoPage.finishCheckout();
        Assert.assertEquals(checkoutCompletePage.getCompleteHeaderText(), "THANK YOU FOR YOUR ORDER");
    }
}
