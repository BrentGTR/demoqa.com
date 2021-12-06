package test.stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import test.pageobjects.LoginPageObjects;
import test.pageobjects.ProfilePageObjects;
import test.pageobjects.SearchPageObjects;
import util.WebDriverFactory;

public class LoginStepDefs {

    private static WebDriver webDriver = WebDriverFactory.create();
    private SearchPageObjects searchPageObjects = new SearchPageObjects(webDriver);
    private LoginPageObjects loginPageObjects  = new LoginPageObjects(webDriver);
    private ProfilePageObjects profilePageObjects  = new ProfilePageObjects(webDriver);

    @When("^navigate to the LogIn page$")
    public void navigateToTheLoginPage(){
        searchPageObjects.navigateToTheLoginPage();
    }

    @And("^enter \"([^\"]*)\" and \"([^\"]*)\"$")
    public void enterUsernameAndPassword(String userName, String password) throws Throwable {
        loginPageObjects.logIntoBookstore(userName, password);
        throw new PendingException();
    }

    @Then("^verify that the Profile page is displayed$")
    public void verifyThatTheProfilePageIsDisplayed() {
        profilePageObjects.verifyThatTheProfilePageIsDisplayed();
    }
}
