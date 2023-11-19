Feature: the version can be retrieved
    Scenario: client makes call to GET version
        Given The user provides password kicia.?312312312As and username kicia2@mail.com
        When The user submits the registration form
        Then The user should be successfully registered

    Scenario: client makes call to GET version
        Given The user provides password kicia and username kicia2@mail.com
        When The user submits the registration form
        Then The user should be unsuccessfully registered


    Scenario: client makes call to GET version
        Given The user provides password kicia.?312312312As and username kici
        When The user submits the registration form
        Then The user should be unsuccessfully registered
