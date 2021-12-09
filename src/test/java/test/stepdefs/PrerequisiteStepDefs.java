package test.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import org.openqa.selenium.WebDriver;
import test.pageobjects.SearchPageObjects;
import util.WebDriverFactory;

public class PrerequisiteStepDefs {

    private static final WebDriver webDriver = WebDriverFactory.create();
    private final SearchPageObjects searchPageObjects = new SearchPageObjects(webDriver);

    @Given("^that the Book Store Demo site has been accessed$")
    public void that_the_Demo_site_has_been_accessed() throws Throwable {
        searchPageObjects.loadUrl();
        if (!searchPageObjects.getTitle().equals(searchPageObjects.getSiteTitle())) {
            searchPageObjects.shutdown();
        }
    }

    @After
    public void closeTest() {
        searchPageObjects.shutdown();
    }
}
