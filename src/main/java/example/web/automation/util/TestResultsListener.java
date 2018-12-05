package example.web.automation.util;

import example.web.automation.driver.WebDriverFactory;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.ByteArrayInputStream;

public class TestResultsListener extends TestListenerAdapter {

    private static final Logger LOG = Logger.getLogger(TestResultsListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        LOG.info("Test class started: " + result.getTestClass().getName());
        LOG.info("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOG.info("Test SUCCESS: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Allure.addAttachment("Screenshot on failure", "image/png", new ByteArrayInputStream(makeScreenshot()), "png");
        LOG.info("Test FAILED: " + result.getName());
        if (result.getThrowable()!=null) {
            result.getThrowable().printStackTrace();
        }
    }

    private byte[] makeScreenshot() {
        return ((TakesScreenshot) WebDriverFactory.getSetWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

}
