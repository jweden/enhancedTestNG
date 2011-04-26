package weden.jason.qa.someProduct;

import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;

/**
 * 
 * Base class for TestNG test classes. Has support for data-driven tests using TestNG data provider.
 * 
 */
public class TestBase {

    private static final Logger LOG = LogManager.getLogger(TestBase.class);

    @DataProvider(name = "queries")
    public Object[][] obtainTestCasesDataProvider() {
        final List<Map<String, String>> testCases = new TestcaseGrabber().getTestcases();

        final Object[][] dataProvider = new Object[testCases.size()][2];
        int outerArrayIndex = 0;

        for (final Map<String, String> testCase : testCases) {
            final Object[] innerArray = new Object[2];
            LOG.info(testCase.get("testDescription"));
            innerArray[0] = testCase.get("testDescription");
            innerArray[1] = testCase.get("xmlToUse");

            dataProvider[outerArrayIndex] = innerArray;
            outerArrayIndex++;
        }

        return dataProvider;
    }

}
