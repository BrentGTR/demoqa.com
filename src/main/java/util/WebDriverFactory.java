package util;

import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;

public class WebDriverFactory {
    private WebDriver driver = null;

    public static WebDriver create(){
        WebDriver driver = null;

        Properties prop = new Properties();
        FileInputStream configData = null;
        try {
            configData = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(configData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if ("chrome".equalsIgnoreCase(prop.getProperty("browser"))) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                if  (prop.getProperty("headless").equalsIgnoreCase("true")) {
                    options.addArguments("headless");
                    options.addArguments("--window-size=1920,1080");
                    options.addArguments("--ignore-certificate-errors");
                    options.addArguments("--incognito");
                }
                options.addArguments("--disable-gpu");
                options.addArguments("--dns-prefetch-disable");
                options.addArguments("--disable-extensions");
                options.addArguments("--no-sandbox");
                LoggingPreferences logPrefs = new LoggingPreferences();
                logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
                options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
                driver = new ChromeDriver(options);
            }

            else if ("IE".equalsIgnoreCase(prop.getProperty("browser"))) {
                WebDriverManager.iedriver().setup();
                InternetExplorerOptions options = new InternetExplorerOptions();
                options.ignoreZoomSettings();
                options.enablePersistentHovering();
                options.introduceFlakinessByIgnoringSecurityDomains();
                options.setCapability("EnableNativeEvents",false);
                options.requireWindowFocus();
                driver = new InternetExplorerDriver(options);

            }
            else if ("Android_Chrome".equalsIgnoreCase(prop.getProperty("browser"))) {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("platformName", "Android");
                capabilities.setCapability("platformVersion", prop.getProperty("platformVersion"));
                capabilities.setCapability("browserName", "Chrome");
                capabilities.setCapability("deviceName",  prop.getProperty("deviceName"));
                capabilities.setCapability("noReset", true);

                //Instantiate Appium Driver
            try {
                driver=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        }
            else if ("Firefox".equalsIgnoreCase(prop.getProperty("browser"))){
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                driver =new FirefoxDriver();
            }
        return driver;
    }
}
