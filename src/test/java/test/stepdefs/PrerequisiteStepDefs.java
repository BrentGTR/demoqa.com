package test.stepdefs;

import cucumber.api.java.en.Given;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import test.pageobjects.SearchPageObjects;
import util.WebDriverFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class PrerequisiteStepDefs {

    private static WebDriver webDriver = WebDriverFactory.create();
    private SearchPageObjects searchPageObjects = new SearchPageObjects(webDriver);

    @Given("^that the Book Store Demo site has been accessed$")
    public void that_the_Demo_site_has_been_accessed() throws Throwable {

        searchPageObjects.loadUrl();
        if (!searchPageObjects.getTitle().equals(searchPageObjects.getSiteTitle())) {
            searchPageObjects.shutdown();
        }
    }

    @AfterSuite
    public void closeTest() throws IOException {
        System.out.println("Tear down started.");

        webDriver.close();
        webDriver.quit();
    }
}
