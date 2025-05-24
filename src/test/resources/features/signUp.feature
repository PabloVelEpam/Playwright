Feature: User Sign Up

  As a new user
  I want to sign up on the application
  So that I can access the services provided by the application

  Scenario: Successful Sign Up
    Given I am on the home page
    When I navigate to the sign-up page
    And I sign up with a new user
    Then I should see the welcome page