Feature: User can get a movie details

  Scenario: User can successfully get a movie details
    Given User want to check details of movie with id 1
    When downloading movie details from the database
    Then User get movie details successfully

  Scenario: User cannot successfully get a movie details
    Given User want to check details of movie with id -2
    When downloading movie details from the database
    Then User get movie details unsuccessfully