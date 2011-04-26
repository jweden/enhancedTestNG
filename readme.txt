This project demonstrates several interesting ways to use TestNG.

1.  You can use Groovy to read in test case data for data-driven tests.  This allows the declarative test case data in testCases.properties to be used for tests.  The groovy piece hooks nicely into the TestNG data provider that supports data-driven tests.
2.  You can also access the TestNG API to obtain data about your test run.  This example just prints out this "test run metadata" but this same code could, with few changes, be made to take the test run data and persist it in some other data store like a database.
3.  This code also adds a custom annotation to the TestNG test cases.  Here this annotation associates one or more requirement IDs to each test case.  This allows for nice requirements tracking for your agile automation project.
4.  One of the TestNG tests uses concurrency to perform the test 5 times concurrently.

The code for item #1 and #4 above is located in the misc directory.  The code for #2 and #3 is in the testNGAdapter directory.  

This demo is easy to run.  Just grab the code, make sure maven is installed on your computer and, run the following from the top-level directory:

mvn clean install -P logResults

You'll see the test run which has passing and failing test cases; it includes a demonstration of both a test case printing out test case data obtained from the use of Groovy and also the printing out of information about the TestNG test run (courtesy of the TestNG API).

Enjoy!
