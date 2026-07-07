package com.gokhan.pages;

import com.gokhan.base.BasePage;
import com.gokhan.config.FrameworkConfig;
import org.openqa.selenium.WebDriver;

public class SecurePage extends BasePage {

    public SecurePage(WebDriver driver) {
        super(driver);
    }

    public void waitForPageToLoad() {
        waitForUrlContains(FrameworkConfig.SECURE_PATH);
    }
}
