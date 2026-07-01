Feature: feature to test login functionality
  Scenario Outline: check login is successful with valid credentials
    Given user is on login page
    When user enters "<username>" and "<password>"
    And clicks on sign in button
    Then user is navigated to dashboard page

    Examples:
      | username | password |
      | testcase24@gmail.com | Testcase@123 |
      | testcase21@gmail.com | Testcase@123 |