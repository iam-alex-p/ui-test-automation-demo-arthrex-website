package com.arthrex.website.ui.pages.account;

public enum AccountField {
    USERNAME(AccountInfoPage.USERNAME_ID),
    EMAIL(AccountInfoPage.EMAIL_ID),
    PASSWORD(AccountInfoPage.PASSWORD_ID),
    PASSWORD_CONFIRM(AccountInfoPage.PASSWORD_CONFIRMATION_ID),
    FIRST_NAME(AccountInfoPage.FIRST_NAME_ID),
    LAST_NAME(AccountInfoPage.LAST_NAME_ID),
    PRACTICE_NAME(AccountInfoPage.PRACTICE_NAME_ID),
    PRACTICE_ZIP(AccountInfoPage.PRACTICE_ZIP_ID),
    PRACTICE_COUNTRY(AccountInfoPage.PRACTICE_COUNTRY_ID),
    PRACTICE_SPECIALTY(AccountInfoPage.PRACTICE_SPECIALTY_ID);

    private final String webElementID;

    AccountField(String webElementID) {
        this.webElementID = webElementID;
    }

    public String getWebElementID() {
        return webElementID;
    }
}
