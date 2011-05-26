package weden.jason.qa.webUITests;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

/**
 * 
 * Base class for TestNG web UI tests.
 * 
 */
public class TestBase {

    private static final Logger LOG = LogManager.getLogger(TestBase.class);
    private static final String NEW_LINE = System.getProperty("line.separator");
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

    public Object sendScript(final String js) {
        LOG.info("The javascript to be executed is: " + js);
        final Object obj = ((JavascriptExecutor) getWebDriver()).executeScript(js, new Object[0]);
        LOG.info("Return value of javascript call is:" + NEW_LINE + getScriptReturnValue(obj));
        return obj;
    }

    /**
     * Get the return value of javascript call. It doesn't deal with nested lists if that is
     * returned so this method could be improved. It turns returns values into strings for logging.
     * 
     * @param scriptReturnValue
     * @return
     */
    private String getScriptReturnValue(final Object scriptReturnValue) {
        final StringBuilder sb = new StringBuilder();
        if (scriptReturnValue == null) {
            return "null";
        } else if (scriptReturnValue instanceof java.util.List) {
            final List<?> jsList = (List<?>) scriptReturnValue;

            for (final Object j : jsList) {
                if (j instanceof java.util.List) {
                    return "a nested list, but this won't be printed out.";
                } else if (j instanceof WebElement) {
                    stringifyWebElement((WebElement) j, sb);
                } else {
                    sb.append(j.toString() + NEW_LINE);
                }
            }
            return sb.toString().trim();
        } else {
            if (scriptReturnValue instanceof WebElement) {
                stringifyWebElement((WebElement) scriptReturnValue, sb);
            } else {
                sb.append(scriptReturnValue.toString());
            }
            return sb.toString();
        }
    }

    /**
     * Selenium framework doesn't have many methods to turn a WebElement to a string, so this at
     * least gets at some parts of the element which is way better than nothing.
     * 
     * @param webElem
     * @param sb
     */
    private void stringifyWebElement(final WebElement webElem, final StringBuilder sb) {
        final WebElement webElement = webElem;
        sb.append("element's tag name = " + webElement.getTagName() + NEW_LINE);
        if (webElement.getAttribute("value") != null) {
            sb.append("element's value attribute = " + webElement.getValue() + NEW_LINE);
        }
        sb.append("element's inner text = " + webElement.getText() + NEW_LINE);
    }

    protected void setWebDriver(final WebDriver webDriver) {
        this.webDriver = getWebDriver();
    }

    protected WebDriver getWebDriver() {
        return sm.getWebDriver();
    }
}
