package test.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import test.pageobjects.SearchPageObjects;
import util.WebDriverFactory;

public class SearchStepDefs {

    private static final WebDriver webDriver = WebDriverFactory.create();
    private final SearchPageObjects searchPageObjects = new SearchPageObjects(webDriver);

    @When("^searching for a book with the title of \"([^\"]*)\"$")
    public void searchingForABookWithTheTitleOf(String title) throws Throwable {
        searchPageObjects.loadUrl();
        searchPageObjects.searchForABook(title);
    }

    @Then("^verify that the book displayed has a title of \"([^\"]*)\"$")
    public void verifyTitleDisplayedMatchesSearchInput(String title){
        searchPageObjects.verifyTitleDisplayedMatchesSearchInput(title);
    }

    @After
    public void closeTest() {
        searchPageObjects.shutdown();
    }
}
