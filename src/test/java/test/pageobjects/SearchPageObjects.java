package test.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;

public class SearchPageObjects {

    @FindBy(id = "searchBox")
    WebElement searchInputBox;
    @FindBy(xpath = "(//span[contains(@id,'see-book-')])[1]")
    WebElement firstBookTitleInResultsTable;
    @FindBy(id = "login")
    WebElement loginButton;

    private WebDriver driver;
    private Properties prop = new Properties();
    FileInputStream configData;
    {
        try {
            configData = new FileInputStream((System.getProperty("user.dir") + "/src/test/resources/config.properties"));
            prop.load(configData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SearchPageObjects(WebDriver driver){
        this.driver = driver;
    }

    public Object getURL() {
        return prop.getProperty("baseUrl");
    }

    public Object getSiteTitle() {
        return prop.getProperty("siteTitle");
    }

    public void loadUrl() {
        System.out.println("The url is: " + getURL());
        driver.get((String) getURL());
    }

    public WebDriver getWebDriver() {
        return driver;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void searchForABook(String searchCriterion){
        searchInputBox.sendKeys(searchCriterion);
    }

    public void verifyTitleDisplayedMatchesSearchInput(String title){
        Assert.assertEquals(title, firstBookTitleInResultsTable.getText());
    }

    public void navigateToTheLoginPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(searchInputBox));
        searchInputBox.sendKeys("searchCriterion");
    }

    public void shutdown() {
        System.out.println("The site title is incorrect. Terminating JVM.");
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        s += "/error.html";
        driver.navigate().to("file://" + s);
        driver.quit();
        System.exit(0);
    }
}
