Feature: Pay Bills


  Scenario: Page title
    Given the user is logged in
    When the user accesses the "Pay Bills" tab
    Then the page title should be "Zero - Pay Bills"


  Scenario: Make payment as expected
    Given the user is logged in
    When the user accesses the "Pay Bills" tab
    And choose "Apple" from Payee
    And choose "Loan" from Account
    And type "100" for Amount
    And type "2022-03-12" for Date
    And type "my payment" as Description
    And click pay button
    Then the text "The payment was successfully submitted." should be displayed


  Scenario: Payment without entering amount
    Given the user is logged in
    When the user accesses the "Pay Bills" tab
    And choose "Wells Fargo" from Payee
    And choose "Loan" from Account
    And type "" for Amount
    And type "2022-02-24" for Date
    And type "payment" as Description
    And click pay button
    Then the text "Lütfen bu alanı doldurun." should be displayed from Amount box



  Scenario: Payment without entering any characters to date
    Given the user is logged in
    When the user accesses the "Pay Bills" tab
    And choose "Wells Fargo" from Payee
    And choose "Loan" from Account
    And type "214" for Amount
    And type "" for Date
    And type "payment" as Description
    And click pay button
    Then the text "Lütfen bu alanı doldurun." should be displayed from Date box


  Scenario: Payment entering alphabetical and special characters to amount
    Given the user is logged in
    When the user accesses the "Pay Bills" tab
    And choose "Wells Fargo" from Payee
    And choose "Loan" from Account
    And type "abc&%*$" for Amount
    And type "2022-02-24" for Date
    And type "payment" as Description
    And click pay button
    Then the text "Lütfen bu alanı doldurun." should be displayed from Amount box


  Scenario: Payment entering Alphabetical characters to date
    Given the user is logged in
    When the user accesses the "Pay Bills" tab
    And choose "Wells Fargo" from Payee
    And choose "Loan" from Account
    And type "214" for Amount
    And type "abc" for Date
    And type "payment" as Description
    And click pay button
    Then the text "Lütfen bu alanı doldurun." should be displayed from Date box