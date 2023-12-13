package com.arthrex.website.ui.tests.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TestDataEntities {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PageFieldsVerificationEntity {
        private String inputValue;
        private Boolean isValid;
        private String errorMessage;
        private String description;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AccountPagePasswordVerificationEntity {
        private String password;
        private String passwordConfirmation;
        private Boolean isValid;
        private String passwordErrorMessage;
        private String passwordConfirmationErrorMessage;
        private String description;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WebsiteUser {
        private String userRole;
        private String username;
        private String email;
        private String password;
        private String firstName;
        private String lastName;
        private String[] arrDesignation;
        private String practiceName;
        private String practiceCountry;
        private String practiceZip;
        private String practiceSpecialty;
    }
}
