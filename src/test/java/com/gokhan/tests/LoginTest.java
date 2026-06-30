package com.gokhan.tests;

import com.gokhan.base.BaseTest;
import com.gokhan.config.FrameworkConfig;
import com.gokhan.pages.LoginPage;
import com.gokhan.testdata.LoginTestData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {

        driver.get(FrameworkConfig.BASE_URL + FrameworkConfig.LOGIN_PATH);

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername(LoginTestData.VALID_USERNAME);
        loginPage.enterPassword(LoginTestData.VALID_PASSWORD);
        loginPage.clickLoginButton();
        String actualResult = loginPage.getFlashMessage();
        String expectedResult = LoginTestData.SUCCESS_MESSAGE;
        Assert.assertTrue(actualResult.contains(expectedResult), "Login failed!");
    }

    @Test
    public void invalidLoginTest() {

        driver.get(FrameworkConfig.BASE_URL + FrameworkConfig.LOGIN_PATH);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(LoginTestData.INVALID_USERNAME);
        loginPage.enterPassword(LoginTestData.INVALID_PASSWORD);
        loginPage.clickLoginButton();
        String actualResult = loginPage.getFlashMessage();
        String expectedResult = LoginTestData.INVALID_PASSWORD_MESSAGE;
        Assert.assertTrue(actualResult.contains(expectedResult), "Invalid login error message was not displayed");
    }
}
