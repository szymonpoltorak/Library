package pl.edu.pw.ee.cinemabackend.entities.movie.interfaces;

import org.mapstruct.Mapper;
import pl.edu.pw.ee.cinemabackend.api.movies.data.MovieResponse;
import pl.edu.pw.ee.cinemabackend.entities.movie.Movie;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieResponse mapToMovieResponse(Movie movie);
}
