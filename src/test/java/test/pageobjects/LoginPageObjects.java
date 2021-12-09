package test.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;

public class LoginPageObjects {

    @FindBy(id = "userName")
    WebElement userNameInputBox;
    @FindBy(id = "password")
    WebElement passwordInputBox;
    @FindBy(id = "login")
    WebElement loginButton;
    @FindBy(xpath = "//div[contains(@Class,'main-header')]")
    WebElement mainHeader;

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

    public LoginPageObjects(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
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

    public void logIntoBookstore(String userName, String password) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(loginButton));
        userNameInputBox.sendKeys(userName);
        passwordInputBox.sendKeys(password);
        loginButton.click();
    }
}
