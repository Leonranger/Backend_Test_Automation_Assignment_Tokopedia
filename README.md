# REST API Test Automation Framework

![TestCase-Design](https://user-images.githubusercontent.com/28475979/102895822-69bbf280-448b-11eb-99ae-196813593faa.PNG)
This Test Automation Framework is created using Java + TestNG + RestAssured + ExtentReports, which can be used across different api based applications. 
In this approach, the endeavor is to wrap the common requirements of API's for specific application within the wrapper class in order to test the API's without spending any extra efforts on common requirements. 
With this framework in place, whenever we need to automate API's, we would not need to start from scratch, and begin with developing the test cases straight away.

# Pre-Requisites
* IntelliJ or Eclipse
* Java JDK-1.8 or higher
* Apache Maven 3 or higher

It uses REST Assured, Cucumber, JUnit, Allure and Gradle.

Set up
Install Git
Install Java 11
Install Gradle
Install Allure (optionally) to get fancy reports
Clone this repository
You can clone this repo using git console via https:

git clone from github

Running tests
To run all the tests just call gradle at project's root with "clean test" command:

gradle clean test


# Reporting
![ExtentReport](https://user-images.githubusercontent.com/28475979/102895706-31b4af80-448b-11eb-94d1-c1e6ef8220b2.PNG)
* The framework supports Extent Report for Reporting purpose. On execution, new directory is created under Ouput folder where all artifacts related to the execution resides. The report gives the details information on the Request & Response for each API executed.


Automation Strategy
This framework needs to be scaled to cater to the entire API testing of your application

Approach
Currently the framework adheres to a BDD model:
Features->Scennarios->Step Defnitions->RestTestLib This approach brings a collaborative model to the API testing where the Business Analysts, Developers and testers could work in tandem in Agile to conceive the acceptance criteria leveraging Gherkin. Test automation engineer could build step defintions and rest assured methods and assertions by consulting the developers. Testsuite could be executed as Maven Verify (mvn verify).

However at present, framework needs to add the following classes/components in order to be more scalable:

An utility class with a few rest assured methods such as request spec builder, response spec builder, common assertions like validating status code, parsing the response for headers, response objects, etc
Helper class (Support Code) specific to a project which returns parsed response. For example JSON path of a particular object or construcing an arraylist object for validation, etc.
Environment variables need to be moved to properties (serenity.properties)
Report doesn't print Rest Query which needs to be investigated
Logging needs to be expanded further
Future model:
Features->Scennarios->Step Defnitions->RestTestLib->Custom Helper Classes

#Test Coverage
Test scenarios Though every microservice is an independent entity, still it needs to be tested for integration. I'll identify the tests with the following approach:

End-to-End test testing of the data flow between various services and its different request/responses. This could achieved by creating the Features/Scenarios from Cucumber and writing step defnitions and further steps to call the API layer.
Services that impact funds/monetary values (For ex., transfer between accounts, updating bank balance)
Services that are called frequently
APIs that could impact the business profusely if failed or unavailable
Also I'll segregate the tests based on 'pre-integration' and 'integration-tests' which would give us the ability to run the new featuers during the 'pre-integration' but the 'integration-tests' for regression, etc.

Continuous Testing with the aid of integration to CI/CD will provide the team a visibility. So it's mandatory to integrate the tests in pipeline.

#Factors to consider
Backgrounds and Sceanrio Outlines - In the feature files in Cucumber Gherkin, this brings the reusability to the fore
Step Defintions Reusability- Reusing the step defintions this trickles down from the Backgrounds and Sceanrio Outlines in Feature files
Building reusable utility/helper classes which could be API Specific, Project related and common methods for entire REST architecture
Providing examples (Dataset) in Features for multiple test iterations
Mocking responses where third-party APIs are required
Meeting the Agile sprint testing goals through Automation
Working in tandem with GUI automation where required (For example., Creating Synthetic data and Deleting it as part of the Tear down exercises using API tests)
Providing feedback by leverging email plugins in CI/CD platform to keep the stakeholders posted on the test results