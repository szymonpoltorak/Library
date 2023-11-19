package pl.edu.pw.ee.cinemabackend;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pw.ee.cinemabackend.api.auth.AuthServiceImpl;
import pl.edu.pw.ee.cinemabackend.api.auth.data.AuthResponse;
import pl.edu.pw.ee.cinemabackend.api.auth.data.RegisterRequest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserRegistrationSteps extends SpringIntegrationTest {

    private RegisterRequest registerRequest;
    private AuthResponse registerResponse;

    @Autowired
    private AuthServiceImpl authService;

    /*
        Rember about env:
        SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5000/cinema;POSTGRES_USER=postgres;POSTGRES_PASSWORD=postgres
        + db musi być włączone... co wsm ma sens bo przy rejestracji piszemy po db
        może trzeba ją mockować?...
     */

    @Given("^The user provides valid registration details$")
    public final void theUserProvidesValidRegistrationDetails() {
        System.err.printf("\ntheUserProvidesValidRegistrationDetails\n");
        registerRequest = RegisterRequest
                .builder()
                .username("username@mail.com")
                .name("John")
                .surname("Doe")
                .password("password")
                .build();
    }

    @When("^The user submits the registration form$")
    public final void theUserSubmitsTheRegistrationForm() {
        System.err.printf("\ntheUserSubmitsTheRegistrationForm\n");
        registerResponse = authService.register(registerRequest);
    }

    @Then("^The user should be successfully registered$")
    public final void theUserShouldBeSuccessfullyRegistered() {
        System.err.printf("\ntheUserShouldBeSuccessfullyRegistered\n");
        assertNotNull(registerResponse.authToken(), "Auth token is null");
        assertNotNull(registerResponse.refreshToken(), "Refresh token is null");
        assertFalse(registerResponse.authToken().isBlank(), "Auth token is blank");
        assertFalse(registerResponse.refreshToken().isBlank(), "Refresh token is blank");
    }

}