Feature: Arthrex Website Account

  Background:
    Given Arthrex Website is open
    When I click Login Button
    And I click Create Website Profile Button

  @SmokeTest @WebsiteProfile @AccountRoles
  Scenario: Verify Arthrex Account Roles
    Then Following Account Roles should be present
      | Operative Physician        | Orthopedic, General Surgery, Trauma, Podiatry, Spine, Neurology, etc                               |
      | Non-Operative Physician    | Sports Medicine, Pain Management, Neurology, Rheumatology, Internal Medicine, Family Medicine, etc |
      | Non-Physician HCP          | PA, RN, APRN, Surgical Technologist, Physical Therapist, Athletic Trainer, etc                     |
      | Health Care Administration | Executive, Director, Nurse/Clinical Leader, ASC Administrator, Information Technology              |

  @SmokeTest @WebsiteProfile @AccountInfoUsername
  Scenario: Verify Arthrex Username Account Information
    When I select "Non-Operative Physician" Account Role
    And I click Next on Subscription Information Page
    Then Verify "Username" Account Field with the following Data
      | inputValue                                                                                           | isValid | errorMessage                                                                | description                     |
      | [blank]                                                                                              | false   | Username cannot be empty                                                    | Invalid Empty Username          |
      | H                                                                                                    | false   | Username must consist of at least 4 characters and less than 60 characters. | Invalid 1 Character Username    |
      | kDR                                                                                                  | false   | Username must consist of at least 4 characters and less than 60 characters. | Invalid 3 Characters Username   |
      | Zmeg                                                                                                 | true    |                                                                             | Valid 4 Characters Username     |
      | 5SZ1X                                                                                                | true    |                                                                             | Valid 5 Characters Username     |
      | V5aXEL4QmJcCTuJVN8mMf1urYgytAJzjuLgf                                                                 | true    |                                                                             | Valid 36 Characters Username    |
      | guyvQrc36mg8ftXFcD6BY3KuwFEUWG2DBKZQU9z0mmn34BJiN7FqXPVkx1Z                                          | true    |                                                                             | Valid 59 Characters Username    |
      | pcdCbjnA05ETGuD7XtqV5xh3zvzkCymMRjxgz5KN6cvDfATHMxPCJthwLEkPf                                        | false   | Username must consist of at least 4 characters and less than 60 characters. | Invalid 61 Characters Username  |
      | B9WGxQFgfCcpugGwGRQJjARecNpB2HCjLXSL76FBgvZPgfCeHfTJx8L6PvYrcqvWhC8vQP0gLWe0ZbdQQ4y6H3yR3UtbgiQAfWG2 | false   | Username must consist of at least 4 characters and less than 60 characters. | Invalid 100 Characters Username |
      | 91YLD90rpfWDWxVcv1vAb3pNL0p3Hwdvi0MURatMgB1NV47aac6vQu07JN2h                                         | false   | Username must consist of at least 4 characters and less than 60 characters. | Invalid 60 Characters Username  |

  @SmokeTest @WebsiteProfile @AccountInfoEmail
  Scenario: Verify Arthrex Email Account Information
    When I select "Non-Operative Physician" Account Role
    And I click Next on Subscription Information Page
    Then Verify "Email" Account Field with the following Data
      | inputValue          | isValid | errorMessage                                | description                         |
      | [blank]             | false   | Email cannot be empty                       | Invalid Empty Email                 |
      | t@e.a               | false   | Email must consist of at least 7 characters | Valid Email, 5 Characters           |
      | t@bk.h              | false   | Email must consist of at least 7 characters | Valid Email, 6 Characters           |
      | test                | false   | Email must consist of at least 7 characters | Invalid Email, 4 Characters         |
      | t@bkbch             | false   | Sorry, you must enter a valid Email         | Invalid Email, 7 Characters         |
      | TestingEmail        | false   | Sorry, you must enter a valid Email         | Invalid Email, 12 Characters        |
      | EmailExample@       | false   | Sorry, you must enter a valid Email         | Invalid Email Format, 13 Characters |
      | EmailExample@gmail  | false   | Sorry, you must enter a valid Email         | Invalid Email Format, 18 Characters |
      | EmailExample@gmail. | false   | Sorry, you must enter a valid Email         | Invalid Email Format, 19 Characters |
      | t@bk.hk             | true    |                                             | Valid Email, 7 Characters           |
      | te@bk.hk            | true    |                                             | Valid Email, 8 Characters           |
      | teste@gmail.com     | true    |                                             | Valid Email, 15 Characters          |

  @SmokeTest @WebsiteProfile @AccountInfoPassword
  Scenario: Verify Arthrex Password Account Information
    When I select "Non-Operative Physician" Account Role
    And I click Next on Subscription Information Page
    Then Verify "Password" Account Field with the following Data
      | inputValue                 | isValid | errorMessage                                   | description                    |
      | [blank]                    | false   | Password must consist of at least 8 characters | Invalid Empty Password         |
      | C                          | false   | Password must consist of at least 8 characters | Invalid Password, 1 Character  |
      | Q3eBi                      | false   | Password must consist of at least 8 characters | Invalid Password, 5 Characters |
      | qHuZeh6                    | false   | Password must consist of at least 8 characters | Invalid Password, 7 Characters |
      | WJzAhynU                   | true    |                                                | Valid Password, 8 Characters   |
      | EHMWUrJCu                  | true    |                                                | Valid Password, 9 Characters   |
      | XR1pTKQ2RVk0uZVpzXfwYHcdtZ | true    |                                                | Valid Password, 26 Characters  |

  @SmokeTest @WebsiteProfile @AccountInfoFirstName
  Scenario: Verify Arthrex First Name Account Information
    When I select "Non-Operative Physician" Account Role
    And I click Next on Subscription Information Page
    Then Verify "First Name" Account Field with the following Data
      | inputValue                                     | isValid | errorMessage                                     | description                      |
      | [blank]                                        | false   | First Name cannot be empty                       | Invalid Empty First Name         |
      | tiBznqEvJzwLFGvp7da8eL1DBeunn6D                | false   | First Name cannot be greater than 30 characters. | Invalid 31 Characters First Name |
      | LDC6b7CxD67UY1bvhZDfwBZnBU8nUxgpwW96SDMKPzRW3C | false   | First Name cannot be greater than 30 characters. | Invalid 46 Characters First Name |
      | E                                              | true    |                                                  | Valid 1 Character First Name     |
      | NMvc72GJSQpWHSH8                               | true    |                                                  | Valid 16 Characters First Name   |
      | MmDq37dbkz7dKuPmuEHqcCqz5jB96                  | true    |                                                  | Valid 29 Characters First Name   |
      | pkNSLuqpH2DD2wgAtjnck5mpN7WdYW                 | true    |                                                  | Valid 30 Characters First Name   |

  @SmokeTest @WebsiteProfile @AccountInfoLastName
  Scenario: Verify Arthrex Last Name Account Information
    When I select "Non-Operative Physician" Account Role
    And I click Next on Subscription Information Page
    Then Verify "Last Name" Account Field with the following Data
      | inputValue                                     | isValid | errorMessage                                          | description                     |
      | [blank]                                        | false   | Last Name cannot be empty                             | Invalid Empty Last Name         |
      | E                                              | false   | Last Name should each be between 2 and 30 characters. | Invalid 1 Character Last Name   |
      | tiBznqEvJzwLFGvp7da8eL1DBeunn6D                | false   | Last Name should each be between 2 and 30 characters. | Invalid 31 Characters Last Name |
      | LDC6b7CxD67UY1bvhZDfwBZnBU8nUxgpwW96SDMKPzRW3C | false   | Last Name should each be between 2 and 30 characters. | Invalid 46 Characters Last Name |
      | Ea                                             | true    |                                                       | Valid 2 Characters Last Name    |
      | Eac                                            | true    |                                                       | Valid 3 Characters Last Name    |
      | NMvc72GJSQpWHSH8                               | true    |                                                       | Valid 16 Characters Last Name   |
      | MmDq37dbkz7dKuPmuEHqcCqz5jB96                  | true    |                                                       | Valid 29 Characters Last Name   |
      | pkNSLuqpH2DD2wgAtjnck5mpN7WdYW                 | true    |                                                       | Valid 30 Characters Last Name   |

  @SmokeTest @WebsiteProfile @AccountInfoPracticeName
  Scenario: Verify Arthrex Practice Name Account Information
    When I select "Non-Operative Physician" Account Role
    And I click Next on Subscription Information Page
    Then Verify "Practice Name" Account Field with the following Data
      | inputValue                                                                                                                                                                                                                                                       | isValid | errorMessage                 | description                          |
      | [blank]                                                                                                                                                                                                                                                          | false   | Organization cannot be empty | Invalid Empty Practice Name          |
      | P                                                                                                                                                                                                                                                                | true    |                              | Valid 1 Character Practice Name      |
      | yfCekAJfmDbzgtCNE2U7qnw99                                                                                                                                                                                                                                        | true    |                              | Valid 25 Characters Practice Name    |
      | ScaAUZmfnpj7V4DGkFpHbFSjBzqneXxBZWXEmLuk6NpkXKFtT96h8qyzikuhM078Xp0i26dRGUFC3McgnKYvWpbfFTKigWxVZCECiEdmfkawtxfVy9Lptv5URHbB9jmU9apTDL2UfiwbB71bAtmD6Carye0GFy67BYvymNwtSy9WtRf2j1DfipxYde91W3Mq3iCqmhqPrqxJma7mj9f0hfTiGQTfQaLvihkZtWDk8RGS8BTStMSTCfafDH71DeQ  | true    |                              | Valid 255 Characters Practice Name   |

  @SmokeTest @WebsiteProfile @AccountInfoPracticeZip
  Scenario: Verify Arthrex Practice Zip Code Account Information
    When I select "Non-Operative Physician" Account Role
    And I click Next on Subscription Information Page
    Then Verify "Practice ZIP" Account Field with the following Data
      | inputValue | isValid | errorMessage                         | description                     |
      | [blank]    | false   | Zip Code cannot be empty             | Invalid Empty Practice Zip Code |
      | ZIP Code   | false   | You must enter a valid U.S. Zip Code | Invalid US Zip Code             |
      | 97232-     | false   | You must enter a valid U.S. Zip Code | Invalid US Zip Code             |
      | 18235-1    | false   | You must enter a valid U.S. Zip Code | Invalid US Zip Code             |
      | 7241-12    | false   | You must enter a valid U.S. Zip Code | Invalid US Zip Code             |
      | 7241-123   | false   | You must enter a valid U.S. Zip Code | Invalid US Zip Code             |
      | 7241-abcd  | false   | You must enter a valid U.S. Zip Code | Invalid US Zip Code             |
      | abcd       | false   | You must enter a valid U.S. Zip Code | Invalid US Zip Code             |
      | abcd-efgh  | false   | You must enter a valid U.S. Zip Code | Invalid US Zip Code             |
      | 156025     | false   | You must enter a valid U.S. Zip Code | Invalid US Zip Code             |
      | 12345      | true    |                                      | Valid US Zip Code               |
      | 18235-2239 | true    |                                      | Valid US Zip Code               |

  @SmokeTest @WebsiteProfile @AccountInfoPassword
  Scenario: Verify Arthrex Account Password Confirmation Logic
    When I select "Non-Operative Physician" Account Role
    And I click Next on Subscription Information Page
    Then Verify Password Confirmation Logic with the following Data
      | password         | passwordConfirmation | isValid | passwordErrorMessage | passwordConfirmationErrorMessage              | description                                      |
      | t                | [blank]              | false   |                      | Password and Confirmation Password must match | Invalid Password, Invalid Blank Confirmation     |
      | uB0Mx7KJn2pr0h00 | [blank]              | false   |                      | Password and Confirmation Password must match | Valid Password, Invalid Blank Confirmation       |
      | AjY6YQUxfWD2     | C1SwTmWFBuaT         | false   |                      | Password and Confirmation Password must match | Valid Password, Invalid Non-Blank Confirmation   |
      | 5r7m3mRbSay4M    | 5r7m3mRbSay4         | false   |                      | Password and Confirmation Password must match | Valid Password, Invalid Non-Blank Confirmation   |
      | [blank]          | [blank]              | true    |                      |                                               | Invalid Blank Password, Valid Blank Confirmation |
      | P8PP0wCEqWQn     | P8PP0wCEqWQn         | true    |                      |                                               | Invalid Short Password, Valid Confirmation       |
      | 7wJ6FhFfrUF      | 7wJ6FhFfrUF          | true    |                      |                                               | Valid Password, Valid Confirmation               |

  @SmokeTest @WebsiteProfile @AccountInfoPracticeCountry
  Scenario: Verify Arthrex Practice Country Account Information
    When I select "Non-Operative Physician" Account Role
    And I click Next on Subscription Information Page
    Then Verify "Practice Country" Account Field with the following Data
      | inputValue       | isValid | errorMessage            | description                    |
      | Choose a country | false   | Country cannot be empty | Invalid Empty Practice Country |
      | Russia           | true    |                         | Valid Practice Country         |

  @SmokeTest @WebsiteProfile @AccountInfoPracticeSpecialty
  Scenario: Verify Arthrex Practice Specialty Account Information
    When I select "Non-Operative Physician" Account Role
    And I click Next on Subscription Information Page
    Then Verify "Practice Specialty" Account Field with the following Data
      | inputValue        | isValid | errorMessage                           | description                |
      | Invalid Specialty | false   | You must select at least one Specialty | Invalid Practice Specialty |
      | Foot Ankle        | true    |                                        | Valid Practice Specialty   |

  @SmokeTest @WebsiteProfile @AccountInfoUserDesignation
  Scenario: Verify Arthrex User Designation Account Information
    When I select "Non-Operative Physician" Account Role
    And I click Next on Subscription Information Page
    When I uncheck all User Designations
    And I click Next on Subscription Information Page
    Then Error Message with text "You must select a designation" should appear
    When I select following User Designations
      | DO    |
      | MD    |
    And I click Next on Subscription Information Page
    Then Error Message with text "You must select a designation" should disappear