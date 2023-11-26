package pl.edu.pw.ee.cinemabackend.steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.edu.pw.ee.cinemabackend.api.screenings.data.ScreeningRequest;
import pl.edu.pw.ee.cinemabackend.api.screenings.data.ScreeningResponse;
import pl.edu.pw.ee.cinemabackend.api.screenings.interfaces.ScreeningController;
import pl.edu.pw.ee.cinemabackend.entities.movie.Movie;
import pl.edu.pw.ee.cinemabackend.entities.movie.interfaces.MovieRepository;
import pl.edu.pw.ee.cinemabackend.entities.user.User;
import pl.edu.pw.ee.cinemabackend.entities.user.UserRole;
import pl.edu.pw.ee.cinemabackend.exceptions.movies.MovieNotFoundException;
import pl.edu.pw.ee.cinemabackend.runners.SpringIntegrationTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static pl.edu.pw.ee.cinemabackend.utils.ScreeningUtil.checkIfValidDate;
import static pl.edu.pw.ee.cinemabackend.utils.ScreeningUtil.checkIfValidTime;
import static pl.edu.pw.ee.cinemabackend.utils.ScreeningUtil.createAdminUser;

public class CreateScreeningSteps extends SpringIntegrationTest {

    @Autowired
    private ScreeningController screeningController;

    @Autowired
    private MovieRepository movieRepository;

    private ScreeningResponse screeningResponse;
    private ScreeningRequest screeningRequest;
    private User user;

    @Before("@createScreening")
    public final void setupMovie() {
        if(movieRepository.findById(1L).isEmpty()) {
            Movie movie = Movie.builder()
                    .movieId(1L)
                    .title("title")
                    .description("description")
                    .timeDuration("15")
                    .minimalAge(15)
                    .build();
            movieRepository.save(movie);
        }
    }

    @Given("^User is (.+) logged in with role (.+) and created a valid screening request$")
    public final void userIsLoggedIn(String logged, String role) {
        if (logged.equals("not")) {
            SecurityContextHolder.getContext().setAuthentication(null);
            return;
        }
        UserRole userRole = UserRole.valueOf(role);
        user = User.builder()
                .username("kicia2@mail.com")
                .name("John")
                .surname("Doe")
                .password("kicia.?312312312As")
                .userRole(userRole)
                .build();

        screeningRequest = ScreeningRequest.builder()
                .movieId(1L)
                .dayOfScreening(LocalDate.now())
                .hourOfScreening(LocalTime.now())
                .build();

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Given("^User is logged in with role ADMIN and creates a screening request with (.+) movieId, (.+)" +
            " day of screening and (.+) hour of screening")
    public final void userIsLoggedInMovieId(String correct, String stringDayOfScreening, String stringHourOfScreening) {
        long id;
        id = correct.equals("incorrect") ? -1L : 1L;

        LocalDate dayOfScreening = checkIfValidDate(stringDayOfScreening);
        LocalTime hourOfScreening = checkIfValidTime(stringHourOfScreening);

        user = createAdminUser();

        screeningRequest = ScreeningRequest.builder()
                .movieId(id)
                .dayOfScreening(dayOfScreening)
                .hourOfScreening(hourOfScreening)
                .build();

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);
    }



    @When("Submits form with screening request")
    public void submitsFormWithRequest() {
        try {
            screeningResponse = screeningController.createScreening(screeningRequest, user);
        } catch (AccessDeniedException | IllegalArgumentException | MovieNotFoundException e) {
            screeningResponse = null;
        }
    }

    @Then("^Screening should be created (.+)$")
    public final void screeningShouldBeCreated(String status) {
        if (status.equals("successfully")) {
            assertNotNull(screeningResponse, "Screening response should not be null");
        } else {
            assertNull(screeningResponse, "Screening response should be null");
        }
    }

}


