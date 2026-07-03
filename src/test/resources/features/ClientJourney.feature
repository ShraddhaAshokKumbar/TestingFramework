Feature: Client End-to-End Journeys
  As a registered client
  I want to browse policies, request them, and manage my requests
  So that I can use the TrueClaim insurance portal effectively

  Background:
    Given the user is on the login page
    When the user logs in with valid client credentials
    Then the user should be redirected to the dashboard

  Scenario: Request a Home Insurance Policy
    When the user navigates to the policies list
    And the user clicks request on a policy
    Then the policy request status should show as "PENDING"

  Scenario: Cancel a Pending Request
    When the user navigates to My Requests
    And the user clicks cancel on a pending request
    Then the request should be removed from the list

  Scenario: Session Timeout Handling
    When the user clears their browser session tokens
    And the user attempts to navigate to the dashboard
    Then the user should be redirected to the login page


  Scenario: Update Client Profile Details
    When the user navigates to the Profile page
    And the user attempts to update their date of birth to "05-05-1995"
    And the user clicks the save changes button
    Then a success message should appear confirming the profile update