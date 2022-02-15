Feature: Account Activity

  @wip
  Scenario: Page title
    Given the user is logged in
    When the user accesses the "Account Activity" tab
    Then the page title should be "Zero - Account Activity"

  @smoke
  Scenario: Account dropdown default condition
    Given the user is logged in
    When  the user accesses the "Account Activity" tab
    Then the dropdown default option should be "Savings"


  Scenario: Account dropdown default condition
    Given the user is logged in
    When  the user accesses the "Account Activity" tab
    Then the dropdown should have the following options
      | Savings     |
      | Checking    |
      | Loan        |
      | Credit Card |
      | Brokerage   |



  Scenario: Account dropdown default condition
    Given the user is logged in
    When  the user accesses the "Account Activity" tab
    Then Transactions table should have the following column names
      | Date        |
      | Description |
      | Deposit     |
      | Withdrawal  |
