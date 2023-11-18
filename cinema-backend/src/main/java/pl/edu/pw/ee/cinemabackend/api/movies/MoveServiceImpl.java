package pl.edu.pw.ee.cinemabackend.api.movies;

import org.springframework.stereotype.Service;
import pl.edu.pw.ee.cinemabackend.api.movies.data.MovieResponse;
import pl.edu.pw.ee.cinemabackend.api.movies.interfaces.MovieService;

import java.util.List;

@Service
public class MoveServiceImpl implements MovieService {
    @Override
    public final List<MovieResponse> getListOfMoviesOnPage(int numOfPage) {
        return null;
    }

    @Override
    public final MovieResponse getMovieDetails(long movieId) {
        return null;
    }
}
