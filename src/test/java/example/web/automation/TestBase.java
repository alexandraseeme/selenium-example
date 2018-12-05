package example.web.automation;

import example.web.automation.driver.WebDriverFactory;
import example.web.automation.pages.LoginPage;
import example.web.automation.util.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeMethod
    @Parameters({ "browserName" })
    public void initTestSuite(String browserName) {
        driver = WebDriverFactory.getInstance(browserName);
        loginPage = goToLoginPage();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        WebDriverFactory.killWebDriver();
    }

    public LoginPage goToLoginPage() {
        driver.get(PropertyLoader.loadProperty("testappurl"));
        return new LoginPage(this.driver);
    }
}
