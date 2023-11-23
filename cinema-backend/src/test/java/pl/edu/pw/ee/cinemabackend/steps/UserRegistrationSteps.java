package pl.edu.pw.ee.cinemabackend.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionSystemException;
import pl.edu.pw.ee.cinemabackend.api.auth.AuthServiceImpl;
import pl.edu.pw.ee.cinemabackend.api.auth.data.AuthResponse;
import pl.edu.pw.ee.cinemabackend.api.auth.data.RegisterRequest;
import pl.edu.pw.ee.cinemabackend.entities.token.interfaces.TokenRepository;
import pl.edu.pw.ee.cinemabackend.entities.user.UserRole;
import pl.edu.pw.ee.cinemabackend.entities.user.interfaces.UserRepository;
import pl.edu.pw.ee.cinemabackend.runners.SpringIntegrationTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserRegistrationSteps extends SpringIntegrationTest {
    private RegisterRequest registerRequest;
    private AuthResponse registerResponse;

    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Given("^The user provides password (.+) and username (.+)$")
    public final void theUserProvidesValidRegistrationDetails(String password, String username) {
        registerRequest = RegisterRequest
                .builder()
                .username(username)
                .name("John")
                .surname("Doe")
                .password(password)
                .userRole(UserRole.USER)
                .build();
    }

    @When("^The user submits the registration form$")
    public final void theUserSubmitsTheRegistrationForm() {
        try {
            registerResponse = authService.register(registerRequest);
        } catch (ConstraintViolationException | TransactionSystemException exception) {
            registerResponse = null;
        }
    }

    @Then("^The user should be (.+) registered$")
    public final void theUserShouldBeSuccessfullyRegistered(String status) {
        if (status.equals("successfully")) {
            assertSuccessfulRegister();
        } else {
            assertNull(registerResponse, "Register response is not null");
        }
        tokenRepository.deleteAll();
        userRepository.deleteAll();
    }

    private void assertSuccessfulRegister() {
        assertNotNull(registerResponse.authToken(), "Auth token is null");
        assertNotNull(registerResponse.refreshToken(), "Refresh token is null");
        assertFalse(registerResponse.authToken().isBlank(), "Auth token is blank");
        assertFalse(registerResponse.refreshToken().isBlank(), "Refresh token is blank");
    }
}