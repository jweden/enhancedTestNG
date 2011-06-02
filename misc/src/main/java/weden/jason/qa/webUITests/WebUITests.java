package weden.jason.qa.webUITests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 
 * Initial Web UI tests.
 * 
 */
public class WebUITests extends TestBase {

    private static final Logger LOG = LogManager.getLogger(WebUITests.class);
    WebDriver webDriver;

    @Test(description = "Mock login to web application ", groups = "initialize")
    @Parameters({"username", "password"})
    public void login(final String username, final String password) {
        LOG.info("Use the username of "
                 + username
                 + " and the password of "
                 + password
                 + " for logging in.");
    }

    @Test(description = "Sends in javascript call to get the number of frames", groups = "initialize")
    public void getNumberofFrames() {
        sendScript("return frames.length");
    }
}