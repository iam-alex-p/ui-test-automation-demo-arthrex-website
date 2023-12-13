package com.arthrex.website.ui.pages.common;

import com.arthrex.website.ui.pages.BasePage;
import com.arthrex.website.ui.pages.account.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderMenuPage extends BasePage {
    @FindBy(xpath = "//i[text()='account_circle']")
    private WebElement btnLogin;

    public HeaderMenuPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage clickLogin() {
        this.click(btnLogin);
        return new LoginPage(this.driver);
    }
}
