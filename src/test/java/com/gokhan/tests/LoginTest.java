package com.gokhan.tests;

import com.gokhan.base.BaseTest;
import com.gokhan.config.FrameworkConfig;
import com.gokhan.pages.LoginPage;
import com.gokhan.testdata.LoginTestData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @DataProvider
    private Object[][] loginData() {
        return new Object[][] {
                {LoginTestData.VALID_USERNAME, LoginTestData.VALID_PASSWORD, LoginTestData.SUCCESS_MESSAGE},
                {LoginTestData.INVALID_USERNAME, LoginTestData.INVALID_PASSWORD, LoginTestData.INVALID_PASSWORD_MESSAGE}
        };
    }

    private LoginPage openLoginPage() {
        driver.get(FrameworkConfig.BASE_URL + FrameworkConfig.LOGIN_PATH);
        return new LoginPage(driver);
    }

    private void assertFlashMessageContains(LoginPage loginPage, String expectedMessage) {
        Assert.assertTrue(loginPage.isFlashMessageDisplayed(), "Flash message was not displayed");
        String actualResult = loginPage.getFlashMessage();
        Assert.assertTrue(
                actualResult.contains(expectedMessage),
                "Flash message text was not correct. Expected: " + expectedMessage + " Actual: " + actualResult);
    }

    @Test(dataProvider = "loginData")
    public void loginShouldDisplayExpectedFlashMessage(String username, String password, String expectedMessage) {
        LoginPage loginPage = openLoginPage();
        loginPage.login(username, password);
        assertFlashMessageContains(loginPage, expectedMessage);
    }
}
