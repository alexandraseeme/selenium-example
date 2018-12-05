package example.web.automation.driver;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

    public static String CHROME = "chrome";
    public static String FIREFOX = "firefox";
    public static String HEADLESS_CHROME = "headless";

    private static WebDriver driver;
    private static final Logger LOG = Logger.getLogger(WebDriverFactory.class);

    private WebDriverFactory() {
    }

    /**
     * Gets single instance of webdriver
     *
     * @param browserName - the name of the browser
     * @return single instance of webdriver
     */
    public static WebDriver getInstance(String browserName) {
        BasicConfigurator.configure();
        if (CHROME.equals(browserName)) {
            setChromeBinaryPath();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            LOG.info("CHROME driver has been instantiated");
        } else if (FIREFOX.equals(browserName)) {
            setFirefoxBinaryPath();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            LOG.info("FIREFOX driver has been instantiated");
        } else if (HEADLESS_CHROME.equals(browserName)) {
            setChromeBinaryPath();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--window-size=1920,1080");
            driver = new ChromeDriver(chromeOptions);
            LOG.info("CHROME driver has been instantiated in HEADLESS mode");
        }
        else {
            throw new IllegalArgumentException("Cannot instantiate webdriver for browser: " + browserName);
        }
        return driver;
    }

    /**
     * Kills driver instance
     */
    public static void killWebDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        LOG.info("WebDriver instance has been killed!");
    }

    private static void setChromeBinaryPath(){
        String os = System.getProperty("os.name").toLowerCase().substring(0, 3);
        String chromeDriver = "src/main/resources/drivers/chromedriver"
                + (os.equals("win") ? ".exe" : "");
        System.setProperty("webdriver.chrome.driver", chromeDriver);
    }

    private static void setFirefoxBinaryPath(){
        String os = System.getProperty("os.name").toLowerCase().substring(0, 3);
        String ffDriver = "src/main/resources/drivers/geckodriver"
                + (os.equals("win") ? ".exe" : "");
        System.setProperty("webdriver.gecko.driver", ffDriver);
    }

    public static WebDriver getSetWebDriver(){
        return driver;
    }
}
