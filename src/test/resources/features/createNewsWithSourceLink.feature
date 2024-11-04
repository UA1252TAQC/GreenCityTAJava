Feature: Create Eco News Article
  As a registered user on the GreenCity site
  I want to create a new eco news article
  So that it can be published on the site

  Scenario: Successful creation of eco news article with valid data
    Given Log in as User
    And the interface language is set to English
    And the 'Eco news' page is opened
    When the user clicks on the 'Create news' button
    And the user enters "International Conference on Higher Education Teaching and Learning" in the 'Title' field
    And the user selects the "Education" tag
    And the user enters "International Conference on Higher Education Teaching and Learning (ICHETL)" in the 'Content' field
    And the user enters "https://internationalconferencealerts.com/education-conference" in the 'Source' field
    And the user clicks on the 'Publish' button
    Then the news article should be created
    And the user should be redirected to the 'My space' page
