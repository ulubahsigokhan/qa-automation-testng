package com.gokhan.pages;

import com.gokhan.base.BasePage;
import com.gokhan.config.FrameworkConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecurePage extends BasePage {

    private By securePageHeading = By.xpath("//h1[text()='Secure Area page for Automation Testing Practice']");

    public SecurePage(WebDriver driver) {
        super(driver);
    }

    public void waitForPageToLoad() {
        waitForUrlContains(FrameworkConfig.SECURE_PATH);
    }

    public boolean isSecurePageHeadingDisplayed() {
        return isDisplayed(securePageHeading);
    }
}

