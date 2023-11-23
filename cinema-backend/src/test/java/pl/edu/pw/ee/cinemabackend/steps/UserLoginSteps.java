package pl.edu.pw.ee.cinemabackend.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.edu.pw.ee.cinemabackend.api.auth.data.AuthResponse;
import pl.edu.pw.ee.cinemabackend.api.auth.data.LoginRequest;
import pl.edu.pw.ee.cinemabackend.api.auth.interfaces.AuthService;
import pl.edu.pw.ee.cinemabackend.entities.token.interfaces.TokenRepository;
import pl.edu.pw.ee.cinemabackend.entities.user.User;
import pl.edu.pw.ee.cinemabackend.entities.user.UserRole;
import pl.edu.pw.ee.cinemabackend.entities.user.interfaces.UserRepository;
import pl.edu.pw.ee.cinemabackend.runners.SpringIntegrationTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserLoginSteps extends SpringIntegrationTest {
    private static final String MAIL = "username@mail.com";
    private static final String PASSWORD = "password";
    private User user;

    private AuthResponse authResponse;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService;

    @Given("^User has (.+) been registered$")
    public final void userHasAlreadyBeenRegistered(String registered) {
        if (registered.equals("not")) {
            return;
        }
        user = User.builder()
                .username(MAIL)
                .name("Name")
                .surname("Surname")
                .password(passwordEncoder.encode(PASSWORD))
                .userRole(UserRole.USER)
                .build();
        userRepository.save(user);
    }

    @When("^Submits form with valid credentials$")
    public final void submitsFormWithValidCredentials() {
        try {
            LoginRequest loginRequest = LoginRequest
                    .builder()
                    .username(MAIL)
                    .password(PASSWORD)
                    .build();
            authResponse = authService.login(loginRequest);
        } catch (BadCredentialsException exception) {
            authResponse = null;
        }
    }

    @Then("^User should (.+) be logged in$")
    public final void userHasAlreadyBeRegistered(String loggedIn) {
        if (loggedIn.equals("not")) {
            assertNull(authResponse);
        } else {
            assertNotNull(authResponse.authToken(), "Auth token is null");
            assertNotNull(authResponse.refreshToken(), "Refresh token is null");
            assertFalse(authResponse.authToken().isBlank(), "Auth token is blank");
            assertFalse(authResponse.refreshToken().isBlank(), "Refresh token is blank");
        }
        tokenRepository.deleteAll();
        userRepository.deleteAll();
    }
}
