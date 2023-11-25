package pl.edu.pw.ee.cinemabackend.utils;

import pl.edu.pw.ee.cinemabackend.entities.movie.Movie;
import pl.edu.pw.ee.cinemabackend.entities.movie.interfaces.MovieRepository;

public final class TestDataBuilder {

    private TestDataBuilder(){

    }

    public static long addMovieToDataBase(String id, MovieRepository movieRepository){
        if (id.equals("good")) {

            Movie movie = Movie.builder()
                    .title("title")
                    .description("description")
                    .timeDuration("duration")
                    .minimalAge(12)
                    .build();
            movie = movieRepository.saveAndFlush(movie);
            return  movie.getMovieId();
        }
         return -2L;

    }
}
