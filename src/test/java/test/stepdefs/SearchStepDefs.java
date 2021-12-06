package test.stepdefs;

import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import test.pageobjects.SearchPageObjects;
import util.WebDriverFactory;

public class SearchStepDefs {

    private static WebDriver webDriver = WebDriverFactory.create();
    private SearchPageObjects searchPageObjects = new SearchPageObjects(webDriver);

    @Then("^verify that the book displayed has a title of \"([^\"]*)\"$")
    public void verifyTitleDisplayedMatchesSearchInput(String title){
        searchPageObjects.searchForABook(title);
        searchPageObjects.verifyTitleDisplayedMatchesSearchInput(title);
    }
}
