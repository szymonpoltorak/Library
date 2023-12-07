package pl.edu.pw.ee.cinemabackend.jmh;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pw.ee.cinemabackend.api.movies.data.MovieRequest;
import pl.edu.pw.ee.cinemabackend.api.movies.interfaces.MovieService;
import pl.edu.pw.ee.cinemabackend.entities.user.User;
import pl.edu.pw.ee.cinemabackend.entities.user.UserRole;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@RunWith(SpringRunner.class)
public class BenchmarkRunner extends AbstractBenchmark {
    @Test
    public static void main(String[] args) throws RunnerException {
        new BenchmarkRunner().executeJmhRunner();
    }

    /**
     * The most important thing is to get Spring (autowired) components into the executing
     * benchmark instance. To accomplish this you can do the following
     *
     *  * set `forks` to 0 for the JMH runner to run the benchmarks within the same VM
     *  * store all @Autowired dependencies into static fields of the surrounding class
     *
     */
    private static MovieService MS;


    @Autowired
    void setDslContext(MovieService MSContext) {
        BenchmarkRunner.MS = MSContext;
    }

    // this variable is set during the benchmarks setup and is accessible by the benchmark method
    MovieRequest someRequest;
    User adimn;

    /*
     * There is no @Test annotated method within here, because the AbstractBenchmark
     * defines one, which spawns the JMH runner. This class only contains JMH/Benchmark
     * related code.
     */

    @Setup(Level.Trial)
    public void setupBenchmark() {
        someRequest = MovieRequest.builder()
                .title("title")
                .description("bench")
                .minimalAge(12)
                .timeDuration("2")
                .build();

        adimn = User.builder()
                .name("Jakub")
                .password("kicia.?312312312As")
                .username("emailpl@gmail.com")
                .surname("Wysocki")
                .userRole(UserRole.ADMIN)
                .build();
    }

    @Benchmark
    public void someBenchmarkMethod() {
        // query the database
        MS.createMovie(someRequest,adimn);
    }
}
