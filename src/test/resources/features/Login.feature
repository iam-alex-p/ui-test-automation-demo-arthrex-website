Feature: Arthrex Website Login

  Background:
    Given Arthrex Website is open
    When I click Login Button

  @SmokeTest @AccountCreation
  Scenario: Arthrex Website Account Creation
    And I click Create Website Profile Button
    When I create a Website user with the following Information
      | userRole                | arrDesignation | practiceCountry | practiceSpecialty |
      | Non-Operative Physician | DO,MD,PhD      | Russia          | Hand Wrist        |
    Then Alert with success message should appear
    And User Profile Page should appear with correct Username
    When I logout
    And I sign in with this User
    Then Profile button should appear