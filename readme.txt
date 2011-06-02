There are two demonstrations in this project here related to Test Automation.  The demos assume maven is installed on your computer: http://maven.apache.org/

***DEMO 1***

An explanation of this demo can also be found on my blog here:  http://jweden.tumblr.com/post/5178219054/agile-data-driven-tests-with-groovy-and-testng

This demo demonstrates several interesting ways to use TestNG.

1.  You can use Groovy to read in test case data for data-driven tests.  This allows the declarative test case data in testCases.properties to be used for tests.  The groovy piece hooks nicely into the TestNG data provider that supports data-driven tests.
2.  You can also access the TestNG API to obtain data about your test run.  This example just prints out this "test run metadata" but this same code could, with few changes, be made to take the test run data and persist it in some other data store like a database.
3.  This code also adds a custom annotation to the TestNG test cases.  Here this annotation associates one or more requirement IDs to each test case.  This allows for nice requirements tracking for your agile automation project.
4.  One of the TestNG tests uses concurrency to perform the test 5 times concurrently.

The code for item #1 and #4 above is located in the "misc" directory.  The code for #2 and #3 is in the "testNGAdapter" directory.  

This demo is easy to run.  Run the following from the top-level directory:

mvn clean install -P logResults

You'll see the test run which has passing and failing test cases; it includes a demonstration of both a test case printing out test case data obtained from the use of Groovy and also the printing out of information about the TestNG test run (courtesy of the TestNG API).

***DEMO 2***

This demo assumes firefox is installed on you computer. 
To enable DEMO 2, change the entry of "false" to "true" and the entry of "true" to "false" in "misc/src/main/resources/testng.xml".  Then from the "misc" directory type:

mvn clean test

This demo demonstrates a couple things: 

1.  This shows how parameters can be passed into a TestNG test via testng.xml; a nice declarative way of passing in username, password, and url is shown.  This test prints out the username and password and uses the url in the base class to bring up the webpage.  The other interesting configuration is that the testng.xml is configured to run tests in a specific order.
2.  This demo uses Selenium and shows how to send javascript to a web application under test.  This becomes very important since many times a tester needs to use javascript calls to find and manipulate dynamically-created content on a webpage.  This test prints out the javascript call it is sending and the corresponding result which are very useful for replicating in a javascript debugging tool like firebug.  The javascript call simply returns the number of frames used on a webpage.

Enjoy!
