Feature: Admin can create a movie
    Scenario: Admin can successfully create a movie
         Given User is already logged in with role ADMIN
         When Submits form with valid movie request
         Then Movie should be created successfully

    Scenario: User cannot successfully create a movie because he is not an admin
         Given User is already logged in with role USER
         When Submits form with valid movie request
         Then Movie should be created unsuccessfully

    Scenario: User cannot successfully create a movie because he is not logged in
         Given User is not logged in with role USER
         When Submits form with valid movie request
         Then Movie should be created unsuccessfully