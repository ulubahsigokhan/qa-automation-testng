package com.gokhan.tests;

import com.gokhan.base.BaseTest;
import com.gokhan.config.FrameworkConfig;
import com.gokhan.pages.LoginPage;
import com.gokhan.testdata.LoginTestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);

    private LoginPage openLoginPage() {
        driver.get(FrameworkConfig.BASE_URL + FrameworkConfig.LOGIN_PATH);
        return new LoginPage(driver);
    }

    @Test
    public void validLoginTest() {

        LoginPage loginPage = openLoginPage();
        loginPage.login(LoginTestData.VALID_USERNAME, LoginTestData.VALID_PASSWORD);

        Assert.assertTrue(loginPage.isFlashMessageDisplayed(), "Flash message was not displayed");

        String actualResult = loginPage.getFlashMessage();
        String expectedResult = LoginTestData.SUCCESS_MESSAGE;
        Assert.assertTrue(actualResult.contains(expectedResult), "Login failed!");
    }

    @Test
    public void invalidLoginTest() {

        LoginPage loginPage = openLoginPage();
        loginPage.login(LoginTestData.INVALID_USERNAME, LoginTestData.INVALID_PASSWORD);

        Assert.assertTrue(loginPage.isFlashMessageDisplayed(), "Flash message was not displayed");

        String actualResult = loginPage.getFlashMessage();
        String expectedResult = LoginTestData.INVALID_PASSWORD_MESSAGE;
        Assert.assertTrue(actualResult.contains(expectedResult), "Invalid login error message was not displayed");
    }
}
