package com.arthrex.website.ui.tests.stepDefinitions;

import com.arthrex.website.ui.pages.account.LoginPage;
import com.arthrex.website.ui.pages.common.MainPage;
import com.arthrex.website.ui.tests.TestContext;
import com.arthrex.website.ui.utilities.Consts;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class CommonStepDefinitions extends BaseTest {
    public CommonStepDefinitions(TestContext testContext) {
        super(testContext);
    }

    @Given("Arthrex Website is open")
    public void arthrex_website_is_open() {
        this.testContext.getDriver().get(Consts.URL_ARTHREX);
    }

    @When("I click Login Button")
    public void i_click_login() {
        new MainPage(this.testContext.getDriver()).getHeaderMenuPage().clickLogin();
    }

    @And("I click Create Website Profile Button")
    public void i_click_create_website_profile() throws InterruptedException {
        new LoginPage(this.testContext.getDriver()).clickCreateWebsiteProfile();
    }
}
