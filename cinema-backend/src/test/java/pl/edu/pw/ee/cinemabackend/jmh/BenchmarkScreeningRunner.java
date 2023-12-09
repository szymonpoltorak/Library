package pl.edu.pw.ee.cinemabackend.jmh;

import org.openjdk.jmh.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.pw.ee.cinemabackend.api.movies.data.MovieRequest;
import pl.edu.pw.ee.cinemabackend.api.movies.interfaces.MovieService;
import pl.edu.pw.ee.cinemabackend.api.screenings.data.ScreeningRequest;
import pl.edu.pw.ee.cinemabackend.api.screenings.interfaces.ScreeningService;
import pl.edu.pw.ee.cinemabackend.entities.movie.Movie;
import pl.edu.pw.ee.cinemabackend.entities.movie.interfaces.MovieRepository;
import pl.edu.pw.ee.cinemabackend.entities.screening.Screening;
import pl.edu.pw.ee.cinemabackend.entities.screening.interfaces.ScreeningRepository;
import pl.edu.pw.ee.cinemabackend.entities.user.User;
import pl.edu.pw.ee.cinemabackend.entities.user.UserRole;
import pl.edu.pw.ee.cinemabackend.utils.TestDataBuilder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class BenchmarkScreeningRunner extends AbstractBenchmark{
    private static ScreeningService screeningService;
    private static ScreeningRepository screeningRepository;
    private static MovieRepository movieRepository;

    @Autowired
    void setContext(ScreeningRepository screeningRepository,ScreeningService screeningService,MovieRepository movieRepository) {
        BenchmarkScreeningRunner.screeningService = screeningService;
        BenchmarkScreeningRunner.screeningRepository = screeningRepository;
        BenchmarkScreeningRunner.movieRepository = movieRepository;
    }

    private MovieRequest movieRequest;
    private ScreeningRequest screeningRequest;
    private User admin;
    private long id;


    @Setup(Level.Trial)
    public void setupBenchmark() {

        admin = User.builder()
                .name("Jakub")
                .password("kicia.?312312312As")
                .username("emailpl@gmail.com")
                .surname("Wysocki")
                .userRole(UserRole.ADMIN)
                .build();

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

        screeningRequest = ScreeningRequest.builder()
                .movieId(1L)
                .dayOfScreening(LocalDate.now())
                .hourOfScreening(LocalTime.now())
                .build();
        if(screeningRepository.findById(1L).isEmpty()){
            Screening screening = Screening.builder()
                    .screeningId(1L)
                    .dayOfScreening(LocalDate.now())
                    .hourOfScreening(LocalTime.of(12, 45))
                    .build();
            screeningRepository.save(screening);
        }
        this.id = 1L;


    }

    @Benchmark
    public void createScreeningBenchmark() {
        screeningService.createScreening(screeningRequest,admin);
    }

    @Benchmark
    public void getScreeningDetailsBenchmark() {
        screeningService.getScreeningDetails(this.id);
    }
    @Benchmark
    public void getScreeningsForGivenDayBenchmark() {
        screeningService.getScreeningsForGivenDay(LocalDate.now());
    }


}
