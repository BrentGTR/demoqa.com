package test.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import test.pageobjects.LoginPageObjects;
import test.pageobjects.ProfilePageObjects;
import test.pageobjects.SearchPageObjects;
import util.WebDriverFactory;

public class LoginStepDefs{

    private final WebDriver webDriver = WebDriverFactory.create();
    private final SearchPageObjects searchPageObjects= new SearchPageObjects(webDriver);
    private final LoginPageObjects loginPageObjects= new LoginPageObjects(webDriver);
    private final ProfilePageObjects profilePageObjects  = new ProfilePageObjects(webDriver);

    @When("^navigate to the LogIn page$")
    public void navigateToTheLoginPage(){
        searchPageObjects.loadUrl();
        searchPageObjects.navigateToTheLoginPage();
    }

    @And("^enter \"([^\"]*)\" and \"([^\"]*)\"$")
    public void enterUsernameAndPassword(String userName, String password) throws Throwable {
        loginPageObjects.logIntoBookstore(userName, password);
    }

    @Then("^verify that the Profile page is displayed$")
    public void verifyThatTheProfilePageIsDisplayed() {
        profilePageObjects.verifyThatTheProfilePageIsDisplayed();
    }

    @After
    public void closeTest() {
        searchPageObjects.shutdown();
    }
}
