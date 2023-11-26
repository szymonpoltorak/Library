Feature: User can get a movie details

  Scenario: User can successfully get a movie details
    Given User want to check details of movie with good id
    When Movies are being downloaded from database
    Then User get movie details successfully

  Scenario: User cannot successfully get a movie details
    Given User want to check details of movie with bad id
    When Movies are being downloaded from database
    Then User get movie details unsuccessfully

  Scenario: User can successfully see list of movies
    Given User wants to see list of movies
    When First page of movies is fetched from the server
    Then The user gets page successfully

  Scenario: User sees empty list when there are no movies
    Given User wants to see list of movies when there are none
    When First page of movies is fetched from the server
    Then The user gets empty page successfully