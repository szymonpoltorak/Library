package pl.edu.pw.ee.cinemabackend.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pw.ee.cinemabackend.api.movies.data.MovieResponse;
import pl.edu.pw.ee.cinemabackend.api.movies.interfaces.MovieService;
import pl.edu.pw.ee.cinemabackend.entities.movie.Movie;
import pl.edu.pw.ee.cinemabackend.entities.movie.interfaces.MovieRepository;
import pl.edu.pw.ee.cinemabackend.exceptions.movies.MovieNotFoundException;
import pl.edu.pw.ee.cinemabackend.runners.SpringIntegrationTest;
import pl.edu.pw.ee.cinemabackend.utils.TestDataBuilder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GetMovieSteps extends SpringIntegrationTest {

    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieRepository movieRepository;
    private MovieResponse movieResponse;
    private long id;

    @Given("^User want to check details of movie with (.+) id$")
    public final void theUserProvidesId(String id) {

        this.id = TestDataBuilder.addMovieToDataBase(id , movieRepository);

    }

    @When("^Movies are being downloaded from database$")
    public final void submitsFormWithValidId() {
        try {
            movieResponse = movieService.getMovieDetails(id);
        } catch (MovieNotFoundException e) {
            movieResponse = null;
        }
    }

    @Then("^User get movie details (.+)$")
    public final void theUserShouldGetMovieDetailsSuccessfully(String status) {
        if (status.equals("successfully")) {
            assertNotNull(movieResponse, "Movie response should not be null");
        } else {
            assertNull(movieResponse, "Movie response is not null");
        }
        movieRepository.deleteAll();
    }

}
