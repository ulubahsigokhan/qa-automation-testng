package com.gokhan.tests;

import com.gokhan.base.BaseTest;
import com.gokhan.config.FrameworkConfig;
import com.gokhan.pages.LoginPage;
import com.gokhan.testdata.LoginTestData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage openLoginPage() {
        driver.get(FrameworkConfig.BASE_URL + FrameworkConfig.LOGIN_PATH);
        return new LoginPage(driver);
    }

    @Test
    public void validLoginTest() {

        LoginPage loginPage = openLoginPage();

        loginPage.enterUsername(LoginTestData.VALID_USERNAME);
        loginPage.enterPassword(LoginTestData.VALID_PASSWORD);
        loginPage.clickLoginButton();

        Assert.assertTrue(loginPage.isFlashMessageDisplayed(), "Flash message was not displayed");

        String actualResult = loginPage.getFlashMessage();
        String expectedResult = LoginTestData.SUCCESS_MESSAGE;
        Assert.assertTrue(actualResult.contains(expectedResult), "Login failed!");
    }

    @Test
    public void invalidLoginTest() {

        LoginPage loginPage = openLoginPage();

        loginPage.enterUsername(LoginTestData.INVALID_USERNAME);
        loginPage.enterPassword(LoginTestData.INVALID_PASSWORD);
        loginPage.clickLoginButton();

        Assert.assertTrue(loginPage.isFlashMessageDisplayed(), "Flash message was not displayed");

        String actualResult = loginPage.getFlashMessage();
        String expectedResult = LoginTestData.INVALID_PASSWORD_MESSAGE;
        Assert.assertTrue(actualResult.contains(expectedResult), "Invalid login error message was not displayed");
    }
}
