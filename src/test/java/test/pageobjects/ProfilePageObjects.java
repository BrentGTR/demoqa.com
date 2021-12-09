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

public class ProfilePageObjects {

    @FindBy(xpath = "//div[contains(@Class,'main-header')]")
    WebElement mainHeader;

    private WebDriver driver;

    public WebDriver getWebDriver() {
        return driver;
    }

    public ProfilePageObjects(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyThatTheProfilePageIsDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(mainHeader));
        Assert.assertEquals(mainHeader.getText(),"Profile" );
    }
}
