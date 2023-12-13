package com.arthrex.website.ui.pages.account;

import com.arthrex.website.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//span[text()='Create Website Profile']")
    private WebElement btnCreateProfile;

    @FindBy(id = "nextUsername")
    private WebElement txtUsername;

    @FindBy(id = "nextPassword")
    private WebElement txtPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement btnSignIn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public RoleSelectionPage clickCreateWebsiteProfile() {
        this.click(this.btnCreateProfile);
        return new RoleSelectionPage(this.driver);
    }

    public void enterUsername(String username) {
        this.enterText(this.txtUsername, username);
    }

    public void enterPassword(String password) {
        this.enterText(this.txtPassword, password);
    }

    public void clickSignIn() {
        this.click(this.btnSignIn);
    }
}
