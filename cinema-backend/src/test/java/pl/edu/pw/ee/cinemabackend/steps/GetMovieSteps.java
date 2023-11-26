package pl.edu.pw.ee.cinemabackend.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pw.ee.cinemabackend.api.movies.data.MovieResponse;
import pl.edu.pw.ee.cinemabackend.api.movies.interfaces.MovieController;
import pl.edu.pw.ee.cinemabackend.entities.movie.interfaces.MovieRepository;
import pl.edu.pw.ee.cinemabackend.entities.screening.interfaces.ScreeningRepository;
import pl.edu.pw.ee.cinemabackend.exceptions.movies.MovieNotFoundException;
import pl.edu.pw.ee.cinemabackend.runners.SpringIntegrationTest;
import pl.edu.pw.ee.cinemabackend.utils.TestDataBuilder;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GetMovieSteps extends SpringIntegrationTest {

    @Autowired
    private MovieController movieController;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ScreeningRepository screeningRepository;

    private long id;
    private MovieResponse movieResponse;

    private List<Long> movieIds;
    private List<MovieResponse> pageOfMovies;

    @Given("^User want to check details of movie with (.+) id$")
    public final void theUserProvidesId(String id) {

        this.id = TestDataBuilder.addMovieToDataBase(id , movieRepository);

    }

    @When("^Movies are being downloaded from database$")
    public final void submitsFormWithValidId() {
        try {
            movieResponse = movieController.getMovieDetails(id);
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
        screeningRepository.deleteAll();
        movieRepository.deleteAll();
    }

    @Given("^User wants to see list of movies$")
    public final void theUserWantsToSeeListOfMovies() {
        movieIds = List.of(
                TestDataBuilder.addMovieToDataBase("good" , movieRepository),
                TestDataBuilder.addMovieToDataBase("good" , movieRepository),
                TestDataBuilder.addMovieToDataBase("good" , movieRepository)
        );
    }

    @Given("^User wants to see list of movies when there are none$")
    public final void theUserWantsToSeeListOfMoviesWhenThereAreNone() {
        movieIds = Collections.emptyList();
    }

    @When("^First page of movies is fetched from the server$")
    public final void fetchesFirstPage() {
        pageOfMovies = Collections.emptyList();
        try {
            pageOfMovies = movieController.getListOfMoviesOnPage(0);
        } catch (MovieNotFoundException e) {
        }
    }

    @Then("^The user gets page successfully$")
    public final void theUsesSuccessfullySeesAllMoviesOnThatPage() {
        assertEquals(
                movieIds,
                pageOfMovies.stream().map(MovieResponse::movieId).toList(),
                "Movie lists should be equal"
        );
        screeningRepository.deleteAll();
        movieRepository.deleteAll();
    }

    @Then("^The user gets empty page successfully$")
    public final void theUsesSeesEmptyList() {
        assertEquals(
                Collections.emptyList(),
                pageOfMovies.stream().map(MovieResponse::movieId).toList(),
                "Movie list should be empty"
        );
        screeningRepository.deleteAll();
        movieRepository.deleteAll();
    }

}
