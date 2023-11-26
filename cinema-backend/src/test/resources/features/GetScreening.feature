Feature: User can get screenings and their details

  Scenario: Some movies will be screened today and user want to see them
    Given Some movies will be screened today
    When Movies that will be screened today are fetched
    Then User should see empty list unsuccessfully for given days screenings

  Scenario: Some movies will not be screened today and user want to see them anyway
    Given Some movies will be screened tomorrow
    When Movies that will be screened today are fetched
    Then User should see empty list successfully for given days screenings

  Scenario: User want to see screenings details
    Given Some movies will be screened today
    When User wants to see details for a indeed valid screening
    Then User should successfully see details for a screening

  Scenario: User want to see screenings details
    Given Some movies will be screened today
    When User wants to see details for a not valid screening
    Then User should unsuccessfully see details for a screening
