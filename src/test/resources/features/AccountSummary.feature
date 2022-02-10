Feature: Account Summary


  Scenario: Page title
    Given the user is logged in
    Then the page title should be "Zero - Account Summary"


  Scenario: Account types
    Given the user is logged in
    Then Account Summary page should have to following account types
      | Cash Accounts       |
      | Investment Accounts |
      | Credit Accounts     |
      | Loan Accounts       |


  Scenario: Credit Cards features
    Given the user is logged in
    Then "Credit Accounts" table should have following columns
      | Account     |
      | Credit Card |
      | Balance     |


  Scenario Outline: <Account Type> features
    Given the user is logged in
    Then "<Account Type>" table should have following columns
      | Account     |
      | Balance     |
    Examples:
      | Account Type        |
      | Cash Accounts       |
      | Investment Accounts |
      | Loan Accounts       |

