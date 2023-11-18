Feature: User can register to the system

    Scenario: Registering user to system
        Given The user provides valid registration details
        When The user submits the registration form
        Then The user should be successfully registered.
