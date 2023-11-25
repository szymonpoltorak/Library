Feature: Admin can update a movie

  Scenario: Admin can successfully update a movie
    Given User is already logged in with role ADMIN
    When Submits form with valid movie request
    Then Movie should be updated successfully

  Scenario: User cannot successfully create a movie because he is not an admin
    Given User is already logged in with role USER
    When Submits form with valid movie request
    Then Movie should be updated unsuccessfully

  Scenario: User creates a movie with valid request
    Given User is logged in with role ADMIN and submits Id 1 title Title, description Description, duration hour and minimal age 5
    When Submits form with valid movie request
    Then Movie should be updated successfully

  Scenario: User creates a movie with invalid request, invalid title
    Given User is logged in with role ADMIN and submits Id 1 title null, description Description, duration hour and minimal age 5
    When Submits form with valid movie request
    Then Movie should be updated unsuccessfully

  Scenario: User creates a movie with invalid request, invalid description
    Given User is logged in with role ADMIN and submits Id 1 title Title, description null, duration hour and minimal age 5
    When Submits form with valid movie request
    Then Movie should be updated unsuccessfully

  Scenario: User creates a movie with invalid request, invalid duration
    Given User is logged in with role ADMIN and submits Id 1 title Title, description Description, duration null and minimal age 5
    When Submits form with valid movie request
    Then Movie should be updated unsuccessfully

  Scenario: User creates a movie with invalid request, invalid age
    Given User is logged in with role ADMIN and submits Id 1 title Title, description Description, duration null and minimal age -5
    When Submits form with valid movie request
    Then Movie should be updated unsuccessfully