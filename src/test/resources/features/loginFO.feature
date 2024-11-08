Feature: Login

  Scenario: Sign in button becomes active and highlighted in green with valid credentials
    Given I open the login form
    When I enter a valid email
    And I enter a valid password
    Then the SignIn button should become active
    And the SignIn button should be highlighted in green



  Scenario Outline: Verify error message for exceeding password length
    Given the application is set to "<lang>" language
    When I open the login form
    And I enter a valid email
    And I enter the password
    And I click inside the form
    Then I should see an error message "<expected>" in the password field
    Examples:
      | lang         | expected                                                      |
      | EN           | Password must be less than 20 characters long without spaces. |