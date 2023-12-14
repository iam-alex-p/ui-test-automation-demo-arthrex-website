package com.arthrex.website.ui.pages.account;

import com.arthrex.website.ui.pages.BasePage;
import com.arthrex.website.ui.pages.common.ToastMessagesPage;
import com.arthrex.website.ui.tests.data.TestDataEntities;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AccountInfoPage extends BasePage {
    private static final String ERROR_ELEMENT_ID = "errorelement";

    public static final String USERNAME_ID = "userAccount-username";
    @FindBy(id = USERNAME_ID)
    private WebElement txtUsername;

    public static final String EMAIL_ID = "userAccount-email";
    @FindBy(id = EMAIL_ID)
    private WebElement txtEmail;

    public static final String PASSWORD_ID = "userAccount-password";
    @FindBy(id = PASSWORD_ID)
    private WebElement txtPassword;

    public static final String PASSWORD_CONFIRMATION_ID = "userAccount-passwordConfirmation";
    @FindBy(id = PASSWORD_CONFIRMATION_ID)
    private WebElement txtConfirmPassword;

    public static final String FIRST_NAME_ID = "userAccount-firstName";
    @FindBy(id = FIRST_NAME_ID)
    private WebElement txtFirstName;

    public static final String LAST_NAME_ID = "userAccount-lastName";
    @FindBy(id = LAST_NAME_ID)
    private WebElement txtLastName;

    @FindBy(css = "input[id*='designationusers']")
    private List<WebElement> lstUserDesignations;

    public static final String PRACTICE_NAME_ID = "userAccount-organization";
    @FindBy(id = PRACTICE_NAME_ID)
    private WebElement txtPracticeName;

    public static final String PRACTICE_COUNTRY_ID = "userAccount-organizationcountry";
    @FindBy(css = "div[id*='organizationcountry']")
    private WebElement drpdownPracticeCountry;

    public static final String PRACTICE_ZIP_ID = "userAccount-organizationzip";
    @FindBy(id = PRACTICE_ZIP_ID)
    private WebElement txtPracticeZip;

    public static final String PRACTICE_SPECIALTY_ID = "userAccount-specialty";
    @FindBy(css = "div[id*='specialty']")
    private WebElement drpdownPracticeSpeciality;

    public static final String ACCEPT_TERMS_NAME = "acceptterms";
    @FindBy(name = ACCEPT_TERMS_NAME)
    private WebElement chckBoxAcceptTerms;

    @FindBy(xpath = "//input[@value='Next']")
    private WebElement btnNext;

    public AccountInfoPage(WebDriver driver) {
        super(driver);
    }

    public void enterTextFieldValue(String value, AccountField accountField) throws InterruptedException {
        WebElement accountFieldElement = null;

        switch (accountField) {
            case USERNAME -> accountFieldElement = this.txtUsername;
            case EMAIL -> accountFieldElement = this.txtEmail;
            case PASSWORD -> accountFieldElement = this.txtPassword;
            case PASSWORD_CONFIRM -> accountFieldElement = this.txtConfirmPassword;
            case FIRST_NAME -> accountFieldElement = this.txtFirstName;
            case LAST_NAME -> accountFieldElement = this.txtLastName;
            case PRACTICE_NAME -> accountFieldElement = this.txtPracticeName;
            case PRACTICE_ZIP -> accountFieldElement = this.txtPracticeZip;
            case PRACTICE_COUNTRY -> accountFieldElement = this.drpdownPracticeCountry;
            case PRACTICE_SPECIALTY -> accountFieldElement = this.drpdownPracticeSpeciality;
        }


        if (accountFieldElement != null) {
            this.moveToElement(accountFieldElement);

            if (accountField.equals(AccountField.PRACTICE_COUNTRY) || accountField.equals(AccountField.PRACTICE_SPECIALTY)) {
                this.click(accountFieldElement);
                WebElement txtInput = accountFieldElement.findElement(By.xpath(".//input[@type='text']"));
                this.enterText(txtInput, String.format("%s%s", value, Keys.RETURN));
            }
            else
                this.enterText(accountFieldElement, value);
        }
    }

    public ConsentDataProcessingPage clickNext() throws InterruptedException {
        this.moveToElement(this.btnNext);
        this.click(this.btnNext);

        return new ConsentDataProcessingPage(this.driver);
    }

    public WebElement getErrorElement(String elementId) {
        List<WebElement> lstAccountInfoElements = this.driver.findElements(By.id(elementId));

        for (WebElement accountInfoElement : lstAccountInfoElements)
            if (this.isElementHasAttr(accountInfoElement, ERROR_ELEMENT_ID))
                return accountInfoElement;

        return null;
    }

    public void uncheckAllUserDesignations() {
        for (WebElement chkBoxDesignation : this.lstUserDesignations) {
            if (chkBoxDesignation.isSelected())
                this.click(chkBoxDesignation);
        }
    }

    public void selectUserDesignation(String designation) throws InterruptedException {
        for (WebElement chkBoxDesignation : this.lstUserDesignations) {
            WebElement userDesignation = chkBoxDesignation.findElement(By.xpath(".."));

            if (userDesignation.getText().equals(designation) && !chkBoxDesignation.isSelected()) {
                this.moveToElement(userDesignation);
                this.click(userDesignation);
                return;
            }
        }
    }

    public void acceptTermsAndConditions() {
        if (!this.chckBoxAcceptTerms.isSelected())
            this.click(this.chckBoxAcceptTerms);
    }

    public AccountInfoPage fillAccountInfo(TestDataEntities.WebsiteUser websiteUser) throws InterruptedException {
        this.enterTextFieldValue(websiteUser.getUsername(), AccountField.USERNAME);
        this.enterTextFieldValue(websiteUser.getEmail(), AccountField.EMAIL);
        this.enterTextFieldValue(websiteUser.getPassword(), AccountField.PASSWORD);
        this.enterTextFieldValue(websiteUser.getPassword(), AccountField.PASSWORD_CONFIRM);
        this.enterTextFieldValue(websiteUser.getFirstName(), AccountField.FIRST_NAME);
        this.enterTextFieldValue(websiteUser.getLastName(), AccountField.LAST_NAME);

        for (String designation : websiteUser.getArrDesignation())
            this.selectUserDesignation(designation);

        this.enterTextFieldValue(websiteUser.getPracticeName(), AccountField.PRACTICE_NAME);
        this.enterTextFieldValue(websiteUser.getPracticeCountry(), AccountField.PRACTICE_COUNTRY);
        this.enterTextFieldValue(websiteUser.getPracticeZip(), AccountField.PRACTICE_ZIP);
        this.enterTextFieldValue(websiteUser.getPracticeSpecialty(), AccountField.PRACTICE_SPECIALTY);

        return this;
    }

    public String getAlertSuccessText() {
        ToastMessagesPage toastMessagesPage = new ToastMessagesPage(this.driver);
        WebElement alertSuccess = toastMessagesPage.getAlertSuccess();

        return alertSuccess != null ? this.getWebElementText(alertSuccess) : null;
    }
}
