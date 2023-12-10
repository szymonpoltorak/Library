package pl.edu.pw.ee.cinemabackend.jmh;

import org.openjdk.jmh.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.edu.pw.ee.cinemabackend.api.auth.data.LoginRequest;
import pl.edu.pw.ee.cinemabackend.api.auth.data.RegisterRequest;
import pl.edu.pw.ee.cinemabackend.api.auth.interfaces.AuthService;
import pl.edu.pw.ee.cinemabackend.entities.token.interfaces.TokenRepository;
import pl.edu.pw.ee.cinemabackend.entities.user.User;
import pl.edu.pw.ee.cinemabackend.entities.user.UserRole;
import pl.edu.pw.ee.cinemabackend.entities.user.interfaces.UserRepository;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class BenchmarkAuthRunner extends AbstractBenchmark {
    private static AuthService authService;
    private static UserRepository userRepository;
    private static TokenRepository tokenRepository;
    private static final String LOGMAIL = "logusername@mail.com";
    private static final String PASSWORD = "kicia.?312312312As";
    private static PasswordEncoder passwordEncoder;
    private RegisterRequest registerRequest;
    private LoginRequest loginRequest;
    private User admin;

    @Autowired
    final public void setContext(AuthService authService, UserRepository userRepository, TokenRepository tokenRepository, PasswordEncoder passwordEncoder) {
        BenchmarkAuthRunner.authService = authService;
        BenchmarkAuthRunner.userRepository = userRepository;
        BenchmarkAuthRunner.tokenRepository = tokenRepository;
        BenchmarkAuthRunner.passwordEncoder = passwordEncoder;
    }

    @Setup(Level.Trial)
    final public void setBenchmarkTrial() {
        registerRequest = RegisterRequest
                .builder()
                .username("username@mail.com")
                .name("John")
                .surname("Doe")
                .password("kicia.?312312312As")
                .userRole(UserRole.USER)
                .build();
        admin = User.builder()
                .name("Jakub")
                .password(passwordEncoder.encode(PASSWORD))
                .username(LOGMAIL)
                .surname("Wysocki")
                .userRole(UserRole.ADMIN)
                .build();

        loginRequest = LoginRequest
                .builder()
                .username(LOGMAIL)
                .password(PASSWORD)
                .build();
    }

    @Setup(Level.Invocation)
    final public void setupBenchmark() {
        userRepository.saveAndFlush(admin);
    }

    @TearDown(Level.Invocation)
    final public void clear() {
        tokenRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Benchmark
    final public void loginBenchmark() {
        authService.login(loginRequest);
    }

    @Benchmark
    final public void registerBenchmark() {
        authService.register(registerRequest);
    }

}
