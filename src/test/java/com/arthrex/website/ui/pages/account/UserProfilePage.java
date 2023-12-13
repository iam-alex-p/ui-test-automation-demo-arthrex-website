package com.arthrex.website.ui.pages.account;

import com.arthrex.website.ui.pages.BasePage;
import com.arthrex.website.ui.pages.common.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserProfilePage extends BasePage {
    @FindBy(xpath = "//div[@data-testid='navLoginInitials']")
    private WebElement btnProfile;

    @FindBy(id = "userAccount-username")
    private WebElement txtUsername;

    public UserProfilePage(WebDriver driver) {
        super(driver);
    }

    public String getUsername() {
        return this.getAttributeValue(this.txtUsername, "value");
    }

    public MainPage logout() {
        this.click(this.btnProfile);

        WebElement btnLogout = null;
        try {
            btnLogout = this.driver.findElement(By.xpath("//button[text()='Logout']"));
        } catch (NoSuchElementException ignored) {}

        if (btnLogout != null)
            this.click(btnLogout);

        return new MainPage(this.driver);
    }
}
