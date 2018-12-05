package example.web.automation;

import example.web.automation.pages.InventoryPage;
import example.web.automation.providers.TestProviders;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTestSuite extends TestBase {

    @Test(dataProvider = "goodCredentials", dataProviderClass = TestProviders.class)
    public void testLoginSuccess(String username, String password) throws IOException {
        InventoryPage inventoryPage = loginPage.login(username, password);
        Assert.assertTrue(inventoryPage.isPageLoaded());
    }
}
