Feature: Login functionality

  Scenario: Successfully logging into the system as a user
    Given I am on the home page
    When I navigate to the Login Page
    And I sign in with the username "PabloTestUser@gmail.com" and password "Password1234"
    Then I should see the welcome page
    And the Welcome Page should be correctly displayed