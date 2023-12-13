package com.arthrex.website.ui.pages.common;

import com.arthrex.website.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {
    private HeaderMenuPage headerMenuPage;

    public MainPage(WebDriver driver) {
        super(driver);
        this.headerMenuPage = new HeaderMenuPage(this.driver);
    }

    public HeaderMenuPage getHeaderMenuPage() {
        return headerMenuPage;
    }
}
