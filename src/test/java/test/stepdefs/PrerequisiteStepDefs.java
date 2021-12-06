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
//public static WebDriver driver;
    private SearchPageObjects searchPageObjects = new SearchPageObjects(webDriver);

    @Given("^that the Book Store Demo site has been accessed$")
    public void that_the_Demo_site_has_been_accessed() throws Throwable {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//
//        driver.get("https://demoqa.com/books");

        searchPageObjects.loadUrl();
        if (!searchPageObjects.getTitle().equals(searchPageObjects.getSiteTitle())) {
            searchPageObjects.shutdown();
        }
    }

    @AfterSuite
    public void closeTest(ITestResult result) throws IOException {
        System.out.println(result);
        System.out.println("Tear down started.");

        webDriver.close();
        webDriver.quit();
    }
}
