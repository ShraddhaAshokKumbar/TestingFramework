@AdminLogin
Feature: Admin Authentication

  Background: Given user launches the application

  Scenario Outline: Successful login with valid admin credentials
    Given the user is on the login page
    When the user enters the admin email <email>
    And the user enters the admin password <password>
    And the user clicks on the sign-in button
    Then the user should be redirected to the admin dashboard page

    Examples:
      | email               | password       |
      | admin@bloodbank.com | Admin@123      |
      | admin@bloodbank.com | wrong@password |
