Feature: User can login with email and password

  Scenario: User can login with email and password
    Given User has already been registered
    When Submits form with valid credentials
    Then User should already be logged in

  Scenario: User cannot login because he was not registered
    Given User has not been registered
    When Submits form with valid credentials
    Then User should not be logged in
