package example.web.automation.providers;

import example.web.automation.util.PropertyLoader;
import org.testng.annotations.DataProvider;

/**
 *
 */
public class TestProviders {

    @DataProvider(name = "goodCredentials")
    public static Object[][] provideGoodCredentials() {

        return new Object[][] {
                {PropertyLoader.loadProperty("standard.user"),  PropertyLoader.loadProperty("user.password"),},
                {PropertyLoader.loadProperty("problem.user"),  PropertyLoader.loadProperty("user.password"),},
        };
    }

    @DataProvider(name = "badCredentials")
    public static Object[][] provideBadCredentials() {

        return new Object[][] {
                {PropertyLoader.loadProperty("locked.user"),  PropertyLoader.loadProperty("user.password"),},
        };
    }
}
