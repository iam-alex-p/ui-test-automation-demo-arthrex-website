package com.arthrex.website.ui.pages.account;

import com.arthrex.website.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SubscriptionInfoPage extends BasePage {
    @FindBy(xpath = "//input[@value='Next']")
    private WebElement btnNext;

    public SubscriptionInfoPage(WebDriver driver) {
        super(driver);
    }

    public AccountInfoPage clickNext() throws InterruptedException {
        this.moveToElement(this.btnNext);
        this.click(this.btnNext);
        return new AccountInfoPage(this.driver);
    }
}
