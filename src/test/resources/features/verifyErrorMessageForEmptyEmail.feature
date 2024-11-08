#noinspection NonAsciiCharacters
Feature: Display error messages in for missing email field input
  As a user I expect system to inform me why it is not working
  I want to see error messages for not entering email field

  Scenario Outline: User don't input email
    Given GreenCity is open
    And the interface language is set to "<language>"
    When user clicks the "Sign In" Tab in the header
    And user enters a valid password "Test1234!"
    And user clicks "Sign In" button
    Then the "<error message>" email error message is appeared

    Examples:
      | language  | error message       |
      | en        | Email is required.  |
      | ua        | Введіть пошту.      |
