@createScreening
Feature: Admin can create a screening

  Scenario: Admin can successfully create a screening
    Given User is already logged in with role ADMIN and created a valid screening request
    When Submits form with screening request
    Then Screening should be created successfully

  Scenario: User cannot successfully create a screening because he is not an admin
    Given User is already logged in with role USER and created a valid screening request
    When Submits form with screening request
    Then Screening should be created unsuccessfully
    
  Scenario: Admin cannot create a screening with invalid movie id
    Given User is logged in with role ADMIN and creates a screening request with incorrect movieId, 2023-12-01 day of screening and 14:30 hour of screening
    When Submits form with screening request
    Then Screening should be created unsuccessfully

  Scenario: Admin cannot create a screening with invalid day of screening
    Given User is logged in with role ADMIN and creates a screening request with correct movieId, null day of screening and 14:30 hour of screening
    When Submits form with screening request
    Then Screening should be created unsuccessfully

  Scenario: Admin cannot create a screening with invalid hour of screening
    Given User is logged in with role ADMIN and creates a screening request with correct movieId, 2023-12-01 day of screening and null hour of screening
    When Submits form with screening request
    Then Screening should be created unsuccessfully

  Scenario: Admin can create a screening with valid screening request
    Given User is logged in with role ADMIN and creates a screening request with correct movieId, 2023-12-01 day of screening and 14:30 hour of screening
    When Submits form with screening request
    Then Screening should be created successfully