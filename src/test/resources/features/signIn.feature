#noinspection NonAsciiCharacters
Feature: Display error messages in for incorrect input
  As a user I expect system to inform me why it is not working
  I want to see error messages for incorrect password inputs
  I want to see error messages for incorrect email inputs

  Scenario Outline: User inputs invalid password
    Given GreenCity is open
    And the interface language is set to "<language>"
    When user clicks the "Sign In" Tab in the header
    And user enters a valid email "test@mail.com"
    And user enters a invalid password "******************"
    And user clicks "Sign In" button
    Then the "<error message>" error message is appeared

    Examples:
    | language  | error message                      |
    | en        | Bad email or password.             |
    | ua        | Введено невірний email або пароль. |
