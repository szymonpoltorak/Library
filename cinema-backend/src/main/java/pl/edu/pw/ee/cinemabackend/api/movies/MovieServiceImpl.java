package pl.edu.pw.ee.cinemabackend.api.movies;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import pl.edu.pw.ee.cinemabackend.api.movies.data.MovieRequest;
import pl.edu.pw.ee.cinemabackend.api.movies.data.MovieResponse;
import pl.edu.pw.ee.cinemabackend.api.movies.interfaces.MovieService;
import pl.edu.pw.ee.cinemabackend.entities.movie.Movie;
import pl.edu.pw.ee.cinemabackend.entities.movie.interfaces.MovieMapper;
import pl.edu.pw.ee.cinemabackend.entities.movie.interfaces.MovieRepository;
import pl.edu.pw.ee.cinemabackend.entities.user.User;
import pl.edu.pw.ee.cinemabackend.entities.user.UserRole;
import pl.edu.pw.ee.cinemabackend.exceptions.movies.MovieNotFoundException;

import java.util.List;

import static pl.edu.pw.ee.cinemabackend.api.movies.constants.ValidationConstants.INVALID_DESCRIPTION_MESSAGE;
import static pl.edu.pw.ee.cinemabackend.api.movies.constants.ValidationConstants.INVALID_DURATION_MESSAGE;
import static pl.edu.pw.ee.cinemabackend.api.movies.constants.ValidationConstants.INVALID_MIN_AGE_MESSAGE;
import static pl.edu.pw.ee.cinemabackend.api.movies.constants.ValidationConstants.INVALID_TITLE_MESSAGE;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private static final int PAGE_SIZE = 20;
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Override
    public final List<MovieResponse> getListOfMoviesOnPage(int numOfPage) {
        Pageable pageable = PageRequest.of(numOfPage, PAGE_SIZE);

        log.info("Finding movies on page: {} with size: {}", numOfPage, PAGE_SIZE);

        Page<Movie> movies = movieRepository.findAllBy(pageable);

        log.info("Number of movies on page: {}", movies.getTotalElements());

        return movies
                .stream()
                .map(movieMapper::mapToMovieResponse)
                .toList();
    }

    @Override
    public final MovieResponse getMovieDetails(long movieId) {
        log.info("Finding movie with id: {}", movieId);

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(
                        () -> new MovieNotFoundException(
                                String.format("Movie with id: %d not found", movieId),
                                HttpStatus.NOT_FOUND
                        )
                );
        log.info("Found movie {}", movie);

        return movieMapper.mapToMovieResponse(movie);
    }

    @Override
    public final MovieResponse createMovie(MovieRequest movieRequest, User user) {
        checkIfUserIsAnAdmin(user);
        validateMovieRequest(movieRequest);
        log.info("Creating a movie: {}", movieRequest);

        Movie movie = Movie.builder()
                .title(movieRequest.title())
                .description(movieRequest.description())
                .minimalAge(movieRequest.minimalAge())
                .timeDuration(movieRequest.timeDuration())
                .build();

        movie = movieRepository.saveAndFlush(movie);
        return movieMapper.mapToMovieResponse(movie);
    }

    @Override
    public final MovieResponse  updateMovie(MovieRequest movieRequest,long movieId, User user) {
        checkIfUserIsAnAdmin(user);
        validateMovieRequest(movieRequest);
        log.info("Updating a movie: {}", movieRequest);

        Movie movie = Movie.builder()
                .movieId(movieId)
                .title(movieRequest.title())
                .description(movieRequest.description())
                .minimalAge(movieRequest.minimalAge())
                .timeDuration(movieRequest.timeDuration())
                .build();

        Movie movie1 = movieRepository.findById(movieId)
                .orElseThrow(
                        () -> new MovieNotFoundException(
                                String.format("Movie with id: %d not found", movieId),
                                HttpStatus.NOT_FOUND
                        )
                );

        movie1.setDescription(movieRequest.description());
        movie1.setTitle(movieRequest.title());
        movie1.setMinimalAge(movieRequest.minimalAge());
        movie1.setTimeDuration(movie.getTimeDuration());
        movie = movieRepository.saveAndFlush(movie);
        return movieMapper.mapToMovieResponse(movie);
    }


    private void validateMovieRequest(MovieRequest movieRequest) {
        if (movieRequest.title() == null || movieRequest.title().isBlank()) {
            throw new IllegalArgumentException(INVALID_TITLE_MESSAGE);
        }
        if (movieRequest.description() == null || movieRequest.description().isBlank()) {
            throw new IllegalArgumentException(INVALID_DESCRIPTION_MESSAGE);
        }
        if (movieRequest.timeDuration() == null || movieRequest.timeDuration().isBlank()) {
            throw new IllegalArgumentException(INVALID_DURATION_MESSAGE);
        }
        if (movieRequest.minimalAge() < 0) {
            throw new IllegalArgumentException(INVALID_MIN_AGE_MESSAGE);
        }
    }

    private void checkIfUserIsAnAdmin(User user) {
        if (user.getUserRole().equals(UserRole.USER)) {
            throw new AccessDeniedException("Signed in user must be an admin");
        }
    }
}

