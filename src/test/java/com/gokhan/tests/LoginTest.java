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

    private void assertFlashMessageContains(LoginPage loginPage, String expectedMessage) {
        Assert.assertTrue(loginPage.isFlashMessageDisplayed(), "Flash message was not displayed");
        String actualResult = loginPage.getFlashMessage();
        Assert.assertTrue(actualResult.contains(expectedMessage),
                "Flash message text was not correct. Expected: " + expectedMessage + " Actual: " + actualResult);
    }

    @Test
    public void validLoginTest() {

        LoginPage loginPage = openLoginPage();
        loginPage.login(LoginTestData.VALID_USERNAME, LoginTestData.VALID_PASSWORD);

        assertFlashMessageContains(loginPage, LoginTestData.SUCCESS_MESSAGE);
    }

    @Test
    public void invalidLoginTest() {

        LoginPage loginPage = openLoginPage();
        loginPage.login(LoginTestData.INVALID_USERNAME, LoginTestData.INVALID_PASSWORD);

        assertFlashMessageContains(loginPage, LoginTestData.INVALID_PASSWORD_MESSAGE);
    }
}
