package pl.edu.pw.ee.cinemabackend.api.screenings;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import pl.edu.pw.ee.cinemabackend.api.screenings.data.ScreeningRequest;
import pl.edu.pw.ee.cinemabackend.api.screenings.data.ScreeningResponse;
import pl.edu.pw.ee.cinemabackend.api.screenings.interfaces.ScreeningService;
import pl.edu.pw.ee.cinemabackend.entities.movie.Movie;
import pl.edu.pw.ee.cinemabackend.entities.movie.interfaces.MovieRepository;
import pl.edu.pw.ee.cinemabackend.entities.screening.Screening;
import pl.edu.pw.ee.cinemabackend.entities.screening.interfaces.ScreeningMapper;
import pl.edu.pw.ee.cinemabackend.entities.screening.interfaces.ScreeningRepository;
import pl.edu.pw.ee.cinemabackend.entities.user.User;
import pl.edu.pw.ee.cinemabackend.entities.user.UserRole;
import pl.edu.pw.ee.cinemabackend.exceptions.movies.MovieNotFoundException;
import pl.edu.pw.ee.cinemabackend.exceptions.screenings.ScreeningNotFoundException;

import java.time.LocalDate;
import java.util.List;

import static pl.edu.pw.ee.cinemabackend.api.screenings.costants.ValidationConstants.INVALID_DAY_OF_SCREENING_MESSAGE;
import static pl.edu.pw.ee.cinemabackend.api.screenings.costants.ValidationConstants.INVALID_HOUR_OF_SCREENING_MESSAGE;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScreeningServiceImpl implements ScreeningService {

    private final MovieRepository movieRepository;
    private final ScreeningRepository screeningRepository;
    private final ScreeningMapper screeningMapper;

    @Override
    public final List<ScreeningResponse> getScreeningsForGivenDay(LocalDate date) {
        log.info("Finding screenings for given day: {}", date);

        List<Screening> screenings = screeningRepository.getScreeningsForGivenDay(date);

        log.info("Returning screening responses of quantity: {}", screenings.size());

        return screenings
                .stream()
                .map(screeningMapper::mapToScreeningResponse)
                .toList();
    }

    @Override
    public List<ScreeningResponse> getScreeningsForMovie(long movieId) {
        //TODO POPRAWIĆ TO NA SPRING DATA JPA QUERY
        return screeningRepository.findAll().stream()
                .filter(s -> s.getMovie().getMovieId() == movieId)
                .map(screeningMapper::mapToScreeningResponse)
                .toList();
    }

    @Override
    public final ScreeningResponse getScreeningDetails(long screeningId) {
        log.info("Finding screening with id: {}", screeningId);

        Screening screening = screeningRepository.findById(screeningId)
                .orElseThrow(
                        () -> new ScreeningNotFoundException(
                                String.format("Screening with id: %d has not been found", screeningId),
                                HttpStatus.NOT_FOUND
                        )
                );
        log.info("Returning screening response of {}", screening);

        return screeningMapper.mapToScreeningResponse(screening);
    }

    @Override
    public final ScreeningResponse createScreening(ScreeningRequest screeningRequest, User user) {
        checkIfUserIsAnAdmin(user);
        validateScreeningRequest(screeningRequest);

        long movieId = screeningRequest.movieId();

        log.info("Finding movie with id: {}", movieId);

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(
                        ()-> new MovieNotFoundException(
                                String.format("Movie with id: %d not found", movieId),
                                HttpStatus.NOT_FOUND
                        )
                );

        log.info("Creating screening for request: {}", screeningRequest);

        Screening screening = Screening.builder()
                .movie(movie)
                .dayOfScreening(screeningRequest.dayOfScreening())
                .hourOfScreening(screeningRequest.hourOfScreening())
                .build();

        log.info("Saving screening: {}", screening);
        Screening savedScreening = screeningRepository.saveAndFlush(screening);

        log.info("Returning screening response of: {}", savedScreening);
        return screeningMapper.mapToScreeningResponse(savedScreening);
    }

    private void validateScreeningRequest(ScreeningRequest screeningRequest) {
        if (screeningRequest.dayOfScreening() == null) {
            throw new IllegalArgumentException(INVALID_DAY_OF_SCREENING_MESSAGE);
        }

        if(screeningRequest.hourOfScreening() == null) {
            throw new IllegalArgumentException(INVALID_HOUR_OF_SCREENING_MESSAGE);
        }
    }

    private void checkIfUserIsAnAdmin(User user) {
        if (user.getUserRole() == UserRole.USER) {
            throw new AccessDeniedException("Signed in user must be an admin");
        }
    }
}
