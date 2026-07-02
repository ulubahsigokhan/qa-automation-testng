package com.gokhan.base;

import com.gokhan.config.FrameworkConfig;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        if (FrameworkConfig.BROWSER.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);
            driver = new ChromeDriver(options);
        }
        else if (FrameworkConfig.BROWSER.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        else if (FrameworkConfig.BROWSER.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }
        else {
            throw new RuntimeException("Unsupported browser: " + FrameworkConfig.BROWSER);
        }

        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
