package weden.jason.qa.testNG;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.xml.XmlSuite;

/**
 * 
 * This class represents functionality run at the end of a testNG run. It grabs certain parts of the
 * test results and prints them to the console. It also shows used of a custom Requirements
 * annotation.
 * 
 */

public class CustomReporter implements IReporter {

    private static final Logger LOG = LogManager.getLogger(CustomReporter.class);

    /**
     * Get the necessary parts of the test run results
     */
    @Override
    public void generateReport(
            final List<XmlSuite> xmlSuites,
            final List<ISuite> suites,
            final String outputDirectory) {
        final List<Map<String, String>> testResults = new ArrayList<Map<String, String>>();
        for (int i = 0; i < xmlSuites.size(); i++) {
            final ISuite suite = suites.get(i);
            for (final ISuiteResult result : suite.getResults().values()) {
                final ITestContext tc = result.getTestContext();
                final ITestNGMethod[] tests = tc.getAllTestMethods();
                final Collection<ITestNGMethod> testsPassed = tc.getPassedTests().getAllMethods();
                final Collection<ITestNGMethod> testsFailed = tc.getFailedTests().getAllMethods();

                for (int j = 0; j < tests.length; j++) {
                    final Map<String, String> testResultsMap = new HashMap<String, String>();
                    final ITestNGMethod test = tests[j];
                    final Class testClass = test.getRealClass();
                    final String methodName = test.getMethodName();
                    final String fullMethodName = (testClass + "." + methodName).split(" ")[1];

                    if (containsTest(fullMethodName, testsPassed)) {
                        testResultsMap.put("TestResult", "PASS");
                    } else if (containsTest(fullMethodName, testsFailed)) {
                        testResultsMap.put("TestResult", "FAIL");
                    } else {
                        testResultsMap.put("TestResult", "SKIP");
                    }

                    testResultsMap.put("TestName", fullMethodName);
                    testResultsMap.put("Description", test.getDescription());
                    testResultsMap.put("Requirements", obtainReqs(testClass, methodName));
                    testResultsMap.put("Version", getVersion());

                    // Convert to mysql DATETIME field format
                    final DateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    final String dateString = dbFormat.format(test.getDate());
                    testResultsMap.put("DateRun", dateString);

                    testResults.add(testResultsMap);
                }
            }

        }

        final String logResults = System.getProperty("logResults");
        if ("true".equalsIgnoreCase(logResults)) {
            logResults(testResults);
        }
    }

    /**
     * Deals with Requirements annotation.
     * 
     * @param className
     * @param methodName
     * @return
     */
    private String obtainReqs(final Class className, final String methodName) {
        final StringBuilder returnBuilder = new StringBuilder();
        for (final Method m : className.getDeclaredMethods()) {
            final Requirements reqsAnnotation = m.getAnnotation(Requirements.class);
            LOG.debug(m.getName() + " -> " + methodName);
            if (reqsAnnotation != null) {

                if (methodName.equals(m.getName())) {
                    final String[] reqsStrings = reqsAnnotation.reqs();
                    for (final String str : reqsStrings) {
                        returnBuilder.append(str);
                        returnBuilder.append(",");
                    }
                    break;
                }
            }
        }
        final String returnString = returnBuilder.toString();
        final int length = returnString.length();
        if (length == 0) {
            return "";
        } else {
            return returnString.substring(0, length - 1);
        }
    }

    /**
     * Helper method to get test name.
     * 
     * @param testEntry
     * @param testMethods
     * @return
     */
    private boolean containsTest(final String testEntry, final Collection<ITestNGMethod> testMethods) {
        for (final ITestNGMethod tm : testMethods) {
            LOG.debug(testEntry + "()" + " -> " + tm);
            if (((testEntry + "()").trim()).equals((tm.toString()).trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * The method could be used to send test results to some remote Test Results Repository. But
     * here, we just print the test items to the console.
     * 
     * @param testResults
     */
    private void logResults(final List<Map<String, String>> testResults) {
        LOG.info("Sending results to Test Results Repository");
        for (final Map<String, String> testCaseEntry : testResults) {
            LOG.info("*************************************");
            for (final Map.Entry<String, String> testAttribute : testCaseEntry.entrySet()) {
                LOG.info(testAttribute.getKey() + " : " + testAttribute.getValue());
            }
        }
        LOG.info("Logging all test results was successful.");
    }

    /**
     * Mocked out for now. In real world it would grab the version number from some external
     * versioning system.
     * 
     * @return version number of product under test
     */
    private String getVersion() {
        return "1.0";
    }

}
