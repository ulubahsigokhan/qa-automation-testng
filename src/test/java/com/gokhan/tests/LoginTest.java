package com.gokhan.tests;

import com.gokhan.base.BaseTest;
import com.gokhan.config.FrameworkConfig;
import com.gokhan.pages.LoginPage;
import com.gokhan.testdata.LoginTestData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    private Object[][] loginData() {
        return new Object[][] {
                {
                        "Valid login",
                        LoginTestData.VALID_USERNAME,
                        LoginTestData.VALID_PASSWORD,
                        LoginTestData.SUCCESS_MESSAGE
                },
                {
                        "Invalid password login",
                        LoginTestData.INVALID_USERNAME,
                        LoginTestData.INVALID_PASSWORD,
                        LoginTestData.INVALID_PASSWORD_MESSAGE
                }
        };
    }

    private LoginPage openLoginPage() {
        driver.get(FrameworkConfig.BASE_URL + FrameworkConfig.LOGIN_PATH);
        return new LoginPage(driver);
    }

    private void assertFlashMessageContains(
            String scenarioName,
            LoginPage loginPage,
            String expectedMessage
    ) {
        Assert.assertTrue(
                loginPage.isFlashMessageDisplayed(),
                "Flash message was not displayed for scenario: " + scenarioName
        );

        String actualResult = loginPage.getFlashMessage();

        Assert.assertTrue(
                actualResult.contains(expectedMessage),
                "Flash message text was not correct for scenario: " + scenarioName
                        + ". Expected: " + expectedMessage
                        + " Actual: " + actualResult
        );
    }

    @Test(dataProvider = "loginData")
    public void loginShouldDisplayExpectedFlashMessage(
            String scenarioName,
            String username,
            String password,
            String expectedMessage
    ) {
        LoginPage loginPage = openLoginPage();
        loginPage.login(username, password);
        assertFlashMessageContains(scenarioName, loginPage, expectedMessage);
    }
}
