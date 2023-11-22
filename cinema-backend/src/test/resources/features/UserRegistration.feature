Feature: User can register to the system

  Scenario: Successful registration with a valid password
    Given The user provides password kicia.?312312312As and username kicia2@mail.com
    When The user submits the registration form
    Then The user should be successfully registered

  Scenario: Unsuccessful registration with a weak password
    Given The user provides password kicia and username kicia2@mail.com
    When The user submits the registration form
    Then The user should be unsuccessfully registered

  Scenario: Unsuccessful registration with an invalid username
    Given The user provides password kicia.?312312312As and username kici
    When The user submits the registration form
    Then The user should be unsuccessfully registered

  Scenario: Unsuccessful registration with a simple password
    Given The user provides password kicia123 and username kicia2@mail.com
    When The user submits the registration form
    Then The user should be unsuccessfully registered

  Scenario: Unsuccessful registration with a common password
    Given The user provides password Pa$$w0rd and username kicia2@mail.com
    When The user submits the registration form
    Then The user should be unsuccessfully registered

  Scenario: Unsuccessful registration with a password missing character types
    Given The user provides password kicia.?123 and username kicia2@mail.com
    When The user submits the registration form
    Then The user should be unsuccessfully registered

  Scenario: Unsuccessful registration with an uppercase-only password
    Given The user provides password KICIA123! and username kicia2@mail.com
    When The user submits the registration form
    Then The user should be unsuccessfully registered

  Scenario: Unsuccessful registration with a common password pattern
    Given The user provides password p@ssword and username kicia2@mail.com
    When The user submits the registration form
    Then The user should be unsuccessfully registered

  Scenario: Unsuccessful registration with a too-long password
    Given The user provides password 12345678901234567890 and username kicia2@mail.com
    When The user submits the registration form
    Then The user should be unsuccessfully registered

  Scenario: Unsuccessful registration with a password lacking special characters
    Given The user provides password 12345ABC! and username kicia2@mail.com
    When The user submits the registration form
    Then The user should be unsuccessfully registered
