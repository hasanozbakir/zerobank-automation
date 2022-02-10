package com.zerobank.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Driver {
    private Driver() {

    }

    private static WebDriver driver;

    public static WebDriver get() {
        // Test
        if (driver == null) {
            // this line will tell which browser should open based on the value from properties file
            String browser = ConfigurationReader.get("browser");
            switch (browser){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver=new ChromeDriver();
                    break;
                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    driver=new ChromeDriver(new ChromeOptions().setHeadless(true));
                    break;
                case "chrome-sll":
                    WebDriverManager.chromedriver().setup();
                    driver=new ChromeDriver(new ChromeOptions().setAcceptInsecureCerts(true));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver=new FirefoxDriver();
                    break;
                case "firefox-headless":
                    WebDriverManager.firefoxdriver().setup();
                    driver=new FirefoxDriver(new FirefoxOptions().setHeadless(true));
                    break;
                case "firefox-sll":
                    WebDriverManager.firefoxdriver().setup();
                    driver=new FirefoxDriver(new FirefoxOptions().setAcceptInsecureCerts(true));
                    break;
                case "ie":
                    if(!System.getProperty("os.name").toLowerCase().contains("windows")){
                        throw new WebDriverException("Your OS does not support Internet Explorer");
                    }
                    WebDriverManager.iedriver().setup();
                    driver=new InternetExplorerDriver();
                    break;
                case "ie-sll":
                    if(!System.getProperty("os.name").toLowerCase().contains("Internet Explorer")){
                        throw new WebDriverException("Your OS does not support Internet Explorer");
                    }
                    WebDriverManager.iedriver().setup();
                    driver=new InternetExplorerDriver(new InternetExplorerOptions().setAcceptInsecureCerts(true));
                    break;
                case "edge":
                    if(!System.getProperty("os.name").toLowerCase().contains("windows")){
                        throw new WebDriverException("Your OS does not support Edge");
                    }
                    WebDriverManager.edgedriver().setup();
                    driver=new EdgeDriver();
                    break;
                case "edge-sll":
                    if(!System.getProperty("os.name").toLowerCase().contains("windows")){
                        throw new WebDriverException("Your OS does not support Edge");
                    }
                    WebDriverManager.edgedriver().setup();
                    driver=new EdgeDriver(new EdgeOptions().setAcceptInsecureCerts(true));
                    break;
                case "safari":
                    if(!System.getProperty("os.name").toLowerCase().contains("safari")){
                        throw new WebDriverException("Your OS does not support Safari");
                    }
                    WebDriverManager.safaridriver().setup();
                    driver=new SafariDriver();
                    break;
                case "safari-sll":
                    if(!System.getProperty("os.name").toLowerCase().contains("mac")){
                        throw new WebDriverException("Your OS does not support Safari");
                    }
                    WebDriverManager.safaridriver().setup();
                    driver=new SafariDriver(new SafariOptions().setAcceptInsecureCerts(true));
                    break;
                default:
                    throw new RuntimeException("no such browser exists!");
            }
            driver.manage().window().maximize();
            WebDriverWait wait=new WebDriverWait(driver,10);
        }

        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}