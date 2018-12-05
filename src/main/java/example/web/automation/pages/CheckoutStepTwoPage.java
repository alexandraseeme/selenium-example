package example.web.automation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutStepTwoPage extends Page {

    @FindBy(className = "checkout_summary_container")
    WebElement root;

    @FindBy(className = "cart_checkout_link")
    WebElement finishBtn;

    public CheckoutStepTwoPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        wait.until(ExpectedConditions.visibilityOf(root));
        return root.isDisplayed();
    }

    @Step("Click on Finish checkout button")
    public CheckoutCompletePage finishCheckout(){
        finishBtn.click();
        return new CheckoutCompletePage(webDriver);
    }
}
