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


public class CreateMovieSteps extends SpringIntegrationTest {
    @Autowired
    private MovieService movieService;
    private MovieResponse movieResponse;

    @Given("^User is (.+) logged in with role (.+)$")
    public final void userIsLoggedIn(String logged, String role){
        if (logged.equals("not")){
            SecurityContextHolder.getContext().setAuthentication(null);
            return;
        }
        UserRole userRole = UserRole.valueOf(role);
        User user = User.builder()
                .username("kicia2@mail.com")
                .name("John")
                .surname("Doe")
                .password("kicia.?312312312As")
                .userRole(userRole)
                .build();
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @When("^Submits form with valid movie request$")
    public final void submitsFormWithValidMovie(){
        MovieRequest movieRequest = MovieRequest.builder()
                .title("title")
                .description("description")
                .timeDuration("15")
                .minimalAge(15)
                .build();

        try {
            movieResponse= movieService.createMovie(movieRequest);
        }catch (AccessDeniedException e){
            movieResponse = null;
        }
    }
    @Then("^Movie should be created (.+)$")
    public final void movieShouldBeCreated(String status){
        if (status.equals("successfully")){
            assertNotNull(movieResponse, "Movie response should not be null");
        }else{
            assertNull(movieResponse, "Movie response is not null");
        }

    }
}
