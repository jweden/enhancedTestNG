package weden.jason.qa.webUITests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 
 * Initial common reporting tests.
 * 
 */
public class WebUITests extends TestBase {

    private static final Logger LOG = LogManager.getLogger(WebUITests.class);
    WebDriver webDriver;

    @Test(description = "Login to web console", groups = "initialize")
    @Parameters({"username", "password"})
    public void login(final String username, final String password) {
        LOG.info("Do something with selenium");
    }

    @Test(description = "Makes sure can do something", groups = "initialize")
    public void doSomething() {
        LOG.info("Do something else with selenium");
    }
}