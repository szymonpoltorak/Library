package pl.edu.pw.ee.cinemabackend.jmh;

import org.openjdk.jmh.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.edu.pw.ee.cinemabackend.api.auth.data.AuthResponse;
import pl.edu.pw.ee.cinemabackend.api.auth.data.LoginRequest;
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
public class BenchmarkAuthRefreshTokenRunner extends AbstractBenchmark {
    private static AuthService authService;
    private static UserRepository userRepository;
    private static TokenRepository tokenRepository;
    private static final String LOGMAIL = "logusername@mail.com";
    private static final String PASSWORD = "kicia.?312312312As";
    private static PasswordEncoder passwordEncoder;
    private LoginRequest loginRequest;
    private AuthResponse authResponse;
    private User admin;

    @Autowired
    public final void setContext(AuthService authService, UserRepository userRepository, TokenRepository tokenRepository, PasswordEncoder passwordEncoder) {
        BenchmarkAuthRefreshTokenRunner.authService = authService;
        BenchmarkAuthRefreshTokenRunner.userRepository = userRepository;
        BenchmarkAuthRefreshTokenRunner.tokenRepository = tokenRepository;
        BenchmarkAuthRefreshTokenRunner.passwordEncoder = passwordEncoder;
    }

    @Setup(Level.Trial)
    public final void setupBenchmarkTrial() {

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
    public final void setupBenchmark() {
        userRepository.saveAndFlush(admin);

        authResponse = authService.login(loginRequest);
        tokenRepository.deleteAll();
    }

    @TearDown(Level.Invocation)
    public final void clear() {
        tokenRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Benchmark
    public final void refreshTokenBenchmark() {
        authService.refreshToken(authResponse.refreshToken());
    }

}
