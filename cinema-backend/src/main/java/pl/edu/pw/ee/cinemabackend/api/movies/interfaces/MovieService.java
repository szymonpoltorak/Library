package pl.edu.pw.ee.cinemabackend.api.movies.interfaces;

import pl.edu.pw.ee.cinemabackend.api.movies.data.MovieResponse;

import java.util.List;

public interface MovieService {
    List<MovieResponse> getListOfMoviesOnPage(int numOfPage);

    MovieResponse getMovieDetails(long movieId);
}
