package com.arthrex.website.ui.tests.stepDefinitions;

import com.arthrex.website.ui.pages.account.*;
import com.arthrex.website.ui.pages.common.MainPage;
import com.arthrex.website.ui.tests.TestContext;
import com.arthrex.website.ui.tests.data.TestDataEntities;
import com.github.javafaker.Faker;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class LoginStepDefinition extends BaseTest {
    private Map<String, TestDataEntities.WebsiteUser> mapUserAccounts = new HashMap<>();

    private MainPage mainPage;
    private UserProfilePage userProfilePage;

    @DataTableType(replaceWithEmptyString = "[blank]")
    public TestDataEntities.WebsiteUser UserEntityTransformer(Map<String, String> row) {
        final String DEFAULT_USER_ROLE = "Operative Physician";
        final String DEFAULT_USER_DESIGNATION = "MBA";

        Faker faker = Faker.instance();

        return new TestDataEntities.WebsiteUser(
                row.getOrDefault("userRole", DEFAULT_USER_ROLE),
                row.getOrDefault("userName", faker.name().username()),
                row.getOrDefault("email", faker.internet().emailAddress()),
                row.getOrDefault("password", faker.internet().password(8, 25)),
                row.getOrDefault("firstName", faker.name().firstName()),
                row.getOrDefault("lastName", faker.name().lastName()),
                row.getOrDefault("arrDesignation", DEFAULT_USER_DESIGNATION).split(","),
                row.getOrDefault("practiceName", faker.medical().hospitalName()),
                row.getOrDefault("practiceCountry", ""),
                row.getOrDefault("practiceZip", faker.address().zipCode()),
                row.getOrDefault("practiceSpecialty", "")
        );
    }

    public LoginStepDefinition(TestContext testContext) {
        super(testContext);
    }

    @When("I create a Website user with the following Information")
    public void i_create_user_with_following_info(TestDataEntities.WebsiteUser websiteUser) throws InterruptedException {
        RoleSelectionPage roleSelectionPage = new RoleSelectionPage(this.testContext.getDriver());

        SubscriptionInfoPage subscriptionInfoPage = roleSelectionPage.selectAccountRole(websiteUser.getUserRole());
        AccountInfoPage accountInfoPage = subscriptionInfoPage.clickNext();

        accountInfoPage.enterTextFieldValue(websiteUser.getUsername(), AccountField.USERNAME);
        accountInfoPage.enterTextFieldValue(websiteUser.getEmail(), AccountField.EMAIL);
        accountInfoPage.enterTextFieldValue(websiteUser.getPassword(), AccountField.PASSWORD);
        accountInfoPage.enterTextFieldValue(websiteUser.getPassword(), AccountField.PASSWORD_CONFIRM);
        accountInfoPage.enterTextFieldValue(websiteUser.getFirstName(), AccountField.FIRST_NAME);
        accountInfoPage.enterTextFieldValue(websiteUser.getLastName(), AccountField.LAST_NAME);

        for (String designation : websiteUser.getArrDesignation())
            accountInfoPage.selectUserDesignation(designation);

        accountInfoPage.enterTextFieldValue(websiteUser.getPracticeName(), AccountField.PRACTICE_NAME);
        accountInfoPage.enterTextFieldValue(websiteUser.getPracticeCountry(), AccountField.PRACTICE_COUNTRY);
        accountInfoPage.enterTextFieldValue(websiteUser.getPracticeZip(), AccountField.PRACTICE_ZIP);
        accountInfoPage.enterTextFieldValue(websiteUser.getPracticeSpecialty(), AccountField.PRACTICE_SPECIALTY);

        accountInfoPage.acceptTermsAndConditions();
        accountInfoPage.clickNext().clickAcceptAndCreateAccount();

        this.mapUserAccounts.putIfAbsent(websiteUser.getUsername(), websiteUser);

        accountInfoPage.waitForElementToAppear(By.xpath("//span[text()='Save Settings']"));
        this.testContext.getDriver().findElement(By.xpath("//span[text()='Save Settings']")).click();
    }

    @Then("User Profile Page should appear with correct Username")
    public void user_profile_page_should_appear() {
        this.userProfilePage = new UserProfilePage(this.testContext.getDriver());

        TestDataEntities.WebsiteUser websiteUser = this.mapUserAccounts.entrySet().iterator().next().getValue();
        Assert.assertEquals(userProfilePage.getUsername(), websiteUser.getUsername(), "Username does not match!");
    }

    @When("I logout")
    public void i_logout() {
        this.mainPage = this.userProfilePage.logout();
    }

    @And("I sign in with this User")
    public void i_sign_in_with_user() {
        LoginPage loginPage = this.mainPage.getHeaderMenuPage().clickLogin();

        TestDataEntities.WebsiteUser websiteUser = this.mapUserAccounts.entrySet().iterator().next().getValue();

        loginPage.enterUsername(websiteUser.getUsername());
        loginPage.enterPassword(websiteUser.getPassword());
        loginPage.clickSignIn();
    }

    @Then("Profile button should appear")
    public void profile_buton_should_appear() {
        WebElement btnProfile = null;

        try {
            btnProfile = this.testContext.getDriver().findElement(By.xpath("//button[@data-testid='navLoginButton']"));
        } catch (NoSuchElementException ignored) {}

        Assert.assertNotNull(btnProfile, "Profile Button did not appear!");
    }
}
