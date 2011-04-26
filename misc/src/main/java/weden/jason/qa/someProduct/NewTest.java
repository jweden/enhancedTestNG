package weden.jason.qa.someProduct;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import weden.jason.qa.testNG.Requirements;

/**
 * 
 * This is a nice model for a testNG test class
 * 
 */
public class NewTest extends TestBase {

    private static final Logger LOG = LogManager.getLogger(NewTest.class);

    @Test(description = "This is a test method description", groups = "specialFeature, fast")
    @Requirements(reqs = {"1", "2"})
    public void aTestHere() {
        LOG.info("In Test");

        Assert.assertTrue(true);
    }

    @Test(description = "This is a second test method description", groups = "specialFeature, slow")
    @Requirements(reqs = {"3"})
    public void anotherTestHere() {
        LOG.info("In this test now");
        Assert.assertTrue(false, "Looking for something");
    }

    @Test(description = "This is a second test method description", groups = "specialFeature, fast")
    public void anotherTestHere2() {
        LOG.info("In this test now");
        Assert.assertTrue(false);
    }

    /**
     * Each item in the testCases.properties will be sent concurrently x times where x is defined in
     * the @Test annotation attribute below. Test case data is made available to the "queries"
     * TestNG data provider courtesy of the use of a Groovy properties file.
     * 
     */
    @Test(enabled = true, description = "5 Thread Search Query", dataProvider = "queries", invocationCount = 5, threadPoolSize = 5, groups = "specialFeature, perf, multiThread")
    @Requirements(reqs = {"1", "2"})
    public void concurrentDbaseQuery(final String testDescription, final String xmlToUse)
            throws InterruptedException {
        LOG.info("Test Description is: " + testDescription);
        LOG.info("XML to use is: " + xmlToUse);
        Assert.assertTrue(true);
    }

    @BeforeClass(description = "This is a test class description")
    public void beforeClass() {
        LOG.info("In beforeClass");
    }

    @BeforeTest
    public void beforeTest() {
        LOG.info("In beforeTest");
    }
}
