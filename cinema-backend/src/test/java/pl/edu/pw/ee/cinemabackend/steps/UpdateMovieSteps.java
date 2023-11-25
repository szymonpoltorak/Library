package pl.edu.pw.ee.cinemabackend.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.edu.pw.ee.cinemabackend.api.movies.data.MovieRequest;
import pl.edu.pw.ee.cinemabackend.api.movies.data.MovieResponse;
import pl.edu.pw.ee.cinemabackend.api.movies.interfaces.MovieService;
import pl.edu.pw.ee.cinemabackend.entities.user.User;
import pl.edu.pw.ee.cinemabackend.entities.user.UserRole;
import pl.edu.pw.ee.cinemabackend.runners.SpringIntegrationTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UpdateMovieSteps extends SpringIntegrationTest {

    @Autowired
    private MovieService movieService;
    private MovieResponse movieResponse;
    private MovieRequest movieRequest;
    private User user;

    private long _id;

    @Given("^User is already logged in with role (.+)$")
    public final void userIsLoggedIn1( String role) {

        UserRole userRole = UserRole.valueOf(role);
        user = User.builder()
                .username("kicia2@mail.com")
                .name("John")
                .surname("Doe")
                .password("kicia.?312312312As")
                .userRole(userRole)
                .build();

        movieRequest = MovieRequest.builder()
                .title("title")
                .description("description")
                .timeDuration("15")
                .minimalAge(15)
                .build();

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Given("^User is logged in with role ADMIN and submits Id (.+) title (.+), description (.+), duration (.+) and minimal age (.+)$")
    public final void userIsLoggedIn1(long id, String title, String description, String duration, int age) {
        title = checkIfStringIsNull1(title);
        description = checkIfStringIsNull1(description);
        duration = checkIfStringIsNull1(duration);
        user = User.builder()
                .username("kicia2@mail.com")
                .name("John")
                .surname("Doe")
                .password("kicia.?312312312As")
                .userRole(UserRole.ADMIN)
                .build();

        _id = id;

        movieRequest = MovieRequest.builder()
                .title(title)
                .description(description)
                .timeDuration(duration)
                .minimalAge(age)
                .build();

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);
    }

    @When("^Submits form with valid movie request$")
    public final void submitsFormWithValidMovie1() {
        try {
            movieResponse = movieService.updateMovie(movieRequest, _id, user);
        } catch (AccessDeniedException | IllegalArgumentException e) {
            movieResponse = null;
        }
    }

    @Then("^Movie should be updated (.+)$")
    public final void movieShouldBeCreated1(String status) {
        if (status.equals("successfully")) {
            assertNotNull(movieResponse, "Movie response should not be null");
        } else {
            assertNull(movieResponse, "Movie response is not null");
        }
    }

    private String checkIfStringIsNull1(String word) {
        if (word.equals("null")) {
            return null;
        }
        return word;
    }
}

