package pl.edu.pw.ee.cinemabackend.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pw.ee.cinemabackend.api.movies.data.MovieRequest;
import pl.edu.pw.ee.cinemabackend.api.movies.data.MovieResponse;
import pl.edu.pw.ee.cinemabackend.api.movies.interfaces.MovieService;
import pl.edu.pw.ee.cinemabackend.entities.movie.Movie;
import pl.edu.pw.ee.cinemabackend.entities.movie.interfaces.MovieRepository;
import pl.edu.pw.ee.cinemabackend.entities.user.User;
import pl.edu.pw.ee.cinemabackend.entities.user.UserRole;
import pl.edu.pw.ee.cinemabackend.exceptions.movies.MovieNotFoundException;
import pl.edu.pw.ee.cinemabackend.runners.SpringIntegrationTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GetMovieSteps extends SpringIntegrationTest {

    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieRepository movieRepository;
    private MovieResponse movieResponse;
    private MovieRequest movieRequest;
    private User user;
    private long Id;

    @Given("^User want to check details of movie with (.+) id$")
    public final void theUserProvidesId(String id){

        if(id.equals("good") ) {

            Movie movie = Movie.builder()
                    .title("title")
                    .description("description")
                    .timeDuration("duration")
                    .minimalAge(12)
                    .build();
            movie = movieRepository.saveAndFlush(movie);
            Id = movie.getMovieId();
        } else {
            Id = -2;
        }

    }

    @When("^downloading movie details from the database$")
    public final void submitsFormWithValidId(){
        try {
            movieResponse = movieService.getMovieDetails(Id);
        } catch (MovieNotFoundException  e) {
            movieResponse = null;
        }
    }

    @Then("^User get movie details (.+)$")
    public final void theUserShouldGetMovieDetailsSuccessfully(String status){
        if (status.equals("successfully")) {
            assertNotNull(movieResponse, "Movie response should not be null");
        } else {
            assertNull(movieResponse, "Movie response is not null");
        }
        movieRepository.deleteAll();
    }

}
