package example.web.automation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutStepOnePage extends Page {

    @FindBy(className = "checkout_info_container")
    WebElement root;

    @FindBy(xpath = "//*[@data-test='firstName']")
    WebElement firstName;

    @FindBy(xpath = "//*[@data-test='lastName']")
    WebElement lastName;


    @FindBy(xpath = "//*[@data-test='postalCode']")
    WebElement zipCode;

    @FindBy(className = "cart_checkout_link")
    WebElement continueBtn;


    public CheckoutStepOnePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @Step("Fill in firstname as {0}, lastname as {1}, zip code as {2} and proceed to checkout page two")
    public CheckoutStepTwoPage fillInTheFormAndContinue(String firstName, String lastName, String zip) {
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.zipCode.sendKeys(zip);
        continueBtn.click();
        return new CheckoutStepTwoPage(webDriver);
    }

    public boolean isPageLoaded() {
        wait.until(ExpectedConditions.visibilityOf(root));
        return root.isDisplayed();
    }
}
