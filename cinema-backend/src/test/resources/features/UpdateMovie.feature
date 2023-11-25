Feature: Admin can update a movie

  Scenario: Admin can successfully update a movie
    Given User is logged in with role ADMIN
    When Submits form with valid movie update request
    Then Movie should be updated successfully

  Scenario: User cannot successfully create a movie because he is not an admin
    Given User is logged in with role USER
    When Submits form with valid movie update request
    Then Movie should be updated unsuccessfully

  Scenario: User creates a movie with valid request
    Given User is signed in with role ADMIN, submits good Id title Title, description Description, duration hour and minimal age 5
    When Submits form with valid movie update request
    Then Movie should be updated successfully

  Scenario: User creates a movie with invalid request, invalid title
    Given User is signed in with role ADMIN, submits good Id title null, description Description, duration hour and minimal age 5
    When Submits form with valid movie update request
    Then Movie should be updated unsuccessfully

  Scenario: User creates a movie with invalid request, invalid description
    Given User is signed in with role ADMIN, submits good Id title Title, description null, duration hour and minimal age 5
    When Submits form with valid movie update request
    Then Movie should be updated unsuccessfully

  Scenario: User creates a movie with invalid request, invalid duration
    Given User is signed in with role ADMIN, submits good Id title Title, description Description, duration null and minimal age 5
    When Submits form with valid movie update request
    Then Movie should be updated unsuccessfully

  Scenario: User creates a movie with invalid request, invalid age
    Given User is signed in with role ADMIN, submits good Id title Title, description Description, duration null and minimal age -5
    When Submits form with valid movie update request
    Then Movie should be updated unsuccessfully