Feature: 'Sign-In' possibility with valid credentials
  As a registered user
  I want to successfully sign in to the system
  So that I can access my account

  Scenario: Successful 'Sign-In' possibility with valid credentials
    Given GreenCity is open
    When user clicks the "Sign In" Tab in the header
    And user enter a registered user email
    And user enter a registered user password
    And user clicks Sign In button with successful login
    Then user should be redirected to the user profile page
    And user name should be in the header