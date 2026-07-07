package com.gokhan.pages;

import com.gokhan.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By usernameInput = By.id("username");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("submit-login");
    private By flashMessage = By.id("flash");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        type(usernameInput, username);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public String getFlashMessage() {
        return getText(flashMessage);
    }

    public boolean isFlashMessageDisplayed() {
        return isDisplayed(flashMessage);
    }
}

