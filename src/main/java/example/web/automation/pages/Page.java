package example.web.automation.pages;

import example.web.automation.util.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public abstract class Page {

    public WebDriver webDriver;
    public WebDriverWait wait;

    public Page(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriver.manage().timeouts().implicitlyWait(Integer.parseInt(PropertyLoader.loadProperty("implicit.wait")), TimeUnit.SECONDS);
        wait = new WebDriverWait(webDriver, Integer.parseInt(PropertyLoader.loadProperty("explicit.wait")));
    }

    public abstract boolean isPageLoaded();

    public String getTitle() {
        return webDriver.getTitle();
    }

    public String getPageUrl() {
        return webDriver.getCurrentUrl();
    }


}
