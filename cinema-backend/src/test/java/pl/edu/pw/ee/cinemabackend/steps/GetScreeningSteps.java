package pl.edu.pw.ee.cinemabackend.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pw.ee.cinemabackend.api.movies.data.MovieResponse;
import pl.edu.pw.ee.cinemabackend.api.screenings.data.ScreeningResponse;
import pl.edu.pw.ee.cinemabackend.api.screenings.interfaces.ScreeningController;
import pl.edu.pw.ee.cinemabackend.entities.movie.interfaces.MovieRepository;
import pl.edu.pw.ee.cinemabackend.entities.screening.Screening;
import pl.edu.pw.ee.cinemabackend.entities.screening.interfaces.ScreeningRepository;
import pl.edu.pw.ee.cinemabackend.exceptions.screenings.ScreeningNotFoundException;
import pl.edu.pw.ee.cinemabackend.runners.SpringIntegrationTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static pl.edu.pw.ee.cinemabackend.utils.ScreeningUtil.relativeDayToLocalDate;

public class GetScreeningSteps extends SpringIntegrationTest {

    @Autowired
    private ScreeningController screeningController;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ScreeningRepository screeningRepository;

    private long validScreeningId;
    private ScreeningResponse screeningDetailsResponse;
    private List<ScreeningResponse> screeningsForGivenDay;

    private MovieResponse movieResponse;

    private List<Long> movieIds;
    private List<MovieResponse> pageOfMovies;

    @Given("^Some movies will be screened (.+)$")
    public final void someMoviesWillBeScreened(String relativeDay) {
        LocalDate date = relativeDayToLocalDate(relativeDay);
        Screening screening = Screening.builder()
                .dayOfScreening(date)
                .hourOfScreening(LocalTime.of(12, 45))
                .build();
        Screening saved = screeningRepository.save(screening);
        validScreeningId = saved.getScreeningId();
    }

    @When("^Movies that will be screened (.+) are fetched$")
    public final void fetchMoviesScreenedAtGivenDay(String relativeDay) {
        LocalDate date = relativeDayToLocalDate(relativeDay);
        screeningsForGivenDay = screeningController.getScreeningsForGivenDay(date);
    }

    @Then("^User should see empty list (.+) for given days screenings$")
    public final void userShouldSeeEmptyList(String status) {
        if (status.equals("successfully")) {
            assertEquals(
                    Collections.emptyList(), screeningsForGivenDay,
                    "List should be empty"
            );
        } else {
            assertNotEquals(
                    Collections.emptyList(), screeningsForGivenDay,
                    "List should not be empty"
            );
        }
        screeningRepository.deleteAll();
    }

    @When("^User wants to see details for a (.+) valid screening$")
    public final void userWantToSeeScreeningDetails(String status) {
        long id = validScreeningId;
        if("not".equals(status)) {
            long invalidId = id = 32456765;
        }
        try {
            screeningDetailsResponse = screeningController.getScreeningDetails(id);
        } catch (ScreeningNotFoundException e) {
            screeningDetailsResponse = null;
        }
    }

    @Then("^User should (.+) see details for a screening$")
    public final void userSeesScreeningDetails(String status) {
        if (status.equals("successfully")) {
            assertNotNull(screeningDetailsResponse, "Screening Details should NOT be null");
        } else {
            assertNull(screeningDetailsResponse, "Screening Details should be null");
        }
        screeningRepository.deleteAll();
    }

}
