Feature: Login

  Scenario: Sign in button becomes active and highlighted in green with valid credentials
    Given I open the login form
    When I enter a valid email
    And I enter a valid password
    Then the "Sign in" button should become active
    And the "Sign in" button should be highlighted in green