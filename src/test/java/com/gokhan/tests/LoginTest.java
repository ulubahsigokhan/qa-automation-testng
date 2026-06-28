package com.gokhan.tests;

import com.gokhan.base.BaseTest;
import com.gokhan.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {
        driver.get("https://practice.expandtesting.com/login");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("practice");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickLoginButton();
        String actualResult = loginPage.getSuccessMessage();
        String expectedResult = "You logged into a secure area!";
        Assert.assertTrue(actualResult.contains(expectedResult), "Login failed");
    }
}
