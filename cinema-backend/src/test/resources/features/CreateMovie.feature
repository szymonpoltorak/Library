Feature: Admin can create a movie

    Scenario: Admin can successfully create a movie
        Given User is already logged in with role ADMIN and created a valid movie request
        When Submits form with valid movie request
        Then Movie should be created successfully

    Scenario: User cannot successfully create a movie because he is not an admin
        Given User is already logged in with role USER and created a valid movie request
        When Submits form with valid movie request
        Then Movie should be created unsuccessfully

    Scenario: User creates a movie with valid request
        Given User is logged in with role ADMIN and submits title Title, description Description, duration hour and minimal age 5
        When Submits form with valid movie request
        Then Movie should be created successfully

    Scenario: User creates a movie with invalid request, invalid title
        Given User is logged in with role ADMIN and submits title null, description Description, duration hour and minimal age 5
        When Submits form with valid movie request
        Then Movie should be created unsuccessfully

    Scenario: User creates a movie with invalid request, invalid description
        Given User is logged in with role ADMIN and submits title Title, description null, duration hour and minimal age 5
        When Submits form with valid movie request
        Then Movie should be created unsuccessfully

    Scenario: User creates a movie with invalid request, invalid duration
        Given User is logged in with role ADMIN and submits title Title, description Description, duration null and minimal age 5
        When Submits form with valid movie request
        Then Movie should be created unsuccessfully

    Scenario: User creates a movie with invalid request, invalid age
        Given User is logged in with role ADMIN and submits title Title, description Description, duration null and minimal age -5
        When Submits form with valid movie request
        Then Movie should be created unsuccessfully

    Scenario: User creates a movie with invalid age
        Given User is logged in with role ADMIN and submits title Title, description Description, duration hour and minimal age -5
        When Submits form with valid movie request
        Then Movie should be created unsuccessfully