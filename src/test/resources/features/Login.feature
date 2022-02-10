Feature: Login


  Scenario: The user login with valid credentials
    Given the user login page
    When the user type username "username" in username box
    And the user type password "password" in password box and enter
    Then the user should be on "Account Summary" page


  Scenario Outline: The user login with invalid credentials
    Given the user login page
    When the user type username "<username>" in username box
    And the user type password "<password>" in password box and enter
    Then the error message "Login and/or password are wrong." should be displayed

    Examples:
      | username  | password  |
      | anyuser   | password  |
      | username  | wrongone  |
      | wronguser | wrongpass |
      | username  |           |
      |           | password  |
      |           |           |


