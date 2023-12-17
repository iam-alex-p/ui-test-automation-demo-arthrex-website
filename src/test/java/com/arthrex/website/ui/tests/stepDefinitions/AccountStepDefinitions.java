package com.arthrex.website.ui.tests.stepDefinitions;

import com.arthrex.website.ui.pages.account.*;
import com.arthrex.website.ui.tests.TestContext;
import com.arthrex.website.ui.tests.data.TestDataEntities;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class AccountStepDefinitions extends BaseTest {
    private RoleSelectionPage roleSelectionPage = new RoleSelectionPage(this.testContext.getDriver());
    private SubscriptionInfoPage subscriptionInfoPage;
    private AccountInfoPage accountInfoPage;

    public AccountStepDefinitions(TestContext testContext) {
        super(testContext);
    }

    @DataTableType(replaceWithEmptyString = "[blank]")
    public TestDataEntities.PageFieldsVerificationEntity accountFieldEntityTransformer(Map<String, String> row) {
        return new TestDataEntities.PageFieldsVerificationEntity(
                row.getOrDefault("inputValue", ""),
                Boolean.parseBoolean(row.getOrDefault("isValid", "false")),
                row.getOrDefault("errorMessage", ""),
                row.getOrDefault("description", "")
        );
    }

    @DataTableType(replaceWithEmptyString = "[blank]")
    public TestDataEntities.AccountPagePasswordVerificationEntity passwordVerificationEntityTransformer(Map<String, String> row) {
        return new TestDataEntities.AccountPagePasswordVerificationEntity(
                row.getOrDefault("password", ""),
                row.getOrDefault("passwordConfirmation", ""),
                Boolean.parseBoolean(row.getOrDefault("isValid", "false")),
                row.getOrDefault("passwordErrorMessage", ""),
                row.getOrDefault("passwordConfirmationErrorMessage", ""),
                row.getOrDefault("description", "")
        );
    }

    @Then("Following Account Roles should be present")
    public void all_expected_account_roles_should_be_present(DataTable tblAccountRoles) {
        this.roleSelectionPage = new RoleSelectionPage(this.testContext.getDriver());

        Map<String, String> mapExpectedAccountRoles = tblAccountRoles.asMap();
        Map<String, String> mapActualAccountRoles = this.roleSelectionPage.getAccountRoles();

        for (String expectedRole : mapExpectedAccountRoles.keySet()) {
            Assert.assertTrue(mapActualAccountRoles.containsKey(expectedRole), String.format("Role [%s] was not found on the Website", expectedRole));
            if (mapActualAccountRoles.containsKey(expectedRole))
                Assert.assertEquals(mapActualAccountRoles.get(expectedRole), mapExpectedAccountRoles.get(expectedRole), String.format("Description is unexpected for the Role [%s]", expectedRole));
        }
    }

    @When("I select {string} Account Role")
    public void i_select_account_role(String roleName) {
        this.subscriptionInfoPage = this.roleSelectionPage.selectAccountRole(roleName);
    }

    @And("I click Next on Subscription Information Page")
    public void i_click_next_on_subscription_info_page() throws InterruptedException {
        this.accountInfoPage = this.subscriptionInfoPage.clickNext();
    }

    @Then("Verify {string} Account Field with the following Data")
    public void verify_account_field_with_following_data(String accountField, List<TestDataEntities.PageFieldsVerificationEntity> lstTestData) throws InterruptedException {
        AccountField field = AccountField.valueOf(accountField.toUpperCase().replaceAll(" ", "_"));
        String errorWebElementID = field.getWebElementID();

        for (TestDataEntities.PageFieldsVerificationEntity testDataEntity : lstTestData) {
            this.accountInfoPage.enterTextFieldValue(testDataEntity.getInputValue(), field);
            this.accountInfoPage.clickNext();

            WebElement elementError = this.accountInfoPage.getErrorElement(errorWebElementID);
            if (!testDataEntity.getIsValid()) {
                Assert.assertNotNull(elementError, String.format("Error Message [%s] is not present for invalid Input Value [%s] with the Description [%s]", testDataEntity.getErrorMessage(), testDataEntity.getInputValue(), testDataEntity.getDescription()));
                Assert.assertEquals(elementError.getText(), testDataEntity.getErrorMessage(), String.format("Error message for Input Value [%s] does not match for Account Field with ID = [%s]", testDataEntity.getInputValue(), errorWebElementID));
            } else
                Assert.assertNull(elementError, String.format("Error message is present for valid Account Field input [%s]", testDataEntity.getInputValue()));
        }
    }

    @Then("Verify Password Confirmation Logic with the following Data")
    public void verify_password_confirmation_with_following_data(List<TestDataEntities.AccountPagePasswordVerificationEntity> lstTestData) throws InterruptedException {
        AccountField pwdConfirmationAccountField = AccountField.PASSWORD_CONFIRM;
        String pwdConfirmErrorWebElementID = pwdConfirmationAccountField.getWebElementID();

        for (TestDataEntities.AccountPagePasswordVerificationEntity testDataEntity : lstTestData) {
            this.accountInfoPage.enterTextFieldValue(testDataEntity.getPassword(), AccountField.PASSWORD);
            this.accountInfoPage.enterTextFieldValue(testDataEntity.getPasswordConfirmation(), pwdConfirmationAccountField);
            this.accountInfoPage.clickNext();

            WebElement elementError = this.accountInfoPage.getErrorElement(pwdConfirmErrorWebElementID);
            if (!testDataEntity.getIsValid()) {
                Assert.assertNotNull(elementError, String.format("Error Message [%s] is not present for invalid Input Value [%s] with the Description [%s]", testDataEntity.getPasswordConfirmationErrorMessage(), testDataEntity.getPasswordConfirmation(), testDataEntity.getDescription()));
                Assert.assertEquals(elementError.getText(), testDataEntity.getPasswordConfirmationErrorMessage(), String.format("Error message for Input Value [%s] does not match for Account Field with ID = [%s]", testDataEntity.getPasswordConfirmation(), pwdConfirmErrorWebElementID));
            } else
                Assert.assertNull(elementError, String.format("Error message is present for valid Account Field input [%s]", testDataEntity.getPasswordConfirmation()));
        }
    }

    @When("I uncheck all User Designations")
    public void i_uncheck_all_user_designations() {
        this.accountInfoPage.uncheckAllUserDesignations();
    }

    @Then("Error Message with text {string} should appear")
    public void error_message_with_text_should_appear(String errorText) {
        Assert.assertTrue(this.accountInfoPage.isElementWithTextExists(errorText), String.format("Error with Text [%s] did not appear!", errorText));
    }

    @When("I select following User Designations")
    public void i_select_following_user_designations(DataTable tblUserDesignations) throws InterruptedException {
        List<String> lstUserDesignations = tblUserDesignations.asList();

        for (String userDesignations : lstUserDesignations)
            this.accountInfoPage.selectUserDesignation(userDesignations);
    }

    @Then("Error Message with text {string} should disappear")
    public void error_message_with_text_should_disappear(String errorText) {
        if (this.accountInfoPage.getElementWithTextOrNull(errorText) != null)
            this.accountInfoPage.waitForElementToDisappear(this.accountInfoPage.getElementWithTextOrNull(errorText));

        Assert.assertFalse(this.accountInfoPage.isElementWithTextExists(errorText), String.format("Error with Text [%s] did not disappear!", errorText));
    }
}
