# BDD should represent business requirements/acceptance criteria with examples. One scenario, One behavior.
# This is the reason why i chose to have two separate scenarios for the assignment's acceptance criteria.

Feature: BBC Sign In

  Scenario: Choose to Sign In to the BBC home page
    Given a web browser is at the BBC home page
    When the user clicks on the Sign In link
    Then the user is redirected to Sign In form

  Scenario: Sign in using only the Email/Username
    When the user enters "EgwEimai" username
    Then the user clicks on the Sign In button
    And the use sees the username error message
    And the use sees the password error message
