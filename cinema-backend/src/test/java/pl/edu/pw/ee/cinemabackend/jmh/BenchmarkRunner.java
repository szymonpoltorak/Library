package pl.edu.pw.ee.cinemabackend.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.pw.ee.cinemabackend.api.movies.data.MovieRequest;
import pl.edu.pw.ee.cinemabackend.api.movies.interfaces.MovieService;
import pl.edu.pw.ee.cinemabackend.entities.user.User;
import pl.edu.pw.ee.cinemabackend.entities.user.UserRole;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class BenchmarkRunner extends AbstractBenchmark {
    private static MovieService MS;

    @Autowired
    void setDslContext(MovieService MSContext) {
        BenchmarkRunner.MS = MSContext;
    }

    MovieRequest someRequest;
    User admin;

    @Setup(Level.Trial)
    public void setupBenchmark() {
        someRequest = MovieRequest.builder()
                .title("title")
                .description("bench")
                .minimalAge(12)
                .timeDuration("2")
                .build();

        admin = User.builder()
                .name("Jakub")
                .password("kicia.?312312312As")
                .username("emailpl@gmail.com")
                .surname("Wysocki")
                .userRole(UserRole.ADMIN)
                .build();
    }

    @Benchmark
    public void someBenchmarkMethod() {
        MS.createMovie(someRequest, admin);
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(BenchmarkRunner.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(options).run();
    }
}
