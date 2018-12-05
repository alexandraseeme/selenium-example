package example.web.automation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutCompletePage extends Page {

    @FindBy(id = "checkout_complete_container")
    WebElement root;

    @FindBy(className = "complete-header")
    WebElement completeHeader;


    public CheckoutCompletePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @Step("Getting checkout complete message")
    public String getCompleteHeaderText(){
        return completeHeader.getText();
    }


    public boolean isPageLoaded() {
        wait.until(ExpectedConditions.visibilityOf(root));
        return root.isDisplayed();
    }
}
