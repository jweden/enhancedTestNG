package weden.jason.qa.webUITests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class SeleniumManager {

    private static final Logger LOG = LogManager.getLogger(SeleniumManager.class);
    public static final String WEBDRIVER_TYPE = "weden.jason.qa.webdrivertype";
    public static final String WEBDRIVER_FF_CLASS_NAME = "org.openqa.selenium.firefox.FirefoxDriver";
    public static final String WEBDRIVER_IE_CLASS_NAME = "org.openqa.selenium.ie.InternetExplorerDriver";
    public String webconsoleStartupURL;
    private WebDriver webDriver;

    public SeleniumManager(final String webconsoleStartupURL) {
        this.webconsoleStartupURL = webconsoleStartupURL;
    }

    protected void startSelenium()
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        final String driverType = System.getProperty(WEBDRIVER_TYPE);
        String webDriverImplementationClass = WEBDRIVER_FF_CLASS_NAME;
        if ("internetexplorer".equalsIgnoreCase(driverType)
            || "ie".equalsIgnoreCase(driverType)
            || "iebrowser".equalsIgnoreCase(driverType)) {
            webDriverImplementationClass = WEBDRIVER_IE_CLASS_NAME;
        }

        final Class<?> webDriverClass = Class.forName(webDriverImplementationClass);
        final WebDriver driver = (WebDriver) webDriverClass.newInstance();
        setWebDriver(driver);
        driver.get(webconsoleStartupURL);
    }

    protected void stopSelenium() {
        getWebDriver().quit();
    }

    public void setWebDriver(final WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }
}