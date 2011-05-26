package weden.jason.qa.webUITests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

/**
 * 
 * Base class for TestNG web UI tests.
 * 
 */
public class TestBase {

    private SeleniumManager sm;
    protected WebDriver webDriver;

    @Parameters({"webconsoleStartupURL"})
    @BeforeClass(description = "Perform class setup tasks")
    public void beforeClass(final String webconsoleStartupURL)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        sm = new SeleniumManager(webconsoleStartupURL);
        sm.startSelenium();
    }

    @AfterClass(description = "Perform class teardown tasks")
    public void afterClass() {
        sm.stopSelenium();
    }

    protected void setWebDriver(final WebDriver webDriver) {
        this.webDriver = getWebDriver();
    }

    protected WebDriver getWebDriver() {
        return sm.getWebDriver();
    }
}
