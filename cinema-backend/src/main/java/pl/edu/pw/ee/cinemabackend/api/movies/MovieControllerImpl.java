package pl.edu.pw.ee.cinemabackend.api.movies;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.ee.cinemabackend.api.movies.data.MovieRequest;
import pl.edu.pw.ee.cinemabackend.api.movies.data.MovieResponse;
import pl.edu.pw.ee.cinemabackend.api.movies.interfaces.MovieController;
import pl.edu.pw.ee.cinemabackend.api.movies.interfaces.MovieService;
import pl.edu.pw.ee.cinemabackend.entities.user.User;

import java.util.List;

import static pl.edu.pw.ee.cinemabackend.api.movies.constants.MovieMappings.API_MOVIES_MAPPING;
import static pl.edu.pw.ee.cinemabackend.api.movies.constants.MovieMappings.CREATE_MOVIE_MAPPING;
import static pl.edu.pw.ee.cinemabackend.api.movies.constants.MovieMappings.GET_MOVIES_ON_PAGE_MAPPING;
import static pl.edu.pw.ee.cinemabackend.api.movies.constants.MovieMappings.GET_MOVIE_DETAILS_MAPPING;

@RestController
@RequestMapping(API_MOVIES_MAPPING)
@RequiredArgsConstructor
public class MovieControllerImpl implements MovieController {
    private final MovieService movieService;

    @Override
    @GetMapping(value = GET_MOVIES_ON_PAGE_MAPPING)
    public final List<MovieResponse> getListOfMoviesOnPage(@RequestParam int numOfPage) {
        return movieService.getListOfMoviesOnPage(numOfPage);
    }

    @Override
    @GetMapping(value = GET_MOVIE_DETAILS_MAPPING)
    public final MovieResponse getMovieDetails(@RequestParam long movieId) {
        return movieService.getMovieDetails(movieId);
    }

    @Override
    @GetMapping(value = CREATE_MOVIE_MAPPING)
    public final MovieResponse createMovie(@RequestBody MovieRequest movieRequest, @AuthenticationPrincipal User user) {
        return movieService.createMovie(movieRequest, user);
    }
}
