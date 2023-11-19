Feature: the version can be retrieved
    Scenario: client makes call to GET version
        Given The user provides valid registration details
        When The user submits the registration form
        Then The user should be successfully registered
