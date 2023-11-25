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
import pl.edu.pw.ee.cinemabackend.entities.movie.Movie;
import pl.edu.pw.ee.cinemabackend.entities.movie.interfaces.MovieRepository;
import pl.edu.pw.ee.cinemabackend.entities.user.User;
import pl.edu.pw.ee.cinemabackend.entities.user.UserRole;
import pl.edu.pw.ee.cinemabackend.runners.SpringIntegrationTest;
import pl.edu.pw.ee.cinemabackend.utils.TestDataBuilder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UpdateMovieSteps extends SpringIntegrationTest {

    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieRepository movieRepository;
    private MovieResponse movieResponse;
    private MovieRequest movieRequest;
    private User user;
    private long id;


    @Given("^User is already signed in with role (.+)$")
    public final void userIsSignedIn(String role) {

        UserRole userRole = UserRole.valueOf(role);
        user = User.builder()
                .username("kicia2@mail.com")
                .name("John")
                .surname("Doe")
                .password("kicia.?312312312As")
                .userRole(userRole)
                .build();

        Movie movie = Movie.builder()
                .title("title")
                .description("description")
                .timeDuration("duration")
                .minimalAge(12)
                .build();
        movie = movieRepository.saveAndFlush(movie);
        id = movie.getMovieId();

        movieRequest = MovieRequest.builder()
                .title("title")
                .description("description")
                .timeDuration("15")
                .minimalAge(15)
                .build();

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Given("^User is signed in with role ADMIN, submits (.+) Id title (.+), description (.+), duration (.+) and minimal age (.+)$")
    public final void userIsSignedIn(String id, String title, String description, String duration, int age) {
        title = checkIfStringIsNull(title);
        description = checkIfStringIsNull(description);
        duration = checkIfStringIsNull(duration);
        user = User.builder()
                .username("kicia2@mail.com")
                .name("John")
                .surname("Doe")
                .password("kicia.?312312312As")
                .userRole(UserRole.ADMIN)
                .build();

        this.id = TestDataBuilder.addMovieToDataBase(id, movieRepository);

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

    @When("^Submits form with valid movie update request$")
    public final void submitsFormWithValidMovie() {
        try {
            movieResponse = movieService.updateMovie(movieRequest, id, user);
        } catch (AccessDeniedException | IllegalArgumentException e) {
            movieResponse = null;
        }
    }

    @Then("^Movie should be updated (.+)$")
    public final void movieShouldBeUpdated(String status) {
        if (status.equals("successfully")) {
            assertNotNull(movieResponse, "Movie response should not be null");
        } else {
            assertNull(movieResponse, "Movie response is not null");
        }
        movieRepository.deleteAll();
    }

    private String checkIfStringIsNull(String word) {
        if (word.equals("null")) {
            return null;
        }
        return word;
    }
}

