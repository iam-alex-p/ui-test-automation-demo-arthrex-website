package com.arthrex.website.ui.pages.account;

import com.arthrex.website.ui.pages.BasePage;
import com.arthrex.website.ui.pages.common.CookieDisclaimerPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConsentDataProcessingPage extends BasePage {
    @FindBy(id = "data-usage-consent-accept")
    private WebElement btnAcceptAndCreateAccount;

    public ConsentDataProcessingPage(WebDriver driver) {
        super(driver);
    }

    public CookieDisclaimerPage clickAcceptAndCreateAccount() {
        this.click(this.btnAcceptAndCreateAccount);
        return new CookieDisclaimerPage(this.driver);
    }
}
