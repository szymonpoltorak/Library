package pl.edu.pw.ee.cinemabackend.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import pl.edu.pw.ee.cinemabackend.api.movies.data.MovieResponse;
import pl.edu.pw.ee.cinemabackend.api.movies.interfaces.MovieService;
import pl.edu.pw.ee.cinemabackend.exceptions.movies.MovieNotFoundException;
import pl.edu.pw.ee.cinemabackend.runners.SpringIntegrationTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GetMovieSteps extends SpringIntegrationTest {

    @Autowired
    private MovieService movieService;
    private MovieResponse movieResponse;

    private long _id;

    @Given("^User want to check details of movie with id (.+)$")
    public final void id_to_check(long id){

        _id = id;

    }

    @When("^downloading movie details from the database$")
    public final void ussed(){
        try {
            movieResponse = movieService.getMovieDetails(_id);
        } catch (MovieNotFoundException  e) {
            movieResponse = null;
        }
    }

    @Then("^User get movie details (.+)$")
    public final void przetestowane(String status){
        if (status.equals("successfully")) {
            assertNotNull(movieResponse, "Movie response should not be null");
        } else {
            assertNull(movieResponse, "Movie response is not null");
        }
    }

}
