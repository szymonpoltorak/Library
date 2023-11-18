package pl.edu.pw.ee.cinemabackend.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pw.ee.cinemabackend.api.auth.data.AuthResponse;
import pl.edu.pw.ee.cinemabackend.api.auth.data.RegisterRequest;
import pl.edu.pw.ee.cinemabackend.api.auth.interfaces.AuthService;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserRegistrationSteps {
//    @Autowired
//    private AuthService authService;
    private RegisterRequest registerRequest;
    private AuthResponse registerResponse;

    @Given("The user provides valid registration details")
    public final void theUserProvidesValidRegistrationDetails() {
        registerRequest = RegisterRequest
                .builder()
                .username("username@mail.com")
                .name("John")
                .surname("Doe")
                .password("password")
                .build();
    }

    @When("The user submits the registration form")
    public final void theUserSubmitsTheRegistrationForm() {
//        registerResponse = authService.register(registerRequest);
    }

    @Then("The user should be successfully registered.")
    public final void theUserShouldBeSuccessfullyRegistered() {
        assertNotNull(registerResponse.authToken(), "Auth token is null");
        assertNotNull(registerResponse.refreshToken(), "Refresh token is null");
        assertFalse(registerResponse.authToken().isBlank(), "Auth token is blank");
        assertFalse(registerResponse.refreshToken().isBlank(), "Refresh token is blank");
        assertNotNull(null);
    }
}
