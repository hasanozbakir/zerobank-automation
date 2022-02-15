Feature: Navigating to specific accounts in Accounts Activity

  @smoke
  Scenario: Savings account redirect
    Given the user is logged in
    When the user clicks on "Savings" link on the Account Summary page
    Then the "Account Activity" page should be displayed
    And Account drop down should have "Savings" selected


  Scenario: Brokerage account redirect
    Given the user is logged in
    When the user clicks on "Brokerage" link on the Account Summary page
    Then the "Account Activity" page should be displayed
    And Account drop down should have "Brokerage" selected


  Scenario: Checking account redirect
    Given the user is logged in
    When the user clicks on "Checking" link on the Account Summary page
    Then the "Account Activity" page should be displayed
    And Account drop down should have "Checking" selected


  Scenario: Credit Card account redirect
    Given the user is logged in
    When the user clicks on "Credit Card" link on the Account Summary page
    Then the "Account Activity" page should be displayed
    And Account drop down should have "Credit Card" selected


  Scenario: Loan account redirect
    Given the user is logged in
    When the user clicks on "Loan" link on the Account Summary page
    Then the "Account Activity" page should be displayed
    And Account drop down should have "Loan" selected

  @DDT
  Scenario Outline: <accountType> account redirect
    Given the user is logged in
    When the user clicks on "<accountType>" link on the Account Summary page
    Then the "<accountSubPage>" page should be displayed
    And Account drop down should have "<accountType>" selected

    Examples:
      | accountType | accountSubPage   |
      | Savings     | Account Activity |
      | Brokerage   | Account Activity |
      | Checking    | Account Activity |
      | Credit Card | Account Activity |
      | Loan        | Account Activity |
