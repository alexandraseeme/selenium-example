package example.web.automation.pages;

import example.web.automation.util.PropertyLoader;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends Page {

    @FindBy(id = "login_button_container")
    private WebElement loginContainer;

    @FindBy(css = "*[data-test='username']")
    private WebElement username;

    @FindBy(css = "*[data-test='password']")
    private WebElement password;

    @FindBy(className = "login-button")
    private WebElement loginBtn;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @Step("Login as a user with {0} username and {1} password")
    public InventoryPage login(String username, String password){
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        this.loginBtn.click();
        return new InventoryPage(webDriver);
    }

    @Step("Login as a standard default user")
    public InventoryPage loginDefault(){
        return login(PropertyLoader.loadProperty("standard.user"), PropertyLoader.loadProperty("user.password"));
    }


    @Override
    public boolean isPageLoaded() {
        wait.until(ExpectedConditions.visibilityOf(loginContainer));
        return loginContainer.isDisplayed();
    }
}
